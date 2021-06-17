package it.polimi.ingsw.Controller.Actions;

/**
 * ActionType enum Class contains definitions for every possible Action coming from a Client
 * to the Server.
 */
public enum ActionType {
    CHOOSE_ACTION("choose_action"),
    GAME_SET("Game Set"),

    INIT_CHOOSE_RESOURCES("Initial Resource Choice"),
    INIT_CHOOSE_LEADER_CARDS("Initial Leader Card Choice"),

    MARKET_CHOOSE_ROW("Buy From Market"),
    CHOOSE_LEADER_CARD("Choose Leader Card"),
    ADD_RESOURCE("Add Resource"),
    SWITCH_DEPOT("Switch Depot"),
    RESET_WAREHOUSE("Reset Warehouse"),
    END_MARKET("End Market"),

    BUY_CARD("Buy Card"),
    PAY_RESOURCE_CARD("Pay Resource to Buy Card"),
    PAY_RESOURCE_PRODUCTION("Pay Resource to Activate Production"),
    END_PAY_CARD("End Pay Cycle"),
    CHOOSE_CARD_SLOT("Choose Card Slot"),

    ACTIVATE_PRODUCTION("Activate Production"),
    END_PAY_PRODUCTION("End Production Payment Cycle"),
    CHOOSE_PRODUCTION_OUTPUT("Choose Production Output"),

    ACTIVATE_LEADERCARD("Activate Leader Card"),
    DELETE_LEADERCARD("Discard Leader Card"),

    END_TURN("End Turn"),
    END_TURN_SINGLEPLAYER("End Turn Singleplayer"),

    FINAL_POINTS("Final Points"),

    STRONGBOX_CHEAT("{{CHEAT}} STRONGBOX"),
    FAITHTRACK_CHEAT("{{CHEAT}} FAITHTRACK"),
    DEVCARDS_CHEAT("{{CHEAT}} DEVCARDS"),
    LEADERCARDS_CHEAT("{{CHEAT}} LEADERCARDS"),
    VICTORYPOINTS_CHEAT("{{CHEAT}} VICTORYPOINTS"),

    DISCONNECTION("DISCONNECTION");


    private final String text;

    /**
     * Constructor for "ActionType" enum Class.
     * @param text String to be associated to the enum.
     */
    ActionType(String text) {
        this.text = text;
    }

    /**
     * Getter for "text" attribute in ActionType enum Class.
     */
    public String getText() {
        return text;
    }

    /**
     * This method is used to get the enum value given its assigned String text.
     * @param text is the String used to get the enum value.
     * @return the enum value
     */
    public static  ActionType fromString(String text) {
        for(ActionType actionType : ActionType.values())
            if(actionType.text.equalsIgnoreCase(text)) {
                return actionType;
            }
        return null;
    }
}

