
package it.polimi.ingsw.Model;

import java.util.ArrayList;

/**
 * DevelopmentCardTable Class contains all 12 Development Card decks available for the player to buy.
 * All decks are stored in a 2D array of DevelopmentCardDeck-type object.
 */
public class DevelopmentCardTable {

    private final DevelopmentCardDeck[][] decks;

    /**
     * Constructor for DevelopmentCardTable Class.
     * <p>
     * This method uses "JSONReader" Class to read all cards from a .json file. Development Cards are then
     * stored inside the 2D array containing all decks.
     */
    public DevelopmentCardTable() {
        DevelopmentCard[] cards = JSONReader.ReadDevelopmentCards();
        int cardsInDeck = 0;

        Color[] colors = Color.values();
        Level[] levels = Level.values();

        decks = new DevelopmentCardDeck[3][4];

        decks[0][0] = new DevelopmentCardDeck(Color.GREEN, Level.THREE);
        decks[0][1] = new DevelopmentCardDeck(Color.BLUE, Level.THREE);
        decks[0][2] = new DevelopmentCardDeck(Color.YELLOW, Level.THREE);
        decks[0][3] = new DevelopmentCardDeck(Color.PURPLE, Level.THREE);

        decks[1][0] = new DevelopmentCardDeck(Color.GREEN, Level.TWO);
        decks[1][1] = new DevelopmentCardDeck(Color.BLUE, Level.TWO);
        decks[1][2] = new DevelopmentCardDeck(Color.YELLOW, Level.TWO);
        decks[1][3] = new DevelopmentCardDeck(Color.PURPLE, Level.TWO);

        decks[2][0] = new DevelopmentCardDeck(Color.GREEN, Level.ONE);
        decks[2][1] = new DevelopmentCardDeck(Color.BLUE, Level.ONE);
        decks[2][2] = new DevelopmentCardDeck(Color.YELLOW, Level.ONE);
        decks[2][3] = new DevelopmentCardDeck(Color.PURPLE, Level.ONE);

        for(DevelopmentCard card : cards) {
            if(card.getColor() == colors[0]) {
                if(card.getLevel() == levels[0]) {
                    decks[2][1].addCard(card);

                } else if(card.getLevel() == levels[1]) {
                    decks[1][1].addCard(card);

                } else if(card.getLevel() == levels[2]) {
                    decks[0][1].addCard(card);
                }
                cardsInDeck ++;

            } else if(card.getColor() == colors[1]) {
                if(card.getLevel() == levels[0]) {
                    decks[2][3].addCard(card);

                } else if(card.getLevel() == levels[1]) {
                    decks[1][3].addCard(card);

                } else if(card.getLevel() == levels[2]) {
                    decks[0][3].addCard(card);
                }
                cardsInDeck ++;

            } else if(card.getColor() == colors[2]) {
                if(card.getLevel() == levels[0]) {
                    decks[2][2].addCard(card);

                } else if(card.getLevel() == levels[1]) {
                    decks[1][2].addCard(card);

                } else if(card.getLevel() == levels[2]) {
                    decks[0][2].addCard(card);

                }
                cardsInDeck ++;

            } else if(card.getColor() == colors[3]) {
                if(card.getLevel() == levels[0]) {
                    decks[2][0].addCard(card);

                } else if(card.getLevel() == levels[1]) {
                    decks[1][0].addCard(card);

                } else if(card.getLevel() == levels[2]) {
                    decks[0][0].addCard(card);
                }
                cardsInDeck ++;

            }
        }
        if(cardsInDeck != 48)
            System.err.println("Error: Too many cards in .json File");

    }

    /**
     * This method returns the deck corresponding to the specified coordinates.
     * @param row is the row corresponding to the deck.
     * @param column is the column corresponding to the deck.
     * @return the requested Development Card deck.
     */
    public DevelopmentCardDeck getDeck(int row, int column) {
        return this.decks[row][column];
    }

    /**
     * This method returns the card at the top of the deck corresponding to the specified coordinates.
     * @param row is the row corresponding to the deck.
     * @param column is the column corresponding to the deck.
     * @return the requested Development Card.
     */
    public DevelopmentCard getTopCardFromDeck(int row, int column) {
        DevelopmentCardDeck deck = getDeck(row, column);
        return deck.getCards()[deck.getCardsInDeck() - 1];
    }

    /**
     * This method is used to buy a development card from a specified deck.
     * <p>
     * If the deck is empty or the player doesn't have enough resources to buy the card,
     * he is notified and the method returns null;
     * @param developmentCardDeck is the deck form which to buy the card.
     * @param resourceManager is the resource manager of the player from which the payment is taken.
     * @return the desired card if the deck is not full and the player has enough resources.
     */
    public DevelopmentCard buyCard(DevelopmentCardDeck developmentCardDeck, ResourceManager resourceManager, LeaderCard[] leaderCards) {
        if(developmentCardDeck.isEmpty()) {
            System.out.println("This deck is empty. Choose another deck!");
            return null;
        }
        DevelopmentCard cardToBuy = developmentCardDeck.getCards()[developmentCardDeck.getCardsInDeck() - 1];

        if(!resourceManager.cardIsBuyable(cardToBuy, leaderCards))
            return null;
        ResourceStack cardToBuyCost = cardToBuy.getCost().copyStack();

        for(LeaderCard leaderCard : leaderCards)
            if(leaderCard.isActive() && leaderCard.getAction() == LeaderCardAction.DISCOUNT)
                cardToBuyCost.removeFromAllTypes(leaderCard.getDiscount());

        if(!resourceManager.cardIsBuyable(cardToBuy, leaderCards))
            return null;

        if(!cardToBuyCost.isEmpty())
            resourceManager.payProductionOrCardPrice(cardToBuyCost);

        return developmentCardDeck.drawCard();
    }

}
