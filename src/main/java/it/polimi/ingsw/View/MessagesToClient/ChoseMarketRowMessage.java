package it.polimi.ingsw.View.MessagesToClient;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.View.ReducedModel.Game;
import it.polimi.ingsw.View.ReducedModel.Player;
import it.polimi.ingsw.View.ReducedModel.ResourceStack;

public class ChoseMarketRowMessage extends MessageToClient {
    /** A boolean and an int attributes define the row or column chosen by the client */
    private boolean isRow;
    private int rowOrColumn;
    private ResourceStack temporaryResources;

    /**
     * Constructor for ChoseMarketRowMessage Class.
     */
    public ChoseMarketRowMessage(int playerId) {
        this.actionDone = ActionType.MARKET_CHOOSE_ROW;
        this.playerId = playerId;
    }

    /**
     * Method used to update the client's view.
     * @param game Game being played by the client.
     */
    @Override
    public void updateView(Game game) {
        for (Player player : game.getPlayers())
            if(player.getTurnPosition() == this.playerId) {
                player.setPossibleActions(this.possibleActions);
                if(this.error.equals("SUCCESS"))
                    player.setTemporaryResources(this.temporaryResources);
            }

        if(this.isRow)
            game.getMarket().rowChange(this.rowOrColumn);
        else
            game.getMarket().columnChange(this.rowOrColumn);
    }
}
