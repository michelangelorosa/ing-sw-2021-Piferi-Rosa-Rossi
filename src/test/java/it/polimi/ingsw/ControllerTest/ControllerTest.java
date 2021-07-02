package it.polimi.ingsw.ControllerTest;

import it.polimi.ingsw.CommonTestMethods;
import it.polimi.ingsw.Controller.Actions.*;
import it.polimi.ingsw.Controller.ControllerClasses.ActionController;
import it.polimi.ingsw.Controller.ControllerClasses.Controller;
import it.polimi.ingsw.Controller.ControllerClasses.Observable;
import static org.junit.Assert.*;

import it.polimi.ingsw.Model.Exceptions.ModelException;
import it.polimi.ingsw.Model.GameModel.Player;
import it.polimi.ingsw.Model.GameModel.ResourceStack;
import it.polimi.ingsw.Model.GameModel.Warehouse;
import it.polimi.ingsw.Model.MessagesToClient.MessageToClient;
import it.polimi.ingsw.Model.MessagesToClient.ModelToView;
import it.polimi.ingsw.Model.MessagesToClient.OtherMessages.DisconnectedMessage;
import org.junit.Test;

/**
 * Unit Test for Controller Class.
 */
public class ControllerTest {

    /**
     * Creating ObservableClass that extends Observable<Action>
     */
    public static class ObservableClass extends Observable<Action> {

    }

    /**
     * Creating ControllerTestClass that extends Controller
     */
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

    /** Method to test controller*/
    @Test
    public void controllerTest() throws ModelException {
        ObservableClass observableClass = new ObservableClass();
        ControllerTestClass controller = new ControllerTestClass();
        ControllerTestClass controller2 = new ControllerTestClass();

        CommonTestMethods.gameInitOne(controller.getActionController().getGame());
        CommonTestMethods.gameInitOne(controller2.getActionController().getGame());

        observableClass.addObserver(controller);
        observableClass.addObserver(controller2);

        Action action = new Action();
        action.setNickname(controller.getActionController().getGame().getCurrentPlayerNickname());

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

    /** Method to test controller*/
    @Test
    public void controllerTest2() {
        Action action = new Action();
        Controller controller = new Controller();
        ObservableClass observableClass = new ObservableClass();
        CommonTestMethods.gameInitOne(controller.getActionController().getGame());
        action.setNickname(controller.getActionController().getGame().getCurrentPlayerNickname());

        observableClass.addObserver(controller);
        observableClass.notify(action);

        assertTrue(observableClass.containsObserver(controller));
        observableClass.clearObservers();
        assertFalse(observableClass.containsObserver(controller));
    }

    /** Method to test prepareView in Controller*/
    @Test
    public void prepareViewTest() {
        ActionController actionController = new ActionController();
        actionController.getGame().getPlayers().add(new Player("Gianni", 0, true));
        actionController.prepareViewGame();

        ModelToView modelToView = actionController.getModelToView();
        assertEquals(modelToView, actionController.getModelToView());
    }

    /** Method to test actionController in Conroller*/
    @Test
    public void actionControllerTest() {
        ActionController actionController = new ActionController();
        CommonTestMethods.gameInitOne(actionController.getGame());

        Action action = new ResetWarehouse();
        actionController.getResetWarehouse().setBackupResources(new ResourceStack(0,0,0,0));
        actionController.getResetWarehouse().setBackupWarehouse(new Warehouse());
        action.setNickname(actionController.getGame().getCurrentPlayerNickname());
        actionController.doAction(action);

        action = new ChooseCardSlot(0);
        actionController.getChooseCardSlot().setCardSlot(0);
        action.setNickname(actionController.getGame().getCurrentPlayerNickname());
        actionController.doAction(action);

        action = new ChooseProductionOutput();
        actionController.getChooseProductionOutput().setBasicProduction(false);
        actionController.getChooseProductionOutput().setFirstLeaderCard(false);
        actionController.getChooseProductionOutput().setSecondLeaderCard(false);
        action.setNickname(actionController.getGame().getCurrentPlayerNickname());
        actionController.doAction(action);

        assertFalse(actionController.gameIsEmpty());
        assertNotNull(actionController.getPersistence());

    }

    /** Method to test disconnected in Controller*/
    @Test
    public void disconnectedTest() {
        ActionController actionController = new ActionController();
        CommonTestMethods.gameInitOne(actionController.getGame());
        assertEquals(actionController.getGame().getPlayers().get(0), actionController.getGame().getCurrentPlayer());
        MessageToClient message = actionController.disconnected("Zero");
        assertEquals(actionController.getGame().getPlayers().get(1), actionController.getGame().getCurrentPlayer());
        assertTrue(message instanceof DisconnectedMessage);
    }

    /**Method to test exceptions*/
    @Test
    public void exceptionTest() {
        class ActionTest1 extends Action {
            protected ActionTest1(ActionController actionController) {
                this.setNickname(actionController.getGame().getCurrentPlayerNickname());
            }

            @Override
            public String doAction(ActionController actionController) throws ModelException{
                throw new ModelException("ModelException!");
            }
        }

        class ActionTest2 extends Action {
            protected ActionTest2(ActionController actionController) {
                this.setNickname(actionController.getGame().getCurrentPlayerNickname());
            }

            @Override
            public String doAction(ActionController actionController) throws IllegalArgumentException{
                throw new IllegalArgumentException("IllegalArgumentException!");
            }
        }

        class ActionTest3 extends Action {
            protected ActionTest3(ActionController actionController) {
                this.setNickname(actionController.getGame().getCurrentPlayerNickname());
            }

            @Override
            public String doAction(ActionController actionController) throws Exception{
                throw new Exception("Exception!");
            }
        }

        ActionController actionController = new ActionController();
        actionController.getGame().getPlayers().add(new Player("Zero", 0, true));

        ActionTest1 actionTest1 = new ActionTest1(actionController);
        actionController.doAction(actionTest1);
        ActionTest2 actionTest2 = new ActionTest2(actionController);
        actionController.doAction(actionTest2);
        ActionTest3 actionTest3 = new ActionTest3(actionController);
        actionController.doAction(actionTest3);
    }
}
