package it.polimi.ingsw.View.ReducedModel;

import it.polimi.ingsw.Model.Enums.Color;
import it.polimi.ingsw.Model.Enums.Level;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;

/**
 * RedDevelopmentCardDeck Class contains three private attributes: an array of DevelopmentCards, a color and a level.
 * Another private integer attribute (cardsInDeck) is used to keep track of how many cards are in the deck.
 * <p><b>Attributes:</b></p>
 * <ul>
 *     <li>RedDevelopmentCard[] "cards": Development Cards of the deck</li>
 *     <li>Color "color": color of the deck</li>
 *     <li>Level "level": level of the deck</li>
 *     <li>int "cardsInDeck": number of cards of the deck</li>
 * </ul>
 */
public class RedDevelopmentCardDeck implements Serializable {
    protected RedDevelopmentCard[] cards;
    protected Color color;
    protected Level level;
    protected int cardsInDeck;

    /**
     * Getter for "cards" attribute.
     */
   public RedDevelopmentCard[] getCards() {
       return cards;
   }

    /**
     * Getter for "color" attribute.
     */
   public Color getColor() {
       return color;
   }

    /**
     * Getter for "level" attribute.
     */
   public Level getLevel() {
       return level;
   }

    /**
     * Getter for "cardsInDeck" attribute.
     */
    public int getCardsInDeck() {
        return cardsInDeck;
    }

    /**
     * This method returns true if "cardsInDeck" equals zero, meaning there are no cards in the deck.
     */
   public boolean isEmpty() {
       return cardsInDeck == 0;
   }

    /**
     * Gets the first card in a deck.
     * @return a DevelopmentCard
     * @throws IllegalArgumentException if the deck is empty.
     */
   public RedDevelopmentCard getTopCard() throws IllegalArgumentException {
       int position = cardsInDeck - 1;
       if(isEmpty()) {
           throw new IllegalArgumentException("Cannot get card from empty deck!");
       }
       return cards[position];
   }

    /**
     * A method to return a string with che color and the level of a card.
     * @return a string that contains the color and the level of the card.
     */
   @Override
   public String toString() {
       return color+" "+level;
   }
}
