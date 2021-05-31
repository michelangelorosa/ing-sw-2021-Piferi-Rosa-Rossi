package it.polimi.ingsw.Controller.Actions;

import it.polimi.ingsw.Controller.ActionController;
import it.polimi.ingsw.Model.GameModel.DevelopmentCardDeck;
import it.polimi.ingsw.Model.GameModel.DevelopmentCardSlots;
import it.polimi.ingsw.Model.MessagesToClient.*;

/**
 * ChooseCardSlot Class contains data and methods to resolve a Client's request when choosing
 * a card slot to put the just bought DevelopmentCard.
 * <p>
 * <b>Attributes:</b>
 * <ul>
 *     <li>int "cardSlot": indicates where the player wants to put the Development Card.</li>
 *     <li>int "rowCardToBuy", "columnCardToBuy": they indicate the position of the DevelopmentCard
 *     bought by the player. <i>Automatically set when the player buys a new Card</i></li>
 * </ul>
 * @author redrick99
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
     * Getter for "cardSlot" attribute.
     */
    public int getCardSlot() {
        return cardSlot;
    }

    /**
     * Getter for "rowCardToBuy" attribute.
     */
    public int getRowCardToBuy() {
        return rowCardToBuy;
    }

    /**
     * Setter for "rowCardToBuy" attribute.
     */
    public void setRowCardToBuy(int rowCardToBuy) {
        this.rowCardToBuy = rowCardToBuy;
    }

    /**
     * Getter for "columnCardToBuy" attribute.
     */
    public int getColumnCardToBuy() {
        return columnCardToBuy;
    }

    /**
     * Setter for "columnCardToBuy" attribute.
     */
    public void setColumnCardToBuy(int columnCardToBuy) {
        this.columnCardToBuy = columnCardToBuy;
    }

    /**
     * Checks if the specified action is formally correct.
     * @return true if "cardSlot" is between 0 and 2.
     * @throws IllegalArgumentException if the "cardSlot" index is out of bounds.
     */
    @Override
    public boolean isCorrect() throws IllegalArgumentException {
        if(cardSlot < 0 || cardSlot > 2)
            throw new IllegalArgumentException("Card Slot index out of bounds.");
        return true;
    }

    /**
     * Controls and executes the action on the Model.
     * <p>Checks if the slot chosen by the player is suitable for a new Dev Card, then proceeds
     * to put the Card in said slot.</p>
     * @param actionController Class used to compute Action messages coming from the Client.
     * @return "SUCCESS" if the action went right, another String if it went wrong.
     */
    @Override
    public String doAction(ActionController actionController) {
        if(!this.canDoAction(actionController))
            return ILLEGAL_ACTION;

        this.isCorrect();

        DevelopmentCardSlots slots = actionController.getGame().getCurrentPlayer().getBoard().getDevelopmentCardSlots();
        DevelopmentCardDeck deck = actionController.getGame().getDevelopmentCardTable().getDeck(this.rowCardToBuy, this.columnCardToBuy);

        if(slots.getSlots()[this.cardSlot].canAdd(deck.getTopCard())) {
            slots.getSlots()[this.cardSlot].addCard(deck.drawCard());
            response = "SUCCESS";
            actionController.getGame().getCurrentPlayer().clearPossibleActions();
            actionController.getGame().getCurrentPlayer().addPossibleAction(ActionType.END_TURN);
            return "SUCCESS";
        }
        else {
            response = "Cannot put card in slot number " + this.cardSlot;
            return "Cannot put card in slot number " + this.cardSlot;
        }

    }

    /**
     * Prepares a ChoseCardSlot MessageToClient type object to be sent to the Client.
     * @param actionController Class used to compute Action messages coming from the Client.
     * @return A message to be sent to the Client.
     */
    public MessageToClient messagePrepare(ActionController actionController) {
        ChoseCardSlotMessage message = new ChoseCardSlotMessage(actionController.getGame().getCurrentPlayerNickname());
        if(this.response.equals(ILLEGAL_ACTION))
            return illegalAction(message, actionController);

        message.setTable(actionController.getGame().getDevelopmentCardTable());
        message.setError(this.response);

        message.setSlots(actionController.getGame().getCurrentPlayer().getBoard().getDevelopmentCardSlots());
        if(this.response.equals("SUCCESS")) {
            this.rowCardToBuy = 0;
            this.columnCardToBuy = 0;
            message.addPossibleAction(ActionType.END_TURN);

            if(actionController.getGame().getCurrentPlayer().canDo(ActionType.ACTIVATE_LEADERCARD))
                message.addPossibleAction(ActionType.ACTIVATE_LEADERCARD);
        }
        else
            message.addPossibleAction(ActionType.CHOOSE_CARD_SLOT);

        return message;
    }
}
