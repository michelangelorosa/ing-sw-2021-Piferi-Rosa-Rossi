package it.polimi.ingsw.Model.Enums;

import it.polimi.ingsw.Model.GameModel.Player;
import it.polimi.ingsw.View.Utility.ANSIColors;

import java.util.ArrayList;

/**
 * Marble colours, used in Market and LeaderCards
 */
public enum Marble {
    WHITE,BLUE,GREY,YELLOW,PURPLE,RED;

    /**
     * Returns the resource corresponding to the marble.
     * @param player used to advance in the faith track if the marble is red.
     * @return the resource corresponding to the marble.
     */
    public ResourceType marbleToResource(Player player) {
        switch(this) {
            case WHITE: player.getBoard().getResourceManager().addWhiteMarble();
            break;
            case BLUE: return ResourceType.SHIELDS;

            case GREY: return ResourceType.STONES;

            case YELLOW: return ResourceType.COINS;

            case PURPLE: return ResourceType.SERVANTS;

            case RED: player.stepAhead(1);
            break;
        }
        return null;
    }

    /**
     * Returns an ArrayList of String containing the visual representation of the given marble.
     * @return an ArrayList of Strings.
     */
    public ArrayList<String> toCli() {
        ArrayList<String> marble = new ArrayList<>();

        marble.add("    ░░▓▓▓▓░░    ");
        marble.add("  ▓▓██▓▓████▓▓  ");
        marble.add("▒▒▓▓  ▒▒▓▓████▓▓");
        marble.add("██▓▓░░░░▓▓▓▓████");
        marble.add("██▓▓▓▓▓▓▓▓▓▓████");
        marble.add("████▓▓▓▓████████");
        marble.add("░░████████████▓▓");
        marble.add("  ▒▒████████▒▒  ");

        String c = "";

        switch(this) {
            case WHITE: c = "";
                break;
            case BLUE: c = ANSIColors.FRONT_BLUE;
                break;
            case GREY: c = ANSIColors.FRONT_DARK_GREY;
                break;
            case YELLOW: c = ANSIColors.FRONT_YELLOW;
                break;
            case PURPLE: c = ANSIColors.FRONT_PURPLE;
                break;
            case RED: c = ANSIColors.FRONT_RED;
                break;
        }

        for(int i = 0; i < marble.size(); i++)
            marble.set(i, c + marble.get(i) + ANSIColors.RESET);

        return marble;
    }
}
