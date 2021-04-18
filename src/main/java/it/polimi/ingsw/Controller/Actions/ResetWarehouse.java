package it.polimi.ingsw.Controller.Actions;

import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.ResourceStack;
import it.polimi.ingsw.Model.ResourceType;
import it.polimi.ingsw.Model.Warehouse;

public class ResetWarehouse extends Action implements ActionInterface {
    private Warehouse backupWarehouse;
    private ResourceStack backupResources;

    public ResetWarehouse() {
        this.actionType = ActionType.RESET_WAREHOUSE;
    }

    public Warehouse getBackupWarehouse() {
        return backupWarehouse;
    }

    public void setBackupWarehouse(Warehouse backupWarehouse) {
        this.backupWarehouse = backupWarehouse;
    }

    public ResourceStack getBackupResources() {
        return backupResources;
    }

    public void setBackupResources(ResourceStack backupResources) {
        this.backupResources = backupResources;
    }

    @Override
    public boolean isCorrect() {
        return true;
    }

    @Override
    public boolean canBeApplied(Game game) {
        return true;
    }

    @Override
    public String doAction(Game game, ChooseProductionOutput chooseProductionOutput, ChooseCardSlot chooseCardSlot, ResetWarehouse resetWarehouse) {
        game.getCurrentPlayer().getBoard().getResourceManager().getWarehouse().revertWarehouse(this.backupWarehouse);
        game.getCurrentPlayer().getBoard().getResourceManager().getTemporaryResourcesToPay().revertBack(this.backupResources);

        this.backupWarehouse = game.getCurrentPlayer().getBoard().getResourceManager().getWarehouse().copyWarehouse();
        this.backupResources = game.getCurrentPlayer().getBoard().getResourceManager().getTemporaryResourcesToPay().copyStack();

        return "SUCCESS";
    }

    public void emptyBackupResource() {
        this.backupWarehouse.reset();
        ResourceType[] resourceTypes = ResourceType.values();
        for(int i = 1; i <= 4; i++)
            this.getBackupResources().setResource(0, resourceTypes[i]);
    }
}
