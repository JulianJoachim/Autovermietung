package modellierung;

/**
 * @author Julian Joachim s0574380<br>
 * @version Eclipse 2019-09 4.1.3 / Java 1.8.0_ 191<br>
 * @since 14.06.2020 zuletzt bearbeitet am 16.06.2020<br>
 *        Die Klasse Buchhalter ist eine spezifischer Beobachter. Er überwacht
 *        die bezahlten Rechnungen.
 *
 */
public class Buchhalter implements Beobachter {
	private String vorname = "Buch";
	private String nachname = "Halter";

	/**
	 * Standardkonstruktor
	 * 
	 * @param vorname  Vorname des Buchführers
	 * @param nachname Nachname des Buchführers
	 */
	public Buchhalter(String vorname, String nachname) {
		this.vorname = vorname;
		this.nachname = nachname;
	}

	/**
	 * Updatet die Informationen die er erhält, in dem Falle nur durch eine
	 * Konsolenausgabe die den Vornamen, Nachnamen und die Rechnungsnummer ausgibt.
	 */
	@Override
	public void update(Rechnung r, Kunde k, String who) {
		if (who.equals("Buchhalter")) {
			System.out.println(
					"Buchhalter " + vorname + " " + nachname + " hat die Information erhalten, dass die Rechnung Nr. "
							+ r.getRechnungsnummer() + " bezahlt wurde.");

		}
	}
}
