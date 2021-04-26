package it.polimi.ingsw.View.ReducedModel;

import it.polimi.ingsw.View.ReducedModel.Enums.*;

import java.io.Serializable;
import java.util.ArrayList;

public class FaithTrack implements Serializable {
    private static final long serialVersionUID = 0x1;
    /**A FaithTrack is an array of Faith Cell*/
    private final FaithCell[] cells;
    /**The three vatican report sectios*/
    private final VaticanReportSection ONE;
    private final VaticanReportSection TWO;
    private final VaticanReportSection THREE;

    /**The three pope space, at the and of any report sections*/
    private boolean popeSpaceONE;
    private boolean popeSpaceTWO;
    private boolean popeSpaceTHREE;

    public FaithTrack(){
        this.cells = new FaithCell[25];
        this.popeSpaceONE = true;
        this.popeSpaceTWO = true;
        this.popeSpaceTHREE = true;
        this.ONE = null;
        this.TWO = null;
        this.THREE = null;
    }

    /**Getter for FatithCell*/
    public FaithCell[] getCells() {
        return cells;
    }

    /**Points relative to the player's position in the FaithTrack*/
    public int getTrackPoints(Player player){
        return player.getVictoryPoints();
    }

}





