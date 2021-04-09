package it.polimi.ingsw.Model;

public class VaticanReportSection {

    private final int begin;
    private final int end;
    private final int points;


    public VaticanReportSection(int begin, int end, int points) {
        this.begin = begin;
        this.end = end;
        this.points = points;

    }

    public int getBegin() {
        return begin;
    }

    public int getEnd() {
        return end;
    }

    public int getPoints() {
        return points;
    }
}
