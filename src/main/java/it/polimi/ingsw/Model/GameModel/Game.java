package it.polimi.ingsw.Model.GameModel;

import it.polimi.ingsw.Model.Enums.GameType;
import it.polimi.ingsw.Model.Enums.PlayerStatus;
import it.polimi.ingsw.Model.Enums.SoloActionToken;
import it.polimi.ingsw.Model.JSON.JSONReader;
import it.polimi.ingsw.Model.Server.Server;


import java.util.ArrayList;

/**
 * Game Class contains all attributes needed when creating an instance of a new game.
 */
public class Game {
    /** Type of the current game -> Single or Multi Player */
    private GameType gameType;
    /** ArrayList containing all players who joined the game */
    private final ArrayList<Player> players;
    /** Index indicating the current player */
    private int currentPlayerIndex;
    /** ArrayList containing all Leader Cards of the game */
    private ArrayList<LeaderCard> leaderCards;

    private boolean lastTurn = false;

    private final Market market;
    private final DevelopmentCardTable developmentCardTable;
    private final FaithTrack faithTrack;

    private final SoloActionToken[] tokens;

    private Server server;

    /**
     * Constructor for Game Class.
     */
    public Game() {
        this.gameType = GameType.SINGLEPLAYER;
        this.players = new ArrayList<>();
        this.currentPlayerIndex = 0;
        this.leaderCards = new ArrayList<>();
        this.leaderCards = JSONReader.ReadLeaderCards();
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
     * Method used when a player wants to join the game. Handles all the initialization of the game.
     * @param clientNames ArrayList of nicknames that have successfully established a connection with the server.
     * @throws IllegalArgumentException if the max number of players has been reached.
     */
    public void gameStart(ArrayList<String> clientNames, Server server) throws IllegalArgumentException {

        setServer(server);
        int numberOfPlayers = clientNames.size();
        if(numberOfPlayers==1)
            setGameType(GameType.SINGLEPLAYER);
        else
            setGameType(GameType.MULTIPLAYER);
        if(numberOfPlayers > 4)
            throw new IllegalArgumentException("Maximum number of players reached!");

        developmentCardTable.shuffleTable();

        Player newPlayer;
        if(gameType==GameType.MULTIPLAYER){
            int activePlayer= (int)(Math.random()*numberOfPlayers);

            for(int position=0;position<numberOfPlayers;position++){
            newPlayer = new Player(clientNames.get(activePlayer),position,position==0);
            players.add(newPlayer);
            if(activePlayer==numberOfPlayers)
                activePlayer=0;
            else activePlayer++;
            sendToClient(newPlayer.getNickname(),server,position);
                if(position==2)
                    newPlayer.stepAhead(1);
                else if(position==3)
                    newPlayer.stepAhead(1);

            }
        }
        else {//Singleplayer Init
            newPlayer = new Player("Lorenzo il Magnifico",-1,false);
            players.add(newPlayer);
            newPlayer = new Player(clientNames.get(0),0,false);
            players.add(newPlayer);
            sendToClient(clientNames.get(0),server,4);
        }

    }

    /**
     * Sends to the client the GameType, the Inkwell status, 4 Leader Cards, the number of resources to pick from
     * Used in the beginning of the game
     * @param nickname  Nickname of the player to send the messages to
     * @param server    Server is used for establishing a connection to the client
     * @param position  Position is the position of the player in the game, used for establishing the inkwell and the extra resources for the startup
     */
    private void sendToClient(String nickname,Server server, int position) {
        System.out.print("Sending to "+nickname);
        System.out.println(" 4");

        server.sendTo(nickname,4);

        server.sendTo(nickname,this.gameType);
        if(position==0)//Has inkwell?
            server.sendTo(nickname,true);
        else server.sendTo(nickname,false);
        server.sendTo(nickname,LeaderCardShuffle.getLeaderShuffled());
        if(position==1||position==2)
            server.sendTo(nickname,1);
        else if(position==3)
            server.sendTo(nickname,2);
        else
            server.sendTo(nickname,0);
        server.sendTo(nickname,nickname);
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
     * Method used to get the next player in turn
     */
    public void nextPlayer() {
        if(currentPlayerIndex == players.size() - 1)
            currentPlayerIndex = 0;
        else
            currentPlayerIndex ++;
    }

    /**
     * Currently unused
     * @return the previous player
     */
    public Player getPreviousPlayer() {
        if(currentPlayerIndex == 0)
            return this.players.get(this.players.size() - 1);
        else
            return this.players.get(this.currentPlayerIndex - 1);
    }

    public boolean currentPlayerIsLast() {
        return this.currentPlayerIndex == this.players.size() - 1;
    }

    /**
     * Method used to set a player's status to WIN.
     * @param player Player to set.
     */
    public void setWinner(Player player) {
        player.setStatus(PlayerStatus.WON);
    }

    /**
     * Method used to set a player's status to LOST.
     * @param player Player to set.
     */
    public void setLooser(Player player) {
        player.setStatus(PlayerStatus.LOST);
    }

    public boolean isLastTurn() {
        return lastTurn;
    }

    /**
     * Method used to check if any player bought 7 Development Cards or finished the Faith Track.
     * @return True if the player finished.
     */
    public boolean checkIfAnyPlayerFinished() {
        for(Player player : this.players)
            if(player.hasFinished()) {
                this.lastTurn = true;
                return true;
            }

        return false;
    }

    public void setFinalVictoryPoints() {
        this.getFaithTrack().addFinalPoints(this.players);

        for(Player player : this.players)
            player.countFinalVictoryPoints();

        this.finalCountVictory();
    }

    /**
     * Method used to count final Victory Points and set the winners.
     */
    public void finalCountVictory() {
        int victoryPoints = 0;

        if(this.gameType == GameType.MULTIPLAYER)
            for(int i = 0; i < players.size(); i++)
                if(players.get(i).getVictoryPoints() == victoryPoints)
                    setWinner(players.get(i));
                else if(players.get(i).getVictoryPoints() > victoryPoints) {
                    victoryPoints = players.get(i).getVictoryPoints();

                    setWinner(players.get(i));

                    for(int j = 0; j < i; j++)
                        setLooser(players.get(j));
                }
                else if(players.get(i).getVictoryPoints() < victoryPoints)
                    setLooser(players.get(i));
    }

    /**
     * Method used in <b>testing only</b> when a player wants to join the game.
     * @param nickname Nickname of the player wanting to join.
     * @throws IllegalArgumentException if the max number of players has already been reached.
     */
    public void join(String nickname) throws IllegalArgumentException {
        if(players.size() == 4)
            throw new IllegalArgumentException("Maximum number of players reached!");

        Player newPlayer;

        if(this.players.size() == 0)
            newPlayer = new Player(nickname, 0, true);
        else {
            for(Player player : players)
                if(player.getNickname().equals(nickname)) {
                    System.out.println("Nickname already chosen, choose another nickname!");
                    return;
                }
            this.gameType = GameType.MULTIPLAYER;
            newPlayer = new Player(nickname, players.size(), false);
        }

        players.add(newPlayer);
    }

    private void setServer(Server server){
        this.server = server;
    }

    public Server getServer(){
        return this.server;
    }

}
