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
 * <p><b>Attributes:</b></p>
 * <ul>
 *     <li>boolean "fromWarehouse": set to true if the payed resource comes from the Warehouse</li>
 *     <li>int "depot": specifies which depot the chosen resource comes from (if it comes from the Warehouse)</li>
 *     <li>ResourceType "resourceType": specifies which type of resource was chosen by the player (if the resource comes
 *     from the Strongbox)</li>
 * </ul>
 * @author redrick99
 */
public class PayResource extends Action {
    private final boolean fromWarehouse;
    private final int depot;
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
     * Getter for "actionType" attribute.
     */
    public ActionType getActionType() {
        return actionType;
    }

    /**
     * Getter for "fromWarehouse" attribute.
     */
    public boolean isFromWarehouse() {
        return fromWarehouse;
    }

    /**
     * Getter for "depot" attribute.
     */
    public int getDepot() {
        return depot;
    }

    /**
     * Getter for "resourceType" attribute.
     */
    public ResourceType getResourceType() {
        return resourceType;
    }

    /**
     * Controls if the input sent to the server is correct by running the following checks:
     * <ul>
     *     <li>if the resource comes from the Warehouse, depot index has to be between 0 and 4</li>
     *     <li>if the resource comes from the Strongbox, the specified resourceType mustn't be of type NONE</li>
     *     <li>if the resource comes from the Warehouse, the specified resourceType MUST be of type NONE</li>
     * </ul>
     * @return true if the action's parameters are correct.
     * @throws IllegalArgumentException if one of the ResourceTypes is equal to NONE.
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
     * Checks if the action is logically applicable by assuring that the depot
     * chosen by the player is Active (if the resource comes from the Warehouse).
     * @param actionController Class used to compute Action messages coming from the Client.
     * @return true if the specified depot can actually be used.
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
     * Controls and executes the action on the Model.
     * @param actionController Class used to compute Action messages coming from the Client.
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
     * This method is overridden in two other subclasses because the Server response to a payment action differs
     * depending on if the player is paying for a Development Card or to activate a Production.
     * @param actionController Class used to compute Action messages coming from the Client.
     * @return null by default as it should be used in this SuperClass.
     */
    @Override
    public MessageToClient messagePrepare(ActionController actionController) {
        return null;
    }


    /**
     * Protected method used by SubClasses to create a new PaymentMessage in case the player has not finished
     * paying for a Development Cart or a Production.
     * @param actionController Class used to compute Action messages coming from the Client.
     * @param card Boolean set to true if the player is buying a Card, false if he is starting a Production.
     * @return A PaymentMessage MessageToClient type object.
     */
    protected PaymentMessage hasToPay(ActionController actionController, boolean card) {
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
