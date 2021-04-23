package it.polimi.ingsw.Controller.Actions;

import it.polimi.ingsw.Model.*;
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
    public boolean canBeApplied(Game game) {
        return true;
    }

    /**
     * Method used to execute the action on the Model.
     * @return "SUCCESS" if the action went right, another String if it went wrong.
     */
    @Override
    public String doAction(Game game, ChooseProductionOutput chooseProductionOutput, ChooseCardSlot chooseCardSlot, ResetWarehouse resetWarehouse) {
        game.getCurrentPlayer().getBoard().getResourceManager().getWarehouse().revertWarehouse(this.backupWarehouse);
        game.getCurrentPlayer().getBoard().getResourceManager().getTemporaryResourcesToPay().revertBack(this.backupResources);

        this.backupWarehouse = game.getCurrentPlayer().getBoard().getResourceManager().getWarehouse().copyWarehouse();
        this.backupResources = game.getCurrentPlayer().getBoard().getResourceManager().getTemporaryResourcesToPay().copyStack();

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
     * @param game Current instance of the Game being played.
     * @return A message to be sent to the client.
     */
    @Override
    public MessageToClient messagePrepare(Game game) {
        ResetWarehouseMessage message = new ResetWarehouseMessage(game.getCurrentPlayerIndex());
        message.setWarehouse(game.getCurrentPlayer().getBoard().getResourceManager().getWarehouse());
        message.setTemporaryResources(game.getCurrentPlayer().getBoard().getResourceManager().getTemporaryResourcesToPay());
        message.setError(this.response);
        message.addPossibleAction(ActionType.ADD_RESOURCE);
        message.addPossibleAction(ActionType.RESET_WAREHOUSE);
        message.addPossibleAction(ActionType.SWITCH_DEPOT);
        message.addPossibleAction(ActionType.END_MARKET);

        return message;
    }
}
