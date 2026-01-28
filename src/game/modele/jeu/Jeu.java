package game.modele.jeu;
import game.modele.joueur.*;
import game.modele.terrain.*;
import game.ecouteur.*;
import java.util.*;

public class Jeu extends AbstractModeleEcoutable{
	private Player j1;
    private Player j2;
    private Player currentPlayer;
    /*protected Grid grilleJ1;
    protected Grid grilleJ2;*/

	private Position positionClique;//pas ouf
        
    public Jeu(/*Grid grilleJ1, Grid grilleJ2,*/ Player j1, Player j2){
		/*this.grilleJ1 = grilleJ1;
		this.grilleJ2 = grilleJ2;*/
		this.j1 = j1;
		this.j2 = j2;
		this.currentPlayer = j1;
    }

	/**
	* Permet d'obtenir le joueur actuelle.
	* @return le joueur actuelle de type player
	*/
	public Player getCurrentPlayer(){
	    return this.currentPlayer;
	}
	
	/**
	* Permet d'obtenir le joueur 1.
	* @return le joueur 1 de type Player
	*/
	public Player getJ1(){
	    return this.j1;
	}
	
	/**
	* Permet d'obtenir le joueur 2.
	* @return le joueur 2 de type Player
	*/
	public Player getJ2(){
	    return this.j2;
	}
	
	/**
	* Permet d'obtenir la grille de jeu.
	* @return la grille de type Grid
	*/
	/*public Grid getGrid1(){
	    return this.grilleJ1;
	}
	
	public Grid getGrid2(){
	    return this.grilleJ2;
	}*/
	
	/**
	* Permet d'obtenir la grille de jeu.
	* @return la position du clique 
	*/
	public Position getPositionClique(){
		return positionClique;
	}

	/**
	* Permet de definir la position du clique
	*/
	public void setPositionClique(Position val){
		this.positionClique = val;
	}
	
	/**
	* Permet d'éxecuter un tour de jeu. Elle utilise d'abord la méthode CoupTire() avec l'action
	* du joueur actuelle, puis change le joueur actuelle.
	*/
    public void execute(){
    	//Il faudrait recup la grille ennemis
    	//coupTire(this.currentPlayer.action(this), this.currentPlayer.getGrid());
	    
		if (this.currentPlayer == this.j1){
			this.j2.getGrid().coupTire(this.currentPlayer.action(this));
			this.currentPlayer = this.j2;
		}
		else {
			this.j1.getGrid().coupTire(this.currentPlayer.action(this));
			this.currentPlayer = this.j1;
		}
		grilleChangement();
	}

	
	/**
	* Permet de spécifier si la partie est finie
	* @return true si il y a un gagnant, faux sinon
	*/
	public boolean isOver(){
		if (this.j1.getFlotte().etatFlotte() || this.j2.getFlotte().etatFlotte()){
			if (getWinner() == this.j1){
	    		return true;
			}
			else {
	    		return true;
			}
		}
		return false;
	}
	
	/**
	* Permet de récuperer le joueur gagnant, selon l'état de la flotte de chacun.
	* @return le joueur 2 si la flotte du joueur 1 vaut false, ou le joueur 1 si la flotte du joueur 2 vaut false. Sinon, renvoie null
	*/
	public Player getWinner(){
		if (this.j1.getFlotte().etatFlotte()){
			return this.j2;
		}
		else if (this.j2.getFlotte().etatFlotte()){
			return this.j1;
		}
		return null;
	}
	
	
}
