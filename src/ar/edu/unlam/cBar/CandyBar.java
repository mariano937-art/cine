package ar.edu.unlam.cBar;

public class CandyBar {
private Producto[]productos;
int cantidadDeProductos;
	public CandyBar(int cantidadDeProductos) {
		productos=new Producto[cantidadDeProductos];
	}

	public boolean agregarProducto(Producto producto) {
		boolean agregado=false;
		int posicion=0;
		while(posicion<this.productos.length&&!agregado) {
			if(this.productos[posicion]==null) {
				this.productos[posicion]=producto;
			agregado=true;
			}
			posicion++;
		}
		
		return agregado;
	}

	
	
	public boolean eliminarProducto(String nombre) {
		boolean eliminado=false;
		int posicion=0;
		while(posicion<this.productos.length&&!eliminado) {
			if(this.productos[posicion]!=null) {
				if(this.productos[posicion].getNombre().equalsIgnoreCase(nombre)) {
					this.productos[posicion]=null;
					eliminado=true;
				}
				posicion++;
			}
		}
		return eliminado;
	}

	public Producto[] obtenerInventario() {
		
		return this.productos;
	}

}
