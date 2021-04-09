package it.polimi.ingsw;

import it.polimi.ingsw.Model.*;
import static org.junit.Assert.*;
import org.junit.Test;

public class FaithCellTest {

    /**
     * Test to check if getId works properly
     */

    @Test
    public void getIdTest(){

        FaithCell[] cells = new FaithCell[25];

        cells[0] = new FaithCell(0, 0, VaticanReportSectionEnum.No, PopeSpace.No);
        cells[1] = new FaithCell(1, 0, VaticanReportSectionEnum.No, PopeSpace.No);
        cells[2] = new FaithCell(2, 0, VaticanReportSectionEnum.No, PopeSpace.No);
        cells[3] = new FaithCell(3, 1, VaticanReportSectionEnum.No, PopeSpace.No);
        cells[4] = new FaithCell(4, 1, VaticanReportSectionEnum.No, PopeSpace.No);
        cells[5] = new FaithCell(5, 1, VaticanReportSectionEnum.ONE, PopeSpace.No);
        cells[6] = new FaithCell(6, 2, VaticanReportSectionEnum.ONE, PopeSpace.No);
        cells[7] = new FaithCell(7, 2, VaticanReportSectionEnum.ONE, PopeSpace.No);
        cells[8] = new FaithCell(8, 2, VaticanReportSectionEnum.ONE, PopeSpace.ONE);
        cells[9] = new FaithCell(9, 4, VaticanReportSectionEnum.No, PopeSpace.No);
        cells[10] = new FaithCell(10, 4, VaticanReportSectionEnum.No, PopeSpace.No);
        cells[11] = new FaithCell(11, 4, VaticanReportSectionEnum.No, PopeSpace.No);
        cells[12] = new FaithCell(12, 6, VaticanReportSectionEnum.TWO, PopeSpace.No);
        cells[13] = new FaithCell(13, 6, VaticanReportSectionEnum.TWO, PopeSpace.No);
        cells[14] = new FaithCell(14, 6, VaticanReportSectionEnum.TWO, PopeSpace.No);
        cells[15] = new FaithCell(15, 9, VaticanReportSectionEnum.TWO, PopeSpace.No);
        cells[16] = new FaithCell(16, 9, VaticanReportSectionEnum.TWO, PopeSpace.TWO);
        cells[17] = new FaithCell(17, 9, VaticanReportSectionEnum.No, PopeSpace.No);
        cells[18] = new FaithCell(18, 12, VaticanReportSectionEnum.No, PopeSpace.No);
        cells[19] = new FaithCell(19, 12, VaticanReportSectionEnum.THREE, PopeSpace.No);
        cells[20] = new FaithCell(20, 12, VaticanReportSectionEnum.THREE, PopeSpace.No);
        cells[21] = new FaithCell(21, 16, VaticanReportSectionEnum.THREE, PopeSpace.No);
        cells[22] = new FaithCell(22, 16, VaticanReportSectionEnum.THREE, PopeSpace.No);
        cells[23] = new FaithCell(23, 16, VaticanReportSectionEnum.THREE, PopeSpace.No);
        cells[24] = new FaithCell(24, 20, VaticanReportSectionEnum.THREE, PopeSpace.THREE);

        for(int i = 0; i < 25; i++){
            assertEquals(i, cells[i].getIdCell());
        }
    }

    /**
     * Test to check if getVictoryPoints works properly
     */

