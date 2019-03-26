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
	 * - une mémoire
	 * 
	 *********************************************************/
	
	private String nom;
	private ArrayList<Noeud> noeuds = new ArrayList<Noeud>();
	private ArrayList<Noeud> memoire = new ArrayList<Noeud>();
	
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
	 * Récupère la mémoire.
	 * 
	 * Retourne une listez de noeuds.
	 * 
	 */
	
	private ArrayList<Noeud> getMemory() {
		return this.memoire;
	}
	
	/*
	 * 
	 * Ajoute un nouveau noeud à la mémoire.
	 * 
	 * Retourne un booléen.
	 * 
	 */
	
	private boolean addMemory(Noeud noeud) {
		return getMemory().add(noeud);
	}
	
	/*
	 * 
	 * Vide la mémoire.
	 * 
	 */
	
	private void clearMemory() {
		getMemory().clear();
	}
	
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
	
	/*
	 * 
	 * Retourne une liste contenant les noeuds qui
	 * ne sont pas présents dans le tableau memoire.
	 * 
	 */
	
	private ArrayList<Noeud> filterMem(ArrayList<Noeud> listeNoeuds) {
		ArrayList<Noeud> nouveau = new ArrayList<Noeud>();
		
		for(int i=0; i<listeNoeuds.size(); i++) {
			
			/* si le noeud n'est pas dans la mémoire */
			if(!getMemory().contains(listeNoeuds.get(i))) {
				nouveau.add(listeNoeuds.get(i));
			}
			
		}
		
		return nouveau;
	}
	
	/*
	 * 
	 * Retourne le premier chemin élémentaire trouvé entre 2 noeuds donnés.
	 * 
	 * - memoire: sauvegarde des noeuds explorés
	 * - chemin: le chemin stocké
	 * 
	 * Retourne une liste de noms de noeuds.
	 * - Vide si aucun chemin trouvé.
	 * 
	 */
	
	private ArrayList<String> cheminRec(Noeud noeudActuel, Noeud noeudCherche, ArrayList<String> chemin) {
		
		/* le noeud cherché a été trouvé */
		if(noeudActuel == noeudCherche) {
			
			/* on ajoute le noeud actuel au chemin */
			chemin.add(noeudActuel.getNom());
			
			return chemin;
		}
		
		/* on n'a pas encore trouvé le noeud cherché */
		else {
			/* on ajoute le noeud actuel à la mémoire */
			addMemory(noeudActuel);
			
			/* on récupère tous les voisins non explorés */
			ArrayList<Noeud> voisNonExplores = filterMem(noeudActuel.getVoisins());
			
			/* s'il y a au moins un voisin non exploré */
			if(voisNonExplores.size() != 0) {
				boolean trouve = false;
				
				/* on accède à tous les voisins non explorés */
				int i = 0;
				while(i < voisNonExplores.size() && !trouve) {
					/* on met à jour le chemin */
					chemin = cheminRec(voisNonExplores.get(i), noeudCherche, chemin);
					
					trouve = chemin.size() != 0;
					i++;
				}
				/* si le noeud cherché a été trouvé */
				if(trouve) {
					/* on ajoute le noeud actuel au chemin obtenu */
					chemin.add(0, noeudActuel.getNom());
					
					return chemin;
				}
				else {
					return new ArrayList<String>();
				}
			}
			/* s'il n'y a aucun voisin non exploré */
			else {
				return new ArrayList<String>();
			}
		}
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
		while(i<getNoeuds().size() && !existe) {
			if(getNoeuds().get(i).degre() > 0) {
				existe = true;
			}
		}
		return existe;
	}
	
	/*
	 * 
	 * Vérifie si un chemin existe entre deux noeuds.
	 * 
	 * Retourne une liste de chaines de caractères, noms des noeuds.
	 * 
	 */
	
	public ArrayList<String> chemin(String noeudActuel, String noeudCherche) {
		clearMemory();
		return cheminRec(getNoeuds().get(noeudExiste(noeudActuel)), getNoeuds().get(noeudExiste(noeudCherche)), new ArrayList<String>());
	}
	
	/*
	 * 
	 * Vérifie si un chemin existe entre deux noeuds donnés.
	 * 
	 * Retourne un booléen.
	 * 
	 */
	
	public boolean cheminExiste(String noeudActuel, String noeudCherche) {
		return chemin(noeudActuel, noeudCherche).size() > 0;
	}
	
	/*
	 * 
	 * Vérifie si le graphe est complet.
	 * 
	 * Retourne un booléen.
	 * 
	 */
	
	public boolean isFull() {
		boolean isFull = true;
		int sizeGraph = getNoeuds().size();
		
		int i = 0;
		while(i<sizeGraph && isFull) {
			/* vérifie si le nombre de voisins est égal au nombre de noeuds du graphe - 1 */
			isFull = getNoeuds().get(i).getVoisins().size() == sizeGraph - 1;
			i++;
		}
		
		return isFull;
	}
	
	/*
	 * 
	 * Calcule le nombre d'arêtes dans le graphe.
	 * 
	 * Retourne un entier.
	 * 
	 */
	
	public int nbAretes() {
		int compt;
		
		int sizeGraph = getNoeuds().size();
		
		/* si c'est un graphe complet */
		if(isFull()) {
			compt = sizeGraph * (sizeGraph - 1) / 2;
		}
		else {
			compt = 0;
			
			for(int i=0; i<sizeGraph; i++) {
				compt += getNoeuds().get(i).getVoisins().size();
			}
		}
		
		return compt;
	}
	
	/*
	 * 
	 * Vérifie si le graphe est connexe.
	 * 
	 * Retourne un booléen.
	 * 
	 */
	
	public boolean isConnected() {
		boolean isConnected = true;
		int sizeGraph = getNoeuds().size();
		
		int i = 0;
		while(i<sizeGraph && isConnected) {
			isConnected = getNoeuds().get(i).getVoisins().size() > 0;
			i++;
		}
		
		return isConnected;
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
