package lisibilite_code;

import java.util.Scanner;

/**********************************************************
 * 
 * ActionConsole:
 * 
 * A pour but d'améliorer la lisibilité de notre code.
 * 
 *********************************************************/

public class ActionConsole {
	
	/**********************************************************
	 * 
	 * Attributs de lisibilite:
	 * - un scanneur
	 * 
	 *********************************************************/
	
	protected static Scanner scan = new Scanner(System.in);
	
	/**********************************************************
	 * 
	 * Méthodes protégées:
	 * 
	 *********************************************************/
	
	protected static void ecrire_console(String s) {
		System.out.println(s);
	}
	
	protected static String recupere_string() {
		return scan.nextLine();
	}
	
	protected static int recupere_int() {
		while(!scan.hasNextInt()) {
			ecrire_console("Un entier est attendu!");
			scan.nextLine();
		}
		int res = scan.nextInt();
		scan.nextLine();
		return res;
	}
	
}
