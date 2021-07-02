package it.polimi.ingsw.ControllerTest;

import it.polimi.ingsw.CommonTestMethods;
import it.polimi.ingsw.Controller.ControllerClasses.ActionController;
import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.Controller.Actions.InitChooseResources;
import it.polimi.ingsw.Model.Enums.ResourceType;
import it.polimi.ingsw.Model.MessagesToClient.InitChoseResourcesMessage;
import it.polimi.ingsw.Model.MessagesToClient.MessageToClient;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * Class Test for InitChooseResource
 */
public class InitChooseResourcesTest {

    HashMap<Integer, ArrayList<ResourceType>> depotResource = new HashMap<>();
    ArrayList<ResourceType> depot0 = new ArrayList<>();
    ArrayList<ResourceType> depot1 = new ArrayList<>();
    ArrayList<ResourceType> depot2 = new ArrayList<>();

    InitChooseResources action;

    ActionController actionController = new ActionController();

    /**
     * Test to check if the method "isCorrect()" works properly in InitChooseResource Class
     */
    @Test
    public void isCorrectTest() {
        depotResource.put(0, depot0);
        depotResource.put(1, depot1);
        depotResource.put(2, depot2);

        action = new InitChooseResources(depotResource);

        assertTrue(action.isCorrect());

        depotResource.get(0).add(ResourceType.NONE);

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, action :: isCorrect);
        assertEquals("Cannot put type NONE resources in Depots", e.getMessage());

        depotResource.get(0).set(0, ResourceType.SHIELDS);
        depotResource.get(0).add(ResourceType.SHIELDS);
        depotResource.get(0).add(ResourceType.SHIELDS);

        assertTrue(action.isCorrect());

        depotResource.get(0).add(ResourceType.SHIELDS);

        e = assertThrows(IllegalArgumentException.class, action :: isCorrect);
        assertEquals("Cannot put more than 3 resources in Depot 1", e.getMessage());

        if (depotResource.get(0).size() > 0) {
            depotResource.get(0).subList(0, depotResource.get(0).size()).clear();
        }

        depotResource.get(1).add(ResourceType.NONE);

        e = assertThrows(IllegalArgumentException.class, action :: isCorrect);
        assertEquals("Cannot put type NONE resources in Depots", e.getMessage());

        depotResource.get(1).set(0, ResourceType.SHIELDS);
        depotResource.get(1).add(ResourceType.SHIELDS);

        assertTrue(action.isCorrect());

        depotResource.get(1).add(ResourceType.SHIELDS);

        e = assertThrows(IllegalArgumentException.class, action :: isCorrect);
        assertEquals("Cannot put more than 2 resources in Depot 2", e.getMessage());

        if (depotResource.get(1).size() > 0) {
            depotResource.get(1).subList(0, depotResource.get(1).size()).clear();
        }

        depotResource.get(2).add(ResourceType.NONE);

        e = assertThrows(IllegalArgumentException.class, action :: isCorrect);
        assertEquals("Cannot put type NONE resources in Depots", e.getMessage());

        depotResource.get(2).set(0, ResourceType.SHIELDS);

        assertTrue(action.isCorrect());

        depotResource.get(2).add(ResourceType.SHIELDS);

