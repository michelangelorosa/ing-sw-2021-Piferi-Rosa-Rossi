package it.polimi.ingsw.View.ReducedModel;

import it.polimi.ingsw.Model.GameModel.FaithTrack;
import it.polimi.ingsw.View.Utility.ANSIfont;
import it.polimi.ingsw.Model.Enums.GameType;

import java.util.ArrayList;

/**
 * Game Class contains all attributes needed when creating an instance of a new game inside the user's View.
 * <p><b>Attributes:</b></p>
 * <ul>
 *     <li>GameType "gameType": Type of the current game -> Single or Multi Player</li>
 *     <li>ArrayList&lt;Player&gt; "players": ArrayList containing all players who joined the game</li>
 *     <li>int "currentPlayerIndex": indicates the current players' index</li>
 *     <li>RedLeaderCard[] "leaderCards": contains all leader cards of the game</li>
 *     <li>RedMarket "market": market of the game</li>
 *     <li>RedDevelopmentCardTable "developmentCardTable": Development Card table of the game</li>
 *     <li>RedFaithTrack "faithTrack": faith track of the game</li>
 *     <li>String "myNickname": name of the user playing the game</li>
 * </ul>
 */
public class Game {
    private GameType gameType;
    private ArrayList<Player> players;
    private int currentPlayerIndex;
    private RedLeaderCard[] leaderCards;

    private RedMarket market;
    private RedDevelopmentCardTable developmentCardTable;
    private RedFaithTrack faithTrack;

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
    }

    /**
     * Getter for "myNickname" attribute.
     */
    public String getMyNickname() {
        return myNickname;
    }

    /**
     * Setter for "myNickname" attribute.
     */
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
     * Getter for "leaderCards" attribute.
     */
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

    /**
     * Setter for "players" attribute.
     */
    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    /**
     * Setter for "market" attribute.
     */
    public void setMarket(RedMarket market) {
        this.market = market;
    }

    /**
     * Setter for "myNickname" attribute.
     */
    public void setFaithTrack(RedFaithTrack faithTrack) {
        this.faithTrack = faithTrack;
    }

    /**
     * Setter for "developmentCardTable" attribute.
     */
    public void setDevelopmentCardTable(RedDevelopmentCardTable developmentCardTable) {
        this.developmentCardTable = developmentCardTable;
    }

    /**
     * Converts a given player's board to its Cli visualization.
     * @param player Player whose board is needed.
     * @return An ArrayList of Strings containing visualization of the board.
     */
    public ArrayList<String> boardToCli(Player player){
        if(player.getNickname().equals("Lorenzo il Magnifico")) {
            return lorenzoToCli();
        }

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

    /**
     * Returns the player's board visualization corresponding to the player who's playing the game on this instance of the client.
     * @return An ArrayList of Strings containing visualization of the board.
     */
    public ArrayList<String> myBoardToCli() {
        return this.boardToCli(this.getMyPlayer());
    }

    public ArrayList<String> lorenzoToCli() {
        ArrayList<String> lorenzo = new ArrayList<>();
        lorenzo.add("HELLO! IM LORENZO'S BOARD!");
        return lorenzo;
    }

    /**
     * Returns the player whose name is equal to "myNickname".
     * @return the player whose name is equal to "myNickname".
     * @throws IllegalStateException if there is no player whose name is equal to "myNickname".
     */
    public Player getMyPlayer() throws IllegalStateException{
        for(Player player : this.players)
            if(player.getNickname().equals(this.myNickname))
                return player;
        throw new IllegalStateException("THERE IS NO PLAYER WITH THIS NAME: " + this.getMyNickname() + " IN GAME!");

    }
}
