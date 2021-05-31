package it.polimi.ingsw.ControllerTest;

import it.polimi.ingsw.Controller.ActionController;
import it.polimi.ingsw.CommonTestMethods;
import it.polimi.ingsw.Controller.Actions.*;
import it.polimi.ingsw.Model.Enums.ResourceType;
import it.polimi.ingsw.Model.GameModel.Game;
import it.polimi.ingsw.Model.MessagesToClient.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

/**
 * Unit Test for AddResource Class.
 */
public class AddResourceTest {
    ActionController actionController = new ActionController();
    Game game = actionController.getGame();

    MessageToClient messageToClient;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * Constructor and Getter test for AddResource Class.
     */
    @Test
    public void constructorTest() {
        AddResource addResource = new AddResource(1, ResourceType.SHIELDS);
        assertEquals(1, addResource.getDepot());
        assertEquals(ResourceType.SHIELDS, addResource.getResourceType());
    }

    /**
     * Test 1 for "isCorrect" method in AddResource Class.
     */
    @Test
    public void isCorrect1Test() {
        AddResource addResource = new AddResource(-1, ResourceType.SHIELDS);
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Depot index out of bounds.");
        addResource.isCorrect();
    }

    /**
     * Test 1 for "isCorrect" method in AddResource Class.
     */
    @Test
    public void isCorrect2Test() {
        AddResource addResource = new AddResource(5, ResourceType.SHIELDS);
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Depot index out of bounds.");
        addResource.isCorrect();
    }

    /**
     * Test 2 for "isCorrect" method in AddResource Class.
     */
    @Test
    public void isCorrect3Test() {
        AddResource addResource = new AddResource(0, ResourceType.NONE);
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("ResourceType can't be NONE.");
        addResource.isCorrect();
    }

    /**
     * Test for "canBeApplied" method in AddResource Class.
     */
    @Test
    public void canBeAppliedTest() {
        AddResource addResource = new AddResource(3, ResourceType.SHIELDS);

        CommonTestMethods.gameInitOne(game);
        assertFalse(addResource.canBeApplied(actionController));

        game.getCurrentPlayer().getBoard().getResourceManager().getWarehouse().activateLeaderDepot(ResourceType.SHIELDS);
        assertTrue(addResource.canBeApplied(actionController));

        game.getCurrentPlayer().getBoard().getResourceManager().getWarehouse().activateLeaderDepot(ResourceType.SERVANTS);
        addResource = new AddResource(4, ResourceType.COINS);
        assertFalse(addResource.canBeApplied(actionController));

        addResource = new AddResource(4, ResourceType.SERVANTS);
        assertTrue(addResource.canBeApplied(actionController));
    }

