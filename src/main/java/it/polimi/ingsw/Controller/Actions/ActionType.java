package it.polimi.ingsw.Controller.Actions;

/**
 * ActionType enum Class contains every single possible action coming from a Client, going
 * to the Server.
 */
public enum ActionType {
    CHOOSE_ACTION("choose_action"),

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

    END_TURN("end_turn");
    private String text;

    ActionType(String text) {
        this.text = text;
    }

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

