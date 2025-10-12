package ar.edu.unlam.cBar;

public class Combo extends Producto {
private Snack snack;
private Bebida bebida;
private double porcentajeDeDescuento;
//Combo: Representa una combinación de un Snack y una Bebida. El precio final es la suma de los
//precios de sus componentes, con un descuento personalizable

	public Combo(String nombre, Snack snack, Bebida bebida, double porcentajeDeDescuento	, int stock) {
		super(nombre,(snack.getPrecioBase()+bebida.getPrecioBase()),stock);//precio de combo calculado aca
		this.snack=snack;
		this.bebida=bebida;
		this.porcentajeDeDescuento=porcentajeDeDescuento;
	}
@Override
protected double calcularPrecioFinal() {
	return precioBase-precioBase*porcentajeDeDescuento;
	//precioBase*(1-porcentajeDeDescuento);
	//(precioBaae*1)-(precioBase*porcentajeDeDescuento);
}

	
}
