package it.polimi.ingsw.Model.JSON;

import it.polimi.ingsw.Model.*;
import it.polimi.ingsw.Model.Enums.GameType;
import it.polimi.ingsw.Model.Enums.SoloActionToken;

import java.util.ArrayList;

public class GameJSON {

    private GameType type;
    private final ArrayList<Player> players;
    private int currentPlayerIndex;
    private ArrayList<LeaderCard> leaderCards;
    private final Market market;
    private final DevelopmentCardTable developmentCardTable;
    private final FaithTrack faithTrack;

    private final SoloActionToken[] tokens;

    public GameJSON(Game game) {
        this.type = game.getGameType();
        this.players = game.getPlayers();
        this.currentPlayerIndex = game.getCurrentPlayerIndex();
        this.market = game.getMarket();
        this.developmentCardTable = game.getDevelopmentCardTable();
        this.faithTrack = game.getFaithTrack();
        this.tokens = game.getTokens();
    }


}
