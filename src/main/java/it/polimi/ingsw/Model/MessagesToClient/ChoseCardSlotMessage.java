package it.polimi.ingsw.Model.MessagesToClient;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.View.ReducedModel.*;
import it.polimi.ingsw.View.User.UserInteraction;

/**
 * ChoseCardSlotMessage Class contains data for a response message to be sent to the client
 * after a ChooseCardSlot request.
 * <p><b>Attributes:</b></p>
 * <ul>
 *     <li>RedDevelopmentCardSlots "slots": new Development Card Slots to be set to the corresponding
 *     player when updating the Client's View</li>
 *     <li>RedDevelopmentCardTable "table": new Development Card Table to be set to the Client's Game
 *     when updating the View</li>
 * </ul>
 * @author redrick99
 */
public class ChoseCardSlotMessage extends MessageToClient {
    private RedDevelopmentCardSlots slots;
    private RedDevelopmentCardTable table;

    /**
     * Constructor for ChooseCardSlotMessage Class.
     */
    public ChoseCardSlotMessage(String playerNickname) {
        super(playerNickname);
        this.actionDone = ActionType.CHOOSE_CARD_SLOT;
    }

    /**
     * Getter for "slots" attribute.
     */
    public RedDevelopmentCardSlots getSlots() {
        return slots;
    }

    /**
     * Getter for "slots" attribute.
     */
    public void setSlots(RedDevelopmentCardSlots slots) {
        this.slots = slots;
    }

    /**
     * Getter for "table" attribute.
     */
    public RedDevelopmentCardTable getTable() {
        return table;
    }

    /**
     * Setter for "table" attribute.
     */
    public void setTable(RedDevelopmentCardTable table) {
        this.table = table;
    }

    /**
     * Checks if the message contains an error and updates the Client's view.
     * @param userInteraction Class containing the Reduced Game and the User Interface.
     */
    @Override
    public void updateView(UserInteraction userInteraction) {
        super.updateView(userInteraction);

        Player player = this.getPlayer(userInteraction);

        if(success()) {
            player.setSlots(this.slots);
            userInteraction.getGame().setDevelopmentCardTable(this.table);

            this.displayAction(userInteraction);
        }
        else
            this.displayError(userInteraction);
    }
}
