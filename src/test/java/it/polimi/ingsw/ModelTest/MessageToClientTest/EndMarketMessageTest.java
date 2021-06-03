package it.polimi.ingsw.ModelTest.MessageToClientTest;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.Model.MessagesToClient.EndMarketMessage;
import it.polimi.ingsw.View.ReducedModel.Player;
import it.polimi.ingsw.View.User.UserInteraction;
import it.polimi.ingsw.ViewTest.CommonViewTestMethods;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class EndMarketMessageTest {
    EndMarketMessage message = new EndMarketMessage("antonio");
    UserInteraction userInteraction = CommonViewTestMethods.createUserInteraction();

    @Test
    public void genericMethodsTest() {
        assertEquals("antonio", message.getPlayerNickname());
        assertEquals(ActionType.END_MARKET, message.getActionDone());

        HashMap<String, Integer> map = new HashMap<>();

        map.put("antonio", 3);
        map.put("Lorenzo il Magnifico", 100);
        map.put("franco", 0);
        map.put("gianpiero", 7);

        message.setPlayersFaithPosition(map);
        assertEquals(map, message.getPlayersFaithPosition());
    }

    @Test
    public void updateViewTest() {
        HashMap<String, Integer> map = new HashMap<>();

        map.put("antonio", 3);
        map.put("Lorenzo il Magnifico", 100);
        map.put("franco", 1);
        map.put("gianpiero", 7);
        message.setPlayersFaithPosition(map);

        Player player1 = CommonViewTestMethods.findPlayerByNickname(userInteraction.getGame(), "antonio");
        Player player2 = CommonViewTestMethods.findPlayerByNickname(userInteraction.getGame(), "Lorenzo il Magnifico");
        Player player3 = CommonViewTestMethods.findPlayerByNickname(userInteraction.getGame(), "franco");
        Player player4 = CommonViewTestMethods.findPlayerByNickname(userInteraction.getGame(), "gianpiero");

        assert player1 != null;
        assert player2 != null;
        assert player3 != null;
        assert player4 != null;

        message.setError("error");
        message.updateView(userInteraction);
        assertNotEquals(3, player1.getFaithTrackPosition());
        assertNotEquals(100, player2.getFaithTrackPosition());
        assertNotEquals(1, player3.getFaithTrackPosition());
        assertNotEquals(7, player4.getFaithTrackPosition());

        message.setError("SUCCESS");
        message.updateView(userInteraction);
        assertEquals(3, player1.getFaithTrackPosition());
        assertEquals(100, player2.getFaithTrackPosition());
        assertEquals(1, player3.getFaithTrackPosition());
        assertEquals(7, player4.getFaithTrackPosition());
    }
}
