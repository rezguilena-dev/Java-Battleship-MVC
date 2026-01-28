package game.ecouteur;
import game.modele.jeu.*;
import game.modele.terrain.*;
import game.modele.joueur.*;
import game.vue.*;
import game.vue.NavalGUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.util.*;

public class ControleurParametre {
	private Jeu jeu;
	private VueTerminal vueT;
	
	private Player adversaire;
	private Amiral player;
	private int taille;
	private Grid grille1;
	private Grid grille2;
	private boolean modeTerminal;
    	
	/**
	 * Constucteur ControleurParametre
	 */
    public ControleurParametre(){
    	this.grille1 = null;
    	this.grille2 = null;
    	this.adversaire = null;
    	this.taille = 0;
    	this.player = null;
    }

	/**
	 * Gere la difficulte de la partie
	 * @param choix le choix de difficulte (easy, normal ou terminator) 
	 */	
    public void setDifficulty(String choix){
    	if (choix == "easy"){
    		this.adversaire = new IaFolle("O", this.grille2, this.taille); 
    	}
    	else if (choix == "normal"){
    		this.adversaire = new IaPseudoIntelligente("@", this.grille2, this.taille);
    	}
    	else {
    		this.adversaire = new Terminator("#", this.grille2, this.taille, this.grille1);
    	}
	}

	/**
	 * Gere la taille de la grille
	 * @param choix le choix de la taille de grille (small, medium ou large) 
	 */	
    public void setGridSize(String choix){
    	if (choix == "small"){
    		this.taille = 2;
		this.grille1 = new Grid(5,5);
		this.grille2 = new Grid(5,5);
		}
    	else if (choix == "medium"){
    		this.grille1 = new Grid(10,10);
    		this.grille2 = new Grid(10,10);
    		this.taille = 5;
    	}
    	else {
    		this.grille1 = new Grid(15,15);
    		this.grille2 = new Grid(15,15);
    		this.taille = 7;
    	}
    	
		this.player = new Amiral("X", new Scanner(System.in), this.grille1, this.taille);
    }
    	
	/**
	 * Gere le type d'affichage voulu
	 * @param choix le choix d'affichage (graphique ou terminal) 
	 */
    public void setView(String choix){
    	if (choix == "graphique"){
    		this.modeTerminal = false;
    	}
    	else{
    		this.modeTerminal = true;
    	}
    }
    	
	/**
	 * Lance une vue avec les parametres definient 
	 */
    public void confirm(){
    	Jeu game = new Jeu(this.player, this.adversaire);
    	if (this.modeTerminal == false){	
    		new NavalGUI(game);
    	}
    	else {
    		VueTerminal vueT = new VueTerminal(game);
    		ControleurTerminal controleJeu = new ControleurTerminal(game, vueT);
    		controleJeu.boucleJeu();	
    	}	
    }
}
