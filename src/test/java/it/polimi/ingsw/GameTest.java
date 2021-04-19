package it.polimi.ingsw;

import it.polimi.ingsw.Model.*;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Unit Test for Game Class.
 */
public class GameTest {
    Game game = new Game();

    /**
     * Constructor and getter test for Game Class.
     */
    @Test
    public void constructorTest() {
        assertEquals(GameType.SINGLEPLAYER, game.getGameType());
        assertEquals(0, game.getCurrentPlayerIndex());
        assertEquals(0, game.getPlayers().size());

    }

    /**
     * Getter test for Game Class.
     */
    @Test
    public void getCurrentPlayerTest() {
        joiner(game);
        assertEquals("Giacomo", game.getCurrentPlayer().getNickname());
    }

    /**
     * Test for "join" method in Game Class.
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
     * Test for "nextPlayer" method in Game Class.
     */
    @Test
    public void nextPlayer() {
        joiner(game);
        assertEquals(0, game.getCurrentPlayerIndex());
        assertEquals("Giacomo", game.getCurrentPlayerNickname());

        game.nextPlayer();
        assertEquals(1, game.getCurrentPlayerIndex());
        assertEquals("Valentino", game.getCurrentPlayerNickname());

        game.nextPlayer();
        assertEquals(2, game.getCurrentPlayerIndex());
        assertEquals("Andrea", game.getCurrentPlayerNickname());

        game.nextPlayer();
        assertEquals(3, game.getCurrentPlayerIndex());
        assertEquals("Lorenzo", game.getCurrentPlayerNickname());

        game.nextPlayer();
        assertEquals(0, game.getCurrentPlayerIndex());
        assertEquals("Giacomo", game.getCurrentPlayerNickname());
    }

    /**
     * Test for "setWinner" method in Game Class.
     */
    @Test
    public void setWinnerTest() {
        joiner(game);
        game.setWinner(game.getCurrentPlayer());

        game.nextPlayer();
        game.setWinner(game.getCurrentPlayer());

        game.nextPlayer();
        game.setLooser(game.getCurrentPlayer());

        game.nextPlayer();
        game.setLooser(game.getCurrentPlayer());

        assertEquals(PlayerStatus.WON, game.getPlayers().get(0).getStatus());
        assertEquals(PlayerStatus.WON, game.getPlayers().get(1).getStatus());
        assertEquals(PlayerStatus.LOST, game.getPlayers().get(2).getStatus());
        assertEquals(PlayerStatus.LOST, game.getPlayers().get(3).getStatus());
    }

    /**
     * Test for "checkIfAnyPlayerFinished" method in Game Class.
     */
    @Test
    public void checkIfAnyPlayerFinished() {
        joiner(game);
        assertFalse(game.checkIfAnyPlayerFinished());
        game.getPlayers().get(3).stepAhead(25);
        assertTrue(game.checkIfAnyPlayerFinished());
    }

    /**
     * Test for "finalCountVictoryPoints" method in Game Class.
     */
    @Test
    public void finalCountVictoryPoints() {
        joiner(game);

        game.finalCountVictory();
        for(Player player : game.getPlayers())
            assertEquals(PlayerStatus.WON, player.getStatus());

        game = new Game();
        joiner(game);
        game.getPlayers().get(0).addVictoryPoints(10);
        game.getPlayers().get(1).addVictoryPoints(9);
        game.getPlayers().get(2).addVictoryPoints(9);
        game.getPlayers().get(3).addVictoryPoints(9);

        game.finalCountVictory();

        assertEquals(PlayerStatus.WON, game.getPlayers().get(0).getStatus());
        assertEquals(PlayerStatus.LOST, game.getPlayers().get(1).getStatus());
        assertEquals(PlayerStatus.LOST, game.getPlayers().get(2).getStatus());
        assertEquals(PlayerStatus.LOST, game.getPlayers().get(3).getStatus());

        game = new Game();
        joiner(game);
        game.getPlayers().get(0).addVictoryPoints(10);
        game.getPlayers().get(1).addVictoryPoints(9);
        game.getPlayers().get(2).addVictoryPoints(9);
        game.getPlayers().get(3).addVictoryPoints(11);

        game.finalCountVictory();

        assertEquals(PlayerStatus.LOST, game.getPlayers().get(0).getStatus());
        assertEquals(PlayerStatus.LOST, game.getPlayers().get(1).getStatus());
        assertEquals(PlayerStatus.LOST, game.getPlayers().get(2).getStatus());
        assertEquals(PlayerStatus.WON, game.getPlayers().get(3).getStatus());

        game = new Game();
        joiner(game);
        game.getPlayers().get(0).addVictoryPoints(10);
        game.getPlayers().get(1).addVictoryPoints(10);
        game.getPlayers().get(2).addVictoryPoints(9);
        game.getPlayers().get(3).addVictoryPoints(10);

        game.finalCountVictory();

        assertEquals(PlayerStatus.WON, game.getPlayers().get(0).getStatus());
        assertEquals(PlayerStatus.WON, game.getPlayers().get(1).getStatus());
        assertEquals(PlayerStatus.LOST, game.getPlayers().get(2).getStatus());
        assertEquals(PlayerStatus.WON, game.getPlayers().get(3).getStatus());

        game = new Game();
        joiner(game);
        game.getPlayers().get(0).addVictoryPoints(10);
        game.getPlayers().get(1).addVictoryPoints(10);
        game.getPlayers().get(2).addVictoryPoints(9);
        game.getPlayers().get(3).addVictoryPoints(11);

        game.finalCountVictory();

        assertEquals(PlayerStatus.LOST, game.getPlayers().get(0).getStatus());
        assertEquals(PlayerStatus.LOST, game.getPlayers().get(1).getStatus());
        assertEquals(PlayerStatus.LOST, game.getPlayers().get(2).getStatus());
        assertEquals(PlayerStatus.WON, game.getPlayers().get(3).getStatus());

        game = new Game();
        joiner(game);
        game.getPlayers().get(0).addVictoryPoints(10);
        game.getPlayers().get(1).addVictoryPoints(100);
        game.getPlayers().get(2).addVictoryPoints(100);
        game.getPlayers().get(3).addVictoryPoints(11);

        game.finalCountVictory();

        assertEquals(PlayerStatus.LOST, game.getPlayers().get(0).getStatus());
        assertEquals(PlayerStatus.WON, game.getPlayers().get(1).getStatus());
        assertEquals(PlayerStatus.WON, game.getPlayers().get(2).getStatus());
        assertEquals(PlayerStatus.LOST, game.getPlayers().get(3).getStatus());
    }

    /**
     * FOR TESTING
     * Method used to create a game of four players.
     */
    private void joiner(Game game) {
        game.join("Giacomo");
        game.join("Valentino");
        game.join("Andrea");
        game.join("Lorenzo");
    }
}
