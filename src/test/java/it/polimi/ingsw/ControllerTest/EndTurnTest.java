package it.polimi.ingsw.ControllerTest;

import static org.junit.Assert.*;

import it.polimi.ingsw.Controller.ControllerClasses.ActionController;
import it.polimi.ingsw.CommonTestMethods;
import it.polimi.ingsw.Controller.Actions.*;
import it.polimi.ingsw.Model.Enums.GameType;
import it.polimi.ingsw.Model.Enums.PlayerStatus;
import it.polimi.ingsw.Model.GameModel.Game;
import it.polimi.ingsw.Model.GameModel.Player;
import it.polimi.ingsw.Model.MessagesToClient.*;

import org.junit.Test;

/**
 * Unit Test for EndTurn Class.
 */
public class EndTurnTest {
    ActionController actionController = new ActionController();
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

    /**
     * Test for "doAction" and "messagePrepare" methods in EndTurn Class when someone finished.
     */
    @Test
    public void doActionWhenSomeoneFinished() {
        CommonTestMethods.gameInitOne(actionController.getGame());

        actionController.getGame().getCurrentPlayer().addPossibleAction(ActionType.END_TURN);

        EndTurn action = new EndTurn();
        actionController.getGame().getPlayers().get(0).setFaithTrackPosition(14);
        actionController.getGame().getPlayers().get(1).setFaithTrackPosition(12);
        actionController.getGame().getPlayers().get(2).setFaithTrackPosition(24);
        actionController.getGame().getPlayers().get(3).setFaithTrackPosition(2);

        action.doAction(actionController);
        actionController.getGame().getCurrentPlayer().addPossibleAction(ActionType.END_TURN);
        action.doAction(actionController);
        actionController.getGame().getCurrentPlayer().addPossibleAction(ActionType.END_TURN);
        action.doAction(actionController);
        actionController.getGame().getCurrentPlayer().addPossibleAction(ActionType.END_TURN);
        action.doAction(actionController);

        MessageToClient message = action.messagePrepare(actionController);
        assertTrue(message instanceof FinalPointsMessage);
    }

    /**
     * Test for "doAction" and "messagePrepare" methods in EndTurn Class in single player mode.
     */
    @Test
    public void doActionSingleplayer() {
        game.getPlayers().add(new Player("pippo", 0, true));
        game.getPlayers().add(new Player("Lorenzo il Magnifico", 1, false));
        game.setGameType(GameType.SINGLEPLAYER);
        game.getPlayers().get(0).addPossibleAction(ActionType.END_TURN);

        EndTurn action = new EndTurn();
        action.doAction(actionController);
        messageToClient = action.messagePrepare(actionController);
        assertTrue(messageToClient instanceof EndTurnSingleplayerMessage);
        assertEquals("SINGLEPLAYER", messageToClient.getError());
        assertEquals(game.getDevelopmentCardTable(), ((EndTurnSingleplayerMessage) messageToClient).getTable());
    }
}
