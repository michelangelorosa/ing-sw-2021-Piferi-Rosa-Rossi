package it.polimi.ingsw.ControllerTest;

import it.polimi.ingsw.CommonTestMethods;
import it.polimi.ingsw.Controller.Actions.Action;
import it.polimi.ingsw.Controller.ControllerClasses.ActionController;
import it.polimi.ingsw.Controller.ControllerClasses.Controller;
import it.polimi.ingsw.Controller.ControllerClasses.Observable;
import static org.junit.Assert.*;

import it.polimi.ingsw.Model.Exceptions.ModelException;
import it.polimi.ingsw.Model.GameModel.Player;
import it.polimi.ingsw.Model.MessagesToClient.GameSetMessage;
import it.polimi.ingsw.Model.MessagesToClient.MessageToClient;
import it.polimi.ingsw.Model.MessagesToClient.ModelToView;
import org.junit.Test;

public class ControllerTest {

    public static class ObservableClass extends Observable<Action> {

    }

    public static class ControllerTestClass extends Controller {
        boolean isUpdated = false;

        @Override
        public void update(Action action) {
            this.isUpdated = true;
            this.getActionController().doAction(action);
        }

        public void setUpdated(boolean updated) {
            isUpdated = updated;
        }
    }

    @Test
    public void controllerTest() throws ModelException {
        ObservableClass observableClass = new ObservableClass();
        ControllerTestClass controller = new ControllerTestClass();
        ControllerTestClass controller2 = new ControllerTestClass();

        CommonTestMethods.gameInitOne(controller.getActionController().getGame());

        observableClass.addObserver(controller);
        observableClass.addObserver(controller2);

        Action action = new Action();

        observableClass.notify(action);

        assertTrue(controller.isUpdated);
        assertTrue(controller2.isUpdated);

        controller.setUpdated(false);
        controller2.setUpdated(false);

        observableClass.removeObserver(controller);

        observableClass.notify(action);

        assertFalse(controller.isUpdated);
        assertTrue(controller2.isUpdated);

        controller.setUpdated(false);
        controller2.setUpdated(false);

        observableClass.removeObserver(controller2);

        assertFalse(controller.isUpdated);
        assertFalse(controller2.isUpdated);
    }

    @Test
    public void controllerTest2() throws ModelException {
        Action action = new Action();
        Controller controller = new Controller();
        ObservableClass observableClass = new ObservableClass();
        CommonTestMethods.gameInitOne(controller.getActionController().getGame());

        observableClass.addObserver(controller);
        observableClass.notify(action);

    }

    @Test
    public void prepareViewTest() {
        ActionController actionController = new ActionController();
        actionController.getGame().getPlayers().add(new Player("Gianni", 0, true));
        actionController.prepareViewGame();

        ModelToView modelToView = actionController.getModelToView();
        assertEquals(modelToView, actionController.getModelToView());
    }
}
