package it.polimi.ingsw.ControllerTest;

import static org.junit.Assert.*;

import it.polimi.ingsw.Controller.ActionController;
import it.polimi.ingsw.Model.Enums.ResourceType;
import it.polimi.ingsw.Model.GameModel.Game;
import it.polimi.ingsw.Model.MessagesToClient.*;
import it.polimi.ingsw.CommonTestMethods;
import it.polimi.ingsw.Controller.Actions.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;

/**
 * Unit Test for ChooseProductionOutput Class.
 */
public class ChooseProductionOutputTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    ActionController actionController = new ActionController();
    Game game = actionController.getGame();
    MessageToClient messageToClient;

    /**
     * Constructor, Getter and Setter test for ChooseProductionOutput Class.
     */
    @Test
    public void constructorTest() {
        ChooseProductionOutput chooseProductionOutput = constructor();
        assertTrue(chooseProductionOutput.isFirstLeaderCard());
        assertTrue(chooseProductionOutput.isSecondLeaderCard());
        assertTrue(chooseProductionOutput.isBasicProduction());
        for(int i = 0; i < 3; i ++) {
            assertEquals(ResourceType.SHIELDS, chooseProductionOutput.getFirstLeaderCardOutput().get(i));
            assertEquals(ResourceType.SERVANTS, chooseProductionOutput.getSecondLeaderCardOutput().get(i));
            assertEquals(ResourceType.COINS, chooseProductionOutput.getBasicProductionOutput().get(i));
        }
    }

    /**
     * Test 1 for "isCorrect" method in ChooseProductionOutput Class.
     */
    @Test
    public void isCorrectTest1() {
        ChooseProductionOutput chooseProductionOutput = constructor();
        chooseProductionOutput.getFirstLeaderCardOutput().add(ResourceType.NONE);
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("1- One resource was of type NONE.");
        chooseProductionOutput.isCorrect();
    }
    /**
     * Test 2 for "isCorrect" method in ChooseProductionOutput Class.
     */
    @Test
    public void isCorrectTest2() {
        ChooseProductionOutput chooseProductionOutput = constructor();
        chooseProductionOutput.getSecondLeaderCardOutput().add(ResourceType.NONE);
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("2- One resource was of type NONE.");
        chooseProductionOutput.isCorrect();
    }
    /**
     * Test 3 for "isCorrect" method in ChooseProductionOutput Class.
     */
    @Test
    public void isCorrectTest3() {
        ChooseProductionOutput chooseProductionOutput = constructor();
        chooseProductionOutput.getBasicProductionOutput().add(ResourceType.NONE);
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("3- One resource was of type NONE.");
        chooseProductionOutput.isCorrect();
    }

    /**
     * Test for "canBeApplied" method in ChooseProductionOutput Class.
     */
    @Test
    public void canBeAppliedTest() {
        ChooseProductionOutput chooseProductionOutput = constructor();
        CommonTestMethods.gameInitOne(game);

        CommonTestMethods.givePlayerLeaderCards(game.getCurrentPlayer(), game.getLeaderCards().get(0), game.getLeaderCards().get(1));
        game.getCurrentPlayer().getBoard().activateLeaderCard(game.getCurrentPlayer().getBoard().getLeaderCards()[0]);
        game.getCurrentPlayer().getBoard().activateLeaderCard(game.getCurrentPlayer().getBoard().getLeaderCards()[1]);
        assertFalse(chooseProductionOutput.canBeApplied(actionController));

        CommonTestMethods.givePlayerLeaderCards(game.getCurrentPlayer(), game.getLeaderCards().get(4), game.getLeaderCards().get(1));
        game.getCurrentPlayer().getBoard().activateLeaderCard(game.getCurrentPlayer().getBoard().getLeaderCards()[0]);
        game.getCurrentPlayer().getBoard().activateLeaderCard(game.getCurrentPlayer().getBoard().getLeaderCards()[1]);
        assertFalse(chooseProductionOutput.canBeApplied(actionController));

        CommonTestMethods.givePlayerLeaderCards(game.getCurrentPlayer(), game.getLeaderCards().get(4), game.getLeaderCards().get(5));
        game.getCurrentPlayer().getBoard().activateLeaderCard(game.getCurrentPlayer().getBoard().getLeaderCards()[0]);
        game.getCurrentPlayer().getBoard().activateLeaderCard(game.getCurrentPlayer().getBoard().getLeaderCards()[1]);
        assertFalse(chooseProductionOutput.canBeApplied(actionController));

        ArrayList<ResourceType> resourceTypes = new ArrayList<>();
        resourceTypes.add(ResourceType.SHIELDS);
        chooseProductionOutput.setBasicProductionOutput(resourceTypes);
        assertTrue(chooseProductionOutput.canBeApplied(actionController));
    }

    /**
     * Test for "doAction" and "messagePrepare" methods in ChooseProductionOutput Class.
     */
    @Test
    public void doActionTest() {
        CommonTestMethods.gameInitOne(game);
        CommonTestMethods.givePlayerLeaderCards(game.getCurrentPlayer(), game.getLeaderCards().get(4), game.getLeaderCards().get(5));
        game.getCurrentPlayer().getBoard().activateLeaderCard(game.getCurrentPlayer().getBoard().getLeaderCards()[0]);
        game.getCurrentPlayer().getBoard().activateLeaderCard(game.getCurrentPlayer().getBoard().getLeaderCards()[1]);
        ChooseProductionOutput chooseProductionOutput = constructor();

        String response;

        game.getCurrentPlayer().addPossibleAction(ActionType.CHOOSE_PRODUCTION_OUTPUT);
        game.getCurrentPlayer().addPossibleAction(ActionType.ACTIVATE_LEADERCARD);

        response = chooseProductionOutput.doAction(actionController);
        assertEquals("Tried to use not valid Leader Cards or tried to get more resources than possible.", response);
        assertEquals("0 0 0 0", game.getCurrentPlayer().getBoard().getResourceManager().getStrongbox().getStoredResources().toString());
        messageToClient = chooseProductionOutput.messagePrepare(actionController);

        assertTrue(messageToClient instanceof ChoseProductionOutputMessage);
        assertEquals(game.getCurrentPlayerNickname(), messageToClient.getPlayerNickname());
        assertEquals("Tried to use not valid Leader Cards or tried to get more resources than possible.", messageToClient.getError());
        assertEquals(ActionType.CHOOSE_PRODUCTION_OUTPUT, messageToClient.getActionDone());
        assertEquals(ActionType.CHOOSE_PRODUCTION_OUTPUT, messageToClient.getPossibleActions().get(0));
        assertNull(((ChoseProductionOutputMessage) messageToClient).getStrongbox());


        ArrayList<ResourceType> resourceTypes = new ArrayList<>();
        resourceTypes.add(ResourceType.SHIELDS);
        chooseProductionOutput.setBasicProductionOutput(resourceTypes);

        response = chooseProductionOutput.doAction(actionController);
        assertEquals("SUCCESS", response);
        assertEquals("4 3 0 0", game.getCurrentPlayer().getBoard().getResourceManager().getStrongbox().getStoredResources().toString());
        messageToClient = chooseProductionOutput.messagePrepare(actionController);

        assertTrue(messageToClient instanceof ChoseProductionOutputMessage);
        assertEquals(game.getCurrentPlayerNickname(), messageToClient.getPlayerNickname());
        assertEquals("SUCCESS", messageToClient.getError());
        assertEquals(ActionType.CHOOSE_PRODUCTION_OUTPUT, messageToClient.getActionDone());
        assertEquals(ActionType.END_TURN, messageToClient.getPossibleActions().get(0));
        assertEquals(ActionType.ACTIVATE_LEADERCARD, messageToClient.getPossibleActions().get(1));
        assertEquals(game.getCurrentPlayer().getBoard().getResourceManager().getStrongbox().toView().toString(), ((ChoseProductionOutputMessage) messageToClient).getStrongbox().toString());

        game.getCurrentPlayer().addPossibleAction(ActionType.CHOOSE_PRODUCTION_OUTPUT);

        chooseProductionOutput.setBasicProduction(false);
        response = chooseProductionOutput.doAction(actionController);
        assertEquals("SUCCESS", response);
        assertEquals("7 6 0 0", game.getCurrentPlayer().getBoard().getResourceManager().getStrongbox().getStoredResources().toString());
        messageToClient = chooseProductionOutput.messagePrepare(actionController);

        assertTrue(messageToClient instanceof ChoseProductionOutputMessage);
        assertEquals(game.getCurrentPlayerNickname(), messageToClient.getPlayerNickname());
        assertEquals("SUCCESS", messageToClient.getError());
        assertEquals(ActionType.CHOOSE_PRODUCTION_OUTPUT, messageToClient.getActionDone());
        assertEquals(ActionType.END_TURN, messageToClient.getPossibleActions().get(0));
        assertEquals(ActionType.ACTIVATE_LEADERCARD, messageToClient.getPossibleActions().get(1));
        assertEquals(game.getCurrentPlayer().getBoard().getResourceManager().getStrongbox().toView().toString(), ((ChoseProductionOutputMessage) messageToClient).getStrongbox().toString());

        game.getCurrentPlayer().addPossibleAction(ActionType.CHOOSE_PRODUCTION_OUTPUT);

        chooseProductionOutput.setSecondLeaderCard(false);
        response = chooseProductionOutput.doAction(actionController);
        assertEquals("SUCCESS", response);
        assertEquals("10 6 0 0", game.getCurrentPlayer().getBoard().getResourceManager().getStrongbox().getStoredResources().toString());
        messageToClient = chooseProductionOutput.messagePrepare(actionController);

        assertTrue(messageToClient instanceof ChoseProductionOutputMessage);
        assertEquals(game.getCurrentPlayerNickname(), messageToClient.getPlayerNickname());
        assertEquals("SUCCESS", messageToClient.getError());
        assertEquals(ActionType.CHOOSE_PRODUCTION_OUTPUT, messageToClient.getActionDone());
        assertEquals(ActionType.END_TURN, messageToClient.getPossibleActions().get(0));
        assertEquals(ActionType.ACTIVATE_LEADERCARD, messageToClient.getPossibleActions().get(1));
        assertEquals(game.getCurrentPlayer().getBoard().getResourceManager().getStrongbox().toView().toString(), ((ChoseProductionOutputMessage) messageToClient).getStrongbox().toString());

        game.getCurrentPlayer().addPossibleAction(ActionType.CHOOSE_PRODUCTION_OUTPUT);

        chooseProductionOutput.setFirstLeaderCard(false);
        response = chooseProductionOutput.doAction(actionController);
        assertEquals("SUCCESS", response);
        assertEquals("10 6 0 0", game.getCurrentPlayer().getBoard().getResourceManager().getStrongbox().getStoredResources().toString());
        messageToClient = chooseProductionOutput.messagePrepare(actionController);

        assertTrue(messageToClient instanceof ChoseProductionOutputMessage);
        assertEquals(game.getCurrentPlayerNickname(), messageToClient.getPlayerNickname());
        assertEquals("SUCCESS", messageToClient.getError());
        assertEquals(ActionType.CHOOSE_PRODUCTION_OUTPUT, messageToClient.getActionDone());
        assertEquals(ActionType.END_TURN, messageToClient.getPossibleActions().get(0));
        assertEquals(ActionType.ACTIVATE_LEADERCARD, messageToClient.getPossibleActions().get(1));
        assertEquals(game.getCurrentPlayer().getBoard().getResourceManager().getStrongbox().toView().toString(), ((ChoseProductionOutputMessage) messageToClient).getStrongbox().toString());
    }


    /**
     * FOR TESTING
     * Method used to add three ResourceType of a given type to an ArrayList.
     */
    private static void ArrayListFiller(ArrayList<ResourceType> resourceTypes, int num) {
        ResourceType[] types = ResourceType.values();
        for(int i = 0; i < 3; i ++)
            resourceTypes.add(types[num]);
    }

    /**
     * FOR TESTING
     * Method used to prepare a chooseProductionOutput for testing.
     */
    private static ChooseProductionOutput constructor() {
        ChooseProductionOutput chooseProductionOutput = new ChooseProductionOutput();
        chooseProductionOutput.setBasicProduction(true);
        chooseProductionOutput.setFirstLeaderCard(true);
        chooseProductionOutput.setSecondLeaderCard(true);

        ArrayList<ResourceType> types1 = new ArrayList<>();
        ArrayList<ResourceType> types2 = new ArrayList<>();
        ArrayList<ResourceType> types3 = new ArrayList<>();

        ArrayListFiller(types1, 1);
        ArrayListFiller(types2, 2);
        ArrayListFiller(types3, 3);

        chooseProductionOutput.setFirstLeaderCardOutput(types1);
        chooseProductionOutput.setSecondLeaderCardOutput(types2);
        chooseProductionOutput.setBasicProductionOutput(types3);

        return chooseProductionOutput;
    }
}

