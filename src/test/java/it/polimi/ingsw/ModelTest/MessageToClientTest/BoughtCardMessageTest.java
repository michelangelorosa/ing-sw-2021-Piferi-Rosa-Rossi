package it.polimi.ingsw.ModelTest.MessageToClientTest;

import it.polimi.ingsw.Model.GameModel.Strongbox;
import it.polimi.ingsw.Model.GameModel.Warehouse;
import it.polimi.ingsw.Model.MessagesToClient.BoughtCardMessage;
import it.polimi.ingsw.View.ReducedModel.Player;
import it.polimi.ingsw.View.ReducedModel.RedStrongbox;
import it.polimi.ingsw.View.ReducedModel.RedWarehouse;
import it.polimi.ingsw.View.User.UserInteraction;
import it.polimi.ingsw.ViewTest.CommonViewTestMethods;
import org.junit.Test;

import static it.polimi.ingsw.Controller.Actions.ActionType.END_PAY_CARD;
import static org.junit.Assert.*;

public class BoughtCardMessageTest {

    BoughtCardMessage message = new BoughtCardMessage("antonio");
    UserInteraction userInteraction = CommonViewTestMethods.createUserInteraction();

    @Test
    public void genericMethodsTest(){
        assertEquals(END_PAY_CARD, message.getActionDone());
        assertEquals("antonio", message.getPlayerNickname());

        RedWarehouse warehouse = new Warehouse();
        RedStrongbox strongbox = new Strongbox();

        message.setWarehouse(warehouse);
        message.setStrongbox(strongbox);

        assertEquals(warehouse, message.getWarehouse());
        assertEquals(strongbox, message.getStrongbox());
    }

    @Test
    public void updateViewTest() {
        RedWarehouse warehouse = new Warehouse();
        RedStrongbox strongbox = new Strongbox();

        message.setWarehouse(warehouse);
        message.setStrongbox(strongbox);

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
