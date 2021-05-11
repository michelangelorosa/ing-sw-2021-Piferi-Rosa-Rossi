package it.polimi.ingsw.Controller.Actions;

import it.polimi.ingsw.Controller.ActionController;
import it.polimi.ingsw.Model.*;
import it.polimi.ingsw.Model.MessagesToClient.*;

/**
 * SwitchDepot Class defines data and methods to complete a Client's request when wanting
 * to switch two depots during an Add interaction.
 */
public class SwitchDepot extends Action {
    private final int firstDepot;
    private final int secondDepot;

    /**
     * Constructor for SwitchDepot Class.
     */
    public SwitchDepot(int firstDepot, int secondDepot) {
        this.actionType = ActionType.SWITCH_DEPOT;
        this.firstDepot = firstDepot;
        this.secondDepot = secondDepot;
    }

    /**
     * Getter for "actionType" attribute in SwitchDepot Class.
     */
    public ActionType getActionType() {
        return actionType;
    }

    /**
     * Getter for "firstDepot" attribute in SwitchDepot Class.
     */
    public int getFirstDepot() {
        return firstDepot;
    }

    /**
     * Getter for "secondDepot" attribute in SwitchDepot Class.
     */
    public int getSecondDepot() {
        return secondDepot;
    }

    /**
     * This method checks if the input sent to the server is correct by assuring that the depot index
     * does not go out of bounds.
     * @throws IllegalArgumentException if the row or column index is out of bounds.
     */
    @Override
    public boolean isCorrect() throws IllegalArgumentException {
        if(firstDepot < 0 || firstDepot > 4)
            throw new IllegalArgumentException("First Depot index out of bounds.");
        else if(secondDepot < 0 || secondDepot > 4)
            throw new IllegalArgumentException("Second Depot index out of bounds.");
        return true;
    }

    /**
     * Method used to check if the action is logically applicable by assuring that the depot
     * picked by the client is Active.
     */
    @Override
    public boolean canBeApplied(ActionController actionController) {
        Player player = actionController.getGame().getCurrentPlayer();
        if(!player.getBoard().getResourceManager().isExtraDepotOneActive() && (firstDepot == 3 || secondDepot == 3))
            return false;
        if(!player.getBoard().getResourceManager().isExtraDepotTwoActive() && (firstDepot == 4 || secondDepot == 4))
            return false;

        return true;
    }

    /**
     * Method used to execute the action on the Model.
     * @return "SUCCESS" if the action went right, another String if it went wrong.
     */
    @Override
    public String doAction(ActionController actionController) {
        this.isCorrect();
        if(!this.canBeApplied(actionController)) {
            this.response = "Can't switch from/to non active depot";
            return "Can't switch from/to non active depot";
        }

        ResourceManager resourceManager = actionController.getGame().getCurrentPlayer().getBoard().getResourceManager();
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

        if(!resourceManager.getWarehouse().canSwitchDepots(firstDepot, secondDepot)) {
            this.response = "Cannot switch depots";
            return "Cannot switch depots";
        }

        else {
            resourceManager.getWarehouse().switchDepots(firstDepot, secondDepot);
            this.response = "SUCCESS";
            return "SUCCESS";
        }
    }

    /**
     * Method used to prepare a messageToClient type object to be sent by the server to the client.
     * @return A message to be sent to the client.
     */
    @Override
    public MessageToClient messagePrepare(ActionController actionController) {
        SwitchDepotMessage message = new SwitchDepotMessage(actionController.getGame().getCurrentPlayerIndex());
        if(this.response.equals("SUCCESS"))
            message.setWarehouse(actionController.getGame().getCurrentPlayer().getBoard().getResourceManager().getWarehouse());
        message.setError(this.response);
        message.addPossibleAction(ActionType.ADD_RESOURCE);
        message.addPossibleAction(ActionType.RESET_WAREHOUSE);
        message.addPossibleAction(ActionType.SWITCH_DEPOT);
        message.addPossibleAction(ActionType.END_MARKET);

        return message;
    }
}
