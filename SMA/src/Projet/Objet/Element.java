package Projet.Objet;

/**
 * Created by BettyFlop on 24/02/2016.
 */
public class Element {

    public double positionPlanX;
    public double positionPlanY;

    /**
     * Constructeur vide
     */
    public Element(){}

    /**
     * Constructeur
     * @param _positionPlanX
     * @param _positionPlanY
     */
    public Element (double _positionPlanX, double _positionPlanY){
        positionPlanX = _positionPlanX;
        positionPlanY = _positionPlanY;
    }

    /**
     * Calcule la distance entre deux éléments
     * @param element
     * @return
     */
    public double CalculerDistance(Element element){
        return Math.sqrt((element.positionPlanX - positionPlanX)*(element.positionPlanX - positionPlanX) + (element.positionPlanY - positionPlanY) * (element.positionPlanY - positionPlanY));
    }

    /**
     * Calcule la distance au carrée entre deux éléments
     * @param element
     * @return
     */
    public double CalculerDistanceCarre(Element element) {
        return (element.positionPlanX - positionPlanX)*(element.positionPlanX - positionPlanX) + (element.positionPlanY - positionPlanY) * (element.positionPlanY - positionPlanY);
    }

}
