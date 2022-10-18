package modellierung;

/**
 * @author Julian Joachim s0574380<br>
 * @version Eclipse 2019-09 4.1.3 / Java 1.8.0_ 191<br>
 * @since 14.06.2020 zuletzt bearbeitet am 16.06.2020<br>
 *        Das Interface Beobachter ist das Grundgerüst des Observer-Patterns für
 *        das erstellen von Observern.
 * 
 *
 */
public interface Beobachter {

	/**
	 * Allgemein gültige Methode, die im spezifischen einen Observer die möglichkeit
	 * gibt, Daten zu aktualisieren.
	 * 
	 * @param r die Rechnung die verändert wurde
	 * @param k die Kunde, zu dem die Rechnung gehört
	 */
	public void update(Rechnung r, Kunde k, String who);
}