    @Test
    public void getVictoryPointsTest(){

        FaithCell[] cells = new FaithCell[25];

        cells[0] = new FaithCell(0, 0, VaticanReportSectionEnum.No, PopeSpace.No);
        cells[1] = new FaithCell(1, 0, VaticanReportSectionEnum.No, PopeSpace.No);
        cells[2] = new FaithCell(2, 0, VaticanReportSectionEnum.No, PopeSpace.No);
        cells[3] = new FaithCell(3, 1, VaticanReportSectionEnum.No, PopeSpace.No);
        cells[4] = new FaithCell(4, 1, VaticanReportSectionEnum.No, PopeSpace.No);
        cells[5] = new FaithCell(5, 1, VaticanReportSectionEnum.ONE, PopeSpace.No);
        cells[6] = new FaithCell(6, 2, VaticanReportSectionEnum.ONE, PopeSpace.No);
        cells[7] = new FaithCell(7, 2, VaticanReportSectionEnum.ONE, PopeSpace.No);
        cells[8] = new FaithCell(8, 2, VaticanReportSectionEnum.ONE, PopeSpace.ONE);
        cells[9] = new FaithCell(9, 4, VaticanReportSectionEnum.No, PopeSpace.No);
        cells[10] = new FaithCell(10, 4, VaticanReportSectionEnum.No, PopeSpace.No);
        cells[11] = new FaithCell(11, 4, VaticanReportSectionEnum.No, PopeSpace.No);
        cells[12] = new FaithCell(12, 6, VaticanReportSectionEnum.TWO, PopeSpace.No);
        cells[13] = new FaithCell(13, 6, VaticanReportSectionEnum.TWO, PopeSpace.No);
        cells[14] = new FaithCell(14, 6, VaticanReportSectionEnum.TWO, PopeSpace.No);
        cells[15] = new FaithCell(15, 9, VaticanReportSectionEnum.TWO, PopeSpace.No);
        cells[16] = new FaithCell(16, 9, VaticanReportSectionEnum.TWO, PopeSpace.TWO);
        cells[17] = new FaithCell(17, 9, VaticanReportSectionEnum.No, PopeSpace.No);
        cells[18] = new FaithCell(18, 12, VaticanReportSectionEnum.No, PopeSpace.No);
        cells[19] = new FaithCell(19, 12, VaticanReportSectionEnum.THREE, PopeSpace.No);
        cells[20] = new FaithCell(20, 12, VaticanReportSectionEnum.THREE, PopeSpace.No);
        cells[21] = new FaithCell(21, 16, VaticanReportSectionEnum.THREE, PopeSpace.No);
        cells[22] = new FaithCell(22, 16, VaticanReportSectionEnum.THREE, PopeSpace.No);
        cells[23] = new FaithCell(23, 16, VaticanReportSectionEnum.THREE, PopeSpace.No);
        cells[24] = new FaithCell(24, 20, VaticanReportSectionEnum.THREE, PopeSpace.THREE);

        assertEquals(0, cells[0].getVictoryPoints());
        assertEquals(0, cells[1].getVictoryPoints());
        assertEquals(0, cells[2].getVictoryPoints());
        assertEquals(1, cells[3].getVictoryPoints());
        assertEquals(1, cells[4].getVictoryPoints());
        assertEquals(1, cells[5].getVictoryPoints());
        assertEquals(2, cells[6].getVictoryPoints());
        assertEquals(2, cells[7].getVictoryPoints());
        assertEquals(2, cells[8].getVictoryPoints());
        assertEquals(4, cells[9].getVictoryPoints());
        assertEquals(4, cells[10].getVictoryPoints());
        assertEquals(4, cells[11].getVictoryPoints());
        assertEquals(6, cells[12].getVictoryPoints());
        assertEquals(6, cells[13].getVictoryPoints());
        assertEquals(6, cells[14].getVictoryPoints());
        assertEquals(9, cells[15].getVictoryPoints());
        assertEquals(9, cells[16].getVictoryPoints());
        assertEquals(9, cells[17].getVictoryPoints());
        assertEquals(12, cells[18].getVictoryPoints());
        assertEquals(12, cells[19].getVictoryPoints());
        assertEquals(12, cells[20].getVictoryPoints());
        assertEquals(16, cells[21].getVictoryPoints());
        assertEquals(16, cells[22].getVictoryPoints());
        assertEquals(16, cells[23].getVictoryPoints());
        assertEquals(20, cells[24].getVictoryPoints());
    }

    /**
     * Test to check if getVaticanReportSection works properly
     */

