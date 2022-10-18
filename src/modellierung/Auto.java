package modellierung;

import java.io.Serializable;

/**
 * @author Julian Joachim s0574380<br>
 * @version Eclipse 2019-09 4.1.3 / Java 1.8.0_ 191<br>
 * @since 03.04.2020 zuletzt bearbeitet am 01.06.2020<br>
 * 
 *        Klasse Auto, beinhaltet dessen Attribute und Methoden.
 *
 */

public class Auto implements Serializable{

	/**
	 * Standard ID um die Seriennummer der Serialisierung zu überwachen.
	 */
	private static final long serialVersionUID = 1L;
	/** Marke des Autos. */
	private String marke = "";
	/** aktueller Kilometerstand des Autos. */
	private double kmStand = 0;
	/** Preis pro gefahrenem Kilometer. */
	private double preisProKM = 0;
	/** Kennzeichen des Autos. */
	private String kennzeichen = "";
	/** Ob das Auto vermietet ist. */
	private boolean istVermietet = false;
	/** Es wird eine Wunschformatierung fuer das Ausgeben der Autodaten kreiert. */
	private String formatAuto = "|%1$-15s|%2$-20s|%3$-20s|%4$-15s\n";

	/**
	 * Standardkonstruktor der Klasse Auto.
	 */
	public Auto() {
	}

	/**
	 * Konstruktor um alle Attribute der Klasse Auto zu initialisieren.
	 * 
	 * @param marke        Marke des Autos.
	 * @param kmStand      derzeitiger Kilometerstand des Autos.
	 * @param preisProKM   Preis pro gefahrenem Kilometer.
	 * @param kennzeichen  Kennzeichen des Autos.
	 * @param istVermietet Ob das Auto derzeit vermietet ist.
	 * 
	 */

	public Auto(String marke, double kmStand, double preisProKM, String kennzeichen, boolean istVermietet) {
		this.marke = marke;
		this.kmStand = kmStand;
		this.preisProKM = preisProKM;
		this.kennzeichen = kennzeichen;
		this.istVermietet = istVermietet;
	}

	/**
	 * @return gibt den aktuellen Kilometerstand des Autos zurueck.
	 */
	public double getKmStand() {
		return kmStand;
	}

	/**
	 * Setzt den aktuellen Kilometerstand.
	 * 
	 * @param kmStand Der uebergebene Kilometerstand
	 */
	public void setKmStand(double kmStand) {
		if (kmStand < 0) {
			System.out.println("Kilometerstand darf nicht negativ sein.");
		} else {
			this.kmStand = kmStand;
		}
	}

	/**
	 * @return gibt den Preis des Autos pro gefahrenem Kilometer zurueck, in Euro.
	 */
	public double getPreisProKM() {
		return preisProKM;
	}

	/**
	 * Setzt einen neuen Preis pro gefahrenem Kilometer, in Euro.
	 * 
	 * @param preisProKM Betrag in Euro.
	 * 
	 */
	public void setPreisProKM(double preisProKM) {
		if (this.preisProKM < 0) {
			System.out.println("Preis pro Kilometer darf nicht negativ sein.");
		} else {
			this.preisProKM = preisProKM;
		}

	}

	/**
	 * @return gibt das Kennzeichen des derzeitigen Autos aus.
	 */
	public String getKennzeichen() {
		return kennzeichen;
	}

	/**
	 * Setzt ein neues Kennzeichen fuer das derzeitige Auto.
	 * 
	 * @param kennzeichen uebergebenes Kennzeichen des Autos
	 */
	public void setKennzeichen(String kennzeichen) {
		this.kennzeichen = kennzeichen;
	}

	/**
	 * 
	 * @return gibt die Marke des derzeitigen Autos aus.
	 */
	public String getMarke() {
		return this.marke;
	}

	/**
	 * 
	 * @return Ob das ausgewaehlte Auto derzeit vermietet ist.
	 */
	public boolean isIstVermietet() {
		return istVermietet;
	}

	/**
	 * Setzt den Status des Autos. Wenn true ist das Auto vermietet, bei false
	 * nicht.
	 * 
	 * @param istVermietet Wert der aussagt ob ds Auto vermietet ist, oder nicht.
	 */
	public void setIstVermietet(boolean istVermietet) {
		this.istVermietet = istVermietet;
	}

	/**
	 * Gibt den Inhalt aller Attribute der Klasse Auto in einem tabellelarischen
	 * Format aus.
	 */
	public void getFormat() {
		System.out.format(formatAuto, this.marke, this.kmStand, this.preisProKM, this.kennzeichen);
	}

	/**
	 * Gibt eine ueberschrift fuer die Ausgabe des Autos aus.
	 */
	public void getFormatHeader() {
		System.out.format(formatAuto, "Marke:", "Kilometerstand:", "Preis pro KM:", "Kennzeichen");
	}

	/**
	 * ueberschreibt die Standart toString() Methode um stattdessen gewuenschte
	 * Attribute auszugeben.
	 * 
	 * @return Alle Attribute der Klasse "Auto" (marke, kmStand, preisProKM und
	 *         kennzeichen).
	 */
	@Override
	public String toString() {
		return marke + " mit einem Kilometerstand von " + kmStand + ", der Preis pro Kilometer ist " + preisProKM + "€"
				+ ", Das Kennzeichen ist " + kennzeichen + ".";
	}

}
