package it.polimi.ingsw.Model.Enums;

import it.polimi.ingsw.View.Utility.ANSIColors;

public enum ResourceType {
    NONE, SHIELDS, SERVANTS, COINS, STONES;

    public String toCli() {
        String string;
        switch (this) {
            case SHIELDS: string = ANSIColors.FRONT_BLUE + "SHIELD " + ANSIColors.RESET;
                break;
            case SERVANTS: string = ANSIColors.FRONT_PURPLE + "SERVANT" + ANSIColors.RESET;
                break;
            case COINS: string = ANSIColors.FRONT_YELLOW + " COIN  " + ANSIColors.RESET;
                break;
            case STONES: string = ANSIColors.FRONT_GREY + " STONE " + ANSIColors.RESET;
                break;
            case NONE: string = "       ";
                break;
            default: string = null;
                break;
        }
        return string;
    }

    public String toCliSymbol() {
        String string;
        switch (this) {
            case SHIELDS: string = ANSIColors.FRONT_BLUE + ANSIColors.SHIELD + ANSIColors.RESET;
                break;
            case SERVANTS: string = ANSIColors.FRONT_PURPLE + ANSIColors.SERVANT + ANSIColors.RESET;
                break;
            case COINS: string = ANSIColors.FRONT_YELLOW + ANSIColors.COIN + ANSIColors.RESET;
                break;
            case STONES: string = ANSIColors.FRONT_GREY + ANSIColors.STONE + ANSIColors.RESET;
                break;
            case NONE: string = " ";
                break;
            default: string = null;
                break;
        }
        return string;
    }

    public String toCliNoColor() {
        String string;
        switch (this) {
            case SHIELDS:  string = "SHIELDS ";
                break;
            case SERVANTS: string = "SERVANTS";
                break;
            case COINS:    string = " COINS  ";
                break;
            case STONES:   string = " STONES ";
                break;
            case NONE:     string = "        ";
                break;
            default:       string = null;
                break;
        }
        return string;
    }

    public static String faithToCli() {
        return ANSIColors.FRONT_RED + ANSIColors.FAITH;
    }

    public static String faithPointsToCli(int points) {
        if(points > 9) return ANSIColors.FRONT_RED+faithToCli()+":"+points+ANSIColors.RESET; else return ANSIColors.FRONT_RED+faithToCli()+": "+points+ANSIColors.RESET;
    }

    public ResourceType viewToModel() {
        switch(this) {
            case SHIELDS:  return ResourceType.SHIELDS;

            case SERVANTS: return ResourceType.SERVANTS;

            case COINS:    return ResourceType.COINS;

            case STONES:   return ResourceType.STONES;

            case NONE:     return ResourceType.NONE;

            default:       return null;
        }
    }
}
