package modellierung;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author Julian Joachim s0574380<br>
 * @version Eclipse 2019-09 4.1.3 / Java 1.8.0_ 191<br>
 * @since 03.04.2020 zuletzt bearbeitet am 16.06.2020<br>
 * 
 *        Klasse Autovermietung, beinhaltet dessen Attribute und Methoden.
 *
 */

public class Autovermietung implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** Name der Autovermietung. */
	private String name = "";
	/** Speicher standort der Klasse Adresse wird angelegt. */
	private Adresse standort;

	/** 5 neue Arrayobjekte kunden der Klasse Kunde werden angelegt. */
	private Kunde[] kunden = new Kunde[4];

	/** 5 neue Arrayobjekte adress der Klasse Adresse werden angelegt. */
	private Adresse[] adress = new Adresse[5];

	/**
	 * Arrayobjekt rechnungen der Klasse Rechnung wird angelegt. Groesse 0, groesse
	 * wird angepasst im programm.
	 */
	private Rechnung[] rechnungen = new Rechnung[10];

	/**
	 * Eine Hashmap namens kennzeichenAuto wird angelegt. Der Schluessel ist String
	 * (Das Kennzeichen), die Daten vom Typ Auto.
	 */
	private HashMap<String, Auto> kennzeichenAuto = new HashMap<>();

	private List<Beobachter> obs = new ArrayList<>();

	/**
	 * Standardkonstruktor fuer Klasse Autovermietung.
	 */
	public Autovermietung() {
	}

	/**
	 * /** Konstruktor um den Namen und den Ort der Autovermietung zu initialisieren
	 * 
	 * @param name     - Name der Autovermietung.
	 * @param standort - Adresse der Autovermietung.
	 */

	public Autovermietung(String name, Adresse standort) {
		this.name = name;
		this.standort = standort;
	}

	/**
	 * /** Konstruktor um alle Attribute der Klasse Autovermietung zu
	 * initialisieren.
	 * 
	 * @param name     - Name der Autovermietung.
	 * @param kunden   - Kunden der Autovermietung.
	 * @param autos    - Autos der Autovermietung.
	 * @param standort - Adresse der Autovermietung.
	 */

	public Autovermietung(String name, Kunde[] kunden, Auto[] autos, Adresse standort) {
		this.name = name;
		this.kunden = kunden;
		for (int i = 0; i < autos.length; i++) {
			kennzeichenAuto.put(autos[i].getKennzeichen(), autos[i]);
		}
		this.standort = standort;
	}

	/**
	 * Methode um Werte in die Arrays zu setzen, damit das Programm leichter
	 * getestet werden kann.
	 */
	public void setHardcodeData() {
		Auto toyota1 = new Auto("Toyota", 1000.0, 3, "B-OG-3821", true);
		kennzeichenAuto.put(toyota1.getKennzeichen(), toyota1);

		Auto audi1 = new Auto("Audi", 2000.8, 4, "B-KV-4911", false);
		kennzeichenAuto.put(audi1.getKennzeichen(), audi1);

		Auto volkswagen1 = new Auto("Volkswagen", 4000.3, 2, "B-BG-5532", false);
		kennzeichenAuto.put(volkswagen1.getKennzeichen(), volkswagen1);

		Auto ford1 = new Auto("Ford", 10.5, 8, "B-EN-0941", true);
		kennzeichenAuto.put(ford1.getKennzeichen(), ford1);

		Auto kia1 = new Auto("Kia", 300.4, 5, "B-OG-4813", true);
		kennzeichenAuto.put(kia1.getKennzeichen(), kia1);

		adress[0] = new Adresse("Reetzer Weg 31", "Berlin", "12621");
		adress[1] = new Adresse("Dharmastreet 1", "Abteilung der Psychologie", "Kaaawa", "96730");
		adress[2] = new Adresse("Kingston Street 43", "Queens", "11361");
		adress[3] = new Adresse("Bleicheroder Str. 22", "Berlin", "13187");
		adress[4] = new Adresse("Stanzer Zeile 59", "c/o Benjamin Linus", "Berlin", "12209");

		kunden[0] = new Privatkunde(Anrede.HERR, "Hugo", "Reyes", 100328, "hugoreyes108@gmail.com", "481516234", true,
				"29 Dez, 1979", ford1, adress[1]);
		ford1.setIstVermietet(true);

		kunden[1] = new Geschaeftskunde("Springer Verlag", 100329, "mark.springer@gmail.com", "838311293", true, kia1,
				adress[3]);
		kia1.setIstVermietet(true);

		kunden[2] = new Privatkunde(Anrede.FRAU, "Carrie", "Heffernan", 100330, "dougandcarrie@gmail.com", "13579246",
				true, "16 Jul, 1971", toyota1, adress[2]);
		toyota1.setIstVermietet(true);

		kunden[3] = new Geschaeftskunde("Paik Heavy Industries", 100331, "jinsookwon@gmail.com", "66632743", false,
				adress[4]);

		rechnungen[0] = new Rechnung(1, 1000, 3000, true, 100330);
		kunden[2].addRechnung(rechnungen[0]);
		rechnungen[1] = new Rechnung(2, 400, 2000, true, 100330);
		kunden[2].addRechnung(rechnungen[1]);
		rechnungen[2] = new Rechnung(3, 30, 150, false, 100329);
		kunden[1].addRechnung(rechnungen[2]);
		rechnungen[3] = new Rechnung(4, 48, 240, true, 100331);
		kunden[3].addRechnung(rechnungen[3]);
		rechnungen[4] = new Rechnung(5, 138, 414, false, 100328);
		kunden[0].addRechnung(rechnungen[4]);
		rechnungen[5] = new Rechnung(6, 923, 2769, true, 100328);
		kunden[0].addRechnung(rechnungen[5]);
		rechnungen[6] = new Rechnung(7, 321, 963, true, 100331);
		kunden[3].addRechnung(rechnungen[6]);
		rechnungen[7] = new Rechnung(8, 123, 369, true, 100330);
		kunden[2].addRechnung(rechnungen[7]);
		rechnungen[8] = new Rechnung(9, 444, 888, false, 100331);
		kunden[3].addRechnung(rechnungen[8]);
		rechnungen[9] = new Rechnung(10, 10, 80, false, 100329);
		kunden[1].addRechnung(rechnungen[9]);

		Beobachter DanielJung = new Buchhalter("Daniel", "Jung");
		Beobachter MarkKram = new Angestellter("Mark", "Kram");

		addObserver(DanielJung);
		addObserver(MarkKram);
	}

	/**
	 * Methode, um einen neuen Kunden einzuspeichern. Array kunden wird um eins
	 * inkrementiert.
	 * 
	 * @param kunde uebergebener, neuer Kunde
	 */
	public void kundeHinzufuegen(Kunde kunde) {
		this.kunden = Arrays.copyOf(this.kunden, this.kunden.length + 1);
		this.kunden[this.kunden.length - 1] = kunde;
	}

	/**
	 * Methode, um ein neues Auto einzuspeichern. Array autos wird um eins
	 * inkrementiert.
	 * 
	 * @param auto uebergebener, neuer Auto
	 */
	public void autoHinzufuegen(Auto auto) {
		kennzeichenAuto.put(auto.getKennzeichen(), auto);
	}

	/**
	 * Methode, um eine neue Rechnung einzuspeichern. Array rechnungen wird um eins
	 * inkrementiert.
	 * 
	 * @param kNr      Kundennummer des zugehoerigen Kunden
	 * @param kmAnzahl Anzahl der gefahrenen Kilometer
	 * @param kosten   Kosten der Automietung
	 * @param bezahlt  ob die Rechnung schon bezahlt wurde(In der Regel nicht)
	 */
	public void erstelleRechnung(int kNr, double kmAnzahl, double kosten, boolean bezahlt) {
		this.rechnungen = Arrays.copyOf(this.rechnungen, this.rechnungen.length + 1);
		this.rechnungen[this.rechnungen.length - 1] = new Rechnung(this.rechnungen.length, kmAnzahl, kosten, bezahlt,
				kNr);

		for (int x = 0; x < kunden.length; x++) {
			if (kunden[x].getKundenNr() == kNr) {
				kunden[x].addRechnung(this.rechnungen[this.rechnungen.length - 1]);
				notify(this.rechnungen[this.rechnungen.length - 1], kunden[x], "Angestellter");
				notify(this.rechnungen[this.rechnungen.length - 1], kunden[x], "GUI");
			}
		}

	}

	/**
	 * Methode, mit welcher alle Beobachter benachrichtigt werden. In den Observern
	 * wird gefiltert, so das nur bestimmte Observer geupdatet werden.
	 * 
	 * @param r   die Rechnung, die bezahlt wurde.
	 * @param k   der Kunde, der die Bezahlung ausgeführt hat.
	 * @param who String mit dem Namen des Observers, der reagieren soll
	 */
	public void notify(Rechnung r, Kunde k, String who) {
		for (Beobachter beo : obs) {
			beo.update(r, k, who);
		}
	}

	/**
	 * Fuegt ein uebergebenes Auto in eine HashMap ein.
	 * 
	 * @param kennzeichen Kennzeichen des Autos (Schluessel)
	 * @param neuesAuto   Daten des Autos (Daten);
	 */
	public void autoToList(String kennzeichen, Auto neuesAuto) {
		kennzeichenAuto.put(kennzeichen, neuesAuto);
	}

	/**
	 * Gibt eine Willkommensnachricht zurueck
	 * 
	 * @return WIllkommensnachricht mit Namen und Standort der Firma
	 */
	public String printStart() {
		return ("Herzlich willkommen bei dem Autoverleih von " + this.name + ". Sie finden uns im " + this.standort
				+ ".");
	}

	/**
	 * 
	 * @return Gibt einen im Array auswaehlbaren Kunden aus.
	 */
	public Kunde[] getKunden() {
		return kunden;
	}

	/**
	 * Setzt alle Kunden der Autovermietung
	 * 
	 * @param kunden - alle neuen Kunden.
	 */
	public void setKunden(Kunde[] kunden) {
		this.kunden = kunden;
	}

	/**
	 * Setzt alle Rechnungen der Autovermietung
	 * 
	 * @param rechnung - alle neuen Rechnungen.
	 */
	public void setRechnung(Rechnung[] rechnung) {
		this.rechnungen = rechnung;
	}

	/**
	 * 
	 * @return Gibt alle Autos zurück.
	 */
	public Auto[] getAutos() {
		return (Auto[]) kennzeichenAuto.values().toArray(new Auto[0]);
	}

	/**
	 * @return gibt die Adresse der Autovermietung zurueck, indem es das Array in
	 *         einen String umwandelt.
	 */
	public Adresse getStandort() {
		return this.standort;
	}

	/**
	 * 
	 * @return Gibt den Namen der Autovermietung zurueck
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gibt alle Rechnungen zurueck
	 * 
	 * @return Rechnungen
	 */
	public Rechnung[] getRechnungen() {
		return rechnungen;
	}

	/**
	 * Gibt groesse des kunden Arrays zurueck.
	 * 
	 * @return laenge des kunden arrays
	 */
	public int kundenLaenge() {
		return kunden.length;
	}

	/**
	 * Gibt groesse des auto Arrays zurueck.
	 * 
	 * @return laenge der AutoHashmap
	 */
	public int autosLaenge() {
		return kennzeichenAuto.size();
	}

	/**
	 * Gibt groesse des rechnungen Arrays zurueck.
	 * 
	 * @return laenge des auto arrays
	 */
	public int rechnungenLaenge() {
		return rechnungen.length;
	}

	/**
	 * Gibt einen Kunden zur Kundennummer, sonst null.
	 * 
	 * @param kundennummer die Kundennummer, nach welcher ein Kunde gesucht wird.
	 * @return der gefundene Kunde, sonst null.
	 */
	public Kunde getKunde(int kundennummer) {
		for (int x = 0; x < kunden.length; x++) {
			if (kunden[x].getKundenNr() == kundennummer) {
				return kunden[x];
			}
		}
		return null;
	}

	/**
	 * Gibt ein Auto zu einem Kennzeichen.
	 * 
	 * @param kennzeichen das Kennzeichen, nach welchem ein Auto gesucht wird.
	 * @return das gefundende Auto.
	 */
	public Auto getAuto(String kennzeichen) {
		return kennzeichenAuto.get(kennzeichen);
	}

	/**
	 * Gibt eine Rechnung zur Rechnungsnummer, sonst null.
	 * 
	 * @param rechnungsnummer die rechnungsnummer, nach welcher eine Rechnung
	 *                        gesucht wird.
	 * @return die gefundene Rechnung, sonst null.
	 */
	public Rechnung getRechnung(int rechnungsnummer) {
		for (int x = 0; x < rechnungen.length; x++) {
			if (rechnungen[x].getRechnungsnummer() == rechnungsnummer) {
				return rechnungen[x];
			}
		}
		return null;
	}

	/**
	 * Leert die Liste der Autos und fügt neue ein.
	 * 
	 * @param autos das übergebene Array mit den neuen Autos.
	 */
	public void setAutos(Auto[] autos) {
		kennzeichenAuto.clear();
		for (int i = 0; i < autos.length; i++) {
			kennzeichenAuto.put(autos[i].getKennzeichen(), autos[i]);
		}
	}

	/**
	 * Methode um Rechnungen zu bezahlen. Zudem werden die Beobachter benachrichtigt
	 * und der Logger logt den Zahlungsvorgang.
	 * 
	 * @param bezKunde    der Kunde, der bezahlt.
	 * @param bezRechnung die Rechnung, die bezahlt wird.
	 * @return 0, wenn alles erfolgreich lief. 1, wenn die Rechnung nicht zu dem
	 *         Kunden gehörte.
	 */
	public int zahleRechnung(Kunde bezKunde, Rechnung bezRechnung) {
		Logger logger = Logger.getLoggerInstance();

		if (kundeHasRechnung(bezKunde, bezRechnung)) {
			bezRechnung.setBezahlt(true);
			notify(bezRechnung, bezKunde, "Buchhalter");
			notify(bezRechnung, bezKunde, "GUI");
			logger.log(bezRechnung, bezKunde);
			return 0;
		} else {
			return 1;
		}

	}

	/**
	 * Methode die prüft ob ein Kunde eine Rechnung besitzt
	 * 
	 * @param kunde    Kunde, welcher auf eine Rechnung geprüft wird
	 * @param rechnung Rechnung, welche eventuell zum Kunden gehört
	 * @return true wenn er Kunde die Rechnung besitzt, false wenn nicht.
	 */
	public boolean kundeHasRechnung(Kunde kunde, Rechnung rechnung) {
		for (int x = 0; x < kunde.getRechnung().length; x++) {
			System.out.println(kunde.getRechnung()[x] + " ------ " + rechnung);
			if (kunde.getRechnung()[x].equals(rechnung)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Fügt einen neuen Observer/Beobachter hinzu.
	 * 
	 * @param neuObs der neue Beobachter.
	 */
	public void addObserver(Beobachter neuObs) {
		obs.add(neuObs);
	}

	/**
	 * Löscht einen Observer/Beobachter.
	 * 
	 * @param neuObs der neue Beobachter.
	 */
	public void removeObserver(Beobachter removeObs) {
		for (int i = 0; i < obs.size(); i++) {
			if (obs.get(i).equals(removeObs)) {
				obs.remove(i);
			}
		}

	}

}
