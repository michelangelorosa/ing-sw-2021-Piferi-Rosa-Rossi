package it.polimi.ingsw.Model;

/**
 * Color enum-type Class contains all possible colors a
 * Development Card (or Development Card Deck) can have.
 */

public enum PopeSpace {
    No, ONE, TWO, THREE;

    /**
     * This particular method uses an array of Color instances containing each different color
     * to return a particular color based on the integer given when calling the method.
     */

    private static final PopeSpace[] popeSpaces = PopeSpace.values();
    public static PopeSpace getPopeSpece(int i) { return PopeSpace.popeSpaces[i]; }
}