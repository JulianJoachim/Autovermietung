package applikation;

import java.awt.EventQueue;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import modellierung.Adresse;
import modellierung.Anrede;
import modellierung.Auto;
import modellierung.Autovermietung;
import modellierung.GUI;
import modellierung.Geschaeftskunde;
import modellierung.GetInput;
import modellierung.Kunde;
import modellierung.LogConsole;
import modellierung.LogTxT;
import modellierung.Logger;
import modellierung.Privatkunde;
import modellierung.Rechnung;
import modellierung.SortByName;
import modellierung.SortByRechnungssumme;

/**
 * @author Julian Joachim s0574380<br>
 * @version Eclipse 2019-09 4.1.3 / Java 1.8.0_ 191<br>
 * @since 03.04.2020 zuletzt bearbeitet am 16.06.2020<br>
 * 
 *        Klasse CarAG ist eine Testklasse. Es wird das Programm getestet.
 */

public class CarAG {
	/**
	 * endProgram wird auf true gesetzt wenn der User wuenscht das Programm zu
	 * beenden.
	 */
	private static boolean endProgram = false;
	/** Import des Scanners zur Konsoleneingabe */
	private static Scanner sc = new Scanner(System.in);

	/**
	 * Es wird eine Wunschformatierung fuer das Ausgeben der Kundendaten kreiert.
	 */
	private static String formatPrivat = "|%1$-25s|%2$-15s|%3$-25s|%4$-30s|%5$-13s|%6$-20s|%7$-50s|%8$-60s\n";
	/**
	 * Es wird eine Wunschformatierung fuer das Ausgeben der Kundendaten kreiert.
	 */
	private static String formatGeschaeft = "|%1$-25s|%2$-15s|%3$-30s|%4$-25s|%5$-13s|%6$-40s|%7$-60s\n";

	/**
	 * es wird ein neues SimpleDateFormat namens format mit dem gewuenschtem Datum
	 * Format angelegt
	 */
	private static SimpleDateFormat format = new SimpleDateFormat("dd MMM, yyyy", Locale.GERMAN);

	/**
	 * Legt eine neues Objekt der Klasse Autovermietung an.
	 */
	private static Autovermietung av = new Autovermietung("CarAG", new Adresse("Reetzer Weg 31", "Berlin", "12621"));

	/**
	 * Main Methode; in ihr werden die Objekte angelegt und die Methoden die zum
	 * Testen benoetigt werden, ausgegeben.
	 * 
	 * @param args ist unbenutzt.
	 */
	public static void main(String[] args) {
		av.setHardcodeData();
		System.out.println(av.printStart());

		do {
			showMenue();
			int menuePoint = GetInput.inputInt();
			handle(menuePoint);
		} while (endProgram == false);

	}

	/**
	 * Gibt auf der Konsole ein Menue aus, mit welchem der User zwischen den
	 * Funktionen navigieren kann.
	 */
	public static void showMenue() {
		String menuItems[] = { "", "(1)\t Privatkunde anlegen", "(2)\t Geschaeftskunde anlegen", "(3)\t Auto anlegen",
				"(4)\t Auto mieten", "(5)\t Kunde anzeigen", "(6)\t unvermietete Autos anzeigen",
				"(7)\t Auto mit Mieter anzeigen", "(8)\t Auto abgeben und Rechnung erstellen",
				"(9)\t Rechnung anzeigen", "(10)\t Alle Rechungen anzeigen", "(11)\t Daten speichern",
				"(12)\t Daten laden", "(13)\t Kunden in CSV Datei exportieren, sortiert nach Namen",
				"(14)\t Rechnung bezahlen", "(15)\t Log-Strategie wählen", "(16)\t GUI für Rechnungen öffnen", "(17)\t Programm beenden" };
		System.out.println();
		System.out.println("Bitte tippen Sie die Nummer des gewuenschten Menuepunkts ein.");
		for (int i = 1; i < menuItems.length; i++) {
			System.out.println(menuItems[i]);
		}
		System.out.println();
	}

