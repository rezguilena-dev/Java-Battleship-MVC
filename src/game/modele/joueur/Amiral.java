package game.modele.joueur;
import game.modele.jeu.*;
import game.modele.terrain.*;
import java.util.*;

public class Amiral extends Player {
	protected Scanner scan;

	/**
	 * Constructeur pour joueur Amiral
	 * @param symbole symbole attribué au joueur réel (principalement pour la vue terminal)
	 * @param scan scanner passé au constructeur pour recupérer les coups choisis par le joueur
	 * @param grille grille sur laquelle sera positionnée la flotte du joueur
	 */
	public Amiral(String symbole, Scanner scan, Grid grille, int tailleFlotte){
		super(symbole,grille,tailleFlotte);
        this.scan = scan;
	}
	
	/**
	 * Affiche la liste des coups valides pour le joueur, 
	 * récupère le coup entré par le joueur,
	 * vérifie si le coup est valide,
	 * sinon redemande un coup au joueur
	 * @param jeu jeu sur lequel on passe la fonction
	 * @return le coup choisi par le joueur de classe Postion
	 */
	@Override
	public Position action(Jeu jeu){
		Position coupChoisi;
		if (jeu.getPositionClique() == null){
			String coord = scan.next();
	    		coupChoisi = this.getGrid().getPositionFromSymbol(coord);
	    		/*if (jeu.getJ2().getGrid().estValide(coupChoisi) != true &&  < 2){
	    			System.out.println("CEST PAS VALIDE CES COORDS");
	    		}*/
	    		while (jeu.getJ2().getGrid().estValide(coupChoisi) != true || coupChoisi == null){
				coord = scan.next();
				coupChoisi = this.getGrid().getPositionFromSymbol(coord);
			}
		}
		else {
			coupChoisi = jeu.getPositionClique();
		}
		this.setListePositionTir(coupChoisi);
	    this.listeTirPossible.remove(coupChoisi);
	    return coupChoisi;
	}
	
	/**
	 * Accesseur ligne de texte à afficher pour le coup choisi
	 * @return ligne de texte action
	 */
	@Override
	public String getActionText(){
		return "Vous avez choisi la position "+this.getListePositionTir().get(this.getListePositionTir().size() - 1).toString();
	}
	
	/**
	 * Accesseur ligne de texte à afficher si victoire du joueur réel
	 * @return ligne de texte victoire
	 */
	@Override
	public String getVictoryText(){
		return "Vous avez gagné ! Le monde est sauvée !";
	}
	
	/**
	 * Accesseur ligne de texte à afficher si défaite du joueur réel
	 * @return ligne texte défaite
	 */
	@Override
	public String getDefeatText(){
		return "Vous avez perdu... C'est la fin, Skynet a pris le contrôle !";
	}
	
	/**
	 * Accesseur identifiant du joueur réel
	 * @return ligne de texte "Amiral de l'US Navy"
	 */
	public String toString(){
    	return "Amiral de l'US Navy";
    }
	
}
