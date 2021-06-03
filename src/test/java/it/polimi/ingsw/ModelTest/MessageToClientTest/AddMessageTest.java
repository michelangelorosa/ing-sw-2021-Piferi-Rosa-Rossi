package it.polimi.ingsw.ModelTest.MessageToClientTest;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.Model.GameModel.ResourceStack;
import it.polimi.ingsw.Model.GameModel.Warehouse;
import it.polimi.ingsw.Model.MessagesToClient.AddMessage;
import it.polimi.ingsw.View.ReducedModel.Player;
import it.polimi.ingsw.View.ReducedModel.RedResourceStack;
import it.polimi.ingsw.View.ReducedModel.RedWarehouse;
import it.polimi.ingsw.View.User.UserInteraction;
import it.polimi.ingsw.ViewTest.CommonViewTestMethods;
import org.junit.Test;
import static org.junit.Assert.*;

public class AddMessageTest {
    AddMessage message = new AddMessage("antonio");
    UserInteraction userInteraction = CommonViewTestMethods.createUserInteraction();

    @Test
    public void genericMethodsTest() {
        assertEquals(ActionType.ADD_RESOURCE, message.getActionDone());
        assertEquals("antonio", message.getPlayerNickname());

        RedResourceStack stack = new ResourceStack(1, 2, 3, 4);
        RedWarehouse warehouse = new Warehouse();

        message.setTemporaryResources(stack);
        message.setWarehouse(warehouse);
        assertEquals(stack, message.getTemporaryResources());
        assertEquals(warehouse, message.getWarehouse());
    }

    @Test
    public void updateViewTest() {
        RedResourceStack stack = new ResourceStack(1, 2, 3, 4);
        RedWarehouse warehouse = new Warehouse();

        message.setTemporaryResources(stack);
        message.setWarehouse(warehouse);
        message.setError("error");

        message.updateView(userInteraction);

        Player player = CommonViewTestMethods.findPlayerByNickname(userInteraction.getGame(), "antonio");

        assert player != null;
        assertNotEquals(message.getTemporaryResources(), player.getTemporaryResources());
        message.setError("SUCCESS");

        message.updateView(userInteraction);

        assertEquals(message.getTemporaryResources(), player.getTemporaryResources());
        assertEquals(message.getWarehouse(), player.getWarehouse());
    }
}
