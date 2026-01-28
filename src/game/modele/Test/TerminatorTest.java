package game.modele.Test;
import game.modele.joueur.*;
import game.modele.jeu.*;
import game.modele.terrain.*;
import java.util.*;

public class TerminatorTest {
    private static int successCount = 0;
    private static int testCount = 0;

    public static boolean test() {
        System.out.println("=== Tests Terminator ===");
        
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
        Player terminator = new Terminator("T", grille, 5, grille);
        
        assertTest("T".equals(terminator.getSymbole()));
        assertTest(terminator.getListePositionTir().isEmpty());
        assertTest(terminator.getFlotte() != null);
        assertTest("TERMINATOR".equals(terminator.toString()));

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
        Player terminator = new Terminator("T", grille, 5, grille);
        Position pos = new Position(3, 4, "D5");
        
        terminator.setListePositionTir(pos);
        assertTest(terminator.getListePositionTir().size() == 1);
        assertTest(terminator.getListePositionTir().get(0).equals(pos));

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
        Player iaAlea = new IaFolle("A", grille1, 5);
        Player terminator = new Terminator("T", grille2, 5, grille1);
        Jeu jeu = new Jeu(iaAlea, terminator);
        
        // Préparer la liste des tirs possibles (positions ennemies)
        List<Position> tirsPossibles = new ArrayList<>();
        tirsPossibles.add(new Position(0, 0, "A1"));
        tirsPossibles.add(new Position(1, 1, "B2"));
        iaAlea.setListeTirPossible(tirsPossibles);
        
        Position coup = iaAlea.action(jeu);
        assertTest(coup != null);
        assertTest(iaAlea.getListePositionTir().size() == 1);
        assertTest(iaAlea.getListeTirPossible().size() == 1);

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
        Player terminator = new Terminator("T", grille, 5, grille);
        Position pos = new Position(2, 3, "C4");
        terminator.setListePositionTir(pos);
        
        assertTest(terminator.getActionText().contains("C4"));
        assertTest(terminator.getVictoryText().contains("DESTRUCTION"));
        assertTest(terminator.getDefeatText().contains("nullos"));

        if (compteurlocal + 3 == successCount) {
            System.out.println("\nTest Textes Validé");
        } else {
            System.out.println("\nTest Textes échoué");
        }
    }
}
