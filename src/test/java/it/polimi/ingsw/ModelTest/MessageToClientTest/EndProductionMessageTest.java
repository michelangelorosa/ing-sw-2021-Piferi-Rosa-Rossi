package it.polimi.ingsw.ModelTest.MessageToClientTest;

import static it.polimi.ingsw.Controller.Actions.ActionType.END_PAY_PRODUCTION;
import static org.junit.Assert.*;

import it.polimi.ingsw.Model.GameModel.Strongbox;
import it.polimi.ingsw.Model.GameModel.Warehouse;
import it.polimi.ingsw.Model.MessagesToClient.EndProductionMessage;
import it.polimi.ingsw.View.ReducedModel.Player;
import it.polimi.ingsw.View.ReducedModel.RedStrongbox;
import it.polimi.ingsw.View.ReducedModel.RedWarehouse;
import it.polimi.ingsw.View.User.UserInteraction;
import it.polimi.ingsw.ViewTest.CommonViewTestMethods;
import org.junit.Test;

/**
 * Tests for EndProductionMessage Class.
 */
public class EndProductionMessageTest {

    EndProductionMessage message = new EndProductionMessage("antonio");
    UserInteraction userInteraction = CommonViewTestMethods.createUserInteraction();

    /**
     * Test for constructor, getters and setters.
     */
    @Test
    public void ConstructorTest(){
        assertEquals(END_PAY_PRODUCTION, message.getActionDone());
        assertEquals("antonio", message.getPlayerNickname());
    }

    /**
     * Test for constructor, getters and setters.
     */
    @Test
    public void genericMethodsTest() {
        RedStrongbox strongbox = new Strongbox();
        RedWarehouse warehouse = new Warehouse();

        message.setStrongbox(strongbox);
        message.setWarehouse(warehouse);

        assertEquals(strongbox, message.getStrongbox());
        assertEquals(warehouse, message.getWarehouse());
    }

    /**
     * updateView method Test.
     */
    @Test
    public void updateViewTest() {
        RedStrongbox strongbox = new Strongbox();
        RedWarehouse warehouse = new Warehouse();

        message.setStrongbox(strongbox);
        message.setWarehouse(warehouse);

        Player player = CommonViewTestMethods.findPlayerByNickname(userInteraction.getGame(), "antonio");

        message.setError("error");
        message.updateView(userInteraction);
        assert player != null;
        assertNotEquals(message.getWarehouse(), player.getWarehouse());

        message.setError("SUCCESS");
        message.updateView(userInteraction);
        assertEquals(message.getWarehouse(), player.getWarehouse());
        assertEquals(message.getStrongbox(), player.getStrongbox());
    }
}
