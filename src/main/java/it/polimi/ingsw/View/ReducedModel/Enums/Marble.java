package it.polimi.ingsw.View.ReducedModel.Enums;

import it.polimi.ingsw.Model.Player;

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

    public ResourceType marbleToResource(Player player) {
        switch(this) {
            case WHITE: player.getBoard().getResourceManager().addWhiteMarble();
            break;
            case BLUE: return ResourceType.SHIELDS;

            case GREY: return ResourceType.STONES;

            case YELLOW: return ResourceType.COINS;

            case PURPLE: return ResourceType.SERVANTS;

            case RED: player.stepAhead(1);
            break;
        }
        return null;
    }
}
