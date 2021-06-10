package it.polimi.ingsw.View.Utility.DebuggingTools;

/**
 * ServerDebugger Class defines data and methods used to print debugging information inside a Server Object.
 * All attributes are static because they are shared between all Debuggers of this type.
 * <p><b>Attributes:</b></p>
 * <ul>
 *     <li>String "S": contains ANSI escape codes and text to specify which Debugger is being used</li>
 *     <li>boolean "active": used to activate or deactivate a specific type of Debugger</li>
 * </ul>
 * @author redrick99
 */
public class ServerDebugger extends Debugger {
    private static final String S = ANSIDebug.SERVER + "[SERVER]            ";
    private static boolean active = true;

    /**
     * Protected Constructor to be accessed only by the DebuggerFactory Class.
     */
    protected ServerDebugger() {

    }

    /**
     * Prints a given String in the particular format of this type of Debugger.
     * @param string String which is to be printed.
     */
    @Override
    public void printDebug(String string) {
        if(allActive && active)
            System.out.println(S + string + R);
    }

    /**
     * Sets all Debuggers of this type to either active or not active.
     * @param active boolean used to set the "active" static attribute.
     */
    public static void setActive(boolean active) {
        ServerDebugger.active = active;
    }
}
