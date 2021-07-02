package it.polimi.ingsw.Model.Enums;

import it.polimi.ingsw.View.Utility.ANSIColors;

/**
 * Color enum-type Class contains all possible colors a
 * Development Card (or Development Card Deck) can have.
 */
public enum Color {
    BLUE, PURPLE, YELLOW, GREEN;

    /**
     * Creates an array of all enum fields.
     */
    private static final Color[] colors = Color.values();

    /**
     * Uses an array of Color instances containing each different color
     * to return a particular color based on the integer given when calling the method.
     */
    public static Color getColor(int i) { return Color.colors[i]; }

    /**
     * Returns ANSI color corresponding to given color's background representation.
     * @return a string containing ANSI code.
     */
    public String backColorToString() {
        String string;
        switch(this) {
            case BLUE: string = ANSIColors.BACK_BLUE;
                break;
            case PURPLE: string = ANSIColors.BACK_PURPLE;
                break;
            case YELLOW:   string = ANSIColors.BACK_DARK_YELLOW;
                break;
            case GREEN: string = ANSIColors.BACK_GREEN;
                break;
            default: string = null;
                break;
        }
        return string;
    }

    /**
     * Returns ANSI color corresponding to given color's foreground representation.
     * @return a string containing ANSI code.
     */
    public String frontColorToString() {
        String string;
        switch(this) {
            case BLUE: string = ANSIColors.FRONT_BLUE;
                break;
            case PURPLE: string = ANSIColors.FRONT_PURPLE;
                break;
            case YELLOW:   string = ANSIColors.FRONT_YELLOW;
                break;
            case GREEN: string = ANSIColors.FRONT_GREEN;
                break;
            default: string = null;
                break;
        }
        return string;
    }

    /**
     * Returns the letter corresponding to given color.
     * @return a string containing the letter.
     */
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
