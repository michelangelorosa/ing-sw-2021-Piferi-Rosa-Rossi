package it.polimi.ingsw.View.ReducedModel;

import it.polimi.ingsw.Model.GameModel.FaithTrack;
import it.polimi.ingsw.Model.GameModel.LeaderCard;
import it.polimi.ingsw.View.Utility.ANSIfont;
import it.polimi.ingsw.Model.Enums.GameType;
import it.polimi.ingsw.Model.Enums.SoloActionToken;

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
    private RedLeaderCard[] leaderCards;

    private RedMarket market;
    private RedDevelopmentCardTable developmentCardTable;
    private RedFaithTrack faithTrack;

    private final SoloActionToken[] tokens;
    private String myNickname;

    /**
     * Constructor for Game Class.
     */
    public Game() {
        this.gameType = GameType.SINGLEPLAYER;
        this.players = new ArrayList<>();
        this.currentPlayerIndex = 0;
        this.market = new RedMarket();
        this.developmentCardTable = new RedDevelopmentCardTable();
        this.faithTrack = new FaithTrack();
        this.tokens = new SoloActionToken[6];
    }

    public String getMyNickname() {
        return myNickname;
    }

    public void setMyNickname(String myNickname) {
        this.myNickname = myNickname;
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
    public RedLeaderCard[] getLeaderCards() {
        return leaderCards;
    }

    /**
     * Getter for "market" attribute in Game Class.
     */
    public RedMarket getMarket() {
        return market;
    }

    /**
     * Getter for "developmentCardTable" attribute in Game Class.
     */
    public RedDevelopmentCardTable getDevelopmentCardTable() {
        return developmentCardTable;
    }

    /**
     * Getter for "faithTrack" attribute in Game Class.
     */
    public RedFaithTrack getFaithTrack() {
        return faithTrack;
    }

    /**
     * Getter for "tokens" attribute in Game Class.
     */
    public SoloActionToken[] getTokens() {
        return tokens;
    }

    public void setLeaderCards(RedLeaderCard[] leaderCards) {
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

    public void setMarket(RedMarket market) {
        this.market = market;
    }

    public void setFaithTrack(RedFaithTrack faithTrack) {
        this.faithTrack = faithTrack;
    }

    public void setDevelopmentCardTable(RedDevelopmentCardTable developmentCardTable) {
        this.developmentCardTable = developmentCardTable;
    }

    public ArrayList<String> boardToCli(Player player){
        boolean up = false;
        ArrayList<String> leader;
        ArrayList<String> board;
        ArrayList<String> faithTrack = this.faithTrack.toCli(this);

        if(this.myNickname.equals(player.getNickname())) up = true;
        leader = player.leaderPrint(up);

        board = player.toCli();

        board.set(0, board.get(0) + " " + leader.get(0));

        for(int i = 1; i <= board.size()-1; i++){
            if(i == (board.size()-2)) board.set(i, board.get(i) + "  " + leader.get(i));
            else if(i == (board.size()-1)) board.set(i, board.get(i) + " " + leader.get(i));
            else if(i == 1) board.set(i, board.get(i) + " " + leader.get(i) );
            else board.set(i, board.get(i) + "  " + leader.get(i));
        }

        if(!up) board.add(0, ANSIfont.ITALIC + player.getNickname() + ANSIfont.RESET+"'s board ");
        else board.add(0, ANSIfont.ITALIC + "My board"+ANSIfont.RESET);

        board.add(0, "                                                                                                                                                             ");

        board.addAll(0, faithTrack);
        board.add(0, "                                                                                                                                                             ");
        board.add(0, " - Faith Track - ");

        return board;
    }

    public ArrayList<String> myBoardToCli() {
        return this.boardToCli(this.getMyPlayer());
    }

    public Player getMyPlayer() throws IllegalStateException{
        for(Player player : this.players)
            if(player.getNickname().equals(this.myNickname))
                return player;

        throw new IllegalStateException("THERE IS NO PLAYER WITH THIS NAME: " + this.getMyNickname() + " IN GAME!");
    }
}
