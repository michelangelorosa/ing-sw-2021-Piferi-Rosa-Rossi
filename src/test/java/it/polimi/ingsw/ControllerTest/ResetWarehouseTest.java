package it.polimi.ingsw.ControllerTest;

import static org.junit.Assert.*;

import it.polimi.ingsw.CommonTestMethods;
import it.polimi.ingsw.Controller.ControllerClasses.ActionController;
import it.polimi.ingsw.Controller.Actions.*;
import it.polimi.ingsw.Model.Enums.ResourceType;
import it.polimi.ingsw.Model.GameModel.Game;
import it.polimi.ingsw.Model.GameModel.ResourceStack;
import it.polimi.ingsw.Model.GameModel.Warehouse;
import it.polimi.ingsw.Model.MessagesToClient.MessageToClient;
import it.polimi.ingsw.Model.MessagesToClient.ResetWarehouseMessage;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ResetWarehouseTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    ActionController actionController = new ActionController();

    Game game = actionController.getGame();
    ResetWarehouse reset = actionController.getResetWarehouse();
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

    /**
     * Test to check if the method "doAction()" work properly in ResetWarehouse Class
     */
    @Test
    public void doActionTest(){
        CommonTestMethods.gameInitOne(game);
        PayResource pay = new PayResourceBuyCard(false, 0, ResourceType.SHIELDS);
        PayResource pay2 = new PayResourceBuyCard(true, 0, ResourceType.NONE);
        PayResource pay3 = new PayResourceBuyCard(false, 0, ResourceType.STONES);

        ResourceStack stack = new ResourceStack(1,3,0,2);
        game.getCurrentPlayer().getBoard().getResourceManager().setTemporaryResourcesToPay(backupResources);
        CommonTestMethods.giveResourcesToPlayer(game.getCurrentPlayer(), 1,2,1,ResourceType.SHIELDS, ResourceType.COINS, ResourceType.SERVANTS, stack);
        reset.setBackupWarehouse(game.getCurrentPlayer().getBoard().getResourceManager().getWarehouse().copyWarehouse());
        reset.setBackupResources(backupResources.copyStack());

        actionController.getGame().getCurrentPlayer().addPossibleAction(ActionType.PAY_RESOURCE_CARD);

        assertEquals("HasToPay", pay.doAction(actionController));
        assertEquals("1 0 0 0", game.getCurrentPlayer().getBoard().getResourceManager().getTemporaryResourcesToPay().toString());
        assertEquals("No SHIELDS left in Strongbox", pay.doAction(actionController));
        assertEquals("This type of resource is not needed", pay3.doAction(actionController));
        assertEquals("SUCCESS", pay2.doAction(actionController));
        assertEquals("0 NONE",game.getCurrentPlayer().getBoard().getResourceManager().getWarehouseDepots()[0].getStoredResources() + " " + game.getCurrentPlayer().getBoard().getResourceManager().getWarehouseDepots()[0].getResourceType());
        assertEquals("2 COINS",game.getCurrentPlayer().getBoard().getResourceManager().getWarehouseDepots()[1].getStoredResources() + " " + game.getCurrentPlayer().getBoard().getResourceManager().getWarehouseDepots()[1].getResourceType());
        assertEquals("1 SERVANTS",game.getCurrentPlayer().getBoard().getResourceManager().getWarehouseDepots()[2].getStoredResources() + " " + game.getCurrentPlayer().getBoard().getResourceManager().getWarehouseDepots()[2].getResourceType());
        assertEquals("0 0 0 0", game.getCurrentPlayer().getBoard().getResourceManager().getTemporaryResourcesToPay().toString());
        assertEquals("SUCCESS", reset.doAction(actionController));
        messageToClient = reset.messagePrepare(actionController);

        assertTrue(messageToClient instanceof ResetWarehouseMessage);
        assertEquals(ActionType.RESET_WAREHOUSE, messageToClient.getActionDone());
        assertEquals("SUCCESS", messageToClient.getError());
        assertEquals(game.getCurrentPlayerNickname(), messageToClient.getPlayerNickname());
        assertEquals(ActionType.ADD_RESOURCE, messageToClient.getPossibleActions().get(0));
        assertEquals(ActionType.RESET_WAREHOUSE, messageToClient.getPossibleActions().get(1));
        assertEquals(ActionType.SWITCH_DEPOT, messageToClient.getPossibleActions().get(2));
        assertEquals(ActionType.END_MARKET, messageToClient.getPossibleActions().get(3));
        assertEquals(game.getCurrentPlayer().getBoard().getResourceManager().getWarehouse().isFull(), ((ResetWarehouseMessage)messageToClient).getWarehouse().isFull());
        assertEquals(game.getCurrentPlayer().getBoard().getResourceManager().getTemporaryResourcesToPay().toString(), ((ResetWarehouseMessage)messageToClient).getTemporaryResources().toString());


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
