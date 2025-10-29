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
		candyBar = new CandyBar(5); 
		palomitas = new Snack("Palomitas", 5.0, Tamano.MEDIANO, 10);
		refresco = new Bebida("Refresco", 3.0, Contenedor.VASO, 15);
	}

	@Test
	public void testAgregarProducto() throws ParametroInvalidoException, ProductoDuplicadoException {
		assertTrue(candyBar.agregarProducto(palomitas));
		assertEquals(1, candyBar.contarProductosEnInventario());

		assertTrue(candyBar.agregarProducto(refresco));
		assertEquals(2, candyBar.contarProductosEnInventario());

		try {
			candyBar.agregarProducto(palomitas); // Intentamos agregar palomitas de nuevo
			fail("Se esperaba una excepción ProductoDuplicadoException");
		} catch (ProductoDuplicadoException e) {
		}
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
}
