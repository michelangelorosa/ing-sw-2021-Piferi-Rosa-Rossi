package it.polimi.ingsw.ModelTest.MessageToClientTest;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.Model.GameModel.Strongbox;
import it.polimi.ingsw.Model.MessagesToClient.ChoseProductionOutputMessage;
import it.polimi.ingsw.View.ReducedModel.Player;
import it.polimi.ingsw.View.ReducedModel.RedStrongbox;
import it.polimi.ingsw.View.User.UserInteraction;
import it.polimi.ingsw.ViewTest.CommonViewTestMethods;
import org.junit.Test;
import static org.junit.Assert.*;

public class ChoseProductionOutputMessageTest {
    ChoseProductionOutputMessage message = new ChoseProductionOutputMessage("antonio");
    UserInteraction userInteraction = CommonViewTestMethods.createUserInteraction();

    @Test
    public void genericMethodsTest() {
        assertEquals("antonio", message.getPlayerNickname());
        assertEquals(ActionType.CHOOSE_PRODUCTION_OUTPUT, message.getActionDone());

        RedStrongbox strongbox = new Strongbox();
        message.setStrongbox(strongbox);

        assertEquals(strongbox, message.getStrongbox());
    }

    @Test
    public void updateViewTest() {
        RedStrongbox strongbox = new Strongbox();
        message.setStrongbox(strongbox);

        Player player = CommonViewTestMethods.findPlayerByNickname(userInteraction.getGame(), "antonio");
        assert player != null;

        message.setError("error");
        message.updateView(userInteraction);
        assertNotEquals(message.getStrongbox(), player.getStrongbox());

        message.setError("SUCCESS");
        message.updateView(userInteraction);
        assertEquals(message.getStrongbox(), player.getStrongbox());
    }
}
