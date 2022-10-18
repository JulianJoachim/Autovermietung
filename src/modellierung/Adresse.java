package modellierung;

import java.io.Serializable;

/**
 * @author Julian Joachim s0574380<br>
 * @version Eclipse 2019-09 4.1.3 / Java 1.8.0_ 191<br>
 * @since 03.04.2020 zuletzt bearbeitet am 01.06.2020<br>
 * 
 *        Klasse Adresse, beinhaltet dessen Attribute und Methoden.
 *
 */

public class Adresse implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** Erste Adresszeile der Adresse, zum Beispiel Straße und Hausnummer. */
	private String adressSatz1 = "";
	/** Zweite Adresszeile der Adresse, zum Beispiel c/o oder Abteilung. */
	private String adressSatz2 = "";
	/** Ort in welchem die Adresse liegt. */
	private String ort = "";
	/** fuenfstellige Zahl die den Ort ergaenzt. */
	private String plz = "";

	/**
	 * Standard Konstruktor der Klasse Adresse
	 */
	public Adresse() {
	}

	/**
	 * Konstruktor um alle Attribute der Klasse Adresse zu initialisieren.
	 * 
	 * @param adr1 - Erste Adresszeile, zum Beispiel Straße und Hausnummer.
	 * @param adr2 - Zweite Adressezeile, zum Beispiel c/o oder Abteilung.
	 * @param ort  - Ort / Stadt in welcher der Kunde wohnt.
	 * @param plz  - PLZ des Ortes.
	 * 
	 */
	public Adresse(String adr1, String adr2, String ort, String plz) {
		this.adressSatz1 = adr1;
		this.adressSatz2 = adr2;
		this.ort = ort;

		if (plz.matches("[0-9]+")) {
			this.plz = plz;
		} else {
			System.out.println("Postleitzahl darf keine Buchstaben enthalten.");
			this.plz = "12345";
		}
	}

	/**
	 * Konstruktor um alle Attribute der Klasse Adresse zu initialisieren, bis auf
	 * adr2. Wird benutzt fuer Kunden die keine Adresszeile 2 besitzen.
	 * 
	 * @param adr1 - Erste Adresszeile, zum Beispiel Straße und Hausnummer.
	 * @param ort  - Ort / Stadt in welcher der Kunde wohnt.
	 * @param plz  - PLZ des Ortes.
	 * 
	 */
	public Adresse(String adr1, String ort, String plz) {
		this.adressSatz1 = adr1;
		this.ort = ort;
		if (plz.matches("[0-9]+")) {
			this.plz = plz;
		} else {
			System.out.println("Postleitzahl darf keine Buchstaben enthalten.");
			this.plz = "12345";
		}
	}

	/**
	 * 
	 * @return Gibt die erste Adresszeile in dem der Kunde wohnt aus.
	 */
	public String getAdressSatz1() {
		return adressSatz1;
	}

	/**
	 * uebergibt einen neuen Wert in das Attribut adressSatz1.
	 * 
	 * @param adressSatz1 - die uebergebene erste Adresszeile
	 */
	public void setAdressSatz1(String adressSatz1) {
		this.adressSatz1 = adressSatz1;
	}

	/**
	 * 
	 * @return Gibt die zweite Adresszeile in dem der Kunde wohnt aus.
	 */
	public String getAdressSatz2() {
		return adressSatz2;
	}

	/**
	 * uebergibt einen neuen Wert in das Attribut adressSatz2.
	 * 
	 * @param adressSatz2 - die uebergebene zweite Adresszeile
	 */
	public void setAdressSatz2(String adressSatz2) {
		this.adressSatz2 = adressSatz2;
	}

	/**
	 * 
	 * @return Gibt den Ort / die Stadt in dem der Kunde lebt aus.
	 */
	public String getOrt() {
		return ort;
	}

	/**
	 * uebergibt einen neuen Wert in das Attribut Ort.
	 * 
	 * @param ort - Der uebergebene Ort
	 */
	public void setOrt(String ort) {
		this.ort = ort;
	}

	/**
	 * 
	 * @return Gibt die PLZ in dem der Kunde lebt aus.
	 */
	public String getPlz() {
		return plz;
	}

	/**
	 * uebergibt einen neuen Wert in das Attribut PLZ. Setzt eine neue Postleitzahl.
	 * 
	 * @param plz - Die uebergebene Postleitzahl.
	 */
	public void setPlz(String plz) {
		this.plz = plz;
	}

	/**
	 * ueberschreibt die Standart toString() Methode um stattdessen gewuenschte
	 * Attribute auszugeben.
	 * 
	 * @return Alle Attribute der Klasse "Adresse" (adressSatz1, adressSatz2, plz
	 *         und ort).
	 */
	@Override
	public String toString() {
		return adressSatz1 + ", " + adressSatz2 + " in " + plz + " " + ort;
	}

}
