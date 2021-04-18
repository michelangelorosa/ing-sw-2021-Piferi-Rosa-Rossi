package it.polimi.ingsw;

import static org.junit.Assert.*;

import it.polimi.ingsw.Model.*;
import org.junit.Test;

/**
 * Unit test for DevelopmentCardTable Class.
 */
public class DevelopmentCardTableTest {
    DevelopmentCardTable table = new DevelopmentCardTable();
    ResourceStack discount1 = new ResourceStack(1,1,0,0);
    ResourceStack discount2 = new ResourceStack(0,0,1,1);
    LeaderRequirements leaderRequirements;

    LeaderCard discountCardOne;
    LeaderCard discountCardTwo;
    LeaderCard extraDepotOne;
    LeaderCard whiteMarbleOne;

    LeaderCard[] leaderCards;

    /**
     * Constructor test for DevelopmentCardTable Class.
     */
    @Test
    public void constructorTest() {
        for(int column = 0; column <= 3; column++)
            for(int row = 0; row <= 2; row++) {
                for (int i = 0; i <= 3; i++) {
                    if (table.getDeck(0, 0).getCards()[i] == null)
                        System.out.println("Error: CARD IS NULL");
                    else
                        System.out.println(table.getDeck(row, column).getCards()[i]);
                }
                System.out.println();
            }

        for(int column = 0; column <= 3; column++)
            for(int row = 0; row <= 2; row++)
                assertEquals(4, table.getDeck(row, column).getCardsInDeck());
    }

    /**
     * Getter test for DevelopmentCardTable Class.
     */
    @Test
    public void getDeckTest() {
        DevelopmentCardDeck deck = table.getDeck(0,0);
        assertEquals("45 12 GREEN THREE 4 0 4 0 0 0 0 1 1 0 3 0 0", deck.drawCard().toString());
        assertEquals("41 11 GREEN THREE 7 0 0 0 0 1 0 0 0 0 1 0 3", deck.drawCard().toString());
        assertEquals("37 10 GREEN THREE 5 2 0 0 0 1 1 0 2 0 0 2 1", deck.drawCard().toString());
        assertEquals("33 9 GREEN THREE 6 0 0 0 0 0 2 0 0 0 0 3 2", deck.drawCard().toString());

        deck = table.getDeck(1,0);
        assertEquals("29 8 GREEN TWO 3 0 3 0 0 0 1 0 2 0 0 0 1", deck.drawCard().toString());
        assertEquals("25 7 GREEN TWO 5 0 0 0 0 0 2 0 0 0 0 2 2", deck.drawCard().toString());
        assertEquals("21 6 GREEN TWO 3 2 0 0 1 1 0 0 0 0 0 3 0", deck.drawCard().toString());
        assertEquals("17 5 GREEN TWO 4 0 0 0 0 0 0 1 0 0 0 0 2", deck.drawCard().toString());

        deck = table.getDeck(1,3);
        assertEquals("30 8 PURPLE TWO 3 3 0 0 0 0 0 1 0 2 0 0 1", deck.drawCard().toString());
        assertEquals("26 7 PURPLE TWO 0 5 0 0 0 0 0 2 0 0 2 0 2", deck.drawCard().toString());
        assertEquals("22 6 PURPLE TWO 0 3 2 0 0 1 1 0 3 0 0 0 0", deck.drawCard().toString());
        assertEquals("18 5 PURPLE TWO 0 4 0 0 0 0 1 0 0 0 0 0 2", deck.drawCard().toString());

        deck = table.getDeck(2,3);
        assertEquals("14 4 PURPLE ONE 0 2 0 2 1 0 1 0 0 0 0 2 1", deck.drawCard().toString());
        assertEquals("10 3 PURPLE ONE 0 3 0 0 0 0 2 0 1 1 0 1 0", deck.drawCard().toString());
        assertEquals("6 2 PURPLE ONE 1 1 1 0 0 0 1 0 1 0 0 0 0", deck.drawCard().toString());
        assertEquals("2 1 PURPLE ONE 0 2 0 0 0 0 0 1 0 0 0 0 1", deck.drawCard().toString());

    }

