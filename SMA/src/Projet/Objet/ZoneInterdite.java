package Projet.Objet;

/**
 * Created by BettyFlop on 24/02/2016.
 */
public class ZoneInterdite extends Element {
    public double rayon;
    public int tempsRestant = 150;

    /**
     * Constructeur
     * @param _positionPlanX
     * @param _positionPlanY
     * @param _rayon
     */
    public ZoneInterdite(double _positionPlanX, double _positionPlanY, double _rayon) {
        positionPlanX = _positionPlanX;
        positionPlanY = _positionPlanY;
        rayon = _rayon;
    }

    /**
     * Décrémente le temps d'apparition de la zone
     */
    public void compteReboursTempsRestant() {
        tempsRestant--;
    }

    /**
     * Signale quand la zone ne s'affiche plus
     * @return boolean
     */
    public boolean estMort() {
        return tempsRestant <= 0;
    }

}