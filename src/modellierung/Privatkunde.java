package modellierung;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author Julian Joachim s0574380<br>
 * @version Eclipse 2019-09 4.1.3 / Java 1.8.0_ 191<br>
 * @since 03.04.2020 zuletzt bearbeitet am 01.06.2020. <br>
 *        Klasse Privatkunde, beinhaltet dessen Attribute und Methoden.
 *        Privatkunde erbt von der abstrakten Oberklasse Kunde.
 *
 */
public class Privatkunde extends Kunde implements Serializable{
	/**
	 * Standard ID um die Seriennummer der Serialisierung zu überwachen.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Ein neues SimpleDateFormat wird erstellt, mit welchem die gewuenschte
	 * Formatierung festgelegt wird.
	 */
	SimpleDateFormat format = new SimpleDateFormat("dd MMM, yyyy", Locale.GERMAN);

	/** Anrede (Geschlecht) des Kunden. */
	private Anrede anrede;
	/** Vorname des Kunden. */
	private String vorname = "";
	/** Nachname des Kunden. */
	private String nachname = "";

	/** Geburtstag des Kunden, es wird ein neues Objekt vom Typ Date angelegt. */
	private Date geburtstag = new Date();
	/** Standard Geburtstag fuer try catch */
	private final String standardGeburtstag = "01 Jan 2000";

	/**
	 * Standardkonstruktor der Klasse Privatkunde
	 */
	public Privatkunde() {
	}

	/**
	 * Konstruktor um alle Attribute der Klasse Kunden zu initialisieren.
	 * 
	 * @param anrede     - Anrede des Kunden (Geschlecht).
	 * @param vorname    - Vorname des Kunden.
	 * @param nachname   - Nachname des Kunden.
	 * @param kundenNr   - Kundennummer des Kunden.
	 * @param email      - Emailadresse des Kunden.
	 * @param telNr      - Telefonnummer des Kunden.
	 * @param hasCar     - ob der Kunde ein Auto hat, wenn true, dann ja.
	 * @param geburtstag - Geburtstag des Kunden.
	 * @param car        - Auto das dem Kunden zugeordnet ist.
	 * @param adress     - Adresse des Kunden.
	 */

	public Privatkunde(Anrede anrede, String vorname, String nachname, int kundenNr, String email, String telNr,
			Boolean hasCar, String geburtstag, Auto car, Adresse adress) {
		super(kundenNr, email, telNr, hasCar, car, adress);

		this.anrede = anrede;
		this.vorname = vorname;
		this.nachname = nachname;
		try {
			this.geburtstag = format.parse(geburtstag);
		} catch (ParseException e) {
			System.out.println("Fehler bei Datumseingabe.");
		}
	}

	/**
	 * Konstruktor um alle Attribute der Klasse Privatkunde zu initialisieren, bis
	 * auf Auto. Wird genutzt wenn der Kunde kein Auto gemietet hat.
	 * 
	 * @param anrede     - Anrede des Kunden (Geschlecht).
	 * @param vorname    - Vorname des Kunden.
	 * @param nachname   - Nachname des Kunden.
	 * @param kundenNr   - Kundennummer des Kunden.
	 * @param email      - Emailadresse des Kunden.
	 * @param telNr      - Telefonnummer des Kunden.
	 * @param hasCar     - ob der Kunde ein Auto hat, wenn true dann ja.
	 * @param geburtstag - Geburtstag des Kunden.
	 * @param adress     - Adresse des Kunden.
	 * 
	 * 
	 */

	public Privatkunde(Anrede anrede, String vorname, String nachname, int kundenNr, String email, String telNr,
			Boolean hasCar, String geburtstag, Adresse adress) {
		this(anrede, vorname, nachname, kundenNr, email, telNr, hasCar, geburtstag, new Auto(), adress);
	}

	/**
	 * 
	 * @return Gibt die Anrede des Kunden zurueck.
	 */
	public Anrede getAnrede() {
		return this.anrede;
	}

	/**
	 * 
	 * @return Gibt den Vornamen des Kunden zurueck
	 */
	public String getVorname() {
		return this.vorname;
	}

	/**
	 * 
	 * @return Gibt den Nachnamen des Kunden zurueck
	 */
	public String getNachname() {
		return this.nachname;
	}

	/**
	 * Aendert den Nachnamen des Kunden (zum Beispiel nach einer Hochzeit).
	 * 
	 * @param nachname neuer Nachnamen des Kunden
	 */
	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	/**
	 *
	 * @return Gibt den Geburtstag des Kunden im Format "dd MMM, yyyy" zurueck.
	 */
	public String getGeburtstag() {
		return format.format(geburtstag);
	}

	/**
	 * Basiert auf getName() der Oberklasse Kunden. Gibt den Namen des Kunden
	 * zurueck, in dieser Klasse bestehend aus Vorname und Nachname.
	 * 
	 * @see Kunde#getName()
	 */
	public String getName() {
		return this.getVorname() + " " + this.getNachname();
	}

	/**
	 * Ueberschreibt die toString-Methode um all Objekte des Privatkunden
	 * auszugeben.
	 */
	@Override
	public String toString() {
		return super.toString()  + anrede + " " + vorname + " " + nachname + ", wurde geboren am: "
				+ format.format(geburtstag) + "und ist ein Privatkunde.]";
	}

}
