package it.polimi.ingsw.Model;

import java.util.ArrayList;

public class Game {
    private GameType gameType;
    private ArrayList<Player> players;
    private int currentPlayerIndex;
    private ArrayList<LeaderCard> leaderCards;
    private final Market market;
    private final DevelopmentCardTable developmentCardTable;
    private final FaithTrack faithTrack;

    private final SoloActionToken[] tokens;

    public Game() {
        this.gameType = GameType.SINGLEPLAYER;
        this.players = new ArrayList<>();
        this.currentPlayerIndex = 0;
        this.leaderCards = JSONReader.ReadLeaderCards();
        this.market = new Market();
        this.developmentCardTable = new DevelopmentCardTable();
        this.faithTrack = new FaithTrack();
        this.tokens = new SoloActionToken[6];
    }

    public GameType getGameType() {
        return gameType;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    public ArrayList<LeaderCard> getLeaderCards() {
        return leaderCards;
    }

    public Market getMarket() {
        return market;
    }

    public DevelopmentCardTable getDevelopmentCardTable() {
        return developmentCardTable;
    }

    public FaithTrack getFaithTrack() {
        return faithTrack;
    }

    public SoloActionToken[] getTokens() {
        return tokens;
    }

    public void setLeaderCards(ArrayList<LeaderCard> leaderCards) {
        this.leaderCards = leaderCards;
    }

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

    public String getCurrentPlayerNickname() {
        return players.get(currentPlayerIndex).getNickname();
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    public void nextPlayer() {
        if(currentPlayerIndex == players.size())
            currentPlayerIndex = 0;
        else
            currentPlayerIndex ++;
    }

    public void setWinner() {
        for(Player player : players) {
            if(player.getNickname().equals(this.getCurrentPlayerNickname()))
                player.setStatus(PlayerStatus.WON);
            else
                player.setStatus(PlayerStatus.LOST);
        }
    }

    public void setTie() {
        for(Player player : players)
            player.setStatus(PlayerStatus.WON);
    }

}
