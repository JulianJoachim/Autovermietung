package modellierung;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Julian Joachim s0574380<br>
 * @version Eclipse 2019-09 4.1.3 / Java 1.8.0_ 191<br>
 * @since 14.06.2020 zuletzt bearbeitet am 16.06.2020<br>
 *        Die Klasse LogTxT ist eine spezifische Implementation des Interfaces
 *        LoggingVerhalten. Es wird ein Log auf der Konsole ausgegeben.
 *
 */
public class LogTxT implements LoggingVerhalten {
	private Date date = new Date();
	private SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

	/**
	 * Die log Methode führt den Logging-Prozess aus. Es werden Kundenname, Datum,
	 * Rechnungsnummer und Rechnungsbetrag in eine Textdatei abgespeichert. Diese
	 * Textdatei heißt LogDaten.txt und wird im Ordner des Javaprogrammes
	 * abgespeichert.
	 * 
	 */
	@Override
	public void log(Rechnung r, Kunde k) {
		try {
			FileWriter writer;
			writer = new FileWriter("LogDaten.txt", true);
			writer.write("Der Kunde " + k.getName() + " hat am " + formatter.format(date)
					+ " seine Rechnung mit der Rechnungsnummer " + r.getRechnungsnummer() + " in Höhe von "
					+ r.getRechnungsbetrag() + " beglichen." + System.lineSeparator());
			writer.close();
		} catch (IOException e) {
			System.out.println("Fehler beim Schreiben in die Logdatei.");
		}
	}
}
