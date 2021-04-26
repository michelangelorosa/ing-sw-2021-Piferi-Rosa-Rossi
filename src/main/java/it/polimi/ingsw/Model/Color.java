package it.polimi.ingsw.Model;

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
}
