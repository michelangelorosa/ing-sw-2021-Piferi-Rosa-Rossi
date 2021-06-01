package it.polimi.ingsw.ControllerTest;

import static org.junit.Assert.*;

import it.polimi.ingsw.Controller.ControllerClasses.ActionController;
import it.polimi.ingsw.Model.GameModel.Game;
import it.polimi.ingsw.Model.MessagesToClient.*;
import it.polimi.ingsw.CommonTestMethods;
import it.polimi.ingsw.Controller.Actions.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Unit Test for MarketChooseRow Class.
 */
public class MarketChooseRowTest {
    ActionController actionController = new ActionController();
    Game game = actionController.getGame();
    MessageToClient messageToClient;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * Constructor and Getter test for MarketChooseRow Class.
     */
    @Test
    public void constructorTest() {
        MarketChooseRow marketChooseRow = new MarketChooseRow(true, 2);
        assertTrue(marketChooseRow.isCorrect());
        assertEquals(2, marketChooseRow.getRowOrColumnNumber());
    }

    /**
     * Test 1 for "isCorrect" method in MarketChooseRow Class.
     */
    @Test
    public void isCorrectTest1() {
        MarketChooseRow marketChooseRow = new MarketChooseRow(true, -1);
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Row or Column index out of bounds.");
        marketChooseRow.isCorrect();
    }

    /**
     * Test 2 for "isCorrect" method in MarketChooseRow Class.
     */
    @Test
    public void isCorrectTest2() {
        MarketChooseRow marketChooseRow = new MarketChooseRow(true, 3);
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Row or Column index out of bounds.");
        marketChooseRow.isCorrect();
    }

    /**
     * Test 3 for "isCorrect" method in MarketChooseRow Class.
     */
    @Test
    public void isCorrectTest3() {
        MarketChooseRow marketChooseRow = new MarketChooseRow(true, 4);
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Row or Column index out of bounds.");
        marketChooseRow.isCorrect();
    }

