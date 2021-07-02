package it.polimi.ingsw.View.ReducedModel;

import java.io.Serializable;

/**
 * Reduced Vatican Report Class
 */
public class RedVaticanReportSection implements Serializable {
    protected final int begin;
    protected final int end;
    protected final int points;

    /**
     * Constructor for Reduced Vatican Report
     * @param begin     The beginning of the section
     * @param end       The end of the section
     * @param points    The points given by the section
     */
    protected RedVaticanReportSection(int begin, int end, int points) {
        this.begin = begin;
        this.end = end;
        this.points = points;
    }

    /**Getter for the VaticanReportSection class */
    public int getBegin() {
        return begin;
    }

    /**
     * Gets the end of the RedVaticanReport
     * @return
     */
    public int getEnd() {
        return end;
    }
    /**
     * Gets the points of the RedVaticanReport
     * @return
     */
    public int getPoints() {
        return points;
    }
    /**
     * Shows the point of the Vatican Section for the Cli to show
     * @return  String with the point information
     */
    public String pointsToCli() {
        if(this.points > 9) return ((Integer)this.points).toString(); else return " " + ((Integer)this.points).toString();
    }

}
