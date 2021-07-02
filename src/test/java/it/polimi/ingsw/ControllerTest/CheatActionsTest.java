package it.polimi.ingsw.ControllerTest;

import it.polimi.ingsw.Controller.Actions.Cheats.*;
import it.polimi.ingsw.Controller.ControllerClasses.ActionController;
import it.polimi.ingsw.Model.GameModel.LeaderCard;
import it.polimi.ingsw.Model.GameModel.LeaderRequirements;
import it.polimi.ingsw.Model.GameModel.Player;
import it.polimi.ingsw.Model.GameModel.ResourceStack;
import it.polimi.ingsw.Model.MessagesToClient.CheatMessage.CheatMessage;
import it.polimi.ingsw.Model.MessagesToClient.MessageToClient;
import it.polimi.ingsw.Model.Server.Server;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Class to test CheatActions
 */
public class CheatActionsTest {
    ActionController actionController = new ActionController();
    MessageToClient message;

    /**
     * Test for DevCardsCheat in cheatAction class
     */
    @Test
    public void devCardCheatTest() {
        actionController.getGame().getPlayers().add(new Player("pippo", 0, true));
        actionController.getGame().getPlayers().add(new Player("antonino", 1, false));

        Server.setCheatMode(true);
        DevCardsCheat action = new DevCardsCheat();
        action.doAction(actionController);
        message = action.messagePrepare(actionController);
        assertEquals(3, actionController.getGame().getCurrentPlayer().getBoard().getDevelopmentCardSlots().getSlots()[0].getLevelOccupied());
        assertEquals(3, actionController.getGame().getCurrentPlayer().getBoard().getDevelopmentCardSlots().getSlots()[1].getLevelOccupied());

        assertTrue(message instanceof CheatMessage);
        assertEquals("pippo", message.getPlayerNickname());
        assertEquals("SUCCESS", message.getError());

        actionController.getGame().nextPlayer();

        Server.setCheatMode(false);
        action.doAction(actionController);
        assertEquals("{ ! CHEAT MODE NOT ACTIVATED ! }", action.getResponse());

        assertNotEquals(3, actionController.getGame().getCurrentPlayer().getBoard().getDevelopmentCardSlots().getSlots()[0].getLevelOccupied());
        assertNotEquals(3, actionController.getGame().getCurrentPlayer().getBoard().getDevelopmentCardSlots().getSlots()[1].getLevelOccupied());
    }

    /**
     * Test for FaithTrackCheat in cheatAction class
     */
    @Test
    public void faithTrackCheatTest() {
        actionController.getGame().getPlayers().add(new Player("pippo", 0, true));
        actionController.getGame().getPlayers().add(new Player("antonino", 1, false));

        Server.setCheatMode(true);
        FaithTrackCheat action = new FaithTrackCheat();
        action.doAction(actionController);
        message = action.messagePrepare(actionController);
        assertTrue(message instanceof CheatMessage);
        assertEquals("pippo", message.getPlayerNickname());
        assertEquals("SUCCESS", message.getError());
        assertEquals(23, actionController.getGame().getCurrentPlayer().getFaithTrackPosition());

        actionController.getGame().nextPlayer();

        Server.setCheatMode(false);
        action.doAction(actionController);
        assertEquals("{ ! CHEAT MODE NOT ACTIVATED ! }", action.getResponse());

        assertNotEquals(23, actionController.getGame().getCurrentPlayer().getFaithTrackPosition());
    }