	/**
	 * Bearbeitet den Input des Users und ruft je nachdem welche Funktion erwuenscht
	 * wurde die jeweilige Methode auf.
	 * 
	 * @param choice Auswahl des Menuepunktes die der User getroffen hat
	 */
	public static void handle(int choice) {
		switch (choice) {
		case 1:
			pkundeAnlegen();
			break;
		case 2:
			gkundeAnlegen();
			break;
		case 3:
			autoAnlegen();
			break;
		case 4:
			autoMieten();
			break;
		case 5:
			kundeAnzeigen();
			break;
		case 6:
			unvAutos();
			break;
		case 7:
			autoAnzeigen();
			break;
		case 8:
			autoAbgeben();
			break;
		case 9:
			zeigeRechnung();
			break;
		case 10:
			alleRechnungenSortiert();
			break;
		case 11:
			try {
				datenSpeichern();
			} catch (NotSerializableException e) {
			} catch (FileNotFoundException e) {
				System.out.println("Die Datei mit dem angegeben Namen oder Pfad konnte nicht gefunden werden.");
			}
			break;
		case 12:
			try {
				datenLaden();
			} catch (NotSerializableException e) {
			} catch (FileNotFoundException e) {
				System.out.println("Die Datei mit dem angegeben Namen oder Pfad konnte nicht gefunden werden.");
			}
			break;
		case 13:
			kundenExport();
			break;
		case 14:
			rechnungBezahlen();
			break;
		case 15:
			switchLog();
			break;
		case 16:
			starteGUI();
			break;
		case 17:
			endProgram = true;
			System.out.println("Programm wird beendet...");
			break;
		default:
			System.out.println("Menuepunkt existiert nicht.");
		}
	}

	/**
	 * Erlaubt dem User einen Privatkunden anzulegen. Es werden ueber Konsolen Ein-
	 * und Ausgaben alle moeglichen Abfragen gemacht und jene dann ueber dem
	 * Konstruktor der Klasse Privatkunde uebergeben.
	 * 
	 * @see Privatkunde#Privatkunde(Anrede, String, String, int, String, String,
	 *      Boolean, String, Adresse)
	 */
	public static void pkundeAnlegen() {
		/** neue Anrede wird erstellt zum uebergeben in den Konstruktor */
		Anrede anrede = null;
		/** Testattribut fuer das feststellen des Geschlechts des neuen Kunden */
		boolean genCheck = true;
		/**
		 * es werden neue String Attribute angelegt um die Usereingaben in den
		 * Konstruktor zu uebergeben.
		 */
		String vorn, nachn, mail, tel, date, adr1, adr2, ort, plz;
		/**
		 * es wird ein neues int Attribute angelegt um die Usereingaben in den
		 * Konstruktor zu uebergeben.
		 */
		int knr = 0;

		System.out.println("Hier koennen Sie einen neuen Privatkunden anlegen.");
		knr = (av.getKunden()[av.kundenLaenge() - 1].getKundenNr()) + 1;
		System.out.println("Die Kundenummer lautet: " + knr);
		System.out.println();

		do {
			genCheck = true;
			System.out.println("Ist der anzulegende Kunde maennlich (M) oder weiblich (W)?");
			char gender = sc.next().charAt(0);
			sc.nextLine();

			if (gender == 'm' || gender == 'M') {
				anrede = Anrede.HERR;
			} else if (gender == 'w' || gender == 'W') {
				anrede = Anrede.FRAU;
			} else {
				System.out.println("Ungueltige Eingabe beim Geschlecht.");
				genCheck = false;
			}
		} while (genCheck == false);

		System.out.println("Wie lautet der Vorname des Kunden?");
		vorn = GetInput.inputString();
		System.out.println("Wie lautet der Nachname des Kunden?");
		nachn = GetInput.inputString();
		System.out.println("Wie lautet die Email-Adresse des Kunden?");
		mail = GetInput.inputString();

		while (true) {
			System.out.println("Wie lautet die Telefonnummer des Kunden?");
			tel = GetInput.inputString();
			if (tel.matches("[0-9]+")) {
				break;
			} else {
				System.out.println(
						"Es sind keine Buchstaben in der Telefonnummer erlaubt. Bitte schreiben sie auch ohne Leerzeichen.");
			}
		}

		while (true) {
			System.out.println("Wann wurde der Kunde geboren? (Eingabe im Format wie: '08 Jan, 2020')");
			date = GetInput.inputString();

			if (datumValid(date) == false) {
				System.out.println("Datumseingabe im falschen Format. Bitte wiederholen Sie die Eingabe.");
			} else {
				break;
			}
		}

		System.out.println("Wie lautet die Adresszeile 1 des Kunden?");
		adr1 = GetInput.inputString();
		System.out.println("Wie lautet die Adresszeile 2 des Kunden?");
		adr2 = GetInput.inputString();
		System.out.println("In welchem Ort wohnt der Kunde?");
		ort = GetInput.inputString();

		while (true) {
			System.out.println("Wie lautet die Postleitzahl des Wohnortes?");
			plz = GetInput.inputString();
			if (plz.matches("[0-9]+")) {
				break;
			} else {
				System.out.println("Es sind keine Buchstaben in der Postleitzahl erlaubt.");
			}
		}

		av.kundeHinzufuegen(
				new Privatkunde(anrede, vorn, nachn, knr, mail, tel, false, date, new Adresse(adr1, adr2, ort, plz)));
	}