    @Test
    public void getVaticanReportSectionTest(){
        FaithCell[] cells = new FaithCell[25];

        cells[0] = new FaithCell(0, 0, VaticanReportSectionEnum.No, PopeSpace.No);
        cells[1] = new FaithCell(1, 0, VaticanReportSectionEnum.No, PopeSpace.No);
        cells[2] = new FaithCell(2, 0, VaticanReportSectionEnum.No, PopeSpace.No);
        cells[3] = new FaithCell(3, 1, VaticanReportSectionEnum.No, PopeSpace.No);
        cells[4] = new FaithCell(4, 1, VaticanReportSectionEnum.No, PopeSpace.No);
        cells[5] = new FaithCell(5, 1, VaticanReportSectionEnum.ONE, PopeSpace.No);
        cells[6] = new FaithCell(6, 2, VaticanReportSectionEnum.ONE, PopeSpace.No);
        cells[7] = new FaithCell(7, 2, VaticanReportSectionEnum.ONE, PopeSpace.No);
        cells[8] = new FaithCell(8, 2, VaticanReportSectionEnum.ONE, PopeSpace.ONE);
        cells[9] = new FaithCell(9, 4, VaticanReportSectionEnum.No, PopeSpace.No);
        cells[10] = new FaithCell(10, 4, VaticanReportSectionEnum.No, PopeSpace.No);
        cells[11] = new FaithCell(11, 4, VaticanReportSectionEnum.No, PopeSpace.No);
        cells[12] = new FaithCell(12, 6, VaticanReportSectionEnum.TWO, PopeSpace.No);
        cells[13] = new FaithCell(13, 6, VaticanReportSectionEnum.TWO, PopeSpace.No);
        cells[14] = new FaithCell(14, 6, VaticanReportSectionEnum.TWO, PopeSpace.No);
        cells[15] = new FaithCell(15, 9, VaticanReportSectionEnum.TWO, PopeSpace.No);
        cells[16] = new FaithCell(16, 9, VaticanReportSectionEnum.TWO, PopeSpace.TWO);
        cells[17] = new FaithCell(17, 9, VaticanReportSectionEnum.No, PopeSpace.No);
        cells[18] = new FaithCell(18, 12, VaticanReportSectionEnum.No, PopeSpace.No);
        cells[19] = new FaithCell(19, 12, VaticanReportSectionEnum.THREE, PopeSpace.No);
        cells[20] = new FaithCell(20, 12, VaticanReportSectionEnum.THREE, PopeSpace.No);
        cells[21] = new FaithCell(21, 16, VaticanReportSectionEnum.THREE, PopeSpace.No);
        cells[22] = new FaithCell(22, 16, VaticanReportSectionEnum.THREE, PopeSpace.No);
        cells[23] = new FaithCell(23, 16, VaticanReportSectionEnum.THREE, PopeSpace.No);
        cells[24] = new FaithCell(24, 20, VaticanReportSectionEnum.THREE, PopeSpace.THREE);

        assertEquals(VaticanReportSectionEnum.No, cells[0].getVaticanReportSection());
        assertEquals(VaticanReportSectionEnum.No, cells[1].getVaticanReportSection());
        assertEquals(VaticanReportSectionEnum.No, cells[2].getVaticanReportSection());
        assertEquals(VaticanReportSectionEnum.No, cells[3].getVaticanReportSection());
        assertEquals(VaticanReportSectionEnum.No, cells[4].getVaticanReportSection());
        assertEquals(VaticanReportSectionEnum.ONE, cells[5].getVaticanReportSection());
        assertEquals(VaticanReportSectionEnum.ONE, cells[6].getVaticanReportSection());
        assertEquals(VaticanReportSectionEnum.ONE, cells[7].getVaticanReportSection());
        assertEquals(VaticanReportSectionEnum.ONE, cells[8].getVaticanReportSection());
        assertEquals(VaticanReportSectionEnum.No, cells[9].getVaticanReportSection());
        assertEquals(VaticanReportSectionEnum.No, cells[10].getVaticanReportSection());
        assertEquals(VaticanReportSectionEnum.No, cells[11].getVaticanReportSection());
        assertEquals(VaticanReportSectionEnum.TWO, cells[12].getVaticanReportSection());
        assertEquals(VaticanReportSectionEnum.TWO, cells[13].getVaticanReportSection());
        assertEquals(VaticanReportSectionEnum.TWO, cells[14].getVaticanReportSection());
        assertEquals(VaticanReportSectionEnum.TWO, cells[15].getVaticanReportSection());
        assertEquals(VaticanReportSectionEnum.TWO, cells[16].getVaticanReportSection());
        assertEquals(VaticanReportSectionEnum.No, cells[17].getVaticanReportSection());
        assertEquals(VaticanReportSectionEnum.No, cells[18].getVaticanReportSection());
        assertEquals(VaticanReportSectionEnum.THREE, cells[19].getVaticanReportSection());
        assertEquals(VaticanReportSectionEnum.THREE, cells[20].getVaticanReportSection());
        assertEquals(VaticanReportSectionEnum.THREE, cells[21].getVaticanReportSection());
        assertEquals(VaticanReportSectionEnum.THREE, cells[22].getVaticanReportSection());
        assertEquals(VaticanReportSectionEnum.THREE, cells[23].getVaticanReportSection());
        assertEquals(VaticanReportSectionEnum.THREE, cells[24].getVaticanReportSection());
    }

    /**
     * Test to check if getPopeSpaceTest works properly
     */

