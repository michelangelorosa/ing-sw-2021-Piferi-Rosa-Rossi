package it.polimi.ingsw.Controller.Actions;

import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.ResourceStack;
import it.polimi.ingsw.Model.Warehouse;

public class ResetWarehouse {
    private Warehouse backupWarehouse;
    private ResourceStack backupResources;

    public ResetWarehouse(Warehouse backupWarehouse, ResourceStack backupResources) {
        this.backupWarehouse = backupWarehouse;
        this.backupResources = backupResources;
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

    public String doResetWarehouse(Game game) {
        game.getCurrentPlayer().getBoard().getResourceManager().getWarehouse().revertWarehouse(this.backupWarehouse);
        game.getCurrentPlayer().getBoard().getResourceManager().getTemporaryResourcesToPay().revertBack(this.backupResources);

        this.backupWarehouse = game.getCurrentPlayer().getBoard().getResourceManager().getWarehouse().copyWarehouse();
        this.backupResources = game.getCurrentPlayer().getBoard().getResourceManager().getTemporaryResourcesToPay().copyStack();

        return "SUCCESS";
    }
}
