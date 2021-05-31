package it.polimi.ingsw.ControllerTest;

import static org.junit.Assert.*;

import it.polimi.ingsw.Controller.ActionController;
import it.polimi.ingsw.Model.Enums.ResourceType;
import it.polimi.ingsw.Model.GameModel.Game;
import it.polimi.ingsw.Model.GameModel.ResourceStack;
import it.polimi.ingsw.Model.MessagesToClient.*;
import it.polimi.ingsw.CommonTestMethods;
import it.polimi.ingsw.Controller.Actions.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;

/**
 * Unit Test for ActivateProduction Class.
 */
public class ActivateProductionTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    MessageToClient messageToClient;
    ActionController actionController = new ActionController();
    Game game = actionController.getGame();

    /**
     * Constructor and Getter Test for ActivateProduction Class.
     */
    @Test
    public void constructorTest() {
        ArrayList<ResourceType> inputs = new ArrayList<>();
        inputs.add(ResourceType.SHIELDS);
        inputs.add(ResourceType.STONES);

        ActivateProduction activateProduction = new ActivateProduction(true, false, true, false, false, true, inputs);

        assertEquals(ActionType.ACTIVATE_PRODUCTION, activateProduction.getActionType());
        assertTrue(activateProduction.isFirstSlot());
        assertFalse(activateProduction.isSecondSlot());
        assertTrue(activateProduction.isThirdSlot());
        assertFalse(activateProduction.isFirstLeaderCard());
        assertFalse(activateProduction.isSecondLeaderCard());
        assertTrue(activateProduction.isBasicProduction());
        assertEquals(inputs, activateProduction.getBasicProductionInputs());
    }

    /**
     * Test 1 for "isCorrect" method in ActivateProduction Class.
     */
    @Test
    public void isCorrectTest() {
        ArrayList<ResourceType> inputs = new ArrayList<>();
        inputs.add(ResourceType.NONE);
        inputs.add(ResourceType.SHIELDS);
        ActivateProduction activateProduction = new ActivateProduction(true, true, true, true, true, true, inputs);

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Illegal Type NONE in active basic production.");
        activateProduction.isCorrect();
    }

    /**
     * Test 2 for "isCorrect" method in ActivateProduction Class.
     */
    @Test
    public void isCorrect2Test() {
        ArrayList<ResourceType> inputs = new ArrayList<>();
        inputs.add(ResourceType.COINS);
        inputs.add(ResourceType.SHIELDS);
        inputs.add(ResourceType.SHIELDS);
        inputs.add(ResourceType.SHIELDS);
        inputs.add(ResourceType.NONE);
        inputs.add(ResourceType.COINS);
        ActivateProduction activateProduction = new ActivateProduction(true, true, true, true, true, true, inputs);

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Illegal Type NONE in active basic production.");
        activateProduction.isCorrect();
    }

    /**
     * Test for "canBeApplied", "doAction" and "messagePrepare" methods in ActivateProduction Class.
     */
    @Test
    public void canBeAppliedAndDoActionTest() {
        CommonTestMethods.gameInitOne(game);
        game.getCurrentPlayer().getBoard().getLeaderCards()[0] = game.getLeaderCards().get(4);
        game.getCurrentPlayer().getBoard().getLeaderCards()[1] = game.getLeaderCards().get(6);
        game.getCurrentPlayer().addPossibleAction(ActionType.ACTIVATE_PRODUCTION);

        ArrayList<ResourceType> inputs = new ArrayList<>();
        inputs.add(ResourceType.COINS);
        inputs.add(ResourceType.COINS);

        ActivateProduction activateProduction = new ActivateProduction(true, true, true, true, true, true, inputs);
        assertEquals("Leader Card not active or not required Type/Error in reading basic production inputs.", activateProduction.doAction(actionController));
        messageToClient = activateProduction.messagePrepare(actionController);

        assertTrue(messageToClient instanceof ActivateProductionMessage);
        assertEquals(ActionType.ACTIVATE_PRODUCTION, messageToClient.getActionDone());
        assertEquals(game.getCurrentPlayerNickname(), messageToClient.getPlayerNickname());
        assertEquals("Leader Card not active or not required Type/Error in reading basic production inputs.", messageToClient.getError());
        assertEquals(ActionType.ACTIVATE_PRODUCTION, messageToClient.getPossibleActions().get(0));
        assertEquals(ActionType.BUY_CARD, messageToClient.getPossibleActions().get(1));
        assertEquals(ActionType.MARKET_CHOOSE_ROW, messageToClient.getPossibleActions().get(2));
        assertEquals(ActionType.ACTIVATE_LEADERCARD, messageToClient.getPossibleActions().get(3));


        activateProduction = new ActivateProduction(true, true, true, true, false, true, inputs);
        assertEquals("Leader Card not active or not required Type/Error in reading basic production inputs.", activateProduction.doAction(actionController));
        messageToClient = activateProduction.messagePrepare(actionController);

        assertTrue(messageToClient instanceof ActivateProductionMessage);
        assertEquals(ActionType.ACTIVATE_PRODUCTION, messageToClient.getActionDone());
        assertEquals(game.getCurrentPlayerNickname(), messageToClient.getPlayerNickname());
        assertEquals("Leader Card not active or not required Type/Error in reading basic production inputs.", messageToClient.getError());
        assertEquals(ActionType.ACTIVATE_PRODUCTION, messageToClient.getPossibleActions().get(0));
        assertEquals(ActionType.BUY_CARD, messageToClient.getPossibleActions().get(1));
        assertEquals(ActionType.MARKET_CHOOSE_ROW, messageToClient.getPossibleActions().get(2));
        assertEquals(ActionType.ACTIVATE_LEADERCARD, messageToClient.getPossibleActions().get(3));


        game.getCurrentPlayer().getBoard().activateLeaderCard(game.getCurrentPlayer().getBoard().getLeaderCards()[0]);

        activateProduction = new ActivateProduction(true, true, true, true, false, true, inputs);
        assertEquals("Can't pick empty slot (1st)", activateProduction.doAction(actionController));
        messageToClient = activateProduction.messagePrepare(actionController);

        assertTrue(messageToClient instanceof ActivateProductionMessage);
        assertEquals(ActionType.ACTIVATE_PRODUCTION, messageToClient.getActionDone());
        assertEquals(game.getCurrentPlayerNickname(), messageToClient.getPlayerNickname());
        assertEquals("Can't pick empty slot (1st)", messageToClient.getError());
        assertEquals(ActionType.ACTIVATE_PRODUCTION, messageToClient.getPossibleActions().get(0));
        assertEquals(ActionType.BUY_CARD, messageToClient.getPossibleActions().get(1));
        assertEquals(ActionType.MARKET_CHOOSE_ROW, messageToClient.getPossibleActions().get(2));
        assertEquals(ActionType.ACTIVATE_LEADERCARD, messageToClient.getPossibleActions().get(3));

        CommonTestMethods.addCardsToPlayersBoard(game.getCurrentPlayer());

        ResourceStack stack = new ResourceStack(2, 2, 2, 2);

        CommonTestMethods.giveResourcesToPlayer(game.getCurrentPlayer(), 2, 2, 1, ResourceType.SHIELDS, ResourceType.COINS, ResourceType.SERVANTS, stack);

        activateProduction = new ActivateProduction(true, false, false, false, false, true, inputs);
        assertEquals("SUCCESS", activateProduction.doAction(actionController));
        assertEquals("0 0 3 1", game.getCurrentPlayer().getBoard().getResourceManager().getTemporaryResourcesToPay().toString());
        messageToClient = activateProduction.messagePrepare(actionController);

        assertTrue(messageToClient instanceof ActivateProductionMessage);
        assertEquals(ActionType.ACTIVATE_PRODUCTION, messageToClient.getActionDone());
        assertEquals(game.getCurrentPlayerNickname(), messageToClient.getPlayerNickname());
        assertEquals("SUCCESS", messageToClient.getError());
        assertEquals(ActionType.PAY_RESOURCE_PRODUCTION, messageToClient.getPossibleActions().get(0));

        game.getCurrentPlayer().addPossibleAction(ActionType.ACTIVATE_PRODUCTION);

        activateProduction = new ActivateProduction(true, false, false, true, false, true, inputs);
        assertEquals("SUCCESS", activateProduction.doAction(actionController));
        assertEquals("1 1 3 1", game.getCurrentPlayer().getBoard().getResourceManager().getTemporaryResourcesToPay().toString());
        messageToClient = activateProduction.messagePrepare(actionController);

        assertTrue(messageToClient instanceof ActivateProductionMessage);
        assertEquals(ActionType.ACTIVATE_PRODUCTION, messageToClient.getActionDone());
        assertEquals(game.getCurrentPlayerNickname(), messageToClient.getPlayerNickname());
        assertEquals("SUCCESS", messageToClient.getError());
        assertEquals(ActionType.PAY_RESOURCE_PRODUCTION, messageToClient.getPossibleActions().get(0));

        game.getCurrentPlayer().addPossibleAction(ActionType.ACTIVATE_PRODUCTION);

        activateProduction = new ActivateProduction(true, true, false, true, false, true, inputs);
        assertEquals("Not enough Resources to start Production", activateProduction.doAction(actionController));
        assertEquals("1 1 3 1", game.getCurrentPlayer().getBoard().getResourceManager().getTemporaryResourcesToPay().toString());
        messageToClient = activateProduction.messagePrepare(actionController);

        assertTrue(messageToClient instanceof ActivateProductionMessage);
        assertEquals(ActionType.ACTIVATE_PRODUCTION, messageToClient.getActionDone());
        assertEquals(game.getCurrentPlayerNickname(), messageToClient.getPlayerNickname());
        assertEquals("Not enough Resources to start Production", messageToClient.getError());
        assertEquals(ActionType.ACTIVATE_PRODUCTION, messageToClient.getPossibleActions().get(0));
        assertEquals(ActionType.BUY_CARD, messageToClient.getPossibleActions().get(1));
        assertEquals(ActionType.MARKET_CHOOSE_ROW, messageToClient.getPossibleActions().get(2));
        assertEquals(ActionType.ACTIVATE_LEADERCARD, messageToClient.getPossibleActions().get(3));


        stack = new ResourceStack(2, 2, 2, 8);
        CommonTestMethods.giveResourcesToPlayer(game.getCurrentPlayer(), 2, 2, 1, ResourceType.SHIELDS, ResourceType.COINS, ResourceType.SERVANTS, stack);

        activateProduction = new ActivateProduction(true, true, false, true, false, true, inputs);
        assertEquals("SUCCESS", activateProduction.doAction(actionController));
        assertEquals("1 1 3 6", game.getCurrentPlayer().getBoard().getResourceManager().getTemporaryResourcesToPay().toString());
        messageToClient = activateProduction.messagePrepare(actionController);

        assertTrue(messageToClient instanceof ActivateProductionMessage);
        assertEquals(ActionType.ACTIVATE_PRODUCTION, messageToClient.getActionDone());
        assertEquals(game.getCurrentPlayerNickname(), messageToClient.getPlayerNickname());
        assertEquals("SUCCESS", messageToClient.getError());
        assertEquals(ActionType.PAY_RESOURCE_PRODUCTION, messageToClient.getPossibleActions().get(0));

        game.getCurrentPlayer().addPossibleAction(ActionType.ACTIVATE_PRODUCTION);

        activateProduction = new ActivateProduction(true, true, true, true, false, true, inputs);
        assertEquals("SUCCESS", activateProduction.doAction(actionController));
        assertEquals("3 2 4 6", game.getCurrentPlayer().getBoard().getResourceManager().getTemporaryResourcesToPay().toString());
        messageToClient = activateProduction.messagePrepare(actionController);

        assertTrue(messageToClient instanceof ActivateProductionMessage);
        assertEquals(ActionType.ACTIVATE_PRODUCTION, messageToClient.getActionDone());
        assertEquals(game.getCurrentPlayerNickname(), messageToClient.getPlayerNickname());
        assertEquals("SUCCESS", messageToClient.getError());
        assertEquals(ActionType.PAY_RESOURCE_PRODUCTION, messageToClient.getPossibleActions().get(0));

        game.getCurrentPlayer().addPossibleAction(ActionType.ACTIVATE_PRODUCTION);

        activateProduction = new ActivateProduction(true, true, true, true, true, true, inputs);
        assertEquals("Leader Card not active or not required Type/Error in reading basic production inputs.", activateProduction.doAction(actionController));
        messageToClient = activateProduction.messagePrepare(actionController);

        assertTrue(messageToClient instanceof ActivateProductionMessage);
        assertEquals(ActionType.ACTIVATE_PRODUCTION, messageToClient.getActionDone());
        assertEquals(game.getCurrentPlayerNickname(), messageToClient.getPlayerNickname());
        assertEquals("Leader Card not active or not required Type/Error in reading basic production inputs.", messageToClient.getError());
        assertEquals(ActionType.ACTIVATE_PRODUCTION, messageToClient.getPossibleActions().get(0));
        assertEquals(ActionType.BUY_CARD, messageToClient.getPossibleActions().get(1));
        assertEquals(ActionType.MARKET_CHOOSE_ROW, messageToClient.getPossibleActions().get(2));
        assertEquals(ActionType.ACTIVATE_LEADERCARD, messageToClient.getPossibleActions().get(3));


        activateProduction = new ActivateProduction(false, false, false, false, false, false, inputs);
        assertEquals("SUCCESS", activateProduction.doAction(actionController));
        messageToClient = activateProduction.messagePrepare(actionController);

        assertTrue(messageToClient instanceof ActivateProductionMessage);
        assertEquals(ActionType.ACTIVATE_PRODUCTION, messageToClient.getActionDone());
        assertEquals(game.getCurrentPlayerNickname(), messageToClient.getPlayerNickname());
        assertEquals("No Payment", messageToClient.getError());
        assertEquals(ActionType.CHOOSE_PRODUCTION_OUTPUT, messageToClient.getPossibleActions().get(0));
    }
}
