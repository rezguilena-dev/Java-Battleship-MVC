package game.modele.joueur;
import game.modele.jeu.*;
import game.modele.terrain.*;
import java.util.*;

public class IaPseudoIntelligente extends Player{
	public Random rand;
	private List<Position> listePositionVoisin;
	
	public IaPseudoIntelligente(String symbole, Grid grille, int tailleFlotte){
		super(symbole, grille,tailleFlotte);
		this.rand = new Random();
		this.listePositionVoisin = new ArrayList<>();
	}
	
	public Position tirAleatoireOptimiser(){
		int i = rand.nextInt(this.listeTirPossible.size());
		while ((this.listeTirPossible.get(i).getX() + this.listeTirPossible.get(i).getY()) % 2 != 0 || this.listeTirPossible.size() == 0){
			i = rand.nextInt(this.listeTirPossible.size());
		}
		return this.listeTirPossible.get(i);
	}
	
	public boolean analyseTir(Position pos, Jeu jeu){
		if (jeu.getJ1().getGrid().getCell(pos.getX(), pos.getY()) instanceof PieceBateau){
			return true;
		}
		return false;
	}
	
	public void tirVoisin(Position pos, Jeu jeu){
			int[] dx = {-1, 1, 0, 0};
        		int[] dy = {0, 0, -1, 1};
        		
        		for (int i = 0; i < 4; i++) {
        			int x = pos.getX() + dx[i];
        			int y = pos.getY() + dy[i];
        			Position posVoisin = new Position(x, y, this.getGrid().getSymbolFromCoord(x, y));
        			if (this.listeTirPossible.contains(posVoisin)) this.listePositionVoisin.add(posVoisin);
			}
	}
	
	
	//Renvoie une Position qui correspond à un coup choisie aléatoirement par l'Ia Folle, selon la liste de coup possible.
	@Override
	public Position action(Jeu jeu){
		Position coupChoisi;
		if (this.listePositionTir.size() != 0){
			Position dernierTir = listePositionTir.get(listePositionTir.size() - 1);
			//Si le dernier tir a toucher un bateau, actualise la liste des cibles voisine.
			if (analyseTir(dernierTir, jeu) ){
				tirVoisin(dernierTir, jeu);
			}
			if (this.listePositionVoisin.size() != 0){
				coupChoisi = this.listePositionVoisin.get(this.listePositionVoisin.size() - 1);
				this.listePositionVoisin.remove(coupChoisi);
					
			}
			else{
				coupChoisi = tirAleatoireOptimiser();
			}
			
		}
		else {
			coupChoisi = tirAleatoireOptimiser();
		}
		this.getGrid().getPositionFromSymbol(this.listeTirPossible.get(rand.nextInt(this.listeTirPossible.size())).toString());
	    	this.setListePositionTir(coupChoisi);
	    	this.listeTirPossible.remove(coupChoisi);
		return coupChoisi;
	}
	
	@Override
	public String getActionText(){
		return "L'Ia Pseudo intelligente a choisie: "+this.listePositionTir.get(this.listePositionTir.size() - 1).toString();
	}
	
	@Override
	public String getVictoryText(){
		return "L'Ia Pseudo intelligente a detruit toute la flotte... Une bataille perdue n'est pas une guerre perdue ! ";
	}
	
	@Override
	public String getDefeatText(){
		return "L'Ia Pseudo intelligente a ete detruite ! Il serait temps de reguler ces machines...";
	}
	
	public String toString(){
		return "Ia Pseudo intelligente";
	}
}