    /**
     * Test for "getTopCardFromDeck" DevelopmentCardTable Class.
     */
    @Test
    public void getTopCardFromDeckTest() {
        assertEquals("45 12 GREEN THREE 4 0 4 0 0 0 0 1 1 0 3 0 0", table.getTopCardFromDeck(0,0).toString());
        assertEquals("45 12 GREEN THREE 4 0 4 0 0 0 0 1 1 0 3 0 0", table.getTopCardFromDeck(0,0).toString());
        table.getDeck(0,0).drawCard();
        assertEquals("41 11 GREEN THREE 7 0 0 0 0 1 0 0 0 0 1 0 3", table.getTopCardFromDeck(0,0).toString());

        assertEquals("29 8 GREEN TWO 3 0 3 0 0 0 1 0 2 0 0 0 1", table.getTopCardFromDeck(1,0).toString());
        assertEquals("29 8 GREEN TWO 3 0 3 0 0 0 1 0 2 0 0 0 1", table.getTopCardFromDeck(1,0).toString());
        table.getDeck(1,0).drawCard();
        assertEquals("25 7 GREEN TWO 5 0 0 0 0 0 2 0 0 0 0 2 2", table.drawCardFromDeck(1,0).toString());

        assertEquals("30 8 PURPLE TWO 3 3 0 0 0 0 0 1 0 2 0 0 1", table.getTopCardFromDeck(1,3).toString());
        table.getDeck(1,3).drawCard();
        assertEquals("26 7 PURPLE TWO 0 5 0 0 0 0 0 2 0 0 2 0 2", table.getTopCardFromDeck(1,3).toString());
        table.getDeck(1,3).drawCard();
        assertEquals("22 6 PURPLE TWO 0 3 2 0 0 1 1 0 3 0 0 0 0", table.getTopCardFromDeck(1,3).toString());
        table.getDeck(1,3).drawCard();
        assertEquals("18 5 PURPLE TWO 0 4 0 0 0 0 1 0 0 0 0 0 2", table.getTopCardFromDeck(1,3).toString());
    }

    /**
     * Test for "drawCardFromDeck" DevelopmentCardTable Class.
     */
    @Test
    public void drawCardFromDeckTest() {
        assertEquals("45 12 GREEN THREE 4 0 4 0 0 0 0 1 1 0 3 0 0", table.drawCardFromDeck(0,0).toString());
        assertEquals("41 11 GREEN THREE 7 0 0 0 0 1 0 0 0 0 1 0 3", table.drawCardFromDeck(0,0).toString());
        assertEquals("37 10 GREEN THREE 5 2 0 0 0 1 1 0 2 0 0 2 1", table.drawCardFromDeck(0,0).toString());
        assertEquals("33 9 GREEN THREE 6 0 0 0 0 0 2 0 0 0 0 3 2", table.drawCardFromDeck(0,0).toString());

        assertEquals("29 8 GREEN TWO 3 0 3 0 0 0 1 0 2 0 0 0 1", table.drawCardFromDeck(1,0).toString());
        assertEquals("25 7 GREEN TWO 5 0 0 0 0 0 2 0 0 0 0 2 2", table.drawCardFromDeck(1,0).toString());
        assertEquals("21 6 GREEN TWO 3 2 0 0 1 1 0 0 0 0 0 3 0", table.drawCardFromDeck(1,0).toString());
        assertEquals("17 5 GREEN TWO 4 0 0 0 0 0 0 1 0 0 0 0 2", table.drawCardFromDeck(1,0).toString());

        assertEquals("30 8 PURPLE TWO 3 3 0 0 0 0 0 1 0 2 0 0 1", table.drawCardFromDeck(1,3).toString());
        assertEquals("26 7 PURPLE TWO 0 5 0 0 0 0 0 2 0 0 2 0 2", table.drawCardFromDeck(1,3).toString());
        assertEquals("22 6 PURPLE TWO 0 3 2 0 0 1 1 0 3 0 0 0 0", table.drawCardFromDeck(1,3).toString());
        assertEquals("18 5 PURPLE TWO 0 4 0 0 0 0 1 0 0 0 0 0 2", table.drawCardFromDeck(1,3).toString());

        assertEquals("14 4 PURPLE ONE 0 2 0 2 1 0 1 0 0 0 0 2 1", table.drawCardFromDeck(2,3).toString());
        assertEquals("10 3 PURPLE ONE 0 3 0 0 0 0 2 0 1 1 0 1 0", table.drawCardFromDeck(2,3).toString());
        assertEquals("6 2 PURPLE ONE 1 1 1 0 0 0 1 0 1 0 0 0 0", table.drawCardFromDeck(2,3).toString());
        assertEquals("2 1 PURPLE ONE 0 2 0 0 0 0 0 1 0 0 0 0 1", table.drawCardFromDeck(2,3).toString());
    }

