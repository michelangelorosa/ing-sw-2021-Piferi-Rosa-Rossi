package it.polimi.ingsw.Model.Enums;

import it.polimi.ingsw.View.Utility.ANSIColors;

import java.util.ArrayList;

/**
 * Different Action Tokens for the SinglePlayer game
 */
public enum SoloActionToken {
    BLACKCROSSPLUS2, BLACKCROSSSHUFFLE, DELETE2BLUE, DELETE2PURPLE, DELETE2YELLOW, DELETE2GREEN;

    public ArrayList<String> toCli() {
        ArrayList<String> token = new ArrayList<>();
        String bc = ANSIColors.BACK_BLACK;
        String c = "";
        String r = ANSIColors.RESET;
        String card = "";

        switch(this) {
            case BLACKCROSSPLUS2:
                token.add("╔═══════╗");
                token.add("║    "+bc+" │ "+r+"║");
                token.add("║ +2 "+bc+"─┼─"+r+"║");
                token.add("║    "+bc+" │ "+r+"║");
                token.add("╚═══════╝");

                return token;

            case BLACKCROSSSHUFFLE:
                token.add("╔═══════╗");
                token.add("║ +1 "+bc+" │ "+r+"║");
                token.add("║┌←─┐"+bc+"─┼─"+r+"║");
                token.add("║└─→┘"+bc+" │ "+r+"║");
                token.add("╚═══════╝");

                return token;

            case DELETE2BLUE:
                c = ANSIColors.FRONT_BLUE;
                bc = ANSIColors.BACK_BLUE;
                card = "B";
                break;
            case DELETE2PURPLE:
                c = ANSIColors.FRONT_PURPLE;
                bc = ANSIColors.BACK_PURPLE;
                card = "P";
                break;
            case DELETE2YELLOW:
                c = ANSIColors.FRONT_YELLOW;
                bc = ANSIColors.BACK_DARK_YELLOW;
                card = "Y";
                break;
            case DELETE2GREEN:
                c = ANSIColors.FRONT_GREEN;
                bc = ANSIColors.BACK_GREEN;
                card = "G";
                break;
        }

        token.add("╔═══════╗");
        token.add("║    "+bc+"╔═╗"+r+"║");
        token.add("║ -2 "+bc+"║"+card+"║"+r+"║");
        token.add("║    "+bc+"╚═╝"+r+"║");
        token.add("╚═══════╝");

        return token;
    }

}