package it.polimi.ingsw.Model.MessagesToClient;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.Model.Enums.GameType;
import it.polimi.ingsw.View.ReducedModel.Player;
import it.polimi.ingsw.View.ReducedModel.RedDevelopmentCardTable;
import it.polimi.ingsw.View.ReducedModel.RedFaithTrack;
import it.polimi.ingsw.View.ReducedModel.RedMarket;
import it.polimi.ingsw.View.User.UserInteraction;

import java.util.ArrayList;

/**
 * GameSetMessage Class defines attributes and methods needed to correctly create a copy of the Model's Game
 * inside the Client's View.
 * <p><b>Attributes:</b></p>
 * <ul>
 *     <li>ArrayList&lt;Player&gt; "players": Contains all View-readable Players of the Model's Game</li>
 *     <li>RedDevelopmentCardTable "table": The Game's DevelopmentCardTable</li>
 *     <li>RedMarket "market": The Game's Market</li>
 *     <li>RedFaithTrack "faithTrack": The Game's FaithTrack</li>
 * </ul>
 * @author redrick99
 */
public class GameSetMessage extends MessageToClient{
    private ArrayList<Player> players;
    private RedDevelopmentCardTable table;
    private RedMarket market;
    private RedFaithTrack faithTrack;

    /**
     * Constructor for GameSetMessage Class.
     */
    public GameSetMessage(String playerName) {
        super(playerName);
        this.error = "SUCCESS";
        this.actionDone = ActionType.GAME_SET;
    }

    /**
     * Setter for "players" attribute.
     */
    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    /**
     * Setter for "table" attribute.
     */
    public void setTable(RedDevelopmentCardTable table) {
        this.table = table;
    }

    /**
     * Setter for "market" attribute.
     */
    public void setMarket(RedMarket market) {
        this.market = market;
    }

    /**
     * Setter for "faithTrack" attribute.
     */
    public void setFaithTrack(RedFaithTrack faithTrack) {
        this.faithTrack = faithTrack;
    }

    /**
     * Checks if the message contains an error and updates the Client's view.
     * @param userInteraction Class containing the Reduced Game and the User Interface.
     */
    @Override
    public void updateView(UserInteraction userInteraction) {
        userInteraction.getGame().setPlayers(this.players);
        userInteraction.getGame().setDevelopmentCardTable(this.table);
        userInteraction.getGame().setMarket(this.market);
        userInteraction.getGame().setFaithTrack(this.faithTrack);
        if(this.players.get(1).getNickname().equals("Lorenzo il Magnifico"))
            userInteraction.getGame().setGameType(GameType.SINGLEPLAYER);
        else
            userInteraction.getGame().setGameType(GameType.MULTIPLAYER);
        userInteraction.setMessage(this);

        this.displayAction(userInteraction);
    }
}
