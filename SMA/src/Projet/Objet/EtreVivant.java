package Projet.Objet;

import java.util.ArrayList;

/**
 * Created by BettyFlop on 18/03/2016.
 */
public class EtreVivant extends Element {

    public static final double SAUT = 3;
    public static final double DISTANCE_MINIMUM = 5;

    public double vitessePlanX;
    public double vitessePlanY;


    /**
     * Constructeur
     * @param _positionPlanX
     * @param _positionPlanY
     * @param _direction
     */
    public EtreVivant(double _positionPlanX, double _positionPlanY, double _direction) {
        positionPlanX = _positionPlanX;
        positionPlanY = _positionPlanY;
        vitessePlanX = calculerVitesseX(_direction);
        vitessePlanY = calculerVitesseY(_direction);

    }

    /**
     * Calcule la vitesseX
     * @param _direction
     * @return
     */
    public double calculerVitesseX(double _direction) {
        return Math.cos(_direction);
    }

    /**
     * Calcule la vitesseY
     * @param _direction
     * @return
     */
    public double calculerVitesseY(double _direction) {
        return Math.sin(_direction);
    }


    /**
     * Calcule la nouvelle position à prendre
     */
    protected void nouvellePosition() {
        positionPlanX += SAUT * vitessePlanX;
        positionPlanY += SAUT * vitessePlanY;
    }


    /**
     * Calcule la distance par rapport au mur
     * @param murXMinimum
     * @param murYMinimum
     * @param murXMaximum
     * @param murYMaximum
     * @return
     */
    protected double DistanceParRapportMur(double murXMinimum, double murYMinimum, double murXMaximum, double murYMaximum) {
        double min = Math.min(positionPlanX - murXMinimum, positionPlanY - murYMinimum);
        min = Math.min(min, murXMaximum - positionPlanX);
        min = Math.min(min, murYMaximum - positionPlanY);
        return min;
    }

    /**
     * Normalise le vecteur
     */
    protected void Normaliser() {
        double longueur = Math.sqrt(vitessePlanX * vitessePlanX + vitessePlanY * vitessePlanY);
        vitessePlanX /= longueur;
        vitessePlanY /= longueur;
    }

    /**
     * Evite les murs formés par la fenêtre
     * @param murXMinimum
     * @param murYMinimum
     * @param murXMaximum
     * @param murYMaximum
     * @return boolean
     */
    protected boolean EviterLesMurs(double murXMinimum, double murYMinimum, double murXMaximum, double murYMaximum) {
        //les poissons sont emprisonnés dans notre fenêtre.
        if (positionPlanX < murXMinimum) {
            positionPlanX = murXMinimum;
        } else if (positionPlanY < murYMinimum) {
            positionPlanY = murYMinimum;
        } else if (positionPlanX > murXMaximum) {
            positionPlanX = murXMaximum;
        } else if (positionPlanY > murYMaximum) {
            positionPlanY = murYMaximum;
        }

        //Et puis, s'il voit le mur, il vont changer de direction
        double distance = DistanceParRapportMur(murXMinimum, murYMinimum, murXMaximum, murYMaximum);
        if (distance < DISTANCE_MINIMUM) {
            if (distance == (positionPlanX - murXMinimum)) {
                vitessePlanX += 0.3;
            } else if (distance == (positionPlanY - murYMinimum)) {
                vitessePlanY += 0.3;
            } else if (distance == (murXMaximum - positionPlanX)) {
                vitessePlanX -= 0.3;
            } else if (distance == (murYMaximum - positionPlanY)) {
                vitessePlanY -= 0.3;
            }
            Normaliser();
            return true;
        }
        return false;
    }

    /**
     * Evite les obstacles présents dans l'océan
     * @param obstacles
     * @return boolean
     */
    protected boolean EviterObstacles(ArrayList<ZoneInterdite> obstacles) {
        if (!obstacles.isEmpty()) {
            // Recherche de l'obstacle le plus proche
            ZoneInterdite obstacleProche = obstacles.get(0);
            double distanceCarre = CalculerDistanceCarre(obstacleProche);
            for (ZoneInterdite o : obstacles) {
                if (CalculerDistanceCarre(o) < distanceCarre) {
                    obstacleProche = o;
                    distanceCarre = CalculerDistanceCarre(o);
                }
            }

            if (distanceCarre < (obstacleProche.rayon * obstacleProche.rayon)) {
                // Si collision, calcul du vecteur diff
                double distance = Math.sqrt(distanceCarre);
                double diffX = (obstacleProche.positionPlanX - positionPlanX) / distance;
                double diffY = (obstacleProche.positionPlanY - positionPlanY) / distance;
                vitessePlanX = vitessePlanX - diffX / 2;
                vitessePlanY = vitessePlanY - diffY / 2;
                Normaliser();
                return true;
            }
        }
        return false;
    }
}

