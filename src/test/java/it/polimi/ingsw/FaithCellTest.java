package it.polimi.ingsw;

import it.polimi.ingsw.Model.*;
import static org.junit.Assert.*;

import it.polimi.ingsw.Model.JSON.JSONReader;
import it.polimi.ingsw.View.ReducedModel.RedFaithCell;
import org.junit.Test;

public class FaithCellTest {
    FaithCell[] cellToTest = JSONReader.ReadFaithCells();
    VaticanReportSection[] vaticanReportSections = JSONReader.ReadVaticanReportSection();
    /**
     * GetterTest
     */

    @Test
    public void getterTest(){
        FaithCell[] cells = new FaithCell[25];

        cells[0] = new FaithCell(0, 0);
        cells[1] = new FaithCell(1, 0);
        cells[2] = new FaithCell(2, 0);
        cells[3] = new FaithCell(3, 1);
        cells[4] = new FaithCell(4, 1);
        cells[5] = new FaithCell(5, 1);
        cells[6] = new FaithCell(6, 2);
        cells[7] = new FaithCell(7, 2);
        cells[8] = new FaithCell(8, 2);
        cells[9] = new FaithCell(9, 4);
        cells[10] = new FaithCell(10, 4);
        cells[11] = new FaithCell(11, 4);
        cells[12] = new FaithCell(12, 6);
        cells[13] = new FaithCell(13, 6);
        cells[14] = new FaithCell(14, 6);
        cells[15] = new FaithCell(15, 9);
        cells[16] = new FaithCell(16, 9);
        cells[17] = new FaithCell(17, 9);
        cells[18] = new FaithCell(18, 12);
        cells[19] = new FaithCell(19, 12);
        cells[20] = new FaithCell(20, 12);
        cells[21] = new FaithCell(21, 16);
        cells[22] = new FaithCell(22, 16);
        cells[23] = new FaithCell(23, 16);
        cells[24] = new FaithCell(24, 20);

        for(int i = 0; i < 25; i++){
            assertEquals(cells[i].getIdCell(), cellToTest[i].getIdCell());
            assertEquals(cells[i].getVictoryPoints(), cellToTest[i].getVictoryPoints());
        }
    }

    /**
     * Test to check if getVaticanReportSection works properly
     */

    @Test
    public void getVaticanReportSectionTest(){
        assertEquals(5, vaticanReportSections[0].getBegin());
        assertEquals(12, vaticanReportSections[1].getBegin());
        assertEquals(19, vaticanReportSections[2].getBegin());
        assertEquals(8, vaticanReportSections[0].getEnd());
        assertEquals(16, vaticanReportSections[1].getEnd());
        assertEquals(24, vaticanReportSections[2].getEnd());
        assertEquals(2, vaticanReportSections[0].getPoints());
        assertEquals(3, vaticanReportSections[1].getPoints());
        assertEquals(4, vaticanReportSections[2].getPoints());
    }


    /**
     * Test to check if toString works properly
     */
    @Test
    public void toStringTest() {

        FaithCell[] cells = new FaithCell[25];

        cells[21] = new FaithCell(0, 0);

        String testString = "0 0";

        assertArrayEquals(testString.toCharArray(), cells[21].toString().toCharArray());
    }


    /**Test for toView method*/
    @Test
    public void toViewTest(){
        RedFaithCell[] faithCellView = new RedFaithCell[25];

        for(int i = 0; i < 25; i++){
            faithCellView[i] = cellToTest[i].toView();
            assertEquals(cellToTest[i].getIdCell(), faithCellView[i].getIdCell());
            assertEquals(cellToTest[i].getVictoryPoints(), faithCellView[i].getVictoryPoints());
        }
    }
}
