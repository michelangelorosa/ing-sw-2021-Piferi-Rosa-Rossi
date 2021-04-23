package it.polimi.ingsw.Model;

import java.io.Serializable;

public class FaithCell implements Serializable{
    private static final long serialVersionUID = 0x1;

    /**Every card is characterized by an id and the point it gives*/
    private final int idCell;
    private int victoryPoints;
    //private VaticanReportSectionEnum vaticanReportSectionEnum;
    //private final PopeSpace popeSpace;

    public FaithCell(int idCell, int victoryPoints) {
        //this.vaticanReportSectionEnum = vaticanReportSectionEnum;
        //this.popeSpace = popeSpace;
        this.idCell = idCell;
        this.victoryPoints = victoryPoints;
    }

    public int getIdCell() {
        return idCell;
    }

    public int getVictoryPoints() {
        return victoryPoints;
    }
/*
    public VaticanReportSectionEnum getVaticanReportSection() {
        return vaticanReportSectionEnum;
    }

    public PopeSpace getPopeSpace() {
        return popeSpace;
    }

    public void setVaticanReportSection(VaticanReportSectionEnum vaticanReportSectionEnum) {
        this.vaticanReportSectionEnum = vaticanReportSectionEnum;
    }
 */

    public void setVictoryPoints(int victoryPoints) {
        this.victoryPoints = victoryPoints;
    }

    public String toString() {
        return idCell+" "+victoryPoints;// + " " + vaticanReportSectionEnum + " " +popeSpace;
    }

}