package it.polimi.ingsw;

import static org.junit.Assert.*;

import it.polimi.ingsw.Model.*;
import it.polimi.ingsw.Model.Enums.Color;
import it.polimi.ingsw.Model.Enums.Level;
import it.polimi.ingsw.Model.Enums.ResourceType;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Unit test for DevelopmentCardDeck Class.
 */
public class DevelopmentCardDeckTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * Here a new DevelopmentCardDeck object is created along with four different DevelopmentCard objects.
     */
    ResourceStack cost = new ResourceStack(0, 1, 2, 3);
    ResourceStack input = new ResourceStack(2, 6, 13, 16);
    ResourceStack output = new ResourceStack(31, 0, 0, 0);
    DevelopmentCard card = new DevelopmentCard(Color.BLUE, Level.ONE, 1, 100, cost, input, output, 0);
    DevelopmentCard cardTest = new DevelopmentCard(Color.BLUE, Level.ONE, 1, 100, cost, input, output, 0);

    ResourceStack cost1 = new ResourceStack(10, 20, 30, 40);
    ResourceStack input1 = new ResourceStack(20, 60, 130, 160);
    ResourceStack output1 = new ResourceStack(1, 1, 1, 1);
    DevelopmentCard card1 = new DevelopmentCard(Color.BLUE, Level.ONE, 2, 10, cost1, input1, output1, 1);
    DevelopmentCard card1Test = new DevelopmentCard(Color.BLUE, Level.ONE, 2, 10, cost1, input1, output1, 1);

    ResourceStack cost2 = new ResourceStack(4, 4, 4, 4);
    ResourceStack input2 = new ResourceStack(4, 4, 4, 4);
    ResourceStack output2 = new ResourceStack(4, 4, 4, 4);
    DevelopmentCard card2 = new DevelopmentCard(Color.BLUE, Level.ONE, 3, 11, cost2, input2, output2, 2);
    DevelopmentCard card2Test = new DevelopmentCard(Color.BLUE, Level.ONE, 3, 11, cost2, input2, output2, 2);

    ResourceStack cost3 = new ResourceStack(5, 5, 5, 5);
    ResourceStack input3 = new ResourceStack(5, 5, 5, 5);
    ResourceStack output3 = new ResourceStack(5, 5, 5, 5);
    DevelopmentCard card3 = new DevelopmentCard(Color.BLUE, Level.ONE, 4, 12, cost3, input3, output3, 3);
    DevelopmentCard card3Test = new DevelopmentCard(Color.BLUE, Level.ONE, 4, 12, cost3, input3, output3, 3);

    DevelopmentCardDeck deck = new DevelopmentCardDeck(Color.BLUE, Level.ONE);

    /**
     * Constructor test for DevelopmentCardDeck Class.
     */
    @Test
    public void constructorTest() {

        assertSame(Color.BLUE, deck.getColor());
        assertSame(Level.ONE, deck.getLevel());

    }

    /**
     * Test for "addCard" method in DevelopmentCardDeck Class. It checks if cards are added correctly
     * to the deck.
     */
    @Test
    public void addCardTest() {

        deck.addCard(card);
        deck.addCard(card1);
        deck.addCard(card2);
        deck.addCard(card3);

        DevelopmentCard[] cardsTest = new DevelopmentCard[4];
        cardsTest[0] = card;
        cardsTest[1] = card1;
        cardsTest[2] = card2;
        cardsTest[3] = card3;

        assertArrayEquals(cardsTest, deck.getCards());
    }

    /**
     * Getter test for DevelopmentCardClass.
     */
    @Test
    public void getterTest() {

        deck.addCard(card);
        deck.addCard(card1);
        deck.addCard(card2);
        deck.addCard(card3);

        DevelopmentCard[] getCardTest = deck.getCards();

        assertSame(Color.BLUE, deck.getColor());
        assertSame(Level.ONE, deck.getLevel());
        assertArrayEquals(getCardTest, deck.getCards());
        assertEquals(4, deck.getCardsInDeck());
    }

    /**
     * Setter test for DevelopmentCardClass.
     */
    @Test
    public void setterTest() {

        DevelopmentCard[] cardsTest = new DevelopmentCard[4];
        cardsTest[0] = cardTest;
        cardsTest[1] = card1Test;
        cardsTest[2] = card2Test;
        cardsTest[3] = card3Test;

        deck.setCards(cardsTest);
        deck.setColor(Color.YELLOW);
        deck.setLevel(Level.TWO);

        assertSame(Color.YELLOW, deck.getColor());
        assertSame(Level.TWO, deck.getLevel());
        assertArrayEquals(cardsTest, deck.getCards());
    }

    /**
     * Test for "isEmpty" method in DevelopmentCardDeck Class. It checks if the method correctly checks
     * whether the deck is empty or not.
     */
    @Test
    public void isEmptyTest() {

        assertTrue(deck.isEmpty());
        deck.addCard(card1);
        assertFalse(deck.isEmpty());
    }

    /**
     * Test for "shuffle" method in DevelopmentCardDeck Class. It checks if the cards inside the deck
     * are correctly shuffled when calling the method.
     * The outcome of this test is to be checked manually (hence the use of a for cycle to print the
     * cards' values).
     */
    @Test
    public void shuffleTest() {

        DevelopmentCard[] cardsToPrint;

        deck.addCard(card);
        deck.addCard(card1);
        deck.addCard(card2);
        deck.addCard(card3);

        deck.shuffle();
        cardsToPrint = deck.getCards();

        for(int i = 0; i < 4; i++)
            System.out.println(cardsToPrint[i]);

        assertTrue(true);
    }

    /**
     * Test for "drawCard" method in DevelopmentCardDeck Class. It checks if the card is drawn correctly
     * and if the deck length is correctly update when calling the method.
     */
    @Test
    public void drawCardTest() {
        DevelopmentCard cardToDraw;
        deck.addCard(card);
        deck.addCard(card1);
        deck.addCard(card2);
        deck.addCard(card3);

        cardToDraw = deck.drawCard();
        assertSame(card3, cardToDraw);
        assertEquals(3, deck.getCardsInDeck());

        cardToDraw = deck.drawCard();
        assertSame(card2, cardToDraw);
        assertEquals(2, deck.getCardsInDeck());

        cardToDraw = deck.drawCard();
        assertSame(card1, cardToDraw);
        assertEquals(1, deck.getCardsInDeck());

        cardToDraw = deck.drawCard();
        assertSame(card, cardToDraw);
        assertEquals(0, deck.getCardsInDeck());

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Cannot draw card from empty deck!");
        cardToDraw = deck.drawCard();
        assertSame(card, cardToDraw);
        assertEquals(0, deck.getCardsInDeck());
    }

    /*
     * Test for "getTopCard" method in DevelopmentCardDeck Class.
     */
    @Test
    public void getTopCard() {
        DevelopmentCard cardToGet;
        deck.addCard(card);
        deck.addCard(card1);
        deck.addCard(card2);
        deck.addCard(card3);

        cardToGet = deck.getTopCard();
        assertSame(card3, cardToGet);

        DevelopmentCardDeck emptyDeck = new DevelopmentCardDeck(Color.GREEN, Level.TWO);
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Cannot get card from empty deck!");
        cardToGet = emptyDeck.getTopCard();
        assertSame(card3, cardToGet);
    }

    /**
     * Test for "toString" method in DevelopmentCardDeck Class.
     */
    @Test
    public void toStringTest() {

        String testString = "BLUE ONE";

        assertArrayEquals(testString.toCharArray(), deck.toString().toCharArray());
    }

    /**Test for toView method*/
    @Test
    public void toViewTest(){
        it.polimi.ingsw.View.ReducedModel.DevelopmentCardDeck deckView;

        deck.addCard(card);
        deck.addCard(card1);
        deck.addCard(card3);

        deckView = deck.toView();

        assertSame(Color.BLUE, deckView.getColor());
        assertSame(Level.ONE, deckView.getLevel());
        assertEquals(deck.getCards()[0].getInput().getResource(ResourceType.STONES), deckView.getCards()[0].getInput().getResource(ResourceType.STONES));
        assertEquals(deck.getCards()[1].getInput().getResource(ResourceType.STONES), deckView.getCards()[1].getInput().getResource(ResourceType.STONES));
        assertEquals(deck.getCards()[2].getCost().getResource(ResourceType.COINS), deckView.getCards()[2].getCost().getResource(ResourceType.COINS));
        assertEquals(deck.getCardsInDeck(), deckView.getCardsInDeck());
    }
}