    /**
     * Test for LeaderCardsCheat in cheatAction class
     */
    @Test
    public void leaderCardsCheat() {
        actionController.getGame().getPlayers().add(new Player("pippo", 0, true));
        actionController.getGame().getPlayers().add(new Player("antonino", 1, false));

        ResourceStack stack = new ResourceStack(0,0,0,0);
        LeaderRequirements requirements = new LeaderRequirements(1,1,1,1);
        LeaderCard card11 = new LeaderCard(1, 1, stack, requirements, stack);
        LeaderCard card12 = new LeaderCard(1, 1, stack, requirements, stack);
        LeaderCard card21 = new LeaderCard(1, 1, stack, requirements, stack);
        LeaderCard card22 = new LeaderCard(1, 1, stack, requirements, stack);

        actionController.getGame().getPlayers().get(0).getBoard().getLeaderCards()[0] = card11;
        actionController.getGame().getPlayers().get(0).getBoard().getLeaderCards()[1] = card12;
        actionController.getGame().getPlayers().get(1).getBoard().getLeaderCards()[0] = card21;
        actionController.getGame().getPlayers().get(1).getBoard().getLeaderCards()[1] = card22;

        Server.setCheatMode(true);
        LeaderCardsCheat action = new LeaderCardsCheat();
        action.doAction(actionController);
        message = action.messagePrepare(actionController);
        assertTrue(message instanceof CheatMessage);
        assertEquals("pippo", message.getPlayerNickname());
        assertEquals("SUCCESS", message.getError());
        assertTrue(actionController.getGame().getCurrentPlayer().getBoard().getLeaderCards()[0].isActive());
        assertTrue(actionController.getGame().getCurrentPlayer().getBoard().getLeaderCards()[1].isActive());

        actionController.getGame().nextPlayer();

        Server.setCheatMode(false);
        action.doAction(actionController);
        assertEquals("{ ! CHEAT MODE NOT ACTIVATED ! }", action.getResponse());

        assertFalse(actionController.getGame().getCurrentPlayer().getBoard().getLeaderCards()[0].isActive());
        assertFalse(actionController.getGame().getCurrentPlayer().getBoard().getLeaderCards()[1].isActive());
    }

    /**
     * Test for StrongboxCheat in cheatAction class
     */
    @Test
    public void strongboxTestCheat() {
        actionController.getGame().getPlayers().add(new Player("pippo", 0, true));
        actionController.getGame().getPlayers().add(new Player("antonino", 1, false));

        Server.setCheatMode(true);
        StrongboxCheat action = new StrongboxCheat();
        action.doAction(actionController);
        message = action.messagePrepare(actionController);
        assertTrue(message instanceof CheatMessage);
        assertEquals("pippo", message.getPlayerNickname());
        assertEquals("SUCCESS", message.getError());
        assertEquals("50 50 50 50", actionController.getGame().getCurrentPlayer().getBoard().getResourceManager().getStrongbox().toString());

        actionController.getGame().nextPlayer();

        Server.setCheatMode(false);
        action.doAction(actionController);
        assertEquals("{ ! CHEAT MODE NOT ACTIVATED ! }", action.getResponse());

        assertNotEquals("50 50 50 50", actionController.getGame().getCurrentPlayer().getBoard().getResourceManager().getStrongbox().toString());
    }

    /**
     * Test for VictoryPointsCheat in cheatAction class
     */
    @Test
    public void victoryPointsCheat() {
        actionController.getGame().getPlayers().add(new Player("pippo", 0, true));
        actionController.getGame().getPlayers().add(new Player("antonino", 1, false));

        Server.setCheatMode(true);
        VictoryPointsCheat action = new VictoryPointsCheat();
        action.doAction(actionController);
        message = action.messagePrepare(actionController);
        assertTrue(message instanceof CheatMessage);
        assertEquals("pippo", message.getPlayerNickname());
        assertEquals("SUCCESS", message.getError());
        assertEquals(50, actionController.getGame().getCurrentPlayer().getVictoryPoints());

        actionController.getGame().nextPlayer();

        Server.setCheatMode(false);
        action.doAction(actionController);
        assertEquals("{ ! CHEAT MODE NOT ACTIVATED ! }", action.getResponse());

        assertNotEquals(50, actionController.getGame().getCurrentPlayer().getVictoryPoints());
    }
}
