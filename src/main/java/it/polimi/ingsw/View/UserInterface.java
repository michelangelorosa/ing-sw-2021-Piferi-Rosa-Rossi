package it.polimi.ingsw.View;

import it.polimi.ingsw.Controller.Actions.Action;
import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.Controller.Actions.PayResourceBuyCard;
import it.polimi.ingsw.Controller.Actions.PayResourceProduction;
import it.polimi.ingsw.View.ReducedModel.Game;
import it.polimi.ingsw.View.ReducedModel.RedLeaderCard;
import it.polimi.ingsw.View.ReducedModel.UserInteraction;

import java.util.ArrayList;

/**
 * UserInterface Interface defines methods Overridden by both Cli and Gui classes to be used to get
 * the user input, ready to be sent to the server via the UserInteraction Abstract Class.
 */
public interface UserInterface {

    void nextAction(UserInteraction userInteraction, int i);

    /**
     * Used to get the Server Address and Server Port as input from the player.
     * @return An ArrayList of object.
     *  - First element in the ArrayList -> String containing server address.
     *  - Second element in the ArrayList -> Integer containing server port.
     */
    ArrayList<Object> init();

    /**
     * Used to display a message while the player waits for all the other players to join.
     */
    void waitingForPlayers();

    void displayError(String s);

}
