package it.polimi.ingsw.ModelTest.MessageToClientTest;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.Model.GameModel.Market;
import it.polimi.ingsw.Model.GameModel.ResourceStack;
import it.polimi.ingsw.Model.MessagesToClient.ChoseMarketRowMessage;
import it.polimi.ingsw.View.ReducedModel.Player;
import it.polimi.ingsw.View.ReducedModel.RedMarket;
import it.polimi.ingsw.View.ReducedModel.RedResourceStack;
import it.polimi.ingsw.View.User.UserInteraction;
import it.polimi.ingsw.ViewTest.CommonViewTestMethods;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Tests for ChoseMarketRow Class.
 */
public class ChoseMarketRowTest {
    UserInteraction userInteraction = CommonViewTestMethods.createUserInteraction();
    ChoseMarketRowMessage message = new ChoseMarketRowMessage("franco");
    RedResourceStack temporaryResources = new ResourceStack(2,2,2,1);

    /**
     * Test for constructor, getters and setters.
     */
    @Test
    public void genericMethodsTest() {
        assertEquals("franco", message.getPlayerNickname());
        assertEquals(ActionType.MARKET_CHOOSE_ROW, message.getActionDone());

        RedMarket market = new Market();
        message.setMarket(market);
        assertEquals(market, message.getMarket());
        message.setTemporaryResources(temporaryResources);
        assertEquals(temporaryResources, message.getTemporaryResources());
    }

    /**
     * updateView method Test.
     */
    @Test
    public void updateView(){
        message.setError("error");
        message.setMarket(userInteraction.getGame().getMarket());
        message.updateView(userInteraction);

        Player player = CommonViewTestMethods.findPlayerByNickname(userInteraction.getGame(), "franco");
        assert player != null;
        assertNotEquals(message.getTemporaryResources(), player.getTemporaryResources());

        assertEquals(message.getMarket().getExtraMarble(), userInteraction.getGame().getMarket().getExtraMarble());
        message.setError("SUCCESS");
        message.updateView(userInteraction);
        assertEquals(message.getTemporaryResources(), player.getTemporaryResources()); }
}
