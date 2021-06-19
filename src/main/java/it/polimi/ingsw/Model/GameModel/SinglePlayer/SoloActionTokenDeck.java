package it.polimi.ingsw.Model.GameModel.SinglePlayer;

import it.polimi.ingsw.Model.Enums.SoloActionToken;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * SoloActionTokenDeck Class defines a deck object where all SoloActionTokens of the singleplayer game are stored.
 * <p><b>Attributes:</b></p>
 * <ul>
 *     <li>SoloActionToken[] "tokens": array containing all six tokens of the singleplayer game</li>
 *     <li>int "tokensInDeck": number of tokens remaining in the deck</li>
 * </ul>
 * @author redrick99 & michelangelorosa
 */
public class SoloActionTokenDeck {
    private final SoloActionToken[] tokens = new SoloActionToken[6];
    private int tokensInDeck;

    /**
     * Constructor for SoloActionTokenDeck Class.
     * <p>Fills the array with all SoloActionTokens, shuffles it and sets the number of tokensInDeck to 6.</p>
     */
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

    /**
     * Draws a token from the array. If the BLACKCROSSSHUFFLE token is drawn, reshuffles the array.
     * @return The token drawn from the array.
     */
    public SoloActionToken draw() {
        SoloActionToken token = tokens[--tokensInDeck];

        if(token == SoloActionToken.BLACKCROSSSHUFFLE) {
            shuffle();
            tokensInDeck = 6;
        }

        return token;
    }

    /**
     * Shuffles the deck using java.util.Random.
     */
    private void shuffle() {
        ArrayList<SoloActionToken> tokensList = new ArrayList<>(Arrays.asList(tokens));
        Random random = new Random();

        for(int i = 0; i < tokens.length; i++) {
            tokens[i] = tokensList.remove(random.nextInt(tokensList.size()));
        }
    }

    /**
     * Getter for "tokens" attribute.
     */
    protected SoloActionToken[] getTokens() {
        return this.tokens;
    }
}
