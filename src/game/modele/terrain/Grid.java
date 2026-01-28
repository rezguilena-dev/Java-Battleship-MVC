package game.modele.terrain;
import java.util.*;

public class Grid{
	private int height;
	private int width;
	private Case[][] grille;
	
	/**
	 * Constructeur Grid, rempli la grille initiale de CaseMer
	 * @param width la largeur de la grille
	 * @param height la longueur de la grille
	 */
	public Grid(int width, int height){
		this.height = height;
		this.width = width;
		this.grille = new Case[width][height];
		
		for (int i = 0; i < this.width; i++) {
			for (int j = 0; j < this.height; j++){
				this.grille[i][j] = new CaseMer();
			}
        }
	}
	
	/**
	 * Accesseur largeur de la grille
	 * @return largeur grille
	 */
	public int getWidth(){
		return this.width;
	}
	
	/**
	 * Accesseur longueur grille
	 * @return longueur grille
	 */
	public int getHeight(){
		return this.height;
	}
	
	/**
	 * Accesseur grille
	 * @return grille
	 */
	public Case[][] getGrid(){
		return this.grille;
	}
	
	/**
	 * Renvoie une instance Position à partir d'un symbole (par exemple, A1 correspond à x = 0, y = 0)
	 * @param symbol un symbole associé à une case 
	 * @return la position associé au symbole dans la grille
	 */
	public Position getPositionFromSymbol(String symbol){
		if (symbol.length() < 2){
			return null;
		}
		int y = symbol.charAt(0) - 'A';
		int x = Integer.parseInt(symbol.substring(1)) - 1;
		return new Position(x,y,symbol);
	}
	
	/**
	 * Renvoie un symbole à partir d'une Position
	 * @param x ligne de la grille
	 * @param y colonne de la grille
	 * @return le symbole (par exemple, A1 correspond à x = 0, y = 0) associé à la cellule correspondant à grille[x][y]
	 */
	public String getSymbolFromCoord(int x, int y){
		return (char) ('A' + y) + String.valueOf(x + 1);
	}
	
	/**
	 * Setteur de cellule de grille
	 * @param i ligne de la grille
	 * @param j colonne de la grille
	 * @param cell Case à associé à grille[i][j]
	 */
	public void setCell(int i, int j, Case cell){
		this.grille[i][j] = cell;
	}
	
	/**
	 * Accesseur à une cellule de la grille
	 * @param i ligne de la grille
	 * @param j colonne de la grille
	 * @return Case associée à grille[i][j]
	 */
	public Case getCell (int i, int j){
		if (i < 0 || i >= this.width || j < 0 || j >= this.height) {
			return null;
		}
		return this.grille[i][j];
	}
	
	public List<Position> getListeCoupPossible(){
		List<Position> res = new ArrayList<>();
		for (int i = 0; i < this.width; i++){
			for(int j = 0; j < this.height; j++){
				res.add(new Position(i, j, getSymbolFromCoord(i,j)));
			}
		}
		return res;
	}
	
	public boolean estValide(Position pos){
		if (pos == null){
			return false;
		}
		return pos.getX() >= 0 && pos.getX() < this.height && pos.getY() >= 0 && pos.getY() < this.width && !this.grille[pos.getX()][pos.getY()].getEtat();
	}
	
	public void coupTire(Position coup){
		
		this.grille[coup.getX()][coup.getY()].setEtat(true);
		
		if (this.grille[coup.getX()][coup.getY()] instanceof PieceBateau == false){
			this.grille[coup.getX()][coup.getY()].setSymbole("*");
		}
		else {
			this.grille[coup.getX()][coup.getY()].setSymbole("!");
		}
	}
	
}