    /**
     * Test for "doAction" and "messagePrepare" methods in MarketChooseRow Class.
     */
    @Test
    public void doActionTest() {
        CommonTestMethods.gameInitOne(game);
        game.getMarket().testMethod();
        System.out.println(game.getMarket());

        String response;

        actionController.getGame().getCurrentPlayer().addPossibleAction(ActionType.MARKET_CHOOSE_ROW);

        CommonTestMethods.givePlayerLeaderCards(game.getCurrentPlayer(), game.getLeaderCards().get(2), game.getLeaderCards().get(3));
        MarketChooseRow marketChooseRow = new MarketChooseRow(true, 0);
        response = marketChooseRow.doAction(actionController);
        assertEquals("SUCCESS", response);
        assertEquals(1, game.getCurrentPlayer().getFaithTrackPosition());
        assertEquals("2 0 1 0", game.getCurrentPlayer().getBoard().getResourceManager().getTemporaryResourcesToPay().toString());
        messageToClient = marketChooseRow.messagePrepare(actionController);

        assertTrue(messageToClient instanceof ChoseMarketRowMessage);
        assertEquals(ActionType.MARKET_CHOOSE_ROW, messageToClient.getActionDone());
        assertEquals("SUCCESS", messageToClient.getError());
        assertEquals(game.getCurrentPlayerNickname(), messageToClient.getPlayerNickname());
        assertEquals(ActionType.ADD_RESOURCE, messageToClient.getPossibleActions().get(0));
        assertEquals(ActionType.RESET_WAREHOUSE, messageToClient.getPossibleActions().get(1));
        assertEquals(ActionType.SWITCH_DEPOT, messageToClient.getPossibleActions().get(2));
        assertEquals(ActionType.END_MARKET, messageToClient.getPossibleActions().get(3));

        actionController.getGame().getCurrentPlayer().addPossibleAction(ActionType.MARKET_CHOOSE_ROW);

        marketChooseRow = new MarketChooseRow(true, 1);
        game.getCurrentPlayer().getBoard().activateLeaderCard(game.getCurrentPlayer().getBoard().getLeaderCards()[0]);
        response = marketChooseRow.doAction(actionController);
        assertEquals("SUCCESS", response);
        assertEquals(1, game.getCurrentPlayer().getFaithTrackPosition());
        assertEquals("0 0 2 2", game.getCurrentPlayer().getBoard().getResourceManager().getTemporaryResourcesToPay().toString());
        messageToClient = marketChooseRow.messagePrepare(actionController);

        assertTrue(messageToClient instanceof ChoseMarketRowMessage);
        assertEquals(ActionType.MARKET_CHOOSE_ROW, messageToClient.getActionDone());
        assertEquals("SUCCESS", messageToClient.getError());
        assertEquals(game.getCurrentPlayerNickname(), messageToClient.getPlayerNickname());
        assertEquals(ActionType.ADD_RESOURCE, messageToClient.getPossibleActions().get(0));
        assertEquals(ActionType.RESET_WAREHOUSE, messageToClient.getPossibleActions().get(1));
        assertEquals(ActionType.SWITCH_DEPOT, messageToClient.getPossibleActions().get(2));
        assertEquals(ActionType.END_MARKET, messageToClient.getPossibleActions().get(3));


        actionController.getGame().getCurrentPlayer().addPossibleAction(ActionType.MARKET_CHOOSE_ROW);

        game.getCurrentPlayer().getBoard().activateLeaderCard(game.getCurrentPlayer().getBoard().getLeaderCards()[1]);
        game.getMarket().testMethod();
        response = marketChooseRow.doAction(actionController);
        assertEquals("Choose Leader Card", response);
        assertEquals(1, game.getCurrentPlayer().getFaithTrackPosition());
        assertEquals("0 0 0 2", game.getCurrentPlayer().getBoard().getResourceManager().getTemporaryResourcesToPay().toString());
        messageToClient = marketChooseRow.messagePrepare(actionController);

        assertTrue(messageToClient instanceof ChoseMarketRowMessage);
        assertEquals(ActionType.MARKET_CHOOSE_ROW, messageToClient.getActionDone());
        assertEquals("Choose Leader Card", messageToClient.getError());
        assertEquals(game.getCurrentPlayerNickname(), messageToClient.getPlayerNickname());
        assertEquals(ActionType.CHOOSE_LEADER_CARD, messageToClient.getPossibleActions().get(0));

        game.getMarket().testMethod();
        CommonTestMethods.givePlayerLeaderCards(game.getCurrentPlayer(), game.getLeaderCards().get(4), game.getLeaderCards().get(5));
        game.getCurrentPlayer().getBoard().activateLeaderCard(game.getCurrentPlayer().getBoard().getLeaderCards()[0]);
        game.getCurrentPlayer().getBoard().activateLeaderCard(game.getCurrentPlayer().getBoard().getLeaderCards()[1]);

        actionController.getGame().getCurrentPlayer().addPossibleAction(ActionType.MARKET_CHOOSE_ROW);

        marketChooseRow = new MarketChooseRow(false, 2);
        response = marketChooseRow.doAction(actionController);
        assertEquals("SUCCESS", response);
        assertEquals(2, game.getCurrentPlayer().getFaithTrackPosition());
        assertEquals("0 0 1 0", game.getCurrentPlayer().getBoard().getResourceManager().getTemporaryResourcesToPay().toString());
        messageToClient = marketChooseRow.messagePrepare(actionController);

        assertTrue(messageToClient instanceof ChoseMarketRowMessage);
        assertEquals(ActionType.MARKET_CHOOSE_ROW, messageToClient.getActionDone());
        assertEquals("SUCCESS", messageToClient.getError());
        assertEquals(game.getCurrentPlayerNickname(), messageToClient.getPlayerNickname());
        assertEquals(ActionType.ADD_RESOURCE, messageToClient.getPossibleActions().get(0));
        assertEquals(ActionType.RESET_WAREHOUSE, messageToClient.getPossibleActions().get(1));
        assertEquals(ActionType.SWITCH_DEPOT, messageToClient.getPossibleActions().get(2));
        assertEquals(ActionType.END_MARKET, messageToClient.getPossibleActions().get(3));

        //TODO check ChoseMarketRow Message attributes
    }
}
