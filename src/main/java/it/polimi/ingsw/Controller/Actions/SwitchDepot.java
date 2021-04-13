package it.polimi.ingsw.Controller.Actions;

import it.polimi.ingsw.Model.*;

public class SwitchDepot {
    private final ActionType actionType;
    private final int firstDepot;
    private final int secondDepot;

    public SwitchDepot(int firstDepot, int secondDepot) {
        this.actionType = ActionType.SWITCH_DEPOT;
        this.firstDepot = firstDepot;
        this.secondDepot = secondDepot;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public int getFirstDepot() {
        return firstDepot;
    }

    public int getSecondDepot() {
        return secondDepot;
    }

    public boolean isCorrect() throws IllegalArgumentException {
        if(firstDepot < 0 || firstDepot > 4)
            throw new IllegalArgumentException("First Depot index out of bounds.");
        else if(secondDepot < 0 || secondDepot > 4)
            throw new IllegalArgumentException("Second Depot index out of bounds.");
        return true;
    }

    public boolean canBeApplied(Player player) {
        if(!player.getBoard().getResourceManager().isExtraDepotOneActive() && (firstDepot == 3 || secondDepot == 3))
            return false;
        if(!player.getBoard().getResourceManager().isExtraDepotTwoActive() && (firstDepot == 4 || secondDepot == 4))
            return false;

        return true;
    }

    public String switchDepot(Game game) {
        this.isCorrect();
        if(!this.canBeApplied(game.getCurrentPlayer()))
            return "Can't switch from/to non active depot";

        ResourceManager resourceManager = game.getCurrentPlayer().getBoard().getResourceManager();
        WarehouseDepot firstDepot, secondDepot;

        if(this.firstDepot == 3)
            firstDepot = resourceManager.getExtraWarehouseDepotOne();
        else if(this.firstDepot == 4)
            firstDepot = resourceManager.getExtraWarehouseDepotTwo();
        else
            firstDepot = resourceManager.getWarehouseDepots()[this.firstDepot];

        if(this.secondDepot == 3)
            secondDepot = resourceManager.getExtraWarehouseDepotOne();
        else if(this.secondDepot == 4)
            secondDepot = resourceManager.getExtraWarehouseDepotTwo();
        else
            secondDepot = resourceManager.getWarehouseDepots()[this.secondDepot];

        if(!resourceManager.getWarehouse().canSwitchDepots(firstDepot, secondDepot))
            return "Cannot switch depots";

        else {
            resourceManager.getWarehouse().switchDepots(firstDepot, secondDepot);
            return "SUCCESS";
        }
    }
}
