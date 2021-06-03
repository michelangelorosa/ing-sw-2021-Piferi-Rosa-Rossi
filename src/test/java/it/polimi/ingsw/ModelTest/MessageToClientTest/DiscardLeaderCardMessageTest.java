package it.polimi.ingsw.ModelTest.MessageToClientTest;

import it.polimi.ingsw.Model.Enums.Marble;
import it.polimi.ingsw.Model.GameModel.LeaderCard;
import it.polimi.ingsw.Model.GameModel.LeaderRequirements;
import it.polimi.ingsw.Model.GameModel.ResourceStack;
import it.polimi.ingsw.Model.MessagesToClient.DiscardLeaderCardMessage;
import it.polimi.ingsw.View.ReducedModel.Player;
import it.polimi.ingsw.View.ReducedModel.RedLeaderCard;
import it.polimi.ingsw.View.User.UserInteraction;
import it.polimi.ingsw.ViewTest.CommonViewTestMethods;
import org.junit.Test;

import static it.polimi.ingsw.Controller.Actions.ActionType.*;
import static org.junit.Assert.*;

public class DiscardLeaderCardMessageTest {
    DiscardLeaderCardMessage leaderCardMessage = new DiscardLeaderCardMessage("antonio");
    UserInteraction userInteraction = CommonViewTestMethods.createUserInteraction();

    @Test
    public void genericMethodsTest(){
        assertEquals(DELETE_LEADERCARD, leaderCardMessage.getActionDone());
        assertEquals("antonio", leaderCardMessage.getPlayerNickname());

        ResourceStack stack = new ResourceStack(1, 2, 3, 4);
        LeaderRequirements requirements = new LeaderRequirements(0, 0, 0, 0);

        RedLeaderCard leaderCard = new LeaderCard(1, 1, stack, requirements, Marble.GREY);

        leaderCardMessage.setLeaderCard(leaderCard);
        leaderCardMessage.setNumber(0);
        assertEquals(leaderCard, leaderCardMessage.getLeaderCard());
        assertEquals(0, leaderCardMessage.getNumber());
    }

    @Test
    public void updateViewTest() {
        ResourceStack stack = new ResourceStack(1, 2, 3, 4);
        LeaderRequirements requirements = new LeaderRequirements(0, 0, 0, 0);

        RedLeaderCard leaderCard = new LeaderCard(1, 1, stack, requirements, Marble.GREY);

        leaderCardMessage.setLeaderCard(leaderCard);
        leaderCardMessage.setNumber(0);

        Player player = CommonViewTestMethods.findPlayerByNickname(userInteraction.getGame(), "antonio");
        assert player != null;

        leaderCardMessage.setError("error");
        leaderCardMessage.updateView(userInteraction);
        assertNotEquals(leaderCardMessage.getLeaderCard(), player.getLeaderCards()[leaderCardMessage.getNumber()]);

        leaderCardMessage.setError("SUCCESS");
        leaderCardMessage.updateView(userInteraction);
        assertEquals(leaderCardMessage.getLeaderCard(), player.getLeaderCards()[leaderCardMessage.getNumber()]);
    }
}
