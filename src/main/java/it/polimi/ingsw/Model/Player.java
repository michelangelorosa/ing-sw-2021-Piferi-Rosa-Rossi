package it.polimi.ingsw.Model;

import java.io.Serializable;

/**
 * Player Class defines attributes and methods of a player.
 * It consists in six attributes being respectively:
 * <ul>
 *     <li>nickname: the player's nickname</li>
 *     <li>turnPosition: the player's position in the defined turn changing mechanism</li>
 *     <li>Inkwell: if the player is the first to play, he has the inkwell</li>
 *     <li>status: the player's status indicating if he's playing, temporary disabled or has finished the game</li>
 *     <li>victoryPoints: the player's number of victory points</li>
 *     <li>board: the player's personal board containing all objects of the game</li>
 * </ul>
 */
public class Player implements Serializable {
    private static final long serialVersionUID = 0x1;

    private final String nickname;
    private final int turnPosition;
    private final boolean Inkwell;
    private PlayerStatus status;
    private int victoryPoints;
    private int faithTrackPosition;
    private final Board board;

    /**
     * Constructor for Player Class.
     */
    public Player(String nickname, int turnPosition, boolean hasInkwell) {
        this.nickname = nickname;
        this.turnPosition = turnPosition;
        this.Inkwell = hasInkwell;
        this.status = PlayerStatus.IN_GAME;
        this.victoryPoints = 0;
        this.faithTrackPosition = 0;
        board = new Board();
    }

    /**
     * Getter for "nickname" attribute in Player Class.
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Getter for "turnPosition" attribute in Player Class.
     */
    public int getTurnPosition() {
        return turnPosition;
    }

    /**
     * Getter for "Inkwell" attribute in Player Class.
     */
    public boolean hasInkwell() {
        return Inkwell;
    }

    /**
     * Getter for "status" attribute in Player Class.
     */
    public PlayerStatus getStatus() {
        return status;
    }

    /**
     * Setter for "status" attribute in Player Class.
     */
    public void setStatus(PlayerStatus status) {
        this.status = status;
    }

    /**
     * Getter for "victoryPoints" attribute in Player Class.
     */
    public int getVictoryPoints() {
        return victoryPoints;
    }
    /**
     * Getter for "faithTrackPosition" attribute in Player Class.
     */
    public int getFaithTrackPosition() {
        return faithTrackPosition;
    }
    /**
     * Getter for "board" attribute in Player Class.
     */
    public Board getBoard() {
        return board;
    }

    /**
     * This method is used to advance the player on the faith track.
     * @param steps is the number of steps.
     */
    public void stepAhead(int steps){
        this.faithTrackPosition += steps;
        endTrack();
    }

    /**
     * This method is used to see if any player reaches the last cell of the Faith Track
     * @return true if the player wins
     */
    public boolean endTrack(){
        if(this.faithTrackPosition >= 24){
            this.faithTrackPosition = 24;
            return true;
        }
        else return false;
    }
    /**
     * This method adds victory points to the player's current amount.
     * @param victoryPoints is the number of victory points to add.
     */
    public void addVictoryPoints(int victoryPoints) {
        this.victoryPoints += victoryPoints;
    }

    public boolean hasFinished() {
        return this.getBoard().getDevelopmentCardSlots().countAllCards() >= 7 || this.getFaithTrackPosition() >= 25;
    }
}