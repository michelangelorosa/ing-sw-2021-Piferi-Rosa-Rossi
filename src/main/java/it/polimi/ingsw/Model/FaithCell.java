package it.polimi.ingsw.Model;

public class FaithCell {

    private VaticanReportSection vaticanReportSection;
    private final PopeSpace popeSpace;
    private final int idCell;
    private int victoryPoints;

    public FaithCell(int idCell, int victoryPoints, VaticanReportSection vaticanReportSection, PopeSpace popeSpace) {
        this.vaticanReportSection = vaticanReportSection;
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

    public VaticanReportSection getVaticanReportSection() {
        return vaticanReportSection;
    }

    public PopeSpace getPopeSpace() {
        return popeSpace;
    }

    public void setVaticanReportSection(VaticanReportSection vaticanReportSection) {
        this.vaticanReportSection = vaticanReportSection;
    }

    public void setVictoryPoints(int victoryPoints) {
        this.victoryPoints = victoryPoints;
    }

    public String toString() {
        return idCell+" "+victoryPoints + " " + vaticanReportSection + " " +popeSpace;
    }

}