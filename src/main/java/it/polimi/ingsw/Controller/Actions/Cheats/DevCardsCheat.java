package it.polimi.ingsw.Controller.Actions.Cheats;

import it.polimi.ingsw.Controller.Actions.Action;
import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.Controller.ControllerClasses.ActionController;
import it.polimi.ingsw.Model.Enums.Color;
import it.polimi.ingsw.Model.Enums.Level;
import it.polimi.ingsw.Model.Exceptions.ModelException;
import it.polimi.ingsw.Model.GameModel.*;
import it.polimi.ingsw.Model.MessagesToClient.MessageToClient;
import it.polimi.ingsw.Model.Server.Server;

/**
 * DevCardsCheat Class is a special Action to be used when Cheat Mode is enabled to give Development Cards to the player.
 */
public class DevCardsCheat extends Action {

    /**
     * Constructor for DevCardsCheat Class.
     */
    public DevCardsCheat() {
        this.actionType = ActionType.DEVCARDS_CHEAT;
    }

    /**
     * Creates cards to be given to the player, already stored in Development Card Slots.
     * @param slots DevelopmentCardSlots where the cards are put.
     */
    private void setCards(ActionController actionController, DevelopmentCardSlots slots) {
        DevelopmentCardTable table = actionController.getGame().getDevelopmentCardTable();

        slots.addCard(0, table.getCardFromId(1));
        slots.addCard(0, table.getCardFromId(17));
        slots.addCard(0, table.getCardFromId(33));

        slots.addCard(1, table.getCardFromId(2));
        slots.addCard(1, table.getCardFromId(18));
        slots.addCard(1, table.getCardFromId(34));

        slots.getSlots()[0].setLevelOccupied(3);
        slots.getSlots()[1].setLevelOccupied(3);

    }

    /**
     * Performs the specified cheat Action on the Server's Model.
     * @param actionController Class used to compute Action messages coming from the Client.
     * @return "SUCCESS" if Cheat Mode is enabled on the Server.
     */
    @Override
    public String doAction(ActionController actionController) {
        if(Server.isCheatMode()) {
            setCards(actionController, actionController.getGame().getCurrentPlayer().getBoard().getDevelopmentCardSlots());
            this.response = "SUCCESS";
            return "SUCCESS";
        }
        else
            this.response = "{ ! CHEAT MODE NOT ACTIVATED ! }";
        return "{ ! CHEAT MODE NOT ACTIVATED ! }";
    }

    /**
     * Prepares a generic CheatMessage to be sent to the Client.
     * @param actionController Class used to compute Action messages coming from the Client.
     * @return a CheatMessage.
     */
    @Override
    public MessageToClient messagePrepare(ActionController actionController) {
        return this.cheatMessage(actionController);
    }
}
