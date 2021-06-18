package it.polimi.ingsw.Model.GameModel.SinglePlayer;

import it.polimi.ingsw.Model.Enums.Color;
import it.polimi.ingsw.Model.Enums.SoloActionToken;
import it.polimi.ingsw.Model.GameModel.*;

import java.util.Random;

/**
 * Handles all the interactions specific with the single player game,
 * each ActionToken is
 */
public class SinglePlayer {
    private final SoloActionTokenDeck tokenDeck = new SoloActionTokenDeck();
    private int lorenzoCards;
    private int lorenzoFaithPoints;
    private boolean lorenzoWon = false;
    private SoloActionToken lastToken;

    public SinglePlayer() {
        lorenzoCards = 0;
        lorenzoFaithPoints = 0;
    }

    public void lorenzoTurn(Game game) {
        this.tokenParser(game, tokenDeck.draw());
    }

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

    protected Player getLorenzo(Game game) {
        return game.getPlayerByNickname("Lorenzo il Magnifico");
    }

    private void lorenzoDrawsCards(Game game, Color color) {
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
            case PURPLE: column = 3;
            break;
            default: column = -1;
        }

        DevelopmentCardDeck deck = table.getDeck(row, column);

        if(!deck.isEmpty()) {
            deck.drawCard();
            lorenzoCards ++;
        }
        if(deck.isEmpty())
            lorenzoWon = true;
    }

    public int getLorenzoCards() {
        return lorenzoCards;
    }

    public int getLorenzoFaithPoints() {
        return lorenzoFaithPoints;
    }

    public boolean hasLorenzoWon() {
        return lorenzoWon;
    }

    public boolean hasLorenzoWonMarket(Game game) {
        lorenzoFaithPoints = getLorenzo(game).getFaithTrackPosition();
        return lorenzoFaithPoints >= 24;
    }

    public SoloActionToken getLastToken() {
        return lastToken;
    }
}
