package it.polimi.ingsw.Model;

import it.polimi.ingsw.Model.Enums.GameType;
import it.polimi.ingsw.Model.Enums.PlayerStatus;
import it.polimi.ingsw.Model.Enums.SoloActionToken;
import it.polimi.ingsw.Model.JSON.JSONReader;


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


    private final Market market;
    private final DevelopmentCardTable developmentCardTable;
    private final FaithTrack faithTrack;

    private final SoloActionToken[] tokens;

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
    public void gameStart(ArrayList<String> clientNames) throws IllegalArgumentException {
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

            for(int i=0;i<numberOfPlayers;i++){
            newPlayer = new Player(clientNames.get(activePlayer),i,i==0);
            players.add(newPlayer);

            if(activePlayer==numberOfPlayers)
                activePlayer=0;
            else activePlayer++;
            if(i==1){
                //TODO: una risorsa a scelta

            }else
                if(i==2)
                {
                    //TODO: una risorsa a scelta

                    newPlayer.stepAhead(1);
                }
                else if(i==3)
                {
                    //TODO: due risorse a scelta

                    newPlayer.stepAhead(1);
                }
            }
        }
        else {//Singleplayer Init
            newPlayer = new Player("Lorenzo il Magnifico",-1,false);
            players.add(newPlayer);
            newPlayer = new Player(clientNames.get(0),0,false);
            players.add(newPlayer);
        }

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

    /**
     * Method used to check if any player bought 7 Development Cards or finished the Faith Track.
     * @return True if the player finished.
     */
    public boolean checkIfAnyPlayerFinished() {
        for(Player player : this.players)
            if(player.hasFinished())
                return true;

        return false;
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
     * Method used when a player wants to join the game.
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
/*
    public void join(String nickname) {
        if(players.size() == 4) {
            System.out.println("Maximum number of players reached!");
            return;
        }

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

 */

}
