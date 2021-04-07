package it.polimi.ingsw.Model;

/**
 * Marble colours, used in Market and LeaderCards
 */
public enum Marble {
    WHITE,BLUE,GREY,YELLOW,PURPLE,RED;
    /**
     * This particular method uses an array of Color instances containing each different color
     * to return a particular color based on the integer given when calling the method.
     */
    private static final Marble[] marble = Marble.values();
    public static Marble getMarble(int i) { return Marble.marble[i]; }
}
