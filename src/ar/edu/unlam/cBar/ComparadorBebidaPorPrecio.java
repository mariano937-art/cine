package ar.edu.unlam.cBar;

import java.util.Comparator;

public class ComparadorBebidaPorPrecio implements Comparator<Bebida> {
	 @Override
	    public int compare(Bebida b1, Bebida b2) {
	        // Comparar precios de menor a mayor
	        return Double.compare(b1.getPrecioBase(), b2.getPrecioBase());
	    }
	}

