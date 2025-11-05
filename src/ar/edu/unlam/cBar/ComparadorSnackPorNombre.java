package ar.edu.unlam.cBar;

import java.util.Comparator;

public class ComparadorSnackPorNombre implements Comparator<Snack> {
	@Override
    public int compare(Snack s1, Snack s2) {
        // Compara nombres ignorando mayúsculas/minúsculas
        return s1.getNombre().compareToIgnoreCase(s2.getNombre());
}
}