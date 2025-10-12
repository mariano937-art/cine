package ar.edu.unlam.cBar;
import ar.edu.unlam.cBar.Contenedor;
public class Bebida extends Producto {
//	• Bebida: Representa refrescos y jugos. El precio final tiene +10% de recargo en concepto de envase
//	si su contenedor es una botella.
	private Contenedor contenedor;
	public Bebida(String nombre, double precioBase, Contenedor contenedor, int stock) {
		super(nombre,precioBase,stock);
		this.contenedor=contenedor;
	}

	@Override
	public double calcularPrecioFinal() {
		double precioFinal=precioBase;
		if(this.contenedor==contenedor.BOTELLA) {
			precioFinal=precioBase*1.1;
			//precioBase+precioBase*0.1;seria lo mismo
		}
		
		return precioFinal;
	}

}
