package game.vue;
import game.ecouteur.*;
import game.modele.jeu.*;
import game.modele.terrain.*;
import game.modele.joueur.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;

public class VueTerminal implements EcouteurModele{
    protected Jeu modele;
    private String textCoupJ1;
    private String textCoupJ2;
    
    /**
     * Constructeur VueTerminal
     * @param modele une jeu de bataille navale
     */
    public VueTerminal(Jeu modele){
        this.modele = modele;
        this.modele.ajoutEcouteur(this);
        this.textCoupJ1 = "";
        this.textCoupJ2 = "";
        System.out.println(this);
    }
    
    /**
     * Construit la manche de jeu en format string affichable dans le terminal
     * @return la situation format string
     */
    @Override
    public String toString(){
    	StringBuilder ch = new StringBuilder();
	    ch.append(letterLine(this.modele.getJ1().getGrid().getWidth()) + "        " +letterLine(this.modele.getJ1().getGrid().getWidth())+ "\n");
	
	    for (int i = 0; i < this.modele.getJ1().getGrid().getWidth(); i++) {
         	ch.append(Integer.toString(i + 1)+ " " + playerGridLineToString(i, this.modele.getJ1().getGrid()) + "     " + Integer.toString(i + 1)+ " " +playerGridLineToString(i, this.modele.getJ2().getGrid())+ "\n");
    	}
    	ch.append(this.textCoupJ1 + "\n" + this.textCoupJ2 + "\n");
	    return ch.toString();
    }
    
    /**
     * Construit la ligen de la grille correspondante
     * @param indexLine la ligne a tranformer en string
     * @param grille la grille 
     * @retur la ligne de grille format string
     */
    public String playerGridLineToString(int indexLine, Grid grille){
    	StringBuilder ch = new StringBuilder();
        for (int i = 0; i < grille.getWidth(); i++) {
        	String symbole = grille.getCell(indexLine, i).getSymbole();
        	if (this.modele.getJ2().getGrid().equals(grille) && symbole == this.modele.getJ2().getSymbole()){
        		ch.append("~");
        	}
        	else {
        		ch.append(symbole);
        	}
            ch.append(" ");
        }
        return ch.toString();
    }
    
    /**
     * Construit la ligne de lettres sur le bord de la grille
     * @param gridWidth largeur de la grille
     */
    public String letterLine(int gridWidth){
    	StringBuilder ch = new StringBuilder();
        for (int i = 0; i < gridWidth; i++) {
            ch.append(" "+String.valueOf((char)(i + 65)));
        }
        return ch.toString();
    }
    
    /**
     * Change l'affichage des coups du joueur reel
     */
    public void setTextCoupJ1(String val){
    	this.textCoupJ1 = val;
    }
    
    /**
     * Change l'affichage des coups du joueur ia
     */
    public void setTextCoupJ2(String val){
    	this.textCoupJ2 = val;
    }
    
    /**
     * Indique que le modele a ete mis a jour
     * @param source ce qui a ete mis a jour 
     */
    @Override
    public void modeleMisAJour(Object source){
        System.out.println(this.toString());
    }
}
