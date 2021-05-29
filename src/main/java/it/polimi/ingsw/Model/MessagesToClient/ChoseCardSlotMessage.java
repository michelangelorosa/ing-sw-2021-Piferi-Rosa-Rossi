package it.polimi.ingsw.Model.MessagesToClient;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.View.ReducedModel.*;

/**
 * ChoseCardSlotMessage Class contains data for a response message to be sent to the client
 * after a ChooseCardSlot request.
 */
public class ChoseCardSlotMessage extends MessageToClient {
    private RedDevelopmentCardSlots slots;
    private RedDevelopmentCardTable table;

    /**
     * Constructor for ChooseCardSlotMessage Class
     */
    public ChoseCardSlotMessage(String playerNickname) {
        super(playerNickname);
        this.actionDone = ActionType.CHOOSE_CARD_SLOT;
    }

    public RedDevelopmentCardSlots getSlots() {
        return slots;
    }

    public void setSlots(RedDevelopmentCardSlots slots) {
        this.slots = slots;
    }

    public RedDevelopmentCardTable getTable() {
        return table;
    }

    public void setTable(RedDevelopmentCardTable table) {
        this.table = table;
    }

    /**
     * Method used to update the client's view.
     * @param userInteraction Class containing the Reduced Game and methods to display Errors.
     */
    @Override
    public void updateView(UserInteraction userInteraction) {
        if(this.error.equals("SUCCESS")) {
            userInteraction.getGame().setDevelopmentCardTable(this.table);
            Game game = userInteraction.getGame();
            for (Player player : game.getPlayers())
                if (player.getNickname().equals(this.getPlayerNickname())) {
                    player.setSlots(this.slots);
                }

        }
        else {
            userInteraction.getUi().displayError(this.error);
        }
    }
}
