package it.polimi.ingsw.Model.GameModel;

import it.polimi.ingsw.Model.Enums.PlayerStatus;
import it.polimi.ingsw.Model.Enums.PopeTile;
import it.polimi.ingsw.View.ReducedModel.RedFaithTrack;
import it.polimi.ingsw.View.ReducedModel.RedVaticanReportSection;

import java.util.ArrayList;

public class FaithTrack extends RedFaithTrack {

    public FaithTrack() {
        super();
        this.popeSpaceONE = true;
        this.popeSpaceTWO = true;
        this.popeSpaceTHREE = true;
        //this.ONE = new VaticanReportSection(5, 8, 2);
        //this.TWO = new VaticanReportSection(12, 16, 3);
        //this.THREE = new VaticanReportSection(19, 24, 4);
    }

    public FaithTrack(VaticanReportSection ONE, VaticanReportSection TWO, VaticanReportSection THREE) {
        super(ONE, TWO, THREE);
        this.popeSpaceONE = true;
        this.popeSpaceTWO = true;
        this.popeSpaceTHREE = true;
    }

    public FaithTrack(ArrayList<RedVaticanReportSection> sections) {
        super(sections);
        this.popeSpaceONE = true;
        this.popeSpaceTWO = true;
        this.popeSpaceTHREE = true;
    }

    /**
     * Getter for FatithCell
     */
    public FaithCell[] getCells() {
        return (FaithCell[]) cells;
    }

    public VaticanReportSection getONE() {
        return (VaticanReportSection) ONE;
    }

    public VaticanReportSection getTWO() {
        return (VaticanReportSection) TWO;
    }

    public VaticanReportSection getTHREE() {
        return (VaticanReportSection) THREE;
    }

    public void setPopeSpaceONE(boolean popeSpaceONE) {
        this.popeSpaceONE = popeSpaceONE;
    }

    public void setPopeSpaceTWO(boolean popeSpaceTWO) {
        this.popeSpaceTWO = popeSpaceTWO;
    }

    public void setPopeSpaceTHREE(boolean popeSpaceTHREE) {
        this.popeSpaceTHREE = popeSpaceTHREE;
    }

    /**
     * In this method all the players except for one, go ahead in the Faith Track.
     * This happens if the player is still on for the game.
     */
    public boolean allAhead(Player player, ArrayList<Player> players, int steps) {
        if (player.getTurnPosition() < 0 || player.getTurnPosition() > 3) {
            System.err.println("Error: No player corresponds to position " + player.getTurnPosition());
            return false;
        }
        if (player.getTurnPosition() != 0 && players.get(0).getStatus() == PlayerStatus.IN_GAME)
            players.get(0).stepAhead(steps);
        if (player.getTurnPosition() != 1 && players.get(1).getStatus() == PlayerStatus.IN_GAME)
            players.get(1).stepAhead(steps);
        if (player.getTurnPosition() != 2 && players.get(2).getStatus() == PlayerStatus.IN_GAME)
            players.get(2).stepAhead(steps);
        if (player.getTurnPosition() != 3 && players.get(3).getStatus() == PlayerStatus.IN_GAME)
            players.get(3).stepAhead(steps);

        return checkFinishedTrack(players);
    }

    /**
     * This method controls if any player arrives at the end of the track
     */
    public boolean checkFinishedTrack(ArrayList<Player> players) {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).endTrack()) return true;
        }
        return false;
    }

    /**
     * At the end of the game this method is used to add the point of the faith cell to thei points
     */
    public void addFinalPoints(ArrayList<Player> players) {
        int victoryPoints = 0;
        for (Player player : players) {
            player.addVictoryPoints(cells[player.getFaithTrackPosition()].getVictoryPoints());
        }
    }

    /**
     * Management of points related to PopeSpace
     */
    public void popeSpaceSector(ArrayList<Player> players) {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getFaithTrackPosition() >= ONE.getEnd() && popeSpaceONE) {
                //Every time a player oversteps a PopeSpace it is deactivated
                popeSpaceONE = false;
                players.get(i).addVictoryPoints(ONE.getPoints());
                players.get(i).getPopeTiles()[0].setPopeTile(PopeTile.UP);
                //Every player on the relative vatican report section earn points
                for (int j = 0; j < players.size(); j++) {
                    if (i != j && players.get(j).getFaithTrackPosition() <= ONE.getEnd() && players.get(j).getFaithTrackPosition() >= ONE.getBegin() && players.get(j).getStatus() == PlayerStatus.IN_GAME) {
                        players.get(j).addVictoryPoints(ONE.getPoints());
                        players.get(j).getPopeTiles()[0].setPopeTile(PopeTile.UP);
                    } else if(i != j){
                        players.get(j).getPopeTiles()[0].setPopeTile(PopeTile.No);
                    }
                }
            }

            if (players.get(i).getFaithTrackPosition() >= TWO.getEnd() && popeSpaceTWO) {
                //Every time a player oversteps a PopeSpace it is deactivated
                popeSpaceTWO = false;
                players.get(i).addVictoryPoints(TWO.getPoints());
                players.get(i).getPopeTiles()[1].setPopeTile(PopeTile.UP);
                //Every player on the relative vatican report section earn points
                for (int j = 0; j < players.size(); j++) {
                    if (i != j && players.get(j).getFaithTrackPosition() <= TWO.getEnd() && players.get(j).getFaithTrackPosition() >= TWO.getBegin() && players.get(j).getStatus() == PlayerStatus.IN_GAME) {
                        players.get(j).addVictoryPoints(TWO.getPoints());
                        players.get(j).getPopeTiles()[1].setPopeTile(PopeTile.UP);
                    } else if(i != j){
                        players.get(j).getPopeTiles()[1].setPopeTile(PopeTile.No);
                    }
                }
            }

            if (players.get(i).getFaithTrackPosition() >= THREE.getEnd() && popeSpaceTHREE) {
                //Every time a player oversteps a PopeSpace it is deactivated
                popeSpaceTHREE = false;
                players.get(i).addVictoryPoints(THREE.getPoints());
                players.get(i).getPopeTiles()[2].setPopeTile(PopeTile.UP);
                //Every player on the relative vatican report section earn points
                for (int j = 0; j < players.size(); j++) {
                    if (i != j && players.get(j).getFaithTrackPosition() <= THREE.getEnd() && players.get(j).getFaithTrackPosition() >= THREE.getBegin() && players.get(j).getStatus() == PlayerStatus.IN_GAME) {
                        players.get(j).addVictoryPoints(THREE.getPoints());
                        players.get(j).getPopeTiles()[2].setPopeTile(PopeTile.UP);
                    } else if(i != j){
                        players.get(j).getPopeTiles()[2].setPopeTile(PopeTile.No);
                    }
                }
            }
        }
    }

    /**Method for converting model classes to view classes*/
    public RedFaithTrack toView() {
        return (RedFaithTrack) this;
    }

}





