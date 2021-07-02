package it.polimi.ingsw.ModelTest.MessageToClientTest;

import it.polimi.ingsw.Model.MessagesToClient.OtherMessages.ExceptionMessage;
import it.polimi.ingsw.View.ReducedModel.Game;
import it.polimi.ingsw.View.ReducedModel.Player;
import it.polimi.ingsw.View.User.CliController;
import it.polimi.ingsw.View.User.UserInteraction;
import org.junit.Test;

/**
 * Tests for ExceptionMessage Class.
 */
public class ExceptionMessageTest {

    /**
     * Test for constructor, getters and setters.
     */
    @Test
    public void test() {
        UserInteraction userInteraction = new UserInteraction() {
        };
        userInteraction.setUi(new CliController());
        userInteraction.setGame(new Game());
        userInteraction.getGame().getPlayers().add(new Player("pippo", 0, true));
        ExceptionMessage message = new ExceptionMessage("pippo");
        message.updateView(userInteraction);
    }
}
