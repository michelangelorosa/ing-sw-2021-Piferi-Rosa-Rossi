package it.polimi.ingsw.Controller.Actions;

import it.polimi.ingsw.Model.*;

/**
 * AddResource Class contains data and methods to resolve a Client request when adding
 * a resource from the Market into his Warehouse.
 */

public class AddResource extends Action implements ActionInterface {
    private final int depot;
    private final ResourceType resourceType;

    public AddResource(int depot, ResourceType resourceType) {
        this.actionType = ActionType.ADD_RESOURCE;
        this.depot = depot;
        this.resourceType = resourceType;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public int getDepot() {
        return depot;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    @Override
    public boolean isCorrect() throws IllegalArgumentException {
        if(resourceType == ResourceType.NONE || depot < 0 || depot > 4)
            throw new IllegalArgumentException("Depot index out of bounds.");
        return true;
    }

    @Override
    public boolean canBeApplied(Game game) {
        Player player = game.getCurrentPlayer();
        if(!player.getBoard().getResourceManager().isExtraDepotOneActive() && depot == 3)
            return false;

        else if(!player.getBoard().getResourceManager().isExtraDepotTwoActive() && depot == 4)
            return false;

        else return true;
    }

    @Override
    public String doAction(Game game, ChooseProductionOutput chooseProductionOutput, ChooseCardSlot chooseCardSlot, ResetWarehouse resetWarehouse) {
        this.isCorrect();

        if(!this.canBeApplied(game))
            return "Extra depot is not active";

        WarehouseDepot depot;
        ResourceManager resourceManager = game.getCurrentPlayer().getBoard().getResourceManager();

        if(this.depot == 3)
            depot = resourceManager.getExtraWarehouseDepotOne();
        else if(this.depot == 4)
            depot = resourceManager.getExtraWarehouseDepotTwo();
        else
            depot = resourceManager.getWarehouseDepots()[this.depot];

        if(!resourceManager.canAddToDepot(this.resourceType, depot))
            return "Can't add "+this.resourceType+" to a "+depot.getResourceType()+" depot";


        else {
            resourceManager.addOneResourceToDepot(this.resourceType, depot);
            return "SUCCESS";
        }
    }

}
