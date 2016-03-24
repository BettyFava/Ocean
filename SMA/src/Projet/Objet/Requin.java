package Projet.Objet;

import java.util.ArrayList;

/**
 * Created by BettyFlop on 24/02/2016.
 */
public class Requin extends EtreVivant {


    /**
     * Constructeur
     * @param _positionPlanX
     * @param _positionPlanY
     * @param _direction
     */
    public Requin(double _positionPlanX, double _positionPlanY, double _direction) {
        super(_positionPlanX, _positionPlanY, _direction);

    }

    /**
     * Mise à jour de la position du requin
     * @param obstacles
     * @param largeur
     * @param hauteur
     */
    public void MiseAJour( ArrayList<ZoneInterdite> obstacles, double largeur, double hauteur) {
        if (!EviterLesMurs(0,0,largeur,hauteur)) {
            if (!EviterObstacles(obstacles)) {

            }
        }
        nouvellePosition();
    }
}

