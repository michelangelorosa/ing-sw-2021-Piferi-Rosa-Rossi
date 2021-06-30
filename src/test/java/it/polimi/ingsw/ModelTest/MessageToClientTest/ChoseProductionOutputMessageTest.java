package it.polimi.ingsw.ModelTest.MessageToClientTest;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.Model.GameModel.PopeTileClass;
import it.polimi.ingsw.Model.GameModel.Strongbox;
import it.polimi.ingsw.Model.MessagesToClient.ChoseProductionOutputMessage;
import it.polimi.ingsw.View.ReducedModel.Player;
import it.polimi.ingsw.View.ReducedModel.RedStrongbox;
import it.polimi.ingsw.View.User.UserInteraction;
import it.polimi.ingsw.ViewTest.CommonViewTestMethods;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class ChoseProductionOutputMessageTest {
    ChoseProductionOutputMessage message = new ChoseProductionOutputMessage("antonio");
    UserInteraction userInteraction = CommonViewTestMethods.createUserInteraction();

    @Test
    public void genericMethodsTest() {
        assertEquals("antonio", message.getPlayerNickname());
        assertEquals(ActionType.CHOOSE_PRODUCTION_OUTPUT, message.getActionDone());

        RedStrongbox strongbox = new Strongbox();
        message.setStrongbox(strongbox);

        assertEquals(strongbox, message.getStrongbox());
    }

    @Test
    public void updateViewTest() {
        RedStrongbox strongbox = new Strongbox();
        message.setStrongbox(strongbox);

        HashMap<String, PopeTileClass[]> tiles = new HashMap<>();
        PopeTileClass[] popeTileClasses1 = new PopeTileClass[3];
        popeTileClasses1[0] = new PopeTileClass(1);
        popeTileClasses1[1] = new PopeTileClass(2);
        popeTileClasses1[2] = new PopeTileClass(3);

        PopeTileClass[] popeTileClasses2 = new PopeTileClass[3];
        popeTileClasses2[0] = new PopeTileClass(4);
        popeTileClasses2[1] = new PopeTileClass(5);
        popeTileClasses2[2] = new PopeTileClass(6);

        PopeTileClass[] popeTileClasses3 = new PopeTileClass[3];
        popeTileClasses3[0] = new PopeTileClass(7);
        popeTileClasses3[1] = new PopeTileClass(8);
        popeTileClasses3[2] = new PopeTileClass(9);

        PopeTileClass[] popeTileClasses4 = new PopeTileClass[3];
        popeTileClasses4[0] = new PopeTileClass(10);
        popeTileClasses4[1] = new PopeTileClass(11);
        popeTileClasses4[2] = new PopeTileClass(12);

        tiles.put("antonio", popeTileClasses1);
        tiles.put("Lorenzo il Magnifico", popeTileClasses2);
        tiles.put("franco", popeTileClasses3);
        tiles.put("gianpiero", popeTileClasses4);

        message.setPlayersPopeTiles(tiles);

        Player player1 = CommonViewTestMethods.findPlayerByNickname(userInteraction.getGame(), "antonio");
        Player player2 = CommonViewTestMethods.findPlayerByNickname(userInteraction.getGame(), "Lorenzo il Magnifico");
        Player player3 = CommonViewTestMethods.findPlayerByNickname(userInteraction.getGame(), "franco");
        Player player4 = CommonViewTestMethods.findPlayerByNickname(userInteraction.getGame(), "gianpiero");

        assert player1 != null;
        assert player2 != null;
        assert player3 != null;
        assert player4 != null;

        Player player = CommonViewTestMethods.findPlayerByNickname(userInteraction.getGame(), "antonio");
        assert player != null;

        message.setError("error");
        message.updateView(userInteraction);
        assertNotEquals(message.getStrongbox(), player.getStrongbox());

        message.setError("SUCCESS");
        message.updateView(userInteraction);
        assertEquals(message.getStrongbox(), player.getStrongbox());
        assertEquals(message.getFaithPoints(), player.getFaithTrackPosition());
        assertArrayEquals(popeTileClasses1, player1.getPopeTiles());
        assertArrayEquals(popeTileClasses2, player2.getPopeTiles());
        assertArrayEquals(popeTileClasses3, player3.getPopeTiles());
        assertArrayEquals(popeTileClasses4, player4.getPopeTiles());

    }
}
