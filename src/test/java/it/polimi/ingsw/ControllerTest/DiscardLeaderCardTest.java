package it.polimi.ingsw.ControllerTest;

import it.polimi.ingsw.CommonTestMethods;
import it.polimi.ingsw.Controller.Actions.DiscardLeaderCard;
import it.polimi.ingsw.Controller.ControllerClasses.ActionController;
import it.polimi.ingsw.Model.GameModel.*;
import it.polimi.ingsw.Model.MessagesToClient.MessageToClient;
import org.junit.Test;

import static it.polimi.ingsw.Controller.Actions.ActionType.DELETE_LEADERCARD;
import static org.junit.Assert.*;

public class DiscardLeaderCardTest {

    ActionController actionController = new ActionController();
    Game game = actionController.getGame();
    MessageToClient messageToClient;
    DiscardLeaderCard action = new DiscardLeaderCard(0);

    /**
     * Test for the method "isCorrect()"
     */
    @Test
    public void isCorrectTest(){
        action = new DiscardLeaderCard(144);
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, action :: isCorrect);
        assertEquals("Not a valid card", e.getMessage());
    }

    /**
     * Test for the method "canBeApplied()"
     */
    @Test
    public void canBeAppliedTest(){
        CommonTestMethods.gameInitOne(game);
        CommonTestMethods.givePlayerLeaderCards(game.getCurrentPlayer(), game.getLeaderCards().get(2), game.getLeaderCards().get(1));
        actionController.getGame().getCurrentPlayer().addPossibleAction(DELETE_LEADERCARD);
        assertTrue(action.canBeApplied(actionController));
        actionController.getGame().getCurrentPlayer().getBoard().getLeaderCards()[0].setActive(true);
        assertFalse(action.canBeApplied(actionController));
    }

    /**
     * Test to check if the method "doAction()" works properly in DiscardLeaderCard Class
     */
    @Test
    public void doActionTest(){
        CommonTestMethods.gameInitOne(game);
        CommonTestMethods.givePlayerLeaderCards(game.getCurrentPlayer(), game.getLeaderCards().get(2), game.getLeaderCards().get(1));
        actionController.getGame().getCurrentPlayer().addPossibleAction(DELETE_LEADERCARD);
        assertTrue(action.canBeApplied(actionController));
        assertEquals("SUCCESS", action.doAction(actionController));
        assertTrue(actionController.getGame().getCurrentPlayer().getBoard().getLeaderCards()[0].isDiscarded());
        assertEquals(1, actionController.getGame().getCurrentPlayer().getFaithTrackPosition());
    }
}
