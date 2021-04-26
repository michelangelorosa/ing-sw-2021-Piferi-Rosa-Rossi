
package it.polimi.ingsw.View.ReducedModel;

import it.polimi.ingsw.View.ReducedModel.Enums.*;

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
     */
    public DevelopmentCardTable() {
        this.decks = new DevelopmentCardDeck[3][4];
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
}
