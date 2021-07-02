package it.polimi.ingsw.ModelTest.MessageToClientTest;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.Model.Enums.Marble;
import it.polimi.ingsw.Model.GameModel.LeaderCard;
import it.polimi.ingsw.Model.GameModel.LeaderRequirements;
import it.polimi.ingsw.Model.GameModel.ResourceStack;
import it.polimi.ingsw.Model.MessagesToClient.InitChoseLeaderCardsMessage;
import it.polimi.ingsw.View.ReducedModel.Player;
import it.polimi.ingsw.View.ReducedModel.RedLeaderCard;
import it.polimi.ingsw.View.User.UserInteraction;
import it.polimi.ingsw.ViewTest.CommonViewTestMethods;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for InitChoseLeaderCards Class.
 */
public class InitChoseLeaderCardsTest {
    InitChoseLeaderCardsMessage message = new InitChoseLeaderCardsMessage("antonio");
    UserInteraction userInteraction = CommonViewTestMethods.createUserInteraction();

    /**
     * Test for constructor, getters and setters.
     */
    @Test
    public void genericMethodsTest() {
        assertEquals("antonio", message.getPlayerNickname());
        assertEquals(ActionType.INIT_CHOOSE_LEADER_CARDS, message.getActionDone());
    }

    /**
     * updateView method Test.
     */
    @Test
    public void updateView() {
        ResourceStack stack = new ResourceStack(1, 2, 3, 4);
        LeaderRequirements requirements = new LeaderRequirements(0, 0, 0, 0);

        RedLeaderCard leaderCard1 = new LeaderCard(1, 1, stack, requirements, Marble.BLUE);
        RedLeaderCard leaderCard2 = new LeaderCard(2, 2, stack, requirements, Marble.GREY);


        Player player = CommonViewTestMethods.findPlayerByNickname(userInteraction.getGame(), "antonio");
        assert player != null;

        message.setError("error");
        message.updateView(userInteraction);

        message.setError("SUCCESS");
        message.updateView(userInteraction);
    }
}
