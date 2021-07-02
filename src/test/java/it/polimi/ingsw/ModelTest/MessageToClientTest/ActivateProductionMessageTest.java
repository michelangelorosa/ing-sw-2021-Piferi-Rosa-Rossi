package it.polimi.ingsw.ModelTest.MessageToClientTest;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.Model.GameModel.ResourceStack;
import it.polimi.ingsw.Model.MessagesToClient.ActivateProductionMessage;
import it.polimi.ingsw.View.ReducedModel.Player;
import it.polimi.ingsw.View.ReducedModel.RedResourceStack;
import it.polimi.ingsw.View.User.UserInteraction;
import it.polimi.ingsw.ViewTest.CommonViewTestMethods;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests for ActivateProductionMessage Class.
 */
public class ActivateProductionMessageTest {

    ActivateProductionMessage message = new ActivateProductionMessage("antonio");
    UserInteraction userInteraction = CommonViewTestMethods.createUserInteraction();

    /**
     * Test for constructor, getters and setters.
     */
    @Test
    public void genericMethodsTest() {
        assertEquals("antonio", message.getPlayerNickname());
        assertEquals(ActionType.ACTIVATE_PRODUCTION, message.getActionDone());

        RedResourceStack stack = new ResourceStack(1, 2, 3, 4);
        message.setResourcesToPay(stack);
        assertEquals(stack, message.getResourcesToPay());
    }

    /**
     * updateView method Test.
     */
    @Test
    public void updateViewTest() {
        message.setError("error");
        message.updateView(userInteraction);

        Player player = CommonViewTestMethods.findPlayerByNickname(userInteraction.getGame(), "antonio");
        assert player != null;

        assertNotEquals(message.getResourcesToPay(), player.getTemporaryResources());

        message.setError("SUCCESS");
        message.updateView(userInteraction);
        assertEquals(message.getResourcesToPay(), player.getTemporaryResources());
    }
}
