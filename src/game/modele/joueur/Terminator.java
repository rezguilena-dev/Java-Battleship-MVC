package game.modele.joueur;
import game.modele.jeu.*;
import game.modele.terrain.*;
import java.util.*;

public class Terminator extends Player {
	private Random rand;
	private List<Position> listeEnnemie;
	
	public Terminator(String symbole, Grid grille1, int tailleFlotte, Grid grille2){
		super(symbole, grille1, tailleFlotte);
		this.rand = new Random();
		
		this.listeEnnemie = calculerTirPossible(grille2);
		
	}
	
	public List<Position> calculerTirPossible(Grid grille){
		List<Position> res = new ArrayList<>();
		for (int i = 0; i < grille.getWidth(); i++){
			for (int j = 0; j < grille.getHeight(); j++){
				if (grille.getCell(i, j) instanceof PieceBateau){
					res.add(new Position(i,j,grille.getSymbolFromCoord(i,j)));
				}
			}
			/*Position currentPos = listeCoup.get(i);
			if (grille.getCell(currentPos.getX(), currentPos.getY()) instanceof PieceBateau && grille.getCell(currentPos.getX(), currentPos.getY()).getSymbole() != this.getSymbole()){
				res.add(currentPos);
			}*/
		}
		return res;
	}

	//Renvoie une Position qui correspond à un coup choisie par le TERMINATOR. Parmis la liste de Position listeEnnemi qui contient chaque Position de chaque piece de bateau adverse.
	@Override
	public Position action(Jeu jeu){
		int coup = rand.nextInt(this.listeEnnemie.size());
		Position coupChoisi = this.getGrid().getPositionFromSymbol(this.listeEnnemie.get(coup).toString());
		this.listeEnnemie.remove(coup);
	    	this.setListePositionTir(coupChoisi);
		return coupChoisi;
	}
	
	@Override
	public String getActionText(){
		return "Le TERMINATOR a choisi la position "+this.getListePositionTir().get(this.getListePositionTir().size() - 1).toString();
	}
	
	@Override
	public String getVictoryText(){
		return "Le TERMINATOR a gagné ! DE-DE-DE DESTRUCTION !!";
	}
	
	@Override
	public String getDefeatText(){
		return "Le TERMINATOR a perdu ! Booouh nullos";
	}
	
	public String toString(){
    	return "TERMINATOR";
    }
}
