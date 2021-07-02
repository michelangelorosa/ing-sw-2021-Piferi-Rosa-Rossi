package it.polimi.ingsw.ModelTest.MessageToClientTest;

import static it.polimi.ingsw.Controller.Actions.ActionType.ACTIVATE_LEADERCARD;
import static org.junit.Assert.*;

import it.polimi.ingsw.Model.Enums.Marble;
import it.polimi.ingsw.Model.Enums.ResourceType;
import it.polimi.ingsw.Model.GameModel.LeaderCard;
import it.polimi.ingsw.Model.GameModel.LeaderRequirements;
import it.polimi.ingsw.Model.GameModel.ResourceStack;
import it.polimi.ingsw.Model.GameModel.Warehouse;
import it.polimi.ingsw.Model.MessagesToClient.ActivateLeaderCardMessage;
import it.polimi.ingsw.View.ReducedModel.Player;
import it.polimi.ingsw.View.ReducedModel.RedLeaderCard;
import it.polimi.ingsw.View.User.UserInteraction;
import it.polimi.ingsw.ViewTest.CommonViewTestMethods;
import org.junit.Test;

/**
 * Tests for ActivateLeaderCardMessage Class.
 */
public class ActivateLeaderCardMessageTest {

    ActivateLeaderCardMessage message = new ActivateLeaderCardMessage("antonio");
    ResourceStack stack = new ResourceStack(0,0,0,0);
    LeaderRequirements leaderRequirements = new LeaderRequirements(0,0,0,0);

    RedLeaderCard leaderCard = new LeaderCard(1, 1, stack, leaderRequirements, Marble.BLUE);

    /**
     * Constructor Test.
     */
    @Test
    public void ConstructorTest(){
        assertEquals(ACTIVATE_LEADERCARD, message.getActionDone());
        assertEquals("antonio", message.getPlayerNickname());
    }

    /**
     * Getter and Setter Test.
     */
    @Test
    public void setterAndGetterTest() {
        message.setLeaderCard(leaderCard);
        Warehouse warehouse = new Warehouse();
        message.setWarehouse(warehouse);
        message.setLeaderCardPosition(0);

        assertEquals(warehouse, message.getWarehouse());
        assertEquals(leaderCard, message.getLeaderCard());
        assertEquals(0, message.getLeaderCardPosition());
    }

    /**
     * updateView method Test.
     */
    @Test
    public void updateViewTest() {
        UserInteraction userInteraction = CommonViewTestMethods.createUserInteraction();

        message.setLeaderCard(leaderCard);
        message.setLeaderCardPosition(0);
        message.setError("error");
        message.updateView(userInteraction);

        message.setError("SUCCESS");
        message.setWarehouse(new Warehouse());
        message.updateView(userInteraction);
        Player player = CommonViewTestMethods.findPlayerByNickname(userInteraction.getGame(), "antonio");
        assert player != null;
        assertEquals(message.getLeaderCard(), player.getLeaderCards()[message.getLeaderCardPosition()]);
        assertNotEquals(message.getWarehouse(), player.getWarehouse());

        RedLeaderCard leaderCard = new LeaderCard(1, 1, stack, leaderRequirements, ResourceType.SERVANTS);

        message.setLeaderCard(leaderCard);
        message.updateView(userInteraction);
        assertEquals(message.getWarehouse(), player.getWarehouse());
    }

}
