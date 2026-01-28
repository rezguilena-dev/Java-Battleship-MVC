package game.vue;
import game.modele.jeu.*;
import game.modele.joueur.*;
import game.modele.terrain.*;
import game.ecouteur.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.*;

public class NavalGUI extends JFrame {
    private VueNaval vue;
    private JPanel sidePanel;
    private JTextField textCoupJ1;
    private JTextField textCoupJ2;

    /**
     * Constructeur NavalGUI
     * @param game un jeu de bataille navale
     */
    public NavalGUI(Jeu game){
        this.setTitle("Bataille Navale");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

	    this.vue = new VueNaval(game, 40, 10);
        
        this.sidePanel = new JPanel();
        this.sidePanel.setLayout(new GridLayout(2, 1, 0, 0));
        
        this.textCoupJ1 = new JTextField("");
        this.textCoupJ1.setEditable(false);
        this.textCoupJ1.setFont(new Font("Arial", Font.PLAIN, 20));
        this.textCoupJ1.setHorizontalAlignment(JTextField.LEFT);
        
        this.textCoupJ2 = new JTextField("");
        this.textCoupJ2.setEditable(false);
        this.textCoupJ2.setFont(new Font("Arial", Font.PLAIN, 20));
        this.textCoupJ2.setHorizontalAlignment(JTextField.LEFT);
	
	    this.vue.addMouseListener(new JeuControleur(game,this.vue, this));
        this.vue.repaint();
        
        this.sidePanel.add(textCoupJ1);
        this.sidePanel.add(textCoupJ2);
        
        this.add(this.vue, BorderLayout.CENTER);
        this.add(sidePanel, BorderLayout.SOUTH);

        this.pack();
        this.setVisible(true);
    }
    
    /**
     * Change l'affichage des coups du joueur reel
     */
    public void setTextCoupJ1(String val){
    	this.textCoupJ1.setText(val);
    }
    
    /**
     * Change l'affichage des coups du joueur ia
     */
    public void setTextCoupJ2(String val){
    	this.textCoupJ2.setText(val);
    }
    
}
