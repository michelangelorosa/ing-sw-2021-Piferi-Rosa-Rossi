package it.polimi.ingsw.Model.MessagesToClient;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.View.ReducedModel.Game;
import it.polimi.ingsw.View.ReducedModel.Player;
import it.polimi.ingsw.View.ReducedModel.RedWarehouse;

/**
 * SwitchDepotMessage Class contains data for a response message to be sent to the client
 * after a SwitchDepot request.
 */
public class SwitchDepotMessage extends MessageToClient {
    /** A warehouse is needed to update the Client's View */
    private RedWarehouse warehouse;

    /**
     * Constructor for SwitchDepotMessage Class.
     */
    public SwitchDepotMessage(String playerNickname) {
        super(playerNickname);
        this.actionDone = ActionType.SWITCH_DEPOT;
    }

    /**
     * Setter for "warehouse" attribute in SwitchDepotMessage Class.
     */
    public void setWarehouse(RedWarehouse warehouse) {
        this.warehouse = warehouse;
    }

    /**
     * Getter for "warehouse" attribute in SwitchDepotMessage Class.
     */
    public RedWarehouse getWarehouse() {
        return warehouse;
    }

    @Override
    public void updateView(Game game) {
        if(this.error.equals("SUCCESS"))
            for(Player player : game.getPlayers())
                if(player.getNickname().equals(this.getPlayerNickname())) {
                    player.setWarehouse(this.warehouse);
                    player.setPossibleActions(this.possibleActions);
                }
                else {
                    //TODO error message
                }
    }
}
