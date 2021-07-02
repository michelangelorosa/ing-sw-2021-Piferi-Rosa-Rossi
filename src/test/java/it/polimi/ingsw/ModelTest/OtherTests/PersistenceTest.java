package it.polimi.ingsw.ModelTest.OtherTests;

import it.polimi.ingsw.Model.Persistance.Persistence;
import it.polimi.ingsw.Model.Persistance.PersistenceServerController;
import it.polimi.ingsw.Model.Server.Server;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Tests for Persistence Class.
 */
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

    /**
     * Test for all methods inside Persistence class that don't write or read on file.
     */
    @Test
    public void testAll() {
        TestPersistence testPersistence = new TestPersistence();
        assertFalse(testPersistence.isGameStarted());
        assertEquals(-1, testPersistence.getNumberOfPlayers());
        assertEquals(0, testPersistence.getPlayerNames().size());
    }

    @Test
    public void persistenceControllerTest() {
        PersistenceServerController psc = new PersistenceServerController(new Server());
        assertNotNull(psc);
    }
}
