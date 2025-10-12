package ar.edu.unlam.cBar;

public abstract class Producto {
	protected String nombre;
	protected double precioBase;
	protected int stock;

	public Producto(String nombre, double precioBase, int stock) {
		super();
		this.nombre = nombre;
		this.precioBase = precioBase;
		this.stock = stock;
	}

	protected abstract double calcularPrecioFinal();

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecioBase() {
		return precioBase;
	}

	public void setPrecioBase(double precioBase) {
		this.precioBase = precioBase;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	} 
	
	
	
}
