package it.polimi.ingsw.Model.GameModel.SinglePlayer;

import it.polimi.ingsw.Model.Enums.Color;
import it.polimi.ingsw.Model.Enums.SoloActionToken;
import it.polimi.ingsw.Model.GameModel.*;

import java.util.Random;

/**
 * SinglePlayer Class contains data and methods used by the Model when playing a singleplayer game.
 * <p><b>Attributes:</b></p>
 * <ul>
 *     <li>SoloActionTokenDeck "tokenDeck": deck containing all Action Tokens of the singleplayer game</li>
 *     <li>int "lorenzoCards": indicates the number of Development Cards owned by Lorenzo il Magnifico</li>
 *     <li>int "lorenzoFaithPoints": stores Lorenzo's faith points</li>
 *     <li>boolean "lorenzoWon": indicates if Lorenzo il Magnifico lost the game</li>
 *     <li>SoloActionToken "lastToken": last token drawn from the token deck by Lorenzo il Magnifico</li>
 * </ul>
 * @author redrick99 & michelangelorosa
 */
public class SinglePlayer {
    private final SoloActionTokenDeck tokenDeck = new SoloActionTokenDeck();
    private int lorenzoCards;
    private int lorenzoFaithPoints;
    private boolean lorenzoWon = false;
    private SoloActionToken lastToken;

    /**
     * Constructor for SinglePlayer Class.
     * <p>Sets Lorenzo's Development Cards and Faith Points to 0</p>
     */
    public SinglePlayer() {
        lorenzoCards = 0;
        lorenzoFaithPoints = 0;
    }

    /**
     * Plays Lorenzo's turn at the end of the player's turn.
     * @param game Instance of the game being played by the player.
     */
    public void lorenzoTurn(Game game) {
        this.tokenParser(game, tokenDeck.draw());
    }

    /**
     * Draws a Token from the SoloActionToken deck and executes code based on the type of token drawn.
     * @param game Instance of the game being played by the player.
     * @param token Token drawn from the deck.
     */
    protected void tokenParser(Game game, SoloActionToken token) {
        Player Lorenzo = getLorenzo(game);
        this.lastToken = token;

        switch (token) {
            case DELETE2BLUE: lorenzoDrawsCards(game, Color.BLUE);
                break;
            case DELETE2GREEN: lorenzoDrawsCards(game, Color.GREEN);
                break;
            case DELETE2PURPLE: lorenzoDrawsCards(game, Color.PURPLE);
                break;
            case DELETE2YELLOW: lorenzoDrawsCards(game, Color.YELLOW);
                break;
            case BLACKCROSSPLUS2:   Lorenzo.stepAhead(2);
                                    lorenzoFaithPoints = Lorenzo.getFaithTrackPosition();
                                    if(lorenzoFaithPoints >= 24)
                                        lorenzoWon = true;
                break;
            case BLACKCROSSSHUFFLE: Lorenzo.stepAhead(1);
                                    lorenzoFaithPoints = Lorenzo.getFaithTrackPosition();
                                    if(lorenzoFaithPoints >= 24)
                                        lorenzoWon = true;
                break;
        }
    }

    /**
     * Return the player who's name is "Lorenzo il Magnifico". Since the server does not allow names of more than
     * 16 characters, Lorenzo il Magnifico can only be found in singleplayer games.
     * @param game Instance of the game being played by the player.
     * @return Lorenzo il Magnifico.
     */
    protected Player getLorenzo(Game game) {
        return game.getPlayerByNickname("Lorenzo il Magnifico");
    }

    protected void lorenzoDrawsCards(Game game, Color color) {
        DevelopmentCardTable table = game.getDevelopmentCardTable();
        Random random = new Random();
        int row = random.nextInt(3);
        int column;

        switch (color) {
            case GREEN: column = 0;
            break;
            case BLUE: column = 1;
            break;
            case YELLOW: column = 2;
            break;
            default: column = 3;
        }

        DevelopmentCardDeck deck = table.getDeck(row, column);

        if(!deck.isEmpty()) {
            deck.drawCard();
            lorenzoCards ++;
        }
        if(deck.isEmpty())
            lorenzoWon = true;
    }

    /**
     * Getter for "lorenzoCards" attribute.
     */
    public int getLorenzoCards() {
        return lorenzoCards;
    }

    /**
     * Getter for "lorenzoFaithPoints" attribute.
     */
    public int getLorenzoFaithPoints() {
        return lorenzoFaithPoints;
    }

    /**
     * Getter for "lorenzoWon" attribute.
     */
    public boolean hasLorenzoWon() {
        return lorenzoWon;
    }

    /**
     * Method used ONLY when the player ends a market interaction
     * @param game Instance of the game being played by the player.
     * @return true if Lorenzo il Magnifico won after the player has discarded resources from the market (thus giving
     * faith points to Lorenzo).
     */
    public boolean hasLorenzoWonMarket(Game game) {
        lorenzoFaithPoints = getLorenzo(game).getFaithTrackPosition();
        return lorenzoFaithPoints >= 24;
    }

    /**
     * Getter for "lastToken" attribute.
     */
    public SoloActionToken getLastToken() {
        return lastToken;
    }
}
