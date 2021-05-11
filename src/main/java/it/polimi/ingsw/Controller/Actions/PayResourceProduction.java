package it.polimi.ingsw.Controller.Actions;

import it.polimi.ingsw.Controller.ActionController;
import it.polimi.ingsw.Model.Enums.ResourceType;
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
     * @return A message to be sent to the client.
     */
    @Override
    public MessageToClient messagePrepare(ActionController actionController) {
        EndProductionMessage endProductionMessage;

        if(this.response.equals("SUCCESS")) {
            endProductionMessage = new EndProductionMessage(actionController.getGame().getCurrentPlayerIndex());
            endProductionMessage.setWarehouse(actionController.getGame().getCurrentPlayer().getBoard().getResourceManager().getWarehouse());
            endProductionMessage.setStrongbox(actionController.getGame().getCurrentPlayer().getBoard().getResourceManager().getStrongbox());
            endProductionMessage.addPossibleAction(ActionType.CHOOSE_PRODUCTION_OUTPUT);
            endProductionMessage.setError(this.response);
            return endProductionMessage;
        }
        else
            return this.hasToPay(actionController, false);
    }
}
