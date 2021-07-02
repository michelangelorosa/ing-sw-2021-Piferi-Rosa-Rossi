package it.polimi.ingsw.ModelTest.GameModelTest;

import static org.junit.Assert.*;

import it.polimi.ingsw.CommonTestMethods;
import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.Controller.Actions.EndMarket;
import it.polimi.ingsw.Model.Enums.*;
import it.polimi.ingsw.Model.Exceptions.ModelException;
import it.polimi.ingsw.Model.GameModel.*;
import it.polimi.ingsw.Model.JSON.ConvertToJSON;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Unit test for Game Class.
 */
public class GameTest {
    Game game = new Game();

    /**
     * Support method for tests
     */
    @Test
    public void  joinTest() {
        joiner(game);

        assertEquals("Giacomo", game.getPlayers().get(0).getNickname());
        assertEquals("Valentino", game.getPlayers().get(1).getNickname());
        assertEquals("Andrea", game.getPlayers().get(2).getNickname());
        assertEquals("Lorenzo", game.getPlayers().get(3).getNickname());
    }

    /**
     * Test to check if the method "getCurrentPlayer()" works properly in Game Class
     */
    @Test
    public void getCurrentPlayerTest() {
        joiner(game);
        assertEquals("Giacomo", game.getCurrentPlayer().getNickname());
    }


    /**
     * Support method for tests
     * @param game is the current game
     */
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

    /**
     * Test to check if the method "convertGame()" works properly in Game Class
     */
    @Test
    public void jsonTest() throws FileNotFoundException, ModelException {
        ConvertToJSON convert = new ConvertToJSON();
        int cardId1=43,victoryPoints1=32,blueCardLv1=1,purpleCardLv1=2,yellowCardLv1=1,greenCardLv1=0,blueCardLv2=0,purpleCardLv2=0,yellowCardLv2=0,greenCardLv2=4,blueCardLv3=0,purpleCardLv3=0,yellowCardLv3=2,greenCardLv3=0;
        ResourceStack resourceStack1 = new ResourceStack(1,4,6,7);
        ResourceStack resourceStackDiscount = new ResourceStack(5,7,0,0);
        LeaderRequirements leaderRequirements = new LeaderRequirements(blueCardLv1,purpleCardLv1,yellowCardLv1,greenCardLv1,blueCardLv2,purpleCardLv2,yellowCardLv2,greenCardLv2,blueCardLv3,purpleCardLv3,yellowCardLv3,greenCardLv3);

        LeaderCard leaderCard1 = new LeaderCard(cardId1,victoryPoints1,resourceStack1,leaderRequirements,resourceStackDiscount);
        joiner(game);
        for(int i = 0; i < game.getPlayers().size(); i++){
            game.getPlayers().get(i).getBoard().getLeaderCards()[0] = leaderCard1;
            game.getPlayers().get(i).getBoard().getLeaderCards()[1] = leaderCard1;
        }
        game.getDevelopmentCardTable().shuffleTable();
        game.getDevelopmentCardTable().drawCardFromDeck(0,0);
        convert.convertGame(game);
    }

