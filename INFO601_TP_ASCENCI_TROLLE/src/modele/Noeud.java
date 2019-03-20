package modele;

import java.util.ArrayList;

/**********************************************************
 * 
 * Noeud:
 * 
 * Représente un noeud d'un graphe.
 * Il possède des prédécesseurs et des successeurs
 * qui sont eux-mêmes d'autres noeuds.
 * 
 *********************************************************/

public class Noeud {
	
	/**********************************************************
	 * 
	 * Attributs du noeud:
	 * - un nom
	 * - une liste de prédécesseurs
	 * - une liste de successeurs
	 * 
	 *********************************************************/
	
	private String nom;
	private ArrayList<Noeud> voisins = new ArrayList<Noeud>();
	
	/**********************************************************
	 * 
	 * Constructeur du noeud:
	 * - un nom
	 * - une liste de prédécesseurs
	 * - une liste de successeurs
	 * 
	 *********************************************************/
	
	public Noeud(String nom, ArrayList<Noeud> voisins) {
		setNom(nom);
		setVoisins(voisins);
	}
	
	public Noeud(String nom) {
		this(nom, new ArrayList<Noeud>());
	}
	
	/**********************************************************
	 * 
	 * Getters/Setters:
	 * 
	 *********************************************************/
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setVoisins(ArrayList<Noeud> pred) {
		for(int i=0; i < pred.size(); i++) {
			this.voisins.add(pred.get(i));
		}
	}
	
	public ArrayList<Noeud> getVoisins() {
		return this.voisins;
	}
	
	/**********************************************************
	 * 
	 * Méthodes privées:
	 * 
	 *********************************************************/
	
	
	
	/**********************************************************
	 * 
	 * Méthodes publiques:
	 * 
	 *********************************************************/
	
	/*
	 * 
	 * Ajouter un nouveau voisin.
	 * 
	 * - vois: Le voisin à ajouter.
	 * 
	 * Retourne un booléen.
	 * 
	 */
	
	public boolean addVoisin(Noeud pred) {
		return this.voisins.add(pred);
	}
	
	/*
	 * 
	 * Supprimer un prédécesseur.
	 * 
	 * - pred: Le prédécesseur à supprimer.
	 * 
	 * Retourne un booléen.
	 * 
	 */
	
	public boolean removeVoisin(Noeud pred) {
		return this.voisins.remove(pred);
	}
	
}
