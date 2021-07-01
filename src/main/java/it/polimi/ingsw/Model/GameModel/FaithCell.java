package it.polimi.ingsw.Model.GameModel;

import it.polimi.ingsw.View.ReducedModel.RedFaithCell;

/**
 * FaithCell Class includes new methods for a Faith Cell to be used on the Model
 * @author francescopiferi99
 */
public class FaithCell extends RedFaithCell{

    /**FaithCell's constructor*/
    public FaithCell(int idCell, int victoryPoints) {
        super(idCell);
        this.victoryPoints = victoryPoints;
    }

    /**Setter for victoryPoints*/
    public void setVictoryPoints(int victoryPoints) {
        this.victoryPoints = victoryPoints;
    }

    /**Method for converting model classes to view classes*/
    public RedFaithCell toView() {
        return this;
    }

}