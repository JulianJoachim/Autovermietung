package modellierung;

import java.util.Comparator;

/**
 * @author Julian Joachim s0574380<br>
 * @version Eclipse 2019-09 4.1.3 / Java 1.8.0_ 191<br>
 * @since 01.06.2020 zuletzt bearbeitet am 02.06.2020<br>
 * 
 * Klasse SortByName ist eine Hilfsklasse. Sie erbt vom Comparator
 * und dient zum vergleichen von Kundenarrays, via dessen Namen.
 */
public class SortByName implements Comparator<Kunde> {
	
	/**
	 * Überschreibt die compare Methode vom Comparator.
	 * @return positiv, wenn o1 größer o2; negativ, wenn o1 kleiner o2; 0 wenn gleich.
	 * 
	 */
	@Override
	public int compare(Kunde o1, Kunde o2) {
		return o1.getName().toLowerCase().compareTo(o2.getName().toLowerCase());
	}

}
