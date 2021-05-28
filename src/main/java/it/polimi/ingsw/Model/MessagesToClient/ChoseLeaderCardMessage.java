package it.polimi.ingsw.Model.MessagesToClient;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.View.ReducedModel.Game;
import it.polimi.ingsw.View.ReducedModel.Player;
import it.polimi.ingsw.View.ReducedModel.RedResourceStack;

public class ChoseLeaderCardMessage extends MessageToClient {
    /**ChoseLeaderCardMessage need a temporary resource stack*/
    RedResourceStack temporaryResources;

    /**ChoseLeaderCardMessage's constructor*/
    public ChoseLeaderCardMessage(String playerNickname) {
        super(playerNickname);
        this.actionDone = ActionType.CHOOSE_LEADER_CARD;
    }

    /**Setter for Temporary resource*/
    public void setTemporaryResources(RedResourceStack temporaryResources) {
        this.temporaryResources = temporaryResources;
    }

    /**
     * Method used to update the client's view.
     * @param game Game being played by the client.
     */
    @Override
    public void updateView(Game game) {
        if(this.error.equals("SUCCESS"))
            for(Player player : game.getPlayers())
                if(player.getNickname().equals(this.getPlayerNickname())) {
                    player.setPossibleActions(this.possibleActions);
                    player.setTemporaryResources(this.temporaryResources);
                }

                else {
                    //TODO error message
                }
    }
}
