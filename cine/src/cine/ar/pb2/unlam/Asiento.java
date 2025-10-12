package cine.ar.pb2.unlam;

public class Asiento {
	private String nombreComprador;
	private boolean ocupado;

	public Asiento() {
		this.liberar();
	}

	public Asiento(String nombreComprador, boolean estado) {
		this.nombreComprador = nombreComprador;
		this.ocupado = estado;
	}

	public String getNombreComprador() {
		return nombreComprador;
	}

	public void ocupar(String nombreComprador) {
		this.nombreComprador = nombreComprador;
		this.ocupado = true;
	}

	public void liberar() {
		this.nombreComprador = "";
		this.ocupado = false;
	}

	public boolean estaOcupado() {
		return ocupado;
	}

}
