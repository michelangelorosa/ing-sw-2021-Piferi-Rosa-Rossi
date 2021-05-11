package it.polimi.ingsw.Model.MessagesToClient;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.View.ReducedModel.Game;
import it.polimi.ingsw.View.ReducedModel.Player;

public class ActivateLeaderCardMessage extends MessageToClient {
    private int leaderCardPosition;

    public ActivateLeaderCardMessage(int playerId) {
        this.actionDone = ActionType.ACTIVATE_LEADERCARD;
        this.playerId = playerId;
    }

    public void setLeaderCardPosition(int leaderCardPosition) {
        this.leaderCardPosition = leaderCardPosition;
    }

    /*
    /**
     * Method used to update the client's view.
     * @param game Game being played by the client.
     */
    /*
    @Override
    public void updateView(Game game) {
        if(this.error.equals("SUCCESS"))
            for(Player player : game.getPlayers())
                if(player.getTurnPosition() == this.playerId) {
                    player.getLeaderCards()[this.leaderCardPosition].setActive(true);
                    player.setPossibleActions(this.possibleActions);
                }
                else {
                    //TODO error message
                }
    }

     */
}
