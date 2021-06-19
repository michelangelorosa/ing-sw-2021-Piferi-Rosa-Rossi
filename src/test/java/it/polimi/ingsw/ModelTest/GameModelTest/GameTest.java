package it.polimi.ingsw.ModelTest.GameModelTest;

import static org.junit.Assert.*;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.Controller.Actions.EndMarket;
import it.polimi.ingsw.Model.Enums.GameType;
import it.polimi.ingsw.Model.Exceptions.ModelException;
import it.polimi.ingsw.Model.GameModel.*;
import it.polimi.ingsw.Model.JSON.ConvertToJSON;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class GameTest {
    Game game = new Game();

    @Test
    public void  joinTest() {
        joiner(game);

        assertEquals("Giacomo", game.getPlayers().get(0).getNickname());
        assertEquals("Valentino", game.getPlayers().get(1).getNickname());
        assertEquals("Andrea", game.getPlayers().get(2).getNickname());
        assertEquals("Lorenzo", game.getPlayers().get(3).getNickname());
    }

    @Test
    public void getCurrentPlayerTest() {
        joiner(game);
        assertEquals("Giacomo", game.getCurrentPlayer().getNickname());
    }


    private void joiner(Game game) {
        game.join("Giacomo");
        game.join("Valentino");
        game.join("Andrea");
        game.join("Lorenzo");

        ResourceStack resourcesRequired = new ResourceStack(0,0,0,0);
        LeaderRequirements cards = new LeaderRequirements(0,0,1,1,0,0,0,0,0,0,0,0);
        ResourceStack discount = new ResourceStack(0,1,0,0);
        game.getCurrentPlayer().getBoard().getLeaderCards()[0] = new LeaderCard(49, 2, resourcesRequired, cards, discount);

        game.getCurrentPlayer().addPossibleAction(ActionType.END_MARKET);
        game.getCurrentPlayer().addPossibleAction(ActionType.END_TURN);
        game.getCurrentPlayer().addPossibleAction(ActionType.RESET_WAREHOUSE);
    }

    @Test
    public void jsonTest() throws FileNotFoundException {
        ConvertToJSON convert = new ConvertToJSON();
        joiner(game);
        game.getDevelopmentCardTable().shuffleTable();
        convert.convertGame(game);
    }

    @Test
    public void getPlayerByNicknameTest() throws FileNotFoundException {
        joiner(game);
        Player Giacomo = game.getPlayerByNickname("Giacomo");
        assertEquals(ActionType.END_MARKET, Giacomo.getPossibleActions().get(0));

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, ()->{game.getPlayerByNickname("Giovanni");});
        assertEquals("[Model.Game]: cannot find player named Giovanni", e.getMessage());
    }

    @Test
    public  void getAllPlayersNameInOrderTest() throws FileNotFoundException {
        joiner(game);
        ArrayList<String> players = new ArrayList<>();
        players.add("Giacomo");
        players.add("Valentino");
        players.add("Andrea");
        players.add("Lorenzo");
        assertEquals(players, game.getAllPlayersNameInOrder());
    }

    @Test
    public void gameStartPlayersTest() throws ModelException {
        ArrayList<String> players = new ArrayList<>();
        ArrayList<String> playersMulti = new ArrayList<>();
        players.add("Giacomo");
        playersMulti.add("Gianni");
        playersMulti.add("Micky");
        playersMulti.add("Cyrano");

        game.gameStartPlayers(players, 1);
        assertEquals(GameType.SINGLEPLAYER, game.getGameType());

        game.gameStartPlayers(playersMulti, 4);
        assertEquals(GameType.MULTIPLAYER, game.getGameType());

    }



}
