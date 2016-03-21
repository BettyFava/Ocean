package Projet;

import Projet.Systeme.Fenetre;

import javax.swing.*;

/**
 * Created by BettyFlop on 18/03/2016.
 */
public class Launch {
    /**
     * Lancement de l'application
     * @param args
     */
    public static void main(String[] args) {
        // Création de la fenêtre
        JFrame fenetre = new JFrame();
        fenetre.setTitle("Demonstration SMA");
        fenetre.setSize(1250, 750);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setResizable(false);
        // Création du contenu
        Fenetre contenu = new Fenetre();
        fenetre.setContentPane(contenu);
        // Affichage
        fenetre.setVisible(true);
        contenu.Lancer();
    }
}
