package it.polimi.ingsw.Model;

import it.polimi.ingsw.Model.Enums.Color;
import it.polimi.ingsw.Model.Enums.Level;
import it.polimi.ingsw.View.ReducedModel.RedDevelopmentCardDeck;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;

 /**
  * DevelopmentCardDeck Class contains three private attributes: an array of DevelopmentCards, a color and a level.
  * Another private integer attribute (cardsInDeck) is used to keep track of how many cards are in the deck.
  */
public class DevelopmentCardDeck extends RedDevelopmentCardDeck {

     /**
      * Constructor for DevelopmentCardDeck Class.
      */
    public DevelopmentCardDeck(Color color, Level level) {
        this.cards = new DevelopmentCard[4];
        this.color = color;
        this.level = level;
        this.cardsInDeck = 0;
    }

     /**
      * Getter for "cards" attribute in DevelopmentCardDeck Class.
      */
    public DevelopmentCard[] getCards() {
        return (DevelopmentCard[]) cards;
    }

     /**
      * Setter for "cards" attribute in DevelopmentCardDeck
      */
    public void setCards(DevelopmentCard[] cards) {
        this.cards = cards;
    }

     /**
      * Setter for "color" attribute in DevelopmentCardDeck
      */
    public void setColor(Color color) {
        this.color = color;
    }

     /**
      * Setter for "level" attribute in DevelopmentCardDeck
      */
    public void setLevel(Level level) {
        this.level = level;
    }

     /**
      * Setter for "cardsInDeck" attribute in DevelopmentCardDeck
      */
     public void setCardsInDeck(int cardsInDeck) {
         this.cardsInDeck = cardsInDeck;
     }

     /**
      * This method uses a java.util.Collections standard method tu shuffle all the cards in the deck.
      */
    public void shuffle() {
        Collections.shuffle(Arrays.asList(cards));
    }

     /**
      * This method adds a card in the "cards" array attribute while also incrementing "cardsInDeck".
      */
    public void addCard(DevelopmentCard card) {
        this.cards[cardsInDeck++] = card;
    }

     /**
      * This method draws a card from the deck and updates the deck's length. If the deck is empty, a new
      * exception is thrown indicating the absence of cards in the deck.
      */
    public DevelopmentCard drawCard() throws IllegalArgumentException {
        if(isEmpty()) {
            throw new IllegalArgumentException("Cannot draw card from empty deck!");
        }
        return (DevelopmentCard) cards[--cardsInDeck];
    }

     /**Method for converting model classes to view classes*/
     public RedDevelopmentCardDeck toView() {
         return (RedDevelopmentCardDeck) this;
     }
}
