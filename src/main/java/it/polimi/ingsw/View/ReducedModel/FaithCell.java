package it.polimi.ingsw.View.ReducedModel;

import java.io.Serializable;

public class FaithCell implements Serializable{
    private static final long serialVersionUID = 0x1;

    /**Every card is characterized by an id and the point it gives*/
    private final int idCell;
    private int victoryPoints;

    public FaithCell(int idCell, int victoryPoints) {
        this.idCell = idCell;
        this.victoryPoints = victoryPoints;
    }

    public int getIdCell() {
        return idCell;
    }

    public int getVictoryPoints() {
        return victoryPoints;
    }

    public String toString() {
        return idCell+" "+victoryPoints;// + " " + vaticanReportSectionEnum + " " +popeSpace;
    }

}