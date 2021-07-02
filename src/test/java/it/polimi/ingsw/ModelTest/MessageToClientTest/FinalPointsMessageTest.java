package it.polimi.ingsw.ModelTest.MessageToClientTest;

import it.polimi.ingsw.Model.Enums.PlayerStatus;
import it.polimi.ingsw.Model.MessagesToClient.FinalPointsMessage;
import it.polimi.ingsw.View.User.UserInteraction;
import it.polimi.ingsw.ViewTest.CommonViewTestMethods;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * Tests for FinalPointsMessage Class.
 */
public class FinalPointsMessageTest {

    UserInteraction userInteraction = CommonViewTestMethods.createUserInteraction();
    FinalPointsMessage message = new FinalPointsMessage("antonio");

    /**
     * Test for constructor, getters and setters.
     */
    @Test
    public void genericMethodsTest() {
        HashMap<String, Integer> namePoints = new HashMap<>();
        namePoints.put("antonio", 10);
        namePoints.put("Lorenzo il Magnifico", 1000);
        namePoints.put("franco", 0);
        namePoints.put("gianpiero", 7);

        message.setNicknamePoints(namePoints);
        message.addWinningPlayers("Lorenzo il Magnifico");

        assertEquals(namePoints, message.getNicknamePoints());
        assertEquals(1, message.getWinningPlayers().size());
        assertEquals("Lorenzo il Magnifico", message.getWinningPlayers().get(0));
    }

    /**
     * updateView method Test.
     */
    @Test
    public void updateViewTest() {
        HashMap<String, Integer> namePoints = new HashMap<>();
        namePoints.put("antonio", 10);
        namePoints.put("Lorenzo il Magnifico", 1000);
        namePoints.put("franco", 0);
        namePoints.put("gianpiero", 7);

        message.setNicknamePoints(namePoints);
        message.addWinningPlayers("Lorenzo il Magnifico");

        message.updateView(userInteraction);

        assertEquals(PlayerStatus.LOST, userInteraction.getGame().getPlayers().get(0).getStatus());
        assertEquals(PlayerStatus.WON, userInteraction.getGame().getPlayers().get(1).getStatus());
        assertEquals(PlayerStatus.LOST, userInteraction.getGame().getPlayers().get(2).getStatus());
        assertEquals(PlayerStatus.LOST, userInteraction.getGame().getPlayers().get(3).getStatus());

        assertEquals(10, userInteraction.getGame().getPlayers().get(0).getVictoryPoints());
        assertEquals(1000, userInteraction.getGame().getPlayers().get(1).getVictoryPoints());
        assertEquals(0, userInteraction.getGame().getPlayers().get(2).getVictoryPoints());
        assertEquals(7, userInteraction.getGame().getPlayers().get(3).getVictoryPoints());
    }
}
