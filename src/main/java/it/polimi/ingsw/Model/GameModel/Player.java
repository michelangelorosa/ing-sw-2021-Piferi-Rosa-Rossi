package it.polimi.ingsw.Model.GameModel;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.Model.Enums.PlayerStatus;
import it.polimi.ingsw.View.ReducedModel.RedLeaderCard;
import it.polimi.ingsw.View.ReducedModel.RedPopeTileClass;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Player Class defines attributes and methods of a player.
 * <p><b>Attributes:</b></p>>
 * <ul>
 *     <li>String "nickname": the player's nickname</li>
 *     <li>int "turnPosition": the player's position in the defined turn changing mechanism</li>
 *     <li>boolean "Inkwell": if the player is the first to play, he has the inkwell</li>
 *     <li>PlayerStatus "status": the player's status indicating if he's playing, temporary disabled or has finished the game</li>
 *     <li>int "victoryPoints": the player's number of victory points</li>
 *     <li>int "faithTrackPosition": indicates the player's position inside the faithTrack</li>
 *     <li>Board "board": the player's personal board containing all objects of the game</li>
 *     <li>PopeTileClass[] "popeTiles": all popeTiles owned by the player</li>
 *     <li>ArrayList&lt;ActionType&gt; "possibleActions": list of all possible actions the player can perform at a certain moment.</li>
 * </ul>
 * @author redrick99
 */
