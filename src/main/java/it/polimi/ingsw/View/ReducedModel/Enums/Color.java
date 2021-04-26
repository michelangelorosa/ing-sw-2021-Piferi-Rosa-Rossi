package it.polimi.ingsw.View.ReducedModel.Enums;

import it.polimi.ingsw.View.ANSIColors;

/**
 * Color enum-type Class contains all possible colors a
 * Development Card (or Development Card Deck) can have.
 */
public enum Color {
    BLUE, PURPLE, YELLOW, GREEN;

    /**
     * This particular method uses an array of Color instances containing each different color
     * to return a particular color based on the integer given when calling the method.
     */
    private static final Color[] colors = Color.values();
    public static Color getColor(int i) { return Color.colors[i]; }

    public String colorToString() {
        String string;
        switch(this) {
            case BLUE: string = ANSIColors.BACK_BLUE;
            break;
            case PURPLE: string = ANSIColors.BACK_PURPLE;
            break;
            case YELLOW:   string = ANSIColors.BACK_YELLOW;
            break;
            case GREEN: string = ANSIColors.BACK_GREEN;
            break;
            default: string = null;
            break;
        }
        return string;
    }

    public String colorToChar() {
        String string;
        switch(this) {
            case BLUE: string = "B";
            break;
            case PURPLE: string = "P";
            break;
            case YELLOW: string = "Y";
            break;
            case GREEN: string = "G";
            break;
            default: string = null;
            break;
        }
        return string;
    }
}
