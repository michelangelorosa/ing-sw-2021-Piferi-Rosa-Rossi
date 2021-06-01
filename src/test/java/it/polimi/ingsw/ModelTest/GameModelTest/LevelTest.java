package it.polimi.ingsw.ModelTest.GameModelTest;

import it.polimi.ingsw.Model.Enums.Level;
import org.junit.Test;

import static org.junit.Assert.assertSame;

/**
 * Unit test for Level enum-type Class.
 */
public class LevelTest {

    /**
     * getter test for Level enum-type Class.
     */
    @Test
    public void getterTest() {

        assertSame(Level.ONE, Level.getLevel(0));
        assertSame(Level.TWO, Level.getLevel(1));
        assertSame(Level.THREE, Level.getLevel(2));

    }
}
