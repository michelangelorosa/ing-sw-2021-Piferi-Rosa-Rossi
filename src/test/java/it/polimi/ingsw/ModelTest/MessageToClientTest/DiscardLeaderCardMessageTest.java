package it.polimi.ingsw.ModelTest.MessageToClientTest;

import it.polimi.ingsw.Model.MessagesToClient.DiscardLeaderCardMessage;
import org.junit.Test;

import static it.polimi.ingsw.Controller.Actions.ActionType.*;
import static org.junit.Assert.assertEquals;

public class DiscardLeaderCardMessageTest {
    DiscardLeaderCardMessage leaderCardMessage = new DiscardLeaderCardMessage("Pierino");

    @Test
    public void constructorTest(){
        assertEquals(DELETE_LEADERCARD, leaderCardMessage.getActionDone());
        assertEquals("Pierino", leaderCardMessage.getPlayerNickname());
    }
}
