package it.polimi.ingsw;

import static it.polimi.ingsw.Controller.Actions.ActionType.ACTIVATE_LEADERCARD;
import static org.junit.Assert.*;
import it.polimi.ingsw.Model.MessagesToClient.ActivateLeaderCardMessage;
import org.junit.Test;

public class ActivateLeaderCardMessageTest {

    ActivateLeaderCardMessage leaderMessage = new ActivateLeaderCardMessage(2);

    @Test
    public void ConstructorTest(){
        assertEquals(ACTIVATE_LEADERCARD, leaderMessage.getActionDone());
        assertEquals(2, leaderMessage.getPlayerPosition());
    }
}
