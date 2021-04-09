package it.polimi.ingsw.Model;

import java.util.ArrayList;

public class FaithTrack {
    private final FaithCell[] cells;
    private final VaticanReportSection ONE;
    private final VaticanReportSection TWO;
    private final VaticanReportSection THREE;

    private boolean popeSpaceONE;
    private boolean popeSpaceTWO;
    private boolean popeSpaceTHREE;

    public FaithTrack(){
        this.cells = JSONReader.ReadFaithCells();
        this.popeSpaceONE = true;
        this.popeSpaceTWO = true;
        this.popeSpaceTHREE = true;
        this.ONE = new VaticanReportSection(5, 8, 2);
        this.TWO = new VaticanReportSection(12, 16, 3);
        this.THREE = new VaticanReportSection(19, 24, 4);

    }

    public FaithCell[] getCells() {
        return cells;
    }

    /**
     * In this method one player goes ahead in the Faith Track
     */
    public boolean stepAhead(Player player, ArrayList <Player> players, int steps){
        if(player.getTurnPosition() == 0) players.get(0).stepAhead(steps);
        else if(player.getTurnPosition() == 1) players.get(1).stepAhead(steps);
        else if(player.getTurnPosition() == 2) players.get(2).stepAhead(steps);
        else if(player.getTurnPosition() == 3) players.get(3).stepAhead(steps);
        else
            System.err.println("Error: No player corresponds to position "+player.getTurnPosition());
        return checkFinishedTrack(players);
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
     * If any player arrive on the last cell of the faith track, he wins the game
     */
    public boolean checkFinishedTrack(ArrayList <Player> players){
        for(int i = 0; i < players.size(); i++){
            if(players.get(i).victory()) return true;
        }
        return false;
    }

    public int getFinalPoints(Player player, ArrayList <Player> players, int points){
        int victoryPoints = 0;
        if(player.getTurnPosition() == 0) victoryPoints = points + cells[players.get(0).getFaithTrackPosition()].getVictoryPoints();
        else if(player.getTurnPosition() == 1) victoryPoints = points + cells[players.get(1).getFaithTrackPosition()].getVictoryPoints();
        else if(player.getTurnPosition() == 2) victoryPoints = points + cells[players.get(2).getFaithTrackPosition()].getVictoryPoints();
        else if(player.getTurnPosition() == 3) victoryPoints = points + cells[players.get(3).getFaithTrackPosition()].getVictoryPoints();
        return victoryPoints;
    }

    public void popeSpaceSector(ArrayList <Player> players){

        for(int i = 0; i < players.size(); i++){
            if(players.get(i).getFaithTrackPosition() >= ONE.getEnd() && popeSpaceONE == true){
                popeSpaceONE = false;
                players.get(i).addVictoryPoints(ONE.getPoints());
                for(int j = 0; j < players.size(); j++){
                    if(i != j && players.get(j).getFaithTrackPosition() <= ONE.getEnd() && players.get(j).getFaithTrackPosition() >= ONE.getBegin() && players.get(j).getStatus() == PlayerStatus.IN_GAME){
                        players.get(j).addVictoryPoints(ONE.getPoints());
                    }
                }
            }

            if(players.get(i).getFaithTrackPosition() >= TWO.getEnd() && popeSpaceTWO == true){
                popeSpaceTWO = false;
                players.get(i).addVictoryPoints(TWO.getPoints());
                for(int j = 0; j < players.size(); j++){
                    if(i != j && players.get(j).getFaithTrackPosition() <= TWO.getEnd() && players.get(j).getFaithTrackPosition() >= TWO.getBegin() && players.get(j).getStatus() == PlayerStatus.IN_GAME){
                        players.get(j).addVictoryPoints(TWO.getPoints());
                    }
                }
            }

            if(players.get(i).getFaithTrackPosition() >= THREE.getEnd() && popeSpaceTHREE == true){
                popeSpaceTHREE = false;
                players.get(i).addVictoryPoints(THREE.getPoints());
                for(int j = 0; j < players.size(); j++){
                    if(i != j && players.get(j).getFaithTrackPosition() <= THREE.getEnd() && players.get(j).getFaithTrackPosition() >= THREE.getBegin() && players.get(j).getStatus() == PlayerStatus.IN_GAME){
                        players.get(j).addVictoryPoints(THREE.getPoints());
                    }
                }
            }
        }
    }
}





