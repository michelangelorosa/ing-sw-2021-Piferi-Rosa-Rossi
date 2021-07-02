package it.polimi.ingsw.ModelTest.MessageToClientTest;

import it.polimi.ingsw.Model.Enums.GameType;
import it.polimi.ingsw.Model.MessagesToClient.GameSetMessage;
import it.polimi.ingsw.View.ReducedModel.Game;
import it.polimi.ingsw.View.User.CliController;
import it.polimi.ingsw.View.User.UserInteraction;
import it.polimi.ingsw.ViewTest.CommonViewTestMethods;
import org.junit.Test;


import static org.junit.Assert.*;

/**
 * Tests for GameSetMessage Class.
 */
public class GameSetMessageTest {
    GameSetMessage message = new GameSetMessage("antonio");
    UserInteraction userInteraction = new UserInteraction() {
    };

    /**
     * Test for constructor, getters and setters.
     */
    @Test
    public void genericMethodsTest() {
        Game game = CommonViewTestMethods.gameCreator();
        userInteraction.setUi(new CliController());

        message.setPlayers(game.getPlayers());
        message.setMarket(game.getMarket());
        message.setTable(game.getDevelopmentCardTable());
        message.setFaithTrack(game.getFaithTrack());
    }

    /**
     * updateView method Test.
     */
    @Test
    public void updateViewTest() {
        Game game = CommonViewTestMethods.gameCreator();
        userInteraction.setUi(new CliController());
        userInteraction.setGame(new Game());

        message.setPlayers(game.getPlayers());
        message.setMarket(game.getMarket());
        message.setTable(game.getDevelopmentCardTable());
        message.setFaithTrack(game.getFaithTrack());

        message.updateView(userInteraction);
        assertEquals(game.getPlayers(), userInteraction.getGame().getPlayers());
        for(int i = 0; i < game.getPlayers().size(); i++)
            assertEquals(game.getPlayers().get(i), userInteraction.getGame().getPlayers().get(i));

        assertEquals(game.getFaithTrack(), userInteraction.getGame().getFaithTrack());
        assertEquals(game.getMarket(), userInteraction.getGame().getMarket());
        assertEquals(game.getDevelopmentCardTable(), userInteraction.getGame().getDevelopmentCardTable());
        assertEquals(GameType.SINGLEPLAYER, userInteraction.getGame().getGameType());

        userInteraction.getGame().getPlayers().remove(1);
        message.updateView(userInteraction);
        assertEquals(GameType.MULTIPLAYER, userInteraction.getGame().getGameType());
    }
}