	/**
	 * Erlaubt dem User einen Privatkunden anzulegen. Es werden ueber Konsolen Ein-
	 * und Ausgaben alle moeglichen Abfragen gemacht und jene dann ueber dem
	 * Konstruktor der Klasse Privatkunde uebergeben.
	 * 
	 * @see Geschaeftskunde#Geschaeftskunde(String, int, String, String, Boolean,
	 *      Adresse)
	 */
	public static void gkundeAnlegen() {
		/**
		 * es werden neue String Attribute angelegt um die Usereingaben in den
		 * Konstruktor zu uebergeben.
		 */
		String fname, mail, tel, adr1, adr2, ort, plz;
		/**
		 * es wird ein neues int Attribut angelegt um die Usereingaben in den
		 * Konstruktor zu uebergeben.
		 */
		int knr = 0;
		/** Anzahl der Kundenobjekte */
		int laenge = av.kundenLaenge();

		System.out.println("Hier koennen Sie einen neuen Geschaeftskunden anlegen.");
		knr = (av.getKunden()[laenge - 1].getKundenNr()) + 1;
		System.out.println("Die Kundenummer ist: " + knr);
		System.out.println();

		System.out.println("Wie lautet der Firmenname des Kunden?");
		fname = GetInput.inputString();
		System.out.println("Wie lautet die Email-Adresse des Kunden?");
		mail = GetInput.inputString();

		while (true) {
			System.out.println("Wie lautet die Telefonnummer des Kunden?");
			tel = GetInput.inputString();
			if (tel.matches("[0-9]+")) {
				break;
			} else {
				System.out.println(
						"Es sind keine Buchstaben in der Telefonnummer erlaubt. Bitte schreiben sie auch ohne Leerzeichen.");
			}
		}

		System.out.println("Wie lautet die Adresszeile 1 des Kunden?");
		adr1 = GetInput.inputString();
		System.out.println("Wie lautet die Adresszeile 2 des Kunden?");
		adr2 = GetInput.inputString();
		System.out.println("In welchem Ort wohnt der Kunde?");
		ort = GetInput.inputString();

		while (true) {
			System.out.println("Wie lautet die Postleitzahl des Wohnortes?");
			plz = GetInput.inputString();
			if (plz.matches("[0-9]+")) {
				break;
			} else {
				System.out.println("Es sind keine Buchstaben in der Postleitzahl erlaubt.");
			}
		}

		av.kundeHinzufuegen(new Geschaeftskunde(fname, knr, mail, tel, false, new Adresse(adr1, adr2, ort, plz)));
	}

