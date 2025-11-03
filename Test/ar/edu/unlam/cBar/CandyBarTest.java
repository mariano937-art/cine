package ar.edu.unlam.cBar;

import static org.junit.Assert.*;

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

}
