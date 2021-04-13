package it.polimi.ingsw;

import static org.junit.Assert.*;

import it.polimi.ingsw.Model.*;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ConvertToJSONTest {

    ConvertToJSON test = new ConvertToJSON();

    /**
     * This test is used to see if the method "convertFaith" works properly by saving all the cells in one array from
     * the file FaithTrack.json.
     * @throws FileNotFoundException if the File doesn't exists.
     */
    @Test
    public void convertFaithTest() throws FileNotFoundException {
        FaithCell[] cell = JSONReader.ReadFaithCells();
        test.covertFaith(cell);
    }

    /**
     * This test is used to see if the method "convertLeaderCard" works properly by saving all the cards in one arrayList from
     * the file LeaderCards.json.
     * @throws FileNotFoundException if the File doesn't exists.
     */
    @Test
    public void convertLeaderCardTest() throws FileNotFoundException {

        ArrayList<LeaderCard> LeaderCards = JSONReader.ReadLeaderCards();

        test.covertLeaderCard(LeaderCards);
    }

    /**
     * This test is used to see if the method "convertDevelopmentCards" works properly by saving all the cards in one array from
     * the file DevelopmentCards.json.
     * @throws FileNotFoundException if the File doesn't exists.
     */
    @Test
    public void convertDevelopmentCardTest() throws FileNotFoundException {
        DevelopmentCard[] cards = JSONReader.ReadDevelopmentCards();
        test.covertDevelopmentCard(cards);
    }


}
