package it.polimi.ingsw.ModelTest.GameModelTest;

import static org.junit.Assert.*;

import it.polimi.ingsw.Model.Enums.PopeSpace;
import it.polimi.ingsw.Model.GameModel.PopeTileClass;
import it.polimi.ingsw.View.ReducedModel.RedPopeTileClass;
import org.junit.Test;

/**
 * Unit test for PopeSpace enum-type Class.
 */

public class PopeSpaceTest {

    /**
     * getter test for PopeSpace enum-type Class.
     */

    @Test
    public void getterTest() {

        assertSame(PopeSpace.No, PopeSpace.getPopeSpace(0));
        assertSame(PopeSpace.ONE, PopeSpace.getPopeSpace(1));
        assertSame(PopeSpace.TWO, PopeSpace.getPopeSpace(2));
        assertSame(PopeSpace.THREE, PopeSpace.getPopeSpace(3));
    }
}