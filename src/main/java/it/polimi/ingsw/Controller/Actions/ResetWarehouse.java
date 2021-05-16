package it.polimi.ingsw.Controller.Actions;

import it.polimi.ingsw.Controller.ActionController;
import it.polimi.ingsw.Model.*;
import it.polimi.ingsw.Model.Enums.ResourceType;
import it.polimi.ingsw.Model.MessagesToClient.*;

/**
 * ResetWarehouse Class contains data and methods to complete a Client's request when wanting
 * to reset his warehouse to initial conditions (when adding resources).
 */
public class ResetWarehouse extends Action {
    private Warehouse backupWarehouse;
    private ResourceStack backupResources;

    /**
     * Constructor for ResetWarehouse Class.
     */
    public ResetWarehouse() {
        this.actionType = ActionType.RESET_WAREHOUSE;
    }

    /**
     * Getter for "backupWarehouse" attribute in ResetWarehouse Class.
     */
    public Warehouse getBackupWarehouse() {
        return backupWarehouse;
    }

    /**
     * Setter for "backupWarehouse" attribute in ResetWarehouse Class.
     */
    public void setBackupWarehouse(Warehouse backupWarehouse) {
        this.backupWarehouse = backupWarehouse;
    }

    /**
     * Getter for "backupResources" attribute in ResetWarehouse Class.
     */
    public ResourceStack getBackupResources() {
        return backupResources;
    }

    /**
     * Setter for "backupResources" attribute in ResetWarehouse Class.
     */
    public void setBackupResources(ResourceStack backupResources) {
        this.backupResources = backupResources;
    }

    /**
     * This method checks if the input sent to the server is correct.
     */
    @Override
    public boolean isCorrect() {
        return true;
    }

    /**
     * Method used to check if the action is logically applicable.
     */
    @Override
    public boolean canBeApplied(ActionController actionController) {
        return true;
    }

    /**
     * Method used to execute the action on the Model.
     * @return "SUCCESS" if the action went right, another String if it went wrong.
     */
    @Override
    public String doAction(ActionController actionController) {
        actionController.getGame().getCurrentPlayer().getBoard().getResourceManager().getWarehouse().revertWarehouse(this.backupWarehouse);
        actionController.getGame().getCurrentPlayer().getBoard().getResourceManager().getTemporaryResourcesToPay().revertBack(this.backupResources);

        this.backupWarehouse = actionController.getGame().getCurrentPlayer().getBoard().getResourceManager().getWarehouse().copyWarehouse();
        this.backupResources = actionController.getGame().getCurrentPlayer().getBoard().getResourceManager().getTemporaryResourcesToPay().copyStack();

        this.response = "SUCCESS";
        return "SUCCESS";
    }

    /**
     * Method used to empty both backup objects when concluding the add phase.
     */
    public void emptyBackupResource() {
        this.backupWarehouse.reset();
        ResourceType[] resourceTypes = ResourceType.values();
        for(int i = 1; i <= 4; i++)
            this.getBackupResources().setResource(0, resourceTypes[i]);
    }

    /**
     * Method used to prepare a messageToClient type object to be sent by the server to the client.
     * @return A message to be sent to the client.
     */
    @Override
    public MessageToClient messagePrepare(ActionController actionController) {
        ResetWarehouseMessage message = new ResetWarehouseMessage(actionController.getGame().getCurrentPlayerIndex());
        message.setWarehouse(actionController.getGame().getCurrentPlayer().getBoard().getResourceManager().getWarehouse().toView());
        message.setTemporaryResources(actionController.getGame().getCurrentPlayer().getBoard().getResourceManager().getTemporaryResourcesToPay().toView());
        message.setError(this.response);
        message.addPossibleAction(ActionType.ADD_RESOURCE);
        message.addPossibleAction(ActionType.RESET_WAREHOUSE);
        message.addPossibleAction(ActionType.SWITCH_DEPOT);
        message.addPossibleAction(ActionType.END_MARKET);

        return message;
    }
}
