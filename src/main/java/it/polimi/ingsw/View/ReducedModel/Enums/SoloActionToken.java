package it.polimi.ingsw.View.ReducedModel.Enums;
/**
 * Different Action Tokens for the SinglePlayer game
 */
public enum SoloActionToken {
    BLACKCROSSPLUS2, BLACKCROSSSHUFFLE, DELETE2BLUE, DELETE2PURPLE, DELETE2YELLOW, DELETE2GREEN;


    private static final SoloActionToken[] soloToken = SoloActionToken.values();
    public static SoloActionToken getToken(int i) { return SoloActionToken.getToken(i); }
}