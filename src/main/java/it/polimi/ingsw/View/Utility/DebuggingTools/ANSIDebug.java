package it.polimi.ingsw.View.Utility.DebuggingTools;

/**
 * ANSIDebug abstract Class contains colors and styles to be used by the Debugger Classes when
 * printing debug information.
 */
public abstract class ANSIDebug {
    public static final String SERVER = "\u001B[38;2;255;121;77m";
    public static final String SERVER_CONNECTION = "\u001B[38;2;255;166;77m";
    public static final String SERVER_MESSAGE_HANDLER = "\u001B[38;2;255;210;77m";

    public static final String CLIENT = "\u001B[38;2;77;166;255m";
    public static final String CLIENT_CONNECTION = "\u001B[38;2;77;255;255m";

    public static final String CLI = "\u001B[38;2;0;255;0m";
    public static final String CLI_CONTROLLER = "\u001B[38;2;191;255;0m";

    public static final String CONTROLLER = "\u001B[38;2;255;0;102m";
    public static final String MODEL = "\u001B[38;2;191;0;255m";
}
