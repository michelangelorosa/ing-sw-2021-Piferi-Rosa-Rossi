package it.polimi.ingsw.ControllerTest;

import static it.polimi.ingsw.Controller.Actions.ActionType.CHOOSE_CARD_SLOT;
import static org.junit.Assert.*;

import it.polimi.ingsw.CommonTestMethods;
import it.polimi.ingsw.Controller.Actions.*;
import it.polimi.ingsw.Model.*;
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
     * This method checks if it possible to put the card in the slot.
     * If it can, the method returns "SUCCESS" and I check that it has actually stored the card in the slot
     * If it can not, it returns "Cannot put card in slot number #"
     */
    @Test
    public void doActionTest(){
        Game game = new Game();
        CommonTestMethods.gameInitOne(game);
        ChooseProductionOutput output = new ChooseProductionOutput();
        ChooseCardSlot cardSlot = new ChooseCardSlot(1);
        ResetWarehouse resetWarehouse = new ResetWarehouse();
        String response;

        DevelopmentCard card = game.getDevelopmentCardTable().getTopCardFromDeck(1,1);
        slot0.setRowCardToBuy(1);
        slot0.setColumnCardToBuy(1);
        response = slot0.doAction(game, output, cardSlot, resetWarehouse);
        assertEquals("Cannot put card in slot number 0", response);
        assertEquals(0, game.getCurrentPlayer().getBoard().getDevelopmentCardSlots().getSlots()[0].getLevelOccupied());
        slot1.setRowCardToBuy(2);
        slot1.setColumnCardToBuy(1);
        assertEquals("SUCCESS", slot1.doAction(game, output, cardSlot, resetWarehouse));
        assertEquals(1, game.getCurrentPlayer().getBoard().getDevelopmentCardSlots().getSlots()[1].getLevelOccupied());
        slot1.setRowCardToBuy(1);
        slot1.setColumnCardToBuy(1);
        assertEquals("SUCCESS", slot1.doAction(game, output, cardSlot, resetWarehouse));
        assertEquals(2, game.getCurrentPlayer().getBoard().getDevelopmentCardSlots().getSlots()[1].getLevelOccupied());
    }
}
