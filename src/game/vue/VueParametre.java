package game.vue;
import game.ecouteur.*;
import game.modele.jeu.*;
import game.modele.terrain.*;
import game.modele.joueur.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;

public class VueParametre extends JFrame{
    private ControleurParametre gameControle;
    
    private JPanel panel;
    private JPanel boutonDifficultyPanel;
    private JPanel boutonGridPanel;
    private JPanel boutonVuePanel;
    
    private JButton buttonEasyMode;
    private JButton buttonNormalMode;
    private JButton buttonHardMode;
    
    private JButton buttonSmallGrid;
    private JButton buttonMediumGrid;
    private JButton buttonLargeGrid;
    
    private JButton buttonVueGraphique;
    private JButton buttonVueTerminal;
    
    private JTextField texte;
    private JButton startButton;
    
    /**
     * Constructeur VueParametre
     */
    public VueParametre(){
        this.setTitle("Bataille Navale");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       	this.setLayout(new BorderLayout(10, 10));
        
        this.gameControle = new ControleurParametre();
       	
       	this.panel = new JPanel();
        this.panel.setLayout(new GridLayout(5, 1, 5, 5));
        
        //CHOIX DE LA TAILLE DE LA GRILLE ------------------------------------
        
        this.boutonGridPanel = new JPanel();
        this.boutonGridPanel.setLayout(new GridLayout(1, 3, 5, 5));
        
        this.buttonSmallGrid = new JButton("Petite grille");
        this.buttonSmallGrid.addActionListener(e -> gridSize("small"));
        
        this.buttonMediumGrid = new JButton("Grille convenable");
        this.buttonMediumGrid.addActionListener(e -> gridSize("medium"));
        
        this.buttonLargeGrid = new JButton("Grande Grille");
        this.buttonLargeGrid.addActionListener(e -> gridSize("large"));

        
        this.boutonGridPanel.add(buttonSmallGrid);
        this.boutonGridPanel.add(buttonMediumGrid);
        this.boutonGridPanel.add(buttonLargeGrid);
        
        //CHOIX DE LA DIFFICULTE ---------------------------------------------
        
        this.boutonDifficultyPanel = new JPanel();
        this.boutonDifficultyPanel.setLayout(new GridLayout(1, 3, 5, 5));
        
        this.buttonEasyMode = new JButton("IA FOLLE");
        this.buttonEasyMode.setBackground(Color.GREEN);
        this.buttonEasyMode.addActionListener(e -> difficulty("easy"));
        
        this.buttonNormalMode = new JButton("IA PSEUDO INTELLIGENTE");
        this.buttonNormalMode.setBackground(Color.ORANGE);
        this.buttonNormalMode.addActionListener(e -> difficulty("normal"));
        
        this.buttonHardMode = new JButton("TERMINATOR");
        this.buttonHardMode.setBackground(Color.RED);
        this.buttonHardMode.addActionListener(e -> difficulty("hard"));
        
        this.buttonEasyMode.setPreferredSize(new Dimension(150, 50));
	    this.buttonNormalMode.setPreferredSize(new Dimension(150, 50));
	    this.buttonHardMode.setPreferredSize(new Dimension(150, 50));
        
        this.buttonEasyMode.setEnabled(false);
    	this.buttonNormalMode.setEnabled(false);
    	this.buttonHardMode.setEnabled(false);
        
        this.boutonDifficultyPanel.add(buttonEasyMode);
        this.boutonDifficultyPanel.add(buttonNormalMode);
        this.boutonDifficultyPanel.add(buttonHardMode);
        
        //CHOIX DE L'AFFICHAGE ---------------------------------------------
        
        this.boutonVuePanel = new JPanel();
        this.boutonVuePanel.setLayout(new GridLayout(1, 2, 5, 5));
        
        this.buttonVueGraphique = new JButton("Affichage graphique");
        this.buttonVueGraphique.addActionListener(e -> view("graphique"));
        
        this.buttonVueTerminal = new JButton("Affichage Terminal");
        this.buttonVueTerminal.addActionListener(e -> view("terminal"));
        
        this.buttonVueGraphique.setEnabled(false);
    	this.buttonVueTerminal.setEnabled(false);
        
        this.boutonVuePanel.add(buttonVueGraphique);
        this.boutonVuePanel.add(buttonVueTerminal);
        
        //LE RESTE ----------------------------------------------------------
        
        this.texte = new JTextField("Choix des parametres");
        this.texte.setEditable(false);
       	this.texte.setHorizontalAlignment(JTextField.CENTER);
       	
       	this.startButton = new JButton("Lancer la partie");
       	this.startButton.addActionListener(e -> startGame());
       	this.startButton.setEnabled(false);
       	
       	this.panel.add(texte);
       	this.panel.add(boutonGridPanel);
       	this.panel.add(boutonDifficultyPanel);
       	this.panel.add(boutonVuePanel);
       	this.panel.add(startButton);
        
        this.add(panel);
        this.setSize(300, 300);
       	this.pack();
       	this.setVisible(true);
    }
    
    /**
     * Definie le choix de taille de grille 
     * + desactive les boutons de choix de grille
     * + active les boutons suivants
     */
    public void gridSize(String choix){
    	this.buttonSmallGrid.setEnabled(false);
    	this.buttonMediumGrid.setEnabled(false);
    	this.buttonLargeGrid.setEnabled(false);
    	
    	this.buttonEasyMode.setEnabled(true);
    	this.buttonNormalMode.setEnabled(true);
    	this.buttonHardMode.setEnabled(true);
    	
    	this.gameControle.setGridSize(choix);
    }
    
    /**
     * Definie le choix de difficulte 
     * + desactive les boutons de choix de difficulte
     * + active les boutons suivants
     */
    public void difficulty(String choix){
    	this.buttonEasyMode.setEnabled(false);
    	this.buttonNormalMode.setEnabled(false);
    	this.buttonHardMode.setEnabled(false);
    	
    	this.buttonVueGraphique.setEnabled(true);
        this.buttonVueTerminal.setEnabled(true);
        
        this.gameControle.setDifficulty(choix);
    }
    
    /**
     * Definie le choix de vue 
     * + desactive les boutons de choix de vue
     * + active le bouton start
     */
    public void view(String choix){
    	this.buttonVueGraphique.setEnabled(false);
        this.buttonVueTerminal.setEnabled(false);
        
        this.startButton.setEnabled(true);
        
    	this.gameControle.setView(choix);
    }
    
    /**
     * Ferme la fenetre actuelle 
     * + ouvre une fenetre avec le choix de vue decide
     */
    public void startGame(){
    	this.dispose();
    	this.gameControle.confirm();
    }
}
