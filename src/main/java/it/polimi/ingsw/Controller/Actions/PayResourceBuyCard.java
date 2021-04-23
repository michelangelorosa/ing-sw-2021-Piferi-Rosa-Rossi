package it.polimi.ingsw.Controller.Actions;

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
     * @param game Current instance of the Game being played.
     * @return A message to be sent to the client.
     */
    @Override
    public MessageToClient messagePrepare(Game game) {
        PaymentMessage paymentMessage;
        BoughtCardMessage boughtCardMessage;

        if(this.response.equals("SUCCESS")) {
            boughtCardMessage = new BoughtCardMessage(game.getCurrentPlayerIndex());
            boughtCardMessage.setWarehouse(game.getCurrentPlayer().getBoard().getResourceManager().getWarehouse());
            boughtCardMessage.setStrongbox(game.getCurrentPlayer().getBoard().getResourceManager().getStrongbox());
            boughtCardMessage.addPossibleAction(ActionType.CHOOSE_CARD_SLOT);
            boughtCardMessage.setError(this.response);
            return boughtCardMessage;
        }
        else {
            paymentMessage = new PaymentMessage(game.getCurrentPlayerIndex());
            if(this.response.equals("HasToPay")) {
                paymentMessage.setWarehouse(game.getCurrentPlayer().getBoard().getResourceManager().getWarehouse());
                paymentMessage.setStrongbox(game.getCurrentPlayer().getBoard().getResourceManager().getStrongbox());
                paymentMessage.setTemporaryResources(game.getCurrentPlayer().getBoard().getResourceManager().getTemporaryResourcesToPay());
            }
            paymentMessage.setError(this.response);
            paymentMessage.addPossibleAction(ActionType.PAY_RESOURCE_CARD);
            paymentMessage.setActionDone(ActionType.PAY_RESOURCE_CARD);
            return paymentMessage;
        }
    }
}
