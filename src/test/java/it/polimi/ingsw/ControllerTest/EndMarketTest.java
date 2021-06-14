package it.polimi.ingsw.ControllerTest;

import static org.junit.Assert.*;

import it.polimi.ingsw.Controller.ControllerClasses.ActionController;
import it.polimi.ingsw.CommonTestMethods;
import it.polimi.ingsw.Controller.Actions.*;
import it.polimi.ingsw.Model.Exceptions.ModelException;
import it.polimi.ingsw.Model.GameModel.Game;
import it.polimi.ingsw.Model.GameModel.Player;
import it.polimi.ingsw.Model.GameModel.ResourceStack;
import it.polimi.ingsw.Model.GameModel.Warehouse;
import it.polimi.ingsw.Model.MessagesToClient.*;

import org.junit.Test;

/**
 * Unit Test for EndMarket Class.
 */
public class EndMarketTest {

    ActionController actionController = new ActionController();
    Game game = actionController.getGame();
    ResetWarehouse resetWarehouse = actionController.getResetWarehouse();
    MessageToClient messageToClient;

    /**
     * Constructor Test for EndMarket Class.
     */
    @Test
    public void constructorTest() {
        EndMarket endMarket = new EndMarket();
        assertEquals(ActionType.END_MARKET, endMarket.getActionType());
    }

    /**
     * Test for "doAction" and "messagePrepare" methods in EndMarket Class.
     */
    @Test
    public void doActionTest() throws ModelException {
        EndMarket endMarket = new EndMarket();
        CommonTestMethods.gameInitOne(game);
        ResourceStack stack1 = new ResourceStack(1, 1, 1, 1);
        resetWarehouse.setBackupResources(stack1);
        resetWarehouse.setBackupWarehouse(new Warehouse());

        ResourceStack stack = new ResourceStack(2, 1, 0, 1);
        game.getCurrentPlayer().getBoard().getResourceManager().setTemporaryResourcesToPay(stack);

        actionController.getGame().getCurrentPlayer().addPossibleAction(ActionType.END_MARKET);
        actionController.getGame().getCurrentPlayer().addPossibleAction(ActionType.ACTIVATE_LEADERCARD);

        endMarket.doAction(actionController);
        messageToClient = endMarket.messagePrepare(actionController);

        assertTrue(messageToClient instanceof EndMarketMessage);
        assertEquals(ActionType.END_MARKET, messageToClient.getActionDone());
        assertEquals("SUCCESS", messageToClient.getError());
        assertEquals(game.getCurrentPlayerNickname(), messageToClient.getPlayerNickname());
        assertEquals(ActionType.END_TURN, messageToClient.getPossibleActions().get(0));
        assertEquals(ActionType.ACTIVATE_LEADERCARD, messageToClient.getPossibleActions().get(1));
        assertEquals(0, (int)((EndMarketMessage)messageToClient).getPlayersFaithPosition().get(actionController.getGame().getPlayers().get(0).getNickname()));
        assertEquals(4, (int)((EndMarketMessage)messageToClient).getPlayersFaithPosition().get(actionController.getGame().getPlayers().get(1).getNickname()));
        assertEquals(4, (int)((EndMarketMessage)messageToClient).getPlayersFaithPosition().get(actionController.getGame().getPlayers().get(2).getNickname()));
        assertEquals(4, (int)((EndMarketMessage)messageToClient).getPlayersFaithPosition().get(actionController.getGame().getPlayers().get(3).getNickname()));


        assertEquals("0 0 0 0", game.getCurrentPlayer().getBoard().getResourceManager().getTemporaryResourcesToPay().toString());
        for(Player player : game.getPlayers())
            if(player.getNickname().equals(game.getCurrentPlayerNickname()))
                assertEquals(0, player.getFaithTrackPosition());
            else
                assertEquals(4, player.getFaithTrackPosition());
    }

}
