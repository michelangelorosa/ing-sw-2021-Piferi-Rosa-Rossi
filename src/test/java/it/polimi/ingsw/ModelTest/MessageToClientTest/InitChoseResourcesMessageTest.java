package it.polimi.ingsw.ModelTest.MessageToClientTest;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.Model.MessagesToClient.InitChoseResourcesMessage;
import it.polimi.ingsw.View.User.UserInteraction;
import it.polimi.ingsw.ViewTest.CommonViewTestMethods;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for InitChoseResourcesMessage Class.
 */
public class InitChoseResourcesMessageTest {
    InitChoseResourcesMessage message = new InitChoseResourcesMessage("antonio");
    UserInteraction userInteraction = CommonViewTestMethods.createUserInteraction();

    /**
     * Test for constructor, getters and setters.
     */
    @Test
    public void genericMethodsTest() {
        assertEquals("", message.getPlayerNickname());
        assertEquals(ActionType.INIT_CHOOSE_RESOURCES, message.getActionDone());
    }

    /**
     * updateView method Test.
     */
    @Test
    public void updateViewTest() {
        message.setError("SUCCESS");
        message.updateView(userInteraction);

        message.setError("error");
        message.updateView(userInteraction);
    }
}
