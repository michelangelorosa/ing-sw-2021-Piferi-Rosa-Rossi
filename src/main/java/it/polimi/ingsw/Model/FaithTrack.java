package it.polimi.ingsw.Model;

import java.util.ArrayList;

public class FaithTrack {
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
        this.cells = JSONReader.ReadFaithCells();
        this.popeSpaceONE = true;
        this.popeSpaceTWO = true;
        this.popeSpaceTHREE = true;
        this.ONE = JSONReader.ReadVaticanReportSection()[0];
        this.TWO = JSONReader.ReadVaticanReportSection()[1];
        this.THREE = JSONReader.ReadVaticanReportSection()[2];
        //this.ONE = new VaticanReportSection(5, 8, 2);
        //this.TWO = new VaticanReportSection(12, 16, 3);
        //this.THREE = new VaticanReportSection(19, 24, 4);

    }

    /**Getter for FatithCell*/
    public FaithCell[] getCells() {
        return cells;
    }

    /**
     * In this method all the players except for one, go ahead in the Faith Track.
     * This happens if the player is still on for the game.
     */
    public boolean allAhead(Player player, ArrayList <Player> players, int steps){
        if (player.getTurnPosition() < 0 || player.getTurnPosition() > 3) {
            System.err.println("Error: No player corresponds to position " + player.getTurnPosition());
            return false;
        }
        if(player.getTurnPosition() != 0 && players.get(0).getStatus() == PlayerStatus.IN_GAME) players.get(0).stepAhead(steps);
        if(player.getTurnPosition() != 1 && players.get(1).getStatus() == PlayerStatus.IN_GAME) players.get(1).stepAhead(steps);
        if(player.getTurnPosition() != 2 && players.get(2).getStatus() == PlayerStatus.IN_GAME) players.get(2).stepAhead(steps);
        if(player.getTurnPosition() != 3 && players.get(3).getStatus() == PlayerStatus.IN_GAME) players.get(3).stepAhead(steps);

        return checkFinishedTrack(players);
    }

    /**
     * This method controls if any player arrives at the end of the track
     */
    public boolean checkFinishedTrack(ArrayList <Player> players){
        for(int i = 0; i < players.size(); i++){
            if(players.get(i).endTrack()) return true;
        }
        return false;
    }

    /**At the end of the game this method is used to add the point of the faith cell to thei points*/
    public void addFinalPoints(ArrayList <Player> players){
        int victoryPoints = 0;
        for(Player player : players){
            player.addVictoryPoints(cells[player.getFaithTrackPosition()].getVictoryPoints());
        }
    }

    /**Points relative to the player's position in the FaithTrack*/
    public int getTrackPoints(Player player){
        return player.getVictoryPoints();
    }

    /**Management of points related to PopeSpace*/
    public void popeSpaceSector(ArrayList <Player> players){

        for(int i = 0; i < players.size(); i++){
            if(players.get(i).getFaithTrackPosition() >= ONE.getEnd() && popeSpaceONE == true){
                //Every time a player oversteps a PopeSpace it is deactivated
                popeSpaceONE = false;
                players.get(i).addVictoryPoints(ONE.getPoints());
                //Every player on the relative vatican report section earn points
                for(int j = 0; j < players.size(); j++){
                    if(i != j && players.get(j).getFaithTrackPosition() <= ONE.getEnd() && players.get(j).getFaithTrackPosition() >= ONE.getBegin() && players.get(j).getStatus() == PlayerStatus.IN_GAME){
                        players.get(j).addVictoryPoints(ONE.getPoints());
                    }
                }
            }

            if(players.get(i).getFaithTrackPosition() >= TWO.getEnd() && popeSpaceTWO == true){
                //Every time a player oversteps a PopeSpace it is deactivated
                popeSpaceTWO = false;
                players.get(i).addVictoryPoints(TWO.getPoints());
                //Every player on the relative vatican report section earn points
                for(int j = 0; j < players.size(); j++){
                    if(i != j && players.get(j).getFaithTrackPosition() <= TWO.getEnd() && players.get(j).getFaithTrackPosition() >= TWO.getBegin() && players.get(j).getStatus() == PlayerStatus.IN_GAME){
                        players.get(j).addVictoryPoints(TWO.getPoints());
                    }
                }
            }

            if(players.get(i).getFaithTrackPosition() >= THREE.getEnd() && popeSpaceTHREE == true){
                //Every time a player oversteps a PopeSpace it is deactivated
                popeSpaceTHREE = false;
                players.get(i).addVictoryPoints(THREE.getPoints());
                //Every player on the relative vatican report section earn points
                for(int j = 0; j < players.size(); j++){
                    if(i != j && players.get(j).getFaithTrackPosition() <= THREE.getEnd() && players.get(j).getFaithTrackPosition() >= THREE.getBegin() && players.get(j).getStatus() == PlayerStatus.IN_GAME){
                        players.get(j).addVictoryPoints(THREE.getPoints());
                    }
                }
            }
        }
    }
}





