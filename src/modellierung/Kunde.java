package modellierung;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;

/**
 * @author Julian Joachim s0574380<br>
 * @version Eclipse 2019-09 4.1.3 / Java 1.8.0_ 191<br>
 * @since 03.04.2020 zuletzt bearbeitet am 16.06.2020<br>
 *        Abstrakte Klasse Kunde, beinhaltet dessen Attribute und Methoden.
 *        Kunde ist abstrakt und dient nur als Oberklasse.
 *
 */

abstract public class Kunde implements Serializable {
	/**
	 * Standard ID um die Seriennummer der Serialisierung zu überwachen.
	 */
	private static final long serialVersionUID = 1L;
	/** Email-Adresse des Kunden. */
	protected String email = "";
	/** Kundennummer des Kunden. */
	protected int kundenNr = 0;
	/** Telefonnummer des Kunden. */
	protected String telNr = "";
	/** Ob der Kunde ein Auto gemietet hat. */
	protected boolean hasCar = false;
	/** Rechnungs Array fuer alle Rechnungen des Kunden. */
	protected Rechnung[] rechnungen = new Rechnung[0];

	/** Speicher car der Klasse Auto wird angelegt. */
	protected Auto car;
	/** Speicher adress der Klasse Adresse wird angelegt. */
	protected Adresse adress;

	/**
	 * Standardkonstruktor fuer Klasse Kunde.
	 */
	public Kunde() {
	}

	/**
	 * Konstruktor um alle Attribute der Klasse Kunden zu initialisieren.
	 * 
	 * @param kundenNr - Kundennummer des Kunden.
	 * @param email    - Emailadresse des Kunden.
	 * @param telNr    - Telefonnummer des Kunden.
	 * @param hasCar   - ob der Kunde ein Auto hat, wenn true dann ja.
	 * @param car      - Auto das dem Kunden zugeordnet ist.
	 * @param adress   - Adresse des Kunden.
	 * 
	 */

	public Kunde(int kundenNr, String email, String telNr, boolean hasCar, Auto car, Adresse adress) {
		this.kundenNr = kundenNr;
		this.email = email;
		if (telNr.matches("[0-9]+")) {
			this.telNr = telNr;
		} else {
			System.out.println("Postleitzahl darf keine Buchstaben enthalten.");
			this.telNr = "12345";
		}
		this.hasCar = hasCar;
		this.car = car;
		this.adress = adress;
		this.rechnungen = new Rechnung[0];
	}

	/**
	 * Konstruktor um alle Attribute der Klasse Kunde zu initialisieren, bis auf
	 * Auto. Wird genutzt wenn der Kunde kein Auto gemietet hat.
	 * 
	 * @param email    - Emailadresse des Kunden.
	 * @param kundenNr - Kundennummer des Kunden.
	 * @param telNr    - Telefonnummer des Kunden.
	 * @param hasCar   - ob der Kunde ein Auto hat. Wenn true, ja.
	 * @param adress   - Adresse des Kunden.
	 * 
	 */

	public Kunde(int kundenNr, String email, String telNr, boolean hasCar, Adresse adress) {
		this.kundenNr = kundenNr;
		this.email = email;
		if (telNr.matches("[0-9]+")) {
			this.telNr = telNr;
		} else {
			System.out.println("Postleitzahl darf keine Buchstaben enthalten.");
			this.telNr = "12345";
		}
		this.hasCar = false;
		this.adress = adress;
		this.rechnungen = new Rechnung[0];
	}

	/**
	 * 
	 * @return Gibt Email-Adresse des Kunden zurueck.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Setzt eine (neue) Email-Adresse fuer den Kunden.
	 * 
	 * @param email Email-Adresse des Kunden.
	 */
	public void setEmail(String email) {
		try {
			this.email = email;
		} catch (Exception e) {
			System.out.println("Fehleingabe bei: Email-Adresse.");
			this.email = "";
		}
	}

	/**
	 * 
	 * @return Gibt die Kundennummer des Kunden zurueck.
	 */
	public int getKundenNr() {
		return kundenNr;
	}

	/**
	 * 
	 * @return Gibt die Telefonnummer des Kunden zurueck.
	 * 
	 */
	public String getTelNr() {
		return telNr;
	}

