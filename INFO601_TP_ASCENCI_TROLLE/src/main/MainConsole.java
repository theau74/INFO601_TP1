package main;

import constantes.ConstantesBasiques;
import lisibilite_code.ActionConsole;
import modele.Graphe;

/**********************************************************
 * 
 * MainConsole:
 * 
 * Interface sur console.
 * L'utilisateur peut manipuler un graphe orienté.
 * 
 * Démonstration lemme de König.
 * Pour un chemin donné, retourne un chemin élémentaire.
 * 
 * IDEE: 
 * Sauvegarde des graphes créés dans fichiers.
 * Un fichier par graphe + un fichier json sauvegardant
 * le nom de chaque graphe.
 * Le nom d'un graphe est unique.
 * On peut récupérer un graphe sauvegardé.
 * 
 * IDEE:
 * Différencier graphe orienté/non orienté.
 * => configGraphe s'en occupe.
 * 
 * IDEE:
 * Pouvoir créer plusieurs noeuds à la fois.
 * 
 * IDEE:
 * Créer des classes Exceptions pour optimiser code.
 * 
 * IDEE:
 * Interface graphique (à faire en dernier lieu)
 * 
 *********************************************************/

public class MainConsole extends ActionConsole {
	
	/**********************************************************
	 * 
	 * Attributs de MainConsole:
	 * - un graphe
	 * 
	 *********************************************************/
	
	private static Graphe g;
	
	/**********************************************************
	 * 
	 * Méthodes privées
	 * 
	 *********************************************************/
	
	/*
	 * 
	 * Configuration du graphe.
	 * 
	 * Demande à l'utilisateur le nom du graphe.
	 * 
	 */
	
	private static void configGraphe() {
		ecrire_console(ConstantesBasiques.CONSOLE_SEPARATOR);
		ecrire_console("Nom du graphe: ");
		
		/* à modifier pour choix entre graphe orienté/non orienté */
		g = new Graphe(recupere_string());
	}
	
	/*
	 * 
	 * Afficher l'état du graphe.
	 * 
	 */
	
	private static void displayStateGraphe() {
		ecrire_console(g.toString());
	}
	
	/*
	 * 
	 * Afficher le menu.
	 * 
	 */
	
	private static void displayMenu() {
		ecrire_console("Commandes:");
		ecrire_console("1 - Creer un nouveau noeud");
		ecrire_console("2 - Supprimer un noeud");
		ecrire_console("3 - Creer un lien entre deux noeuds");
		ecrire_console("4 - Supprimer un lien existant entre deux noeuds");
		ecrire_console("5 - Verifier si un chemin existe entre deux noeuds");
		ecrire_console(ConstantesBasiques.CONSOLE_SEPARATOR);
	}
	
	/*
	 * 
	 * Traite le cas où l'utilisateur souhaite
	 * créer un nouveau noeud.
	 * 
	 */
	
	private static void handleNewNoeud() {
		ecrire_console("Nom du nouveau noeud:");
		while(!g.newNoeud(recupere_string())) {
			ecrire_console("Ce nom a deja ete attribue.");
		}
		ecrire_console("Le noeud a bien ete cree.");
	}
	
	/*
	 * 
	 * Traite le cas où l'utilisateur souhaite
	 * supprimer un noeud existant.
	 * 
	 */
	
	private static void handleRemoveNoeud() {
		if(g.nbNoeuds() > 0) {
			ecrire_console("Nom du noeud a supprimer:");
			
			/* on autorise l'utilisateur a revenir en arriere en cas d'erreur */
			if(g.removeNoeud(recupere_string())) {
				ecrire_console("Le noeud a bien ete supprime.");
			}
			else {
				ecrire_console("Aucun noeud n'a ce nom.");
			}
		}
		else {
			ecrire_console("Aucun noeud n'a encore ete cree.");
		}
	}
	
	/*
	 * 
	 * Traite le cas où l'utilisateur souhaite
	 * créer un lien entre deux noeuds existants.
	 * 
	 */
	
