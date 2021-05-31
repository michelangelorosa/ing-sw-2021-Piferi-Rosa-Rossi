package it.polimi.ingsw.Controller.Actions;

import it.polimi.ingsw.Controller.ActionController;
import it.polimi.ingsw.Model.Enums.ResourceType;
import it.polimi.ingsw.Model.MessagesToClient.*;

/**
 * PayResourceBuyCard Class extends PayResource Class to diversify messages going to the Client
 * for a better response management.
 * @author redrick99
 */
public class PayResourceBuyCard extends PayResource {

    /**
     * Constructor for PayResourceBuyCard Class (uses superclass default constructor).
     */
    public PayResourceBuyCard(boolean fromWarehouse, int depot, ResourceType resourceType) {
        super(fromWarehouse, depot, resourceType);
        this.actionType = ActionType.PAY_RESOURCE_CARD;
    }

    /**
     * Prepares a BoughtCardMessage or PaymentMessage MessageToClient type object to be sent to the Client.
     * @param actionController Class used to compute Action messages coming from the Client.
     * @return A BoughtCardMessage if the player finished paying for a Development Card, or a PaymentMessage
     * if the player has <b>not</b> yet finished paying for said card.
     */
    @Override
    public MessageToClient messagePrepare(ActionController actionController) {
        BoughtCardMessage boughtCardMessage;

        if(this.response.equals(ILLEGAL_ACTION)) {
            boughtCardMessage = new BoughtCardMessage(actionController.getGame().getCurrentPlayerNickname());
            return illegalAction(boughtCardMessage, actionController);
        }

        if(this.response.equals("SUCCESS")) {
            boughtCardMessage = new BoughtCardMessage(actionController.getGame().getCurrentPlayerNickname());
            boughtCardMessage.setWarehouse(actionController.getGame().getCurrentPlayer().getBoard().getResourceManager().getWarehouse().toView());
            boughtCardMessage.setStrongbox(actionController.getGame().getCurrentPlayer().getBoard().getResourceManager().getStrongbox().toView());
            boughtCardMessage.addPossibleAction(ActionType.CHOOSE_CARD_SLOT);
            boughtCardMessage.setError(this.response);
            return boughtCardMessage;
        }
        else
            return this.hasToPay(actionController, true);
    }
}
