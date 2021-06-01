package it.polimi.ingsw.Model.MessagesToClient;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.View.ReducedModel.*;
import it.polimi.ingsw.View.User.UserInteraction;

/**
 * ChoseMarketRowMessage Class defines a response to be sent to the client after
 * a MarketChooseRow request.
 */
public class ChoseMarketRowMessage extends MessageToClient {
    private RedMarket market;
    private RedResourceStack temporaryResources;

    /**
     * Constructor for ChoseMarketRowMessage Class.
     */
    public ChoseMarketRowMessage(String playerNickname) {
        super(playerNickname);
        this.actionDone = ActionType.MARKET_CHOOSE_ROW;
    }

    public RedMarket getMarket() {
        return market;
    }

    public void setMarket(RedMarket market) {
        this.market = market;
    }

    public RedResourceStack getTemporaryResources() {
        return temporaryResources;
    }

    public void setTemporaryResources(RedResourceStack temporaryResources) {
        this.temporaryResources = temporaryResources;
    }

    /**
     * Method used to update the client's view.
     * @param userInteraction Class containing the Reduced Game and methods to display Errors.
     */
    @Override
    public void updateView(UserInteraction userInteraction) {
        userInteraction.getGame().setMarket(this.market);
        Game game = userInteraction.getGame();
        for (Player player : game.getPlayers())
            if(player.getNickname().equals(this.getPlayerNickname())) {
                player.setPossibleActions(this.possibleActions);
                if(this.error.equals("SUCCESS"))
                    player.setTemporaryResources(this.temporaryResources);
            }
    }
}
