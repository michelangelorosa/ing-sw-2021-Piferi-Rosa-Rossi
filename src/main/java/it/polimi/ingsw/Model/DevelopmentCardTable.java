
package it.polimi.ingsw.Model;

import it.polimi.ingsw.Model.Enums.Color;
import it.polimi.ingsw.Model.Enums.LeaderCardAction;
import it.polimi.ingsw.Model.Enums.Level;

import java.io.Serializable;

/**
 * DevelopmentCardTable Class contains all 12 Development Card decks available for the player to buy.
 * All decks are stored in a 2D array of DevelopmentCardDeck-type object.
 */
public class DevelopmentCardTable implements Serializable {
    private static final long serialVersionUID = 0x1;

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
     * @param row the deck's row index.
     * @param column the deck's column index.
     * @return the requested Development Card.
     */
    public DevelopmentCard getTopCardFromDeck(int row, int column) {
        DevelopmentCardDeck deck = getDeck(row, column);
        if(!deck.isEmpty())
            return deck.getCards()[deck.getCardsInDeck() - 1];
        else {
            System.out.println("This deck is empty. Choose another deck!");
            return null;
        }
    }

    /**
     * This method is used to shuffle each deck on the DevelopmentCardTable.
     */
    public void shuffleTable() {
        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 4; j++)
                this.decks[i][j].shuffle();
    }

    /**
     * This method is used to draw one card from a specified deck inside the Development Card Table.
     * @param row the deck's row index.
     * @param column the deck's column index.
     * @return the requested Development Card.
     */
    public DevelopmentCard drawCardFromDeck(int row, int column) {
        DevelopmentCardDeck deck = getDeck(row, column);
        if(!deck.isEmpty())
            return deck.drawCard();
        else
            return null;
    }

    /**
     * This method is used when a player wants to buy a specified Development Card to update the card's cost,
     * preparing the player for the paying process.
     * @param cardToBuy Development Card which the player wants to buy.
     * @param leaderCards player's owned Leader Cards.
     * @param resourceManager player's Resource Manager System.
     */
    public void updateCardToBuyCost(DevelopmentCard cardToBuy, LeaderCard[] leaderCards, ResourceManager resourceManager) {
        ResourceStack cardCost = cardToBuy.getCost().copyStack();

        for(LeaderCard leaderCard : leaderCards)
            if(leaderCard.isActive() && leaderCard.getAction() == LeaderCardAction.DISCOUNT)
                cardCost.removeFromAllTypes(leaderCard.getDiscount());

        resourceManager.setTemporaryResourcesToPay(cardCost);
    }

    public it.polimi.ingsw.View.ReducedModel.DevelopmentCardTable toView(){
        it.polimi.ingsw.View.ReducedModel.DevelopmentCardTable table = new it.polimi.ingsw.View.ReducedModel.DevelopmentCardTable();

        table.getDecks()[0][0] = this.decks[0][0].toView();
        table.getDecks()[0][0] = this.decks[0][1].toView();
        table.getDecks()[0][0] = this.decks[0][2].toView();
        table.getDecks()[0][0] = this.decks[0][3].toView();
        table.getDecks()[0][0] = this.decks[1][0].toView();
        table.getDecks()[0][0] = this.decks[1][1].toView();
        table.getDecks()[0][0] = this.decks[1][2].toView();
        table.getDecks()[0][0] = this.decks[1][3].toView();
        table.getDecks()[0][0] = this.decks[2][0].toView();
        table.getDecks()[0][0] = this.decks[2][1].toView();
        table.getDecks()[0][0] = this.decks[2][2].toView();
        table.getDecks()[0][0] = this.decks[2][3].toView();

        return table;
    }

}
