package Projet.Systeme;

import Projet.Environnement.OceanPacifique;
import Projet.Objet.Poisson;
import Projet.Objet.Requin;
import Projet.Objet.ZoneInterdite;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by BettyFlop on 18/03/2016.
 */
public class Fenetre extends JPanel implements Observer, MouseListener {
    private OceanPacifique oceanPacifique;
    protected Timer temps;
    Color color = Color.RED;

    /**
     * Constructeur
     */
    public Fenetre() {
        this.setBackground(new Color(106, 186, 208));
        this.addMouseListener(this);
    }

    /**
     * Ensemble des élements présents au lancement
     */
    public void Lancer() {
        oceanPacifique = new OceanPacifique(2000, this.getWidth(), getHeight());
        oceanPacifique.addObserver(this);
        TimerTask tache = new TimerTask() {
            @Override
            public void run() {
                oceanPacifique.MiseAJourOcean();
            }
        };
        temps = new Timer();
        temps.scheduleAtFixedRate(tache, 0, 15);
    }

    /**
     * Affichage des poissons
     * @param poisson
     * @param gra
     */
    protected void DessinerPoisson(Poisson poisson, Graphics gra) {
        gra.drawLine((int) poisson.positionPlanX, (int) poisson.positionPlanY, (int) (poisson.positionPlanX - 10 * poisson.vitessePlanX), (int) (poisson.positionPlanY - 10 * poisson.vitessePlanY));
    }

    /**
     * Affichage du requin
     * @param requin
     * @param gra
     */
    protected void DessinerRequin(Requin requin, Graphics gra) {
        gra.drawLine((int) requin.positionPlanX, (int) requin.positionPlanY, (int) (requin.positionPlanX - 50 * requin.vitessePlanX), (int) (requin.positionPlanY - 50 * requin.vitessePlanY));
        gra.setColor(color);
    }

    /**
     * Affichage de l'obstacle
     * @param zone
     * @param gra
     */
    protected void DessinerObstacle(ZoneInterdite zone, Graphics gra) {
        gra.drawOval((int) (zone.positionPlanX - zone.rayon), (int) (zone.positionPlanY - zone.rayon), (int) zone.rayon * 4, (int) zone.rayon * 4);
    }

    /**
     * Mise à jour
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {
        this.repaint();
    }

    /**
     * Affichage de tous les éléments
     * @param gra
     */
    @Override
    public void paintComponent(Graphics gra) {
        super.paintComponent(gra);
        for (Poisson poisson : oceanPacifique.poissons) {
            DessinerPoisson(poisson, gra);
        }
        for (ZoneInterdite zone : oceanPacifique.obstacles) {
            DessinerObstacle(zone, gra);
        }
        DessinerRequin(oceanPacifique.requin, gra);
    }

    /**
     * Evenement quand clic de souris
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        oceanPacifique.AjouterObstacle(e.getX(), e.getY(), 10);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }
    @Override
    public void mouseReleased(MouseEvent e) {

    }
    @Override
    public void mouseEntered(MouseEvent e) {

    }
    @Override
    public void mouseExited(MouseEvent e) {

    }


}
