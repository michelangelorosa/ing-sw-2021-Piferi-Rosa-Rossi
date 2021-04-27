package it.polimi.ingsw.View.ReducedModel;

import java.io.Serializable;

public class VaticanReportSection implements Serializable {
    private static final long serialVersionUID = 0x1;

    private final int begin;
    private final int end;
    private final int points;


    public VaticanReportSection(int begin, int end, int points) {
        this.begin = begin;
        this.end = end;
        this.points = points;

    }

    /**Getter for the VaticanReportSection class */
    public int getBegin() {
        return begin;
    }

    public int getEnd() {
        return end;
    }

    public int getPoints() {
        return points;
    }

    public String pointsToCli() {
        if(this.points > 9) return ((Integer)this.points).toString(); else return " " + ((Integer)this.points).toString();
    }

}
