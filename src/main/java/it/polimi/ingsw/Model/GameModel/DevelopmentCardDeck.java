package it.polimi.ingsw.Model.GameModel;

import it.polimi.ingsw.Model.Enums.Color;
import it.polimi.ingsw.Model.Enums.Level;
import it.polimi.ingsw.View.ReducedModel.RedDevelopmentCardDeck;

import java.util.Arrays;
import java.util.Collections;

 /**
  * DevelopmentCardDeck Class defines methods to be used on Development Card Deck objects inside the Model.
  * @author everyone
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
      * Getter for "cards" attribute i.
      */
    public DevelopmentCard[] getCards() {
        return (DevelopmentCard[]) cards;
    }

     /**
      * Setter for "cards" attribute.
      */
    public void setCards(DevelopmentCard[] cards) {
        this.cards = cards;
    }

     /**
      * Setter for "color" attribute.
      */
    public void setColor(Color color) {
        this.color = color;
    }

     /**
      * Setter for "level" attribute.
      */
    public void setLevel(Level level) {
        this.level = level;
    }

     /**
      * Uses a java.util.Collections standard method tu shuffle all the cards in the deck.
      */
    public void shuffle() {
        Collections.shuffle(Arrays.asList(cards));
    }

     /**
      * Setter for "cardsInDeck" attribute.
      */
    public void setCardsInDeck(int cardsInDeck){
        this.cardsInDeck = cardsInDeck;
    }

     /**
      * Adds a card in the "cards" array attribute while also incrementing "cardsInDeck".
      */
    public void addCard(DevelopmentCard card) {
        this.cards[cardsInDeck++] = card;
    }

     /**
      * Draws a card from the deck and updates the deck's length. If the deck is empty, a new
      * exception is thrown indicating the absence of cards in the deck.
      */
    public DevelopmentCard drawCard() throws IllegalArgumentException {
        if(isEmpty()) {
            throw new IllegalArgumentException("Cannot draw card from empty deck!");
        }
        return (DevelopmentCard) cards[--cardsInDeck];
    }
}
