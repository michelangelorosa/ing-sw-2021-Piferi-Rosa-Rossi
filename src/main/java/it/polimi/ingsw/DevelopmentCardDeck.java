package it.polimi.ingsw;

public class DevelopmentCardDeck {
    private DevelopmentCard[] cards;
    private DevelopmentCard.Color color;
    private DevelopmentCard.Level level;
    private int cardsInDeck;

    public DevelopmentCardDeck(DevelopmentCard.Color color, DevelopmentCard.Level level) {
        cards = new DevelopmentCard[4];
        this.color = color;
        this.level = level;
    }

    public void reset() {

        DevelopmentCard.Color[] colors = DevelopmentCard.Color.values();
        DevelopmentCard.Level[] levels = DevelopmentCard.Level.values();
        cardsInDeck = 0;

        //BLUE CARDS
        if(this.color == colors[0]) {
            if (this.level == levels[0]) {

                cards[cardsInDeck++] = new DevelopmentCard();
                cards[cardsInDeck++] = new DevelopmentCard();
                cards[cardsInDeck++] = new DevelopmentCard();
                cards[cardsInDeck++] = new DevelopmentCard();

            } else if (this.level == levels[1]) {

                cards[cardsInDeck++] = new DevelopmentCard();
                cards[cardsInDeck++] = new DevelopmentCard();
                cards[cardsInDeck++] = new DevelopmentCard();
                cards[cardsInDeck++] = new DevelopmentCard();

            } else if (this.level == levels[2]) {
                cards[cardsInDeck++] = new DevelopmentCard();
                cards[cardsInDeck++] = new DevelopmentCard();
                cards[cardsInDeck++] = new DevelopmentCard();
                cards[cardsInDeck++] = new DevelopmentCard();
            }
        }

        //PURPLE CARDS
        else if(this.color == colors[1]) {
            if (this.level == levels[0]) {

                cards[cardsInDeck++] = new DevelopmentCard();
                cards[cardsInDeck++] = new DevelopmentCard();
                cards[cardsInDeck++] = new DevelopmentCard();
                cards[cardsInDeck++] = new DevelopmentCard();

            } else if (this.level == levels[1]) {

                cards[cardsInDeck++] = new DevelopmentCard();
                cards[cardsInDeck++] = new DevelopmentCard();
                cards[cardsInDeck++] = new DevelopmentCard();
                cards[cardsInDeck++] = new DevelopmentCard();

            } else if (this.level == levels[2]) {

                cards[cardsInDeck++] = new DevelopmentCard();
                cards[cardsInDeck++] = new DevelopmentCard();
                cards[cardsInDeck++] = new DevelopmentCard();
                cards[cardsInDeck++] = new DevelopmentCard();

            }
        }

        //YELLOW CARDS
        else if(this.color == colors[2]) {
            if (this.level == levels[0]) {

                cards[cardsInDeck++] = new DevelopmentCard();
                cards[cardsInDeck++] = new DevelopmentCard();
                cards[cardsInDeck++] = new DevelopmentCard();
                cards[cardsInDeck++] = new DevelopmentCard();

            } else if (this.level == levels[1]) {

                cards[cardsInDeck++] = new DevelopmentCard();
                cards[cardsInDeck++] = new DevelopmentCard();
                cards[cardsInDeck++] = new DevelopmentCard();
                cards[cardsInDeck++] = new DevelopmentCard();

            } else if (this.level == levels[2]) {

                cards[cardsInDeck++] = new DevelopmentCard();
                cards[cardsInDeck++] = new DevelopmentCard();
                cards[cardsInDeck++] = new DevelopmentCard();
                cards[cardsInDeck++] = new DevelopmentCard();

            }
        }

        //GREEN CARDS
        else if(this.color == colors[3]) {
            if (this.level == levels[0]) {

                cards[cardsInDeck++] = new DevelopmentCard();
                cards[cardsInDeck++] = new DevelopmentCard();
                cards[cardsInDeck++] = new DevelopmentCard();
                cards[cardsInDeck++] = new DevelopmentCard();

            } else if (this.level == levels[1]) {

                cards[cardsInDeck++] = new DevelopmentCard();
                cards[cardsInDeck++] = new DevelopmentCard();
                cards[cardsInDeck++] = new DevelopmentCard();
                cards[cardsInDeck++] = new DevelopmentCard();

            } else if (this.level == levels[2]) {

                cards[cardsInDeck++] = new DevelopmentCard();
                cards[cardsInDeck++] = new DevelopmentCard();
                cards[cardsInDeck++] = new DevelopmentCard();
                cards[cardsInDeck++] = new DevelopmentCard();

            }
        }
    }
}
