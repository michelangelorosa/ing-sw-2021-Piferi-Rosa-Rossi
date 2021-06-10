package it.polimi.ingsw.View.Utility;

public class ANSIColors {
    public final static String RESET = "\u001B[0m";

    public final static String TITLE_COLOR_BACK = "\u001B[48;2;21;0;81m";
    public final static String TITLE_COLOR_FRONT = "\u001B[38;2;204;204;0m";

    public static final String FRONT_BLUE  = "\u001B[38;5;12m";
    public static final String FRONT_PURPLE = "\u001B[35m";
    public static final String FRONT_YELLOW = "\u001B[38;5;11m";
    public static final String FRONT_GREY = "\u001B[38;5;251m";
    public static final String FRONT_DARK_GREY = "\u001B[38;2;77;77;77m";

    public static final String FRONT_BLACK = "\u001B[38;5;16m";
    public static final String FRONT_RED = "\u001B[31m";
    public static final String FRONT_GREEN = "\u001B[38;2;0;153;0m";
    public static final String FRONT_CYAN = "\u001B[36m";
    public static final String FRONT_WHITE = "\u001B[37m";

    public static final String FRONT_BRIGHT_RED = "\u001B[91m";
    public static final String FRONT_BRIGHT_GREEN = "\u001B[92m";
    public static final String FRONT_BRIGHT_BLUE = "\u001B[94m";
    public static final String FRONT_BRIGHT_MAGENTA = "\u001B[95m";
    public static final String FRONT_BRIGHT_CYAN = "\u001B[96m";
    public static final String FRONT_BRIGHT_WHITE = "\u001B[38;5;255m";
    public static final String FRONT_ORANGE = "\u001B[38;2;255;166;77m";

    public static final String BACK_BLUE  = "\u001B[48;2;46;92;184m";
    public static final String BACK_PURPLE = "\u001B[48;5;91m";
    public static final String BACK_YELLOW = "\u001B[103m";
    public static final String BACK_GREY = "\u001B[100m";

    public static final String BACK_BLACK = "\u001B[48;5;16m";
    public static final String BACK_RED = "\u001B[41m";
    public static final String BACK_GREEN = "\u001B[48;2;0;102;0m";
    public static final String BACK_CYAN = "\u001B[46m";
    public static final String BACK_WHITE = "\u001B[47m";

    public static final String BACK_BRIGHT_RED = "\u001B[101m";
    public static final String BACK_BRIGHT_GREEN = "\u001B[102m";
    public static final String BACK_BRIGHT_BLUE = "\u001B[104m";
    public static final String BACK_BRIGHT_MAGENTA = "\u001B[105m";
    public static final String BACK_BRIGHT_CYAN = "\u001B[106m";
    public static final String BACK_BRIGHT_WHITE = "\u001B[48;5;255m";
    public static final String BACK_DARK_YELLOW = "\u001B[48;5;136m";
    public static final String BACK_BROWN = "\u001B[48;2;63;42;20m";

    public static final String BOLD = "\u001B[1m";

    public static final String SHIELD = "█";
    public static final String SERVANT = "§";
    public static final String COIN = "©";
    public static final String STONE = "¤";
    public static final String FAITH = "┼";

    public static final String YOUR_TURN_COLOR = "\u001B[1m" + "\u001B[48;5;154m" + "\u001B[38;5;233m";

    public static final String GAMEPLAY_ACTIONS = ANSIfont.UNDERLINE + "\u001B[1m" + "\u001B[38;5;39m";

    public static final String VISUALIZE = ANSIfont.UNDERLINE + "\u001B[1m" + "\u001B[38;5;202m";

    //TODO choose colors
    public static final String FINAL_POINTS = "";

    public static final String BLANK = "\u2800";
}
