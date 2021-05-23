package it.polimi.ingsw.View;

import it.polimi.ingsw.Controller.Actions.Action;
import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.Controller.Actions.PayResourceBuyCard;
import it.polimi.ingsw.Controller.Actions.PayResourceProduction;
import it.polimi.ingsw.View.ReducedModel.Game;
import it.polimi.ingsw.View.ReducedModel.RedLeaderCard;

import java.util.ArrayList;

/**
 * UserInterface Interface defines methods Overridden by both Cli and Gui classes to be used to get
 * the user input, ready to be sent to the server via the UserInteraction Abstract Class.
 */
public interface UserInterface {
    /**
     * Used to get the Server Address and Server Port as input from the player.
     * @return An ArrayList of object.
     *  - First element in the ArrayList -> String containing server address.
     *  - Second element in the ArrayList -> Integer containing server port.
     */
    ArrayList<Object> init();

    /**
     * Used to get a boolean as input from the player to decide if he wants to start a new game or join
     * an existing one.
     * @return true  -> player wants to start a new game.
     *         false -> player wants to join an existing game.
     */
    boolean startOrJoin();

    /**
     * Used to get the number of players as input from the player who decided to start a new game.
     * @return the number of players decided.
     */
    int numberOfPlayers();

    /**
     * Used to get the UserName of the player as input.
     * @return A String containing the UserName
     */
    String initialInsertName();

    /**
     * Used to display a message while the player waits for all the other players to join.
     */
    void waitingForPlayers();

    /**
     * Used to get a boolean as input from the player indicating whether he's ready to play or not.
     * @return true if the player is ready.
     */
    boolean initialLobby();

    /**
     * Used to get the initial LeaderCards as input from the player.
     * @param leaderCards The 4 leader cards sent by the server to choose from.
     * @return An Action Class message containing the 4 leader cards and methods to assign the 4 leader cards
     * to the player inside the Model's Game.
     * @throws IllegalArgumentException if the number of leader cards in the input ArrayList is != 4
     */
    Action initialChooseLeaderCards(ArrayList<RedLeaderCard> leaderCards) throws IllegalArgumentException;

    /**
     * Used to get the initial Resources as input from the player.
     * @param resources The number of resources the player has to choose.
     * @return An Action Class message containing all resources chosen and the places chosen by the player
     * to put said resources.
     */
    Action initialChooseResources(int resources);

    /**
     * Used at the start of the actual Game turn to make the player choose which action he wants to do when
     * playing the game. All methods below are mapped 1 to 1 with each Action Class message and are used to get
     * a specific Action SUBCLASS which will be passed to this method.
     * @param game Reduced Model game used to print/show the Game played.
     * @return a specific Action Class message Subclass casted to its SuperClass and ready to be notified and sent
     * to the Server.
     * @throws IllegalStateException If the player does not have any possible action inside his
     * ArrayList<ActionType> possibleActions.
     */
    Action actionPicker(Game game) throws IllegalStateException;

    Action activateLeaderCard(Game game);

    Action activateProduction(Game game);

    Action addResource(Game game);

    Action buyCard(Game game);

    Action chooseCardSlot(Game game);

    Action chooseLeaderCard(Game game);

    Action chooseProductionOutput(Game game);

    Action marketChooseRow(Game game);

    Action payResource(Game game);

    PayResourceProduction payResourceProduction(Game game);

    PayResourceBuyCard payResourceBuyCard(Game game);

    Action resetWarehouse();

    Action switchDepot(Game game);

    Action indexToAction(ActionType action, Game game);

    void displayError(String s);

}
