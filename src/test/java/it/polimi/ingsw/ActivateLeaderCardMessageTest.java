package it.polimi.ingsw;

import static it.polimi.ingsw.Controller.Actions.ActionType.ACTIVATE_LEADERCARD;
import static org.junit.Assert.*;
import it.polimi.ingsw.Model.MessagesToClient.ActivateLeaderCardMessage;
import org.junit.Test;

public class ActivateLeaderCardMessageTest {

    ActivateLeaderCardMessage leaderMessage = new ActivateLeaderCardMessage("antonino");

    @Test
    public void ConstructorTest(){
        assertEquals(ACTIVATE_LEADERCARD, leaderMessage.getActionDone());
        assertEquals("antonino", leaderMessage.getPlayerNickname());
    }
}
