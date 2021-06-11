package it.polimi.ingsw.View.Utility.DebuggingTools;

/**
 * CliDebugger Class defines data and methods used to print debugging information inside a Cli Object.
 * All attributes are static because they are shared between all Debuggers of this type.
 * <p><b>Attributes:</b></p>
 * <ul>
 *     <li>String "C": contains ANSI escape codes and text to specify which Debugger is being used</li>
 *     <li>boolean "active": used to activate or deactivate a specific type of Debugger</li>
 * </ul>
 * @author redrick99
 */
public class CliDebugger extends Debugger {
    private static final String C = ANSIDebug.CLI + "[CLI]               ";
    private static boolean active = true;

    /**
     * Protected Constructor to be accessed only by the DebuggerFactory Class.
     */
    protected CliDebugger() {

    }

    /**
     * Prints a given String in the particular format of this type of Debugger.
     * @param string String which is to be printed.
     */
    @Override
    public void printDebug(String string) {
        if(allActive && active)
            System.out.println(C + string + R);
    }

    /**
     * Sets all Debuggers of this type to either active or not active.
     * @param active boolean used to set the "active" static attribute.
     */
    public static void setActive(boolean active) {
        CliDebugger.active = active;
    }
}
