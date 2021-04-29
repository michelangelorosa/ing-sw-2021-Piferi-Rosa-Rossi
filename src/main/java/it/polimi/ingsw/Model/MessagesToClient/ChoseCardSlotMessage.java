package it.polimi.ingsw.Model.MessagesToClient;

import it.polimi.ingsw.Controller.Actions.ActionType;

/**
 * ChoseCardSlotMessage Class contains data for a response message to be sent to the client
 * after a ChooseCardSlot request.
 */
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

    /**
     * Setter for "row" attribute in ChoseCardSlotMessage Class.
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * Setter for "column" attribute in ChoseCardSlotMessage Class.
     */
    public void setColumn(int column) {
        this.column = column;
    }

    /**
     * Setter for "slot" attribute in ChoseCardSlotMessage Class.
     */
    public void setSlot(int slot) {
        this.slot = slot;
    }

    /**
     * Getter for "row" attribute in ChoseCardSlotMessage Class.
     */
    public int getRow() {
        return row;
    }

    /**
     * Getter for "column" attribute in ChoseCardSlotMessage Class.
     */
    public int getColumn() {
        return column;
    }

    /**
     * Getter for "slot" attribute in ChoseCardSlotMessage Class.
     */
    public int getSlot() {
        return slot;
    }
}