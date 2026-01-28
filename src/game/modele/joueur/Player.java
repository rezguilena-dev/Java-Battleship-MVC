package game.modele.joueur;
import game.modele.jeu.*;
import game.modele.terrain.*;
import java.util.*;

public abstract class Player{
    private String symbole;
    private Flotte flotte;
    protected List<Position> listePositionTir;
	protected List<Position> listeTirPossible;
	protected Grid grille;

	/**
	 * Constructeur de joueur
	 * @param symbole symbole attribue au joueur (principalement pour la vue terminal)
	 * @param grille une grille sur laquelle sera positionnée la flotte du joueur
	 * @param tailleFlotte un entier qui decide du nombre de bateau dans la flotte du joueur 
	 */
    public Player(String symbole, Grid grille, int tailleFlotte){
        this.symbole = symbole;
        this.listePositionTir = new ArrayList<>();
        this.flotte = new Flotte(grille, this, tailleFlotte);
        this.listeTirPossible = grille.getListeCoupPossible();
        this.grille = grille;
    }
    
    public Grid getGrid(){
		return this.grille;
	}

    /**
	 * Accesseur symbole joueur
	 * @return le symbole attribué au joueur réel
	 */
	public String getSymbole(){
		return this.symbole;
	}
	
	/**
	 * Accesseur liste de position où on a déjà tirer
	 * @return liste de positions
	 */
	public List<Position> getListePositionTir(){
		return this.listePositionTir;
	}

	/**
	 * Accesseur liste de position où il est possible de tirer
	 * @return liste de position pour tirer
	 */
	public List<Position> getListeTirPossible(){
		return this.listeTirPossible;
	}
	
	/**
	 * Setteur liste de position où on a déjà tirer
	 * @param pos position à ajouter à la liste
	 */
	public void setListePositionTir(Position pos){
		this.listePositionTir.add(pos);
	}
	
	/**
	 * Setteur liste de positions où il est possible de tirer
	 * @param val nouvelle liste de position mise à jour
	 */
	public void setListeTirPossible(List<Position> val){
		this.listeTirPossible = val;
	}
	
	/**
	* Permet d'obtenir la flotte de bateaux.
	* @return la flotte de type Flotte.
	*/
	public Flotte getFlotte(){
	    return this.flotte;
	}

	public abstract Position action(Jeu jeu);
	public abstract String getActionText();
	public abstract String getVictoryText();
	public abstract String getDefeatText();
}