    @Test
    public void getPopeSpaceTest(){
        FaithCell[] cells = new FaithCell[25];

        cells[0] = new FaithCell(0, 0, VaticanReportSectionEnum.No, PopeSpace.No);
        cells[1] = new FaithCell(1, 0, VaticanReportSectionEnum.No, PopeSpace.No);
        cells[2] = new FaithCell(2, 0, VaticanReportSectionEnum.No, PopeSpace.No);
        cells[3] = new FaithCell(3, 1, VaticanReportSectionEnum.No, PopeSpace.No);
        cells[4] = new FaithCell(4, 1, VaticanReportSectionEnum.No, PopeSpace.No);
        cells[5] = new FaithCell(5, 1, VaticanReportSectionEnum.ONE, PopeSpace.No);
        cells[6] = new FaithCell(6, 2, VaticanReportSectionEnum.ONE, PopeSpace.No);
        cells[7] = new FaithCell(7, 2, VaticanReportSectionEnum.ONE, PopeSpace.No);
        cells[8] = new FaithCell(8, 2, VaticanReportSectionEnum.ONE, PopeSpace.ONE);
        cells[9] = new FaithCell(9, 4, VaticanReportSectionEnum.No, PopeSpace.No);
        cells[10] = new FaithCell(10, 4, VaticanReportSectionEnum.No, PopeSpace.No);
        cells[11] = new FaithCell(11, 4, VaticanReportSectionEnum.No, PopeSpace.No);
        cells[12] = new FaithCell(12, 6, VaticanReportSectionEnum.TWO, PopeSpace.No);
        cells[13] = new FaithCell(13, 6, VaticanReportSectionEnum.TWO, PopeSpace.No);
        cells[14] = new FaithCell(14, 6, VaticanReportSectionEnum.TWO, PopeSpace.No);
        cells[15] = new FaithCell(15, 9, VaticanReportSectionEnum.TWO, PopeSpace.No);
        cells[16] = new FaithCell(16, 9, VaticanReportSectionEnum.TWO, PopeSpace.TWO);
        cells[17] = new FaithCell(17, 9, VaticanReportSectionEnum.No, PopeSpace.No);
        cells[18] = new FaithCell(18, 12, VaticanReportSectionEnum.No, PopeSpace.No);
        cells[19] = new FaithCell(19, 12, VaticanReportSectionEnum.THREE, PopeSpace.No);
        cells[20] = new FaithCell(20, 12, VaticanReportSectionEnum.THREE, PopeSpace.No);
        cells[21] = new FaithCell(21, 16, VaticanReportSectionEnum.THREE, PopeSpace.No);
        cells[22] = new FaithCell(22, 16, VaticanReportSectionEnum.THREE, PopeSpace.No);
        cells[23] = new FaithCell(23, 16, VaticanReportSectionEnum.THREE, PopeSpace.No);
        cells[24] = new FaithCell(24, 20, VaticanReportSectionEnum.THREE, PopeSpace.THREE);

        for(int i = 0; i < 25; i++){
            if(i != 8 && i != 16 && i != 24){
                assertEquals(PopeSpace.No, cells[i].getPopeSpace());
            }
            else if(i == 8){
                assertEquals(PopeSpace.ONE, cells[i].getPopeSpace());
            }
            else if(i == 16){
                assertEquals(PopeSpace.TWO, cells[i].getPopeSpace());
            }
            else if(i == 20){
                assertEquals(PopeSpace.THREE, cells[i].getPopeSpace());
            }
        }
    }

    /**
     * Test to check if setVaticanReportSection works properly
     */

    @Test
    public void setVaticanReportSectionTest(){
        FaithCell[] cells = new FaithCell[25];

        cells[5] = new FaithCell(0, 0, VaticanReportSectionEnum.No, PopeSpace.No);
        cells[5].setVaticanReportSection(VaticanReportSectionEnum.THREE);

        assertEquals(VaticanReportSectionEnum.THREE, cells[5].getVaticanReportSection());
    }

    /**
     * Test to check if setVictoryPoints works properly
     */

    @Test
    public void setVictoryPointsTest(){
        FaithCell[] cells = new FaithCell[25];

        cells[12] = new FaithCell(0, 0, VaticanReportSectionEnum.No, PopeSpace.No);
        cells[12].setVictoryPoints(134);

        assertEquals(134, cells[12].getVictoryPoints());
    }

    /**
     * Test to check if toString works properly
     */

    @Test
    public void toStringTest() {

        FaithCell[] cells = new FaithCell[25];

        cells[21] = new FaithCell(0, 0, VaticanReportSectionEnum.No, PopeSpace.No);

        String testString = "0 0 No No";

        assertArrayEquals(testString.toCharArray(), cells[21].toString().toCharArray());
    }

}
