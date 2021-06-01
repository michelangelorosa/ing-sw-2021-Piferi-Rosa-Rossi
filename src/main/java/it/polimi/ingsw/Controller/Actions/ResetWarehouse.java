package it.polimi.ingsw.Controller.Actions;

import it.polimi.ingsw.Controller.ControllerClasses.ActionController;
import it.polimi.ingsw.Model.GameModel.ResourceStack;
import it.polimi.ingsw.Model.GameModel.Warehouse;
import it.polimi.ingsw.Model.MessagesToClient.*;

/**
 * ResetWarehouse Class contains data and methods to complete a Client's request when wanting
 * to reset his warehouse to initial conditions (when adding resources).
 * <p><b>Attributes:</b></p>
 * <ul>
 *     <li>Warehouse "backupWarehouse": Backed Up Warehouse use to reset the player's Warehouse to initial conditions</li>
 *     <li>ResourceStack "backupResources": </li>
 * </ul>
 * @author redrick99
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
     * Getter for "backupWarehouse" attribute.
     */
    public Warehouse getBackupWarehouse() {
        return backupWarehouse;
    }

    /**
     * Setter for "backupWarehouse" attribute.
     */
    public void setBackupWarehouse(Warehouse backupWarehouse) {
        this.backupWarehouse = backupWarehouse;
    }

    /**
     * Getter for "backupResources" attribute.
     */
    public ResourceStack getBackupResources() {
        return backupResources;
    }

    /**
     * Setter for "backupResources" attribute.
     */
    public void setBackupResources(ResourceStack backupResources) {
        this.backupResources = backupResources;
    }

    /**
     * Executes the action on the Model.
     * @param actionController Class used to compute Action messages coming from the Client.
     * @return "SUCCESS".
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
     * Empties both backup objects when concluding the <i>Add Cycle</i>.
     */
    public void emptyBackupResource() {
        this.backupWarehouse = new Warehouse();
        this.backupResources = new ResourceStack(0,0,0,0);
    }

    /**
     * Prepares a ResetWarehouseMessage MessageToClient type object to be sent to the Client.
     * @param actionController Class used to compute Action messages coming from the Client.
     * @return A message to be sent to the client.
     */
    @Override
    public MessageToClient messagePrepare(ActionController actionController) {
        ResetWarehouseMessage message = new ResetWarehouseMessage(actionController.getGame().getCurrentPlayerNickname());
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
