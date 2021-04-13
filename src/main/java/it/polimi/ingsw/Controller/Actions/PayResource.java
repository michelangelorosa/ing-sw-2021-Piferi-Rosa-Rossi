package it.polimi.ingsw.Controller.Actions;

import it.polimi.ingsw.Model.*;

public class PayResource {
    private final ActionType actionType;
    private final boolean fromWarehouse;
    private final int depot;
    private final ResourceType resourceType;

    public PayResource(boolean fromWarehouse, int depot, ResourceType resourceType) {
        this.actionType = ActionType.PAY_RESOURCE;
        this.fromWarehouse = fromWarehouse;
        this.depot = depot;
        this.resourceType = resourceType;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public boolean isFromWarehouse() {
        return fromWarehouse;
    }

    public int getDepot() {
        return depot;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public boolean isCorrect() throws IllegalArgumentException {
        if(depot < 0 || depot > 4)
            throw new IllegalArgumentException("Depot index out of bounds.");

        else if(fromWarehouse && resourceType != ResourceType.NONE)
            throw new IllegalArgumentException("Specified type when choosing depot.");

        else if (!fromWarehouse && resourceType == ResourceType.NONE)
            throw new IllegalArgumentException("Resource coming from strongbox was of type NONE.");

        return true;
    }

    public boolean canBeApplied(Player player) {
        if(!fromWarehouse)
            return true;
        else
            if(!(player.getBoard().getResourceManager().isExtraDepotOneActive()) && depot == 3)
                return false;
            if(!(player.getBoard().getResourceManager().isExtraDepotTwoActive()) && depot == 4)
                return false;
            return true;
    }

    public String payResource(Game game) {
        this.isCorrect();

        if(!this.canBeApplied(game.getCurrentPlayer()))
            return "Can't take resource from a non active depot";

        ResourceManager resourceManager = game.getCurrentPlayer().getBoard().getResourceManager();

        if(this.fromWarehouse) {
            WarehouseDepot depot;

            if (this.depot == 3)
                depot = resourceManager.getExtraWarehouseDepotOne();
            else if (this.depot == 4)
                depot = resourceManager.getExtraWarehouseDepotTwo();
            else
                depot = resourceManager.getWarehouseDepots()[this.depot];

            if(depot.isEmpty())
                return "Chosen depot is empty";

            else if(!resourceManager.resourceIsNeededToPay(depot.getResourceType()))
                return "This type of resource is not needed";

            else {
                resourceManager.payOneResourceWarehouse(depot);
                if(resourceManager.hasPayed())
                    return "SUCCESS";
                else
                    return "HasToPay";
            }
        }
        else {
            if(resourceManager.getStrongbox().getStoredResources().isEmpty())
                return "Can't take resource from empty Strongbox";

            else if(resourceManager.getStrongbox().countResourcesByType(this.resourceType) == 0)
                return "No "+resourceType+" left in Strongbox";

            else if(!resourceManager.resourceIsNeededToPay(this.resourceType))
                return "This type of resource is not needed";

            else {
                resourceManager.payOneResourceStrongbox(this.resourceType);
                if(resourceManager.hasPayed())
                    return "SUCCESS";
                else
                    return "HasToPay";
            }
        }
    }
}
