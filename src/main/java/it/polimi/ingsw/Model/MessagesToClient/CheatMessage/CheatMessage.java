package it.polimi.ingsw.Model.MessagesToClient.CheatMessage;

import it.polimi.ingsw.Controller.ControllerClasses.ActionController;
import it.polimi.ingsw.Model.MessagesToClient.MessageToClient;
import it.polimi.ingsw.View.ReducedModel.*;
import it.polimi.ingsw.View.User.UserInteraction;

/**
 * CheatMessage Class contains data and methods to use when a Player performs a Cheat Action.
 * <p><b>Attributes:</b></p>
 * <ul>
 *     <li>RedStrongbox "strongbox": strongbox to be set on the player's view</li>
 *     <li>RedWarehouse "warehouse": warehouse to be set on the player's view</li>
 *     <li>RedDevelopmentCardSlots "slots": development card slots to be set on the player's view</li>
 *     <li>RedLeaderCard[] "leaderCards": leader cards to be set on the player's view</li>
 *     <li>int "faithPoints": faithPoints to be set on the player's view</li>
 *     <li>int "victoryPoints": victory points to be set on the player's view</li>
 * </ul>
 * @author redrick99
 */
public class CheatMessage extends MessageToClient {
    private final RedStrongbox strongbox;
    private final RedWarehouse warehouse;
    private final RedDevelopmentCardSlots slots;
    private final RedLeaderCard[] leaderCards;
    private final int faithPoints;
    private final int victoryPoints;

    /**
     * Constructor for CheatMessage Class.
     */
    public CheatMessage(ActionController actionController) {
        this.strongbox = actionController.getGame().getCurrentPlayer().getBoard().getResourceManager().getStrongbox();
        this.warehouse = actionController.getGame().getCurrentPlayer().getBoard().getResourceManager().getWarehouse();
        this.slots = actionController.getGame().getCurrentPlayer().getBoard().getDevelopmentCardSlots();
        this.leaderCards = actionController.getGame().getCurrentPlayer().getBoard().getLeaderCards();
        this.faithPoints = actionController.getGame().getCurrentPlayer().getFaithTrackPosition();
        this.victoryPoints = actionController.getGame().getCurrentPlayer().getVictoryPoints();
        this.possibleActions = actionController.getGame().getCurrentPlayer().getPossibleActions();
        this.playerNickname = actionController.getGame().getCurrentPlayerNickname();
    }

    /**
     * Updates the client's view.
     * @param userInteraction Class containing the instance of the reduced Game seen by the player.
     */
    @Override
    public void updateView(UserInteraction userInteraction) {
        super.updateView(userInteraction);

        if(this.success()) {
            Player player = this.getPlayer(userInteraction);

            player.setWarehouse(warehouse);
            player.setStrongbox(strongbox);
            player.setSlots(slots);
            player.setLeaderCards(leaderCards);
            player.setFaithTrackPosition(faithPoints);
            player.setVictoryPoints(victoryPoints);

            this.displayAction(userInteraction);
        }
        else
            this.displayError(userInteraction);
    }
}
