package it.polimi.ingsw.ControllerTest;

import it.polimi.ingsw.CommonTestMethods;
import it.polimi.ingsw.Controller.Actions.ChooseCardSlot;
import it.polimi.ingsw.Controller.Actions.ChooseLeaderCard;
import it.polimi.ingsw.Controller.Actions.ChooseProductionOutput;
import it.polimi.ingsw.Controller.Actions.ResetWarehouse;
import it.polimi.ingsw.Model.*;
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

    ResetWarehouse resetWarehouse = new ResetWarehouse();
    ChooseProductionOutput chooseProductionOutput = new ChooseProductionOutput();
    ChooseCardSlot chooseCardSlot = new ChooseCardSlot(0);
    Game game = new Game();

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
        assertFalse(chooseLeaderCard.canBeApplied(game));
        game.getCurrentPlayer().getBoard().activateLeaderCard(game.getCurrentPlayer().getBoard().getLeaderCards()[0]);
        assertTrue(chooseLeaderCard.canBeApplied(game));

        chooseLeaderCard = new ChooseLeaderCard(1);
        assertFalse(chooseLeaderCard.canBeApplied(game));
        game.getCurrentPlayer().getBoard().activateLeaderCard(game.getCurrentPlayer().getBoard().getLeaderCards()[1]);
        assertFalse(chooseLeaderCard.canBeApplied(game));
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

        String response;

        response = chooseLeaderCard.doAction(game, chooseProductionOutput, chooseCardSlot, resetWarehouse);
        assertEquals("Leader Card not active or not of type WHITE MARBLE", response);

        game.getCurrentPlayer().getBoard().activateLeaderCard(game.getCurrentPlayer().getBoard().getLeaderCards()[0]);
        response = chooseLeaderCard.doAction(game, chooseProductionOutput, chooseCardSlot, resetWarehouse);
        assertEquals("Another White Marble", response);
        response = chooseLeaderCard.doAction(game, chooseProductionOutput, chooseCardSlot, resetWarehouse);
        assertEquals("SUCCESS", response);
    }
}
