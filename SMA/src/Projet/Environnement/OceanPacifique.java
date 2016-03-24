package Projet.Environnement;

import Projet.Objet.Poisson;
import Projet.Objet.Requin;
import Projet.Objet.ZoneInterdite;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

/**
 * Created by BettyFlop on 24/02/2016.
 */
public class OceanPacifique extends Observable {
    public Poisson[] poissons;
    public Requin requin;
    public ArrayList<ZoneInterdite> obstacles;
    public Random generateur;
    public double largeur;
    public double hauteur;

    /**
     * Constructeur
     * @param _nombrePoissons
     * @param _largeur
     * @param _hauteur
     */
    public OceanPacifique(int _nombrePoissons, double _largeur, double _hauteur) {
        largeur = _largeur;
        hauteur = _hauteur;
        generateur = new Random();
        obstacles = new ArrayList();
        requin = new Requin(generateur.nextDouble() * largeur, generateur.nextDouble() * hauteur, generateur.nextDouble() * 2 * Math.PI);
        poissons = new Poisson[_nombrePoissons];
        for (int i = 0; i < _nombrePoissons; i++) {
            poissons[i] = new Poisson(generateur.nextDouble() * largeur, generateur.nextDouble() * hauteur, generateur.nextDouble() * 2 * Math.PI);
        }
    }

    /**
     * Ajoute un obstacle
     * @param _positionPlanX
     * @param _positionPlanY
     * @param rayon
     */
    public void AjouterObstacle(double _positionPlanX, double _positionPlanY, double rayon) {
        obstacles.add(new ZoneInterdite(_positionPlanX, _positionPlanY, rayon));
    }

    /**
     * Met à jour les obstacles selon le temps qu'il reste à apparaître
     */
    protected void MiseAJourObstacles() {
        for(ZoneInterdite obstacle : obstacles) {
            obstacle.compteReboursTempsRestant();
        }
        obstacles.removeIf(o -> o.estMort());
    }

    /**
     * Met à jour les poissons présents dans l'océan
     */
    protected void MiseAJourPoissons() {
        for (Poisson p : poissons) {
            p.MiseAJour(poissons, obstacles, requin, largeur, hauteur);
        }

    }

    /**
     * Met à jour le requin
     */
    protected void MiseAJourRequin() {
        requin.MiseAJour( obstacles,  largeur, hauteur);

    }

    /**
     * Met à jour l'ensemble de l'océan
     */
    public void MiseAJourOcean() {
        MiseAJourObstacles();
        MiseAJourPoissons();
        MiseAJourRequin();
        setChanged();
        notifyObservers();
    }
}
