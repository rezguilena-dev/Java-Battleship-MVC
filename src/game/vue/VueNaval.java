package game.vue;
import game.ecouteur.*;
import game.modele.jeu.*;
import game.modele.terrain.*;
import game.modele.joueur.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;

public class VueNaval extends JPanel implements EcouteurModele{
    protected Jeu modele;
    protected int tailleCellule;
    protected int tailleMarge;
    private int spaceBetween;
    private boolean border;
    
	/**
	 * Constructeur VueNaval
	 * @param modele un jeu de bataille navale
	 * @param tailleCellule une taille de cellule pour l'affichage
	 * @param tailleMarge une taille de marge pour l'affichage
	 */
    public VueNaval(Jeu modele, int tailleCellule, int tailleMarge){
        this.modele = modele;
        this.modele.ajoutEcouteur(this);
        this.tailleCellule = tailleCellule;
        this.tailleMarge = tailleMarge;
        this.spaceBetween = modele.getJ1().getGrid().getWidth() * tailleCellule + 80;
        this.border = false;
        
        int largeur = 10 * this.tailleCellule + (10-1) + 2 * this.tailleMarge;
        int longueur = 10 * this.tailleCellule + (10-1) + 2 * this.tailleMarge;
        
        setPreferredSize(new Dimension(2 * longueur + this.spaceBetween / 2, largeur + this.spaceBetween / 3));
    }
    
	/**
	 * Accesseur de l'espace entre les deux grilles
	 * @return l'espace entre les deux grilles
	 */
    public int getSpaceBetween(){
    	return this.spaceBetween;
    }
    
	/**
	 * Accesseur de la taille cellule
	 * @return la taille de la cellule
	 */
    public int getTailleCellule(){
    	return this.tailleCellule;
    }
    
	/**
	 * Gere l'affichage graphique sur un panel du jeu
	 * @param g composante Graphics
	 */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        	for (int j = 0; j <= this.spaceBetween; j += this.spaceBetween){
			int y;
			int x;
			g2.setColor(Color.BLACK);
			//Dessin des lettre A - J
			for (int i = 0; i < this.modele.getJ1().getGrid().getHeight(); i++){
				y = (i * this.tailleCellule) + this.tailleCellule;
				g2.drawString(String.valueOf((char)(i + 65)), j + y + i + this.tailleCellule / 3, this.tailleCellule / 2);
			}
			
			//Dessin des chiffres 1 - 10
			for (int i = 0; i < this.modele.getJ1().getGrid().getHeight(); i++){
				x = (i * this.tailleCellule) + this.tailleCellule;
				g2.drawString(Integer.toString(i + 1), j + this.tailleCellule / 2, x + i + this.tailleCellule / 3);
			}
			
			g2.setColor(Color.BLACK);
			g2.drawRect(this.tailleCellule + j, this.tailleCellule, this.tailleCellule * this.modele.getJ1().getGrid().getWidth(), this.tailleCellule * this.modele.getJ1().getGrid().getHeight());
		}
        drawPlayerGrid(g2, this.modele.getJ1(), this.modele.getJ2().getGrid(), 0);
        drawPlayerGrid(g2, this.modele.getJ2(), this.modele.getJ1().getGrid(), this.spaceBetween);
        
    }
    
	/**
	 * Gere l'affichage graphique d'une grille joueur sur un panel
	 * @param g2 composante Graphics2D
	 * @param joueur le joueur
	 * @param grilleAdverse la grille de l'adversaire
	 * @param spaceBetween l'espace entre les deux grilles
	 */
    public void drawPlayerGrid(Graphics2D g2, Player joueur, Grid grilleAdverse, int spaceBetween){
    	int y;
        int x;
	
		//Dessin bateau --------------------------------------
		for (int i = 0; i < joueur.getFlotte().getListeBateau().size(); i++){
			Bateau currentBateau = joueur.getFlotte().getListeBateau().get(i);
				
			x = (currentBateau.getPosTete().getY() * this.tailleCellule) + this.tailleCellule + spaceBetween;
			y = (currentBateau.getPosTete().getX() * this.tailleCellule) + this.tailleCellule;
			g2.setColor(Color.BLACK);
				
			//la valeur true dans les if permet de tout le temps afficher les bateaux ennemis, pour les tests.
			if (currentBateau.getOrientation().equals("V")){
				if ((/*true ||*/ joueur.equals(this.modele.getJ2()) && currentBateau.estDetruit()) || joueur.equals(this.modele.getJ1())){
					g2.drawRoundRect(x, y, this.tailleCellule * currentBateau.getTaille(), this.tailleCellule, this.tailleCellule, this.tailleCellule);
				}
			}
			else {
				if ((/*true ||*/ joueur.equals(this.modele.getJ2()) && currentBateau.estDetruit()) || joueur.equals(this.modele.getJ1())){
					g2.drawRoundRect(x, y, this.tailleCellule, (this.tailleCellule) * currentBateau.getTaille(), this.tailleCellule, this.tailleCellule);
				}
			}
				
		}
	
		if (joueur.equals(this.modele.getJ1())){
			spaceBetween = this.spaceBetween;
		}
		else {
			spaceBetween = 0;
		}
	
		//Dessin des tirs
		
		for (int i = 0; i < joueur.getListePositionTir().size(); i++){
			Position currentPos = joueur.getListePositionTir().get(i);
			y = (currentPos.getX() * this.tailleCellule) + this.tailleCellule;
			x = (currentPos.getY() * this.tailleCellule) + this.tailleCellule + spaceBetween;
				
			if (grilleAdverse.getCell(currentPos.getX(),currentPos.getY()) instanceof PieceBateau){
				g2.setColor(Color.RED);
				g2.fillOval(x + this.tailleCellule / 4, y + this.tailleCellule / 4, this.tailleCellule / 2, this.tailleCellule / 2);
			}
			else {
				g2.setColor(Color.BLUE);
				g2.fillOval(x + this.tailleCellule / 4, y + this.tailleCellule / 4, this.tailleCellule / 2, this.tailleCellule / 2);
			}
		}
		
    }
    
	/**
	 * Change l'affichage sur le panel
	 */
    @Override
    public void modeleMisAJour(Object source){
        this.repaint();
    }
}
