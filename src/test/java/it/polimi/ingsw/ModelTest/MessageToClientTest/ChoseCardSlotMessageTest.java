package it.polimi.ingsw.ModelTest.MessageToClientTest;

import it.polimi.ingsw.Model.GameModel.DevelopmentCardSlots;
import it.polimi.ingsw.Model.GameModel.DevelopmentCardTable;
import it.polimi.ingsw.Model.MessagesToClient.ChoseCardSlotMessage;
import it.polimi.ingsw.View.ReducedModel.Player;
import it.polimi.ingsw.View.ReducedModel.RedDevelopmentCardSlots;
import it.polimi.ingsw.View.ReducedModel.RedDevelopmentCardTable;
import it.polimi.ingsw.View.User.UserInteraction;
import it.polimi.ingsw.ViewTest.CommonViewTestMethods;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for ChoseCardSlotMessage Class.
 */
public class ChoseCardSlotMessageTest {

    ChoseCardSlotMessage message = new ChoseCardSlotMessage("antonio");
    UserInteraction userInteraction = CommonViewTestMethods.createUserInteraction();

    /**
     * Test for constructor, getters and setters.
     */
    @Test
    public void genericMethodsTest() {
        RedDevelopmentCardSlots slots = new DevelopmentCardSlots();
        RedDevelopmentCardTable table = new DevelopmentCardTable();

        message.setSlots(slots);
        message.setTable(table);

        assertEquals(message.getSlots(), slots);
        assertEquals(message.getTable(), table);
    }

    /**
     * updateView method Test.
     */
    @Test
    public void updateViewTest() {
        RedDevelopmentCardSlots slots = new DevelopmentCardSlots();
        RedDevelopmentCardTable table = new DevelopmentCardTable();

        message.setSlots(slots);
        message.setTable(table);

        Player player = CommonViewTestMethods.findPlayerByNickname(userInteraction.getGame(), "antonio");
        assert player != null;

        message.setError("error");
        message.updateView(userInteraction);

        assertNotEquals(message.getSlots(), player.getSlots());

        message.setError("SUCCESS");
        message.updateView(userInteraction);

        assertEquals(message.getSlots(), player.getSlots());
        assertEquals(message.getTable(), userInteraction.getGame().getDevelopmentCardTable());
    }
}
