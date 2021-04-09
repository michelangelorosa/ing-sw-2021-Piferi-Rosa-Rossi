package it.polimi.ingsw;

import it.polimi.ingsw.Model.*;
import static org.junit.Assert.*;
import org.junit.Test;

public class JSONReaderTest {

    @Test
    public void ReadDevelopmentCardsTest() {

        ResourceStack cost;
        ResourceStack input;
        ResourceStack output;
        int outputFaith;

        DevelopmentCard[] cardsToTest = JSONReader.ReadDevelopmentCards();

        DevelopmentCard[] cards = new DevelopmentCard[48];

        cost = new ResourceStack(2,0,0,0);
        input = new ResourceStack(0,0,1,0);
        output = new ResourceStack(0,0,0,0);
        outputFaith = 1;
        cards[0] = new DevelopmentCard(Color.GREEN, Level.ONE, 1, 1, cost, input, output, outputFaith);

        cost = new ResourceStack(0,2,0,0);
        input = new ResourceStack(0,0,0,1);
        output = new ResourceStack(0,0,0,0);
        outputFaith = 1;
        cards[1] = new DevelopmentCard(Color.PURPLE, Level.ONE, 2, 1, cost, input, output, outputFaith);

        cost = new ResourceStack(0,0,2,0);
        input = new ResourceStack(1,0,0,0);
        output = new ResourceStack(0,0,0,0);
        outputFaith = 1;
        cards[2] = new DevelopmentCard(Color.BLUE, Level.ONE, 3, 1, cost, input, output, outputFaith);

        cost = new ResourceStack(0,0,0,2);
        input = new ResourceStack(0,1,0,0);
        output = new ResourceStack(0,0,0,0);
        outputFaith = 1;
        cards[3] = new DevelopmentCard(Color.YELLOW, Level.ONE, 4, 1, cost, input, output, outputFaith);

        cost = new ResourceStack(1,1,0,1);
        input = new ResourceStack(0,0,0,1);
        output = new ResourceStack(0,1,0,0);
        outputFaith = 0;
        cards[4] = new DevelopmentCard(Color.GREEN, Level.ONE, 5, 2, cost, input, output, outputFaith);

        cost = new ResourceStack(1,1,1,0);
        input = new ResourceStack(0,0,1,0);
        output = new ResourceStack(1,0,0,0);
        outputFaith = 0;
        cards[5] = new DevelopmentCard(Color.PURPLE, Level.ONE, 6, 2, cost, input, output, outputFaith);

        cost = new ResourceStack(0,1,1,1);
        input = new ResourceStack(0,1,0,0);
        output = new ResourceStack(0,0,0,1);
        outputFaith = 0;
        cards[6] = new DevelopmentCard(Color.BLUE, Level.ONE, 7, 2, cost, input, output, outputFaith);

        cost = new ResourceStack(1,0,1,1);
        input = new ResourceStack(1,0,0,0);
        output = new ResourceStack(0,0,1,0);
        outputFaith = 0;
        cards[7] = new DevelopmentCard(Color.YELLOW, Level.ONE, 8, 2, cost, input, output, outputFaith);

        cost = new ResourceStack(3,0,0,0);
        input = new ResourceStack(0,2,0,0);
        output = new ResourceStack(1,0,1,1);
        outputFaith = 0;
        cards[8] = new DevelopmentCard(Color.GREEN, Level.ONE, 9, 3, cost, input, output, outputFaith);

        cost = new ResourceStack(0,3,0,0);
        input = new ResourceStack(0,0,2,0);
        output = new ResourceStack(1,1,0,1);
        outputFaith = 0;
        cards[9] = new DevelopmentCard(Color.PURPLE, Level.ONE, 10, 3, cost, input, output, outputFaith);

        cost = new ResourceStack(0,0,3,0);
        input = new ResourceStack(0,0,0,2);
        output = new ResourceStack(1,1,1,0);
        outputFaith = 0;
        cards[10] = new DevelopmentCard(Color.BLUE, Level.ONE, 11, 3, cost, input, output, outputFaith);

        cost = new ResourceStack(0,0,0,3);
        input = new ResourceStack(2,0,0,0);
        output = new ResourceStack(0,1,1,1);
        outputFaith = 0;
        cards[11] = new DevelopmentCard(Color.YELLOW, Level.ONE, 12, 3, cost, input, output, outputFaith);

        cost = new ResourceStack(2,0,2,0);
        input = new ResourceStack(0,1,0,1);
        output = new ResourceStack(0,0,2,0);
        outputFaith = 1;
        cards[12] = new DevelopmentCard(Color.GREEN, Level.ONE, 13, 4, cost, input, output, outputFaith);

        cost = new ResourceStack(0,2,0,2);
        input = new ResourceStack(1,0,1,0);
        output = new ResourceStack(0,0,0,2);
        outputFaith = 1;
        cards[13] = new DevelopmentCard(Color.PURPLE, Level.ONE, 14, 4, cost, input, output, outputFaith);

        cost = new ResourceStack(0,2,2,0);
        input = new ResourceStack(1,0,0,1);
        output = new ResourceStack(0,2,0,0);
        outputFaith = 1;
        cards[14] = new DevelopmentCard(Color.BLUE, Level.ONE, 15, 4, cost, input, output, outputFaith);

        cost = new ResourceStack(2,0,0,2);
        input = new ResourceStack(0,1,1,0);
        output = new ResourceStack(2,0,0,0);
        outputFaith = 1;
        cards[15] = new DevelopmentCard(Color.YELLOW, Level.ONE, 16, 4, cost, input, output, outputFaith);

        cost = new ResourceStack(4,0,0,0);
        input = new ResourceStack(0,0,0,1);
        output = new ResourceStack(0,0,0,0);
        outputFaith = 2;
        cards[16] = new DevelopmentCard(Color.GREEN, Level.TWO, 17, 5, cost, input, output, outputFaith);

        cost = new ResourceStack(0,4,0,0);
        input = new ResourceStack(0,0,1,0);
        output = new ResourceStack(0,0,0,0);
        outputFaith = 2;
        cards[17] = new DevelopmentCard(Color.PURPLE, Level.TWO, 18, 5, cost, input, output, outputFaith);

        cost = new ResourceStack(0,0,4,0);
        input = new ResourceStack(0,1,0,0);
        output = new ResourceStack(0,0,0,0);
        outputFaith = 2;
        cards[18] = new DevelopmentCard(Color.BLUE, Level.TWO, 19, 5, cost, input, output, outputFaith);

        cost = new ResourceStack(0,0,0,4);
        input = new ResourceStack(1,0,0,0);
        output = new ResourceStack(0,0,0,0);
        outputFaith = 2;
        cards[19] = new DevelopmentCard(Color.YELLOW, Level.TWO, 20, 5, cost, input, output, outputFaith);

        cost = new ResourceStack(3,2,0,0);
        input = new ResourceStack(1,1,0,0);
        output = new ResourceStack(0,0,0,3);
        outputFaith = 0;
        cards[20] = new DevelopmentCard(Color.GREEN, Level.TWO, 21, 6, cost, input, output, outputFaith);

        cost = new ResourceStack(0,3,2,0);
        input = new ResourceStack(0,1,1,0);
        output = new ResourceStack(3,0,0,0);
        outputFaith = 0;
        cards[21] = new DevelopmentCard(Color.PURPLE, Level.TWO, 22, 6, cost, input, output, outputFaith);

        cost = new ResourceStack(0,0,3,2);
        input = new ResourceStack(0,0,1,1);
        output = new ResourceStack(0,3,0,0);
        outputFaith = 0;
        cards[22] = new DevelopmentCard(Color.BLUE, Level.TWO, 23, 6, cost, input, output, outputFaith);

        cost = new ResourceStack(2,0,0,3);
        input = new ResourceStack(1,0,0,1);
        output = new ResourceStack(0,0,3,0);
        outputFaith = 0;
        cards[23] = new DevelopmentCard(Color.YELLOW, Level.TWO, 24, 6, cost, input, output, outputFaith);

        cost = new ResourceStack(5,0,0,0);
        input = new ResourceStack(0,0,2,0);
        output = new ResourceStack(0,0,0,2);
        outputFaith = 2;
        cards[24] = new DevelopmentCard(Color.GREEN, Level.TWO, 25, 7, cost, input, output, outputFaith);

        cost = new ResourceStack(0,5,0,0);
        input = new ResourceStack(0,0,0,2);
        output = new ResourceStack(0,0,2,0);
        outputFaith = 2;
        cards[25] = new DevelopmentCard(Color.PURPLE, Level.TWO, 26, 7, cost, input, output, outputFaith);

        cost = new ResourceStack(0,0,5,0);
        input = new ResourceStack(0,2,0,0);
        output = new ResourceStack(2,0,0,0);
        outputFaith = 2;
        cards[26] = new DevelopmentCard(Color.BLUE, Level.TWO, 27, 7, cost, input, output, outputFaith);

        cost = new ResourceStack(0,0,0,5);
        input = new ResourceStack(2,0,0,0);
        output = new ResourceStack(0,2,0,0);
        outputFaith = 2;
        cards[27] = new DevelopmentCard(Color.YELLOW, Level.TWO, 28, 7, cost, input, output, outputFaith);

        cost = new ResourceStack(3,0,3,0);
        input = new ResourceStack(0,0,1,0);
        output = new ResourceStack(2,0,0,0);
        outputFaith = 1;
        cards[28] = new DevelopmentCard(Color.GREEN, Level.TWO, 29, 8, cost, input, output, outputFaith);

        cost = new ResourceStack(3,3,0,0);
        input = new ResourceStack(0,0,0,1);
        output = new ResourceStack(0,2,0,0);
        outputFaith = 1;
        cards[29] = new DevelopmentCard(Color.PURPLE, Level.TWO, 30, 8, cost, input, output, outputFaith);

        cost = new ResourceStack(0,0,3,3);
        input = new ResourceStack(0,1,0,0);
        output = new ResourceStack(0,0,0,2);
        outputFaith = 1;
        cards[30] = new DevelopmentCard(Color.BLUE, Level.TWO, 31, 8, cost, input, output, outputFaith);

        cost = new ResourceStack(0,3,0,3);
        input = new ResourceStack(1,0,0,0);
        output = new ResourceStack(0,0,2,0);
        outputFaith = 1;
        cards[31] = new DevelopmentCard(Color.YELLOW, Level.TWO, 32, 8, cost, input, output, outputFaith);

        cost = new ResourceStack(6,0,0,0);
        input = new ResourceStack(0,0,2,0);
        output = new ResourceStack(0,0,0,3);
        outputFaith = 2;
        cards[32] = new DevelopmentCard(Color.GREEN, Level.THREE, 33, 9, cost, input, output, outputFaith);

        cost = new ResourceStack(0,6,0,0);
        input = new ResourceStack(0,0,0,2);
        output = new ResourceStack(0,0,3,0);
        outputFaith = 2;
        cards[33] = new DevelopmentCard(Color.PURPLE, Level.THREE, 34, 9, cost, input, output, outputFaith);

        cost = new ResourceStack(0,0,6,0);
        input = new ResourceStack(0,2,0,0);
        output = new ResourceStack(3,0,0,0);
        outputFaith = 2;
        cards[34] = new DevelopmentCard(Color.BLUE, Level.THREE, 35, 9, cost, input, output, outputFaith);

        cost = new ResourceStack(0,0,0,6);
        input = new ResourceStack(2,0,0,0);
        output = new ResourceStack(0,3,0,0);
        outputFaith = 2;
        cards[35] = new DevelopmentCard(Color.YELLOW, Level.THREE, 36, 9, cost, input, output, outputFaith);

        cost = new ResourceStack(5,2,0,0);
        input = new ResourceStack(0,1,1,0);
        output = new ResourceStack(2,0,0,2);
        outputFaith = 1;
        cards[36] = new DevelopmentCard(Color.GREEN, Level.THREE, 37, 10, cost, input, output, outputFaith);

        cost = new ResourceStack(0,5,2,0);
        input = new ResourceStack(1,0,0,1);
        output = new ResourceStack(0,2,2,0);
        outputFaith = 1;
        cards[37] = new DevelopmentCard(Color.PURPLE, Level.THREE, 38, 10, cost, input, output, outputFaith);

        cost = new ResourceStack(0,0,5,2);
        input = new ResourceStack(1,0,1,0);
        output = new ResourceStack(0,2,0,2);
        outputFaith = 1;
        cards[38] = new DevelopmentCard(Color.BLUE, Level.THREE, 39, 10, cost, input, output, outputFaith);

        cost = new ResourceStack(0,2,0,5);
        input = new ResourceStack(0,1,0,1);
        output = new ResourceStack(2,0,2,0);
        outputFaith = 1;
        cards[39] = new DevelopmentCard(Color.YELLOW, Level.THREE, 40, 10, cost, input, output, outputFaith);

        cost = new ResourceStack(7,0,0,0);
        input = new ResourceStack(0,1,0,0);
        output = new ResourceStack(0,0,1,0);
        outputFaith = 3;
        cards[40] = new DevelopmentCard(Color.GREEN, Level.THREE, 41, 11, cost, input, output, outputFaith);

        cost = new ResourceStack(0,7,0,0);
        input = new ResourceStack(0,0,1,0);
        output = new ResourceStack(0,0,0,1);
        outputFaith = 3;
        cards[41] = new DevelopmentCard(Color.PURPLE, Level.THREE, 42, 11, cost, input, output, outputFaith);

        cost = new ResourceStack(0,0,7,0);
        input = new ResourceStack(0,0,0,1);
        output = new ResourceStack(1,0,0,0);
        outputFaith = 3;
        cards[42] = new DevelopmentCard(Color.BLUE, Level.THREE, 43, 11, cost, input, output, outputFaith);

        cost = new ResourceStack(0,0,0,7);
        input = new ResourceStack(1,0,0,0);
        output = new ResourceStack(0,1,0,0);
        outputFaith = 3;
        cards[43] = new DevelopmentCard(Color.YELLOW, Level.THREE, 44, 11, cost, input, output, outputFaith);

        cost = new ResourceStack(4,0,4,0);
        input = new ResourceStack(0,0,0,1);
        output = new ResourceStack(1,0,3,0);
        outputFaith = 0;
        cards[44] = new DevelopmentCard(Color.GREEN, Level.THREE, 45, 12, cost, input, output, outputFaith);

        cost = new ResourceStack(4,4,0,0);
        input = new ResourceStack(0,0,1,0);
        output = new ResourceStack(0,1,0,3);
        outputFaith = 0;
        cards[45] = new DevelopmentCard(Color.PURPLE, Level.THREE, 46, 12, cost, input, output, outputFaith);

        cost = new ResourceStack(0,0,4,4);
        input = new ResourceStack(0,1,0,0);
        output = new ResourceStack(3,0,1,0);
        outputFaith = 0;
        cards[46] = new DevelopmentCard(Color.BLUE, Level.THREE, 47, 12, cost, input, output, outputFaith);

        cost = new ResourceStack(0,4,0,4);
        input = new ResourceStack(1,0,0,0);
        output = new ResourceStack(0,3,0,1);
        outputFaith = 0;
        cards[47] = new DevelopmentCard(Color.YELLOW, Level.THREE, 48, 12, cost, input, output, outputFaith);


        for(int i = 0; i < 48; i++){
            assertEquals(cards[i].toString(), cardsToTest[i].toString());
        }
    }

    @Test
    public void ReadFaithCellsTest() {

        FaithCell[] cellToTest = JSONReader.ReadFaithCells();

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
            assertEquals(cells[i].toString(), cellToTest[i].toString());
        }
    }
}