package it.polimi.ingsw.Model;

public class FaithTrack {
    private final FaithCell[] cells;
    private int playerONE;
    private int playerTWO;
    private int playerTHREE;
    private int playerFOUR;

    public FaithTrack(){
        this.cells = JSONReader.ReadFaithCells();
        this.playerONE = 0;
        this.playerTWO = 0;
        this.playerTHREE = 0;
        this.playerFOUR = 0;
    }

    public FaithCell[] getCells() {
        return cells;
    }

    public void setPlayerONE(int playerONE) {
        this.playerONE = playerONE;
    }

    public void setPlayerTWO(int playerTWO) {
        this.playerTWO = playerTWO;
    }

    public void setPlayerTHREE(int playerTHREE) {
        this.playerTHREE = playerTHREE;
    }

    public void setPlayerFOUR(int playerFOUR) {
        this.playerFOUR = playerFOUR;
    }

    public int getPlayerONE() {
        return playerONE;
    }

    public int getPlayerTWO() {
        return playerTWO;
    }

    public int getPlayerTHREE() {
        return playerTHREE;
    }

    public int getPlayerFOUR() {
        return playerFOUR;
    }

    /**
     * In this method one player goes ahead in the Faith Track
     */
    public boolean stepAhead(Player player, int steps){
        if(player.getTurnPosition() == 0) playerONE += steps;
        else if(player.getTurnPosition() == 1) playerTWO += steps;
        else if(player.getTurnPosition() == 2) playerTHREE += steps;
        else if(player.getTurnPosition() == 3) playerFOUR += steps;
        else
            System.err.println("Error: No player corresponds to position "+player.getTurnPosition());
        return checkFinishedTrack();
    }

    /**
     * In this method all the players except for one, go ahead in the Faith Track.
     * This happens if the player is still on for the game.
     */
    public boolean allAhead(Player player, Player[] players, int steps){
        if (player.getTurnPosition() < 0 || player.getTurnPosition() > 3) {
            System.err.println("Error: No player corresponds to position " + player.getTurnPosition());
            return false;
        }
        if(player.getTurnPosition() != 0 && players[0].getStatus() == PlayerStatus.IN_GAME) playerONE += steps;
        if(player.getTurnPosition() != 1 && players[1].getStatus() == PlayerStatus.IN_GAME) playerTWO += steps;
        if(player.getTurnPosition() != 2 && players[2].getStatus() == PlayerStatus.IN_GAME) playerTHREE += steps;
        if(player.getTurnPosition() != 3 && players[3].getStatus() == PlayerStatus.IN_GAME) playerFOUR += steps;

        return checkFinishedTrack();
    }

    /**
     * If any player arrive on the last cell of the faith track, he wins the game
     */
    public boolean checkFinishedTrack(){
        if(playerONE >= 24){
            playerONE = 24;
            return true;
        }
        if(playerTWO >= 24){
            playerTWO = 24;
            return true;
        }
        if(playerTHREE >= 24) {
            playerTHREE = 24;
            return true;
        }
        if(playerFOUR >= 24) {
            playerFOUR = 24;
            return true;
        }
        return false;
    }

    public int getFinalPoints(Player player, int points){
        int victoryPoints = 0;
        if(player.getTurnPosition() == 0) victoryPoints = points + cells[playerONE].getVictoryPoints();
        else if(player.getTurnPosition() == 1) victoryPoints = points + cells[playerTWO].getVictoryPoints();
        else if(player.getTurnPosition() == 2) victoryPoints = points + cells[playerTHREE].getVictoryPoints();
        else if(player.getTurnPosition() == 3) victoryPoints = points + cells[playerFOUR].getVictoryPoints();
        return victoryPoints;
    }
}
