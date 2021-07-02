package it.polimi.ingsw.ModelTest.MessageToClientTest;

import static it.polimi.ingsw.Controller.Actions.ActionType.CHOOSE_LEADER_CARD;
import static org.junit.Assert.*;

import it.polimi.ingsw.Model.GameModel.ResourceStack;
import it.polimi.ingsw.Model.MessagesToClient.ChoseLeaderCardMessage;
import it.polimi.ingsw.View.ReducedModel.Player;
import it.polimi.ingsw.View.ReducedModel.RedLeaderCard;
import it.polimi.ingsw.View.ReducedModel.RedResourceStack;
import it.polimi.ingsw.View.User.UserInteraction;
import it.polimi.ingsw.ViewTest.CommonViewTestMethods;
import org.junit.Test;

/**
 * Tests for ChoseLeaderCardMessage Class.
 */
public class ChoseLeaderCardMessageTest {
    ChoseLeaderCardMessage leaderCardMessage = new ChoseLeaderCardMessage("franco");
    UserInteraction userInteraction = CommonViewTestMethods.createUserInteraction();

    /**
     * Test for constructor, getters and setters.
     */
    @Test
    public void genericMethodsTest(){
        assertEquals(CHOOSE_LEADER_CARD, leaderCardMessage.getActionDone());
        assertEquals("franco", leaderCardMessage.getPlayerNickname());

        RedResourceStack temporaryResources = new ResourceStack(1,1,1,1);
        leaderCardMessage.setTemporaryResources(temporaryResources);
        assertEquals(temporaryResources, leaderCardMessage.getTemporaryResources());
    }

    /**
     * updateView method Test.
     */
    @Test
    public void updateViewTest() {
        leaderCardMessage.setError("error");
        leaderCardMessage.updateView(userInteraction);

        Player player = CommonViewTestMethods.findPlayerByNickname(userInteraction.getGame(), "franco");
        assert player != null;

        assertNotEquals(leaderCardMessage.getTemporaryResources(), player.getTemporaryResources());

        leaderCardMessage.setError("SUCCESS");
        leaderCardMessage.updateView(userInteraction);
        assertEquals(leaderCardMessage.getTemporaryResources(), player.getTemporaryResources());
    }

}
