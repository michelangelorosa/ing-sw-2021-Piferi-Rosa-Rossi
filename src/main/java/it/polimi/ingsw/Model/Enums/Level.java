package it.polimi.ingsw.Model.Enums;

/**
 * Level enum-type Class contains all possible levels a
 * Development Card (or Development Card Deck) can have.
 */
public enum Level {
    ONE, TWO, THREE;

    /**
     * Creates an array of all enum fields.
     */
    private static final Level[] levels = Level.values();

    /**
     * Uses an array of Level instances containing each different level
     * to return a particular color based on the integer given when calling the method.
     */
    public static Level getLevel(int i) { return Level.levels[i]; }
}