    /**
     * Test for "doAction" and "messagePrepare" methods in AddResource Class.
     */
    @Test
    public void doActionTest() {
        CommonTestMethods.gameInitOne(game);
        String response;

        actionController.getGame().getCurrentPlayer().addPossibleAction(ActionType.ADD_RESOURCE);

        AddResource addResource = new AddResource(0, ResourceType.SHIELDS);
        response = addResource.doAction(actionController);
        assertEquals("SUCCESS", response);
        messageToClient = addResource.messagePrepare(actionController);

        assertTrue(messageToClient instanceof AddMessage);
        assertEquals(ActionType.ADD_RESOURCE, messageToClient.getActionDone());
        assertEquals("SUCCESS", messageToClient.getError());
        assertEquals(game.getCurrentPlayerNickname(), messageToClient.getPlayerNickname());
        assertEquals(game.getCurrentPlayer().getBoard().getResourceManager().getWarehouse().toView().getWarehouseDepots()[0].getStoredResources(), ((AddMessage) messageToClient).getWarehouse().getWarehouseDepots()[0].getStoredResources());
        assertEquals(game.getCurrentPlayer().getBoard().getResourceManager().getTemporaryResourcesToPay().toView().getResource(ResourceType.SHIELDS), ((AddMessage) messageToClient).getTemporaryResources().getResource(ResourceType.SHIELDS));
        assertEquals(ActionType.ADD_RESOURCE, messageToClient.getPossibleActions().get(0));
        assertEquals(ActionType.SWITCH_DEPOT, messageToClient.getPossibleActions().get(1));
        assertEquals(ActionType.RESET_WAREHOUSE, messageToClient.getPossibleActions().get(2));
        assertEquals(ActionType.END_MARKET, messageToClient.getPossibleActions().get(3));


        response = addResource.doAction(actionController);
        assertEquals("SUCCESS", response);
        messageToClient = addResource.messagePrepare(actionController);

        assertTrue(messageToClient instanceof AddMessage);
        assertEquals(ActionType.ADD_RESOURCE, messageToClient.getActionDone());
        assertEquals("SUCCESS", messageToClient.getError());
        assertEquals(game.getCurrentPlayerNickname(), messageToClient.getPlayerNickname());
        assertEquals(game.getCurrentPlayer().getBoard().getResourceManager().getWarehouse().toView().getWarehouseDepots()[1].getResourceType(), ((AddMessage) messageToClient).getWarehouse().getWarehouseDepots()[1].getResourceType());
        assertEquals(game.getCurrentPlayer().getBoard().getResourceManager().getTemporaryResourcesToPay().toView().getResource(ResourceType.COINS), ((AddMessage) messageToClient).getTemporaryResources().getResource(ResourceType.COINS));
        assertEquals(ActionType.ADD_RESOURCE, messageToClient.getPossibleActions().get(0));
        assertEquals(ActionType.SWITCH_DEPOT, messageToClient.getPossibleActions().get(1));
        assertEquals(ActionType.RESET_WAREHOUSE, messageToClient.getPossibleActions().get(2));
        assertEquals(ActionType.END_MARKET, messageToClient.getPossibleActions().get(3));

        addResource = new AddResource(1, ResourceType.SHIELDS);
        response = addResource.doAction(actionController);
        assertEquals("Can't add SHIELDS to this depot", response);
        messageToClient = addResource.messagePrepare(actionController);

        assertTrue(messageToClient instanceof AddMessage);
        assertEquals(ActionType.ADD_RESOURCE, messageToClient.getActionDone());
        assertEquals("Can't add SHIELDS to this depot", messageToClient.getError());
        assertEquals(game.getCurrentPlayerNickname(), messageToClient.getPlayerNickname());
        assertNull(((AddMessage) messageToClient).getWarehouse());
        assertNull(((AddMessage) messageToClient).getTemporaryResources());
        assertEquals(ActionType.ADD_RESOURCE, messageToClient.getPossibleActions().get(0));
        assertEquals(ActionType.SWITCH_DEPOT, messageToClient.getPossibleActions().get(1));
        assertEquals(ActionType.RESET_WAREHOUSE, messageToClient.getPossibleActions().get(2));
        assertEquals(ActionType.END_MARKET, messageToClient.getPossibleActions().get(3));


        addResource = new AddResource(2, ResourceType.SHIELDS);
        response = addResource.doAction(actionController);
        assertEquals("Can't add SHIELDS to this depot", response);
        messageToClient = addResource.messagePrepare(actionController);

        assertTrue(messageToClient instanceof AddMessage);
        assertEquals(ActionType.ADD_RESOURCE, messageToClient.getActionDone());
        assertEquals("Can't add SHIELDS to this depot", messageToClient.getError());
        assertEquals(game.getCurrentPlayerNickname(), messageToClient.getPlayerNickname());
        assertNull(((AddMessage) messageToClient).getWarehouse());
        assertNull(((AddMessage) messageToClient).getTemporaryResources());
        assertEquals(ActionType.ADD_RESOURCE, messageToClient.getPossibleActions().get(0));
        assertEquals(ActionType.SWITCH_DEPOT, messageToClient.getPossibleActions().get(1));
        assertEquals(ActionType.RESET_WAREHOUSE, messageToClient.getPossibleActions().get(2));
        assertEquals(ActionType.END_MARKET, messageToClient.getPossibleActions().get(3));


        addResource = new AddResource(0, ResourceType.SERVANTS);
        response = addResource.doAction(actionController);
        assertEquals("Can't add SERVANTS to this depot", response);
        messageToClient = addResource.messagePrepare(actionController);

        assertTrue(messageToClient instanceof AddMessage);
        assertEquals(ActionType.ADD_RESOURCE, messageToClient.getActionDone());
        assertEquals("Can't add SERVANTS to this depot", messageToClient.getError());
        assertEquals(game.getCurrentPlayerNickname(), messageToClient.getPlayerNickname());
        assertNull(((AddMessage) messageToClient).getWarehouse());
        assertNull(((AddMessage) messageToClient).getTemporaryResources());
        assertEquals(ActionType.ADD_RESOURCE, messageToClient.getPossibleActions().get(0));
        assertEquals(ActionType.SWITCH_DEPOT, messageToClient.getPossibleActions().get(1));
        assertEquals(ActionType.RESET_WAREHOUSE, messageToClient.getPossibleActions().get(2));
        assertEquals(ActionType.END_MARKET, messageToClient.getPossibleActions().get(3));


        addResource = new AddResource(1, ResourceType.SERVANTS);
        response = addResource.doAction(actionController);
        assertEquals("SUCCESS", response);

        game.getCurrentPlayer().getBoard().getResourceManager().getWarehouse().activateLeaderDepot(ResourceType.SHIELDS);
        addResource = new AddResource(3, ResourceType.SERVANTS);
        response = addResource.doAction(actionController);
        assertEquals("Extra depot is not active or not of given type", response);
        messageToClient = addResource.messagePrepare(actionController);

        assertTrue(messageToClient instanceof AddMessage);
        assertEquals(ActionType.ADD_RESOURCE, messageToClient.getActionDone());
        assertEquals("Extra depot is not active or not of given type", messageToClient.getError());
        assertEquals(game.getCurrentPlayerNickname(), messageToClient.getPlayerNickname());
        assertNull(((AddMessage) messageToClient).getWarehouse());
        assertNull(((AddMessage) messageToClient).getTemporaryResources());
        assertEquals(ActionType.ADD_RESOURCE, messageToClient.getPossibleActions().get(0));
        assertEquals(ActionType.SWITCH_DEPOT, messageToClient.getPossibleActions().get(1));
        assertEquals(ActionType.RESET_WAREHOUSE, messageToClient.getPossibleActions().get(2));
        assertEquals(ActionType.END_MARKET, messageToClient.getPossibleActions().get(3));


        addResource = new AddResource(3, ResourceType.SHIELDS);
        response = addResource.doAction(actionController);
        assertEquals("SUCCESS", response);
        messageToClient = addResource.messagePrepare(actionController);

        assertTrue(messageToClient instanceof AddMessage);
        assertEquals(ActionType.ADD_RESOURCE, messageToClient.getActionDone());
        assertEquals("SUCCESS", messageToClient.getError());
        assertEquals(game.getCurrentPlayerNickname(), messageToClient.getPlayerNickname());
        assertEquals(game.getCurrentPlayer().getBoard().getResourceManager().getWarehouse().toView().getWarehouseDepots()[2].isFromLeaderCardAbility(), ((AddMessage) messageToClient).getWarehouse().getWarehouseDepots()[2].isFromLeaderCardAbility());
        assertEquals(game.getCurrentPlayer().getBoard().getResourceManager().getTemporaryResourcesToPay().toView().toString(), ((AddMessage) messageToClient).getTemporaryResources().toString());
        assertEquals(ActionType.ADD_RESOURCE, messageToClient.getPossibleActions().get(0));
        assertEquals(ActionType.SWITCH_DEPOT, messageToClient.getPossibleActions().get(1));
        assertEquals(ActionType.RESET_WAREHOUSE, messageToClient.getPossibleActions().get(2));
        assertEquals(ActionType.END_MARKET, messageToClient.getPossibleActions().get(3));

        addResource = new AddResource(4, ResourceType.SERVANTS);
        response = addResource.doAction(actionController);
        assertEquals("Extra depot is not active or not of given type", response);
        messageToClient = addResource.messagePrepare(actionController);

        assertTrue(messageToClient instanceof AddMessage);
        assertEquals(ActionType.ADD_RESOURCE, messageToClient.getActionDone());
        assertEquals("Extra depot is not active or not of given type", messageToClient.getError());
        assertEquals(game.getCurrentPlayerNickname(), messageToClient.getPlayerNickname());
        assertNull(((AddMessage) messageToClient).getWarehouse());
        assertNull(((AddMessage) messageToClient).getTemporaryResources());
        assertEquals(ActionType.ADD_RESOURCE, messageToClient.getPossibleActions().get(0));
        assertEquals(ActionType.SWITCH_DEPOT, messageToClient.getPossibleActions().get(1));
        assertEquals(ActionType.RESET_WAREHOUSE, messageToClient.getPossibleActions().get(2));
        assertEquals(ActionType.END_MARKET, messageToClient.getPossibleActions().get(3));
    }
}
