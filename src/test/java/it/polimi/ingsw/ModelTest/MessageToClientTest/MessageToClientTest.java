package it.polimi.ingsw.ModelTest.MessageToClientTest;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.Model.MessagesToClient.MessageToClient;
import it.polimi.ingsw.View.ReducedModel.Player;
import it.polimi.ingsw.View.User.UserInteraction;
import it.polimi.ingsw.ViewTest.CommonViewTestMethods;
import org.junit.Test;

import java.util.Objects;

import static org.junit.Assert.*;

public class MessageToClientTest {
    MessageToClient message = new MessageToClient("antonio");
    UserInteraction userInteraction = CommonViewTestMethods.createUserInteraction();

    @Test
    public void constructorTest() {
        message = new MessageToClient();
        assertEquals(ActionType.ACTIVATE_LEADERCARD, message.getActionDone());
        assertEquals("SUPERCLASS GENERIC MESSAGE", message.getError());

        message = new MessageToClient("antonio");
        assertEquals("antonio", message.getPlayerNickname());
    }

    @Test
    public void setterAndGetterTest() {
        message.setError("error");
        message.addPossibleAction(ActionType.ADD_RESOURCE);

        assertEquals("error", message.getError());
        assertEquals(1, message.getPossibleActions().size());
        assertEquals(ActionType.ADD_RESOURCE, message.getPossibleActions().get(0));
    }

    @Test
    public void methodTest() {
        Player player = message.getPlayer(userInteraction);
        assertEquals(player, userInteraction.getGame().getPlayers().get(0));
        assertEquals("antonio", userInteraction.getGame().getPlayers().get(0).getNickname());

        userInteraction.getGame().setMyNickname("pippo");
        assertFalse(message.imPlaying(userInteraction));
        userInteraction.getGame().setMyNickname("antonio");
        assertTrue(message.imPlaying(userInteraction));

        class TestMessage extends MessageToClient {
            protected TestMessage(String nickname) {
                super(nickname);
            }

            protected boolean success() {
                return super.success();
            }

            protected void displayAction(UserInteraction userInteraction) {
                super.displayAction(userInteraction);
            }

            protected void displayError(UserInteraction userInteraction) {
                super.displayError(userInteraction);
            }

            protected void changePossibleActions(UserInteraction userInteraction) {
                super.changePossibleActions(userInteraction);
            }
        }

        TestMessage message = new TestMessage("antonio");
        message.setError("error");
        assertFalse(message.success());
        message.setError("SUCCESS");
        assertTrue(message.success());

        message.addPossibleAction(ActionType.CHOOSE_PRODUCTION_OUTPUT);
        message.addPossibleAction(ActionType.DELETE_LEADERCARD);
        message.changePossibleActions(userInteraction);

        Player player1 = Objects.requireNonNull(CommonViewTestMethods.findPlayerByNickname(userInteraction.getGame(), "antonio"));
        assertEquals(2, player1.getPossibleActions().size());
        assertEquals(ActionType.CHOOSE_PRODUCTION_OUTPUT, player1.getPossibleActions().get(0));
        assertEquals(ActionType.DELETE_LEADERCARD, player1.getPossibleActions().get(1));
        message.displayError(userInteraction);
        message.displayAction(userInteraction);
    }
}
