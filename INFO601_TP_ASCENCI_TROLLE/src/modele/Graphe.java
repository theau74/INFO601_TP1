package modele;

import java.util.ArrayList;

import constantes.ConstantesBasiques;

/**********************************************************
 * 
 * Graphe:
 * 
 * Représente un graphe orienté.
 * Il est défini par un ensemble de noeuds et de liens.
 * 
 *********************************************************/

public class Graphe {

	/**********************************************************
	 * 
	 * Attributs du graphe:
	 * - un nom
	 * - une liste de noeuds
	 * 
	 *********************************************************/
	
	private String nom;
	private ArrayList<Noeud> noeuds = new ArrayList<Noeud>();
	
	/**********************************************************
	 * 
	 * Constructeur du graphe:
	 * - un nom
	 * - une liste de noeuds
	 * 
	 *********************************************************/
	
	public Graphe(String nom, ArrayList<Noeud> noeuds) {
		setNom(nom);
		setNoeuds(noeuds);
	}
	
	public Graphe(String nom) {
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
	
	public void setNoeuds(ArrayList<Noeud> pred) {
		for(int i=0; i < pred.size(); i++) {
			this.noeuds.add(pred.get(i));
		}
	}
	
	public ArrayList<Noeud> getNoeuds() {
		return this.noeuds;
	}
	
	/**********************************************************
	 * 
	 * Méthodes privées:
	 * 
	 *********************************************************/
	
	/*
	 * 
	 * Vérifie s'il existe un noeud de nom donné.
	 * 
	 * - nomNoeud: Le nom cherché.
	 * 
	 * Retourne la position du noeud si trouvé, sinon -1.
	 * 
	 */
	
	private int noeudExiste(String nomNoeud) {
		int trouve = -1;
		int i = 0;
		while(i < getNoeuds().size() && trouve == -1) {
			if(getNoeuds().get(i).getNom().equals(nomNoeud)) {
				trouve = i;
			}
			i++;
		}
		return trouve;
	}
	
	/**********************************************************
	 * 
	 * Méthodes publiques:
	 * 
	 *********************************************************/
	
	/*
	 * 
	 * Créer un nouveau noeud.
	 * La création est faite si et seulement si
	 * aucun noeud de même nom existe.
	 * 
	 * - nomNoeud: Le nom du nouveau noeud.
	 * 
	 * Retourne un booléen.
	 * 
	 */
	
	public boolean newNoeud(String nomNoeud) {
		boolean nExistePas = (noeudExiste(nomNoeud) == -1);
		if(nExistePas) {
			getNoeuds().add(new Noeud(nomNoeud));
		}
		return nExistePas;
	}
	
	/*
	 * 
	 * Supprime le noeud ayant le nom donné.
	 * 
	 * - nomNoeud: Le nom du noeud à supprimer.
	 * 
	 * Retourne un booléen.
	 * 
	 */
	
	public boolean removeNoeud(String nomNoeud) {
		int pos = noeudExiste(nomNoeud);
		if (pos != -1) {
			int n = getNoeuds().get(pos).degre();
			/* on supprime toutes les relations liees a ce noeud */
			for(int i=0; i<n; i++) {
				removeLien(nomNoeud, getNoeuds().get(pos).getVoisins().get(0).getNom()); // 0 => ArrayList supprime au fur et à mesure
			}
			
			/* on supprime le noeud */
			getNoeuds().remove(pos);
		}
		return pos != -1;
	}
	
	/*
	 * 
	 * Retourne le nombre de noeuds existants dans le graphe.
	 * 
	 */
	
	public int nbNoeuds() {
		return getNoeuds().size();
	}
	
	/*
	 * 
	 * Créer une nouveau lien orienté entre deux noeuds.
	 * 
	 * - vois1: Le nom du noeud prédécesseur.
	 * - vois2: Le nom du noeud successeur.
	 * 
	 * Retourne un booléen.
	 * 
	 */
	
	public boolean newLien(String vois1, String vois2) {
		int posVois1 = noeudExiste(vois1);
		int posVois2 = noeudExiste(vois2);
		
		boolean okay = posVois1 != -1 && posVois2 != -1;
		
		if(okay) {
            getNoeuds().get(posVois1).addVoisin(getNoeuds().get(posVois2));
            
            /* gérer le cas où un noeud s'ajoute lui-même en voisin */
            if (!getNoeuds().get(posVois1).getNom().equals(getNoeuds().get(posVois2).getNom())) {
                getNoeuds().get(posVois2).addVoisin(getNoeuds().get(posVois1));
            }
        }
		
		return okay;
	}
	
	/*
	 * 
	 * Supprimer un lien existant.
	 * 
	 * - vois1: Le nom du noeud prédécesseur.
	 * - vois2: Le nom du noeud successeur.
	 * 
	 * Retourne un booléen.
	 * 
	 */
		
	public boolean removeLien(String vois1, String vois2) {
		int posVois1 = noeudExiste(vois1);
		int posVois2 = noeudExiste(vois2);
		
		boolean okay = posVois1 != -1 && posVois2 != -1 && getNoeuds().get(posVois1).hasForNeighbour(getNoeuds().get(posVois2));
		
		System.out.println("(" + vois1 + ", " + vois2 + "): " + okay);
		
		if(okay) {
			getNoeuds().get(posVois1).removeVoisin(getNoeuds().get(posVois2));
			
			/* gérer le cas où un noeud s'ajoute lui-même en voisin */
            if (!getNoeuds().get(posVois1).getNom().equals(getNoeuds().get(posVois2).getNom())) {
            	getNoeuds().get(posVois2).removeVoisin(getNoeuds().get(posVois1));
            }
		}
		
		return okay;
	}
	
	/*
	 * 
	 * Vérifie s'il existe un lien dans le graphe.
	 * 
	 * Retourne un booléen.
	 * 
	 */
	
	public boolean lienExiste() {
		boolean existe = false;
		int i = 0;
		while(i<getNoeuds().size()) {
			if(getNoeuds().get(i).degre() > 0) {
				existe = true;
			}
		}
		return existe;
	}
	
	/*
	 * 
	 * Retourne l'état du graphe.
	 * 
	 * Retourne une chaine de caractères.
	 * 
	 */
	
	public String toString() {
		String s = ConstantesBasiques.CONSOLE_SEPARATOR + "\n";
		s += "Nom du graphe: " + getNom() + "\n";
		s += ConstantesBasiques.CONSOLE_SEPARATOR + "\n";
		for(int i=0; i < getNoeuds().size(); i++) {
			s += "(" + getNoeuds().get(i).getNom() + "):\n";
			
			int nbVois = getNoeuds().get(i).degre();
			if(nbVois > 0) {
				s += "voisins => (";
				for(int j=0; j < nbVois; j++) {
					s += "(" + getNoeuds().get(i).getVoisins().get(j).getNom() + ")";
				}
				s += ")\n";
			}
		}
		s += ConstantesBasiques.CONSOLE_SEPARATOR + "\n";
		return s;
	}
	
}
