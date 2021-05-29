package it.polimi.ingsw.Controller.Actions;

import it.polimi.ingsw.Controller.ActionController;
import it.polimi.ingsw.Model.Enums.ResourceType;
import it.polimi.ingsw.Model.GameModel.Player;
import it.polimi.ingsw.Model.GameModel.ResourceManager;
import it.polimi.ingsw.Model.GameModel.WarehouseDepot;
import it.polimi.ingsw.Model.MessagesToClient.*;

/**
 * PayResource Class defines data and methods to complete a Client's request when paying for a
 * production or a Development Card.
 */
public class PayResource extends Action {
    /** a boolean is used to tell if the resource comes from the warehouse or the strongbox */
    private final boolean fromWarehouse;
    /** int indicating the depot where the player took the resource from */
    private final int depot;
    /** ResourceType of the resources taken by the player (if !fromWarehouse) */
    private final ResourceType resourceType;

    /**
     * Constructor for PayResource Class
     */
    public PayResource(boolean fromWarehouse, int depot, ResourceType resourceType) {
        this.fromWarehouse = fromWarehouse;
        this.depot = depot;
        this.resourceType = resourceType;
    }

    /**
     * Getter for "actionType" attribute in PayResource Class.
     */
    public ActionType getActionType() {
        return actionType;
    }

    /**
     * Getter for "fromWarehouse" attribute in PayResource Class.
     */
    public boolean isFromWarehouse() {
        return fromWarehouse;
    }

    /**
     * Getter for "depot" attribute in PayResource Class.
     */
    public int getDepot() {
        return depot;
    }

    /**
     * Getter for "resourceType" attribute in PayResource Class.
     */
    public ResourceType getResourceType() {
        return resourceType;
    }

    /**
     * This method checks if the input sent to the server is correct by assuring that the depot index
     * does not go out of bounds and .
     * @throws IllegalArgumentException if on of the ResourceTypes is NONE.
     */
    @Override
    public boolean isCorrect() throws IllegalArgumentException {
        if(fromWarehouse && (depot < 0 || depot > 4))
            throw new IllegalArgumentException("Depot index out of bounds.");

        else if(fromWarehouse && resourceType != ResourceType.NONE)
            throw new IllegalArgumentException("Specified type when choosing depot.");

        else if (!fromWarehouse && resourceType == ResourceType.NONE)
            throw new IllegalArgumentException("Resource coming from strongbox was of type NONE.");

        return true;
    }

    /**
     * Method used to check if the action is logically applicable by assuring that the depot
     * picked by the client is Active.
     */
    @Override
    public boolean canBeApplied(ActionController actionController) {
        Player player = actionController.getGame().getCurrentPlayer();

        if(!fromWarehouse)
            return true;
        else
        if(!(player.getBoard().getResourceManager().isExtraDepotOneActive()) && depot == 3)
            return false;
        if(!(player.getBoard().getResourceManager().isExtraDepotTwoActive()) && depot == 4)
            return false;
        return true;
    }

    /**
     * Method used to execute the action on the Model.
     * @return "SUCCESS" if the action went right, another String if it went wrong.
     */
    @Override
    public String doAction(ActionController actionController) {
        this.isCorrect();

        if(!this.canBeApplied(actionController)) {
            response = "Can't take resource from a non active depot";
            return "Can't take resource from a non active depot";
        }

        ResourceManager resourceManager = actionController.getGame().getCurrentPlayer().getBoard().getResourceManager();

        if(this.fromWarehouse) {
            WarehouseDepot depot;

            if (this.depot == 3)
                depot = resourceManager.getExtraWarehouseDepotOne();
            else if (this.depot == 4)
                depot = resourceManager.getExtraWarehouseDepotTwo();
            else
                depot = resourceManager.getWarehouseDepots()[this.depot];

            if(depot.isEmpty()) {
                response = "Chosen depot is empty";
                return "Chosen depot is empty";
            }

            else if(!resourceManager.resourceIsNeededToPay(depot.getResourceType())) {
                response = "This type of resource is not needed";
                return "This type of resource is not needed";
            }

            else {
                resourceManager.payOneResourceWarehouse(depot);
                if(resourceManager.hasPayed()) {
                    response = "SUCCESS";
                    return "SUCCESS";
                }
                else {
                    response = "HasToPay";
                    return "HasToPay";
                }
            }
        }
        else {
            if(resourceManager.getStrongbox().getStoredResources().isEmpty()) {
                response = "Can't take resource from empty Strongbox";
                return "Can't take resource from empty Strongbox";
            }

            else if(resourceManager.getStrongbox().countResourcesByType(this.resourceType) == 0) {
                response = "No " + resourceType + " left in Strongbox";
                return "No " + resourceType + " left in Strongbox";
            }

            else if(!resourceManager.resourceIsNeededToPay(this.resourceType)) {
                response = "This type of resource is not needed";
                return "This type of resource is not needed";
            }

            else {
                resourceManager.payOneResourceStrongbox(this.resourceType);
                if(resourceManager.hasPayed()) {
                    response = "SUCCESS";
                    return "SUCCESS";
                }
                else {
                    response = "HasToPay";
                    return "HasToPay";
                }
            }
        }
    }

    /**
     * This method is overridden in Two other subclasses
     * @return null by default as it should be overridden.
     */
    @Override
    public MessageToClient messagePrepare(ActionController actionController) {
        return null;
    }

    public PaymentMessage hasToPay(ActionController actionController, boolean card) {
        PaymentMessage paymentMessage = new PaymentMessage(actionController.getGame().getCurrentPlayerNickname());
        if(this.response.equals("HasToPay")) {
            paymentMessage.setWarehouse(actionController.getGame().getCurrentPlayer().getBoard().getResourceManager().getWarehouse().toView());
            paymentMessage.setStrongbox(actionController.getGame().getCurrentPlayer().getBoard().getResourceManager().getStrongbox().toView());
            paymentMessage.setTemporaryResources(actionController.getGame().getCurrentPlayer().getBoard().getResourceManager().getTemporaryResourcesToPay().toView());
        }
        paymentMessage.setError(this.response);
        if(card) {
            paymentMessage.addPossibleAction(ActionType.PAY_RESOURCE_PRODUCTION);
            paymentMessage.setActionDone(ActionType.PAY_RESOURCE_PRODUCTION);
        }
        else {
            paymentMessage.addPossibleAction(ActionType.PAY_RESOURCE_CARD);
            paymentMessage.setActionDone(ActionType.PAY_RESOURCE_CARD);
        }
        return paymentMessage;
    }
}
