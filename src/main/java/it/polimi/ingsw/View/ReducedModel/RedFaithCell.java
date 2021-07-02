package it.polimi.ingsw.View.ReducedModel;

import it.polimi.ingsw.View.Utility.ANSIColors;
import it.polimi.ingsw.Model.Enums.GameType;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * RedFaithCell Class contains data and methods used to create a faith cell inside the user's view.
 * <p><b>Attributes:</b></p>
 * <ul>
 *     <li>int "idCell": id of the faith cell</li>
 *     <li>int "victoryPoints": victory points of the faith cell</li>
 * </ul>
 */
public class RedFaithCell implements Serializable{
    protected final int idCell;
    protected int victoryPoints;

    /**
     * Constructor for RedFaithCell Class.
     */
    protected RedFaithCell(int idCell) {
        this.idCell = idCell;
    }

    /**Getter for idCell*/
    public int getIdCell() {
        return idCell;
    }

    /**Getter for victoryPoints*/
    public int getVictoryPoints() {
        return victoryPoints;
    }

    /**
     * Overridden toString method.
     */
    public String toString() {
        return idCell+" "+victoryPoints;
    }

    /**
     * Builds a visual representation of the faith track.
     * @param game game to extract information from.
     * @return An ArrayList of Strings containing the representation.
     */
    public ArrayList<String> toCli(Game game) {
        ArrayList<String> s = new ArrayList<>();
        s.add(" ");
        s.add(" ");
        s.add(" ");
        s.add(" ");

        ArrayList<Player> players = game.getPlayers();


        String character = " ";
        if(game.getGameType() == GameType.MULTIPLAYER)
            for(int i = 0; i < players.size(); i++) {
                if(i == 0 && players.get(0).getFaithTrackPosition() == this.idCell)
                    character = ANSIColors.RESET + "þ" + ANSIColors.FRONT_ORANGE;
                else if (i == 1 && players.get(1).getFaithTrackPosition() == this.idCell)
                    character = ANSIColors.RESET + "¶" + ANSIColors.FRONT_ORANGE;
                else if (i == 2 && players.get(2).getFaithTrackPosition() == this.idCell)
                    character = ANSIColors.RESET + "÷" + ANSIColors.FRONT_ORANGE;
                else if (i == 3 && players.get(3).getFaithTrackPosition() == this.idCell)
                    character = ANSIColors.RESET + "┼" + ANSIColors.FRONT_ORANGE;
                s.set(i, character);
                character = " ";
            }
        else {
            if(players.get(0).getFaithTrackPosition() == this.idCell)
                s.set(0, ANSIColors.RESET + "þ" + ANSIColors.FRONT_ORANGE);
            if(players.get(1).getFaithTrackPosition() == this.idCell)
                s.set(3, ANSIColors.RESET + ANSIColors.BACK_BLACK + "┼" + ANSIColors.RESET + ANSIColors.FRONT_ORANGE);
        }

        ArrayList<String> cell = new ArrayList<>();
        cell.add(ANSIColors.FRONT_ORANGE + "╔════╗");
        cell.add("║"+s.get(0)+"  "+s.get(1)+"║");
        cell.add("║ "+this.vpToCli()+" ║");
        cell.add("║"+s.get(2)+"  "+s.get(3)+"║");
        cell.add("╚════╝");

        return cell;
    }

    /**
     * Converts victory points into a viewable string.
     */
    private String vpToCli() {
        if(this.victoryPoints > 9) return ((Integer)this.victoryPoints).toString(); else return " " + ((Integer)this.victoryPoints).toString();
    }

}