package it.polimi.ingsw.View.Utility.DebuggingTools;

/**
 * DebuggerFactory Class contains a method used to generate a Debugger sub-class.
 */
public class DebuggerFactory {

    /**
     * Returns a specific Debugger sub-class depending on input parameter.
     * @param type DebuggerType used to specify which type of Debugger is used by a specific Class.
     * @return A new Debugger sub-class.
     */
    public static Debugger getDebugger(DebuggerType type) {
        switch(type) {
            case SERVER: return new ServerDebugger();

            case SERVER_CONNECTION: return new ServerConnectionDebugger();

            case SERVER_MESSAGE_HANDLER: return new ServerMessageHandlerDebugger();

            case CLIENT: return new ClientDebugger();

            case CLIENT_CONNECTION: return new ClientConnectionDebugger();

            case CLI: return new CliDebugger();

            case CLI_CONTROLLER: return new CliControllerDebugger();

            case CONTROLLER: return new ControllerDebugger();

            case MODEL: return new ModelDebugger();

            default: return new Debugger() {

            };
        }
    }
}
