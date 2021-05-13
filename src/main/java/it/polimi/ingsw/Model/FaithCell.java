package it.polimi.ingsw.Model;

import java.io.Serializable;

public class FaithCell implements Serializable{
    private static final long serialVersionUID = 0x1;

    /**Every card is characterized by an id and the point it gives*/
    private final int idCell;
    private int victoryPoints;

    /**FaithCell's constructor*/
    public FaithCell(int idCell, int victoryPoints) {
        this.idCell = idCell;
        this.victoryPoints = victoryPoints;
    }

    /**Getter for idCell*/
    public int getIdCell() {
        return idCell;
    }

    /**Getter for victoryPoints*/
    public int getVictoryPoints() {
        return victoryPoints;
    }

    /**Setter for victoryPoints*/
    public void setVictoryPoints(int victoryPoints) {
        this.victoryPoints = victoryPoints;
    }

    public String toString() {
        return idCell+" "+victoryPoints;
    }

    /**Method for converting model classes to view classes*/
    public it.polimi.ingsw.View.ReducedModel.FaithCell toView() {
        return new it.polimi.ingsw.View.ReducedModel.FaithCell(this.idCell, this.victoryPoints);
    }

}