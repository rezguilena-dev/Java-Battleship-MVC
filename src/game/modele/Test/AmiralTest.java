package game.modele.Test;
import game.modele.joueur.*;
import game.modele.jeu.*;
import game.modele.terrain.*;
import java.util.*;

public class AmiralTest{
    private static int testCount = 0;
    private static int successCount = 0;

     public static boolean test(){
        System.out.println("=== Début des tests manuels pour la classe Amiral ===");
        
        testGetSymbole();
        testListePositionTir();
        testAction();
        testGetActionText();
        return testCount==successCount;
    }

    private static void testGetSymbole() {
        
        Grid grille = new Grid(10,10);
        Player amiral = new Amiral("X", new Scanner(System.in), grille, 5);
        
        boolean result = "X".equals(amiral.getSymbole());
        assertTest(result);
        
    }

    private static void testListePositionTir() {
        Grid grille = new Grid(10,10);
        Player amiral = new Amiral("X", new Scanner(System.in), grille, 5);
        
        // Test liste vide initialement
        
        boolean result1 = amiral.getListePositionTir().isEmpty();
        assertTest(result1);
        
        // Test après ajout d'une position
        Position pos = new Position(1, 1, "B2");
        amiral.setListePositionTir(pos);
        boolean result2 = !amiral.getListePositionTir().isEmpty() && 
                         amiral.getListePositionTir().get(0).equals(pos);
        assertTest(result2);
        
    }

    private static void testAction() {
        
        
        // Configuration du test
        Scanner scan = new Scanner("A1\nB2\n");
        
        Grid grille1 = new Grid(10,10);
        Grid grille2 = new Grid(10,10);
        Player amiral1 = new Amiral("X", scan, grille1, 5);
        Player amiral2 = new Amiral("X", scan, grille2, 5);

        Jeu jeu = new Jeu(amiral1,amiral2);
        
        
        // Création de la liste des tirs possibles
        List<Position> tirsPossibles = new ArrayList<>();
        tirsPossibles.add(new Position(0, 0, "A1"));
        tirsPossibles.add(new Position(1, 1, "B2"));
        amiral1.setListeTirPossible(tirsPossibles);
        
        // Exécution de l'action
        Position coup = amiral1.action(jeu);
        
        // Vérifications
        boolean result1 = coup != null;
        boolean result2 = coup.getX() == 0 && coup.getY() == 0;
        boolean result3 = amiral1.getListePositionTir().size() == 1;
        boolean result4 = amiral1.getListeTirPossible().size() == 1;
        assertTest(result1);
        assertTest(result2);
        assertTest(result3);
        assertTest(result4);
    }

    private static void testGetActionText() {        
        Grid grille = new Grid(10,10);
        Player amiral = new Amiral("X", new Scanner(System.in), grille, 5);
        
        Position pos = new Position(3, 4, "D5");
        amiral.setListePositionTir(pos);
        
        String expected = "Vous avez choisi la position D5";
        boolean result = expected.equals(amiral.getActionText());
        assertTest(result);
    }

    private static void assertTest(boolean condition) {
        testCount++;
        if (condition) {
            successCount++;
    }
}}
