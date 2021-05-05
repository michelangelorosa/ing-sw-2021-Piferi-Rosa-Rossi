package it.polimi.ingsw.View.MessagesToClient;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.View.ReducedModel.*;


public class AddMessage extends MessageToClient {
    /** warehouse and temporaryResources attributes are needed to update the client's View */
    private Warehouse warehouse;
    private ResourceStack temporaryResources;

    /**
     * Constructor for AddMessage Class.
     * @param playerId
     */
    public AddMessage(int playerId) {
        this.actionDone = ActionType.ADD_RESOURCE;
        this.playerId = playerId;
    }

    /**
     * Method used to update the client's view.
     * @param game Game being played by the client.
     */
    @Override
    public void updateView(Game game) {
        if(this.error.equals("SUCCESS"))
            for(Player player : game.getPlayers())
                if(player.getTurnPosition() == this.playerId) {
                    player.setWarehouse(this.warehouse);
                    player.setTemporaryResources(this.temporaryResources);
                    player.setPossibleActions(this.possibleActions);
                }
        else {
            //TODO error message
        }
    }
}
