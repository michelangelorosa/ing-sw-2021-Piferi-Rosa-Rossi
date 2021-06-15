package it.polimi.ingsw.Controller.Actions;

import it.polimi.ingsw.Controller.ControllerClasses.ActionController;
import it.polimi.ingsw.Model.Enums.GameType;
import it.polimi.ingsw.Model.GameModel.LeaderCard;
import it.polimi.ingsw.Model.MessagesToClient.InitChoseLeaderCardsMessage;
import it.polimi.ingsw.Model.MessagesToClient.MessageToClient;
import it.polimi.ingsw.View.ReducedModel.RedLeaderCard;

/**
 * InitChooseLeaderCards Class defines data and methods used to give to the player the two
 * Leader Cards he chooses at the beginning of the game.
 * <p>
 * <b>Attributes:</b>
 * <ul>
 *     <li>RedLeaderCard "leaderCard1": first Leader Card chose by the player</li>
 *     <li>RedLeaderCard "leaderCard2": second Leader Card chose by the player</li>
 * </ul>
 * @author redrick99
 */
public class InitChooseLeaderCards extends Action{
    private final RedLeaderCard leaderCard1;
    private final RedLeaderCard leaderCard2;
    private String nickname;

    /**
     * Constructor for InitChooseLeaderCards Class.
     */
    public InitChooseLeaderCards(RedLeaderCard leaderCard1, RedLeaderCard leaderCard2) {
        this.leaderCard1 = leaderCard1;
        this.leaderCard2 = leaderCard2;
    }

    /**
     * Checks if the Action parameters are correct by assuring that the two specified Leader Cards
     * are not of null value.
     * @return true if neither of the two cards equal to null.
     * @throws IllegalArgumentException if at least one Leader Card is equal to null.
     */
    @Override
    public boolean isCorrect() throws IllegalArgumentException {
        if(this.leaderCard1 == null)
            throw new IllegalArgumentException("Error: Leader Card 1 is null");

        if(this.leaderCard2 == null)
            throw new IllegalArgumentException("Error: Leader Card 2 is null");

        return true;
    }

    /**
     * Controls and Executes the action on the Model.
     * @param actionController Class used to compute Action messages coming from the Client.
     * @return "SUCCESS" if it doesn't encounter an Exception.
     */
    @Override
    public String doAction(ActionController actionController) {
        if(!this.isCorrect())
            return null;

        actionController.getGame().getCurrentPlayer().getBoard().getLeaderCards()[0] = (LeaderCard)leaderCard1;
        actionController.getGame().getCurrentPlayer().getBoard().getLeaderCards()[1] = (LeaderCard)leaderCard2;
        this.nickname = actionController.getGame().getCurrentPlayerNickname();
        if(actionController.getGame().getGameType() == GameType.MULTIPLAYER)
            actionController.getGame().nextPlayer();

        this.response = "SUCCESS";
        return "SUCCESS";
    }

    @Override
    public MessageToClient messagePrepare(ActionController actionController) {
        InitChoseLeaderCardsMessage message = new InitChoseLeaderCardsMessage(this.nickname);
        message.setError(this.response);
        return message;
    }

    /**
     * Getter for "leaderCard1" attribute.
     */
    public RedLeaderCard getLeaderCard1() {
        return leaderCard1;
    }

    /**
     * Getter for "leaderCard2" attribute.
     */
    public RedLeaderCard getLeaderCard2() {
        return leaderCard2;
    }
}
