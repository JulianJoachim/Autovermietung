package modellierung;

import java.util.Scanner;

/**
 * @author Julian Joachim s0574380<br>
 * @version Eclipse 2019-09 4.1.3 / Java 1.8.0_ 191<br>
 * @since 06.05.2020 zuletzt bearbeitet am 13.05.2020. <br>
 *        Klasse GetInput, stellt Methoden bereit, mit welchen leicht
 *        Scannereingaben erfolgen koennen.
 *
 */
public class GetInput {

	/** Import des Scanners zur Konsoleneingabe */
	static Scanner sc = new Scanner(System.in);

	/**
	 * Standardkonstruktor der Klasse GetInput.
	 */
	public GetInput() {
	}

	/**
	 * Universelle Methode die einen String input des Users liest und ihn zurueck
	 * gibt
	 * 
	 * @return Input des Users
	 */
	public static String inputString() {
		String choiceInternal;
		while (true) {
			try {
				choiceInternal = sc.nextLine();
				break;
			} catch (Exception e) {
				System.out.println("Sie haben keinen gueltigen Wert eingegeben. Bitte gib eine gueltige Eingabe ein.");
			}
		}
		return choiceInternal;
	}

	/**
	 * Universelle Methode die einen int input des Users liest und ihn zurueck gibt
	 * 
	 * @return Input des Users
	 */
	public static int inputInt() {
		int choiceInternal;
		while (true) {
			try {
				choiceInternal = Integer.parseInt(sc.next());
				sc.nextLine();
				break;
			} catch (Exception e) {
				System.out.println("Sie haben keinen gueltigen Wert eingegeben. Bitte gib eine gueltige Eingabe ein.");
			}
		}
		return choiceInternal;
	}

	/**
	 * Universelle Methode die einen double input des Users liest und ihn zurueck
	 * gibt
	 * 
	 * @return Input des Users
	 */
	public static double inputDouble() {
		double choiceInternal;
		while (true) {
			try {
				choiceInternal = Double.parseDouble(sc.next());
				sc.nextLine();
				break;
			} catch (Exception e) {
				System.out.println("Sie haben keinen gueltigen Wert eingegeben. Bitte gib eine gueltige Eingabe ein.");
			}
		}
		return choiceInternal;
	}

}
