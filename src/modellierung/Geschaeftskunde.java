package modellierung;

import java.io.Serializable;

/**
 * @author Julian Joachim s0574380<br>
 * @version Eclipse 2019-09 4.1.3 / Java 1.8.0_ 191<br>
 * @since 03.04.2020 zuletzt bearbeitet am 01.06.2020. <br>
 *        Klasse Geschaeftskunde, beinhaltet dessen Attribute und Methoden.
 *        Geschaeftskunde erbt von der abstrakten Oberklasse Kunde.
 *
 */
public class Geschaeftskunde extends Kunde implements Serializable{
	/**
	 * Standard ID um die Seriennummer der Serialisierung zu überwachen.
	 */
	private static final long serialVersionUID = 1L;
	/** Firmenname des Geschaeftskunden */
	String firmenname = "";

	/**
	 * Standardkonstruktor fuer Klasse Geschaeftskunde
	 */
	public Geschaeftskunde() {
	}

	/**
	 * Konstruktor um alle Attribute der Klasse Kunden zu initialisieren.
	 * 
	 * @param firmenname - Firmenname des Kunden
	 * @param kundenNr   - Kundennummer des Kunden
	 * @param email      - Emailadresse des Kunden
	 * @param telNr      - Telefonnummer des Kunden
	 * @param hasCar     - ob der Kunde ein Auto hat oder nicht
	 * @param car        - gemietetes Auto des Kunden
	 * @param adress     - Adresse des Kunden
	 * 
	 */

	public Geschaeftskunde(String firmenname, int kundenNr, String email, String telNr, Boolean hasCar, Auto car,
			Adresse adress) {
		super(kundenNr, email, telNr, hasCar, car, adress);
			this.firmenname = firmenname;

	}

	/**
	 * Konstruktor um alle Attribute der Klasse Kunden zu initialisieren, bis auf
	 * car. Wird benutzt wenn der Kunde nicht von anfang an ein Auto zugeteilt
	 * bekommt.
	 * 
	 * @param firmenname - Firmenname des Kunden
	 * @param kundenNr   - Kundennummer des Kunden
	 * @param email      - Emailadresse des Kunden
	 * @param telNr      - Telefonnummer des Kunden
	 * @param hasCar     - ob der Kunde ein Auto hat oder nicht
	 * @param adress     - Adresse des Kunden
	 */
	public Geschaeftskunde(String firmenname, int kundenNr, String email, String telNr, Boolean hasCar,
			Adresse adress) {
		this(firmenname, kundenNr, email, telNr, hasCar, new Auto(), adress);
	}

	/**
	 * 
	 * @return den Namen der Firma des Geschaeftskunden.
	 */
	public String getFirmenname() {
		return firmenname;
	}

	/**
	 * Basiert auf getName() der Oberklasse Kunden. Gibt den Namen des Kunden
	 * zurueck, in dieser Klasse bestehend aus dem Firmenname.
	 * 
	 * @see Kunde#getName()
	 */
	public String getName() {
		return this.getFirmenname();
	}

	/**
	 * Ueberschreibt die toString-Methode um all Objekte des Geschaeftskunden auszugeben.
	 */
	@Override
	public String toString() {
		return super.toString() + firmenname + " ist ein Geschaeftskunde.]";
	}
}
