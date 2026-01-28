package game.modele.Test;
import game.modele.joueur.*;
import game.modele.jeu.*;
import game.modele.terrain.*;


public class GridTest {
    private static int successCount = 0;
    private static int testCount = 0;

     public static boolean test() {
        System.out.println("=== Tests Grid ===");
        
        testConstructeur();
        testGetters();
        testPositionSymbolConversion();
        testCellOperations();
        
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
        Grid grid = new Grid(10, 10);
        
        assertTest(grid.getWidth() == 10);
        assertTest(grid.getHeight() == 10);
        assertTest(grid.getGrid() != null);
        assertTest(grid.getCell(0, 0) instanceof CaseMer);

        if (compteurlocal + 4 == successCount) {
            System.out.println("\nTest Constructeur Validé");
        } else {
            System.out.println("\nTest Constructeur échoué");
        }
    }

    private static void testGetters() {
        int compteurlocal = successCount;
        Grid grid = new Grid(5, 8);
        assertTest(grid.getGrid().length == 5);
        assertTest(grid.getGrid()[0].length == 8);

        if (compteurlocal + 2 == successCount) {
            System.out.println("\nTest Getters Validé");
        } else {
            System.out.println("\nTest Getters échoué");
        }
    }

    private static void testPositionSymbolConversion() {
        int compteurlocal = successCount;
        Grid grid = new Grid(10, 10);
        
        // Test getPositionFromSymbol
        Position pos = grid.getPositionFromSymbol("B3");
        assertTest(pos.getX() == 2);
        assertTest(pos.getY() == 1);
        
        // Test getSymbolFromCoord
        String symbol = grid.getSymbolFromCoord(4, 2);
        assertTest("C5".equals(symbol));

        if (compteurlocal + 3 == successCount) {
            System.out.println("\nTest Position/Symbol Conversion Validé");
        } else {
            System.out.println("\nTest Position/Symbol Conversion échoué");
        }
    }

    private static void testCellOperations() {
        int compteurlocal = successCount;
        Grid grid = new Grid(10, 10);
        Case testCase = new PieceBateau("X");
        
        // Test setCell/getCell
        grid.setCell(2, 3, testCase);
        Case retrieved = grid.getCell(2, 3);
        assertTest(retrieved == testCase);
        
        // Test getCell hors limites
        assertTest(grid.getCell(-1, 0) == null);
        assertTest(grid.getCell(10, 5) == null);
        assertTest(grid.getCell(5, 10) == null);

        if (compteurlocal + 4 == successCount) {
            System.out.println("\nTest Cell Operations Validé");
        } else {
            System.out.println("\nTest Cell Operations échoué");
        }
    }
}
