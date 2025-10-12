package ar.edu.unlam.cBar;
import ar.edu.unlam.cBar.Tamano;
public class Snack extends Producto {
	//Snack: Representa productos como palomitas de maíz o nachos. El precio final tiene 20% de
	//recargo si es grande y 15% de descuento si es pequeño.
	private Tamano tamano;
	
	public Snack(String nombre, double precioBase, Tamano tamano, int stock) {
		super(nombre,precioBase,stock);
		this.tamano=tamano;
	}

	@Override
	protected double calcularPrecioFinal() {
double precioFinal=precioBase;
switch(this.tamano) {
case PEQUENO:
	precioFinal=precioBase-(precioBase*0.15);
	break;
	
case GRANDE:
precioFinal=precioBase+(precioBase*0.20);
//formas de hacer lo mismo	
//precioBase*1.20;
//precioBase*1+precioBase*0.20;
	break;
}
		return precioFinal;
	}

	
}

