package it.polimi.ingsw.Controller.Actions;

import it.polimi.ingsw.Model.DevelopmentCardDeck;
import it.polimi.ingsw.Model.DevelopmentCardSlots;
import it.polimi.ingsw.Model.Game;

public class ChooseCardSlot {
    private final ActionType actionType;
    private final int cardSlot;
    private int rowCardToBuy;
    private int columnCardToBuy;

    public ChooseCardSlot(int cardSlot) {
        this.actionType = ActionType.CHOOSE_CARD_SLOT;
        this.cardSlot = cardSlot;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public int getCardSlot() {
        return cardSlot;
    }

    public int getRowCardToBuy() {
        return rowCardToBuy;
    }

    public void setRowCardToBuy(int rowCardToBuy) {
        this.rowCardToBuy = rowCardToBuy;
    }

    public int getColumnCardToBuy() {
        return columnCardToBuy;
    }

    public void setColumnCardToBuy(int columnCardToBuy) {
        this.columnCardToBuy = columnCardToBuy;
    }

    public boolean isCorrect() throws IllegalArgumentException {
        if(cardSlot < 0 || cardSlot > 2)
            throw new IllegalArgumentException("Card Slot index out of bounds.");
        return true;
    }

    public String chooseCardSlot(Game game) {
        this.isCorrect();

        DevelopmentCardSlots slots = game.getCurrentPlayer().getBoard().getDevelopmentCardSlots();
        DevelopmentCardDeck deck = game.getDevelopmentCardTable().getDeck(this.rowCardToBuy, this.columnCardToBuy);

        if(slots.getSlots()[this.cardSlot].canAdd(deck.getTopCard())) {
            slots.getSlots()[this.cardSlot].addCard(deck.drawCard());
            this.rowCardToBuy = 0;
            this.columnCardToBuy = 0;
            return "SUCCESS";
        }
        else
            return "Cannot put card in slot number "+this.cardSlot;

    }
}
