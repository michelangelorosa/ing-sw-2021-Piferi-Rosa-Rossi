package it.polimi.ingsw.ModelTest.MessageToClientTest;

import it.polimi.ingsw.Model.Enums.GameType;
import it.polimi.ingsw.Model.Enums.SoloActionToken;
import it.polimi.ingsw.Model.MessagesToClient.EndTurnSingleplayerMessage;
import it.polimi.ingsw.View.ReducedModel.Game;
import it.polimi.ingsw.View.ReducedModel.Player;
import it.polimi.ingsw.View.User.CliController;
import it.polimi.ingsw.View.User.UIActions;
import it.polimi.ingsw.View.User.UserInteraction;
import org.junit.Test;
import static org.junit.Assert.*;

public class EndTurnSingleplayerMessageTest {

    EndTurnSingleplayerMessage message = new EndTurnSingleplayerMessage("pippo");

    @Test
    public void commonMethodsTest() {
        message.setLorenzoFaith(10);
        message.setToken(SoloActionToken.BLACKCROSSPLUS2);
        message.setVictoryPoints(20);

        assertEquals(10, message.getLorenzoFaith());
        assertEquals(SoloActionToken.BLACKCROSSPLUS2, message.getToken());
        assertEquals(20, message.getVictoryPoints());
    }

    @Test
    public void updateViewTest() {
        UserInteraction userInteraction = new UserInteraction() {
        };
        Game game = new Game();
        game.getPlayers().add(new Player("pippo", 0, true));
        game.getPlayers().add(new Player("Lorenzo il Magnifico", 1, false));
        game.setGameType(GameType.SINGLEPLAYER);

        userInteraction.setGame(game);
        userInteraction.setUi(new CliController());

        message.setError("SINGLEPLAYER");
        message.updateView(userInteraction);
        assertEquals(UIActions.SINGLEPLAYER_TURN, userInteraction.getUiAction());

        message.setError("SINGLEPLAYER LOOSE");
        message.updateView(userInteraction);
        assertEquals(UIActions.SINGLEPLAYER_END_LOST, userInteraction.getUiAction());

        message.setError("SINGLEPLAYER WIN");
        message.updateView(userInteraction);
        assertEquals(UIActions.SINGLEPLAYER_END_WON, userInteraction.getUiAction());
    }
}