    /**
     * Test for "updateCardToBuyCost" method in DevelopmentCardTable Class.
     */
    @Test
    public void updateCardToBuyCost() {
        leaderRequirements = new LeaderRequirements(0,0,0,0,0,0,0,0,0,0,0,0);
        discountCardOne = new LeaderCard(1, 1, discount1, leaderRequirements, discount1);
        discountCardTwo = new LeaderCard(2, 1, discount2, leaderRequirements, discount2);
        extraDepotOne = new LeaderCard(3, 3, discount1, leaderRequirements, ResourceType.COINS);
        whiteMarbleOne = new LeaderCard(4, 4, discount1, leaderRequirements, Marble.BLUE);

        leaderCards = new LeaderCard[2];
        leaderCards[0] = extraDepotOne;
        leaderCards[1] = whiteMarbleOne;

        leaderCards[0].setActive(true);

        ResourceManager resourceManager = new ResourceManager();

        DevelopmentCard card = table.getTopCardFromDeck(0, 0);

        table.updateCardToBuyCost(card, leaderCards, resourceManager);

        assertEquals(4, resourceManager.getTemporaryResourcesToPay().getResource(ResourceType.SHIELDS));
        assertEquals(0, resourceManager.getTemporaryResourcesToPay().getResource(ResourceType.SERVANTS));
        assertEquals(4, resourceManager.getTemporaryResourcesToPay().getResource(ResourceType.COINS));
        assertEquals(0, resourceManager.getTemporaryResourcesToPay().getResource(ResourceType.STONES));

        leaderCards[0] = discountCardOne;
        leaderCards[1] = discountCardTwo;
        table.updateCardToBuyCost(card, leaderCards, resourceManager);

        assertEquals(4, resourceManager.getTemporaryResourcesToPay().getResource(ResourceType.SHIELDS));
        assertEquals(0, resourceManager.getTemporaryResourcesToPay().getResource(ResourceType.SERVANTS));
        assertEquals(4, resourceManager.getTemporaryResourcesToPay().getResource(ResourceType.COINS));
        assertEquals(0, resourceManager.getTemporaryResourcesToPay().getResource(ResourceType.STONES));

        leaderCards[0].setActive(true);
        table.updateCardToBuyCost(card, leaderCards, resourceManager);

        assertEquals(3, resourceManager.getTemporaryResourcesToPay().getResource(ResourceType.SHIELDS));
        assertEquals(0, resourceManager.getTemporaryResourcesToPay().getResource(ResourceType.SERVANTS));
        assertEquals(4, resourceManager.getTemporaryResourcesToPay().getResource(ResourceType.COINS));
        assertEquals(0, resourceManager.getTemporaryResourcesToPay().getResource(ResourceType.STONES));

        leaderCards[1].setActive(true);
        table.updateCardToBuyCost(card, leaderCards, resourceManager);

        assertEquals(3, resourceManager.getTemporaryResourcesToPay().getResource(ResourceType.SHIELDS));
        assertEquals(0, resourceManager.getTemporaryResourcesToPay().getResource(ResourceType.SERVANTS));
        assertEquals(3, resourceManager.getTemporaryResourcesToPay().getResource(ResourceType.COINS));
        assertEquals(0, resourceManager.getTemporaryResourcesToPay().getResource(ResourceType.STONES));

        discount1 = new ResourceStack(5,5,5,5);
        leaderCards[0] = new LeaderCard(1, 1, discount1, leaderRequirements, discount1);
        leaderCards[0].setActive(true);

        table.updateCardToBuyCost(card, leaderCards, resourceManager);

        assertEquals(0, resourceManager.getTemporaryResourcesToPay().getResource(ResourceType.SHIELDS));
        assertEquals(0, resourceManager.getTemporaryResourcesToPay().getResource(ResourceType.SERVANTS));
        assertEquals(0, resourceManager.getTemporaryResourcesToPay().getResource(ResourceType.COINS));
        assertEquals(0, resourceManager.getTemporaryResourcesToPay().getResource(ResourceType.STONES));
    }
}