public class Player implements Serializable {
    private final String nickname;
    private final int turnPosition;
    private final boolean Inkwell;
    private PlayerStatus status;
    private int victoryPoints;
    private int faithTrackPosition;
    private final Board board;
    private final PopeTileClass[] popeTiles;
    private final ArrayList<ActionType> possibleActions;

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
        this.board = new Board();
        this.popeTiles = new PopeTileClass[3];
        this.popeTiles[0] = new PopeTileClass(2);
        this.popeTiles[1] = new PopeTileClass(3);
        this.popeTiles[2] = new PopeTileClass(4);
        this.possibleActions = new ArrayList<>();
    }

    /**
     * Getter for "popeTiles" attribute.
     */
    public PopeTileClass[] getPopeTiles() {
        return popeTiles;
    }

    /**
     * Getter for "nickname" attribute.
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Getter for "turnPosition" attribute.
     */
    public int getTurnPosition() {
        return turnPosition;
    }

    /**
     * Getter for "Inkwell" attribute.
     */
    public boolean hasInkwell() {
        return Inkwell;
    }

    /**
     * Getter for "status" attribute.
     */
    public PlayerStatus getStatus() {
        return status;
    }

    /**
     * Setter for "status" attribute.
     */
    public void setStatus(PlayerStatus status) {
        this.status = status;
    }

    /**
     * Getter for "victoryPoints" attribute.
     */
    public int getVictoryPoints() {
        return victoryPoints;
    }

    /**
     * Getter for "faithTrackPosition" attribute.
     */
    public int getFaithTrackPosition() {
        return faithTrackPosition;
    }

    /**
     * Setter for "faithTrackPosition" attribute.
     */
    public void setFaithTrackPosition(int faithTrackPosition){
        this.faithTrackPosition = faithTrackPosition;
    }
    /**
     * Getter for "board" attribute in Player Class.
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Getter for "possibleActions" attribute.
     */
    public ArrayList<ActionType> getPossibleActions() {
        return possibleActions;
    }

    /**
     * Removes all ActionTypes inside "possibleActions" attribute.
     */
    public void clearPossibleActions() {
        ActionType type = null;
        ActionType type2 = null;

        if(this.canDo(ActionType.ACTIVATE_LEADERCARD))
            type = ActionType.ACTIVATE_LEADERCARD;

        if(this.canDo(ActionType.DELETE_LEADERCARD))
            type2 = ActionType.DELETE_LEADERCARD;

        if(this.possibleActions.size() > 0)
            this.possibleActions.clear();

        if(type != null)
            this.possibleActions.add(type);
        if(type2 != null)
            this.possibleActions.add(type2);
    }

    /**
     * Removes one specified ActionType from "possibleActions" attribute.
     * @param type ActionType to remove.
     */
    public void removePossibleAction(ActionType type) {
        if(this.possibleActions.size() > 0)
            this.possibleActions.remove(type);
    }

    /**
     * Adds a give ActionType to "possibleActions" attribute.
     * @param type ActionType to be added.
     */
    public void addPossibleAction(ActionType type) {
        if(!this.possibleActions.contains(type))
            this.possibleActions.add(type);
    }

    /**
     * Checks if the player can perform can perform a give action by searching for
     * a given ActionType inside "possibleActions" attribute.
     * @param type ActionType of the action the player is trying to perform.
     * @return true if the given ActionType is found inside "possibleActions" attribute.
     */
    public boolean canDo(ActionType type) {
        return this.possibleActions.contains(type);
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

    /**
     * Checks if the player meets requirements to finish the game.
     */
    public boolean hasFinished() {
        return this.getBoard().getDevelopmentCardSlots().countAllCards() >= 7 || this.getFaithTrackPosition() >= 24;
    }

    /**
     * Counts the player's final victory points.
     */
    public void countFinalVictoryPoints() {
        this.victoryPoints += this.getBoard().getDevelopmentCardSlots().totalPoints();
    }

    /**Method for converting model classes to view classes*/
    public it.polimi.ingsw.View.ReducedModel.Player toView() {
        it.polimi.ingsw.View.ReducedModel.Player player = new it.polimi.ingsw.View.ReducedModel.Player(this.nickname, this.turnPosition, this.Inkwell);
        player.setVictoryPoints(this.victoryPoints);
        player.setFaithTrackPosition(this.faithTrackPosition);
        player.setBasicProduction(this.board.getBasicProduction());
        player.setWarehouse(this.board.getResourceManager().getWarehouse());
        player.setStrongbox(this.board.getResourceManager().getStrongbox());
        player.setSlots(this.board.getDevelopmentCardSlots());

        RedPopeTileClass[] popeTileClasses = new RedPopeTileClass[3];
        for(int i = 0; i < 3; i++) {
            popeTileClasses[i] = this.popeTiles[i];
        }
        player.setPopeTiles(popeTileClasses);

        RedLeaderCard[] leaderCards = new RedLeaderCard[2];
        leaderCards[0] = this.getBoard().getLeaderCards()[0];
        leaderCards[1] = this.getBoard().getLeaderCards()[1];

        player.setLeaderCards(leaderCards);
        player.setPossibleActions(this.possibleActions);

        return player;
    }

    /**
     * Checks if the player was paying or adding resources.
     * @return True if the player was paying or adding resource.
     */
    public boolean wasPayingOrAdding() {
       return this.possibleActions.contains(ActionType.ADD_RESOURCE) || this.possibleActions.contains(ActionType.CHOOSE_CARD_SLOT) || this.possibleActions.contains(ActionType.CHOOSE_PRODUCTION_OUTPUT)
               || this.possibleActions.contains(ActionType.PAY_RESOURCE_CARD) || this.possibleActions.contains(ActionType.PAY_RESOURCE_PRODUCTION) || this.possibleActions.contains(ActionType.CHOOSE_LEADER_CARD);
    }

    /**
     * Prepares the player in case he disconnected from the game.
     */
    public void disconnectionPrepareTurn() {
        clearPossibleActions();
        if(!this.possibleActions.contains(ActionType.ACTIVATE_LEADERCARD))
            this.possibleActions.add(ActionType.ACTIVATE_LEADERCARD);

        this.possibleActions.add(ActionType.ACTIVATE_PRODUCTION);
        this.possibleActions.add(ActionType.BUY_CARD);
        this.possibleActions.add(ActionType.MARKET_CHOOSE_ROW);

        if(!this.possibleActions.contains(ActionType.DELETE_LEADERCARD))
            this.possibleActions.add(ActionType.DELETE_LEADERCARD);
    }

}