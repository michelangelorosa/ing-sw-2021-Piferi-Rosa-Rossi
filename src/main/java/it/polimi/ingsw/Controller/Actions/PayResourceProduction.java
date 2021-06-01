package it.polimi.ingsw.Controller.Actions;

import it.polimi.ingsw.Controller.ControllerClasses.ActionController;
import it.polimi.ingsw.Model.Enums.ResourceType;
import it.polimi.ingsw.Model.MessagesToClient.*;

/**
 * PayResourceProduction Class extends PayResource Class to diversify messages going to the Client
 * for a better response management.
 * @author redrick99
 */
public class PayResourceProduction extends PayResource {

    /**
     * Constructor for PayResourceProduction Class (uses superclass default constructor).
     */
    public PayResourceProduction(boolean fromWarehouse, int depot, ResourceType resourceType) {
        super(fromWarehouse, depot, resourceType);
        this.actionType = ActionType.PAY_RESOURCE_PRODUCTION;
    }

    /**
     * Prepares a EndProductionMessage or PaymentMessage MessageToClient type object to be sent to the Client.
     * @param actionController Class used to compute Action messages coming from the Client.
     * @return A EndProductionMessage if the player has finished paying for a Production, or a PaymentMessage
     * if the player has <b>not</b> yet finished paying for a Production.
     */
    @Override
    public MessageToClient messagePrepare(ActionController actionController) {
        EndProductionMessage endProductionMessage;

        if(this.response.equals(ILLEGAL_ACTION)) {
            endProductionMessage = new EndProductionMessage(actionController.getGame().getCurrentPlayerNickname());
            return illegalAction(endProductionMessage, actionController);
        }

        if(this.response.equals("SUCCESS")) {
            endProductionMessage = new EndProductionMessage(actionController.getGame().getCurrentPlayerNickname());
            endProductionMessage.setWarehouse(actionController.getGame().getCurrentPlayer().getBoard().getResourceManager().getWarehouse().toView());
            endProductionMessage.setStrongbox(actionController.getGame().getCurrentPlayer().getBoard().getResourceManager().getStrongbox().toView());
            endProductionMessage.addPossibleAction(ActionType.CHOOSE_PRODUCTION_OUTPUT);
            endProductionMessage.setError(this.response);
            return endProductionMessage;
        }
        else
            return this.hasToPay(actionController, false);
    }
}
