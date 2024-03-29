
package it.polimi.ingsw.View.ReducedModel;

import it.polimi.ingsw.Model.Exceptions.ModelException;
import it.polimi.ingsw.Model.GameModel.DevelopmentCardDeck;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * RedDevelopmentCardTable Class contains all 12 Development Card decks available for the player to buy.
 * All decks are stored in a 2D array of DevelopmentCardDeck-type object.
 * <p><b>Attributes:</b></p>
 * <ul>
 *     <li>RedDevelopmentCardDeck[][] "decks": matrix containing all decks</li>
 * </ul>
 */
public class RedDevelopmentCardTable implements Serializable {
    protected final RedDevelopmentCardDeck[][] decks;

    /**
     * Constructor for RedDevelopmentCardTable Class.
     */
    protected RedDevelopmentCardTable() {
        this.decks = new DevelopmentCardDeck[3][4];
    }

    /**
     * Alternative constructor for RedDevelopmentCardTable Class.
     */
    protected RedDevelopmentCardTable(RedDevelopmentCardDeck[][] decks) {
        this.decks = decks;
    }

    /**
     * Returns the deck corresponding to the specified coordinates.
     * @param row is the row corresponding to the deck.
     * @param column is the column corresponding to the deck.
     * @return the requested Development Card deck.
     */
    public RedDevelopmentCardDeck getDeck(int row, int column) {
        return this.decks[row][column];
    }

    /**
     * Returns the card at the top of the deck corresponding to the specified coordinates.
     * @param row the deck's row index.
     * @param column the deck's column index.
     * @return the requested Development Card.
     */
    public RedDevelopmentCard getTopCardFromDeck(int row, int column) throws ModelException {
        RedDevelopmentCardDeck deck = getDeck(row, column);
        if(!deck.isEmpty())
            return deck.getCards()[deck.getCardsInDeck() - 1];
        else {
            System.out.println("This deck is empty. Choose another deck!");
            return null;
        }
    }

    /**
     * Builds a visual representation of the DevelopmentCardTable.
     * @return An ArrayList of Strings containing the visual representation.
     */
    public ArrayList<String> toCli() {
        ArrayList<String> table = new ArrayList<>();
        ArrayList<String> column2 = new ArrayList<>();
        ArrayList<String> column3 = new ArrayList<>();
        ArrayList<String> column4 = new ArrayList<>();
        int i, j;


        try {
            for (i = 0; i < 3; i++) {
                table.addAll(this.getTopCardFromDeck(i, 0).toCli());
                column2.addAll(this.getTopCardFromDeck(i, 1).toCli());
                column3.addAll(this.getTopCardFromDeck(i, 2).toCli());
                column4.addAll(this.getTopCardFromDeck(i, 3).toCli());
            }
        }catch (ModelException e) {
            System.out.println("Cannot draw from empty deck");
            e.printStackTrace();
        }

        for(i = 0; i < table.size(); i++)
            table.set(i, "  " + table.get(i) + " " + column2.get(i) + " " + column3.get(i) + " " + column4.get(i));

        table.add(0, "╔═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗");
        table.add("╚═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");

        return table;
    }
}
