package it.polimi.ingsw.Controller.Actions;

import it.polimi.ingsw.Model.*;
import it.polimi.ingsw.Model.MessagesToClient.*;

/**
 * ChooseCardSlot Class contains data and methods to resolve a Client's request when choosing
 * a card slot to put the just bought DevelopmentCard.
 */
public class ChooseCardSlot extends Action {
    private final int cardSlot;
    private int rowCardToBuy;
    private int columnCardToBuy;

    /**
     * Constructor for ChooseCardSlot Class.
     */
    public ChooseCardSlot(int cardSlot) {
        this.actionType = ActionType.CHOOSE_CARD_SLOT;
        this.cardSlot = cardSlot;
    }

    /**
     * Getter for "actionType" attribute in ChooseCardSlot.
     */
    public ActionType getActionType() {
        return actionType;
    }

    /**
     * Getter for "cardSlot" attribute in ChooseCardSlot.
     */
    public int getCardSlot() {
        return cardSlot;
    }

    /**
     * Getter for "rowCardToBuy" attribute in ChooseCardSlot.
     */
    public int getRowCardToBuy() {
        return rowCardToBuy;
    }

    /**
     * Setter for "rowCardToBuy" attribute in ChooseCardSlot.
     */
    public void setRowCardToBuy(int rowCardToBuy) {
        this.rowCardToBuy = rowCardToBuy;
    }

    /**
     * Getter for "columnCardToBuy" attribute in ChooseCardSlot.
     */
    public int getColumnCardToBuy() {
        return columnCardToBuy;
    }

    /**
     * Setter for "columnCardToBuy" attribute in ChooseCardSlot.
     */
    public void setColumnCardToBuy(int columnCardToBuy) {
        this.columnCardToBuy = columnCardToBuy;
    }

    /**
     * Method used to check if the specified action is formally correct.
     * @throws IllegalArgumentException if the row or column indexes are out of bounds.
     */
    @Override
    public boolean isCorrect() throws IllegalArgumentException {
        if(cardSlot < 0 || cardSlot > 2)
            throw new IllegalArgumentException("Card Slot index out of bounds.");
        return true;
    }

    /**
     * Method used to check if the specified action is logically correct.
     * @return true as there are no constraints regarding this Action.
     */
    @Override
    public boolean canBeApplied(Game game) {
        return true;
    }

    /**
     * Method used to execute the action on the Model.
     * @return "SUCCESS" if the action went right, another String if it went wrong.
     */
    @Override
    public String doAction(Game game, ChooseProductionOutput chooseProductionOutput, ChooseCardSlot chooseCardSlot, ResetWarehouse resetWarehouse) {
        this.isCorrect();

        DevelopmentCardSlots slots = game.getCurrentPlayer().getBoard().getDevelopmentCardSlots();
        DevelopmentCardDeck deck = game.getDevelopmentCardTable().getDeck(this.rowCardToBuy, this.columnCardToBuy);

        if(slots.getSlots()[this.cardSlot].canAdd(deck.getTopCard())) {
            slots.getSlots()[this.cardSlot].addCard(deck.drawCard());
            response = "SUCCESS";
            return "SUCCESS";
        }
        else {
            response = "Cannot put card in slot number " + this.cardSlot;
            return "Cannot put card in slot number " + this.cardSlot;
        }

    }

    /**
     * Method used to prepare a messageToClient type object to be sent by the server to the client.
     * @param game Current instance of the Game being played.
     * @return A message to be sent to the client.
     */
    public MessageToClient messagePrepare(Game game) {
        ChoseCardSlotMessage message = new ChoseCardSlotMessage(game.getCurrentPlayerIndex());
        message.setRow(this.rowCardToBuy);
        message.setColumn(this.columnCardToBuy);

        this.rowCardToBuy = 0;
        this.columnCardToBuy = 0;

        message.setSlot(this.cardSlot);
        message.setError(this.response);
        if(this.response.equals("SUCCESS")) {
            message.addPossibleAction(ActionType.END_TURN);
            message.addPossibleAction(ActionType.ACTIVATE_LEADERCARD);
        }
        else
            message.addPossibleAction(ActionType.CHOOSE_CARD_SLOT);

        return message;
    }
}
