package it.polimi.ingsw.ControllerTest;

import it.polimi.ingsw.Controller.ActionController;
import it.polimi.ingsw.Controller.Actions.Action;
import org.junit.Test;
import static org.junit.Assert.*;

public class ActionTest {

    Action action = new Action();
    ActionController actionController = new ActionController();

    @Test
    public void ActionTestAll() {
        assertFalse(action.isCorrect());
        assertFalse(action.canBeApplied(actionController));
        assertNull(action.doAction(actionController));
        assertNull(action.messagePrepare(actionController));
    }
}
