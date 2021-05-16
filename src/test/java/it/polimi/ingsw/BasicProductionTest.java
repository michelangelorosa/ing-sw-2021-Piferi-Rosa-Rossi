package it.polimi.ingsw;

import static org.junit.Assert.*;

import it.polimi.ingsw.Model.BasicProduction;
import it.polimi.ingsw.Model.ResourceStack;
import org.junit.Test;

/**
 * Test for BasicProduction Class.
 */
public class BasicProductionTest {

    ResourceStack stack = new ResourceStack(1,1,1,1);
    ResourceStack stack2 = new ResourceStack(2,2,2,2);

    BasicProduction basicProductionOne = new BasicProduction(5, 12);
    BasicProduction basicProductionTwo = new BasicProduction(stack, stack2, 1, 2, 3);

    /**
     * Constructor and getter test for BasicProduction Class.
     */
    @Test
    public void constructorTest() {


        assertEquals(5, basicProductionOne.getJollyIn());
        assertEquals(12, basicProductionOne.getJollyOut());

        assertEquals("1 1 1 1", basicProductionTwo.getFixedInputs().toString());
        assertEquals("2 2 2 2", basicProductionTwo.getFixedOutputs().toString());
        assertEquals(1, basicProductionTwo.getJollyIn());
        assertEquals(2, basicProductionTwo.getJollyOut());
        assertEquals(3, basicProductionTwo.getOutputFaith());
    }

    /**Test for toView method*/
    @Test
    public void toViewTest(){
        it.polimi.ingsw.View.ReducedModel.BasicProduction basicView;

        basicView = basicProductionOne.toView();

        assertEquals(5, basicView.getJollyIn());
        assertEquals(12, basicView.getJollyOut());

        basicView = basicProductionTwo.toView();

        assertEquals("1 1 1 1", basicView.getFixedInputs().toString());
        assertEquals("2 2 2 2", basicView.getFixedOutputs().toString());
        assertEquals(1, basicView.getJollyIn());
        assertEquals(2, basicView.getJollyOut());
        assertEquals(3, basicView.getOutputFaith());
    }
}
