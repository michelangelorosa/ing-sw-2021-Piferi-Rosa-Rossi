package it.polimi.ingsw.ModelTest.OtherTests;

import it.polimi.ingsw.Model.Persistance.Persistence;
import it.polimi.ingsw.Model.Persistance.PersistenceServerController;
import it.polimi.ingsw.Model.Server.Server;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class PersistenceTest {
    static class TestPersistence extends Persistence {
        protected void testCreateFile() {
            try {
                super.testCreateFile();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testAll() {
        TestPersistence testPersistence = new TestPersistence();
        assertFalse(testPersistence.isGameStarted());
        assertEquals(-1, testPersistence.getNumberOfPlayers());
        assertEquals(0, testPersistence.getPlayerNames().size());

        ArrayList<String> playerNames = new ArrayList<>();
        playerNames.add("One");
        playerNames.add("Two");
        playerNames.add("Three");
        playerNames.add("Four");

        testPersistence.testCreateFile();
        testPersistence.writeFile(true, 4, playerNames);
        testPersistence.readFile();

        assertTrue(testPersistence.isGameStarted());
        assertEquals(4, testPersistence.getNumberOfPlayers());
        assertEquals(4, testPersistence.getPlayerNames().size());
        assertEquals("One", testPersistence.getPlayerNames().get(0));
        assertEquals("Two", testPersistence.getPlayerNames().get(1));
        assertEquals("Three", testPersistence.getPlayerNames().get(2));
        assertEquals("Four", testPersistence.getPlayerNames().get(3));

        testPersistence.wantsToRestart();
        assertTrue(testPersistence.majorityWantsToRestart());
        testPersistence.wantsToContinue();
        testPersistence.wantsToContinue();
        assertFalse(testPersistence.majorityWantsToRestart());

        try {
            testPersistence.resetFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        testPersistence.readFile();
        assertFalse(testPersistence.isGameStarted());
    }

    @Test
    public void persistenceControllerTest() {
        PersistenceServerController psc = new PersistenceServerController(new Server());
    }
}
