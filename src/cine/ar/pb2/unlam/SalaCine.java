package cine.ar.pb2.unlam;

import java.util.HashMap;
import java.util.Map;


public class SalaCine {

	private Map<String, Asiento> butacas;
	private Pelicula pelicula;

	public SalaCine(int filas, int columnas) {
		this.butacas = new HashMap<>();
		generarButacas(filas, columnas);// metodo externo que genera las butacas
	}

	public void generarButacas(int filas, int columnas) {
		for (int i = 0; i < filas; i++) {
			char letraFila = (char) ('A' + i);// fila a,b,c,d
			for (int j = 1; j <= columnas; j++) {
				String key = letraFila + String.valueOf(j);// quedaria"A1","B3"
				butacas.put(key, new Asiento());
			}

		}
	}

	public Map<String, Asiento> getButacas() {
		return butacas;
	}

	public Pelicula getPeliculaActual() {
		return pelicula;
	}

	public void cambiarPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}

	public boolean venderBoleto(String filaLetra, int columna, int edad, String nombreComprador) {
		String key = filaLetra.toUpperCase() + columna;
		Asiento asiento = butacas.get(key);
		if (asiento == null)
			return false;
		if (asiento.estaOcupado())
			return false;
		if (pelicula == null)
			return false;
		if (pelicula.getEdadMinima() > edad)
			return false;
		if (nombreComprador == null)
			return false;

		asiento.ocupar(nombreComprador);
		return true;
	}

	public boolean liberarAsiento(String filaLetra, int columna) {
		String key = filaLetra.toUpperCase() + columna;
		Asiento asiento = butacas.get(key);

		if (asiento == null)
			return false;
		asiento.liberar();
		return true;
	}

//uso iterador	
	public int contarAsientosOcupados() {
		int contador = 0;
		for (Map.Entry<String, Asiento> entry : butacas.entrySet()) {
			Asiento asiento = entry.getValue();
			if (asiento.estaOcupado()) {
				contador++;
			}
		}
		return contador;
	}

	public int getTotalAsientos() {
		return butacas.size();
	}

	public String getTitulo() {
		if (pelicula != null) {
			return pelicula.getTitulo();
		}
		return null;
	}

	public void reiniciarSala() {
		for (String key : butacas.keySet()) {
			Asiento asiento = butacas.get(key);
			asiento.liberar();
		}
	}

	public String mostrarButacasDetalle() {
		String mensaje = "Detalle de butacas ocupada:\n";
		boolean butacaOcupada = false;
		for (String clave : butacas.keySet()) {
			Asiento asiento = butacas.get(clave);
			if (asiento.estaOcupado()) {
				mensaje = mensaje + "Butaca" + clave + "-comprador" + asiento.getNombreComprador() + "\n";
				butacaOcupada = true;
			}
		}
		if (!butacaOcupada) {
			mensaje = mensaje + "no hay butacas opcupadas";
		}
		return mensaje;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//	private Asiento[][] butacas;
//	private Pelicula pelicula;
//
//	public SalaCine(int fila, int columna) {
//		this.butacas = new Asiento[fila][columna];
//
//		for (int i = 0; i < fila; i++) {
//			for (int j = 0; j < columna; j++) {
//				this.butacas[i][j] = new Asiento();
//			}
//		}
//	}
//
//	public Asiento[][] getButacas() {
//		return butacas;
//	}
//
//	public int contarAsientosOcupados() {
//		int contador = 0;
//		for (int i = 0; i < butacas.length; i++) {
//			for (int j = 0; j < butacas[i].length; j++) {
//				if (this.butacas[i][j].estaOcupado())
//					contador++;
//
//			}
//		}
//		return contador;
//	}
//
//	public int getTotalAsientos() {
//		return butacas.length * butacas[0].length;
//	}
//
//	public void cambiarPelicula(Pelicula pelicula) {
//		this.pelicula = pelicula;
//	}
//
//	public Pelicula getPeliculaActual() {
//		return pelicula;
//	}
//
//	public boolean venderBoleto(int fila, int columna, int edad, String nombreComprador) {
//
//		if (butacas[fila][columna].estaOcupado())
//			return false;
//
//		if (this.pelicula == null)
//			return false;
//
//		if (this.pelicula.getEdadMinima() > edad)
//			return false;
//
//		if (nombreComprador == null)
//			return false;
//
//		butacas[fila][columna].ocupar(nombreComprador);
//		return true;
//	}
//
//	public String getTitulo() {
//		return pelicula.getTitulo();
//	}
//
//	public boolean liberarAsiento(int fila, int columna) {
//
//		boolean seLibero = false;
//		if (fila < 0 || fila >= butacas.length || columna < 0 || columna >= butacas[0].length) {
//			seLibero = false;
//		}
//		if (fila <= butacas.length && columna <= butacas[0].length) {
//			butacas[fila][columna].liberar();
//			seLibero = true;
//		}
//
//		return seLibero;
//	}
//
//	public void reiniciarSala() {
//		if (this.butacas != null) {
//			for (int i = 0; i < this.butacas.length; i++) {
//				if (this.butacas[i] != null) {
//					for (int j = 0; j < this.butacas[i].length; j++) {
//						if (this.butacas[i][j] != null) {
//							this.butacas[i][j].liberar();
//						}
//
//					}
//				}
//			}
//		}
//	}
//
//	public String mostrarButacasDetalle() {
//		String mensaje = "Detalle de butacas ocupadas" + ":";
//		boolean butacaOcupada = false;
//		for (int i = 0; i < this.butacas.length; i++) {
//			for (int j = 0; j < this.butacas[i].length; j++) {
//				if (this.butacas[i][j].estaOcupado()) {
//					mensaje += "Fila" + i + ",columna" + j + "-comprador:" + this.butacas[i][j].getNombreComprador()
//							+ "\n";
//					butacaOcupada = true;
//				}
//			}
//		}
//		if (!butacaOcupada) {
//			mensaje += "no hay butacas ocupadas";
//		}
//		return mensaje;
//	}

}
