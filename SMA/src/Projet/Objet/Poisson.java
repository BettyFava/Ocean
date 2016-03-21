package Projet.Objet;

import java.util.ArrayList;

/**
 * Created by BettyFlop on 24/02/2016.
 */
public class Poisson extends EtreVivant {


    public static final double DISTANCE_MINIMUM_CARREE = 25;
    public static final double DISTANCE_MAXIMUM_CARREE = 1600;
    public static final double DISTANCE_REQUIN = 15000;

    /**
     * Constructeur
     * @param _positionPlanX
     * @param _positionPlanY
     * @param _direction
     */
    public Poisson(double _positionPlanX, double _positionPlanY, double _direction) {
        super(_positionPlanX, _positionPlanY, _direction);

    }

    /**
     * Vérifie l'alignement avec un poisson
     * @param poisson
     * @return boolean
     */
    protected boolean estAligneAvec(Poisson poisson) {
        double distanceCarre = CalculerDistanceCarre(poisson);
        return (distanceCarre < DISTANCE_MAXIMUM_CARREE && distanceCarre > DISTANCE_MINIMUM_CARREE);
    }


    /**
     * Evite le requin selon la distance choisie
     * @param requin
     * @return boolean
     */
    protected boolean EviterRequin(Requin requin) {
        double distanceCarre = CalculerDistanceCarre(requin);
        if (distanceCarre  < DISTANCE_REQUIN) {
            double distance = Math.sqrt(distanceCarre);
            double ecartX = ((requin.positionPlanX - positionPlanX) *2)/ distance;
            double ecartY = ((requin.positionPlanY - positionPlanY) *2)/ distance;
            vitessePlanX = vitessePlanX - ecartX / 4;
            vitessePlanY = vitessePlanY - ecartY / 4;
            Normaliser();
            return true;
        }
        return false;
    }

    /**
     * Evite ses copains
     * @param poissons
     * @return
     */
    protected boolean EviterSesCopains(Poisson[] poissons) {
        // Recherche du poisson le plus proche
        Poisson pote;
        if (!poissons[0].equals(this)) {
            pote = poissons[0];
        } else {
            pote = poissons[1];
        }
        double distanceCarre = CalculerDistanceCarre(pote);
        for (Poisson poisson : poissons) {
            if (CalculerDistanceCarre(poisson) < distanceCarre && !poisson.equals(this)) {
                pote = poisson;
                distanceCarre = CalculerDistanceCarre(pote);
            }
        }

        // Evitement
        if (distanceCarre < DISTANCE_MINIMUM_CARREE) {
            double distance = Math.sqrt(distanceCarre);
            double ecartX = (pote.positionPlanX - positionPlanX) / distance;
            double ecartY = (pote.positionPlanY - positionPlanY) / distance;
            vitessePlanX = vitessePlanX - ecartX / 4;
            vitessePlanY = vitessePlanY - ecartY / 4;
            Normaliser();
            return true;
        }
        return false;
    }

    /**
     * Calcule la direction prise par l'ensemble de ses congénères
     * @param poissons
     */
    protected void CalculerDirectionPrise(Poisson[] poissons) {
        double vitesseXTotal = 0;
        double vitesseYTotal = 0;
        int nbTotal = 0;
        for (Poisson p : poissons) {
            if (estAligneAvec(p)) {
                vitesseXTotal += p.vitessePlanX;
                vitesseYTotal += p.vitessePlanY;
                nbTotal++;
            }
        }
        if (nbTotal >= 1) {
            vitessePlanX = (vitesseXTotal / nbTotal + vitessePlanX) / 2;
            vitessePlanY = (vitesseYTotal / nbTotal + vitessePlanY) / 2;
            Normaliser();
        }
    }

    /**
     * Mise à jour de la position du poisson
     * @param poissons
     * @param obstacles
     * @param requin
     * @param largeur
     * @param hauteur
     */
    public void MiseAJour(Poisson[] poissons, ArrayList<ZoneInterdite> obstacles, Requin requin, double largeur, double hauteur) {
        if (!EviterLesMurs(0, 0, largeur, hauteur)) {
            if (!EviterObstacles(obstacles)) {
                if (!EviterRequin(requin)) {
                    if (!EviterSesCopains(poissons)) {
                        CalculerDirectionPrise(poissons);
                    }
                }
            }
        }
        nouvellePosition();
    }

}