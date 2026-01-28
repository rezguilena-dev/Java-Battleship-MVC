package game.ecouteur;
import game.modele.jeu.*;
import game.modele.terrain.*;
import game.vue.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.util.*;

public class JeuControleur extends MouseAdapter {
	private Jeu jeu;
	private VueNaval vue;
	private NavalGUI gui;

    /**
	 * Constructeur JeuControleur
	 * @param jeu un jeu de bataille navale
	 * @param vue une vue pour l'affichage graphique
	 * @param gui l'affichage graphique
	 */	
    public JeuControleur(Jeu jeu, VueNaval vue, NavalGUI gui){
    	this.jeu = jeu;
    	this.vue = vue;
    	this.gui = gui;
    }
    	
	/**
	 * Gere le clique de la souris
	 * @param e le clique
	 */
    @Override
    public void mouseClicked(MouseEvent e) {

		int spaceBetween = vue.getSpaceBetween();
		int tailleCellule = vue.getTailleCellule();
		int j = (e.getX() - spaceBetween) / tailleCellule - 1;
		int i = e.getY() / tailleCellule - 1;
		Position pos = new Position(i,j,this.jeu.getJ1().getGrid().getSymbolFromCoord(i,j));
			
		if (e.getX() >= spaceBetween + tailleCellule && e.getX() < spaceBetween + this.jeu.getJ1().getGrid().getWidth() * tailleCellule + tailleCellule && this.jeu.getJ1().getListeTirPossible().contains(pos)) {
			this.jeu.setPositionClique(pos);
			tour();
		}
        else {
        	this.gui.setTextCoupJ1("Position invalide, veuillez reessayer");
        }
    }

	/**
	 * Verifie si le jeu est fini + affiche les textes de fin de partie de chaque joueurs 
	 */
    public void gameOver(){
	    for (MouseListener ml : this.vue.getMouseListeners()) {
			this.vue.removeMouseListener(ml);
	    }

	    if (this.jeu.getWinner().equals(this.jeu.getJ2())){
			this.gui.setTextCoupJ2(this.jeu.getJ2().getVictoryText());
			this.gui.setTextCoupJ1(this.jeu.getJ1().getDefeatText());
	    }
	    else {
	    	this.gui.setTextCoupJ1(this.jeu.getJ1().getVictoryText());
			this.gui.setTextCoupJ2(this.jeu.getJ2().getDefeatText());
	    }
    }
    
	/**
	 * Gere une manche de jeu (tour joueur + tour ia)
	 */
    public void tour(){
	    //tour du joueur
	    this.jeu.execute();
	    this.gui.setTextCoupJ1(this.jeu.getJ1().getActionText());

	    if (this.jeu.isOver()){
	    	gameOver();
	    	return;
	    }
		
	    //tour de l'Ia
	    this.jeu.execute();
	    this.gui.setTextCoupJ2(this.jeu.getJ2().getActionText());

	    if (this.jeu.isOver()){
	    	gameOver();
	    	return;
	    }	
    }
}
