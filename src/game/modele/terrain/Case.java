package game.modele.terrain;

public abstract class Case{
	private boolean estTouche;
	private String symbole;
	
	/**
	 * Constructeur Case
	 * @param symbole symbole attribué à la case
	 * @param bool booléen la case est une partie de bateu ou non
	 */
	public Case(String symbole){
		this.estTouche = false;	
		this.symbole = symbole;
	}
	
	/**
	 * Accesseur état touché ou non
	 * @return l'état de la case
	 */
	public boolean getEtat(){
		return this.estTouche;
	}
	
	/**
	 * Accesseur symbole de la case
	 * @return le symbole attribué à la case
	 */
	public String getSymbole(){
		return this.symbole;
	}
	
	/**
	 * Setteur symbole permet de changer ce qu'est la case (de CaseMer à PieceBateau par exemple)
	 * @param valeur le nouveau symbole de la case
	 */
	public void setSymbole(String valeur){
		this.symbole = valeur;
	}
	
	/**
	 * Setteur état de la Case (si touché ou non)
	 * @param valeur le nouvel état de la case
	 */
	public void setEtat(boolean valeur){
		this.estTouche = valeur;
	}
}
