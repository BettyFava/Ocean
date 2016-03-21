package Projet.Objet;

import java.util.ArrayList;

/**
 * Created by BettyFlop on 24/02/2016.
 */
public class Requin extends EtreVivant {

    public static final double SAUT = 3;
    public static final double DISTANCE_MINIMUM = 5;
    public static final double DISTANCE_MINIMUM_CARREE = 25;
    public static final double DISTANCE_MAXIMUM = 40;
    public static final double DISTANCE_MAXIMUM_CARREE = 1600;


    public Requin(double _positionPlanX, double _positionPlanY, double _direction) {
        super(_positionPlanX, _positionPlanY, _direction);

    }


    public void MiseAJour(Poisson[] poissons, ArrayList<ZoneInterdite> obstacles, Requin requin, double largeur, double hauteur) {
        if (!EviterLesMurs(0,0,largeur,hauteur)) {
            if (!EviterObstacles(obstacles)) {

            }
        }
        nouvellePosition();
    }
}

