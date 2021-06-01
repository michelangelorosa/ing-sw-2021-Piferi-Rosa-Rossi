package it.polimi.ingsw.ControllerTest;

import static org.junit.Assert.*;

import it.polimi.ingsw.Controller.ControllerClasses.ActionController;
import it.polimi.ingsw.CommonTestMethods;
import it.polimi.ingsw.Controller.Actions.*;
import it.polimi.ingsw.Model.Enums.PlayerStatus;
import it.polimi.ingsw.Model.GameModel.Game;
import it.polimi.ingsw.Model.MessagesToClient.*;

import org.junit.Test;

/**
 * Unit Test for EndTurn Class.
 */
public class EndTurnTest {
    ActionController actionController = new ActionController();
    ResetWarehouse resetWarehouse = actionController.getResetWarehouse();
    ChooseProductionOutput chooseProductionOutput = actionController.getChooseProductionOutput();
    ChooseCardSlot chooseCardSlot = actionController.getChooseCardSlot();
    Game game = actionController.getGame();
    MessageToClient messageToClient;

    /**
     * Constructor and Getter test for EndTurn Class.
     */
    @Test
    public void constructorTest() {
        EndTurn endTurn = new EndTurn();
        assertEquals(ActionType.END_TURN, endTurn.getActionType());
    }

    /**
     * Test for "doAction" and "messagePrepare" methods in EndTurn Class.
     */
    @Test
    public void doActionTest() {
        CommonTestMethods.gameInitOne(game);
        game.getPlayers().get(2).setStatus(PlayerStatus.IDLE);
        assertEquals("Zero", game.getCurrentPlayerNickname());
        String response;

        actionController.getGame().getCurrentPlayer().addPossibleAction(ActionType.END_TURN);

        EndTurn endTurn = new EndTurn();
        response = endTurn.doAction(actionController);
        assertEquals("SUCCESS", response);
        assertEquals("One", game.getCurrentPlayerNickname());
        messageToClient = endTurn.messagePrepare(actionController);

        assertTrue(messageToClient instanceof EndTurnMessage);
        assertEquals(ActionType.END_TURN, messageToClient.getActionDone());
        assertEquals("SUCCESS", messageToClient.getError());
        assertEquals("Zero", messageToClient.getPlayerNickname());
        assertEquals(ActionType.ACTIVATE_PRODUCTION, messageToClient.getPossibleActions().get(0));
        assertEquals(ActionType.BUY_CARD, messageToClient.getPossibleActions().get(1));
        assertEquals(ActionType.MARKET_CHOOSE_ROW, messageToClient.getPossibleActions().get(2));
        assertEquals(ActionType.ACTIVATE_LEADERCARD, messageToClient.getPossibleActions().get(3));
        assertEquals("One", ((EndTurnMessage)messageToClient).getNextPlayerNickname());

        actionController.getGame().getCurrentPlayer().addPossibleAction(ActionType.END_TURN);

        endTurn.doAction(actionController);
        assertEquals("Three", game.getCurrentPlayerNickname());
        messageToClient = endTurn.messagePrepare(actionController);

        assertTrue(messageToClient instanceof EndTurnMessage);
        assertEquals(ActionType.END_TURN, messageToClient.getActionDone());
        assertEquals("SUCCESS", messageToClient.getError());
        assertEquals("One", messageToClient.getPlayerNickname());
        assertEquals(ActionType.ACTIVATE_PRODUCTION, messageToClient.getPossibleActions().get(0));
        assertEquals(ActionType.BUY_CARD, messageToClient.getPossibleActions().get(1));
        assertEquals(ActionType.MARKET_CHOOSE_ROW, messageToClient.getPossibleActions().get(2));
        assertEquals(ActionType.ACTIVATE_LEADERCARD, messageToClient.getPossibleActions().get(3));
        assertEquals("Three", ((EndTurnMessage)messageToClient).getNextPlayerNickname());

        actionController.getGame().getCurrentPlayer().addPossibleAction(ActionType.END_TURN);

        endTurn.doAction(actionController);
        assertEquals("Zero", game.getCurrentPlayerNickname());
        messageToClient = endTurn.messagePrepare(actionController);

        assertTrue(messageToClient instanceof EndTurnMessage);
        assertEquals(ActionType.END_TURN, messageToClient.getActionDone());
        assertEquals("SUCCESS", messageToClient.getError());
        assertEquals("Three", messageToClient.getPlayerNickname());
        assertEquals(ActionType.ACTIVATE_PRODUCTION, messageToClient.getPossibleActions().get(0));
        assertEquals(ActionType.BUY_CARD, messageToClient.getPossibleActions().get(1));
        assertEquals(ActionType.MARKET_CHOOSE_ROW, messageToClient.getPossibleActions().get(2));
        assertEquals(ActionType.ACTIVATE_LEADERCARD, messageToClient.getPossibleActions().get(3));
        assertEquals("Zero", ((EndTurnMessage)messageToClient).getNextPlayerNickname());
    }
}
