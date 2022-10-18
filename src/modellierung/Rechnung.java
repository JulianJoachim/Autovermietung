package modellierung;

import java.io.Serializable;

/**
 * @author Julian Joachim s0574380<br>
 * @version Eclipse 2019-09 4.1.3 / Java 1.8.0_ 191<br>
 * @since 07.05.2020 zuletzt bearbeitet am 02.06.2020. <br>
 *        Klasse Rechnung, beinhaltet dessen Attribute und Methoden.
 *
 */
public class Rechnung implements Serializable{
	/**
	 * Standard ID um die Seriennummer der Serialisierung zu überwachen.
	 */
	private static final long serialVersionUID = 1L;
	/** Nummer der Rechnung, beginnt bei 1. */
	int rechnungsnummer = 0;
	/**  anzahl der Kilometer die mit dem geliehenen auto gefahren wurden. */
	double gefahreneKilometer = 0;
	/** Kosten der automietung.*/
	double rechnungsbetrag = 0;
	/** ob die Rechnung bereits bezahlt wurde.*/
	boolean bezahlt = false;
	/** Kundennummer des zugehoerigen Kunden. */
	int zugKundenNummer = 0;

	/**
	 * Standardkonstruktor der Klasse Rechnung.
	 */
	public Rechnung() {
	}

	/**
	 * Konstruktor der Klasse Rechnung, der dessen attribute einen Initialen Wert zuweist.
	 * @param rechnungsnummer - Nummer der Rechnung, beginnt bei 1.
	 * @param gefahreneKilometer -  anzahl der Kilometer die mit dem geliehenen auto gefahren wurden.
	 * @param rechnungsbetrag - Kosten der automietung.
	 * @param bezahlt - ob die Rechnung bereits bezahlt wurde, in der Regel ist das nicht moeglich, es sei denn die Rechnung wird spaeter angelegt.
	 * @param zugKundenNummer - Kundennummer des zugehoerigen Kunden.
	 */
	public Rechnung(int rechnungsnummer, double gefahreneKilometer, double rechnungsbetrag, boolean bezahlt,
			int zugKundenNummer) {
		this.rechnungsnummer = rechnungsnummer;
		this.gefahreneKilometer = gefahreneKilometer;
		this.rechnungsbetrag = rechnungsbetrag;
		this.bezahlt = bezahlt;
		this.zugKundenNummer = zugKundenNummer;
	}

	/**
	 * Gibt die Rechnungsnummer zurueck
	 * @return Rechnungsnummer
	 */
	public int getRechnungsnummer() {
		return rechnungsnummer;
	}

	/**
	 * Gibt aus wieviele Kilometer mit dem auto gefahren wurden
	 * @return gefahrene Kilometer
	 */
	public double getGefahreneKilometer() {
		return gefahreneKilometer;
	}

	/**
	 * Hoehe der Rechnung
	 * @return Rechnungsbetrag in Euro
	 */
	public double getRechnungsbetrag() {
		return rechnungsbetrag;
	}
	
	/**
	 * aendert den Betrag der Rechnung
	 * @param betrag neuer Betrag.
	 */
	public void setRechnungsbetrag(double betrag) {
		this.rechnungsbetrag = betrag;
	}

	/**
	 * Gibt true zurueck wenn die Rechnung bezahlt ist, false wenn nicht.
	 * @return Bezahlstatus der Rechnung.
	 */
	public boolean isBezahlt() {
		return bezahlt;
	}

	/**
	 * Setzt einen neuen Wert in den Bezahlungsstatus. In der Regel true, in ausnahmefaellen kann er aber auch nachtraeglich auf false gesetzt werden.
	 * @param bezahlt ob die Rechnung bezahlt ist
	 */
	public void setBezahlt(boolean bezahlt) {
		this.bezahlt = bezahlt;
	}

	/**
	 * Gibt die zur Rechnung gehoerende Kundennummer.
	 * @return zugehoerige Kundennummer
	 */
	public int getZugKundenNummer() {
		return zugKundenNummer;
	}

	/**
	 * Ordnet die Rechnung einem neuen Kunden ueber die Kundennummer zu. In der Regel nur bei Tippfehler zu benutzen.
	 * @param zugKundenNummer die neue Kundennummer
	 */
	public void setZugKundenNummer(int zugKundenNummer) {
		this.zugKundenNummer = zugKundenNummer;
	}

	/**
	 * Ueberschreibt die toString-Methode um all Objekte der Rechnung auszugeben.
	 */
	@Override
	public String toString() {
		return "[Rechnungsnummer: " + rechnungsnummer + System.lineSeparator() + 
				"gefahrene Kilometer: " + gefahreneKilometer + System.lineSeparator() + 
				"Rechnungsbetrag: " + rechnungsbetrag + System.lineSeparator() +
				"bezahlt: " + bezahlt + System.lineSeparator() +
				"zugehoerige Kundenummer: " + zugKundenNummer + "]" + System.lineSeparator();
	}

}
