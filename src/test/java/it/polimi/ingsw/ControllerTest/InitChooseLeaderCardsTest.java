package it.polimi.ingsw.ControllerTest;

import it.polimi.ingsw.CommonTestMethods;
import it.polimi.ingsw.Controller.ControllerClasses.ActionController;
import it.polimi.ingsw.Controller.Actions.InitChooseLeaderCards;
import it.polimi.ingsw.Model.Enums.Marble;
import it.polimi.ingsw.Model.GameModel.LeaderCard;
import it.polimi.ingsw.Model.GameModel.LeaderRequirements;
import it.polimi.ingsw.Model.GameModel.ResourceStack;
import it.polimi.ingsw.View.ReducedModel.RedLeaderCard;
import org.junit.Test;
import static org.junit.Assert.*;

public class InitChooseLeaderCardsTest {

    InitChooseLeaderCards action;
    ActionController actionController = new ActionController();

    @Test
    public void isCorrectTest() {
        ResourceStack resourceStack = new ResourceStack(0,0,0,0);
        LeaderRequirements leaderRequirements = new LeaderRequirements(0,0,0,0);

        RedLeaderCard card1 = new LeaderCard(1, 12, resourceStack, leaderRequirements, Marble.RED);
        RedLeaderCard card2 = new LeaderCard(1, 12, resourceStack, leaderRequirements, Marble.BLUE);

        action = new InitChooseLeaderCards(null, card2);

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, action :: isCorrect);
        assertEquals("Error: Leader Card 1 is null", e.getMessage());

        action = new InitChooseLeaderCards(card1, null);

        e = assertThrows(IllegalArgumentException.class, action :: isCorrect);
        assertEquals("Error: Leader Card 2 is null", e.getMessage());

        action = new InitChooseLeaderCards(null, null);

        e = assertThrows(IllegalArgumentException.class, action :: isCorrect);
        assertEquals("Error: Leader Card 1 is null", e.getMessage());

        action = new InitChooseLeaderCards(card1, card2);

        assertTrue(action.isCorrect());

    }

    @Test
    public void doActionTest() {
        ResourceStack resourceStack = new ResourceStack(0,0,0,0);
        LeaderRequirements leaderRequirements = new LeaderRequirements(0,0,0,0);

        RedLeaderCard card1 = new LeaderCard(1, 12, resourceStack, leaderRequirements, Marble.RED);
        RedLeaderCard card2 = new LeaderCard(1, 12, resourceStack, leaderRequirements, Marble.BLUE);

        action = new InitChooseLeaderCards(card1, card2);

        CommonTestMethods.gameInitOne(actionController.getGame());

        action.doAction(actionController);

        assertEquals(card1, actionController.getGame().getCurrentPlayer().getBoard().getLeaderCards()[0]);
        assertEquals(card2, actionController.getGame().getCurrentPlayer().getBoard().getLeaderCards()[1]);
    }
}
