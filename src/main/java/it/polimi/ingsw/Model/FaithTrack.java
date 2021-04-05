package it.polimi.ingsw.Model;

public class FaithTrack {
    private final FaithCell[] cells;
    private int playerONE;
    private int playerTWO;
    private int playerTHREE;
    private int playerFOUR;

    private boolean playerONEOnGame;
    private boolean playerTWOOnGame;
    private boolean playerTHREEOnGame;
    private boolean playerFOUROnGame;

    public FaithTrack(){
        this.cells = new FaithCell[25];
        this.playerONE = 0;
        this.playerONEOnGame = false;
        this.playerTWO = 0;
        this.playerTWOOnGame = false;
        this.playerTHREE = 0;
        this.playerTHREEOnGame = false;
        this.playerFOUR = 0;
        this.playerFOUROnGame = false;
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

    public void setPlayerONEOnGame(boolean playerONEOnGame) {
        this.playerONEOnGame = playerONEOnGame;
    }

    public void setPlayerTWOOnGame(boolean playerTWOOnGame) {
        this.playerTWOOnGame = playerTWOOnGame;
    }

    public void setPlayerTHREEOnGame(boolean playerTHREEOnGame) {
        this.playerTHREEOnGame = playerTHREEOnGame;
    }

    public void setPlayerFOUROnGame(boolean playerFOUROnGame) {
        this.playerFOUROnGame = playerFOUROnGame;
    }

    public FaithCell[] getCells() {
        return cells;
    }

    public boolean isPlayerONEOnGame() {
        return playerONEOnGame;
    }

    public boolean isPlayerTWOOnGame() {
        return playerTWOOnGame;
    }

    public boolean isPlayerTHREEOnGame() {
        return playerTHREEOnGame;
    }

    public boolean isPlayerFOUROnGame() {
        return playerFOUROnGame;
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
        if(player == Player.PLAYER_ONE) playerONE+=steps;
        else if(player == Player.PLAYER_TWO) playerTWO+=steps;
        else if(player == Player.PLAYER_THREE) playerTHREE+=steps;
        else if(player == Player.PLAYER_FOUR) playerFOUR+=steps;
        //else ERROR
        return checkFinishedTrack();
    }

    /**
     * In this method all the players except for one, go ahead in the Faith Track.
     * This happens if the player is still on for the game.
     */

    public boolean allAhead(Player player, int steps){
        //if player != 0,1,2,3 ERROR
        if(player != Player.PLAYER_ONE && playerONEOnGame) playerONE+=steps;
        if(player != Player.PLAYER_TWO && playerTWOOnGame) playerTWO+=steps;
        if(player != Player.PLAYER_THREE && playerTHREEOnGame) playerTHREE+=steps;
        if(player != Player.PLAYER_FOUR && playerFOUROnGame) playerFOUR+=steps;

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
        if(playerTWO == 24){
            playerTWO = 24;
            return true;
        }
        if(playerTHREE == 24) return true;
        if(playerFOUR == 24) return true;
        return false;
    }

    public int getFinalPoints(Player player, int points){
        int victoryPoints = 0;
        if(player == Player.PLAYER_ONE) victoryPoints =points + cells[playerONE].getVictoryPoints();
        else if(player == Player.PLAYER_TWO) victoryPoints = points + cells[playerTWO].getVictoryPoints();
        else if(player == Player.PLAYER_THREE) victoryPoints = points + cells[playerTHREE].getVictoryPoints();
        else if(player == Player.PLAYER_FOUR) victoryPoints = points + cells[playerFOUR].getVictoryPoints();
        return victoryPoints;
    }
}
