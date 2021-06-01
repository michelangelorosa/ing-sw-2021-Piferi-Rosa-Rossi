package it.polimi.ingsw.ViewTest;

import it.polimi.ingsw.Model.MessagesToClient.MessageToClient;
import it.polimi.ingsw.View.ReducedModel.Game;
import it.polimi.ingsw.View.ReducedModel.RedLeaderCard;
import it.polimi.ingsw.View.User.CliController;
import it.polimi.ingsw.View.User.UserInteraction;
import it.polimi.ingsw.View.User.UserInterface;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class UserInteractionTest {
    UserInteraction userInteraction = new UserInteraction() {
    };

    @Test
    public void getterAndSetterTest() {
        Game game = new Game();
        userInteraction.setGame(game);
        assertEquals(game, userInteraction.getGame());

        UserInterface ui = new CliController();
        userInteraction.setUi(ui);
        assertEquals(ui, userInteraction.getUi());

        userInteraction.setActionNumber(3);
        assertEquals(3, userInteraction.getActionNumber());

        ArrayList<RedLeaderCard> leaderCards = new ArrayList<>();
        userInteraction.setInitLeaderCards(leaderCards);
        assertEquals(leaderCards, userInteraction.getInitLeaderCards());

        userInteraction.setInitNumberOfResources(10);
        assertEquals(10, userInteraction.getInitNumberOfResources());

        MessageToClient message = new MessageToClient();
        userInteraction.setMessage(message);
        assertEquals(message, userInteraction.getMessage());

        message = new MessageToClient();
        userInteraction.setPreviousMessage(message);
        assertEquals(message, userInteraction.getPreviousMessage());
    }

    @Test
    public void methodsTest() {
        System.setIn(new ByteArrayInputStream("localhost\n8765".getBytes()));
        userInteraction.setUi(new CliController());
        userInteraction.setMessage(new MessageToClient());
        assertFalse(userInteraction.sameMessages());

        ArrayList<Object> objects = userInteraction.connectToServer();
        assertEquals("localhost", objects.get(0));
        assertEquals(8765, objects.get(1));

        userInteraction.displayError("error");
        userInteraction.nextAction(3);
    }
}
