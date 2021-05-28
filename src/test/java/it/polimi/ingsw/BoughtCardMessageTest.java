package it.polimi.ingsw;

import it.polimi.ingsw.Model.MessagesToClient.BoughtCardMessage;
import org.junit.Test;

import static it.polimi.ingsw.Controller.Actions.ActionType.END_PAY_CARD;
import static org.junit.Assert.*;

public class BoughtCardMessageTest {

    BoughtCardMessage cardMessage = new BoughtCardMessage(3);

    @Test
    public void ConstructorTest(){
        assertEquals(END_PAY_CARD, cardMessage.getActionDone());
        assertEquals(3, cardMessage.getPlayerPosition());
    }
}
