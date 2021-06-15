package it.polimi.ingsw.ModelTest.GameModelTest;

import it.polimi.ingsw.Model.Enums.SoloActionToken;
import it.polimi.ingsw.Model.GameModel.SinglePlayer.SoloActionTokenDeck;
import org.junit.Test;

public class SoloActionTokenDeckTest {

    static class TokenDeckTest extends SoloActionTokenDeck {
        public TokenDeckTest() {
            super();
        }

        @Override
        protected SoloActionToken[] getTokens() {
            return super.getTokens();
        }
    }

    TokenDeckTest deck = new TokenDeckTest();

    @Test
    public void drawTest() {
        for(SoloActionToken t : deck.getTokens())
            print(t);
        System.out.println("\n\n");

        SoloActionToken token;
        do {
            token = deck.draw();
            print(token);
        } while(token != SoloActionToken.BLACKCROSSSHUFFLE);

        System.out.println("\n\n");

        for(SoloActionToken t : deck.getTokens())
            print(t);
    }

    private void print(SoloActionToken token) {
        for(String s : token.toCli())
            System.out.println(s);
    }
}
