package modele;

import java.util.ArrayList;

/**********************************************************
 * 
 * Noeud:
 * 
 * Représente un noeud d'un graphe.
 * Il possède des voisins.
 * qui sont eux-mêmes d'autres noeuds.
 * 
 *********************************************************/

public class Noeud {
	
	/**********************************************************
	 * 
	 * Attributs du noeud:
	 * - un nom
	 * - une liste de voisins
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
	
	public boolean addVoisin(Noeud vois) {
		return getVoisins().add(vois);
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
	
	public boolean removeVoisin(Noeud vois) {
		return getVoisins().remove(vois);
	}
	
	/*
	 * 
	 * Vérifie si un noeud a pour voisin un noeud donné.
	 * 
	 * Retourne un booléen.
	 * 
	 */
	
	public boolean hasForNeighbour(Noeud noeud) {
		boolean trouve = false;
		int i = 0;
		while(i < getVoisins().size() && !trouve) {
			trouve = getVoisins().get(i).equals(noeud);
			i++;
		}
		
		return trouve;
	}
	
	/*
	 * 
	 * Retourne le degré d'un noeud.
	 * 
	 * Retourne un entier.
	 * 
	 */
	
	public int degre() {
		return getVoisins().size();
	}
	
}
