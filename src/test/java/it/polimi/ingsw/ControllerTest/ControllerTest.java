package it.polimi.ingsw.ControllerTest;

import it.polimi.ingsw.CommonTestMethods;
import it.polimi.ingsw.Controller.Actions.Action;
import it.polimi.ingsw.Controller.Controller;
import it.polimi.ingsw.Controller.Observable;
import static org.junit.Assert.*;
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
    public void controllerTest() {
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
    public void controllerTest2() {
        Action action = new Action();
        Controller controller = new Controller();
        ObservableClass observableClass = new ObservableClass();
        CommonTestMethods.gameInitOne(controller.getActionController().getGame());

        observableClass.addObserver(controller);
        observableClass.notify(action);

    }
}
