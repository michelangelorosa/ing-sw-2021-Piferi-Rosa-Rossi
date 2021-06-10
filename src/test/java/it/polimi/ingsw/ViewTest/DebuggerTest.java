package it.polimi.ingsw.ViewTest;

import it.polimi.ingsw.View.Utility.DebuggingTools.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class DebuggerTest {
    @Test
    public void debuggerTest() {
        String e = "this is an error!";
        Debugger.setAllActive(true);

        Debugger debugger = DebuggerFactory.getDebugger(DebuggerType.SERVER);
        assertTrue(debugger instanceof ServerDebugger);
        debugger.printDebug(e);
        ServerDebugger.setActive(false);

        debugger = DebuggerFactory.getDebugger(DebuggerType.SERVER_CONNECTION);
        assertTrue(debugger instanceof ServerConnectionDebugger);
        debugger.printDebug(e);
        ServerConnectionDebugger.setActive(false);

        debugger = DebuggerFactory.getDebugger(DebuggerType.SERVER_MESSAGE_HANDLER);
        assertTrue(debugger instanceof ServerMessageHandlerDebugger);
        debugger.printDebug(e);
        ServerMessageHandlerDebugger.setActive(false);

        debugger = DebuggerFactory.getDebugger(DebuggerType.CLIENT);
        assertTrue(debugger instanceof ClientDebugger);
        debugger.printDebug(e);
        ClientDebugger.setActive(false);

        debugger = DebuggerFactory.getDebugger(DebuggerType.CLIENT_CONNECTION);
        assertTrue(debugger instanceof ClientConnectionDebugger);
        debugger.printDebug(e);
        ClientConnectionDebugger.setActive(false);

        debugger = DebuggerFactory.getDebugger(DebuggerType.DEFAULT);
        debugger.printDebug("error");
        Debugger.setAllActive(false);
        assertFalse(Debugger.isAllActive());

        ANSIDebug debug = new ANSIDebug() {
        };
        System.out.println(debug);
    }
}
