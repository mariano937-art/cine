package cine.ar.pb2.unlam;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

class CineTest {
	Pelicula peliculas[] = new Pelicula[4];
    SalaCine sala1;

    @BeforeEach
    void setUp() {
        peliculas[0] = new PeliculaAccion("Piratas del Caribe 1", 200, 14);
        peliculas[1] = new PeliculaAccion("Misión Imposible", 150, 13);
        peliculas[2] = new PeliculaAccion("John Wick 4", 169, 16);
        peliculas[3] = new PeliculaAccion("Top Gun: Maverick", 130, 13);

        sala1 = new SalaCine(2, 3); // 2 filas (A,B) y 3 columnas (1-3)
    }

    @Test
    void crearSalaOk() {
        sala1.cambiarPelicula(peliculas[0]);
        
        // Total asientos = filas * columnas
        assertEquals(6, sala1.getTotalAsientos());
        
        // Película actual debe ser la misma que asignamos
        assertEquals("Piratas del Caribe 1", sala1.getPeliculaActual().getTitulo());
    }

    @Test
    void venderBoletoExitoso() {
        sala1.cambiarPelicula(peliculas[0]);
        boolean venta1 = sala1.venderBoleto("A", 1, 14, "Julian Morga");
        boolean venta2 = sala1.venderBoleto("A", 2, 16, "Carla Díaz");
        assertTrue(venta1);
        assertTrue(venta2);
    }

    @Test
    void venderBoletoNoExitosoPorqueYaEstaOcupado() {
        sala1.cambiarPelicula(peliculas[0]);
        boolean venta1 = sala1.venderBoleto("A", 1, 18, "Julian Morga");
        boolean venta2 = sala1.venderBoleto("A", 1, 18, "Otro Comprador");
        assertTrue(venta1);
        assertFalse(venta2);
    }

    @Test
    void venderBoletoNoExitosoPorEdadMenorALaMinima() {
        sala1.cambiarPelicula(peliculas[0]);
        boolean venta1 = sala1.venderBoleto("A", 1, 12, "Julian Morga");
        boolean venta2 = sala1.venderBoleto("A", 2, -5, "Juan Pérez");
        assertFalse(venta1);
        assertFalse(venta2);
    }

    @Test
    void obtenerTotalAsientosTest() {
        assertEquals(6, sala1.getTotalAsientos());
    }

    @Test
    void contarAsientosOcupadosTest() {
        sala1.cambiarPelicula(peliculas[0]);
        sala1.venderBoleto("A", 1, 18, "Julian Morga");
        sala1.venderBoleto("A", 2, 18, "Julian Morga");
        sala1.venderBoleto("B", 2, 18, "Julian Morga");

        assertEquals(3, sala1.contarAsientosOcupados());
    }

    @Test
    void liberarAsientoVendido() {
        sala1.cambiarPelicula(peliculas[0]);
        sala1.venderBoleto("A", 1, 18, "Julian Morga");
        sala1.venderBoleto("A", 2, 18, "Julian Morga");
        sala1.venderBoleto("B", 2, 18, "Julian Morga");

        assertEquals(3, sala1.contarAsientosOcupados());
        assertTrue(sala1.liberarAsiento("A", 1));
        assertEquals(2, sala1.contarAsientosOcupados());
        assertTrue(sala1.venderBoleto("A", 1, 18, "Nuevo Comprador"));
    }

    @Test
    void liberarAsientoFueraDeRangoDebeFallar() {
        sala1.cambiarPelicula(peliculas[0]);
        sala1.venderBoleto("A", 1, 18, "Julian Morga");
        sala1.venderBoleto("A", 2, 18, "Julian Morga");

        assertFalse(sala1.liberarAsiento("Z", 10));
        assertFalse(sala1.liberarAsiento("A", 99));
        assertFalse(sala1.liberarAsiento("C", 1));
        assertEquals(2, sala1.contarAsientosOcupados());
    }

    @Test
    void reiniciarSalaLiberaTodosLosAsientos() {
        sala1.cambiarPelicula(peliculas[0]);
        sala1.venderBoleto("A", 1, 18, "Persona 1");
        sala1.venderBoleto("B", 2, 18, "Persona 2");
        assertEquals(2, sala1.contarAsientosOcupados());

        sala1.reiniciarSala();
        assertEquals(0, sala1.contarAsientosOcupados());
    }

    @Test
    void mostrarButacasDetalleDebeMostrarCorrectamente() {
        sala1.cambiarPelicula(peliculas[0]);
        sala1.venderBoleto("A", 1, 18, "Juan Pérez");
        String detalle = sala1.mostrarButacasDetalle();

        assertTrue(detalle.contains("ButacaA1"));
        assertTrue(detalle.contains("Juan Pérez"));
    }

    @Test
    void mostrarButacasDetalleSinOcupadas() {
        String detalle = sala1.mostrarButacasDetalle();
        assertTrue(detalle.contains("no hay butacas opcupadas"));
    }

}
