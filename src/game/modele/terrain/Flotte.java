package game.modele.terrain;
import game.modele.jeu.*;
import game.modele.joueur.*;
import java.util.*;

public class Flotte {
	private Grid grille;
	private List<Bateau> listeBateau;
	private Player proprio;
	private Random rand;
	private final static String[] ORIENTATION = {"H","V"};
	private final static int[] tailleBateau = {2, 3, 3, 4, 5, 3, 2};
	
	/**
	 * Constructeur de Flotte d'un joueur
	 * @param grille grille sur laquelle est positionnée la flotte
	 * @param proprio propriétaire de la flotte
	 */
	public Flotte (Grid grille, Player proprio, int tailleFlotte){
		this.grille = grille;
		this.rand = new Random();
		this.proprio = proprio;
		this.listeBateau = new ArrayList<>();
		remplisListeBateau(tailleFlotte);
	}
	
	/**
	 * Accesseur liste de bateau 
	 * @return la liste des bateaux du joueur
	 */
	public List<Bateau> getListeBateau(){
		return this.listeBateau;
	}
	
	/**
	 * Accesseur propriétaire de la flotte
	 * @return le propriétaire de la flotte
	 */
	public Player getProprio(){
		return this.proprio;
	}
	
	/**
	 * Remplis la listeBateau de bateau, avec la méthode creeBateau
	 * @param listeBateau une liste de bateau à remplir
	 */
	public void remplisListeBateau(int tailleFlotte){
		//Peut etre faire avec une boucle si on utilise une fonction qui permet de crée manuellement les bateaux.
		for (int i = 0; i < tailleFlotte; i++){
			this.listeBateau.add(creeBateau(tailleBateau[i]));
		}
	}
	
	/**
	 * Crée un bateau, selon la taille choisie
	 * @param taille la taille du bateau à créer
	 * @return le bateau créer de classe Bateau
	 */
	public Bateau creeBateau(int taille){
		Bateau b = new Bateau(taille, ORIENTATION[this.rand.nextInt(2)]);
		int x = this.rand.nextInt(this.grille.getWidth());
		int y = this.rand.nextInt(this.grille.getHeight());
		Position positionTete = new Position(x, y, this.grille.getSymbolFromCoord(x,y));

		while (positionPossible(positionTete, b) == false){
			x = this.rand.nextInt(this.grille.getWidth());
			y = this.rand.nextInt(this.grille.getHeight());
			positionTete = new Position(x, y, this.grille.getSymbolFromCoord(x,y));
		}
		
		b.setPosTete(positionTete);
		b.setCoque(creeQueue(b));
		return b;
	}
	
	/**
	 * Vérifie si la future position du bateau est possible
	 * @param positionTete position de la tête du bateau
	 * @param bat le bateau à positionner sur la grille
	 * @return si la position est valide ou non
	 */
	public boolean positionPossible (Position positionTete, Bateau bat){
		boolean estEnIntersections = false;
		for (int i = 0; i < bat.getTaille(); i++){
			Case cell;
			if (bat.getOrientation() == "H"){
				cell = this.grille.getCell(positionTete.getX() + i, positionTete.getY());
			}
			else {
				cell = this.grille.getCell(positionTete.getX(), positionTete.getY() + i);
			}
			if (cell != null && cell instanceof PieceBateau){
				estEnIntersections = true;
				break;
			}
		}

		if (bat.getOrientation() == "H"){
			return ( positionTete.getX() + bat.getTaille()) < this.grille.getWidth() && !estEnIntersections;
		}
		else {
			return (positionTete.getY() + bat.getTaille()) < this.grille.getHeight() && !estEnIntersections;
		}
	}
	
	/**
	 * Crée la queue du bateau, a partir de la position de la tete du bateau, son orientation et sa taille.
	 * @param bat un bateau à positionner sur la grille
	 * @return la liste des pièces du bateau maintenant placé dans la grille
	 */
	public List<PieceBateau> creeQueue(Bateau bat){
		List<PieceBateau> queue = new ArrayList<>();
		for (int i = 0; i < bat.getTaille(); i++){
			PieceBateau piece = new PieceBateau(this.proprio.getSymbole());
			if (bat.getOrientation() == "H"){
				this.grille.setCell(bat.getPosTete().getX() + i, bat.getPosTete().getY(), piece);
			}
			else {
				this.grille.setCell(bat.getPosTete().getX(), bat.getPosTete().getY() + i, piece);
			}
			queue.add(piece); 
		}
		
		return queue;
	}
	
	/**
	 * Vérifie l'état de la flotte,
	 * si un seule bateau est vivant, 
	 * alors la flotte est encore vivante
	 * @return s'il reste des bateaux dans la flotte du joueur
	 */
	public boolean etatFlotte(){
		for (int i = 0; i < this.listeBateau.size(); i++){
			if (this.listeBateau.get(i).estDetruit() == false){
				return false;
			}
		}
		return true;
	}
        
	/**
	 * Affichage de la flotte (pour le terminale)
	 * @return la flotte au format String
	 */
    public String toString(){
    	String ch = "";
		for (int i = 0; i < this.listeBateau.size(); i++){
			ch += this.listeBateau.get(i).toString();
			ch += "\n";
		}
		return ch;
    }
        
}
