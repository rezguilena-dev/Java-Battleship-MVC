package game.modele.Test;
import game.modele.joueur.*;
import game.modele.jeu.*;
import game.modele.terrain.*;
import java.util.*;

public class TestUnitaire {
    private static int successCount = 0;
    private static int testCount = 0;

    public static void main(String[] args) {
        System.out.println("Les Teste Unitaire débutent !");
        AmiralTest testAmiral=new AmiralTest();
        BateauTest testBateau=new BateauTest();
        CaseTest testCase=new CaseTest();
        FlotteTest testFlotte=new FlotteTest();
        GridTest testGrid=new GridTest();
        IaFolleTest testIaFolle=new IaFolleTest();
        JeuTest testJeu=new JeuTest();
        TerminatorTest testTerminator=new TerminatorTest();
        boolean testUnitairValide=true;
        testUnitairValide=testUnitairValide && testAmiral.test();
        testUnitairValide=testUnitairValide && testBateau.test();
        testUnitairValide=testUnitairValide && testCase.test();
        testUnitairValide=testUnitairValide && testFlotte.test();
        testUnitairValide=testUnitairValide && testGrid.test();
        testUnitairValide=testUnitairValide && testIaFolle.test();
        testUnitairValide=testUnitairValide && testJeu.test();
        testUnitairValide=testUnitairValide && testTerminator.test();
        if (testUnitairValide){
            System.out.println("Toutes les tests sont validé");
        }
        else {
            System.out.println("au moins un test KO ");
        }

    }
}
