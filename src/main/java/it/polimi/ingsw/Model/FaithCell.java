package it.polimi.ingsw.Model;

import it.polimi.ingsw.View.ReducedModel.RedFaithCell;

public class FaithCell extends RedFaithCell{

    /**FaithCell's constructor*/
    public FaithCell(int idCell, int victoryPoints) {
        this.idCell = idCell;
        this.victoryPoints = victoryPoints;
    }

    /**Setter for victoryPoints*/
    public void setVictoryPoints(int victoryPoints) {
        this.victoryPoints = victoryPoints;
    }

    /**Method for converting model classes to view classes*/
    public RedFaithCell toView() {
        return (RedFaithCell) this;
    }

}