package ar.edu.unlam.cBar;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class CandyBar {

	private Set<Producto> productos;
	private int capacidadMaxima;

	public CandyBar(int capacidadMaxima) {
		this.productos = new TreeSet<>();
		this.capacidadMaxima = capacidadMaxima;
	}

	// Método para agregar un producto

	public boolean agregarProducto(Producto producto) throws ParametroInvalidoException, ProductoDuplicadoException {
		if (producto == null) {
			throw new ParametroInvalidoException("Producto no puede ser nulo");
		}

		// Verificar capacidad
		if (productos.size() >= capacidadMaxima) {
			throw new ParametroInvalidoException("El inventario está lleno");
		}

		// Verificar si el producto ya existe
		if (productos.contains(producto)) {
			throw new ProductoDuplicadoException("El producto ya existe en el inventario");
		}

		// Agregar el producto
		return productos.add(producto);
	}

	public boolean eliminarProducto(String nombre) throws ProductoNoEncontradoException, ParametroInvalidoException {
		if (nombre == null)
			throw new ParametroInvalidoException("El nombre no puede ser nulo");

		Producto encontrado = null;
		for (Producto p : productos) {
			if (p.getNombre().equalsIgnoreCase(nombre)) {
				encontrado = p;
				break;
			}
		}
		if (encontrado == null) {
			throw new ProductoNoEncontradoException("No se encontró producto con nombre: " + nombre);
		}

		productos.remove(encontrado);
		return true;
	}

	public Producto[] obtenerInventario() {
		return productos.toArray(new Producto[0]);
	}

	// Método para contar los productos en el inventario
	public int contarProductosEnInventario() {
		return productos.size();
	}

	public Set<Producto> mostrarProductos() {
		return new TreeSet<>(productos);
	}

	public List<Bebida> obtenerBebidasOrdenadasPorPrecio() {
		List<Bebida> lista = new ArrayList<>();
		for (Producto p : productos) {
			if (p instanceof Bebida) {
				lista.add((Bebida) p);
			}
		}

		lista.sort(Comparator.comparingDouble(Bebida::getPrecioBase));

		return lista;
	}

	public List<Snack> obtenerSnacksOrdenadosPorNombre() {
		List<Snack> lista = new ArrayList<>();
		for (Producto p : productos) {
			if (p instanceof Snack) {
				lista.add((Snack) p);
			}
		}

		lista.sort(Comparator.comparing(Snack::getNombre, String::compareToIgnoreCase));
		return lista;
	}
}
//private Producto[]productos;
//int cantidadDeProductos;
//	public CandyBar(int cantidadDeProductos) {
//		productos=new Producto[cantidadDeProductos];
//	}
//
//	public boolean agregarProducto(Producto producto) {
//		boolean agregado=false;
//		int posicion=0;
//		while(posicion<this.productos.length&&!agregado) {
//			if(this.productos[posicion]==null) {
//				this.productos[posicion]=producto;
//			agregado=true;
//			}
//			posicion++;
//		}
//		
//		return agregado;
//	}
//
//	
//	
//	public boolean eliminarProducto(String nombre) {
//		boolean eliminado=false;
//		int posicion=0;
//		while(posicion<this.productos.length&&!eliminado) {
//			if(this.productos[posicion]!=null) {
//				if(this.productos[posicion].getNombre().equalsIgnoreCase(nombre)) {
//					this.productos[posicion]=null;
//					eliminado=true;
//				}
//				posicion++;
//			}
//		}
//		return eliminado;
//	}
//
//	public Producto[] obtenerInventario() {
//		
//		return this.productos;
//	}

//}
