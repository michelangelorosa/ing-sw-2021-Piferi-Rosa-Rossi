
package it.polimi.ingsw.Model.GameModel;

import it.polimi.ingsw.Model.Enums.Color;
import it.polimi.ingsw.Model.Enums.LeaderCardAction;
import it.polimi.ingsw.Model.Enums.Level;
import it.polimi.ingsw.Model.Exceptions.ModelException;
import it.polimi.ingsw.View.ReducedModel.RedDevelopmentCardDeck;
import it.polimi.ingsw.View.ReducedModel.RedDevelopmentCardTable;
import it.polimi.ingsw.Model.JSON.*;

/**
 * DevelopmentCardTable Class contains all 12 Development Card decks available for the player to buy.
 * All decks are stored in a 2D array of DevelopmentCardDeck-type object.
 */
public class DevelopmentCardTable extends RedDevelopmentCardTable {
    /**
     * Constructor for DevelopmentCardTable Class.
     * <p>
     * This method uses "JSONReader" Class to read all cards from a .json file. Development Cards are then
     * stored inside the 2D array containing all decks.
     */
    public DevelopmentCardTable() {
        super();
        DevelopmentCard[] cards = JSONReader.ReadDevelopmentCards();

        Color[] colors = Color.values();
        Level[] levels = Level.values();

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
                    ((DevelopmentCardDeck)decks[2][1]).addCard(card);

                } else if(card.getLevel() == levels[1]) {
                    ((DevelopmentCardDeck)decks[1][1]).addCard(card);

                } else if(card.getLevel() == levels[2]) {
                    ((DevelopmentCardDeck)decks[0][1]).addCard(card);
                }

            } else if(card.getColor() == colors[1]) {
                if(card.getLevel() == levels[0]) {
                    ((DevelopmentCardDeck)decks[2][3]).addCard(card);

                } else if(card.getLevel() == levels[1]) {
                    ((DevelopmentCardDeck)decks[1][3]).addCard(card);

                } else if(card.getLevel() == levels[2]) {
                    ((DevelopmentCardDeck)decks[0][3]).addCard(card);
                }

            } else if(card.getColor() == colors[2]) {
                if(card.getLevel() == levels[0]) {
                    ((DevelopmentCardDeck)decks[2][2]).addCard(card);

                } else if(card.getLevel() == levels[1]) {
                    ((DevelopmentCardDeck)decks[1][2]).addCard(card);

                } else if(card.getLevel() == levels[2]) {
                    ((DevelopmentCardDeck)decks[0][2]).addCard(card);

                }

            } else if(card.getColor() == colors[3]) {
                if(card.getLevel() == levels[0]) {
                    ((DevelopmentCardDeck)decks[2][0]).addCard(card);

                } else if(card.getLevel() == levels[1]) {
                    ((DevelopmentCardDeck)decks[1][0]).addCard(card);

                } else if(card.getLevel() == levels[2]) {
                    ((DevelopmentCardDeck)decks[0][0]).addCard(card);
                }

            }
        }
    }

    /**
     * Alternative Constructor for DevelopmentCardTable Class.
     */
    public DevelopmentCardTable(RedDevelopmentCardDeck[][] decks) {
        super(decks);
    }

    /**
     * Returns the deck corresponding to the specified coordinates.
     * @param row is the row corresponding to the deck.
     * @param column is the column corresponding to the deck.
     * @return the requested Development Card deck.
     */
    public DevelopmentCardDeck getDeck(int row, int column) {
        return (DevelopmentCardDeck)this.decks[row][column];
    }

    /**
     * Returns the card at the top of the deck corresponding to the specified coordinates.
     * @param row the deck's row index.
     * @param column the deck's column index.
     * @return the requested Development Card.
     */
    public DevelopmentCard getTopCardFromDeck(int row, int column) throws ModelException {
        RedDevelopmentCardDeck deck = getDeck(row, column);
        if(!deck.isEmpty())
            return ((DevelopmentCard)deck.getCards()[deck.getCardsInDeck() - 1]);
        else {
            throw new ModelException("This deck is empty. Choose another deck!");
        }
    }

    /**
     * Shuffles each deck on the DevelopmentCardTable.
     */
    public void shuffleTable() {
        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 4; j++)
                ((DevelopmentCardDeck)this.decks[i][j]).shuffle();
    }

    /**
     * Draws one card from a specified deck inside the Development Card Table.
     * @param row the deck's row index.
     * @param column the deck's column index.
     * @return the requested Development Card.
     */
    public DevelopmentCard drawCardFromDeck(int row, int column) throws ModelException {
        DevelopmentCardDeck deck = getDeck(row, column);
        if(!deck.isEmpty())
            return deck.drawCard();
        else
            throw new ModelException("This deck is empty. Choose another deck!");
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

    /**
     * Returns the Development Card corresponding to the given id.
     * @param id Id of the card to get.
     */
    public DevelopmentCard getCardFromId(int id) {
        for(int r = 0; r < 3; r++){
            for(int c = 0; c < 4; c++){
                for(int pos = 0; pos < 4; pos ++){
                    if(this.getDeck(r, c).getCards()[pos].getCardId() == id) return this.getDeck(r, c).getCards()[pos];
                }
            }
        }
        return null;
    }

    public boolean columnIsEmpty(int column) {
        return column >= 0 && column <= 3 && decks[0][column].isEmpty() && decks[1][column].isEmpty() && decks[2][column].isEmpty();
    }

    public DevelopmentCardDeck getLowestDeckByColumn(int column) {
        if (column < 0 || column > 3)
            return null;

        if(!decks[2][column].isEmpty())
            return (DevelopmentCardDeck) decks[2][column];
        else if(!decks[1][column].isEmpty())
            return (DevelopmentCardDeck) decks[1][column];
        else if(!decks[0][column].isEmpty())
            return (DevelopmentCardDeck) decks[0][column];
        else
            return null;
    }

    public RedDevelopmentCardTable toView(){
        return this;
    }
}
