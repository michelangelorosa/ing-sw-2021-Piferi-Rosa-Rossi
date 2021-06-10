package it.polimi.ingsw.View.Utility.DebuggingTools;

import it.polimi.ingsw.View.Utility.ANSIColors;

/**
 * Debugger Class defines common data and methods used to print debugging information.
 * All attributes are static because they are shared between all Debuggers.
 * <p><b>Attributes:</b></p>
 * <ul>
 *     <li>boolean "allActive": used to activate or deactivate all Debuggers at once</li>
 *     <li>String "R": contains ANSI escape code needed to reset color and fonts</li>
 * </ul>
 * @author redrick99
 */
public abstract class Debugger {
    protected static boolean allActive;
    protected static final String R = ANSIColors.RESET;

    /**
     * Protected Constructor to be accessed only by the DebuggerFactory Class.
     */
    protected Debugger() {

    }

    /**
     * Prints a given String in the particular format of the type of Debugger which overrides this Class.
     * @param string String which is to be printed.
     */
    public void printDebug(String string) {

    }

    /**
     * Sets all Debuggers active or not active at once.
     * @param allActive boolean used to set the "allActive" static attribute.
     */
    public static void setAllActive(boolean allActive) {
        Debugger.allActive = allActive;
    }

    /**
     * Getter for "allActive" static attribute.
     */
    public static boolean isAllActive() {
        return allActive;
    }
}
