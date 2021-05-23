package it.polimi.ingsw.View.ReducedModel;

import java.io.Serializable;

public class RedVaticanReportSection implements Serializable {
    private static final long serialVersionUID = 0x1;

    protected final int begin;
    protected final int end;
    protected final int points;

    protected RedVaticanReportSection(int begin, int end, int points) {
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
