package game.modele.Test;
import game.modele.joueur.*;
import game.modele.jeu.*;
import game.modele.terrain.*;
import java.util.*;

public class FlotteTest {
    private static int successCount = 0;
    private static int testCount = 0;

     public static boolean test() {
        System.out.println("=== Tests Flotte ===");
        
        testConstructeurEtGetters();
        testRemplisListeBateau();
        testCreeBateau();
        testEtatFlotte();
        
        return testCount==successCount;

    }

    private static void assertTest(boolean condition) {
        testCount++;
        if (condition) {
            successCount++;
        }
        else {
        	System.out.println("Ici");
        }
    }
    private static void testConstructeurEtGetters() {
        Grid grille = new Grid(10,10);
        Player proprio = new Amiral("X", new Scanner(System.in), grille, 5);
        //Flotte flotte = new Flotte(grille, proprio);
        int compteurlocal=successCount;
        assertTest(proprio.getFlotte().getListeBateau() != null);
        assertTest(proprio.getFlotte().getProprio() == proprio);
        assertTest(proprio.getFlotte().getListeBateau().size() == 5);
      
        if (compteurlocal+3 == successCount){
            System.out.println("\nTest Constructeur Validé");
        }
        else {
            System.out.println("\nTest Constructeur échoué");
        };
    }

    private static void testRemplisListeBateau() {
        Grid grille = new Grid(10,10);
        Player proprio = new Amiral("X", new Scanner(System.in), grille, 5);
        //Flotte flotte = new Flotte(grille, proprio, 5);
        List<Bateau> liste = proprio.getFlotte().getListeBateau();
        
        // Vérifie les tailles des bateaux créés
        int count5 = 0, count4 = 0, count3 = 0, count2 = 0;
        for (Bateau b : liste) {
            if (b.getTaille() == 5) count5++;
            if (b.getTaille() == 4) count4++;
            if (b.getTaille() == 3) count3++;
            if (b.getTaille() == 2) count2++;
            
        }
        int compteurlocal=successCount;
        assertTest(count5 == 1);
        assertTest(count4 == 1); // 3 destroyers + 1 sous-marin
        assertTest(count3 == 2);
        assertTest(count2 == 1);
        if (compteurlocal+4 == successCount){
            System.out.println("\nTest RemplisListeBateau Validé");
        }
        else {
            System.out.println("\nTest RemplisListeBateau échoué");
        };
    }

    private static void testCreeBateau() {
        Grid grille = new Grid(10,10);
        Player proprio = new Amiral("X", new Scanner(System.in), grille, 5);
        //Flotte flotte = new Flotte(grille, proprio, 5);
        
        Bateau bateau = proprio.getFlotte().creeBateau(3);
        int compteurlocal=successCount;
        assertTest(bateau != null);
        assertTest(bateau.getTaille() == 3);
        assertTest(bateau.getPosTete() != null);
        assertTest(bateau.getOrientation().equals("H") || bateau.getOrientation().equals("V"));
        if (compteurlocal+4==successCount){
            System.out.println("\nTest CreeBateau Validé");
        }
        else {
            System.out.println("\nTest CreeBateau échoué");
        };
    }

    private static void testEtatFlotte() {
        Grid grille = new Grid(10,10);
        Player proprio = new Amiral("X", new Scanner(System.in), grille, 5);
        //Flotte flotte = new Flotte(grille, proprio, 5);
        int compteurlocal=successCount;
        // Tous les bateaux sont intacts initialement
        assertTest(!proprio.getFlotte().etatFlotte());
        
        // Détruire tous les bateaux
        for (Bateau b : proprio.getFlotte().getListeBateau()) {
            for (PieceBateau p : b.getCoque()) {
                p.setEtat(true);
            }
        }
        assertTest(proprio.getFlotte().etatFlotte());
        
        if (compteurlocal+2==successCount){
            System.out.println("\nTest EtatFlotte Validé");
        }
        else {
            System.out.println("\nTest EtatFlotte échoué");
        };
    }
}
