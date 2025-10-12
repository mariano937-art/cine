package cine.ar.pb2.unlam;

public class SalaCine {
	private Asiento[][] butacas;
	private Pelicula pelicula;

	public SalaCine(int fila, int columna) {
		this.butacas = new Asiento[fila][columna];

		for (int i = 0; i < fila; i++) {
			for (int j = 0; j < columna; j++) {
				this.butacas[i][j] = new Asiento();
			}
		}
	}

	public Asiento[][] getButacas() {
		return butacas;
	}

	public int contarAsientosOcupados() {
		int contador = 0;
		for (int i = 0; i < butacas.length; i++) {
			for (int j = 0; j < butacas[i].length; j++) {
				if (this.butacas[i][j].estaOcupado())
					contador++;

			}
		}
		return contador;
	}

	public int getTotalAsientos() {
		return butacas.length * butacas[0].length;
	}

	public void cambiarPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}

	public Pelicula getPeliculaActual() {
		return pelicula;
	}

	public boolean venderBoleto(int fila, int columna, int edad, String nombreComprador) {

		if (butacas[fila][columna].estaOcupado())
			return false;

		if (this.pelicula == null)
			return false;

		if (this.pelicula.getEdadMinima() > edad)
			return false;

		if (nombreComprador == null)
			return false;

		butacas[fila][columna].ocupar(nombreComprador);
		return true;
	}

	public String getTitulo() {
		return pelicula.getTitulo();
	}

	public boolean liberarAsiento(int fila, int columna) {

		boolean seLibero = false;
		if (fila < 0 || fila >= butacas.length || columna < 0 || columna >= butacas[0].length) {
			seLibero = false;
		}
		if (fila <= butacas.length && columna <= butacas[0].length) {
			butacas[fila][columna].liberar();
			seLibero = true;
		}

		return seLibero;
	}

	public void reiniciarSala() {
		if (this.butacas != null) {
			for (int i = 0; i < this.butacas.length; i++) {
				if (this.butacas[i] != null) {
					for (int j = 0; j < this.butacas[i].length; j++) {
						if (this.butacas[i][j] != null) {
							this.butacas[i][j].liberar();
						}

					}
				}
			}
		}
	}

	public String mostrarButacasDetalle() {
		String mensaje = "Detalle de butacas ocupadas" + ":";
		boolean butacaOcupada = false;
		for (int i = 0; i < this.butacas.length; i++) {
			for (int j = 0; j < this.butacas[i].length; j++) {
				if (this.butacas[i][j].estaOcupado()) {
					mensaje += "Fila" + i + ",columna" + j + "-comprador:" + this.butacas[i][j].getNombreComprador()
							+ "\n";
					butacaOcupada = true;
				}
			}
		}
		if (!butacaOcupada) {
			mensaje += "no hay butacas ocupadas";
		}
		return mensaje;
	}

}
