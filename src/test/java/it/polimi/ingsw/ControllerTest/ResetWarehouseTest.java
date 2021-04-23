package it.polimi.ingsw.ControllerTest;

import static org.junit.Assert.*;

import it.polimi.ingsw.CommonTestMethods;
import it.polimi.ingsw.Controller.Actions.*;
import it.polimi.ingsw.Model.*;
import it.polimi.ingsw.Model.MessagesToClient.MessageToClient;
import it.polimi.ingsw.Model.MessagesToClient.ResetWarehouseMessage;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ResetWarehouseTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    ResetWarehouse reset = new ResetWarehouse();
    Warehouse backupWarehouse = new Warehouse();
    Warehouse warehouse = new Warehouse();
    ResourceStack backupResources = new ResourceStack(2,0,0,0);
    ResourceStack backupResources2 = new ResourceStack(2,2,5,7);
    ResourceStack resources = new ResourceStack(0,0,0,0);

    MessageToClient messageToClient;

    /**Method to create a warehouse*/
    public Warehouse create(){
        backupWarehouse.getWarehouseDepots()[0].setResourceType(ResourceType.SHIELDS);
        backupWarehouse.getWarehouseDepots()[1].setResourceType(ResourceType.SERVANTS);
        backupWarehouse.getWarehouseDepots()[2].setResourceType(ResourceType.STONES);

        backupWarehouse.getWarehouseDepots()[0].addResources(3);
        backupWarehouse.getWarehouseDepots()[1].addResources(1);
        backupWarehouse.getWarehouseDepots()[2].addResources(1);

        return backupWarehouse;
    }

    /**Setter and getter test*/
    @Test
    public void setterAndGetterTest(){
        backupWarehouse = create();
        reset.setBackupWarehouse(backupWarehouse);
        reset.setBackupResources(backupResources);

        resources = reset.getBackupResources();
        warehouse = reset.getBackupWarehouse();
        assertEquals(3, warehouse.getWarehouseDepots()[0].getStoredResources());
    }

    /**This test checks that the method returns true everytime*/
    @Test
    public void isCorrectTest(){
        assertTrue(reset.isCorrect());
    }

    /**This test checks that the method returns true everytime*/
    @Test
    public void canBeAppliedTest(){
        Game game = new Game();
        assertTrue(reset.canBeApplied(game));
    }

    /**
     * I created a game, gave to the current player some resources, tried to buy the cards and then check that with the
     * reset the warehouse and the strongbox return at the beginning condition.
     * At the beginning in the warehouse there are 1 shield, 2 coins and 1 servant.
     * The price of the card is 2 shields, the first time I pay a shield from the strongbox, there still a shield to pay
     * so it returns "HasToPay". The second time I try to pay again with a shield from the strongbox but there are no
     * shields in the strongbox, so it returns "No SHIELDS left in Strongbox". Then I try to pay with a stone from the
     * strongbox, but it is not necessary for the card so it returns "This type of resource is not needed". Then I pay
     * with a shield from the warehouse. There are no resources needed than it returns "SUCCESS".
     * The condition of the warehouse is now:
     *      First depot   NOTHING
     *      Second depot  2 COINS
     *      Third depot   1 SERVANT
     * The condition of the strongbox is:
     *      0 SHIELDS
     *      3 SERVANTS
     *      0 COINS
     *      2 STONES
     * If I reset to the beginning condition the resources to pay returns to be 2 SHIELDS, and the conditions of the
     * warehouse and of the strongbox became:
     * Warehouse:
     *      First depot   NOTHING
     *      Second depot  2 COINS
     *      Third depot   1 SERVANT
     * Strongbox:
     *      1 SHIELD
     *      3 SERVANTS
     *      0 COINS
     *      2 STONES
     */
    @Test
    public void doActionTest(){
        Game game = new Game();
        CommonTestMethods.gameInitOne(game);
        PayResource pay = new PayResource(false, 0, ResourceType.SHIELDS);
        PayResource pay2 = new PayResource(true, 0, ResourceType.NONE);
        PayResource pay3 = new PayResource(false, 0, ResourceType.STONES);


        ChooseProductionOutput output = new ChooseProductionOutput();
        ChooseCardSlot cardSlot = new ChooseCardSlot(1);
        ResourceStack stack = new ResourceStack(1,3,0,2);
        game.getCurrentPlayer().getBoard().getResourceManager().setTemporaryResourcesToPay(backupResources);
        CommonTestMethods.giveResourcesToPlayer(game.getCurrentPlayer(), 1,2,1,ResourceType.SHIELDS, ResourceType.COINS, ResourceType.SERVANTS, stack);
        reset.setBackupWarehouse(game.getCurrentPlayer().getBoard().getResourceManager().getWarehouse().copyWarehouse());
        reset.setBackupResources(backupResources.copyStack());
        ResetWarehouse resetWarehouse = reset;

        assertEquals("HasToPay", pay.doAction(game, output, cardSlot, resetWarehouse));
        assertEquals("1 0 0 0", game.getCurrentPlayer().getBoard().getResourceManager().getTemporaryResourcesToPay().toString());
        assertEquals("No SHIELDS left in Strongbox", pay.doAction(game, output, cardSlot, resetWarehouse));
        assertEquals("This type of resource is not needed", pay3.doAction(game, output, cardSlot, resetWarehouse));
        assertEquals("SUCCESS", pay2.doAction(game, output, cardSlot, resetWarehouse));
        assertEquals("0 NONE",game.getCurrentPlayer().getBoard().getResourceManager().getWarehouseDepots()[0].getStoredResources() + " " + game.getCurrentPlayer().getBoard().getResourceManager().getWarehouseDepots()[0].getResourceType());
        assertEquals("2 COINS",game.getCurrentPlayer().getBoard().getResourceManager().getWarehouseDepots()[1].getStoredResources() + " " + game.getCurrentPlayer().getBoard().getResourceManager().getWarehouseDepots()[1].getResourceType());
        assertEquals("1 SERVANTS",game.getCurrentPlayer().getBoard().getResourceManager().getWarehouseDepots()[2].getStoredResources() + " " + game.getCurrentPlayer().getBoard().getResourceManager().getWarehouseDepots()[2].getResourceType());
        assertEquals("0 0 0 0", game.getCurrentPlayer().getBoard().getResourceManager().getTemporaryResourcesToPay().toString());
        assertEquals("SUCCESS", reset.doAction(game, output, cardSlot, resetWarehouse));
        messageToClient = reset.messagePrepare(game);

        assertTrue(messageToClient instanceof ResetWarehouseMessage);
        assertEquals(ActionType.RESET_WAREHOUSE, messageToClient.getActionDone());
        assertEquals("SUCCESS", messageToClient.getError());
        assertEquals(game.getCurrentPlayerIndex(), messageToClient.getPlayerPosition());
        assertEquals(ActionType.ADD_RESOURCE, messageToClient.getPossibleActions().get(0));
        assertEquals(ActionType.RESET_WAREHOUSE, messageToClient.getPossibleActions().get(1));
        assertEquals(ActionType.SWITCH_DEPOT, messageToClient.getPossibleActions().get(2));
        assertEquals(ActionType.END_MARKET, messageToClient.getPossibleActions().get(3));
        assertEquals(game.getCurrentPlayer().getBoard().getResourceManager().getWarehouse(), ((ResetWarehouseMessage)messageToClient).getWarehouse());
        assertEquals(game.getCurrentPlayer().getBoard().getResourceManager().getTemporaryResourcesToPay(), ((ResetWarehouseMessage)messageToClient).getTemporaryResources());


        assertEquals("2 0 0 0", game.getCurrentPlayer().getBoard().getResourceManager().getTemporaryResourcesToPay().toString());
        assertEquals("1 SHIELDS",game.getCurrentPlayer().getBoard().getResourceManager().getWarehouseDepots()[0].getStoredResources() + " " + game.getCurrentPlayer().getBoard().getResourceManager().getWarehouseDepots()[0].getResourceType());
        assertEquals("2 COINS",game.getCurrentPlayer().getBoard().getResourceManager().getWarehouseDepots()[1].getStoredResources() + " " + game.getCurrentPlayer().getBoard().getResourceManager().getWarehouseDepots()[1].getResourceType());
        assertEquals("1 SERVANTS",game.getCurrentPlayer().getBoard().getResourceManager().getWarehouseDepots()[2].getStoredResources() + " " + game.getCurrentPlayer().getBoard().getResourceManager().getWarehouseDepots()[2].getResourceType());
    }

    /**
     * This test check that after emptyBackupResource() the backup resources became 0
     */
    @Test
    public void emptyBackupResourceTest(){
        Game game = new Game();
        CommonTestMethods.gameInitOne(game);
        ResourceStack stack = new ResourceStack(5,3,0,2);
        game.getCurrentPlayer().getBoard().getResourceManager().setTemporaryResourcesToPay(backupResources2);
        CommonTestMethods.giveResourcesToPlayer(game.getCurrentPlayer(), 1,2,0,ResourceType.SHIELDS, ResourceType.COINS, ResourceType.SERVANTS, stack);
        reset.setBackupWarehouse(game.getCurrentPlayer().getBoard().getResourceManager().getWarehouse().copyWarehouse());
        reset.setBackupResources(backupResources2.copyStack());
            assertEquals(2, reset.getBackupResources().getResource(ResourceType.SHIELDS));
            assertEquals(2, reset.getBackupResources().getResource(ResourceType.SERVANTS));
            assertEquals(5, reset.getBackupResources().getResource(ResourceType.COINS));
            assertEquals(7, reset.getBackupResources().getResource(ResourceType.STONES));
        reset.emptyBackupResource();
            assertEquals(0, reset.getBackupResources().getResource(ResourceType.SHIELDS));
            assertEquals(0, reset.getBackupResources().getResource(ResourceType.SERVANTS));
            assertEquals(0, reset.getBackupResources().getResource(ResourceType.COINS));
            assertEquals(0, reset.getBackupResources().getResource(ResourceType.STONES));

    }

}
