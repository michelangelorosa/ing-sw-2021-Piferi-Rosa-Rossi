package it.polimi.ingsw.Model;

import it.polimi.ingsw.Model.Enums.Color;
import it.polimi.ingsw.Model.Enums.Level;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;

 /**
  * DevelopmentCardDeck Class contains three private attributes: an array of DevelopmentCards, a color and a level.
  * Another private integer attribute (cardsInDeck) is used to keep track of how many cards are in the deck.
  */
public class DevelopmentCardDeck implements Serializable {
     private static final long serialVersionUID = 0x1;

     private DevelopmentCard[] cards;
    private Color color;
    private Level level;
    private int cardsInDeck;

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
        return cards;
    }

     /**
      * Setter for "cards" attribute in DevelopmentCardDeck
      */
    public void setCards(DevelopmentCard[] cards) {
        this.cards = cards;
    }

     /**
      * Getter for "color" attribute in DevelopmentCardDeck
      */
    public Color getColor() {
        return color;
    }

     /**
      * Setter for "color" attribute in DevelopmentCardDeck
      */
    public void setColor(Color color) {
        this.color = color;
    }

     /**
      * Getter for "level" attribute in DevelopmentCardDeck
      */
    public Level getLevel() {
        return level;
    }

     /**
      * Setter for "level" attribute in DevelopmentCardDeck
      */
    public void setLevel(Level level) {
        this.level = level;
    }

     /**
      * Getter for "cardsInDeck" attribute in DevelopmentCardDeck
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
        return cards[--cardsInDeck];
    }

     /**
      * Method to get the first card in a deck.
      * @return a DevelopmentCard
      * @throws IllegalArgumentException if the deck is empty.
      */
    public DevelopmentCard getTopCard() throws IllegalArgumentException {
        int position = cardsInDeck - 1;
        if(isEmpty()) {
            throw new IllegalArgumentException("Cannot get card from empty deck!");
        }
        return cards[position];
    }

     /**
      * A method to return a string with che color and the level of a card
      * @return a string that contains the color and the level of the card
      */
    @Override
    public String toString() {
        return color+" "+level;
    }

     /**Method for converting model classes to view classes*/
     public it.polimi.ingsw.View.ReducedModel.DevelopmentCardDeck toView() {
         it.polimi.ingsw.View.ReducedModel.DevelopmentCardDeck developmentCardDeck = new it.polimi.ingsw.View.ReducedModel.DevelopmentCardDeck(this.color, this.level);
         developmentCardDeck.getCards()[0] = this.cards[0].toView();
         developmentCardDeck.getCards()[1] = this.cards[1].toView();
         developmentCardDeck.getCards()[2] = this.cards[2].toView();
         developmentCardDeck.getCards()[3] = this.cards[3].toView();

         developmentCardDeck.setCardsInDeck(this.cardsInDeck);

         return developmentCardDeck;
     }
}
