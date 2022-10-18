package modellierung;

/**
 * @author Julian Joachim s0574380<br>
 * @version Eclipse 2019-09 4.1.3 / Java 1.8.0_ 191<br>
 * @since 14.06.2020 zuletzt bearbeitet am 16.06.2020<br>
 *        Das Interface LoggingVerhalten ist das Grundgerüst des
 *        Strategie-Patterns für das erstellen von Logs.
 * 
 *
 */
public interface LoggingVerhalten {

	/**
	 * Allgemein gültige Methode, die im spezifischen einen Loggingprozess erfüllen
	 * soll.
	 * 
	 * @param r die Rechnung die geloggt wird
	 * @param k die Kunde, zu dem die Rechnung gehört
	 */
	public void log(Rechnung r, Kunde k);
}
