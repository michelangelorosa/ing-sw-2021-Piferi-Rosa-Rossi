package it.polimi.ingsw.ModelTest.GameModelTest;

import static org.junit.Assert.*;

import it.polimi.ingsw.Model.Enums.VaticanReportSectionEnum;
import it.polimi.ingsw.Model.Exceptions.ModelException;
import it.polimi.ingsw.Model.GameModel.VaticanReportSection;
import it.polimi.ingsw.View.ReducedModel.RedVaticanReportSection;
import org.junit.Test;

/**
 * Unit test for public VaticanReportSectionTest enum-type Class.
 */

public class VaticanReportSectionEnumTest {

    /**
     * getter test for VaticanReportSectionTest enum-type Class.
     */

    @Test
    public void getterTest() {
        assertSame(VaticanReportSectionEnum.No, VaticanReportSectionEnum.getReportSection(0));
        assertSame(VaticanReportSectionEnum.ONE, VaticanReportSectionEnum.getReportSection(1));
        assertSame(VaticanReportSectionEnum.TWO, VaticanReportSectionEnum.getReportSection(2));
        assertSame(VaticanReportSectionEnum.THREE, VaticanReportSectionEnum.getReportSection(3));
    }

    /**
     * Test to check if the method "isCorrect()" works properly in VaticanReportSectionEnum Class
     */
    @Test
    public void isCorrectTest(){
        VaticanReportSection vatican = new VaticanReportSection(1, 6, 3);
        vatican.isCorrect();

        vatican = new VaticanReportSection(8, 1, 3);
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, vatican::isCorrect);
        assertEquals("Vatican Report Section index out of bound", e.getMessage());
    }
}