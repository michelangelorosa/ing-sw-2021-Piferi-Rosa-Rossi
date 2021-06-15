package it.polimi.ingsw.Model.GameModel.SinglePlayer;

import it.polimi.ingsw.Model.Enums.SoloActionToken;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class SoloActionTokenDeck {
    private final SoloActionToken[] tokens = new SoloActionToken[6];
    private int tokensInDeck;

    public SoloActionTokenDeck() {
        tokens[0] = SoloActionToken.DELETE2BLUE;
        tokens[1] = SoloActionToken.DELETE2GREEN;
        tokens[2] = SoloActionToken.DELETE2PURPLE;
        tokens[3] = SoloActionToken.DELETE2YELLOW;
        tokens[4] = SoloActionToken.BLACKCROSSPLUS2;
        tokens[5] = SoloActionToken.BLACKCROSSSHUFFLE;
        shuffle();
        tokensInDeck = 6;
    }

    public SoloActionToken draw() {
        SoloActionToken token = tokens[--tokensInDeck];

        if(token == SoloActionToken.BLACKCROSSSHUFFLE) {
            shuffle();
            tokensInDeck = 6;
        }

        return token;
    }

    private void shuffle() {
        ArrayList<SoloActionToken> tokensList = new ArrayList<>(Arrays.asList(tokens));
        Random random = new Random();

        for(int i = 0; i < tokens.length; i++) {
            tokens[i] = tokensList.remove(random.nextInt(tokensList.size()));
        }
    }

    protected SoloActionToken[] getTokens() {
        return this.tokens;
    }
}
