package it.polimi.ingsw.ControllerTest;

import static org.junit.Assert.*;

import it.polimi.ingsw.Model.*;
import it.polimi.ingsw.CommonTestMethods;
import it.polimi.ingsw.Controller.Actions.*;
import it.polimi.ingsw.Model.MessagesToClient.*;

import org.junit.Test;

/**
 * Unit Test for EndTurn Class.
 */
public class EndTurnTest {
    ResetWarehouse resetWarehouse = new ResetWarehouse();
    ChooseProductionOutput chooseProductionOutput = new ChooseProductionOutput();
    ChooseCardSlot chooseCardSlot = new ChooseCardSlot(0);
    Game game = new Game();
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
     * Test for "isCorrect" method in EndTurn Class.
     */
    @Test
    public void isCorrectTest() {
        EndTurn endTurn = new EndTurn();
        assertTrue(endTurn.isCorrect());
    }

    /**
     * Test for "canBeApplied" method in EndTurn Class.
     */
    @Test
    public void canBeAppliedTest() {
        EndTurn endTurn = new EndTurn();
        assertTrue(endTurn.canBeApplied(game));
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

        EndTurn endTurn = new EndTurn();
        response = endTurn.doAction(game, chooseProductionOutput, chooseCardSlot, resetWarehouse);
        assertEquals("SUCCESS", response);
        assertEquals("One", game.getCurrentPlayerNickname());
        messageToClient = endTurn.messagePrepare(game);

        assertTrue(messageToClient instanceof EndTurnMessage);
        assertEquals(ActionType.END_TURN, messageToClient.getActionDone());
        assertEquals("SUCCESS", messageToClient.getError());
        assertEquals(0, messageToClient.getPlayerPosition());
        assertEquals(ActionType.ACTIVATE_PRODUCTION, messageToClient.getPossibleActions().get(0));
        assertEquals(ActionType.BUY_CARD, messageToClient.getPossibleActions().get(1));
        assertEquals(ActionType.MARKET_CHOOSE_ROW, messageToClient.getPossibleActions().get(2));
        assertEquals(ActionType.ACTIVATE_LEADERCARD, messageToClient.getPossibleActions().get(3));
        assertEquals(1, ((EndTurnMessage)messageToClient).getNextPlayerId());


        endTurn.doAction(game, chooseProductionOutput, chooseCardSlot, resetWarehouse);
        assertEquals("Three", game.getCurrentPlayerNickname());
        messageToClient = endTurn.messagePrepare(game);

        assertTrue(messageToClient instanceof EndTurnMessage);
        assertEquals(ActionType.END_TURN, messageToClient.getActionDone());
        assertEquals("SUCCESS", messageToClient.getError());
        assertEquals(1, messageToClient.getPlayerPosition());
        assertEquals(ActionType.ACTIVATE_PRODUCTION, messageToClient.getPossibleActions().get(0));
        assertEquals(ActionType.BUY_CARD, messageToClient.getPossibleActions().get(1));
        assertEquals(ActionType.MARKET_CHOOSE_ROW, messageToClient.getPossibleActions().get(2));
        assertEquals(ActionType.ACTIVATE_LEADERCARD, messageToClient.getPossibleActions().get(3));
        assertEquals(3, ((EndTurnMessage)messageToClient).getNextPlayerId());


        endTurn.doAction(game, chooseProductionOutput, chooseCardSlot, resetWarehouse);
        assertEquals("Zero", game.getCurrentPlayerNickname());
        messageToClient = endTurn.messagePrepare(game);

        assertTrue(messageToClient instanceof EndTurnMessage);
        assertEquals(ActionType.END_TURN, messageToClient.getActionDone());
        assertEquals("SUCCESS", messageToClient.getError());
        assertEquals(3, messageToClient.getPlayerPosition());
        assertEquals(ActionType.ACTIVATE_PRODUCTION, messageToClient.getPossibleActions().get(0));
        assertEquals(ActionType.BUY_CARD, messageToClient.getPossibleActions().get(1));
        assertEquals(ActionType.MARKET_CHOOSE_ROW, messageToClient.getPossibleActions().get(2));
        assertEquals(ActionType.ACTIVATE_LEADERCARD, messageToClient.getPossibleActions().get(3));
        assertEquals(0, ((EndTurnMessage)messageToClient).getNextPlayerId());
    }
}
