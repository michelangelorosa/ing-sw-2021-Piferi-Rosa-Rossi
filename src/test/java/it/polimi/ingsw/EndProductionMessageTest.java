package it.polimi.ingsw;

import static it.polimi.ingsw.Controller.Actions.ActionType.END_PAY_PRODUCTION;
import static org.junit.Assert.*;
import it.polimi.ingsw.Model.MessagesToClient.EndProductionMessage;
import org.junit.Test;

public class EndProductionMessageTest {

    EndProductionMessage productionMessage = new EndProductionMessage(2);

    @Test
    public void ConstructorTest(){
        assertEquals(END_PAY_PRODUCTION, productionMessage.getActionDone());
        assertEquals(2, productionMessage.getPlayerPosition());
    }
}
