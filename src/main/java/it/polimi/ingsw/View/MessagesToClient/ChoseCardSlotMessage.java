package it.polimi.ingsw.View.MessagesToClient;


public class ChoseCardSlotMessage extends MessageToClient {
    /** row and column of the deck and the slot int position are required to update the Client's View */
    private int row;
    private int column;
    private int slot;

    /**
     * Constructor for ChooseCardSlotMessage Class
     */
    public ChoseCardSlotMessage(int playerId) {
        this.actionDone = ActionType.CHOOSE_CARD_SLOT;
        this.playerId = playerId;
    }
}
