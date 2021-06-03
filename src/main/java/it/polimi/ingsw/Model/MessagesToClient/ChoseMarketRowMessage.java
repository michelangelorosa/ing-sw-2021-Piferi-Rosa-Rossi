package it.polimi.ingsw.Model.MessagesToClient;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.View.ReducedModel.*;
import it.polimi.ingsw.View.User.UserInteraction;

/**
 * ChoseMarketRowMessage Class defines a response to be sent to the client after
 * a MarketChooseRow request.
 * <p><b>Attributes:</b></p>
 * <ul>
 *     <li>RedMarket "market": new Market to be set to the Client's Reduced Game</li>
 *     <li>RedResourceStack "temporaryResources": contains the number of resources to add to the player's
 *     Warehouse</li>
 * </ul>
 * @author redrick99
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

    /**
     * Getter for "market" attribute.
     */
    public RedMarket getMarket() {
        return market;
    }

    /**
     * Setter for "market" attribute.
     */
    public void setMarket(RedMarket market) {
        this.market = market;
    }

    /**
     * Getter for "temporaryResources" attribute.
     */
    public RedResourceStack getTemporaryResources() {
        return temporaryResources;
    }

    /**
     * Setter for "temporaryResources" attribute.
     */
    public void setTemporaryResources(RedResourceStack temporaryResources) {
        this.temporaryResources = temporaryResources;
    }

    /**
     * Checks if the message contains an error and updates the Client's view.
     * @param userInteraction Class containing the Reduced Game and the User Interface.
     */
    @Override
    public void updateView(UserInteraction userInteraction) {
        super.updateView(userInteraction);

        Player player = this.getPlayer(userInteraction);

        if(this.success()) {
            userInteraction.getGame().setMarket(this.market);
            player.setTemporaryResources(this.temporaryResources);

            this.displayAction(userInteraction);
        }
        else
            this.displayError(userInteraction);
    }
}
