package it.polimi.ingsw.ModelTest.MessageToClientTest;

import it.polimi.ingsw.Model.Enums.Marble;
import it.polimi.ingsw.Model.GameModel.LeaderCard;
import it.polimi.ingsw.Model.GameModel.LeaderRequirements;
import it.polimi.ingsw.Model.GameModel.PopeTileClass;
import it.polimi.ingsw.Model.GameModel.ResourceStack;
import it.polimi.ingsw.Model.MessagesToClient.DiscardLeaderCardMessage;
import it.polimi.ingsw.View.ReducedModel.Player;
import it.polimi.ingsw.View.ReducedModel.RedLeaderCard;
import it.polimi.ingsw.View.User.UserInteraction;
import it.polimi.ingsw.ViewTest.CommonViewTestMethods;
import org.junit.Test;

import java.util.HashMap;

import static it.polimi.ingsw.Controller.Actions.ActionType.*;
import static org.junit.Assert.*;

public class DiscardLeaderCardMessageTest {
    DiscardLeaderCardMessage leaderCardMessage = new DiscardLeaderCardMessage("antonio");
    UserInteraction userInteraction = CommonViewTestMethods.createUserInteraction();

    @Test
    public void genericMethodsTest(){
        assertEquals(DELETE_LEADERCARD, leaderCardMessage.getActionDone());
        assertEquals("antonio", leaderCardMessage.getPlayerNickname());

        ResourceStack stack = new ResourceStack(1, 2, 3, 4);
        LeaderRequirements requirements = new LeaderRequirements(0, 0, 0, 0);

        RedLeaderCard leaderCard = new LeaderCard(1, 1, stack, requirements, Marble.GREY);

        leaderCardMessage.setLeaderCard(leaderCard);
        leaderCardMessage.setNumber(0);
        assertEquals(leaderCard, leaderCardMessage.getLeaderCard());
        assertEquals(0, leaderCardMessage.getNumber());
    }

    @Test
    public void updateViewTest() {
        ResourceStack stack = new ResourceStack(1, 2, 3, 4);
        LeaderRequirements requirements = new LeaderRequirements(0, 0, 0, 0);

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

        leaderCardMessage.setPlayersPopeTiles(tiles);

        Player player1 = CommonViewTestMethods.findPlayerByNickname(userInteraction.getGame(), "antonio");
        Player player2 = CommonViewTestMethods.findPlayerByNickname(userInteraction.getGame(), "Lorenzo il Magnifico");
        Player player3 = CommonViewTestMethods.findPlayerByNickname(userInteraction.getGame(), "franco");
        Player player4 = CommonViewTestMethods.findPlayerByNickname(userInteraction.getGame(), "gianpiero");

        assert player1 != null;
        assert player2 != null;
        assert player3 != null;
        assert player4 != null;

        RedLeaderCard leaderCard = new LeaderCard(1, 1, stack, requirements, Marble.GREY);

        leaderCardMessage.setLeaderCard(leaderCard);
        leaderCardMessage.setNumber(0);

        Player player = CommonViewTestMethods.findPlayerByNickname(userInteraction.getGame(), "antonio");
        assert player != null;

        leaderCardMessage.setError("error");
        leaderCardMessage.updateView(userInteraction);
        assertNotEquals(leaderCardMessage.getLeaderCard(), player.getLeaderCards()[leaderCardMessage.getNumber()]);

        leaderCardMessage.setError("SUCCESS");
        leaderCardMessage.updateView(userInteraction);
        assertEquals(leaderCardMessage.getLeaderCard(), player.getLeaderCards()[leaderCardMessage.getNumber()]);
        assertEquals(leaderCardMessage.getFaithPoints(), player.getFaithTrackPosition());
        assertArrayEquals(popeTileClasses1, player1.getPopeTiles());
        assertArrayEquals(popeTileClasses2, player2.getPopeTiles());
        assertArrayEquals(popeTileClasses3, player3.getPopeTiles());
        assertArrayEquals(popeTileClasses4, player4.getPopeTiles());
    }
}
