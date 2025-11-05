package ar.edu.unlam.cBar;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import ar.edu.unlam.cBar.Bebida;
import ar.edu.unlam.cBar.CandyBar;
import ar.edu.unlam.cBar.Contenedor;
import ar.edu.unlam.cBar.Producto;
import ar.edu.unlam.cBar.Snack;
import ar.edu.unlam.cBar.Tamano;

public class CandyBarTest {

	private CandyBar candyBar;
	private Snack palomitas;
	private Bebida refresco;

	@Before
	public void setUp() {
		candyBar = new CandyBar(5); // Capacidad para 5 productos
		palomitas = new Snack("Palomitas", 5.0, Tamano.MEDIANO, 10);
		refresco = new Bebida("Refresco", 3.0, Contenedor.VASO, 15);
	}

	@Test
	public void testAgregarProducto() throws ParametroInvalidoException, ProductoDuplicadoException {
		assertTrue(candyBar.agregarProducto(palomitas));
		assertEquals(1, candyBar.contarProductosEnInventario());

		assertTrue(candyBar.agregarProducto(refresco));
		assertEquals(2, candyBar.contarProductosEnInventario());
	}

	@Test(expected = ProductoDuplicadoException.class)
	public void testAgregarProductoDuplicado() throws ParametroInvalidoException, ProductoDuplicadoException {
		candyBar.agregarProducto(palomitas); // Agregamos "Palomitas" al inventario
		candyBar.agregarProducto(palomitas); // Intentamos agregar el mismo producto
	}

	@Test
	public void testEliminarProductoExistente() {
		try {
			candyBar.agregarProducto(palomitas);
			candyBar.agregarProducto(refresco);

			boolean eliminado = candyBar.eliminarProducto("Palomitas");
			assertTrue(eliminado);
			assertEquals(1, candyBar.contarProductosEnInventario());

		} catch (ProductoDuplicadoException | ParametroInvalidoException | ProductoNoEncontradoException e) {
			fail("Excepción inesperada: " + e.getMessage());
		}
	}

	@Test(expected = ParametroInvalidoException.class)
	public void testInventarioLleno() throws ParametroInvalidoException, ProductoDuplicadoException {
		// Llenamos el inventario hasta su capacidad máxima (5 productos)
		for (int i = 0; i < 5; i++) {
			candyBar.agregarProducto(new Snack("Snack " + i, 1.0, Tamano.MEDIANO, 5));
		}

		// Intentamos agregar un sexto producto, lo cual debería lanzar una excepción
		candyBar.agregarProducto(new Snack("Snack Extra", 1.0, Tamano.MEDIANO, 3));
	}

	@Test(expected = ParametroInvalidoException.class)
	public void testAgregarProductoNull() throws ParametroInvalidoException, ProductoDuplicadoException {
		candyBar.agregarProducto(null); 
	}

	

	@Test(expected = ParametroInvalidoException.class)
	public void testAgregarProductoExcesoCapacidad() throws ParametroInvalidoException, ProductoDuplicadoException {
		for (int i = 0; i < 5; i++) {
			candyBar.agregarProducto(new Snack("Snack " + i, 1.0, Tamano.MEDIANO, 5));
		}

		candyBar.agregarProducto(new Snack("Snack Extra", 1.0, Tamano.MEDIANO, 3));
	}

	@Test(expected = ParametroInvalidoException.class)
	public void testEliminarProductoNull() throws ProductoNoEncontradoException, ParametroInvalidoException {
		candyBar.eliminarProducto(null);
	}

	@Test(expected = ProductoNoEncontradoException.class)
	public void testEliminarProductoNoExistente() throws ProductoNoEncontradoException, ParametroInvalidoException {
		candyBar.eliminarProducto("ProductoInexistente");
	}

	@Test
	public void testAgregarProductosHastaCapacidadMaxima() {
		try {
			for (int i = 0; i < 5; i++) {
				candyBar.agregarProducto(new Snack("Snack " + i, 1.0, Tamano.MEDIANO, 5));
			}

			assertEquals(5, candyBar.contarProductosEnInventario());

		} catch (ProductoDuplicadoException | ParametroInvalidoException e) {
			fail("Excepción inesperada: " + e.getMessage());
		}
	}

	
	
	 @Test
	    public void testMostrarProductosDevuelveCopiaSegura() throws Exception {
	        Bebida b1 = new Bebida("Cola", 20.0, Contenedor.LATA, 5);
	        Snack s1 = new Snack("Alfajor", 30.0, Tamano.MEDIANO, 10);

	        candyBar.agregarProducto(b1);
	        candyBar.agregarProducto(s1);

	        Set<Producto> copia = candyBar.mostrarProductos();
	        int tamOriginal = candyBar.contarProductosEnInventario();

	        copia.add(new Bebida("Falsa", 1.0, Contenedor.VASO, 1));

	        assertEquals("La modificación de la colección devuelta no debe alterar la colección interna",
	                     tamOriginal, candyBar.contarProductosEnInventario());
	    }

	    @Test
	    public void testObtenerBebidasOrdenadasPorPrecio() throws Exception {
	        Bebida b1 = new Bebida("Baja", 40.0, Contenedor.LATA, 5);
	        Bebida b2 = new Bebida("Media", 35.0, Contenedor.BOTELLA, 5);
	        Bebida b3 = new Bebida("Barata", 20.0, Contenedor.VASO, 5);
	        Snack s1 = new Snack("SnackX", 10.0, Tamano.MEDIANO, 2);

	        candyBar.agregarProducto(b1);
	        candyBar.agregarProducto(b2);
	        candyBar.agregarProducto(b3);
	        candyBar.agregarProducto(s1);

	        List<Bebida> ordenadas = candyBar.obtenerBebidasOrdenadasPorPrecio();

	        assertEquals("Debe contener las 3 bebidas", 3, ordenadas.size());
	        assertEquals(20.0, ordenadas.get(0).getPrecioBase(), 0.0001);
	        assertEquals(35.0, ordenadas.get(1).getPrecioBase(), 0.0001);
	        assertEquals(40.0, ordenadas.get(2).getPrecioBase(), 0.0001);
	    }

	    @Test
	    public void testObtenerSnacksOrdenadosPorNombre() throws Exception {
	        Snack s1 = new Snack("brownie", 25.0, Tamano.MEDIANO, 3);
	        Snack s2 = new Snack("Alfajor", 15.0, Tamano.PEQUENO, 4);
	        Snack s3 = new Snack("chocolatina", 12.0, Tamano.GRANDE, 2);
	        Bebida b1 = new Bebida("Ignorado", 5.0, Contenedor.VASO, 1);

	        candyBar.agregarProducto(s1);
	        candyBar.agregarProducto(s2);
	        candyBar.agregarProducto(s3);
	        candyBar.agregarProducto(b1);

	        List<Snack> ordenadas = candyBar.obtenerSnacksOrdenadosPorNombre();

	        assertEquals("Debe contener las 3 snacks", 3, ordenadas.size());
	        assertEquals("Alfajor", ordenadas.get(0).getNombre());
	        assertEquals("brownie", ordenadas.get(1).getNombre());
	        assertEquals("chocolatina", ordenadas.get(2).getNombre());
	    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
