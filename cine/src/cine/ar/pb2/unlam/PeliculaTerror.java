package cine.ar.pb2.unlam;

public class PeliculaTerror extends Pelicula {
	public PeliculaTerror(String titulo, int duracion) {
		super(titulo, duracion, 18);

	}

	@Override
	public String mostrarSinopsis() {
		String sinopsis = "Datos de la pelicula" + '\n';
		sinopsis += "Titulo: " + super.getTitulo() + '\n';
		sinopsis += "Duración en minutos: " + super.getDuracion() + '\n';
		sinopsis += "Edad Minima: " + super.getEdadMinima() + '\n';
		sinopsis += "Sinopsis : Las peliculas de accion se caracterizan por tener un alto grado de violancia, explosibos impresionantes y heroes que salvan al mundo.";
		return sinopsis;

	}

}
