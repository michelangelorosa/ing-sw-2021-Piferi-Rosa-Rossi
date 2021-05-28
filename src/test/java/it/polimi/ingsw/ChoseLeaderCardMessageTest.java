package it.polimi.ingsw;

import static it.polimi.ingsw.Controller.Actions.ActionType.CHOOSE_LEADER_CARD;
import static org.junit.Assert.*;
import it.polimi.ingsw.Model.MessagesToClient.ChoseLeaderCardMessage;
import org.junit.Test;

public class ChoseLeaderCardMessageTest {
    ChoseLeaderCardMessage leaderCardMessage = new ChoseLeaderCardMessage("antonino");

    @Test
    public void ConstructorTest(){
        assertEquals(CHOOSE_LEADER_CARD, leaderCardMessage.getActionDone());
        assertEquals("antonino", leaderCardMessage.getPlayerNickname());
    }

}