        e = assertThrows(IllegalArgumentException.class, action :: isCorrect);
        assertEquals("Cannot put more than 1 resource in Depot 1", e.getMessage());
    }

    /**
     * Test to check if the method "canBeApplied()" works properly in InitChooseResource Class
     */
    @Test
    public void canBeAppliedTest() {
        depotResource.put(0, depot0);
        depotResource.put(1, depot1);
        depotResource.put(2, depot2);

        action = new InitChooseResources(depotResource);

        depotResource.get(0).add(ResourceType.SHIELDS);
        depotResource.get(0).add(ResourceType.COINS);

        assertFalse(action.canBeApplied(actionController));
        assertEquals("Cannot put two different resources in the same depot! (Depot 0)", action.getResponse());

        if (depotResource.get(0).size() > 0) {
            depotResource.get(0).subList(0, depotResource.get(0).size()).clear();
        }

        depotResource.get(1).add(ResourceType.SHIELDS);
        depotResource.get(1).add(ResourceType.COINS);

        assertFalse(action.canBeApplied(actionController));
        assertEquals("Cannot put two different resources in the same depot! (Depot 1)", action.getResponse());

        if (depotResource.get(1).size() > 0) {
            depotResource.get(1).subList(0, depotResource.get(1).size()).clear();
        }

        depotResource.get(0).add(ResourceType.SHIELDS);
        depotResource.get(0).add(ResourceType.SHIELDS);
        depotResource.get(0).add(ResourceType.SHIELDS);

        depotResource.get(1).add(ResourceType.COINS);
        depotResource.get(1).add(ResourceType.COINS);

        depotResource.get(2).add(ResourceType.STONES);

        assertTrue(action.canBeApplied(actionController));



        if (depotResource.get(1).size() > 0) {
            depotResource.get(1).subList(0, depotResource.get(1).size()).clear();
        }

        if (depotResource.get(2).size() > 0) {
            depotResource.get(2).subList(0, depotResource.get(2).size()).clear();
        }

        depotResource.get(1).add(ResourceType.SHIELDS);
        assertFalse(action.canBeApplied(actionController));
        assertEquals("Cannot put the same type of resources in two different depots!", action.getResponse());

        if (depotResource.get(1).size() > 0) {
            depotResource.get(1).subList(0, depotResource.get(1).size()).clear();
        }

        depotResource.get(2).add(ResourceType.SHIELDS);
        assertFalse(action.canBeApplied(actionController));
        assertEquals("Cannot put the same type of resources in two different depots!", action.getResponse());

        if (depotResource.get(0).size() > 0) {
            depotResource.get(0).subList(0, depotResource.get(0).size()).clear();
        }

        depotResource.get(1).add(ResourceType.SHIELDS);
        assertFalse(action.canBeApplied(actionController));
        assertEquals("Cannot put the same type of resources in two different depots!", action.getResponse());
    }

    /**
     * Test to check if the method "doAction()" and "messagePrepare()" work properly in InitChooseResource Class
     */
    @Test
    public void doActionAndMessagePrepare() {
        MessageToClient message;

        CommonTestMethods.gameInitOne(actionController.getGame());

        depotResource.put(0, depot0);
        depotResource.put(1, depot1);
        depotResource.put(2, depot2);

        action = new InitChooseResources(depotResource);

        action.doAction(actionController);
        message = action.messagePrepare(actionController);

        assertEquals("SUCCESS", action.getResponse());
        assertEquals("SUCCESS", message.getError());
        assertTrue(message instanceof InitChoseResourcesMessage);

        depotResource.get(0).add(ResourceType.SHIELDS);
        depotResource.get(0).add(ResourceType.SHIELDS);
        depotResource.get(0).add(ResourceType.SHIELDS);

        depotResource.get(1).add(ResourceType.COINS);
        depotResource.get(1).add(ResourceType.COINS);

        depotResource.get(2).add(ResourceType.STONES);

        action.doAction(actionController);
        message = action.messagePrepare(actionController);

        assertEquals("SUCCESS", action.getResponse());
        assertEquals("SUCCESS", message.getError());
        assertTrue(message instanceof InitChoseResourcesMessage);

        depotResource.get(1).set(1, ResourceType.SHIELDS);
        action.doAction(actionController);
        message = action.messagePrepare(actionController);

        assertEquals("Cannot put two different resources in the same depot! (Depot 1)", action.getResponse());
        assertEquals("Cannot put two different resources in the same depot! (Depot 1)", message.getError());
        assertTrue(message instanceof InitChoseResourcesMessage);
    }
}
