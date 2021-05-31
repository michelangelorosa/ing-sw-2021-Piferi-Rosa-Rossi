package it.polimi.ingsw.ControllerTest;

import it.polimi.ingsw.CommonTestMethods;
import it.polimi.ingsw.Controller.ActionController;
import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.Controller.Actions.ChooseLeaderCard;
import it.polimi.ingsw.Model.GameModel.Game;
import it.polimi.ingsw.Model.MessagesToClient.ChoseLeaderCardMessage;
import it.polimi.ingsw.Model.MessagesToClient.MessageToClient;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

/**
 * Unit Test for ChooseLeaderCard Class.
 */
public class ChooseLeaderCardTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    ActionController actionController = new ActionController();
    Game game = actionController.getGame();

    /**
     * Constructor and Getter test for ChooseLeaderCard Class.
     */
    @Test
    public void constructorTest() {
        ChooseLeaderCard chooseLeaderCard = new ChooseLeaderCard(0);
        assertEquals(0, chooseLeaderCard.getLeaderCard());
    }

    /**
     * Test 1 for "isCorrect" method in ChooseLeaderCard Class.
     */
    @Test
    public void isCorrectTest1() {
        ChooseLeaderCard chooseLeaderCard = new ChooseLeaderCard(-1);
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Leader Card index out of bounds.");
        chooseLeaderCard.isCorrect();
    }

    /**
     * Test 2 for "isCorrect" method in ChooseLeaderCard Class.
     */
    @Test
    public void isCorrectTest2() {
        ChooseLeaderCard chooseLeaderCard = new ChooseLeaderCard(2);
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Leader Card index out of bounds.");
        chooseLeaderCard.isCorrect();
    }

    /**
     * Test for "canBeApplied" method in ChooseLeaderCard Class.
     */
    @Test
    public void canBeApplied() {
        CommonTestMethods.gameInitOne(game);
        CommonTestMethods.givePlayerLeaderCards(game.getCurrentPlayer(), game.getLeaderCards().get(2), game.getLeaderCards().get(6));

        ChooseLeaderCard chooseLeaderCard = new ChooseLeaderCard(0);
        assertFalse(chooseLeaderCard.canBeApplied(actionController));
        game.getCurrentPlayer().getBoard().activateLeaderCard(game.getCurrentPlayer().getBoard().getLeaderCards()[0]);
        assertTrue(chooseLeaderCard.canBeApplied(actionController));

        chooseLeaderCard = new ChooseLeaderCard(1);
        assertFalse(chooseLeaderCard.canBeApplied(actionController));
        game.getCurrentPlayer().getBoard().activateLeaderCard(game.getCurrentPlayer().getBoard().getLeaderCards()[1]);
        assertFalse(chooseLeaderCard.canBeApplied(actionController));
    }

    /**
     * Test for "doAction" method in ChooseLeaderCard Class.
     */
    @Test
    public void doAction() {
        CommonTestMethods.gameInitOne(game);
        CommonTestMethods.givePlayerLeaderCards(game.getCurrentPlayer(), game.getLeaderCards().get(2), game.getLeaderCards().get(6));
        game.getCurrentPlayer().getBoard().getResourceManager().setTemporaryWhiteMarbles(2);

        ChooseLeaderCard chooseLeaderCard = new ChooseLeaderCard(0);
        MessageToClient message;

        String response;

        chooseLeaderCard.doAction(actionController);
        message = chooseLeaderCard.messagePrepare(actionController);
        assertTrue(message instanceof ChoseLeaderCardMessage);
        assertEquals("You cannot perform this action at this moment", message.getError());

        game.getCurrentPlayer().addPossibleAction(ActionType.CHOOSE_LEADER_CARD);

        response = chooseLeaderCard.doAction(actionController);
        assertEquals("Leader Card not active or not of type WHITE MARBLE", response);
        message = chooseLeaderCard.messagePrepare(actionController);
        assertTrue(message instanceof ChoseLeaderCardMessage);
        assertEquals("Leader Card not active or not of type WHITE MARBLE", message.getError());
        assertEquals(1, message.getPossibleActions().size());
        assertEquals(ActionType.CHOOSE_LEADER_CARD, message.getPossibleActions().get(0));

        game.getCurrentPlayer().getBoard().activateLeaderCard(game.getCurrentPlayer().getBoard().getLeaderCards()[0]);
        response = chooseLeaderCard.doAction(actionController);
        assertEquals("Another White Marble", response);
        response = chooseLeaderCard.doAction(actionController);
        assertEquals("SUCCESS", response);
        message = chooseLeaderCard.messagePrepare(actionController);
        assertTrue(message instanceof ChoseLeaderCardMessage);
        assertEquals("SUCCESS", message.getError());
        assertEquals(4, message.getPossibleActions().size());
        assertEquals(ActionType.ADD_RESOURCE, message.getPossibleActions().get(0));
        assertEquals(ActionType.SWITCH_DEPOT, message.getPossibleActions().get(1));
        assertEquals(ActionType.RESET_WAREHOUSE, message.getPossibleActions().get(2));
        assertEquals(ActionType.END_MARKET, message.getPossibleActions().get(3));
    }
}
