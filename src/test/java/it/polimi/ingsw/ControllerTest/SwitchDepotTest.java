package it.polimi.ingsw.ControllerTest;

import static org.junit.Assert.*;

import it.polimi.ingsw.CommonTestMethods;
import it.polimi.ingsw.Controller.Actions.*;
import it.polimi.ingsw.Model.*;
import it.polimi.ingsw.Model.MessagesToClient.MessageToClient;
import it.polimi.ingsw.Model.MessagesToClient.SwitchDepotMessage;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class SwitchDepotTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    SwitchDepot depot = new SwitchDepot(0, 4);
    Warehouse warehouse = new Warehouse();

    MessageToClient messageToClient;

    /**Getter test*/
    @Test
    public void getterTest(){
        assertEquals(ActionType.SWITCH_DEPOT, depot.getActionType());
        assertEquals(0, depot.getFirstDepot());
        assertEquals(4, depot.getSecondDepot());
    }

    /**This test check that the first number is correct*/
    @Test
    public void isCorrect1(){
        assertTrue(depot.isCorrect());

        SwitchDepot depotErr = new SwitchDepot(-1, 2);
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("First Depot index out of bounds.");
        depotErr.isCorrect();
    }

    /**This test check that the first number is correct*/
    @Test
    public void isCorrect2(){
        assertTrue(depot.isCorrect());

        SwitchDepot depotErr = new SwitchDepot(2, 5);
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Second Depot index out of bounds.");
        depotErr.isCorrect();
    }

    /**
     * canBeApplied check if the switch is or is not allowed.
     */
    @Test
    public void canBeAppliedTest(){
        Game game = new Game();
        CommonTestMethods.gameInitOne(game);
        game.getCurrentPlayer().getBoard().getResourceManager().getWarehouse().addResources(2, ResourceType.SHIELDS, warehouse.getWarehouseDepots()[0]);
        game.getCurrentPlayer().getBoard().activateLeaderCard(game.getLeaderCards().get(7));
        game.getCurrentPlayer().getBoard().getResourceManager().getExtraWarehouseDepotOne().addResources(2);
        SwitchDepot switchDepot = new SwitchDepot(3, 1);
        SwitchDepot switchDepot2 = new SwitchDepot(4, 1);
        assertTrue(switchDepot.canBeApplied(game));
        SwitchDepot switchDepot3 = new SwitchDepot(0, 1);
        assertTrue(switchDepot3.canBeApplied(game));
        assertFalse(switchDepot2.canBeApplied(game));
    }

    /**
     * The first switch is not allowed because the extraDepot2 is not active.
     * The second switch is allowed and it returns "SUCCESS"
     * The last switch is not allowed because in the second slot there are COINS and in the extra depot can store only
     * SHIELDS
     */
    @Test
    public void doActionTest(){
        Game game = new Game();
        CommonTestMethods.gameInitOne(game);

        ChooseProductionOutput output = new ChooseProductionOutput();
        ChooseCardSlot cardSlot = new ChooseCardSlot(1);
        ResetWarehouse resetWarehouse = new ResetWarehouse();
        ResourceStack stack = new ResourceStack(0,0,0,0);

        CommonTestMethods.giveResourcesToPlayer(game.getCurrentPlayer(), 2, 2, 1, ResourceType.SHIELDS, ResourceType.COINS, ResourceType.SERVANTS, stack);

        assertEquals(2, game.getCurrentPlayer().getBoard().getResourceManager().getWarehouse().getWarehouseDepots()[0].getStoredResources());
        assertEquals(2, game.getCurrentPlayer().getBoard().getResourceManager().getWarehouse().getWarehouseDepots()[1].getStoredResources());
        assertEquals(1, game.getCurrentPlayer().getBoard().getResourceManager().getWarehouse().getWarehouseDepots()[2].getStoredResources());
        assertEquals(0, game.getCurrentPlayer().getBoard().getResourceManager().getWarehouse().getExtraWarehouseDepot1().getStoredResources());
        assertEquals(0, game.getCurrentPlayer().getBoard().getResourceManager().getWarehouse().getExtraWarehouseDepot2().getStoredResources());

        assertEquals("Can't switch from/to non active depot", depot.doAction(game, output, cardSlot, resetWarehouse));
        messageToClient = depot.messagePrepare(game);

        assertTrue(messageToClient instanceof SwitchDepotMessage);
        assertEquals(ActionType.SWITCH_DEPOT, messageToClient.getActionDone());
        assertEquals("Can't switch from/to non active depot", messageToClient.getError());
        assertEquals(game.getCurrentPlayerIndex(), messageToClient.getPlayerPosition());
        assertEquals(ActionType.ADD_RESOURCE, messageToClient.getPossibleActions().get(0));
        assertEquals(ActionType.RESET_WAREHOUSE, messageToClient.getPossibleActions().get(1));
        assertEquals(ActionType.SWITCH_DEPOT, messageToClient.getPossibleActions().get(2));
        assertEquals(ActionType.END_MARKET, messageToClient.getPossibleActions().get(3));
        assertNull(((SwitchDepotMessage)messageToClient).getWarehouse());


        game.getCurrentPlayer().getBoard().activateLeaderCard(game.getLeaderCards().get(7));
        game.getCurrentPlayer().getBoard().getResourceManager().getExtraWarehouseDepotOne().addResources(2);
        game.getCurrentPlayer().getBoard().activateLeaderCard(game.getLeaderCards().get(6));
        game.getCurrentPlayer().getBoard().getResourceManager().getExtraWarehouseDepotTwo().addResources(1);

        assertEquals(2, game.getCurrentPlayer().getBoard().getResourceManager().getWarehouse().getWarehouseDepots()[0].getStoredResources());
        assertEquals(2, game.getCurrentPlayer().getBoard().getResourceManager().getWarehouse().getWarehouseDepots()[1].getStoredResources());
        assertEquals(1, game.getCurrentPlayer().getBoard().getResourceManager().getWarehouse().getWarehouseDepots()[2].getStoredResources());
        assertEquals(2, game.getCurrentPlayer().getBoard().getResourceManager().getWarehouse().getExtraWarehouseDepot1().getStoredResources());
        assertEquals(1, game.getCurrentPlayer().getBoard().getResourceManager().getWarehouse().getExtraWarehouseDepot2().getStoredResources());

        assertEquals("SUCCESS", depot.doAction(game, output, cardSlot, resetWarehouse));
        messageToClient = depot.messagePrepare(game);

        assertTrue(messageToClient instanceof SwitchDepotMessage);
        assertEquals(ActionType.SWITCH_DEPOT, messageToClient.getActionDone());
        assertEquals("SUCCESS", messageToClient.getError());
        assertEquals(game.getCurrentPlayerIndex(), messageToClient.getPlayerPosition());
        assertEquals(ActionType.ADD_RESOURCE, messageToClient.getPossibleActions().get(0));
        assertEquals(ActionType.RESET_WAREHOUSE, messageToClient.getPossibleActions().get(1));
        assertEquals(ActionType.SWITCH_DEPOT, messageToClient.getPossibleActions().get(2));
        assertEquals(ActionType.END_MARKET, messageToClient.getPossibleActions().get(3));
        assertEquals(game.getCurrentPlayer().getBoard().getResourceManager().getWarehouse(), ((SwitchDepotMessage)messageToClient).getWarehouse());


        assertEquals(1, game.getCurrentPlayer().getBoard().getResourceManager().getWarehouse().getWarehouseDepots()[0].getStoredResources());
        assertEquals(2, game.getCurrentPlayer().getBoard().getResourceManager().getWarehouse().getWarehouseDepots()[1].getStoredResources());
        assertEquals(1, game.getCurrentPlayer().getBoard().getResourceManager().getWarehouse().getWarehouseDepots()[2].getStoredResources());
        assertEquals(2, game.getCurrentPlayer().getBoard().getResourceManager().getWarehouse().getExtraWarehouseDepot1().getStoredResources());
        assertEquals(2, game.getCurrentPlayer().getBoard().getResourceManager().getWarehouse().getExtraWarehouseDepot2().getStoredResources());

        SwitchDepot switchDepot = new SwitchDepot(0, 1);
        assertEquals( "SUCCESS", switchDepot.doAction(game, output, cardSlot, resetWarehouse));
        messageToClient = depot.messagePrepare(game);

        assertTrue(messageToClient instanceof SwitchDepotMessage);
        assertEquals(ActionType.SWITCH_DEPOT, messageToClient.getActionDone());
        assertEquals("SUCCESS", messageToClient.getError());
        assertEquals(game.getCurrentPlayerIndex(), messageToClient.getPlayerPosition());
        assertEquals(ActionType.ADD_RESOURCE, messageToClient.getPossibleActions().get(0));
        assertEquals(ActionType.RESET_WAREHOUSE, messageToClient.getPossibleActions().get(1));
        assertEquals(ActionType.SWITCH_DEPOT, messageToClient.getPossibleActions().get(2));
        assertEquals(ActionType.END_MARKET, messageToClient.getPossibleActions().get(3));
        assertEquals(game.getCurrentPlayer().getBoard().getResourceManager().getWarehouse(), ((SwitchDepotMessage)messageToClient).getWarehouse());

        assertEquals(2, game.getCurrentPlayer().getBoard().getResourceManager().getWarehouse().getWarehouseDepots()[0].getStoredResources());
        assertEquals(1, game.getCurrentPlayer().getBoard().getResourceManager().getWarehouse().getWarehouseDepots()[1].getStoredResources());
        assertEquals(1, game.getCurrentPlayer().getBoard().getResourceManager().getWarehouse().getWarehouseDepots()[2].getStoredResources());
        assertEquals(2, game.getCurrentPlayer().getBoard().getResourceManager().getWarehouse().getExtraWarehouseDepot1().getStoredResources());
        assertEquals(2, game.getCurrentPlayer().getBoard().getResourceManager().getWarehouse().getExtraWarehouseDepot2().getStoredResources());

        SwitchDepot switchDepot1 = new SwitchDepot(3, 1);

        assertEquals("Cannot switch depots", switchDepot1.doAction(game, output, cardSlot, resetWarehouse));
        messageToClient = switchDepot1.messagePrepare(game);

        assertTrue(messageToClient instanceof SwitchDepotMessage);
        assertEquals(ActionType.SWITCH_DEPOT, messageToClient.getActionDone());
        assertEquals("Cannot switch depots", messageToClient.getError());
        assertEquals(game.getCurrentPlayerIndex(), messageToClient.getPlayerPosition());
        assertEquals(ActionType.ADD_RESOURCE, messageToClient.getPossibleActions().get(0));
        assertEquals(ActionType.RESET_WAREHOUSE, messageToClient.getPossibleActions().get(1));
        assertEquals(ActionType.SWITCH_DEPOT, messageToClient.getPossibleActions().get(2));
        assertEquals(ActionType.END_MARKET, messageToClient.getPossibleActions().get(3));
        assertNull(((SwitchDepotMessage)messageToClient).getWarehouse());

        assertEquals(2, game.getCurrentPlayer().getBoard().getResourceManager().getWarehouse().getWarehouseDepots()[0].getStoredResources());
        assertEquals(1, game.getCurrentPlayer().getBoard().getResourceManager().getWarehouse().getWarehouseDepots()[1].getStoredResources());
        assertEquals(1, game.getCurrentPlayer().getBoard().getResourceManager().getWarehouse().getWarehouseDepots()[2].getStoredResources());
        assertEquals(2, game.getCurrentPlayer().getBoard().getResourceManager().getWarehouse().getExtraWarehouseDepot1().getStoredResources());
        assertEquals(2, game.getCurrentPlayer().getBoard().getResourceManager().getWarehouse().getExtraWarehouseDepot2().getStoredResources());
    }

}
