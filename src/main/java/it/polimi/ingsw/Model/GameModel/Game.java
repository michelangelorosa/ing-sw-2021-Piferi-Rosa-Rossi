package it.polimi.ingsw.Model.GameModel;

import it.polimi.ingsw.Model.Enums.GameType;
import it.polimi.ingsw.Model.Enums.PlayerStatus;
import it.polimi.ingsw.Model.Enums.SoloActionToken;
import it.polimi.ingsw.Model.GameModel.SinglePlayer.SinglePlayer;
import it.polimi.ingsw.Model.Exceptions.ModelException;
import it.polimi.ingsw.Model.JSON.ConvertToJSON;
import it.polimi.ingsw.Model.JSON.JSONReader;
import it.polimi.ingsw.View.ReducedModel.RedLeaderCard;


import java.util.ArrayList;
import java.util.Random;

/**
 * Game Class contains all attributes and methods needed when creating an instance of a new Game.
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

    private Market market;
    private DevelopmentCardTable developmentCardTable;
    private final FaithTrack faithTrack;

    private final SinglePlayer singlePlayer = new SinglePlayer();


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

    public void setLeaderCards(ArrayList<LeaderCard> leaderCards) {
        this.leaderCards = leaderCards;
    }

    /**
     * Setter for "gameType" attribute in Game Class.
     */
    public void setGameType(GameType gameType) {
        this.gameType = gameType;
    }

    public SinglePlayer getSinglePlayer() {
        return singlePlayer;
    }

    /**
     * Returns the player which corresponds to the given String.
     * @param nickname String containing the name of the player to search.
     * @return The Player corresponding to the String.
     * @throws IllegalArgumentException if no player is found with the given name.
     */
    public Player getPlayerByNickname(String nickname) throws IllegalArgumentException{
        for(Player player : this.players)
            if(player.getNickname().equals(nickname))
                return player;

        throw new IllegalArgumentException("[Model.Game]: cannot find player named " + nickname);
    }

    /**
     * Creates an ArrayList of all the Game's players names (in order from position 0 to size()).
     * @return An ArrayList containing the ordered names.
     */
    public ArrayList<String> getAllPlayersNameInOrder() {
        ArrayList<String> names = new ArrayList<>();

        for(Player player : this.players) {
            names.add(player.getNickname());
        }

        return names;
    }

    /**
     * Takes the name of all the Players of the game and sets a new Game accordingly.
     * @param playerNames Names of all the players of the game
     * @param numberOfPlayers Number of the Players who are going to play.
     */
    public void gameStartPlayers(ArrayList<String> playerNames, int numberOfPlayers) throws ModelException {
        if(numberOfPlayers > 1 && numberOfPlayers < 5)
            this.gameType = GameType.MULTIPLAYER;
        else if(numberOfPlayers == 1)
            this.gameType = GameType.SINGLEPLAYER;
        else throw new ModelException("Too many players");

        if(this.gameType == GameType.MULTIPLAYER)
            this.multiplayerGameStartPlayers(playerNames);
        else
            this.singleplayerGameStartPlayers(playerNames);

        this.developmentCardTable.shuffleTable();

    }

    /**
     * Sets the Game Players if the Game is of Singleplayer type.
     * @param playerNames Contains the name of the Player who's going to play the game.
     */
    public void singleplayerGameStartPlayers(ArrayList<String> playerNames) {
        this.players.add(new Player(playerNames.get(0), 0, true));
        this.players.add(new Player("Lorenzo il Magnifico", 1, false));
    }

    /**
     * Sets the Game Players if the Game is of Multiplayer type.
     * @param playerNames Contains the name of the Players who are going to play the game.
     */
    public void multiplayerGameStartPlayers(ArrayList<String> playerNames) {
        int randomPosition;
        int size = playerNames.size();
        Random random = new Random();

        for(int j = 0; j < size; j++) {
            randomPosition = random.nextInt(playerNames.size());
            this.players.add(new Player(playerNames.remove(randomPosition), j, j == 0));
        }
    }

    /**
     * Creates an array of RedLeaderCards to be sent to the Player for the Initial Leader Card Choice.
     * @return An array containing 4 RedLeaderCard objects.
     */
    public RedLeaderCard[] gameStartLeaderCards() {
        return LeaderCardShuffle.getLeaderShuffled();
    }

    /**
     * Sets to the corresponding player the number of resources and faith points he gets at the beginning of the Game.
     * @param name String containing the name of the Player receiving the goods.
     * @return The number of resources which the Player has to choose.
     * @throws IllegalArgumentException If the Player's position is out of bounds for the Game's rules.
     */
    public int gameStartResources(String name) throws IllegalArgumentException{
        Player player = this.getPlayerByNickname(name);

        switch(player.getTurnPosition()) {
            case 0: return 0;

            case 1: return 1;

            case 2: player.stepAhead(1);
                return 1;

            case 3: player.stepAhead(1);
                return 2;

            default: throw new IllegalArgumentException("[Model.Game.gameStartResources]: player's position was " + player.getTurnPosition());
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
     */
    public void checkIfAnyPlayerFinished() {
        for(Player player : this.players)
            if(player.hasFinished()) {
                this.lastTurn = true;
                return;
            }

    }

    /**
     * Sets the Final Victory points for all Players at the end of the Game.
     */
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

    /**
     * Converts all the Players inside the Game to a compatible version which can be used by the Client.
     * @return An ArrayList containing the View's Players.
     */
    public ArrayList<it.polimi.ingsw.View.ReducedModel.Player> getViewPlayers() {
        ArrayList<it.polimi.ingsw.View.ReducedModel.Player> players = new ArrayList<>();

        for(Player player : this.players)
            players.add(player.toView());

        return players;
    }

    public void changeCurrentPlayerReconnection(String playerName) {
        Player player = this.getPlayerByNickname(playerName);
        this.currentPlayerIndex = player.getTurnPosition();
    }

    public boolean isPlayerInGameNickname (String name) {
        ArrayList<String> playersNames = new ArrayList<>();

        if(this.players.isEmpty())
            return false;

        for(Player p : this.players)
            playersNames.add(p.getNickname());

        return playersNames.contains(name);
    }

    public void removePlayerByNickname(String name) {
        Player player = null;

        if(isPlayerInGameNickname(name))
            player = getPlayerByNickname(name);

        if(player != null) {
            this.players.remove(player);
            if(currentPlayerIndex >= players.size())
                currentPlayerIndex = 0;
        }
    }

    public void persistence(){


        DevelopmentCardDeck[][] deck = JSONReader.developmentCardDecksDisconnection();
        DevelopmentCardTable table = new DevelopmentCardTable(deck);
        Integer[] card = JSONReader.convertCardsInDeck();

        table.getDeck(0,0).setCardsInDeck(card[0]);
        table.getDeck(0,1).setCardsInDeck(card[1]);
        table.getDeck(0,2).setCardsInDeck(card[2]);
        table.getDeck(0,3).setCardsInDeck(card[3]);
        table.getDeck(1,0).setCardsInDeck(card[4]);
        table.getDeck(1,1).setCardsInDeck(card[5]);
        table.getDeck(1,2).setCardsInDeck(card[6]);
        table.getDeck(1,3).setCardsInDeck(card[7]);
        table.getDeck(2,0).setCardsInDeck(card[8]);
        table.getDeck(2,1).setCardsInDeck(card[9]);
        table.getDeck(2,2).setCardsInDeck(card[10]);
        table.getDeck(2,3).setCardsInDeck(card[11]);

        this.developmentCardTable = table;

        ArrayList<Player> newPlayers = JSONReader.playersDisconnections(table);
        players.addAll(newPlayers);


        this.market = JSONReader.convertMarketPersistence();
        ArrayList<LeaderCard> leader = JSONReader.convertLeaderCardPersistence();
        if(this.players.size() > 0){
            this.getPlayers().get(0).getBoard().getLeaderCards()[0] = leader.get(0);
            this.getPlayers().get(0).getBoard().getLeaderCards()[1] = leader.get(1);
        }
        if(this.players.size() > 1 && !this.players.get(1).getNickname().equals("Lorenzo il Magnifico")){
            this.gameType = GameType.MULTIPLAYER;
            this.getPlayers().get(1).getBoard().getLeaderCards()[0] = leader.get(2);
            this.getPlayers().get(1).getBoard().getLeaderCards()[1] = leader.get(3);
        }
        else if(this.players.get(1).getNickname().equals("Lorenzo il Magnifico")) {
            this.gameType = GameType.SINGLEPLAYER;
        }
        if(this.players.size() > 2){
            this.getPlayers().get(2).getBoard().getLeaderCards()[0] = leader.get(4);
            this.getPlayers().get(2).getBoard().getLeaderCards()[1] = leader.get(5);
        }
        if(this.players.size() > 3){
            this.getPlayers().get(3).getBoard().getLeaderCards()[0] = leader.get(6);
            this.getPlayers().get(3).getBoard().getLeaderCards()[1] = leader.get(7);
        }

    }

}
