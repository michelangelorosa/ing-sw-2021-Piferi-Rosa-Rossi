package it.polimi.ingsw.ControllerTest;

import static org.junit.Assert.*;

import it.polimi.ingsw.CommonTestMethods;
import it.polimi.ingsw.Controller.ControllerClasses.ActionController;
import it.polimi.ingsw.Controller.Actions.*;
import it.polimi.ingsw.Model.Enums.ResourceType;
import it.polimi.ingsw.Model.GameModel.Game;
import it.polimi.ingsw.Model.GameModel.Player;
import it.polimi.ingsw.Model.GameModel.ResourceStack;
import it.polimi.ingsw.Model.GameModel.WarehouseDepot;
import it.polimi.ingsw.Model.MessagesToClient.BoughtCardMessage;
import it.polimi.ingsw.Model.MessagesToClient.EndProductionMessage;
import it.polimi.ingsw.Model.MessagesToClient.MessageToClient;
import it.polimi.ingsw.Model.MessagesToClient.PaymentMessage;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
public class PayResourceTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    PayResource pay = new PayResourceBuyCard(false, 0, ResourceType.SHIELDS);
    PayResource pay2 = new PayResourceProduction(true, 0, ResourceType.NONE);
    PayResource pay3 = new PayResourceProduction(false, 0, ResourceType.STONES);
    PayResource pay4 = new PayResourceBuyCard(false, 0, ResourceType.COINS);
    PayResource pay5 = new PayResourceBuyCard(true, 3, ResourceType.NONE);

    ActionController actionController = new ActionController();

    /**getter test*/
    @Test
    public void getActionTypeTest(){

    }

    /**Test to check if isFromWarehouse method works properly*/
    @Test
    public void isFromWarehouseTest(){
        assertFalse(pay.isFromWarehouse());
    }

    /**Getter test*/
    @Test
    public void getDepotTest(){
        assertEquals(0, pay2.getDepot());
    }

    /**Getter test*/
    @Test
    public void getResourceTypeTest(){
        assertEquals(ResourceType.SHIELDS, pay.getResourceType());
    }

    /**Test to check if isCorrect method works properly when the index of the depot is out of bound*/
    @Test
    public void isCorrect1Test(){
        PayResource payErr = new PayResource(true, 5, ResourceType.STONES);
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Depot index out of bounds.");
        payErr.isCorrect();
    }

    /**Test to check if isCorrect method works properly when it's specify the type of a depot*/
    @Test
    public void isCorrect2Test(){
        PayResource payErr = new PayResource(true, 1, ResourceType.STONES);
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Specified type when choosing depot.");
        payErr.isCorrect();
    }

    /**Test to check if isCorrect method works properly when is not specify the type of resource in the strongbox*/
    @Test
    public void isCorrect3Test(){
        PayResource payErr = new PayResource(false, 1, ResourceType.NONE);
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Resource coming from strongbox was of type NONE.");
        payErr.isCorrect();
    }

    /**Test to check if canBeApplied works properly*/
    @Test
    public void canBeAppliedTest(){
        Game game = actionController.getGame();
        CommonTestMethods.gameInitOne(game);
    //When the resources comes from the strongbox, return true.
        assertTrue(pay.canBeApplied(actionController));
        WarehouseDepot warehouseDepot = new WarehouseDepot(2, true);
        game.getCurrentPlayer().getBoard().getResourceManager().addOneResourceToDepot(ResourceType.SHIELDS, warehouseDepot);
    //If the resource comes from an extraDepot but it is not active, return false.
        assertFalse(pay5.canBeApplied(actionController));
    //If the resource comes from an extraDepot but it is active, return true.
        game.getCurrentPlayer().getBoard().activateLeaderCard(game.getLeaderCards().get(7));
        assertTrue(pay5.canBeApplied(actionController));
    }

    /**
     * Test to check if the method "doAction()" and "messagePrepare()" work properly in PayResource Class
     */
    @Test
    public void doActionTest(){
        Game game = actionController.getGame();
        CommonTestMethods.gameInitOne(game);

        PayResource payResource = new PayResource(true, 1, ResourceType.COINS);
        assertNull(payResource.messagePrepare(actionController));

        MessageToClient message;
        actionController.getGame().getCurrentPlayer().addPossibleAction(ActionType.PAY_RESOURCE_CARD);
        ResourceStack stack = new ResourceStack(5,3,0,2);
        ResourceStack temporary = new ResourceStack(2,0,0,0);
        game.getCurrentPlayer().getBoard().getResourceManager().setTemporaryResourcesToPay(temporary);
        CommonTestMethods.giveResourcesToPlayer(game.getCurrentPlayer(), 1,2,0,ResourceType.SHIELDS, ResourceType.COINS, ResourceType.SERVANTS, stack);
        assertEquals("2 0 0 0", game.getCurrentPlayer().getBoard().getResourceManager().getTemporaryResourcesToPay().toString());

        assertEquals("HasToPay", pay.doAction(actionController));
        assertEquals("1 0 0 0", game.getCurrentPlayer().getBoard().getResourceManager().getTemporaryResourcesToPay().toString());
        message = pay.messagePrepare(actionController);
        assertTrue(message instanceof PaymentMessage);
        assertEquals("HasToPay", message.getError());
        assertEquals(1, message.getPossibleActions().size());
        assertEquals(ActionType.PAY_RESOURCE_CARD, message.getPossibleActions().get(0));

        actionController.getGame().getCurrentPlayer().addPossibleAction(ActionType.PAY_RESOURCE_PRODUCTION);

        assertEquals("This type of resource is not needed", pay3.doAction(actionController));
        message = pay3.messagePrepare(actionController);
        assertTrue(message instanceof PaymentMessage);
        assertEquals("This type of resource is not needed", message.getError());
        assertEquals(1, message.getPossibleActions().size());
        assertEquals(ActionType.PAY_RESOURCE_PRODUCTION, message.getPossibleActions().get(0));

        assertEquals("1 0 0 0", game.getCurrentPlayer().getBoard().getResourceManager().getTemporaryResourcesToPay().toString());
        assertEquals("No COINS left in Strongbox", pay4.doAction(actionController));
        assertEquals("Can't take resource from a non active depot", pay5.doAction(actionController));
        assertEquals("SUCCESS", pay2.doAction(actionController));
        message = pay2.messagePrepare(actionController);
        assertTrue(message instanceof EndProductionMessage);
        assertEquals("SUCCESS", message.getError());
        assertEquals(1, message.getPossibleActions().size());
        assertEquals(ActionType.CHOOSE_PRODUCTION_OUTPUT, message.getPossibleActions().get(0));

        assertEquals(ResourceType.NONE, game.getCurrentPlayer().getBoard().getResourceManager().getWarehouseDepots()[0].getResourceType());
    }

    /**
     * Test to check if the methods work properly in InitChooseResource Class
     */
    @Test
    public void otherTests() {
        PayResourceBuyCard action = new PayResourceBuyCard(false, 0, ResourceType.SHIELDS);
        actionController.getGame().getPlayers().add(new Player("pippo", 0, true));
        actionController.getGame().getCurrentPlayer().getBoard().getResourceManager().setTemporaryResourcesToPay(new ResourceStack(1,0,0,0));
        actionController.getGame().getCurrentPlayer().getBoard().getResourceManager().addProductionResources(new ResourceStack(1,0,0,0));
        actionController.getGame().getCurrentPlayer().addPossibleAction(ActionType.PAY_RESOURCE_CARD);
        action.doAction(actionController);
        MessageToClient message = action.messagePrepare(actionController);

        assertTrue(message instanceof BoughtCardMessage);
        assertEquals("SUCCESS", message.getError());
        assertEquals(((BoughtCardMessage) message).getStrongbox(), actionController.getGame().getCurrentPlayer().getBoard().getResourceManager().getStrongbox());
        assertEquals(((BoughtCardMessage) message).getWarehouse(), actionController.getGame().getCurrentPlayer().getBoard().getResourceManager().getWarehouse());
    }
}
