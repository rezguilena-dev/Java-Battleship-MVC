package game.modele.terrain;
import java.util.*;

public class Position{
	private int x;
	private int y;
	private String symbol;
	
	public Position(int x, int y, String symbol){
		this.x = x;
		this.y = y;
		this.symbol = symbol;
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	public String toString(){
		return ""+this.symbol;
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (this == obj) return true;
	    if (obj == null || getClass() != obj.getClass()) return false;
	    Position position = (Position) obj;
	    boolean isEqual = x == position.x && y == position.y;
    		//System.out.println("Comparing: (" + x + "," + y + ") with (" + position.x + "," + position.y + ") -> " + isEqual);
    	return isEqual;
	}

	@Override
	public int hashCode() {
	    int hash = Objects.hash(x, y);
    	//System.out.println("Hash for (" + x + "," + y + ") = " + hash);
    	return hash;
	}
}