	private static void handleNewLien() {
		if(g.nbNoeuds() > 0) {
			ecrire_console("Nom du premier noeud:");
			String vois1 = recupere_string();
			ecrire_console("Nom du deuxieme noeud:");
			String vois2 = recupere_string();
			
			while(!g.newLien(vois1, vois2)) {
				ecrire_console("Au moins un des deux noeuds n'existe pas.");
				
				ecrire_console("Nom du premier noeud:");
				vois1 = recupere_string();
				ecrire_console("Nom du deuxieme noeud:");
				vois2 = recupere_string();
			}
			ecrire_console("Le lien a bien ete cree entre " + vois1 + " et " + vois2 + ".");
			
		}
		else {
			ecrire_console("Il n'y a pas assez de noeuds crees.");
		}
	}
	
	/*
	 * 
	 * Traite le cas où l'utilisateur souhaite
	 * supprimer un lien existant entre deux
	 * noeuds.
	 * 
	 */
	
	private static void handleRemoveLien() {
		if(g.lienExiste()) {
			ecrire_console("Nom du premier noeud:");
			String vois1 = recupere_string();
			ecrire_console("Nom du deuxieme noeud:");
			String vois2 = recupere_string();
			
			while(!g.removeLien(vois1, vois2)) {
				ecrire_console("Au moins un des deux noeuds ou le lien n'existe pas.");
				
				ecrire_console("Nom du premier noeud:");
				vois1 = recupere_string();
				ecrire_console("Nom du deuxieme noeud:");
				vois2 = recupere_string();
			}
			ecrire_console("Le lien a bien ete supprime entre " + vois1 + " et " + vois2 + ".");
		}
		else {
			ecrire_console("Aucun lien n'a encore ete cree.");
		}
	}
	
	/*
	 * 
	 * 
	 * 
	 */
	
	private static void handleChemin() {
		if(g.nbNoeuds() > 1) {
			ecrire_console("Nom du premier noeud:");
			String noeud1 = recupere_string();
			ecrire_console("Nom du deuxieme noeud:");
			String noeud2 = recupere_string();
			
			if(g.cheminExiste(noeud1, noeud2)) {
				ecrire_console("Il existe au moins un chemin entre les deux noeuds.");
				ecrire_console("Un exemple de chemin:");
				ecrire_console(g.chemin(noeud1, noeud2).toString());
			}
			else {
				ecrire_console("Aucun chemin ne relie les deux noeuds.");
			}
		}
		else {
			ecrire_console("Il n'y a pas assez de noeuds crees.");
		}
	}
	
	/*
	 * 
	 * Récupère le choix de commande de l'utilisateur
	 * et gère le cas à traiter.
	 * 
	 */
	
	private static Graphe handleCommand() {
		ecrire_console("Choix de commande (1 - 4) :");
		
		int choix = recupere_int();
		
		while(!(choix >= 1 && choix <= 5)) {
			ecrire_console("La commande saisie doit etre comprise entre 1 et 5.");
			choix = recupere_int();
		}
		
		switch (choix) {
		case 1:
			handleNewNoeud();
			break;
		case 2:
			handleRemoveNoeud();
			break;
		case 3:
			handleNewLien();
			break;
		case 4:
			handleRemoveLien();
			break;
		case 5:
			handleChemin();
			break;
		default:
			ecrire_console("Erreur.");
			break;
		}
		
		return g;
	}
	
	/**********************************************************
	 * 
	 * La méthode principale de l'application.
	 * Affiche sur la console la liste des commandes disponibles.
	 * Lui permet d'entrer une commande.
	 * Affiche le résultat.
	 * 
	 *********************************************************/

	public static void main(String[] args) {
		
		/* initialisation des variables */
		boolean continuer = true;
		
		/* configuration */
		configGraphe();
		
		/* affichage de l'état du graphe */
		displayStateGraphe();
		
		/* coeur du programme */
		while (continuer) {
			
			/* affiche le menu */
			displayMenu();
			
			/* récupère et traite le choix de l'utilisateur */
			handleCommand();
			
			/* affiche l'état du graphe */
			displayStateGraphe();
			
		}
		
	}

}