	/**
	 * Aendert die aktuelle Telefonnummer des Kunden.
	 * 
	 * @param telNr neue Telefonnummer vom Kunden.
	 */
	public void setTelNr(String telNr) {
		try {
			while (true) {
				if (telNr.matches("[0-9]+")) {
					this.telNr = telNr;
					break;
				} else {
					System.out.println("Es sind keine Buchstaben in der Postleitzahl erlaubt.");
				}
			}
		} catch (Exception e) {
			System.out.println("Fehleingabe bei: Telefonnummer.");
		}
	}

	/**
	 *
	 * @return Gibt das derzeitige Auto des Kunden aus.
	 */
	public Auto getCar() {
		return this.car;
	}

	/**
	 * uebergibt dem Kunden ein neues Auto
	 * 
	 * @param car Auto das dem Kunden uebergeben wird.
	 */
	public void setCar(Auto car) {
		try {
			this.car = car;
			this.hasCar = true;
		} catch (Exception e) {
			System.out.println("Fehleingabe bei: Uebergabe des Autos.");
			this.car = new Auto();
		}
	}

	/**
	 * Loescht das derzeit gespeicherte Auto des Kunden (welches er gemietet hatte.)
	 * Attribut hasCar wird auf falsch gesetzt, weil er kein Auto mehr hat.
	 */
	public void removeCar() {
		this.car = new Auto();
		this.hasCar = false;
	}

	/**
	 * 
	 * @return True wenn der Kunde ein Auto gemietet hat.
	 */
	public boolean isHasCar() {
		return hasCar;
	}

	/**
	 * @return Gibt die derzeitige Adressen des Kunden aus.
	 */
	public Adresse getAdress() {
		return this.adress;
	}

	/**
	 * Setzt eine neue Adresse fuer den Kundne
	 * 
	 * @param adress neue Kundenadresse.
	 */
	public void setAdress(Adresse adress) {
		try {
			this.adress = adress;
		} catch (Exception e) {
			System.out.println("Fehleingabe bei: Adresse.");
			this.adress = new Adresse();
		}
	}

	/**
	 * Gibt alle Rechungen des Kunden zurueck.
	 * 
	 * @return alle Rechnungen dieses Kunden.
	 */
	public Rechnung[] getRechnung() {
		return rechnungen;
	}

	/**
	 * Fuegt dem Array an Rechnungen ein neues Element hinzu. Inkrementiert dafuer
	 * das vorhandene Array um 1.
	 * 
	 * @param rechnung Die Rechnung die dem Kunden hinzugefuegt wird.
	 */
	public void addRechnung(Rechnung rechnung) {
		this.rechnungen = Arrays.copyOf(this.rechnungen, this.rechnungen.length + 1);
		this.rechnungen[this.rechnungen.length - 1] = rechnung;
	}

	/**
	 * Rechnet alle offenen Rechnungsbeträge die der Kunde hat zusammen,
	 * 
	 * @return die Summe aller offenen Rechnungsbeträge.
	 */
	public double getTotalBetrag() {
		double total = 0;
		for (int i = 0; i < rechnungen.length; i++) {
			if (this.rechnungen[i].isBezahlt() == false) {
				total = total + this.rechnungen[i].getRechnungsbetrag();
			}
		}
		return total;
	}

	/**
	 * Abstrakte Methode die in den jeweiligen Unterklassen angepasst den jeweiligen
	 * Namen ausgeben soll.
	 * 
	 * @return nichts, weil abstrakt.
	 */
	public abstract String getName();

	@Override
	public String toString() {
		return "[Der Kunde hat die Kundennummer " + kundenNr + System.lineSeparator() + "Telefonnummer " + telNr
				+ System.lineSeparator() + "Ob er ein Auto gemietet hat? = " + hasCar + System.lineSeparator()
				+ "Wenn ja, folgendes: " + car + System.lineSeparator() + "Seine Rechnungen sind: "
				+ Arrays.toString(rechnungen) + System.lineSeparator() + "Wohnen tut er In: " + adress + ". "
				+ System.lineSeparator();
	}

}
