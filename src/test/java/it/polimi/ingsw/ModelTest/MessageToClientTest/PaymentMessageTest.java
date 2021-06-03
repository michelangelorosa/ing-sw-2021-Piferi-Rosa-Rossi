package it.polimi.ingsw.ModelTest.MessageToClientTest;


import static org.junit.Assert.*;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.Model.GameModel.ResourceStack;
import it.polimi.ingsw.Model.GameModel.Strongbox;
import it.polimi.ingsw.Model.GameModel.Warehouse;
import it.polimi.ingsw.Model.MessagesToClient.PaymentMessage;
import it.polimi.ingsw.View.ReducedModel.Player;
import it.polimi.ingsw.View.ReducedModel.RedResourceStack;
import it.polimi.ingsw.View.ReducedModel.RedStrongbox;
import it.polimi.ingsw.View.ReducedModel.RedWarehouse;
import it.polimi.ingsw.View.User.UserInteraction;
import it.polimi.ingsw.ViewTest.CommonViewTestMethods;
import org.junit.Test;

public class PaymentMessageTest {

    PaymentMessage paymentMessage = new PaymentMessage("franco");
    PaymentMessage paymentMessage1 = new PaymentMessage("franco");

    RedWarehouse messageWarehouse = new Warehouse();
    RedStrongbox messageStrongbox = new Strongbox();
    RedResourceStack messageTemporaryResources = new ResourceStack(1, 0, 2, 0);

    UserInteraction userInteraction = CommonViewTestMethods.createUserInteraction();


    @Test
    public void genericMethodsTest(){
        paymentMessage.setActionDone(ActionType.PAY_RESOURCE_CARD);
        paymentMessage1.setActionDone(ActionType.PAY_RESOURCE_PRODUCTION);
        paymentMessage.setStrongbox(messageStrongbox);
        paymentMessage.setTemporaryResources(messageTemporaryResources);
        paymentMessage.setWarehouse(messageWarehouse);
        assertEquals("franco", paymentMessage.getPlayerNickname());
        assertEquals(ActionType.PAY_RESOURCE_CARD, paymentMessage.getActionDone());
        assertEquals(ActionType.PAY_RESOURCE_PRODUCTION, paymentMessage1.getActionDone());
        assertEquals(messageStrongbox, paymentMessage.getStrongbox());
        assertEquals(messageWarehouse, paymentMessage.getWarehouse());
        assertEquals(messageTemporaryResources, paymentMessage.getTemporaryResources());
    }

    @Test
    public void updateView1Test(){
        paymentMessage.setActionDone(ActionType.PAY_RESOURCE_CARD);
        paymentMessage1.setActionDone(ActionType.PAY_RESOURCE_PRODUCTION);
        paymentMessage.setStrongbox(messageStrongbox);
        paymentMessage.setTemporaryResources(messageTemporaryResources);
        paymentMessage.setWarehouse(messageWarehouse);

        paymentMessage.setError("error");
        paymentMessage.updateView(userInteraction);

        Player player = CommonViewTestMethods.findPlayerByNickname(userInteraction.getGame(), "franco");
        assert player != null;

        assertNotEquals(messageWarehouse, player.getWarehouse());
        assertNotEquals(messageStrongbox, player.getStrongbox());
        assertNotEquals(messageTemporaryResources, player.getTemporaryResources());

        paymentMessage.setError("HasToPay");
        paymentMessage.updateView(userInteraction);
        assertEquals(messageWarehouse, player.getWarehouse());
        assertEquals(messageStrongbox, player.getStrongbox());
        assertEquals(messageTemporaryResources, player.getTemporaryResources());
    }

    @Test
    public void updateView2Test(){
        paymentMessage1.setActionDone(ActionType.PAY_RESOURCE_PRODUCTION);
        paymentMessage1.setStrongbox(messageStrongbox);
        paymentMessage1.setTemporaryResources(messageTemporaryResources);
        paymentMessage1.setWarehouse(messageWarehouse);

        paymentMessage1.setError("error");
        paymentMessage1.updateView(userInteraction);

        Player player = CommonViewTestMethods.findPlayerByNickname(userInteraction.getGame(), "franco");
        assert player != null;

        assertNotEquals(messageWarehouse, player.getWarehouse());
        assertNotEquals(messageStrongbox, player.getStrongbox());
        assertNotEquals(messageTemporaryResources, player.getTemporaryResources());

        paymentMessage1.setError("HasToPay");
        paymentMessage1.updateView(userInteraction);
        assertEquals(messageWarehouse, player.getWarehouse());
        assertEquals(messageStrongbox, player.getStrongbox());
        assertEquals(messageTemporaryResources, player.getTemporaryResources());
    }
}
