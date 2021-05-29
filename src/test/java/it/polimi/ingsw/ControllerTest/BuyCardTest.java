package it.polimi.ingsw.ControllerTest;

import static it.polimi.ingsw.Controller.Actions.ActionType.*;
import static org.junit.Assert.*;

import it.polimi.ingsw.CommonTestMethods;
import it.polimi.ingsw.Controller.ActionController;
import it.polimi.ingsw.Controller.Actions.*;
import it.polimi.ingsw.Model.Enums.ResourceType;
import it.polimi.ingsw.Model.GameModel.Game;
import it.polimi.ingsw.Model.GameModel.ResourceStack;
import it.polimi.ingsw.Model.MessagesToClient.BuyCardMessage;
import it.polimi.ingsw.Model.MessagesToClient.MessageToClient;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class BuyCardTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    BuyCard card = new BuyCard(2, 0);
    ActionController actionController = new ActionController();
    Game game = actionController.getGame();
    MessageToClient messageToClient;

    /**Test to check if the method getAction works properly*/
    @Test
    public void getActionTest(){
        assertEquals(BUY_CARD, card.getActionType());
    }

    /**Getter Test*/
    @Test
    public void getterTest(){
        assertEquals(0, card.getColumn());
        assertEquals(2, card.getRow());
    }

    /**isCorrect checks if the coordinates of the card in the table are correct*/
    @Test
    public void isCorrectTest(){
        BuyCard cardErr = new BuyCard(12,0);

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Row index out of bounds.");
        cardErr.isCorrect();
    }

    /**
     * Test for "doAction" and "messagePrepare" methods.
     * I created a game, gave to the current player some resources and tried to buy the cards
     * <ul>
     *     <li>The first time the player has no resources so it returns "Not enough resources to buy Card"</li>
     *     <li>The second time the process is successful</li>
     *     <li>The third time I tried to buy a level three card but there are no level two on the board so it returns
     *     "Card does not fit inside Personal Board"</li>
     * </ul>
     */
    @Test
    public void doAction(){
        CommonTestMethods.gameInitOne(game);
        String response;
        CommonTestMethods.givePlayerLeaderCards(game.getCurrentPlayer(), game.getLeaderCards().get(2), game.getLeaderCards().get(1));

        response = card.doAction(actionController);
        assertEquals("Not enough resources to buy Card", response);
        messageToClient = card.messagePrepare(actionController);

        assertTrue(messageToClient instanceof BuyCardMessage);
        assertEquals(ActionType.BUY_CARD, messageToClient.getActionDone());
        assertEquals("Not enough resources to buy Card", messageToClient.getError());
        assertEquals(game.getCurrentPlayerNickname(), messageToClient.getPlayerNickname());
        assertEquals(ActionType.ACTIVATE_PRODUCTION, messageToClient.getPossibleActions().get(0));
        assertEquals(ActionType.BUY_CARD, messageToClient.getPossibleActions().get(1));
        assertEquals(ActionType.MARKET_CHOOSE_ROW, messageToClient.getPossibleActions().get(2));
        assertEquals(ActionType.ACTIVATE_LEADERCARD, messageToClient.getPossibleActions().get(3));


        ResourceStack stack = new ResourceStack(5,3,3,0);
        CommonTestMethods.giveResourcesToPlayer(game.getCurrentPlayer(), 1,12,1, ResourceType.SHIELDS, ResourceType.COINS, ResourceType.STONES, stack);
        BuyCard secondCard = new BuyCard(0,0);
        response = card.doAction(actionController);
        assertEquals("2 0 2 0", game.getCurrentPlayer().getBoard().getResourceManager().getTemporaryResourcesToPay().toString());
        assertEquals("SUCCESS", response);
        messageToClient = card.messagePrepare(actionController);

        assertTrue(messageToClient instanceof BuyCardMessage);
        assertEquals(ActionType.BUY_CARD, messageToClient.getActionDone());
        assertEquals("SUCCESS", messageToClient.getError());
        assertEquals(game.getCurrentPlayerNickname(), messageToClient.getPlayerNickname());
        assertEquals(ActionType.PAY_RESOURCE_CARD, messageToClient.getPossibleActions().get(0));


        assertEquals("Card does not fit inside Personal Board", secondCard.doAction(actionController));
        messageToClient = secondCard.messagePrepare(actionController);

        assertTrue(messageToClient instanceof BuyCardMessage);
        assertEquals(ActionType.BUY_CARD, messageToClient.getActionDone());
        assertEquals("Card does not fit inside Personal Board", messageToClient.getError());
        assertEquals(game.getCurrentPlayerNickname(), messageToClient.getPlayerNickname());
        assertEquals(ActionType.ACTIVATE_PRODUCTION, messageToClient.getPossibleActions().get(0));
        assertEquals(ActionType.BUY_CARD, messageToClient.getPossibleActions().get(1));
        assertEquals(ActionType.MARKET_CHOOSE_ROW, messageToClient.getPossibleActions().get(2));
        assertEquals(ActionType.ACTIVATE_LEADERCARD, messageToClient.getPossibleActions().get(3));
    }
}
