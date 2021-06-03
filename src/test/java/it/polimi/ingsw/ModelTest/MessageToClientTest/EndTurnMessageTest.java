package it.polimi.ingsw.ModelTest.MessageToClientTest;

import static org.junit.Assert.*;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.Model.MessagesToClient.EndTurnMessage;
import it.polimi.ingsw.View.User.UserInteraction;
import it.polimi.ingsw.ViewTest.CommonViewTestMethods;
import org.junit.Test;

public class EndTurnMessageTest {

    EndTurnMessage message = new EndTurnMessage("antonio");
    UserInteraction userInteraction = CommonViewTestMethods.createUserInteraction();

    @Test
    public void genericMethodsTest() {
        assertEquals("antonio", message.getPlayerNickname());
        assertEquals(ActionType.END_TURN, message.getActionDone());

        message.setNextPlayerNickname("franco");
        assertEquals("franco", message.getNextPlayerNickname());
    }

    @Test
    public void updateViewTest() {
        message.setNextPlayerNickname("franco");
        message.setError("notError");
        message.updateView(userInteraction);
    }
}
