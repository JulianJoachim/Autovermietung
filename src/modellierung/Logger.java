package modellierung;

/**
 * @author Julian Joachim s0574380<br>
 * @version Eclipse 2019-09 4.1.3 / Java 1.8.0_ 191<br>
 * @since 14.06.2020 zuletzt bearbeitet am 16.06.2020<br>
 *        Die Klasse Logger ist nur zum implementieren der Strategy-Pattern
 *        vorhanden. Sie ist Singleton, das heißt man kann Sie nur mit dessen
 *        Methode getLoggerInstance() erreichen.
 *
 */
public class Logger implements LoggingVerhalten {
	private static Logger logger = null;
	private LoggingVerhalten LoggingVerhalten = new LogConsole();

	/**
	 * privater Konstruktor, damit keine weiteren Instanzen angelegt werden können.
	 */
	private Logger() {
	}

	/**
	 * Methode um die eine einzige Instanz der Klasse zurückzugeben, bzw diese
	 * zunächst zu erstellen.
	 * 
	 * @return Instanz von Logger
	 */
	public static Logger getLoggerInstance() {
		if (logger == null) {
			logger = new Logger();
		}
		return logger;
	}

	public void switchLogging(LoggingVerhalten newLogVer) {
		System.out.println("Loggingverhalten geändert zu: " + newLogVer.getClass().getSimpleName());
		LoggingVerhalten = newLogVer;
	}

	/**
	 * Methode, die zwangsweise implementiert werden muss, da Logger vom Interface
	 * LoggingVerhalten gebrauch macht. Diese Methode sollte jedoch im Normalfall
	 * niemals aufgerufen werden.
	 */
	@Override
	public void log(Rechnung r, Kunde k) {
		LoggingVerhalten.log(r, k);
	}
}
