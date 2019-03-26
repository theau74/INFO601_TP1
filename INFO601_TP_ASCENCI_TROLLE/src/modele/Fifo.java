package modele;

import java.util.ArrayList;

/**********************************************************
 * 
 * Fifo:
 * 
 * Représente une liste FIFO.
 * 
 *********************************************************/

public class Fifo extends ArrayList<String> {

	/**********************************************************
	 * 
	 * Constructeur de Fifo:
	 * 
	 *********************************************************/
	
	public Fifo() {
		super();
	}
	
	/**********************************************************
	 * 
	 * Méthodes publiques:
	 * 
	 *********************************************************/
	
	/*
	 * 
	 * Ajoute une chaine de caractères en fin de file.
	 * 
	 * Retourne un booléen.
	 * 
	 */
	
	public boolean add(String element) {
		return super.add(element);
	}
	
	/*
	 * 
	 * Retire la tête de file.
	 * 
	 * Retourne la chaine de caractères retirée.
	 * 
	 */
	
	public String removeFirst() {
		return super.remove(0);
	}
	
}
