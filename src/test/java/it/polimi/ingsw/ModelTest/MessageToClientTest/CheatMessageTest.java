package it.polimi.ingsw.ModelTest.MessageToClientTest;

import it.polimi.ingsw.CommonTestMethods;
import it.polimi.ingsw.Controller.ControllerClasses.ActionController;
import it.polimi.ingsw.Model.MessagesToClient.CheatMessage.CheatMessage;
import it.polimi.ingsw.View.ReducedModel.Game;
import it.polimi.ingsw.View.ReducedModel.Player;
import it.polimi.ingsw.View.User.CliController;
import it.polimi.ingsw.View.User.UserInteraction;
import org.junit.Test;

/**
 * Tests for CheatMessage Class.
 */
public class CheatMessageTest {
    ActionController actionController = new ActionController();
    UserInteraction userInteraction = new UserInteraction() {
    };
    CheatMessage message;

    /**
     * Test for constructor, getters and setters.
     */
    @Test
    public void commonMethodsTest() {
        CommonTestMethods.gameInitOne(actionController.getGame());
        Game game = new Game();
        game.getPlayers().add(new Player("Zero", 0, true));
        userInteraction.setGame(game);
        userInteraction.setUi(new CliController());

        message = new CheatMessage(actionController);
        message.setError("SUCCESS");
        message.updateView(userInteraction);

        message.setError("error");
        message.updateView(userInteraction);
    }
}
