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
        DevelopmentCardDeck deck = table.getDeck(0,0);
        assertEquals("45 12 GREEN THREE 4 0 4 0 0 0 0 1 1 0 3 0 0", table.getTopCardFromDeck(0,0).toString());
        deck.drawCard();
        assertEquals("41 11 GREEN THREE 7 0 0 0 0 1 0 0 0 0 1 0 3", table.getTopCardFromDeck(0,0).toString());
        deck.drawCard();
        assertEquals("37 10 GREEN THREE 5 2 0 0 0 1 1 0 2 0 0 2 1", table.getTopCardFromDeck(0,0).toString());
        deck.drawCard();
        assertEquals("33 9 GREEN THREE 6 0 0 0 0 0 2 0 0 0 0 3 2", table.getTopCardFromDeck(0,0).toString());

        deck = table.getDeck(1,0);
        assertEquals("29 8 GREEN TWO 3 0 3 0 0 0 1 0 2 0 0 0 1", table.getTopCardFromDeck(1,0).toString());
        deck.drawCard();
        assertEquals("25 7 GREEN TWO 5 0 0 0 0 0 2 0 0 0 0 2 2", table.getTopCardFromDeck(1,0).toString());
        deck.drawCard();
        assertEquals("21 6 GREEN TWO 3 2 0 0 1 1 0 0 0 0 0 3 0", table.getTopCardFromDeck(1,0).toString());
        deck.drawCard();
        assertEquals("17 5 GREEN TWO 4 0 0 0 0 0 0 1 0 0 0 0 2", table.getTopCardFromDeck(1,0).toString());

        deck = table.getDeck(1,3);
        assertEquals("30 8 PURPLE TWO 3 3 0 0 0 0 0 1 0 2 0 0 1", table.getTopCardFromDeck(1,3).toString());
        deck.drawCard();
        assertEquals("26 7 PURPLE TWO 0 5 0 0 0 0 0 2 0 0 2 0 2", table.getTopCardFromDeck(1,3).toString());
        deck.drawCard();
        assertEquals("22 6 PURPLE TWO 0 3 2 0 0 1 1 0 3 0 0 0 0", table.getTopCardFromDeck(1,3).toString());
        deck.drawCard();
        assertEquals("18 5 PURPLE TWO 0 4 0 0 0 0 1 0 0 0 0 0 2", table.getTopCardFromDeck(1,3).toString());

        deck = table.getDeck(2,3);
        assertEquals("14 4 PURPLE ONE 0 2 0 2 1 0 1 0 0 0 0 2 1", table.getTopCardFromDeck(2,3).toString());
        deck.drawCard();
        assertEquals("10 3 PURPLE ONE 0 3 0 0 0 0 2 0 1 1 0 1 0", table.getTopCardFromDeck(2,3).toString());
        deck.drawCard();
        assertEquals("6 2 PURPLE ONE 1 1 1 0 0 0 1 0 1 0 0 0 0", table.getTopCardFromDeck(2,3).toString());
        deck.drawCard();
        assertEquals("2 1 PURPLE ONE 0 2 0 0 0 0 0 1 0 0 0 0 1", table.getTopCardFromDeck(2,3).toString());

    }

    /**
     * Test for "buyCard" method in DevelopmentCardTable Class.
     */
    @Test
    public void buyCardTest() {
        leaderRequirements = new LeaderRequirements(0,0,0,0,0,0,0,0,0,0,0,0);
        discountCardOne = new LeaderCard(1, 1, discount1, leaderRequirements, discount1);
        discountCardTwo = new LeaderCard(2, 1, discount2, leaderRequirements, discount2);
        extraDepotOne = new LeaderCard(3, 3, discount1, leaderRequirements, ResourceType.COINS);
        whiteMarbleOne = new LeaderCard(4, 4, discount1, leaderRequirements, Marble.BLUE);

        leaderCards = new LeaderCard[2];
        leaderCards[0] = extraDepotOne;
        leaderCards[1] = whiteMarbleOne;

        ResourceManager resourceManager = new ResourceManager();
        ResourceStack resourceStack = new ResourceStack(0, 1, 2, 3);
        resourceManager.addMarketResourcesByType(3, ResourceType.COINS, resourceManager.getWarehouse().getWarehouseDepots()[0]);
        resourceManager.addMarketResourcesByType(2, ResourceType.STONES, resourceManager.getWarehouse().getWarehouseDepots()[1]);

        resourceManager.addProductionResources(resourceStack);

        table.buyCard(table.getDeck(1,3), resourceManager, leaderCards);

        assertEquals(0, resourceManager.countAllResourcesByType(ResourceType.SHIELDS));
        assertEquals(1, resourceManager.countAllResourcesByType(ResourceType.SERVANTS));
        assertEquals(5, resourceManager.countAllResourcesByType(ResourceType.COINS));
        assertEquals(5, resourceManager.countAllResourcesByType(ResourceType.STONES));

        resourceManager.addMarketResourcesByType(1, ResourceType.SERVANTS, resourceManager.getWarehouse().getWarehouseDepots()[2]);

        assertEquals("14 4 PURPLE ONE 0 2 0 2 1 0 1 0 0 0 0 2 1", table.getDeck(2,3).getCards()[3].toString());
        DevelopmentCard card = table.buyCard(table.getDeck(2,3), resourceManager, leaderCards);

        assertEquals(0, resourceManager.countAllResourcesByType(ResourceType.SHIELDS));
        assertEquals(0, resourceManager.countAllResourcesByType(ResourceType.SERVANTS));
        assertEquals(5, resourceManager.countAllResourcesByType(ResourceType.COINS));
        assertEquals(3, resourceManager.countAllResourcesByType(ResourceType.STONES));
        assertEquals("14 4 PURPLE ONE 0 2 0 2 1 0 1 0 0 0 0 2 1", card.toString());

        card = table.buyCard(table.getDeck(2,3), resourceManager, leaderCards);
        assertEquals(0, resourceManager.countAllResourcesByType(ResourceType.SHIELDS));
        assertEquals(0, resourceManager.countAllResourcesByType(ResourceType.SERVANTS));
        assertEquals(5, resourceManager.countAllResourcesByType(ResourceType.COINS));
        assertEquals(3, resourceManager.countAllResourcesByType(ResourceType.STONES));
        assertNull(card);

        resourceStack = new ResourceStack(0, 4, 0, 0);
        resourceManager.addProductionResources(resourceStack);

        card = table.buyCard(table.getDeck(2,3), resourceManager, leaderCards);

        assertEquals(0, resourceManager.countAllResourcesByType(ResourceType.SHIELDS));
        assertEquals(1, resourceManager.countAllResourcesByType(ResourceType.SERVANTS));
        assertEquals(5, resourceManager.countAllResourcesByType(ResourceType.COINS));
        assertEquals(3, resourceManager.countAllResourcesByType(ResourceType.STONES));
        assertEquals("10 3 PURPLE ONE 0 3 0 0 0 0 2 0 1 1 0 1 0", card.toString());

        resourceStack = new ResourceStack(1, 2, 1, 1);
        resourceManager.addProductionResources(resourceStack);

        card = table.buyCard(table.getDeck(2,3), resourceManager, leaderCards);

        assertEquals(0, resourceManager.countAllResourcesByType(ResourceType.SHIELDS));
        assertEquals(2, resourceManager.countAllResourcesByType(ResourceType.SERVANTS));
        assertEquals(5, resourceManager.countAllResourcesByType(ResourceType.COINS));
        assertEquals(4, resourceManager.countAllResourcesByType(ResourceType.STONES));
        assertEquals("6 2 PURPLE ONE 1 1 1 0 0 0 1 0 1 0 0 0 0", card.toString());

        card = table.buyCard(table.getDeck(2,3), resourceManager, leaderCards);

        assertEquals(0, resourceManager.countAllResourcesByType(ResourceType.SHIELDS));
        assertEquals(0, resourceManager.countAllResourcesByType(ResourceType.SERVANTS));
        assertEquals(5, resourceManager.countAllResourcesByType(ResourceType.COINS));
        assertEquals(4, resourceManager.countAllResourcesByType(ResourceType.STONES));
        assertEquals("2 1 PURPLE ONE 0 2 0 0 0 0 0 1 0 0 0 0 1", card.toString());

        card = table.buyCard(table.getDeck(2,3), resourceManager, leaderCards);
        assertEquals(0, resourceManager.countAllResourcesByType(ResourceType.SHIELDS));
        assertEquals(0, resourceManager.countAllResourcesByType(ResourceType.SERVANTS));
        assertEquals(5, resourceManager.countAllResourcesByType(ResourceType.COINS));
        assertEquals(4, resourceManager.countAllResourcesByType(ResourceType.STONES));
        assertNull(card);

        card = table.buyCard(table.getDeck(2,3), resourceManager, leaderCards);
        assertEquals(0, resourceManager.countAllResourcesByType(ResourceType.SHIELDS));
        assertEquals(0, resourceManager.countAllResourcesByType(ResourceType.SERVANTS));
        assertEquals(5, resourceManager.countAllResourcesByType(ResourceType.COINS));
        assertEquals(4, resourceManager.countAllResourcesByType(ResourceType.STONES));
        assertNull(card);

        card = table.buyCard(table.getDeck(2,3), resourceManager, leaderCards);
        assertEquals(0, resourceManager.countAllResourcesByType(ResourceType.SHIELDS));
        assertEquals(0, resourceManager.countAllResourcesByType(ResourceType.SERVANTS));
        assertEquals(5, resourceManager.countAllResourcesByType(ResourceType.COINS));
        assertEquals(4, resourceManager.countAllResourcesByType(ResourceType.STONES));
        assertNull(card);

        ResourceStack cardCost = new ResourceStack(3,2,0,0);
        DevelopmentCard cardToBuy = new DevelopmentCard(Color.GREEN,Level.TWO, 29, 8, cardCost, cardCost, cardCost, 1);
        DevelopmentCardDeck deck = new DevelopmentCardDeck(Color.GREEN, Level.TWO);
        deck.addCard(cardToBuy);

        leaderCards = new LeaderCard[2];
        leaderCards[0] = discountCardOne;
        leaderCards[1] = discountCardTwo;

        leaderCards[0].setActive(false);
        leaderCards[1].setActive(false);

        resourceStack = new ResourceStack(1, 1, 0, 0);
        resourceManager.reset();
        resourceManager.addMarketResourcesByType(1, ResourceType.COINS, resourceManager.getWarehouse().getWarehouseDepots()[0]);
        resourceManager.addMarketResourcesByType(1, ResourceType.STONES, resourceManager.getWarehouse().getWarehouseDepots()[1]);
        resourceManager.addMarketResourcesByType(1, ResourceType.SHIELDS, resourceManager.getWarehouse().getWarehouseDepots()[2]);

        resourceManager.addProductionResources(resourceStack);

        card = table.buyCard(deck, resourceManager, leaderCards);
        assertEquals(2, resourceManager.countAllResourcesByType(ResourceType.SHIELDS));
        assertEquals(1, resourceManager.countAllResourcesByType(ResourceType.SERVANTS));
        assertEquals(1, resourceManager.countAllResourcesByType(ResourceType.COINS));
        assertEquals(1, resourceManager.countAllResourcesByType(ResourceType.STONES));
        assertNull(card);

        leaderCards[0].setActive(true);

        card = table.buyCard(deck, resourceManager, leaderCards);
        assertEquals(0, resourceManager.countAllResourcesByType(ResourceType.SHIELDS));
        assertEquals(0, resourceManager.countAllResourcesByType(ResourceType.SERVANTS));
        assertEquals(1, resourceManager.countAllResourcesByType(ResourceType.COINS));
        assertEquals(1, resourceManager.countAllResourcesByType(ResourceType.STONES));
        assertEquals("29 8 GREEN TWO 3 2 0 0 3 2 0 0 3 2 0 0 1", card.toString());

        cardCost = new ResourceStack(1,1,0,0);
        cardToBuy = new DevelopmentCard(Color.GREEN,Level.TWO, 29, 8, cardCost, cardCost, cardCost, 1);
        deck.addCard(cardToBuy);

        ResourceStack cardCost2 = new ResourceStack(0,0,2,1);
        DevelopmentCard cardToBuy2 = new DevelopmentCard(Color.GREEN,Level.TWO, 29, 8, cardCost2, cardCost, cardCost, 1);
        deck.addCard(cardToBuy2);

        card = table.buyCard(deck, resourceManager, leaderCards);
        assertEquals(0, resourceManager.countAllResourcesByType(ResourceType.SHIELDS));
        assertEquals(0, resourceManager.countAllResourcesByType(ResourceType.SERVANTS));
        assertEquals(1, resourceManager.countAllResourcesByType(ResourceType.COINS));
        assertEquals(1, resourceManager.countAllResourcesByType(ResourceType.STONES));
        assertNull(card);

        leaderCards[1].setActive(true);

        card = table.buyCard(deck, resourceManager, leaderCards);
        assertEquals(0, resourceManager.countAllResourcesByType(ResourceType.SHIELDS));
        assertEquals(0, resourceManager.countAllResourcesByType(ResourceType.SERVANTS));
        assertEquals(0, resourceManager.countAllResourcesByType(ResourceType.COINS));
        assertEquals(1, resourceManager.countAllResourcesByType(ResourceType.STONES));
        assertEquals("29 8 GREEN TWO 0 0 2 1 1 1 0 0 1 1 0 0 1", card.toString());

        card = table.buyCard(deck, resourceManager, leaderCards);
        assertEquals(0, resourceManager.countAllResourcesByType(ResourceType.SHIELDS));
        assertEquals(0, resourceManager.countAllResourcesByType(ResourceType.SERVANTS));
        assertEquals(0, resourceManager.countAllResourcesByType(ResourceType.COINS));
        assertEquals(1, resourceManager.countAllResourcesByType(ResourceType.STONES));
        assertEquals("29 8 GREEN TWO 1 1 0 0 1 1 0 0 1 1 0 0 1", card.toString());
    }
}
