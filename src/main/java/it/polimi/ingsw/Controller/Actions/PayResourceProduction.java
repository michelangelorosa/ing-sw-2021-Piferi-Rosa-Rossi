package it.polimi.ingsw.Controller.Actions;

import it.polimi.ingsw.Model.*;
import it.polimi.ingsw.Model.MessagesToClient.*;

/**
 * PayResourceProduction Class extends PayResource Class to diversify messages going to the Client
 * for a better response management.
 */
public class PayResourceProduction extends PayResource {

    /**
     * Constructor for PayResourceProduction Class (uses superclass default constructor).
     */
    public PayResourceProduction(boolean fromWarehouse, int depot, ResourceType resourceType) {
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
        EndProductionMessage endProductionMessage;

        if(this.response.equals("SUCCESS")) {
            endProductionMessage = new EndProductionMessage(game.getCurrentPlayerIndex());
            endProductionMessage.setWarehouse(game.getCurrentPlayer().getBoard().getResourceManager().getWarehouse());
            endProductionMessage.setStrongbox(game.getCurrentPlayer().getBoard().getResourceManager().getStrongbox());
            endProductionMessage.addPossibleAction(ActionType.CHOOSE_PRODUCTION_OUTPUT);
            endProductionMessage.setError(this.response);
            return endProductionMessage;
        }
        else {
            paymentMessage = new PaymentMessage(game.getCurrentPlayerIndex());
            if(this.response.equals("HasToPay")) {
                paymentMessage.setWarehouse(game.getCurrentPlayer().getBoard().getResourceManager().getWarehouse());
                paymentMessage.setStrongbox(game.getCurrentPlayer().getBoard().getResourceManager().getStrongbox());
                paymentMessage.setTemporaryResources(game.getCurrentPlayer().getBoard().getResourceManager().getTemporaryResourcesToPay());
            }
            paymentMessage.setError(this.response);
            paymentMessage.addPossibleAction(ActionType.PAY_RESOURCE_PRODUCTION);
            paymentMessage.setActionDone(ActionType.PAY_RESOURCE_PRODUCTION);
            return paymentMessage;
        }
    }
}
