package it.polimi.ingsw.ControllerTest;

import static it.polimi.ingsw.Controller.Actions.ActionType.ACTIVATE_LEADERCARD;
import static it.polimi.ingsw.Controller.Actions.ActionType.CHOOSE_CARD_SLOT;
import static org.junit.Assert.*;

import it.polimi.ingsw.CommonTestMethods;
import it.polimi.ingsw.Controller.ActionController;
import it.polimi.ingsw.Controller.Actions.*;
import it.polimi.ingsw.Model.GameModel.Game;
import it.polimi.ingsw.Model.MessagesToClient.ChoseCardSlotMessage;
import it.polimi.ingsw.Model.MessagesToClient.MessageToClient;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ChooseCardSlotTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    ChooseCardSlot slot0 = new ChooseCardSlot(0);
    ChooseCardSlot slot1 = new ChooseCardSlot(1);
    ChooseCardSlot slot2 = new ChooseCardSlot(2);
    ChooseCardSlot slotExtra1 = new ChooseCardSlot(3);
    ChooseCardSlot slotExtra2 = new ChooseCardSlot(4);
    ActionController actionController = new ActionController();

    MessageToClient messageToClient;

    /**Getter and Setter Test*/
    @Test
    public void getActionTest(){
        assertEquals(CHOOSE_CARD_SLOT, slot1.getActionType());
        assertEquals(2, slot2.getCardSlot());
        assertEquals(0, slot1.getRowCardToBuy());
        assertEquals(0, slot1.getColumnCardToBuy());
        slotExtra1.setRowCardToBuy(2);
        slotExtra2.setColumnCardToBuy(5);
        assertEquals(2, slotExtra1.getRowCardToBuy());
        assertEquals(5, slotExtra2.getColumnCardToBuy());
    }

    /**Test to check if the card slot number is correct*/
    @Test
    public void isCorrectTest(){
        ChooseCardSlot cardSlotErr = new ChooseCardSlot(5);
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Card Slot index out of bounds.");
        cardSlotErr.isCorrect();
    }

    /**
     * Test for "doAction" and "messagePrepare" methods.
     * This method checks if it possible to put the card in the slot.
     * If it can, the method returns "SUCCESS" and I check that it has actually stored the card in the slot
     * If it can not, it returns "Cannot put card in slot number #"
     */
    @Test
    public void doActionTest(){
        Game game = actionController.getGame();
        CommonTestMethods.gameInitOne(game);
        String response;

        game.getCurrentPlayer().addPossibleAction(CHOOSE_CARD_SLOT);
        game.getCurrentPlayer().addPossibleAction(ACTIVATE_LEADERCARD);

        slot0.setRowCardToBuy(1);
        slot0.setColumnCardToBuy(1);
        response = slot0.doAction(actionController);
        assertEquals("Cannot put card in slot number 0", response);
        assertEquals(0, game.getCurrentPlayer().getBoard().getDevelopmentCardSlots().getSlots()[0].getLevelOccupied());
        messageToClient = slot0.messagePrepare(actionController);

        assertTrue(messageToClient instanceof ChoseCardSlotMessage);
        assertEquals(CHOOSE_CARD_SLOT, messageToClient.getActionDone());
        assertEquals("Cannot put card in slot number 0", messageToClient.getError());
        assertEquals(game.getCurrentPlayerNickname(), messageToClient.getPlayerNickname());
        assertEquals(CHOOSE_CARD_SLOT, messageToClient.getPossibleActions().get(0));


        slot1.setRowCardToBuy(2);
        slot1.setColumnCardToBuy(1);
        assertEquals("SUCCESS", slot1.doAction(actionController));
        assertEquals(1, game.getCurrentPlayer().getBoard().getDevelopmentCardSlots().getSlots()[1].getLevelOccupied());
        messageToClient = slot1.messagePrepare(actionController);

        assertTrue(messageToClient instanceof ChoseCardSlotMessage);
        assertEquals(CHOOSE_CARD_SLOT, messageToClient.getActionDone());
        assertEquals("SUCCESS", messageToClient.getError());
        assertEquals(game.getCurrentPlayerNickname(), messageToClient.getPlayerNickname());
        assertEquals(ActionType.END_TURN, messageToClient.getPossibleActions().get(0));
        assertEquals(ActionType.ACTIVATE_LEADERCARD, messageToClient.getPossibleActions().get(1));

        game.getCurrentPlayer().addPossibleAction(CHOOSE_CARD_SLOT);

        slot1.setRowCardToBuy(1);
        slot1.setColumnCardToBuy(1);
        assertEquals("SUCCESS", slot1.doAction(actionController));
        assertEquals(2, game.getCurrentPlayer().getBoard().getDevelopmentCardSlots().getSlots()[1].getLevelOccupied());
        messageToClient = slot1.messagePrepare(actionController);

        assertTrue(messageToClient instanceof ChoseCardSlotMessage);
        assertEquals(CHOOSE_CARD_SLOT, messageToClient.getActionDone());
        assertEquals("SUCCESS", messageToClient.getError());
        assertEquals(game.getCurrentPlayerNickname(), messageToClient.getPlayerNickname());
        assertEquals(ActionType.END_TURN, messageToClient.getPossibleActions().get(0));
        assertEquals(ActionType.ACTIVATE_LEADERCARD, messageToClient.getPossibleActions().get(1));

        //TODO check ChoseCardSlot message attributes.
    }
}
