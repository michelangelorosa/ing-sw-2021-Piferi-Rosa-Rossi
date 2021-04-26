package it.polimi.ingsw.View.ReducedModel;

import it.polimi.ingsw.View.ReducedModel.Enums.*;

import java.util.ArrayList;

/**
 * Game Class contains all attributes needed when creating an instance of a new game.
 */
public class Game {
    /** Type of the current game -> Single or Multi Player */
    private GameType gameType;
    /** ArrayList containing all players who joined the game */
    private ArrayList<Player> players;
    /** Index indicating the current player */
    private int currentPlayerIndex;
    /** ArrayList containing all Leader Cards of the game */
    private ArrayList<LeaderCard> leaderCards;

    private Market market;
    private final DevelopmentCardTable developmentCardTable;
    private FaithTrack faithTrack;

    private final SoloActionToken[] tokens;

    /**
     * Constructor for Game Class.
     */
    public Game() {
        this.gameType = GameType.SINGLEPLAYER;
        this.players = new ArrayList<>();
        this.currentPlayerIndex = 0;
        this.leaderCards = new ArrayList<>();
        this.market = new Market();
        this.developmentCardTable = new DevelopmentCardTable();
        this.faithTrack = new FaithTrack();
        this.tokens = new SoloActionToken[6];
    }

    /**
     * Getter for "gameType" attribute in Game Class.
     */
    public GameType getGameType() {
        return gameType;
    }

    /**
     * Getter for "getPlayers" attribute in Game Class.
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * Getter for "currentPlayerIndex" attribute in Game Class.
     */
    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    /**
     * Getter for "leaderCards" attribute in Game Class.
     */
    public ArrayList<LeaderCard> getLeaderCards() {
        return leaderCards;
    }

    /**
     * Getter for "market" attribute in Game Class.
     */
    public Market getMarket() {
        return market;
    }

    /**
     * Getter for "developmentCardTable" attribute in Game Class.
     */
    public DevelopmentCardTable getDevelopmentCardTable() {
        return developmentCardTable;
    }

    /**
     * Getter for "faithTrack" attribute in Game Class.
     */
    public FaithTrack getFaithTrack() {
        return faithTrack;
    }

    /**
     * Getter for "tokens" attribute in Game Class.
     */
    public SoloActionToken[] getTokens() {
        return tokens;
    }

    public void setLeaderCards(ArrayList<LeaderCard> leaderCards) {
        this.leaderCards = leaderCards;
    }

    /**
     * Setter for "gameType" attribute in Game Class.
     */
    public void setGameType(GameType gameType) {
        this.gameType = gameType;
    }

    /**
     * Getter for the current player's nickname in Game Class.
     */
    public String getCurrentPlayerNickname() {
        return players.get(currentPlayerIndex).getNickname();
    }

    /**
     * Getter for the current player (based on index) in Game Class.
     */
    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public void setCurrentPlayerIndex(int currentPlayerIndex) {
        this.currentPlayerIndex = currentPlayerIndex;
    }

    public void setMarket(Market market) {
        this.market = market;
    }

    public void setFaithTrack(FaithTrack faithTrack) {
        this.faithTrack = faithTrack;
    }
}
