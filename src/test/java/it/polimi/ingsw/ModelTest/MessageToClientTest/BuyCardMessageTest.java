package it.polimi.ingsw.ModelTest.MessageToClientTest;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.Model.GameModel.ResourceStack;
import it.polimi.ingsw.Model.MessagesToClient.BuyCardMessage;
import it.polimi.ingsw.View.ReducedModel.Player;
import it.polimi.ingsw.View.ReducedModel.RedResourceStack;
import it.polimi.ingsw.View.User.UserInteraction;
import it.polimi.ingsw.ViewTest.CommonViewTestMethods;
import org.junit.Test;
import static org.junit.Assert.*;

public class BuyCardMessageTest {
    BuyCardMessage message = new BuyCardMessage("antonio");
    UserInteraction userInteraction = CommonViewTestMethods.createUserInteraction();

    @Test
    public void genericMethodsTest() {
        assertEquals("antonio", message.getPlayerNickname());
        assertEquals(ActionType.BUY_CARD, message.getActionDone());

        RedResourceStack stack = new ResourceStack(1, 2, 3, 4);
        message.setTemporaryResources(stack);

        assertEquals(stack, message.getTemporaryResources());
    }

    @Test
    public void updateViewTest() {
        RedResourceStack stack = new ResourceStack(1, 2, 3, 4);
        message.setTemporaryResources(stack);

        Player player = CommonViewTestMethods.findPlayerByNickname(userInteraction.getGame(), "antonio");

        message.setError("error");
        message.updateView(userInteraction);
        assert player != null;
        assertNotEquals(message.getTemporaryResources(), player.getTemporaryResources());

        message.setError("SUCCESS");
        message.updateView(userInteraction);
        assertEquals(message.getTemporaryResources(), player.getTemporaryResources());
    }
}
