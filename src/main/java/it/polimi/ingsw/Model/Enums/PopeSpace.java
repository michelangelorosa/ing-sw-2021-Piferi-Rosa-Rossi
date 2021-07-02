package it.polimi.ingsw.Model.Enums;

/**
 * Color enum-type Class contains all possible colors a
 * Development Card (or Development Card Deck) can have.
 */

public enum PopeSpace {
    No, ONE, TWO, THREE;

    /**
     * Creates an array of all enum fields.
     */
    private static final PopeSpace[] popeSpaces = PopeSpace.values();

    /**
     * Uses an array of PopeSpace instances containing each different PopeSpace type
     * to return a particular color based on the integer given when calling the method.
     */
    public static PopeSpace getPopeSpace(int i) {
        return PopeSpace.popeSpaces[i];
    }
}