	/**
	 * Diese Methode kreiert ein neues Auto indem es das derzeitige Array um 1
	 * erhoeht und in den neuen Speicher mit Kundeneingaben den Konstruktor von Auto
	 * aufruft.
	 * 
	 * @see Auto#Auto(String, double, double, String, boolean)
	 */
	public static void autoAnlegen() {
		/**
		 * es werden neue String Attribute angelegt um die Usereingaben in den
		 * Konstruktor zu uebergeben.
		 */
		String marke, kennzeichen;
		/**
		 * es werden neue Double Attribute angelegt um die Usereingaben in den
		 * Konstruktor zu uebergeben.
		 */
		double kmStand, ppKM;
		/**
		 * Testvariable fuer das Pruefen auf Duplikate in der Vergabe der Kennzeichen.
		 */
		int allOK = 0;

		System.out.println("Hier koennen Sie ein neues Auto anlegen.");

		System.out.println("Was ist die Marke des Autos?");
		marke = GetInput.inputString();
		System.out.println("Wieviele Kilometer ist das Auto bereits gefahren?");
		kmStand = Math.ceil(GetInput.inputDouble());
		System.out.println("Wieviele Euro pro Kilometer soll das Auto kosten?");
		ppKM = GetInput.inputDouble();

		do {
			System.out.println("Wie lautet das Kennzeichen des Autos?");
			kennzeichen = GetInput.inputString();

			for (int i = 0; i < av.autosLaenge() - 1; i++) {
				if (kennzeichen.equals(av.getAutos()[i].getKennzeichen())) {
					System.out.println(
							"Das Kennzeichen gibt es bereits. Doppelte Kennzeichen sind nicht erlaubt. Bitte neu eingeben.");
				} else {
					allOK++;
				}
			}
		} while (allOK != av.autosLaenge() - 1);

		av.autoHinzufuegen(new Auto(marke, kmStand, ppKM, kennzeichen, false));
	}

	/**
	 * Es muss eingegeben werden, welcher Kunde ein Auto mieten soll. Wenn er keins
	 * hat, werden alle verfuegbaren ausgegeben und es kann ueber das Kennzeichen
	 * ein Auto fuer den Kunden ausgewaehlt werden.
	 * 
	 */
	public static void autoMieten() {
		System.out.println("Welcher Kunde will ein Auto mieten? (Kundennummer)");
		Kunde kunde = av.getKunde(GetInput.inputInt());

		try {
			if (kunde.isHasCar() == true) {
				System.out.println("Kunde hat bereits ein Auto.");
			} else {
				System.out.println("Folgende Autos sind noch zu mieten:");
				unvAutos();
				System.out.println();
				System.out.println(
						"Welches Auto moechten Sie mieten? Geben Sie dazu das Autokennzeichen ein (Achten Sie auf Gross- und Kleinschreibung!)");
				Auto auto = av.getAuto(GetInput.inputString());

				if (auto.isIstVermietet() == true) {
					System.out.println(
							"Das ausgewaehlte Auto ist schon vermietet. Bitte waehlen Sie stattdessen aus der Liste der verfuegbaren Autos.");
				} else {
					kunde.setCar(auto);
					auto.setIstVermietet(true);
					System.out.println("Der Kunde hat erfolgreich folgendes Auto gemietet:");
					System.out.println(kunde.getCar());
				}
			}
		} catch (NullPointerException e) {
			System.out.println("Diese Nummer ist keinem Objekt zugeordnet.");
		}

	}

