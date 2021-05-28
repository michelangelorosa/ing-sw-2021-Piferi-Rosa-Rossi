package it.polimi.ingsw.Model.MessagesToClient;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.View.ReducedModel.Game;
import it.polimi.ingsw.View.ReducedModel.Player;

public class ActivateProductionMessage extends MessageToClient {

    public ActivateProductionMessage(String playerNickname) {
        super(playerNickname);
        this.actionDone = ActionType.ACTIVATE_PRODUCTION;
    }


    /**
     * Method used to update the client's view.
     * @param game Game being played by the client.
     */

    @Override
    public void updateView(Game game) {
        if(this.error.equals("SUCCESS") || this.error.equals("No Payment"))
            for(Player player : game.getPlayers())
                if(player.getNickname().equals(this.getPlayerNickname()))
                    player.setPossibleActions(this.possibleActions);
                else{
                    //TODO error message
                }
    }

}
