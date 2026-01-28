package game.modele.joueur;
import game.modele.jeu.*;
import game.modele.terrain.*;
import java.util.*;

public class IaFolle extends Player{
	public Random rand;
	
	/**
	 * Constructeur IaFolle (strategie aleatoire)
	 * @param symbole le symbole attribuer au joueur IaFolle (principalement pour la vue terminal)
	 * @param grille une grille sur laquelle sera positionnée la flotte du joueur IaFolle
	 * @param tailleFlotte le nombre de bateau dans la flotte ?
	 */
	public IaFolle(String symbole, Grid grille, int tailleFlotte){
		super(symbole, grille,tailleFlotte);
		this.rand = new Random();
	}
	
	/**
	 * Renvoie une Position qui correspond à un coup choisie aléatoirement par l'Ia Folle,
	 * selon la liste de coup possible.
	 * @param jeu jeu sur lequel on passe la fonction
	 * @return le coup choisi par le joueur IaFolle de classe Postion
	 */
	@Override
	public Position action(Jeu jeu){
		Position coupChoisi = this.getGrid().getPositionFromSymbol(this.getListeTirPossible().get(rand.nextInt(this.getListeTirPossible().size())).toString());
	    this.setListePositionTir(coupChoisi);
	    this.getListeTirPossible().remove(coupChoisi);
		return coupChoisi;
	}
	
	/**
	 * Accesseur ligne de texte à afficher pour le coup choisi
	 * @return ligne de texte action
	 */
	@Override
	public String getActionText(){
		return "L'Ia completement TARÉE a choisie: "+this.getListePositionTir().get(this.getListePositionTir().size() - 1).toString();
	}
	
	/**
	 * Accesseur ligne de texte à afficher si victoire du joueur IaFolle
	 * @return ligne de texte victoire
	 */
	@Override
	public String getVictoryText(){
		return "L'Ia completement TARÉE a eliminer les derniers bateaux... L'avenir du monde est incertain... ";
	}
	
	/**
	 * Accesseur ligne de texte à afficher si défaite du joueur IaFolle
	 * @return ligne texte défaite
	 */
	@Override
	public String getDefeatText(){
		return "L'Ia completement TARÉE a ete detruite ! Pfiouu";
	}
	
	/**
	 * Accesseur identifiant du joueur réel
	 * @return ligne de texte "Amiral de l'US Navy"
	 */
	public String toString(){
    	return "Ia complétement FOLLE et TARÉE";
    }
}
