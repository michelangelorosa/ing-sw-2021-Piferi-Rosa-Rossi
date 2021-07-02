package it.polimi.ingsw.ModelTest.GameModelTest;

import it.polimi.ingsw.Model.Exceptions.ModelException;
import it.polimi.ingsw.Model.GameModel.DevelopmentCardTable;
import it.polimi.ingsw.Model.GameModel.Game;
import it.polimi.ingsw.Model.GameModel.Player;
import it.polimi.ingsw.Model.GameModel.SinglePlayer.SinglePlayer;
import it.polimi.ingsw.Model.Enums.SoloActionToken;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit test for SinglePlayer class
 */

public class SinglePlayerTest {

    static class SinglePlayerTestClass extends SinglePlayer {
        public SinglePlayerTestClass() {
            super();
        }

        @Override
        protected void tokenParser(Game game, SoloActionToken token) {
            super.tokenParser(game, token);
        }

        @Override
        protected Player getLorenzo(Game game) {
            return super.getLorenzo(game);
        }

    }
    SinglePlayerTestClass singlePlayer = new SinglePlayerTestClass();
    Game game = new Game();


    /**
     * Testing the initial conditions of the SinglePlayer game
     * The token types and number has to be correct.
     * Tests also getTokens and tokenShuffle
     */
    @Test
    public void constructorTest(){
        assertEquals(0, singlePlayer.getLorenzoCards());
        assertEquals(0, singlePlayer.getLorenzoFaithPoints());
        assertFalse(singlePlayer.hasLorenzoWon());

    }

    /**
     * Tests the actionTokenHandler method for the actions due in SinglePlayer Game
     */
    @Test
    public void lorenzoTurnTest(){
        game.join("Pippo");
        game.join("Lorenzo il Magnifico");
        game.getPlayers().get(1).stepAhead(25);

        assertEquals(game.getPlayers().get(1), singlePlayer.getLorenzo(game));

        for(SoloActionToken t : SoloActionToken.values())
            singlePlayer.tokenParser(game, t);

        assertEquals(8, singlePlayer.getLorenzoCards());
        assertEquals(24, singlePlayer.getLorenzoFaithPoints());

        singlePlayer.lorenzoTurn(game);

        DevelopmentCardTable table = game.getDevelopmentCardTable();

        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 4; j++)
                while(!table.getDeck(i, j).isEmpty())
                    table.getDeck(i, j).drawCard();

        singlePlayer.tokenParser(game, SoloActionToken.DELETE2BLUE);
        assertTrue(singlePlayer.hasLorenzoWon());

        SoloActionToken token = singlePlayer.getLastToken();
    }

    /**
     * Test to check if the methods work properly in SinglePlayer Class
     */
    @Test
    public void lorenzoTurn2Test() {
        game.join("Pippo");
        game.join("Lorenzo il Magnifico");
        singlePlayer.tokenParser(game, SoloActionToken.DELETE2GREEN);
        assertEquals(2, singlePlayer.getLorenzoCards());
        try {
            game.getDevelopmentCardTable().drawCardFromDeck(2, 0);
        } catch (ModelException e) {
            e.printStackTrace();
        }
        singlePlayer.tokenParser(game, SoloActionToken.DELETE2GREEN);
        assertEquals(4, singlePlayer.getLorenzoCards());

        assertEquals(0, game.getDevelopmentCardTable().getDeck(2, 0).getCardsInDeck());
        assertEquals(3, game.getDevelopmentCardTable().getDeck(1, 0).getCardsInDeck());

        for(int i = 1; i < 3; i++) {
            while(!game.getDevelopmentCardTable().getDeck(i, 0).isEmpty())
                game.getDevelopmentCardTable().getDeck(i, 0).drawCard();
        }

        game.getDevelopmentCardTable().getDeck(0, 0).drawCard();
        game.getDevelopmentCardTable().getDeck(0, 0).drawCard();
        game.getDevelopmentCardTable().getDeck(0, 0).drawCard();

        singlePlayer.tokenParser(game, SoloActionToken.DELETE2GREEN);
        assertEquals(5, singlePlayer.getLorenzoCards());
        assertTrue(singlePlayer.hasLorenzoWon());
        assertTrue(game.getDevelopmentCardTable().columnIsEmpty(0));
        assertNull(game.getDevelopmentCardTable().getLowestDeckByColumn(0));

        singlePlayer.tokenParser(game, SoloActionToken.DELETE2GREEN);
        assertEquals(5, singlePlayer.getLorenzoCards());
    }
}
