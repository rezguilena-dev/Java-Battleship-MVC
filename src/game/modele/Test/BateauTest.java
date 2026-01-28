package game.modele.Test;
import game.modele.joueur.*;
import game.modele.jeu.*;
import game.modele.terrain.*;
import java.util.*;

public class BateauTest {
    private static int successCount = 0;
    private static int testCount = 0;

    public static boolean test() {
        System.out.println("=== Tests Bateau ===");
        
        testConstructeur();
        testPositionTete();
        testCoqueEtDestruction();
        testToString();
        
        return testCount==successCount;

    }

    private static void assertTest(boolean condition) {
        testCount++;
        if (condition) {
            successCount++;
        }
    }

    private static void testConstructeur() {
        Bateau bateau = new Bateau(3, "H");
        int compteurlocal=successCount;
        assertTest(bateau.getTaille() == 3);
        assertTest("H".equals(bateau.getOrientation()));
        assertTest(bateau.estDetruit()); // Car coque vide
        if (compteurlocal+3==successCount){
            System.out.println("\nTest Constructeur Validé");
        }
        else {
            System.out.println("\nTest Constructeur échoué");
        };
    }

    private static void testPositionTete() {
        Bateau bateau = new Bateau(2, "V");
        Position pos = new Position(1, 1, "X");
        int compteurlocal=successCount;

        bateau.setPosTete(pos);
        assertTest(pos.equals(bateau.getPosTete()));
        if (compteurlocal+1==successCount){
            System.out.println("\nTest Position Tete Validé");
        }
        else {
            System.out.println("\nTest Position Tete échoué ");
        };
    }

    private static void testCoqueEtDestruction() {
        Bateau bateau = new Bateau(2, "H");
        int compteurlocal=successCount;

        // Création pièces
        List<PieceBateau> pieces = new ArrayList<>();
        pieces.add(new PieceBateau("X"));
        pieces.add(new PieceBateau("X"));
        bateau.setCoque(pieces);
        
        // Tests
        assertTest(!bateau.estDetruit()); // Toutes pièces intactes
        
        pieces.get(0).setEtat(true); // Détruit une pièce
        assertTest(!bateau.estDetruit()); // Une pièce encore vivante
        
        pieces.get(1).setEtat(true); // Détruit dernière pièce
        assertTest(bateau.estDetruit()); // Toutes pièces détruites
        if (compteurlocal+3==successCount){
            System.out.println("\nTest Coque et Destruction Validé");
        }
        else {
            System.out.println("\nTest Coque et Destruction échoué ");
        };
    }

    private static void testToString() {
        Bateau bateau = new Bateau(2, "H");
        int compteurlocal=successCount;

        List<PieceBateau> pieces = new ArrayList<>();
        pieces.add(new PieceBateau("X"));
        pieces.add(new PieceBateau("!"));
        bateau.setCoque(pieces);
        
        String result = bateau.toString();
        assertTest(result.contains("X") && result.contains("!"));
        if (compteurlocal+1==successCount){
            System.out.println("\nTest toString Validé");
        }
        else {
            System.out.println("\nTest toString échoué ");
        };
        
    }
}
