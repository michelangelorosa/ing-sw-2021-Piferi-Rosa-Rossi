package it.polimi.ingsw.View;

import it.polimi.ingsw.Controller.Actions.Action;
import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.Controller.Actions.PayResourceBuyCard;
import it.polimi.ingsw.Controller.Actions.PayResourceProduction;
import it.polimi.ingsw.View.ReducedModel.Game;
import it.polimi.ingsw.View.ReducedModel.LeaderCard;

import java.util.ArrayList;

public interface UserInterface {
    ArrayList<Object> init();

    boolean startOrJoin();

    int numberOfPlayers();

    String initialInsertName();

    void waitingForPlayers();

    boolean initialLobby();

    Action initialChooseLeaderCards(ArrayList<LeaderCard> leaderCards) throws IllegalArgumentException;

    Action initialChooseResources(int resources);

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
