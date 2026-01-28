package game.modele.Test;
import game.modele.joueur.*;
import game.modele.jeu.*;
import game.modele.terrain.*;
import java.util.*;

public class IaFolleTest {
    private static int successCount = 0;
    private static int testCount = 0;

     public static boolean test(){
        System.out.println("=== Tests IaFolle ===");
        
        testConstructeurEtGetters();
        testPositionTir();
        testAction();
        testTextes();
        
        return testCount==successCount;

    }

    private static void assertTest(boolean condition) {
        testCount++;
        if (condition) {
            successCount++;
        }
    }

    private static void testConstructeurEtGetters() {
        int compteurlocal = successCount;
        Random rand = new Random();
        Grid grille = new Grid(10, 10);
        IaFolle ia = new IaFolle("O", grille, 5);
        
        assertTest("O".equals(ia.getSymbole()));
        assertTest(ia.getListePositionTir().isEmpty());
        assertTest(ia.getFlotte() != null);
        assertTest("Ia complétement FOLLE et TARÉE".equals(ia.toString()));

        if (compteurlocal + 4 == successCount) {
            System.out.println("\nTest Constructeur et Getters Validé");
        } else {
            System.out.println("\nTest Constructeur et Getters échoué");
        }
    }

    private static void testPositionTir() {
        int compteurlocal = successCount;
        Random rand = new Random();
        Grid grille = new Grid(10, 10);
        IaFolle ia = new IaFolle("O", grille, 5);
        Position pos = new Position(3, 4, "D5");
        
        ia.setListePositionTir(pos);
        assertTest(ia.getListePositionTir().size() == 1);
        assertTest(ia.getListePositionTir().get(0).equals(pos));

        if (compteurlocal + 2 == successCount) {
            System.out.println("\nTest Position Tir Validé");
        } else {
            System.out.println("\nTest Position Tir échoué");
        }
    }

    private static void testAction() {
        int compteurlocal = successCount;
        Random rand = new Random();
        Grid grille1 = new Grid(10, 10);
        Grid grille2 = new Grid(10, 10);
        IaFolle ia1 = new IaFolle("O", grille1, 5);
        IaFolle ia2 = new IaFolle("O", grille2, 5);
        Jeu jeu = new Jeu( ia1, ia2);
        
        
        // Préparer la liste des tirs possibles
        List<Position> tirsPossibles = new ArrayList<>();
        tirsPossibles.add(new Position(0, 0, "A1"));
        tirsPossibles.add(new Position(1, 1, "B2"));
        ia1.setListeTirPossible(tirsPossibles);
        
        Position coup = ia1.action(jeu);
        assertTest(coup != null);
        assertTest(ia1.getListePositionTir().size() == 1);
        assertTest(ia1.getListeTirPossible().size() == 1);

        if (compteurlocal + 3 == successCount) {
            System.out.println("\nTest Action Validé");
        } else {
            System.out.println("\nTest Action échoué");
        }
    }

    private static void testTextes() {
        int compteurlocal = successCount;
        Random rand = new Random();
        Grid grille = new Grid(10, 10);
        IaFolle ia = new IaFolle("O", grille, 5);
        Position pos = new Position(2, 3, "C4");
        ia.setListePositionTir(pos);
        
        assertTest(ia.getActionText().contains("C4"));
        assertTest(ia.getVictoryText().contains("avenir du monde"));
        assertTest(ia.getDefeatText().contains("detruite"));

        if (compteurlocal + 3 == successCount) {
            System.out.println("\nTest Textes Validé");
        } else {
            System.out.println("\nTest Textes échoué");
        }
    }
}
