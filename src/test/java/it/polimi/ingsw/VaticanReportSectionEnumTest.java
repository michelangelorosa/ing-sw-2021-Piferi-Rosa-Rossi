package it.polimi.ingsw;

import static org.junit.Assert.*;

import it.polimi.ingsw.Model.Enums.VaticanReportSectionEnum;
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
}