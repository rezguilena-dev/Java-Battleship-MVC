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

public class ControleurTerminal {
	private Jeu jeu;
	private VueTerminal vueT;
    	
	/**
	 * Constructeur ControleurTerminal
	 * @param jeu un jeu de bataille navale
	 * @param vueT une vue pour le terminal
	 */
    public ControleurTerminal(Jeu jeu, VueTerminal vueT){
    	this.jeu = jeu;
    	this.vueT = vueT;
    }
    	
	/**
	 * La boucle de jeu pour le terminal
	 */
    public void boucleJeu(){
    	while (!this.jeu.isOver()){
    		tour();
    	}
    }
    	
	/**
	 * Verifie si le jeu est fini + affiche les textes de fin de partie de chaque joueurs 
	 */
    public void gameOver(){
	   	if (this.jeu.getWinner().equals(this.jeu.getJ2())){
		this.vueT.setTextCoupJ2(this.jeu.getJ2().getVictoryText());
		this.vueT.setTextCoupJ1(this.jeu.getJ1().getDefeatText());
	   	}
	   	else {
	   		this.vueT.setTextCoupJ1(this.jeu.getJ1().getVictoryText());
			this.vueT.setTextCoupJ2(this.jeu.getJ2().getDefeatText());
	  	}
    }
    
	/**
	 * Gere une manche de jeu (tour joueur + tour ia)
	 */
    public void tour(){
	   	this.jeu.execute();
	   	this.vueT.setTextCoupJ1(this.jeu.getJ1().getActionText());
	
	   	if (this.jeu.isOver()){
	   		gameOver();
	   		return;
	   	}
	
	   	this.jeu.execute(); //tour de l'Ia
	   	this.vueT.setTextCoupJ2(this.jeu.getJ2().getActionText());
	
	   	if (this.jeu.isOver()){
	   		gameOver();
	   		return;
	   	}	
    }
}
