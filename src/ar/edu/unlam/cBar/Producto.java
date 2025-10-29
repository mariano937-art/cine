package ar.edu.unlam.cBar;

public abstract class Producto implements Comparable<Producto> {

	protected String nombre;
	protected double precioBase;
	protected int stock;

	public Producto(String nombre, double precioBase, int stock) {
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

	// orden natural por cantidad de stock
	@Override
	public int compareTo(Producto otro) {
		int comp = Integer.compare(this.stock, otro.stock);
		if (comp == 0) {
			return this.nombre.compareToIgnoreCase(otro.getNombre());
		}
		return comp;
	}

	// Método equals para comparar productos por su nombre
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Producto producto = (Producto) obj;
		return nombre.equalsIgnoreCase(producto.nombre); // Compara solo por nombre
	}

	// Método hashCode para evitar duplicados en colecciones
	@Override
	public int hashCode() {
		return this.nombre.toLowerCase().hashCode(); // Usa el nombre para hash
	}

	// Método toString para representación en forma de cadena
	@Override
	public String toString() {
		return nombre + "(stock=" + stock + ", precioBase=" + precioBase + ")";
	}

}
