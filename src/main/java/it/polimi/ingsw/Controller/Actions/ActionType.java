package it.polimi.ingsw.Controller.Actions;

/**
 * ActionType enum Class contains definitions for every possible Action coming from a Client
 * to the Server.
 */
public enum ActionType {
    CHOOSE_ACTION("choose_action"),

    INIT_CHOOSE_RESOURCES("init_choose_resources"),
    INIT_CHOOSE_LEADER_CARDS("init_choose_leader_cards"),

    BUY_FROM_MARKET("buy_market"),
    MARKET_CHOOSE_ROW("market_choose_row"),
    CHOOSE_LEADER_CARD("choose_leader_card"),
    ADD_RESOURCE("add_resource"),
    SWITCH_DEPOT("add_resource"),
    RESET_WAREHOUSE("reset_warehouse"),
    END_MARKET("end_market"),

    BUY_CARD("buy_card"),
    PAY_RESOURCE_CARD("pay_resource_card"),
    PAY_RESOURCE_PRODUCTION("pay_resource_production"),
    END_PAY_CARD("end_pay"),
    CHOOSE_CARD_SLOT("choose_card_slot"),

    ACTIVATE_PRODUCTION("activate_production"),
    END_PAY_PRODUCTION("end_pay_production"),
    CHOOSE_PRODUCTION_OUTPUT("choose_production_output"),

    ACTIVATE_LEADERCARD("activate_leadercard"),
    DELETE_LEADERCARD("delete_leadercard"),

    END_TURN("end_turn"),

    FINAL_POINTS("Final Points");


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