	/**
	 * Methode um einen beliebigen Kunden auf der Konsole auszugeben. Entscheidung
	 * durch Konsoleneingabe des Users ueber die Kundennummer.
	 */
	public static void kundeAnzeigen() {
		System.out.println();
		System.out.println("Geben Sie bitte die Kundennummer des anzuzeigenden Kunden ein:");

		int testNr = GetInput.inputInt();
		Kunde kunde = av.getKunde(testNr);

		try {
			if (kunde.getClass().getSimpleName().equals("Geschaeftskunde")) {
				System.out.format(formatGeschaeft, "Typ", "Kundennummer", "Name", "Email", "Telefon",
						"Wohnort des Kunden", "gemietetes Auto des Kunden");

				if (kunde.isHasCar() == true) {
					System.out.format(formatGeschaeft, "Ist ein Geschaeftskunde.", kunde.getKundenNr(), kunde.getName(),
							kunde.getEmail(), kunde.getTelNr(), kunde.getAdress(), kunde.getCar());
				} else {
					System.out.format(formatGeschaeft, "Ist ein Geschaeftskunde.", kunde.getKundenNr(), kunde.getName(),
							kunde.getEmail(), kunde.getTelNr(), kunde.getAdress(), "Hat derzeit kein Auto gemietet");
				}
			} else if (kunde.getClass().getSimpleName().equals("Privatkunde")) {

				System.out.format(formatPrivat, "Typ", "Kundennummer", "Name", "Email", "Telefon", "Geboren am",
						"Wohnort des Kunden", "gemietetes Auto des Kunden");

				if (kunde.isHasCar() == true) {
					System.out.format(formatPrivat, "Ist ein Privatkunde.", kunde.getKundenNr(),
							((Privatkunde) kunde).getAnrede() + " " + kunde.getName(), kunde.getEmail(),
							kunde.getTelNr(), ((Privatkunde) kunde).getGeburtstag(), kunde.getAdress(), kunde.getCar());
				} else {
					System.out.format(formatPrivat, "Ist ein Privatkunde.", kunde.getKundenNr(),
							((Privatkunde) kunde).getAnrede() + " " + kunde.getName(), kunde.getEmail(),
							kunde.getTelNr(), ((Privatkunde) kunde).getGeburtstag(), kunde.getAdress(),
							"Hat derzeit kein Auto gemietet.");
				}

			}
		} catch (NullPointerException e) {
			System.out.println("Es existiert kein Kunde mit dieser Kundennummer.");
		}
	}

	/**
	 * Gibt alle Autos die noch unvermietet sind auf der Konsole aus.
	 */
	public static void unvAutos() {
		System.out.println();
		System.out.println("Hier sehen Sie alle unvermieteten Autos.");

		av.getAutos()[0].getFormatHeader();
		for (int i = 0; i < av.autosLaenge(); i++) {
			if (av.getAutos()[i].isIstVermietet() == false) {
				av.getAutos()[i].getFormat();
			}

		}

	}

	/**
	 * Zeigt ein beliebiges Auto an, das mit dem Kennzeichen ausgewaehlt wird.
	 * Zusaetzlich wird die Kundennummer des Mieters angezeigt.
	 */
	public static void autoAnzeigen() {
		int kundenCount = 0;
		System.out.println(
				"Geben Sie bitte das Kennzeichen des anzuzeigenden Autos ein. Achten Sie dabei auf Gross- und Kleinschreibung.");
		Auto auto = av.getAuto(GetInput.inputString());

		try {
			for (int i = 0; i < av.kundenLaenge(); i++) {
				if (auto.getKennzeichen().equals(av.getKunden()[i].getCar().getKennzeichen())) {
					System.out.println(auto);
					System.out.println("Die Kundennummer des Mieters lautet: " + av.getKunden()[i].getKundenNr());
				} else {
					kundenCount++;
				}
			}
		} catch (NullPointerException e) {
			System.out.println("Es existiert kein Auto mit diesem Kennzeichen.");
		}

		if (kundenCount == av.kundenLaenge()) {
			System.out.println("Dieses Auto ist aktuell von keinem Kunden gemietet.");
		}

	}

