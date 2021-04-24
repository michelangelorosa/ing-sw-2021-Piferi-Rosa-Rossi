package it.polimi.ingsw.View.ReducedModel.Enums;

/**
 * Level enum-type Class contains all possible levels a
 * Development Card (or Development Card Deck) can have.
 */
public enum Level {
    ONE, TWO, THREE;

    /**
     * This particular method uses an array of Level instances containing each different level
     * to return a particular color based on the integer given when calling the method.
     */
    private static final Level[] levels = Level.values();
    public static Level getLevel(int i) { return Level.levels[i]; }
}
