package it.polimi.ingsw;

import it.polimi.ingsw.Controller.Controller;
import it.polimi.ingsw.Model.*;
import static org.junit.Assert.*;

import it.polimi.ingsw.Model.Enums.*;
import org.junit.Test;

import java.util.ArrayList;

public class JSONReaderTest {

    @Test
    public void constructorTest() {
        Controller controller = new Controller();
    }

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
        VaticanReportSection[] vaticanReportSections = new VaticanReportSection[3];

        vaticanReportSections[0] = new VaticanReportSection(5, 8, 2);
        vaticanReportSections[1] = new VaticanReportSection(12, 16, 3);
        vaticanReportSections[2] = new VaticanReportSection(19, 24, 4);


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

    @Test
    public void ReadPlayers() {

        ArrayList<Player> players = JSONReader.ReadPlayersName();

        System.out.println(players.get(0).getNickname());

        System.out.println(players.get(1).getPopeTiles()[0].toCli());
    }



    @Test
    public void ReadLeaderCardTest(){

        ArrayList<LeaderCard> LeaderCards = JSONReader.ReadLeaderCards();

        LeaderCard[] cardsToTest = new LeaderCard[16];

        ResourceStack resourcesRequired = new ResourceStack(0,0,0,0);
        LeaderRequirements cards = new LeaderRequirements(0,0,1,1,0,0,0,0,0,0,0,0);
        ResourceStack discount = new ResourceStack(0,1,0,0);
        cardsToTest[0] = new LeaderCard(49, 2, resourcesRequired, cards, discount);

        resourcesRequired = new ResourceStack(0,0,0,0);
        cards = new LeaderRequirements(1,1,0,0,0,0,0,0,0,0,0,0);
        discount = new ResourceStack(1,0,0,0);
        cardsToTest[1] = new LeaderCard(50, 2, resourcesRequired, cards, discount);

        resourcesRequired = new ResourceStack(0,0,0,0);
        cards = new LeaderRequirements(1,0,0,1,0,0,0,0,0,0,0,0);
        discount = new ResourceStack(0,0,0,1);
        cardsToTest[2] = new LeaderCard(51, 2, resourcesRequired, cards, discount);

        resourcesRequired = new ResourceStack(0,0,0,0);
        cards = new LeaderRequirements(0,1,1,0,0,0,0,0,0,0,0,0);
        discount = new ResourceStack(0,0,1,0);
        cardsToTest[3] = new LeaderCard(52, 2, resourcesRequired, cards, discount);

        resourcesRequired = new ResourceStack(0,0,5,0);
        cards = new LeaderRequirements(0,0,0,0,0,0,0,0,0,0,0,0);
        ResourceType type = ResourceType.STONES;
        cardsToTest[4] = new LeaderCard(53, 3, resourcesRequired, cards, type);

        resourcesRequired = new ResourceStack(0,0,0,5);
        cards = new LeaderRequirements(0,0,0,0,0,0,0,0,0,0,0,0);
        type = ResourceType.SERVANTS;
        cardsToTest[5] = new LeaderCard(54, 3, resourcesRequired, cards, type);

        resourcesRequired = new ResourceStack(0,5,0,0);
        cards = new LeaderRequirements(0,0,0,0,0,0,0,0,0,0,0,0);
        type = ResourceType.SHIELDS;
        cardsToTest[6] = new LeaderCard(55, 3, resourcesRequired, cards, type);

        resourcesRequired = new ResourceStack(5,0,0,0);
        cards = new LeaderRequirements(0,0,0,0,0,0,0,0,0,0,0,0);
        type = ResourceType.COINS;
        cardsToTest[7] = new LeaderCard(56, 3, resourcesRequired, cards, type);

        resourcesRequired = new ResourceStack(0,0,0,0);
        cards = new LeaderRequirements(1,0,2,0,0,0,0,0,0,0,0,0);
        Marble marble = Marble.PURPLE;
        cardsToTest[8] = new LeaderCard(57, 5, resourcesRequired, cards, marble);

        resourcesRequired = new ResourceStack(0,0,0,0);
        cards = new LeaderRequirements(0,1,0,2,0,0,0,0,0,0,0,0);
        marble = Marble.BLUE;
        cardsToTest[9] = new LeaderCard(58, 5, resourcesRequired, cards, marble);

        resourcesRequired = new ResourceStack(0,0,0,0);
        cards = new LeaderRequirements(2,0,1,0,0,0,0,0,0,0,0,0);
        marble = Marble.GREY;
        cardsToTest[10] = new LeaderCard(59, 5, resourcesRequired, cards, marble);

        resourcesRequired = new ResourceStack(0,0,0,0);
        cards = new LeaderRequirements(0,2,0,1,0,0,0,0,0,0,0,0);
        marble = Marble.YELLOW;
        cardsToTest[11] = new LeaderCard(60, 5, resourcesRequired, cards, marble);

        resourcesRequired = new ResourceStack(0,0,0,0);
        cards = new LeaderRequirements(0,0,0,0,0,0,1,0,0,0,0,0);
        ResourceStack input = new ResourceStack(1,0,0,0);
        int jollyOut = 1;
        int faithOut = 1;
        cardsToTest[12] = new LeaderCard(61, 4, resourcesRequired, cards, input, jollyOut, faithOut);

        resourcesRequired = new ResourceStack(0,0,0,0);
        cards = new LeaderRequirements(0,0,0,0,1,0,0,0,0,0,0,0);
        input = new ResourceStack(0,1,0,0);
        jollyOut = 1;
        faithOut = 1;
        cardsToTest[13] = new LeaderCard(62, 4, resourcesRequired, cards, input, jollyOut, faithOut);

        resourcesRequired = new ResourceStack(0,0,0,0);
        cards = new LeaderRequirements(0,0,0,0,0,1,0,0,0,0,0,0);
        input = new ResourceStack(0,0,0,1);
        jollyOut = 1;
        faithOut = 1;
        cardsToTest[14] = new LeaderCard(63, 4, resourcesRequired, cards, input, jollyOut, faithOut);

        resourcesRequired = new ResourceStack(0,0,0,0);
        cards = new LeaderRequirements(0,0,0,0,0,0,0,1,0,0,0,0);
        input = new ResourceStack(0,0,1,0);
        jollyOut = 1;
        faithOut = 1;
        cardsToTest[15] = new LeaderCard(64, 4, resourcesRequired, cards, input, jollyOut, faithOut);

        for(int i = 0; i < 16; i++){
            assertEquals(cardsToTest[i].getCardId(), LeaderCards.get(i).getCardId());
            assertEquals(cardsToTest[i].getVictoryPoints(), LeaderCards.get(i).getVictoryPoints());
            assertEquals(cardsToTest[i].getAction(), LeaderCards.get(i).getAction());
            assertEquals(cardsToTest[i].getFaith(), LeaderCards.get(i).getFaith());
            assertEquals(cardsToTest[i].getCardsRequired().getBlueCardLv1(), LeaderCards.get(i).getCardsRequired().getBlueCardLv1());
            assertEquals(cardsToTest[i].getCardsRequired().getBlueCardLv2(), LeaderCards.get(i).getCardsRequired().getBlueCardLv2());
            assertEquals(cardsToTest[i].getCardsRequired().getBlueCardLv3(), LeaderCards.get(i).getCardsRequired().getBlueCardLv3());
            assertEquals(cardsToTest[i].getCardsRequired().getPurpleCardLv1(), LeaderCards.get(i).getCardsRequired().getPurpleCardLv1());
            assertEquals(cardsToTest[i].getCardsRequired().getPurpleCardLv2(), LeaderCards.get(i).getCardsRequired().getPurpleCardLv2());
            assertEquals(cardsToTest[i].getCardsRequired().getPurpleCardLv3(), LeaderCards.get(i).getCardsRequired().getPurpleCardLv3());
            assertEquals(cardsToTest[i].getCardsRequired().getYellowCardLv1(), LeaderCards.get(i).getCardsRequired().getYellowCardLv1());
            assertEquals(cardsToTest[i].getCardsRequired().getYellowCardLv2(), LeaderCards.get(i).getCardsRequired().getYellowCardLv2());
            assertEquals(cardsToTest[i].getCardsRequired().getYellowCardLv3(), LeaderCards.get(i).getCardsRequired().getYellowCardLv3());
            assertEquals(cardsToTest[i].getCardsRequired().getGreenCardLv1(), LeaderCards.get(i).getCardsRequired().getGreenCardLv1());
            assertEquals(cardsToTest[i].getCardsRequired().getGreenCardLv2(), LeaderCards.get(i).getCardsRequired().getGreenCardLv2());
            assertEquals(cardsToTest[i].getCardsRequired().getGreenCardLv3(), LeaderCards.get(i).getCardsRequired().getGreenCardLv3());
            if (i < 4) {
                assertEquals(cardsToTest[i].getDiscount().getResource(ResourceType.SHIELDS), LeaderCards.get(i).getDiscount().getResource(ResourceType.SHIELDS));
                assertEquals(cardsToTest[i].getDiscount().getResource(ResourceType.SERVANTS), LeaderCards.get(i).getDiscount().getResource(ResourceType.SERVANTS));
                assertEquals(cardsToTest[i].getDiscount().getResource(ResourceType.COINS), LeaderCards.get(i).getDiscount().getResource(ResourceType.COINS));
                assertEquals(cardsToTest[i].getDiscount().getResource(ResourceType.STONES), LeaderCards.get(i).getDiscount().getResource(ResourceType.STONES));
            }

            if (i > 11) {

                assertEquals(cardsToTest[i].getInput().getResource(ResourceType.SHIELDS), LeaderCards.get(i).getInput().getResource(ResourceType.SHIELDS));
                assertEquals(cardsToTest[i].getInput().getResource(ResourceType.SERVANTS), LeaderCards.get(i).getInput().getResource(ResourceType.SERVANTS));
                assertEquals(cardsToTest[i].getInput().getResource(ResourceType.COINS), LeaderCards.get(i).getInput().getResource(ResourceType.COINS));
                assertEquals(cardsToTest[i].getInput().getResource(ResourceType.STONES), LeaderCards.get(i).getInput().getResource(ResourceType.STONES));
            }
            if(i > 7 && i < 12){
                assertEquals(cardsToTest[i].getMarble(), LeaderCards.get(i).getMarble());
            }
            if(i > 3 && i < 8){
                assertEquals(cardsToTest[i].getResource(), LeaderCards.get(i).getResource());
            }
        }

        //int size = LeaderCards.size();
        //System.out.println(size);
    }
}