	/**
	 * Ein Kunde, der ueber die Kundenummer ausgewaehlt wird, gibt sein gemietetes
	 * Auto ab. Wenn dies erfolgreich passiert, wird angewiesen eine Rechnung zu
	 * erstellen.
	 * 
	 * @see Autovermietung#erstelleRechnung(int, double, double, boolean)
	 */
	public static void autoAbgeben() {
		System.out.println("Welcher Kunde will ein Auto abgeben? Geben Sie bitte dessen Kundennummer ein.");
		Kunde kunde = av.getKunde(GetInput.inputInt());

		try {
			if (kunde.isHasCar() == false) {
				System.out.println("Dieser Kunde hat kein Auto was er abgeben kann. Prozess wird beendet.");
			} else {
				double kmGefahren = getKMAnzahl();
				kunde.getCar().setIstVermietet(false);
				kunde.getCar().setKmStand(kunde.getCar().getKmStand() + kmGefahren);
				av.erstelleRechnung(kunde.getKundenNr(), kmGefahren,
						berechneKosten(kmGefahren, kunde.getCar().getPreisProKM()), false);
				kunde.removeCar();
			}
		} catch (NullPointerException e) {
			System.out.println("Dieser Kunde existiert nicht.");
		}
	}

	/**
	 * Zeigt alle Rechnungen eines Kunden, auswaehlbar ueber die Kundennummer.
	 */
	public static void zeigeRechnung() {
		System.out.println("Wie lautet die Rechnungsnummer welche Sie angezeigt bekommen wollen?");
		Rechnung rechnung = av.getRechnung(GetInput.inputInt());

		if (rechnung != null) {

			System.out.println(rechnung);
		} else {
			System.out.println("Rechnung mit genannter Rechnungsnummer existiert nicht.");
		}

	}

	/**
	 * Zeigt alle Rechnungen, sortiert aufsteigend nach Rechnungsbetrag an.
	 */
	public static void alleRechnungenSortiert() {
		Rechnung[] output = new Rechnung[av.getRechnungen().length];
		output = sortRechnung(av.getRechnungen());

		for (int x = 0; x < output.length; x++) {
			System.out.println(output[x]);
		}

	}

