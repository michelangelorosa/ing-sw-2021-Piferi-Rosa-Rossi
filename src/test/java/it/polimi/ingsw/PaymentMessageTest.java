package it.polimi.ingsw;


import static org.junit.Assert.*;
import it.polimi.ingsw.Model.MessagesToClient.ActivateLeaderCardMessage;
import it.polimi.ingsw.Model.MessagesToClient.PaymentMessage;
import org.junit.Test;

public class PaymentMessageTest {

    PaymentMessage paymentMessage = new PaymentMessage("antonino");

    @Test
    public void ConstructorTest(){
        assertEquals("antonino", paymentMessage.getPlayerNickname());
    }
}
