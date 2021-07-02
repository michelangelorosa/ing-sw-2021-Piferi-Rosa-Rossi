package it.polimi.ingsw.ControllerTest;

import it.polimi.ingsw.CommonTestMethods;
import it.polimi.ingsw.Controller.ControllerClasses.ActionController;
import it.polimi.ingsw.Controller.Actions.*;
import it.polimi.ingsw.Model.Enums.ResourceType;
import it.polimi.ingsw.Model.Exceptions.ModelException;
import it.polimi.ingsw.Model.MessagesToClient.MessageToClient;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for Action
 */
public class ActionTest {

    Action action = new Action();
    ActionController actionController = new ActionController();

    /**
     * test ActionType for Action class
     */
    @Test
    public void actionTypeTest() {
        ActionType type = ActionType.fromString("choose_action");
        assert type != null;
        assertEquals("choose_action", type.getText());

        assertNull(ActionType.fromString("i am not an ActionType"));
    }

    /**
     * tests for Action class
     */
    @Test
    public void ActionTestAll() throws Exception {
        assertFalse(action.isCorrect());
        assertFalse(action.canBeApplied(actionController));
        assertNull(action.doAction(actionController));
        assertNull(action.messagePrepare(actionController));
    }

    /**
     * tests for all the methods for Action class
     */
    @Test
    public void methodTest() {
        ActionController actionController = new ActionController();
        CommonTestMethods.gameInitOne(actionController.getGame());

        actionController.getGame().getCurrentPlayer().addPossibleAction(ActionType.PAY_RESOURCE_CARD);
        actionController.getGame().getCurrentPlayer().addPossibleAction(ActionType.CHOOSE_CARD_SLOT);
        actionController.getGame().getCurrentPlayer().addPossibleAction(ActionType.ACTIVATE_LEADERCARD);

        Action action0 = new PayResourceBuyCard(true, 3, ResourceType.COINS);
        assertTrue(action0.canDoAction(actionController));
        Action action1 = new ChooseCardSlot(3);
        assertTrue(action1.canDoAction(actionController));
        Action action2 = new ActivateLeaderCard(0);
        assertTrue(action2.canDoAction(actionController));
        Action action3 = new AddResource(1, ResourceType.COINS);
        assertFalse(action3.canDoAction(actionController));

        MessageToClient message = new MessageToClient();

        message.addPossibleAction(ActionType.ACTIVATE_LEADERCARD);

        action3.illegalAction(message, actionController);
        assertEquals("You cannot perform this action at this moment", message.getError());
        assertEquals(ActionType.PAY_RESOURCE_CARD, actionController.getGame().getCurrentPlayer().getPossibleActions().get(0));
        assertEquals(ActionType.CHOOSE_CARD_SLOT, actionController.getGame().getCurrentPlayer().getPossibleActions().get(1));
        assertEquals(ActionType.ACTIVATE_LEADERCARD, actionController.getGame().getCurrentPlayer().getPossibleActions().get(2));

    }
}
