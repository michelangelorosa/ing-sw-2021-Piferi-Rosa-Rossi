package it.polimi.ingsw.Model;

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

    /**Method to check if the the vatican section is correct*/
    public void isCorrect() throws  IllegalArgumentException{
        if(begin < end || begin < 0 || end < 0 || begin > 25 || end > 25) throw new IllegalArgumentException("Vatican Report Section index out of bound");
    }
}