	/**
	 * Methode datenSpeichern, serialisiert ein Objekt Autovermietung in eine .txt
	 * Datei. Der Nutzer gibt Pfad und Dateiname über die Konsole ein.
	 * 
	 * @throws FileNotFoundException    - wenn die Datei oder der Pfad nicht
	 *                                  gefunden wird.
	 * @throws NotSerializableException - wenn das übergebene Objekt nicht
	 *                                  serialisierbar ist.
	 */
	public static void datenSpeichern() throws FileNotFoundException, NotSerializableException {

		String home = System.getProperty("user.home");
		String path = erfragePfad(home);
		String name = erfrageName();

		File datei = new File(home + path + name + ".txt");

		FileOutputStream fos = new FileOutputStream(datei);
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(fos);
			oos.writeObject(av);
			oos.close();
			System.lineSeparator();
			System.out.println("Daten gespeichert in: " + datei);
		} catch (IOException e) {
			System.out.println("IO EXCEPTION");
			e.printStackTrace();
		}
	}

	/**
	 * Methode datenLaden, deserialisiert ein Objekt Autovermietung aus einer .txt
	 * Datei. Die Objekte werden in die jeweiligen Arrays und Maps übergeben. Der
	 * Nutzer gibt Pfad und Dateiname über die Konsole ein.
	 * 
	 * @throws FileNotFoundException    - wenn die Datei oder der Pfad nicht
	 *                                  gefunden wird.
	 * @throws NotSerializableException - wenn das übergebene Objekt nicht
	 *                                  serialisierbar ist.
	 */
	public static void datenLaden() throws FileNotFoundException, NotSerializableException {
		String home = System.getProperty("user.home");
		String path = erfragePfad(home);
		String name = erfrageName();

		File datei = new File(home + path + name + ".txt");

		FileInputStream fis = new FileInputStream(datei);
		ObjectInputStream ois;

		try {
			ois = new ObjectInputStream(fis);
			av = (Autovermietung) ois.readObject();
			ois.close();
		} catch (IOException e) {
			System.out.println("IO EXCEPTION");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("Fehler bei Class");
			e.printStackTrace();
		} catch (ClassCastException e) {
			System.out.println("Die Datei die Sie versucht haben zu laden, enthält ein Objekt vom falschen Typ.");
		}

	}

	/**
	 * Methode erfragePfad, fragt den Nutzer nach dem Pfad in welchem er eine Datei
	 * speichern oder laden möchte. Es wird zunächst der derzeitige Homepfad des
	 * Nutzers ausgegeben, denn dieser soll ergänzt werden. Danach ein Beispiel
	 * Pfad.
	 * 
	 * @param home der übergebene Homepfad, um den User eine mögliche Eingabe
	 *             aufzuweisen.
	 * @return gibt den vom User eingegebenen Pfad zurück.
	 */
	public static String erfragePfad(String home) {
		System.out.println("Wie lautet der Pfad der Datei? " + System.lineSeparator()
				+ "Ergänzen Sie dazu den folgenden Pfad: " + home + System.lineSeparator() + "Im folgenden Format: "
				+ home + File.separator + "Desktop" + File.separator + "EinOrdner" + File.separator + "...");
		return GetInput.inputString();
	}

	public static String erfrageName() {
		System.out.println("Wie lautet der Name der Datei? Bitte machen Sie keine Angaben zum Dateityp.");
		return (File.separator + GetInput.inputString());
	}

	/**
	 * Methode kundenExport, exportiert Kundennummer, Name, Kundenart und
	 * Gesamtbetrag aller Kunden in einer .CSV Datei. Der Nutzer gibt Pfad und
	 * Dateiname über die Konsole ein.
	 */
	public static void kundenExport() {
		String home = System.getProperty("user.home");
		String path = erfragePfad(home);
		String name = erfrageName();

		File datei = new File(home + path + name + ".csv");

		try {

			FileWriter writer = new FileWriter(datei);
			writer.write("Kundennummer;Name;Kundentyp;Rechnungsbetrag" + System.lineSeparator());

			Kunde[] exportKunden = sortKunde(av.getKunden());

			for (Kunde kunde : exportKunden) {
				writer.write(kunde.getKundenNr() + ";" + kunde.getName() + ";" + kunde.getClass().getSimpleName() + ";"
						+ kunde.getTotalBetrag() + System.lineSeparator());
			}

			System.out.println(exportKunden.length + " Datensätze in die Datei " + datei + " exportiert.");
			writer.close();
		} catch (IOException e) {
		}

	}

	/**
	 * Sortiert ein Rechnungsarray nach Rechnungsbetrag.
	 * 
	 * @param toSort das Array an Rechnungen das sortiert werden soll.
	 * @return das nach Rechnungsbetrag sortierte Rechnungsarray.
	 */
	public static Rechnung[] sortRechnung(Rechnung[] toSort) {
		Arrays.sort(toSort, new SortByRechnungssumme());
		return toSort;
	}

	/**
	 * Sortiert ein Kundenarray Alphabetisch nach dem Namen.
	 * 
	 * @param toSort das Array an Kunden das sortiert werden soll.
	 * @return das nach Namen zu sortierte Kundenarray.
	 */
	public static Kunde[] sortKunde(Kunde[] toSort) {
		Arrays.sort(toSort, new SortByName());
		return toSort;
	}

	/**
	 * @return Fragt den User wieviele Kilometer der Kunde gefahren ist und gibt den
	 *         Wert zurueck.
	 */
	public static double getKMAnzahl() {
		double input = 0;

		do {
			System.out.println("Wieviele Kilometer ist der Kunde gefahren?");
			input = GetInput.inputDouble();
			if (input <= 0.0) {
				System.out.println("Die Kilometeranzahl darf nicht negativ sein. Bitte wiederholen Sie die Eingabe.");
				System.lineSeparator();
			}
		} while (input < 0);

		return input;

	}

	/**
	 * berechnet die Kosten fuer ein Auto
	 * 
	 * @param km     gefahrene Kilometer
	 * @param kosten preis pro Kilometer
	 * @return km*kosten, was der Endpreis fuer eine Rechnung ist.
	 */
	public static double berechneKosten(Double km, Double kosten) {
		return (km * kosten);
	}

	/**
	 * Universelle Methode, die checkt ob ein Input null ist.
	 * 
	 * @param bez   Beschriftung fuer den print.
	 * @param input Objekt das auf Null getestet werden soll.
	 * @return true wenn das Objekt null ist, false wenn nicht.
	 */
	public static boolean isItNull(String bez, Object input) {
		if (input == null) {
			System.out.println("Es existiert kein " + bez);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Methode, welche den Nutzer fragt welcher Kunde eine Rechnung begleichen will,
	 * und welche. Sie werden auf Existenz geprüft, dann wird an zahleRechnung()
	 * weitergegeben.
	 * 
	 * @see "Autovermietung#zahleRechnung(Kunde, Rechnung)"
	 */
	private static void rechnungBezahlen() {
		System.out.println("Welcher Kunde will eine Rechnung begleichen? (Kundennummer)");
		int knr = GetInput.inputInt();

		Kunde bezKunde = av.getKunde(knr);
		Rechnung[] bezRechnungen;
		try {
			bezRechnungen = bezKunde.getRechnung();
		} catch (NullPointerException e) {
			System.out.println("Kunde existiert nicht.");
			return;
		}

		if (bezRechnungen.length <= 0) {
			System.out.println(
					"Keine Rechnung vorhanden. Bitte wählen Sie einen anderen Kunden oder erstellen Sie eine Rechnung.");
			return;
		}

		System.out.println("Welche Rechnung soll beglichen werden? (Rechnungsnummer): ");
		System.lineSeparator();
		for (Rechnung r : bezRechnungen) {
			System.out.println(r);
		}

		int bezRNr = GetInput.inputInt();
		Rechnung bezRechnung = av.getRechnung(bezRNr);

		try {
			if (av.kundeHasRechnung(bezKunde, bezRechnung) == true && bezRechnung.isBezahlt() == false) {
				av.zahleRechnung(bezKunde, bezRechnung);
			} else {
				System.out.println("Diese Rechnung gehört nicht zu dem gewählten Kunden oder wurde bereits bezahlt.");
			}
		} catch (NullPointerException e) {
			System.out.println("Rechnung existiert nicht.");
			return;
		}
	}

	/**
	 * Prueft ob das uebergebene Datum dem vorgegebenen Format entspricht.
	 * 
	 * @param dateToValidate das Datum das geprueft werden soll
	 * @return true wenn das Datum korrekt eingegeben wurde, false wenn nicht.
	 * 
	 */
	public static boolean datumValid(String dateToValidate) {
		if (dateToValidate == null) {
			return false;
		}
		format.setLenient(false);

		try {
			Date testDate = format.parse(dateToValidate);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}

	/**
	 * Ändert die Vorgehensweise des Loggers, welcher auf dem Strategie Pattern basiert.
	 * Derzeite Loggingoptionen: Konsolenausgabe, TXT.
	 */
	public static void switchLog() {
		Logger logger = Logger.getLoggerInstance();

		
		System.out.println("Wählen Sie ein Loggingverhalten: ");
		System.out.println("1. Konsolenausgabe");
		System.out.println("2. Speichern in .TXT");

		int newLog = -1;
		newLog = GetInput.inputInt();

		switch (newLog) {
		case 1:
			logger.switchLogging(new LogConsole());
			break;
		case 2:
			logger.switchLogging(new LogTxT());
			break;
		default:
			System.out.println("Ungültige Eingabe.");
			break;
		}
	}

	public static void starteGUI() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					System.out.println(av.getKunden()[0].getName());
					GUI frame = new GUI(av);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}
