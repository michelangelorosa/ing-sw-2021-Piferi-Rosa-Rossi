package it.polimi.ingsw;

import static org.junit.Assert.*;

import it.polimi.ingsw.Model.BasicProduction;
import it.polimi.ingsw.Model.ResourceStack;
import org.junit.Test;

/**
 * Test for BasicProduction Class.
 */
public class BasicProductionTest {

    /**
     * Constructor and getter test for BasicProduction Class.
     */
    @Test
    public void constructorTest() {
        ResourceStack stack = new ResourceStack(1,1,1,1);
        ResourceStack stack2 = new ResourceStack(2,2,2,2);

        BasicProduction basicProductionOne = new BasicProduction(5, 12);
        BasicProduction basicProductionTwo = new BasicProduction(stack, stack2, 1, 2, 3);

        assertEquals(5, basicProductionOne.getJollyIn());
        assertEquals(12, basicProductionOne.getJollyOut());

        assertEquals("1 1 1 1", basicProductionTwo.getFixedInputs().toString());
        assertEquals("2 2 2 2", basicProductionTwo.getFixedOutputs().toString());
        assertEquals(1, basicProductionTwo.getJollyIn());
        assertEquals(2, basicProductionTwo.getJollyOut());
        assertEquals(3, basicProductionTwo.getOutputFaith());
    }
}