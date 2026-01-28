package game.modele.Test;
import game.modele.joueur.*;
import game.modele.jeu.*;
import game.modele.terrain.*;

public class CaseTest {
    private static int successCount = 0;
    private static int testCount = 0;

    private static class CaseTeste extends Case {
        public CaseTeste(String symbole) {
            super(symbole);
        }
    }

     public static boolean test() {
        System.out.println("=== Tests Case ===");
        
        testConstructeur();
        testGetters();
        testSetters();
        
        return testCount==successCount;

    }

    private static void assertTest(boolean condition) {
        testCount++;
        if (condition) {
            successCount++;
        }
    }

    private static void testConstructeur() {
        int compteurlocal = successCount;
        Case caseMer = new CaseTeste("~");
        Case caseBateau = new CaseTeste("B");
        
        assertTest(!caseMer.getEtat());
        assertTest("~".equals(caseMer.getSymbole()));
        
        assertTest(!caseBateau.getEtat());
        assertTest("B".equals(caseBateau.getSymbole()));

        if (compteurlocal + 4 == successCount) {
            System.out.println("\nTest Constructeur Validé");
        } else {
            System.out.println("\nTest Constructeur échoué");
        }
    }

    private static void testGetters() {
        int compteurlocal = successCount;
        Case caseTeste = new CaseTeste("X");
        
        assertTest(!caseTeste.getEtat());
        assertTest("X".equals(caseTeste.getSymbole()));

        if (compteurlocal + 2 == successCount) {
            System.out.println("\nTest Getters Validé");
        } else {
            System.out.println("\nTest Getters échoué");
        }
    }

    private static void testSetters() {
        int compteurlocal = successCount;
        Case caseTeste = new CaseTeste("X");
        
        caseTeste.setSymbole("Y");
        assertTest("Y".equals(caseTeste.getSymbole()));
        
        caseTeste.setEtat(true);
        assertTest(caseTeste.getEtat());
        
        // Vérification que isBateau reste inchangé (pas de setter)
        //assertTest(!caseTeste.getIsBateau());

        if (compteurlocal + 2 == successCount) {
            System.out.println("\nTest Setters Validé");
        } else {
            System.out.println("\nTest Setters échoué");
        }
    }
}
