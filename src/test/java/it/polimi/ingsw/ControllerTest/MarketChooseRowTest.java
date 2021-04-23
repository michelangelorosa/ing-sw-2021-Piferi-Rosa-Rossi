package it.polimi.ingsw.ControllerTest;

import static org.junit.Assert.*;

import it.polimi.ingsw.Model.MessagesToClient.*;
import it.polimi.ingsw.CommonTestMethods;
import it.polimi.ingsw.Controller.Actions.*;
import it.polimi.ingsw.Model.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Unit Test for MarketChooseRow Class.
 */
public class MarketChooseRowTest {
    ResetWarehouse resetWarehouse = new ResetWarehouse();
    ChooseProductionOutput chooseProductionOutput = new ChooseProductionOutput();
    ChooseCardSlot chooseCardSlot = new ChooseCardSlot(0);
    Game game = new Game();
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
     * Test for "canBeApplied" method in MarketChooseRow Class.
     */
    @Test
    public void canBeAppliedTest() {
        MarketChooseRow marketChooseRow = new MarketChooseRow(true, 2);
        assertTrue(marketChooseRow.canBeApplied(game));
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

        CommonTestMethods.givePlayerLeaderCards(game.getCurrentPlayer(), game.getLeaderCards().get(2), game.getLeaderCards().get(3));
        MarketChooseRow marketChooseRow = new MarketChooseRow(true, 0);
        response = marketChooseRow.doAction(game, chooseProductionOutput, chooseCardSlot, resetWarehouse);
        assertEquals("SUCCESS", response);
        assertEquals(1, game.getCurrentPlayer().getFaithTrackPosition());
        assertEquals("2 0 1 0", game.getCurrentPlayer().getBoard().getResourceManager().getTemporaryResourcesToPay().toString());
        messageToClient = marketChooseRow.messagePrepare(game);

        assertTrue(messageToClient instanceof ChoseMarketRowMessage);
        assertEquals(ActionType.MARKET_CHOOSE_ROW, messageToClient.getActionDone());
        assertEquals("SUCCESS", messageToClient.getError());
        assertEquals(game.getCurrentPlayerIndex(), messageToClient.getPlayerPosition());
        assertEquals(ActionType.ADD_RESOURCE, messageToClient.getPossibleActions().get(0));
        assertEquals(ActionType.RESET_WAREHOUSE, messageToClient.getPossibleActions().get(1));
        assertEquals(ActionType.SWITCH_DEPOT, messageToClient.getPossibleActions().get(2));
        assertEquals(ActionType.END_MARKET, messageToClient.getPossibleActions().get(3));
        assertTrue(((ChoseMarketRowMessage)messageToClient).isRow());
        assertEquals(0, ((ChoseMarketRowMessage)messageToClient).getRowOrColumn());


        marketChooseRow = new MarketChooseRow(true, 1);
        game.getCurrentPlayer().getBoard().activateLeaderCard(game.getCurrentPlayer().getBoard().getLeaderCards()[0]);
        response = marketChooseRow.doAction(game, chooseProductionOutput, chooseCardSlot, resetWarehouse);
        assertEquals("SUCCESS", response);
        assertEquals(1, game.getCurrentPlayer().getFaithTrackPosition());
        assertEquals("0 0 2 2", game.getCurrentPlayer().getBoard().getResourceManager().getTemporaryResourcesToPay().toString());
        messageToClient = marketChooseRow.messagePrepare(game);

        assertTrue(messageToClient instanceof ChoseMarketRowMessage);
        assertEquals(ActionType.MARKET_CHOOSE_ROW, messageToClient.getActionDone());
        assertEquals("SUCCESS", messageToClient.getError());
        assertEquals(game.getCurrentPlayerIndex(), messageToClient.getPlayerPosition());
        assertEquals(ActionType.ADD_RESOURCE, messageToClient.getPossibleActions().get(0));
        assertEquals(ActionType.RESET_WAREHOUSE, messageToClient.getPossibleActions().get(1));
        assertEquals(ActionType.SWITCH_DEPOT, messageToClient.getPossibleActions().get(2));
        assertEquals(ActionType.END_MARKET, messageToClient.getPossibleActions().get(3));
        assertTrue(((ChoseMarketRowMessage)messageToClient).isRow());
        assertEquals(1, ((ChoseMarketRowMessage)messageToClient).getRowOrColumn());


        game.getCurrentPlayer().getBoard().activateLeaderCard(game.getCurrentPlayer().getBoard().getLeaderCards()[1]);
        game.getMarket().testMethod();
        response = marketChooseRow.doAction(game, chooseProductionOutput, chooseCardSlot, resetWarehouse);
        assertEquals("Choose Leader Card", response);
        assertEquals(1, game.getCurrentPlayer().getFaithTrackPosition());
        assertEquals("0 0 0 2", game.getCurrentPlayer().getBoard().getResourceManager().getTemporaryResourcesToPay().toString());
        messageToClient = marketChooseRow.messagePrepare(game);

        assertTrue(messageToClient instanceof ChoseMarketRowMessage);
        assertEquals(ActionType.MARKET_CHOOSE_ROW, messageToClient.getActionDone());
        assertEquals("Choose Leader Card", messageToClient.getError());
        assertEquals(game.getCurrentPlayerIndex(), messageToClient.getPlayerPosition());
        assertEquals(ActionType.CHOOSE_LEADER_CARD, messageToClient.getPossibleActions().get(0));
        assertTrue(((ChoseMarketRowMessage)messageToClient).isRow());
        assertEquals(1, ((ChoseMarketRowMessage)messageToClient).getRowOrColumn());

        game.getMarket().testMethod();
        CommonTestMethods.givePlayerLeaderCards(game.getCurrentPlayer(), game.getLeaderCards().get(4), game.getLeaderCards().get(5));
        game.getCurrentPlayer().getBoard().activateLeaderCard(game.getCurrentPlayer().getBoard().getLeaderCards()[0]);
        game.getCurrentPlayer().getBoard().activateLeaderCard(game.getCurrentPlayer().getBoard().getLeaderCards()[1]);

        marketChooseRow = new MarketChooseRow(false, 2);
        response = marketChooseRow.doAction(game, chooseProductionOutput, chooseCardSlot, resetWarehouse);
        assertEquals("SUCCESS", response);
        assertEquals(2, game.getCurrentPlayer().getFaithTrackPosition());
        assertEquals("0 0 1 0", game.getCurrentPlayer().getBoard().getResourceManager().getTemporaryResourcesToPay().toString());
        messageToClient = marketChooseRow.messagePrepare(game);

        assertTrue(messageToClient instanceof ChoseMarketRowMessage);
        assertEquals(ActionType.MARKET_CHOOSE_ROW, messageToClient.getActionDone());
        assertEquals("SUCCESS", messageToClient.getError());
        assertEquals(game.getCurrentPlayerIndex(), messageToClient.getPlayerPosition());
        assertEquals(ActionType.ADD_RESOURCE, messageToClient.getPossibleActions().get(0));
        assertEquals(ActionType.RESET_WAREHOUSE, messageToClient.getPossibleActions().get(1));
        assertEquals(ActionType.SWITCH_DEPOT, messageToClient.getPossibleActions().get(2));
        assertEquals(ActionType.END_MARKET, messageToClient.getPossibleActions().get(3));
        assertFalse(((ChoseMarketRowMessage)messageToClient).isRow());
        assertEquals(2, ((ChoseMarketRowMessage)messageToClient).getRowOrColumn());
    }
}
