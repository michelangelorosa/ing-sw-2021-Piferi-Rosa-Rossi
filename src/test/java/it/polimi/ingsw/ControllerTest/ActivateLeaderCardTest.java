package it.polimi.ingsw.ControllerTest;

import it.polimi.ingsw.CommonTestMethods;
import it.polimi.ingsw.Controller.ControllerClasses.ActionController;
import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.Controller.Actions.ActivateLeaderCard;
import it.polimi.ingsw.Model.MessagesToClient.ActivateLeaderCardMessage;
import it.polimi.ingsw.Model.MessagesToClient.MessageToClient;
import it.polimi.ingsw.Model.GameModel.ResourceStack;
import org.junit.Test;
import static org.junit.Assert.*;

public class ActivateLeaderCardTest {

    ActivateLeaderCard action;

    ActionController actionController = new ActionController();

    @Test
    public void isCorrectTest() {
        action = new ActivateLeaderCard(-1);
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, action :: isCorrect);
        assertEquals("Leader Card index out of bounds.", e.getMessage());

        action = new ActivateLeaderCard(2);
        e = assertThrows(IllegalArgumentException.class, action :: isCorrect);
        assertEquals("Leader Card index out of bounds.", e.getMessage());

        action = new ActivateLeaderCard(0);
        assertTrue(action.isCorrect());

        action = new ActivateLeaderCard(1);
        assertTrue(action.isCorrect());
    }

    @Test
    public void canBeAppliedTest() {
        CommonTestMethods.gameInitOne(actionController.getGame());

        actionController.getGame().getCurrentPlayer().getBoard().getLeaderCards()[0] = actionController.getGame().getLeaderCards().get(0);
        actionController.getGame().getCurrentPlayer().getBoard().getLeaderCards()[1] = actionController.getGame().getLeaderCards().get(1);


        actionController.getGame().getCurrentPlayer().getBoard().getLeaderCards()[0].setActive(true);
        action = new ActivateLeaderCard(0);
        assertFalse(action.canBeApplied(actionController));
        action = new ActivateLeaderCard(1);
        assertTrue(action.canBeApplied(actionController));

        actionController.getGame().getCurrentPlayer().getBoard().getLeaderCards()[1].setActive(true);
        action = new ActivateLeaderCard(1);
        assertFalse(action.canBeApplied(actionController));
    }

    @Test
    public void doActionTest() {
        CommonTestMethods.gameInitOne(actionController.getGame());

        MessageToClient message;

        action = new ActivateLeaderCard(0);
        action.doAction(actionController);
        message = action.messagePrepare(actionController);
        assertTrue(message instanceof ActivateLeaderCardMessage);
        assertEquals("You cannot perform this action at this moment", message.getError());

        actionController.getGame().getCurrentPlayer().addPossibleAction(ActionType.ACTIVATE_LEADERCARD);

        actionController.getGame().getCurrentPlayer().getBoard().getLeaderCards()[0] = actionController.getGame().getLeaderCards().get(0);
        actionController.getGame().getCurrentPlayer().getBoard().getLeaderCards()[1] = actionController.getGame().getLeaderCards().get(1);
        assertFalse(actionController.getGame().getCurrentPlayer().getBoard().getLeaderCards()[0].isActive());
        assertFalse(actionController.getGame().getCurrentPlayer().getBoard().getLeaderCards()[1].isActive());

        action = new ActivateLeaderCard(0);
        action.doAction(actionController);
        message = action.messagePrepare(actionController);
        assertTrue(message instanceof ActivateLeaderCardMessage);
        assertEquals("Not enough resources to activate Leader Card", message.getError());

        actionController.getGame().getCurrentPlayer().getBoard().getResourceManager().getStrongbox().addToAllTypes(new ResourceStack(20, 20, 20, 20));
        actionController.getGame().getCurrentPlayer().addPossibleAction(ActionType.ACTIVATE_LEADERCARD);
        action = new ActivateLeaderCard(0);
        actionController.getGame().getCurrentPlayer().addPossibleAction(ActionType.END_TURN);
        action.doAction(actionController);

        assertTrue(actionController.getGame().getCurrentPlayer().getBoard().getLeaderCards()[0].isActive());
        assertFalse(actionController.getGame().getCurrentPlayer().getBoard().getLeaderCards()[1].isActive());

        message = action.messagePrepare(actionController);
        assertTrue(message instanceof ActivateLeaderCardMessage);
        assertEquals("SUCCESS", message.getError());
        assertEquals(actionController.getGame().getCurrentPlayer().getBoard().getResourceManager().getWarehouse(), ((ActivateLeaderCardMessage) message).getWarehouse());
        assertEquals(actionController.getGame().getCurrentPlayerNickname(), message.getPlayerNickname());
        assertEquals(0, ((ActivateLeaderCardMessage) message).getLeaderCardPosition());
        assertEquals(actionController.getGame().getCurrentPlayer().getBoard().getLeaderCards()[0], ((ActivateLeaderCardMessage) message).getLeaderCard());

        actionController.getGame().getCurrentPlayer().addPossibleAction(ActionType.ACTIVATE_LEADERCARD);

        action.doAction(actionController);
        message = action.messagePrepare(actionController);
        assertTrue(message instanceof ActivateLeaderCardMessage);
        assertEquals("Leader Card has already been activated", message.getError());

    }
}
