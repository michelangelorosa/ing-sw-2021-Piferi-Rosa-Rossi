package it.polimi.ingsw.ModelTest.MessageToClientTest;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.Model.GameModel.Warehouse;
import it.polimi.ingsw.Model.MessagesToClient.SwitchDepotMessage;
import it.polimi.ingsw.View.ReducedModel.Player;
import it.polimi.ingsw.View.ReducedModel.RedWarehouse;
import it.polimi.ingsw.View.User.UserInteraction;
import it.polimi.ingsw.ViewTest.CommonViewTestMethods;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class SwitchDepotMessageTest {

    SwitchDepotMessage message = new SwitchDepotMessage("antonio");
    UserInteraction userInteraction = CommonViewTestMethods.createUserInteraction();

    @Test
    public void genericMethodsTest() {
        assertEquals("antonio", message.getPlayerNickname());
        assertEquals(ActionType.SWITCH_DEPOT, message.getActionDone());

        RedWarehouse warehouse = new Warehouse();
        message.setWarehouse(warehouse);
        assertEquals(warehouse, message.getWarehouse());
    }

    @Test
    public void updateViewTest() {
        RedWarehouse warehouse = new Warehouse();
        message.setWarehouse(warehouse);
        Player player = CommonViewTestMethods.findPlayerByNickname(userInteraction.getGame(), "antonio");
        assert player != null;

        message.setError("error");
        message.updateView(userInteraction);
        assertNotEquals(message.getWarehouse(), player.getWarehouse());

        message.setError("SUCCESS");
        message.updateView(userInteraction);
        assertEquals(message.getWarehouse(), player.getWarehouse());
    }
}
