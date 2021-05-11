package it.polimi.ingsw.Controller.Actions;

import it.polimi.ingsw.Controller.ActionController;
import it.polimi.ingsw.Model.*;
import it.polimi.ingsw.Model.MessagesToClient.*;

/**
 * PayResourceBuyCard Class extends PayResource Class to diversify messages going to the Client
 * for a better response management.
 */
public class PayResourceBuyCard extends PayResource {

    /**
     * Constructor for PayResourceBuyCard Class (uses superclass default constructor).
     */
    public PayResourceBuyCard(boolean fromWarehouse, int depot, ResourceType resourceType) {
        super(fromWarehouse, depot, resourceType);
    }

    /**
     * Method used to prepare a messageToClient type object to be sent by the server to the client.
     * @return A message to be sent to the client.
     */
    @Override
    public MessageToClient messagePrepare(ActionController actionController) {
        BoughtCardMessage boughtCardMessage;

        if(this.response.equals("SUCCESS")) {
            boughtCardMessage = new BoughtCardMessage(actionController.getGame().getCurrentPlayerIndex());
            boughtCardMessage.setWarehouse(actionController.getGame().getCurrentPlayer().getBoard().getResourceManager().getWarehouse());
            boughtCardMessage.setStrongbox(actionController.getGame().getCurrentPlayer().getBoard().getResourceManager().getStrongbox());
            boughtCardMessage.addPossibleAction(ActionType.CHOOSE_CARD_SLOT);
            boughtCardMessage.setError(this.response);
            return boughtCardMessage;
        }
        else
            return this.hasToPay(actionController, true);
    }
}
