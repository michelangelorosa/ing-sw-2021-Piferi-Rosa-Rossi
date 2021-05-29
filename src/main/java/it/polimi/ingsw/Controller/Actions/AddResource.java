package it.polimi.ingsw.Controller.Actions;

import it.polimi.ingsw.Controller.ActionController;
import it.polimi.ingsw.Model.Enums.ResourceType;
import it.polimi.ingsw.Model.GameModel.Player;
import it.polimi.ingsw.Model.GameModel.ResourceManager;
import it.polimi.ingsw.Model.GameModel.WarehouseDepot;
import it.polimi.ingsw.Model.MessagesToClient.*;

/**
 * AddResource Class contains data and methods to resolve a Client request when adding
 * a resource from the Market into his Warehouse.
 */
public class AddResource extends Action {
    private final int depot;
    private final ResourceType resourceType;

    /**
     * Constructor for AddResource Class.
     */
    public AddResource(int depot, ResourceType resourceType) {
        this.actionType = ActionType.ADD_RESOURCE;
        this.depot = depot;
        this.resourceType = resourceType;
    }

    /**
     * Getter for actionType attribute in AddResource Class.
     */
    public ActionType getActionType() {
        return actionType;
    }

    /**
     * Getter for depot attribute in AddResource Class.
     */
    public int getDepot() {
        return depot;
    }

    /**
     * Getter for resourceType attribute in AddResource Class.
     */
    public ResourceType getResourceType() {
        return resourceType;
    }

    /**
     * Method used to check if the specified action is formally correct.
     * @throws IllegalArgumentException if the depot index is out of bounds.
     */
    @Override
    public boolean isCorrect() throws IllegalArgumentException {
        if(depot < 0 || depot > 4)
            throw new IllegalArgumentException("Depot index out of bounds.");
        else if(resourceType == ResourceType.NONE)
            throw new IllegalArgumentException("ResourceType can't be NONE.");
        return true;
    }

    /**
     * Method used to check if the specified action is logically correct.
     * @return false if the player is referring to a inactive Leader Depot or if the
     * specified Type of resource cannot be put inside a Leader Depot.
     */
    @Override
    public boolean canBeApplied(ActionController actionController) {
        Player player = actionController.getGame().getCurrentPlayer();
        if((!player.getBoard().getResourceManager().isExtraDepotOneActive() || player.getBoard().getResourceManager().getExtraWarehouseDepotOne().getResourceType() != resourceType) && depot == 3)
            return false;

        else if((!player.getBoard().getResourceManager().isExtraDepotTwoActive() || player.getBoard().getResourceManager().getExtraWarehouseDepotTwo().getResourceType() != resourceType) && depot == 4)
            return false;

        else return true;
    }

    /**
     * Method used to execute the action on the Model.
     * @return "SUCCESS" if the action went right, another String if it went wrong.
     */
    @Override
    public String doAction(ActionController actionController) {
        this.isCorrect();

        if(!this.canBeApplied(actionController)) {
            response = "Extra depot is not active or not of given type";
            return "Extra depot is not active or not of given type";
        }

        WarehouseDepot depot;
        ResourceManager resourceManager = actionController.getGame().getCurrentPlayer().getBoard().getResourceManager();

        if(this.depot == 3)
            depot = resourceManager.getExtraWarehouseDepotOne();
        else if(this.depot == 4)
            depot = resourceManager.getExtraWarehouseDepotTwo();
        else
            depot = resourceManager.getWarehouseDepots()[this.depot];

        if(!resourceManager.canAddToDepot(this.resourceType, depot)) {
            response = "Can't add " + this.resourceType + " to this depot";
            return "Can't add " + this.resourceType + " to this depot";
        }

        else {
            resourceManager.addOneResourceToDepot(this.resourceType, depot);
            response = "SUCCESS";
            return "SUCCESS";
        }
    }

    /**
     * Method used to prepare a messageToClient type object to be sent by the server to the client.
     * @return A message to be sent to the client.
     */
    @Override
    public MessageToClient messagePrepare(ActionController actionController) {
        AddMessage message = new AddMessage(actionController.getGame().getCurrentPlayerNickname());
        message.setError(this.response);
        message.addPossibleAction(ActionType.ADD_RESOURCE);
        message.addPossibleAction(ActionType.SWITCH_DEPOT);
        message.addPossibleAction(ActionType.RESET_WAREHOUSE);
        message.addPossibleAction(ActionType.END_MARKET);

        if(this.response.equals("SUCCESS")) {
            message.setWarehouse(actionController.getGame().getCurrentPlayer().getBoard().getResourceManager().getWarehouse());
            message.setTemporaryResources(actionController.getGame().getCurrentPlayer().getBoard().getResourceManager().getTemporaryResourcesToPay());
        }

        return message;
    }

}
