package it.polimi.ingsw;

import static org.junit.Assert.*;

import it.polimi.ingsw.Model.Color;
import it.polimi.ingsw.Model.Player;
import it.polimi.ingsw.Model.VaticanReportSection;
import org.junit.Test;

/**
 * Unit test for public VaticanReportSectionTest enum-type Class.
 */

public class VaticanReportSectionTest {

    /**
     * getter test for VaticanReportSectionTest enum-type Class.
     */

    @Test
    public void getterTest() {

        assertSame(VaticanReportSection.No, VaticanReportSection.getReportSection(0));
        assertSame(VaticanReportSection.ONE, VaticanReportSection.getReportSection(1));
        assertSame(VaticanReportSection.TWO, VaticanReportSection.getReportSection(2));
        assertSame(VaticanReportSection.THREE, VaticanReportSection.getReportSection(3));

    }
}