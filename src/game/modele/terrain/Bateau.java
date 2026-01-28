package game.modele.terrain;
import game.modele.joueur.*;
import java.util.*;

public class Bateau {
	private int taille;
	private String orientation;
	private boolean estMort;
	private List<PieceBateau> coque;
	private int nbVie;
	private Position positionTete;
	//SI L'ORIENTATION DU BATEAU EST H, on ne travaille que sur (lui,j), POUR V ON NE TRAVAILLE QUE SUR (i,lui)
	
	/**
	 * Constructeur pour un Bateau
	 * @param taille taille du bateau (en case)
	 * @param orientation "H" pour Horizontal ou "V" pour Vertical
	 */
	public Bateau (int taille, String orientation){
		this.taille = taille;
		this.orientation = orientation;
		this.estMort = false;
		this.nbVie = taille;
		this.coque = new ArrayList<>();
		this.positionTete = null;
	}
	
	/**
	 * Accesseur taille bateau
	 * @return la taille du bateau
	 */
	public int getTaille(){
		return this.taille;
	}
	
	/**
	 * Accesseur orientation bateau
	 * @return l'orientation du bateau ("H" ou "V")
	 */
	public String getOrientation(){
		return this.orientation;
	}
	
	/**
	 * Accesseur position tête
	 * @return la position de la tête du bateau de classe Position
	 */
	public Position getPosTete(){
		return this.positionTete;
	}
	
	public List<PieceBateau> getCoque(){
		return this.coque;
	}
	
	/**
	 * Setteur position tête
	 * @param coord coordonnée où se situe la tête du bateau
	 */
	public void setPosTete(Position coord){
		this.positionTete = coord;
	}
	
	/**
	 * Setteur liste de pieces du bateau (combien de pieces à le bateau = longeur de la liste)
	 * @param queue liste des pieces qui composent le bateau
	 */
	public void setCoque(List<PieceBateau> queue){
		this.coque = queue;
	}
	
	
	/**
	 * Vérifie si le bateau est detruit où non,
	 * si une seule piece du bateau est encore vivante; le bateau n'est pas mort,
	 * sinon, c'est que le bateau est mort
	 * @return l'état du bateau
	 * */
	public boolean estDetruit(){
		for (int i = 0; i < this.coque.size(); i++){
			if (this.coque.get(i).getEtat() == false){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Affichage du bateau (pour le terminale)
	 * @return le bateau au format String
	 */
	public String toString(){
        String ch = "";
		for (int i = 0; i < this.coque.size(); i++){
			ch += this.coque.get(i).getSymbole()+" ";
		}
		return ch;
    }
}
