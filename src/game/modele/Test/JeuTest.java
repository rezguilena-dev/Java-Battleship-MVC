package game.modele.Test;
import game.modele.joueur.*;
import game.modele.jeu.*;
import game.modele.terrain.*;
import java.util.*;

public class JeuTest {
    private static int successCount = 0;
    private static int testCount = 0;

     public static boolean test() {
        System.out.println("=== Tests Jeu ===");
        
        testInitialisation();
        testJoueurs();
        testCoupValide();
        testExecution();
        testFinPartie();
        
        return testCount==successCount;

    }

    private static void assertTest(boolean condition) {
        testCount++;
        if (condition) {
            successCount++;
        }
    }

    private static void testInitialisation() {
        int compteurlocal = successCount;
        Grid grille1 = new Grid(10, 10);
        Grid grille2 = new Grid(10, 10);
        Player j1 = new Amiral("X", new Scanner(System.in), grille1, 5);
        Player j2 = new IaFolle("O", grille2, 5);
        Jeu jeu = new Jeu( j1, j2);
        
        assertTest(jeu.getJ1() == j1);
        assertTest(jeu.getJ2() == j2);
        assertTest(jeu.getCurrentPlayer() == j1);
        assertTest(jeu.getJ1().getGrid() == grille1);
        assertTest(jeu.getJ2().getGrid() == grille2);

        if (compteurlocal + 5 == successCount) {
            System.out.println("\nTest Initialisation Validé");
        } else {
            System.out.println("\nTest Initialisation échoué");
        }
    }

    private static void testJoueurs() {
        int compteurlocal = successCount;
        Grid grille1 = new Grid(10, 10);
        Grid grille2 = new Grid(10, 10);
        Player j1 = new IaFolle("O", grille1, 5);
        Player j2 = new Terminator("X", grille2, 5, grille1);
        Jeu jeu = new Jeu( j1, j2);
        
        assertTest(jeu.getCurrentPlayer() == j1);
        jeu.execute();
        assertTest(jeu.getCurrentPlayer() == j2);
        jeu.execute();
        assertTest(jeu.getCurrentPlayer() == j1);

        if (compteurlocal + 3 == successCount) {
            System.out.println("\nTest Joueurs Validé");
        } else {
            System.out.println("\nTest Joueurs échoué");
        }
    }

    private static void testCoupValide() {
        int compteurlocal = successCount;
        Grid grille1 = new Grid(10, 10);
        Grid grille2 = new Grid(10, 10);
        Player j1 = new IaFolle("O", grille1, 5);
        Player j2 = new Terminator("X", grille2, 5, grille1);
        Jeu jeu = new Jeu( j1, j2);
        
        Position coupValide = new Position(2, 2, "C3");
        Position coupInvalide = new Position(-1, -1, "Z0");
        
        assertTest(jeu.getJ1().getGrid().estValide(coupValide));
        assertTest(!jeu.getJ1().getGrid().estValide(coupInvalide));

        if (compteurlocal + 2 == successCount) {
            System.out.println("\nTest Coup Valide Validé");
        } else {
            System.out.println("\nTest Coup Valide échoué");
        }
    }

    private static void testExecution() {
        int compteurlocal = successCount;
        Grid grille1 = new Grid(10, 10);
        Grid grille2 = new Grid(10, 10);
        Player j1 = new IaFolle("O", grille1, 5);
        Player j2 = new Terminator("X", grille2, 5, grille1);
        Jeu jeu = new Jeu(j1, j2);
        
        //List<Position> listeCoup = j2.getListeTirPossible();
        jeu.execute();
        jeu.execute();
        
        assertTest(j2.getListeTirPossible().contains(j2.getListePositionTir().get(0)));
        assertTest(jeu.getCurrentPlayer() == j1);

        if (compteurlocal + 2 == successCount) {
            System.out.println("\nTest Execution Validé");
        } else {
            System.out.println("\nTest Execution échoué");
        }
    }

    private static void testFinPartie() {
        int compteurlocal = successCount;
        Grid grille1 = new Grid(10, 10);
        Grid grille2 = new Grid(10, 10);
        Player j1 = new IaFolle("O", grille1, 5);
        Player j2 = new Terminator("X", grille2, 5, grille1);
        Jeu jeu = new Jeu(j1, j2);
        
        // Simuler destruction de la flotte de j1
        for (Bateau b : j1.getFlotte().getListeBateau()) {
            for (PieceBateau p : b.getCoque()) {
                p.setEtat(true);
            }
        }
        
        assertTest(jeu.isOver());
        assertTest(jeu.getWinner() == j2);

        if (compteurlocal + 2 == successCount) {
            System.out.println("\nTest Fin Partie Validé");
        } else {
            System.out.println("\nTest Fin Partie échoué");
        }
    }
}