    /**
     * Test to check if the method "getPlayerByNickname()" works properly in Game Class
     */
    @Test
    public void getPlayerByNicknameTest() throws FileNotFoundException {
        joiner(game);
        Player Giacomo = game.getPlayerByNickname("Giacomo");
        assertEquals(ActionType.END_MARKET, Giacomo.getPossibleActions().get(0));

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, ()->{game.getPlayerByNickname("Giovanni");});
        assertEquals("[Model.Game]: cannot find player named Giovanni", e.getMessage());
    }

    /**
     * Test to check if the method "getAllPlayersNameInOrder()" works properly in Game Class
     */
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

    /**
     * Test to check if the method "gameStartPlayers()" works properly in Game Class
     */
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


    /**
     * Test to check if the method "gameStartResource()" works properly in Game Class
     */
    @Test
    public void gameStartResourcesTest() {
        joiner(game);
        assertEquals(0, game.gameStartResources("Giacomo"));
        assertEquals(1, game.gameStartResources("Valentino"));
        assertEquals(1, game.gameStartResources("Andrea"));
        assertEquals(1, game.getPlayers().get(2).getFaithTrackPosition());
        assertEquals(2, game.gameStartResources("Lorenzo"));
        assertEquals(1, game.getPlayers().get(3).getFaithTrackPosition());

        game.getPlayers().add(new Player("pippo", 5, false));
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {game.gameStartResources("pippo");});
        assertEquals("[Model.Game.gameStartResources]: player's position was 5", e.getMessage());
    }

    /**
     * Test to check if the method "changeCurrentPlayerReconnection()" works properly in Game Class
     */
    @Test
    public void changeCurrentPlayerReconnectionTest(){
        joiner(game);
        assertEquals("Giacomo", game.getCurrentPlayer().getNickname());
        game.changeCurrentPlayerReconnection("Andrea");
        assertEquals("Andrea", game.getCurrentPlayer().getNickname());
    }

    /**
     * Test to check if the method "isPlayerInGameNickname()" works properly in Game Class
     */
    @Test
    public void isPlayerInGameNicknameTest(){
        assertFalse(game.isPlayerInGameNickname("Giacomo"));
        joiner(game);
        assertFalse(game.isPlayerInGameNickname("Antonio"));
        assertTrue(game.isPlayerInGameNickname("Giacomo"));
    }

    /**
     * Test to check if the method "removePlayerByNickname()" works properly in Game Class
     */
    @Test
    public void removePlayerByNickname(){
        joiner(game);
        assertTrue(game.isPlayerInGameNickname("Giacomo"));
        game.removePlayerByNickname("Giacomo");
        assertFalse(game.isPlayerInGameNickname("Giacomo"));
    }

    /**
     * Method to check if exceptions are thrown correctly
     */
    @Test
    public void fillerTest() {
        joiner(game);
        assertEquals("Lorenzo", game.getPreviousPlayer().getNickname());
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> game.join("Gianni"));
        assertEquals("Maximum number of players reached!", e.getMessage());
        game.nextPlayer();
        game.nextPlayer();
        game.nextPlayer();
        game.removePlayerByNickname("Lorenzo");
        game.join("Giacomo");

        ModelException e1 = assertThrows(ModelException.class, () -> game.gameStartPlayers(new ArrayList<>(), 10));
        assertEquals("Too many players", e1.getMessage());
    }

    /**
     * Test to check if the method "allIdle()" works properly in Game Class
     */
    @Test
    public void allIdleTest() {
        joiner(game);
        assertFalse(game.allIdle());
        for(Player p : game.getPlayers())
            p.setStatus(PlayerStatus.IDLE);
        assertTrue(game.allIdle());
    }

    /**
     * Test to check if the method "removeCardWhenPayingDisconnection()" works properly in Game Class
     */
    @Test
    public void removeCardWhenPayingDisconnection() {
        joiner(game);
        game.getCurrentPlayer().getPossibleActions().add(ActionType.PAY_RESOURCE_CARD);
        game.removeCardWhenPayingDisconnection(2,0);
        assertEquals(0, game.getCurrentPlayer().getBoard().getDevelopmentCardSlots().getSlots()[0].getLevelOccupied());

        game.getCurrentPlayer().getPossibleActions().add(ActionType.CHOOSE_CARD_SLOT);
        game.removeCardWhenPayingDisconnection(2,0);
        assertEquals(1, game.getCurrentPlayer().getBoard().getDevelopmentCardSlots().getSlots()[0].getLevelOccupied());

        try {
            game.getDevelopmentCardTable().drawCardFromDeck(2,0);
            game.getDevelopmentCardTable().drawCardFromDeck(2,0);
        } catch (ModelException e) {
            e.printStackTrace();
        }

        game.removeCardWhenPayingDisconnection(2,0);

    }

}
