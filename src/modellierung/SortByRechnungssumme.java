package modellierung;

import java.util.Comparator;

/**
 * @author Julian Joachim s0574380<br>
 * @version Eclipse 2019-09 4.1.3 / Java 1.8.0_ 191<br>
 * @since 30.05.2020 zuletzt bearbeitet am 02.06.2020<br>
 * 
 * Klasse SortByRechnungsnummer ist eine Hilfsklasse. Sie erbt vom Comparator
 * und dient zum vergleichen von Rechnungsarrays, via dessen Rechnungsbetrag.
 */

public class SortByRechnungssumme implements Comparator<Rechnung> {

	/**
	 * Überschreibt die compare methode vom Comparator.
	 * @return positiv, wenn o1 größer o2; negativ, wenn o1 kleiner o2; 0 wenn gleich.
	 * 
	 */
	@Override
	public int compare(Rechnung o1, Rechnung o2) {
		return Double.compare(o1.getRechnungsbetrag(), o2.getRechnungsbetrag());
	}

}
