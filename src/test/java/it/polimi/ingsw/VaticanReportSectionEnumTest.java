package it.polimi.ingsw;

import static org.junit.Assert.*;

import it.polimi.ingsw.Model.Enums.VaticanReportSectionEnum;
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

    /**Test for toView method*/
    @Test
    public void toViewTest(){
        RedVaticanReportSection vaticanView;
        VaticanReportSection vatican = new VaticanReportSection(1, 4, 7);
        vaticanView = vatican.toView();
        assertSame(1, vaticanView.getBegin());
        assertSame(4, vaticanView.getEnd());
        assertSame(7, vaticanView.getPoints());
    }
}