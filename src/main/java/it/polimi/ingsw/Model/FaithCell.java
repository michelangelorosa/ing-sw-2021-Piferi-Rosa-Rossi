package it.polimi.ingsw.Model;

public class FaithCell {

    private final int idCell;
    private int victoryPoints;
    private VaticanReportSectionEnum vaticanReportSectionEnum;
    private final PopeSpace popeSpace;

    public FaithCell(int idCell, int victoryPoints, VaticanReportSectionEnum vaticanReportSectionEnum, PopeSpace popeSpace) {
        this.vaticanReportSectionEnum = vaticanReportSectionEnum;
        this.popeSpace = popeSpace;
        this.idCell = idCell;
        this.victoryPoints = victoryPoints;
    }

    public int getIdCell() {
        return idCell;
    }

    public int getVictoryPoints() {
        return victoryPoints;
    }

    public VaticanReportSectionEnum getVaticanReportSection() {
        return vaticanReportSectionEnum;
    }

    public PopeSpace getPopeSpace() {
        return popeSpace;
    }

    public void setVaticanReportSection(VaticanReportSectionEnum vaticanReportSectionEnum) {
        this.vaticanReportSectionEnum = vaticanReportSectionEnum;
    }

    public void setVictoryPoints(int victoryPoints) {
        this.victoryPoints = victoryPoints;
    }

    public String toString() {
        return idCell+" "+victoryPoints + " " + vaticanReportSectionEnum + " " +popeSpace;
    }

}