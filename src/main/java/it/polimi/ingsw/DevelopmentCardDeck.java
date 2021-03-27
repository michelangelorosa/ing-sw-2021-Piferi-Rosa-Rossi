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

                cards[cardsInDeck++] = new DevelopmentCard(colors[0], levels[0], 3, 1,0,0,2,0, 1, 0,0, 0,0, 0,0,0,1);
                cards[cardsInDeck++] = new DevelopmentCard(colors[0], levels[0], 6, 2,0,1,1,1, 0, 1,0, 0,0, 0,0,1,0);
                cards[cardsInDeck++] = new DevelopmentCard(colors[0], levels[0], 11, 3,0,0,3,0, 0, 0,0, 2,1, 1,1,0,0);
                cards[cardsInDeck++] = new DevelopmentCard(colors[0], levels[0], 15, 4,0,2,2,0, 1, 0,0, 1,0, 2,0,0,1);

            } else if (this.level == levels[1]) {

                cards[cardsInDeck++] = new DevelopmentCard(colors[0], levels[1], 19, 5,0,0,4,0, 0, 1,0, 0,0, 0,0,0,2);
                cards[cardsInDeck++] = new DevelopmentCard(colors[0], levels[1], 23, 6,0,0,3,2, 0, 0,1, 1,0, 0,0,3,0);
                cards[cardsInDeck++] = new DevelopmentCard(colors[0], levels[1], 27, 7,0,0,5,0, 0, 2,0, 0,2, 0,0,0,2);
                cards[cardsInDeck++] = new DevelopmentCard(colors[0], levels[1], 31, 8,0,0,3,3, 0, 1,0, 0,0, 0,0,2,1);

            } else if (this.level == levels[2]) {
                cards[cardsInDeck++] = new DevelopmentCard(colors[0], levels[2], 35, 9,0,0,6,0, 0, 2,0, 0,3, 0,0,0,2);
                cards[cardsInDeck++] = new DevelopmentCard(colors[0], levels[2], 39, 10,0,0,5,2, 1, 0,1, 0,0, 2,0,2,1);
                cards[cardsInDeck++] = new DevelopmentCard(colors[0], levels[2], 43, 11,0,0,7,0, 0, 0,0, 1,1, 0,0,0,3);
                cards[cardsInDeck++] = new DevelopmentCard(colors[0], levels[2], 47, 12,0,0,4,4, 0, 1,0, 0,3, 0,1,0,0);
            }
        }

        //PURPLE CARDS
        else if(this.color == colors[1]) {
            if (this.level == levels[0]) {

                cards[cardsInDeck++] = new DevelopmentCard(colors[1], levels[0], 2, 1,0,2,0,0, 0, 0,0, 1,0, 0,0,0,1);
                cards[cardsInDeck++] = new DevelopmentCard(colors[1], levels[0], 6, 2,1,1,1,0, 0, 0,1, 0,1, 0,0,0,0);
                cards[cardsInDeck++] = new DevelopmentCard(colors[1], levels[0], 10, 3,0,3,0,0, 0, 0,2, 0,1, 1,1,1,0);
                cards[cardsInDeck++] = new DevelopmentCard(colors[1], levels[0], 14, 4,0,2,0,2, 1, 0,1, 0,0, 0,0,2,1);

            } else if (this.level == levels[1]) {

                cards[cardsInDeck++] = new DevelopmentCard(colors[1], levels[1], 18, 5,0,4,0,0, 0, 0,1, 0,0, 0,0,0,2);
                cards[cardsInDeck++] = new DevelopmentCard(colors[1], levels[1], 22, 6,0,3,2,0, 0, 1,1, 0,3, 0,0,0,0);
                cards[cardsInDeck++] = new DevelopmentCard(colors[1], levels[1], 26, 7,0,5,0,0, 0, 0,0, 2,0, 0,2,0,2);
                cards[cardsInDeck++] = new DevelopmentCard(colors[1], levels[1], 30, 8,3,3,0,0, 0, 0,0, 1,0, 2,0,0,1);

            } else if (this.level == levels[2]) {

                cards[cardsInDeck++] = new DevelopmentCard(colors[1], levels[2], 34, 9,0,6,0,0, 0, 0,0, 2,0, 0,3,0,2);
                cards[cardsInDeck++] = new DevelopmentCard(colors[1], levels[2], 38, 10,0,5,2,0, 1, 0,0, 1,0, 2,2,0,1);
                cards[cardsInDeck++] = new DevelopmentCard(colors[1], levels[2], 42, 11,0,7,0,0, 0, 0,1, 0,0, 0,0,1,3);
                cards[cardsInDeck++] = new DevelopmentCard(colors[1], levels[2], 46, 12,4,4,0,0, 0, 0,1, 0,0, 1,0,3,0);

            }
        }

        //YELLOW CARDS
        else if(this.color == colors[2]) {
            if (this.level == levels[0]) {

                cards[cardsInDeck++] = new DevelopmentCard(colors[2], levels[0], 4, 1,0,0,0,2, 0, 1,0, 0,0, 0,0,0,1);
                cards[cardsInDeck++] = new DevelopmentCard(colors[2], levels[0], 8, 2,1,0,1,1, 1, 0,0, 0,0, 0,1,0,0);
                cards[cardsInDeck++] = new DevelopmentCard(colors[2], levels[0], 12, 3,0,0,0,3, 2, 0,0, 0,0, 1,1,1,0);
                cards[cardsInDeck++] = new DevelopmentCard(colors[2], levels[0], 16, 4,2,0,0,2, 0, 1,1, 0,2, 0,0,0,1);

            } else if (this.level == levels[1]) {

                cards[cardsInDeck++] = new DevelopmentCard(colors[2], levels[1], 20, 5,0,0,0,4, 1, 0,0, 0,0, 0,0,0,2);
                cards[cardsInDeck++] = new DevelopmentCard(colors[2], levels[1], 24, 6,2,0,0,3, 1, 0,0, 1,0, 0,3,0,0);
                cards[cardsInDeck++] = new DevelopmentCard(colors[2], levels[1], 28, 7,0,0,0,5, 2, 0,0, 0,0, 2,0,0,2);
                cards[cardsInDeck++] = new DevelopmentCard(colors[2], levels[1], 32, 8,0,3,0,3, 1, 0,0, 0,0, 0,2,0,1);

            } else if (this.level == levels[2]) {

                cards[cardsInDeck++] = new DevelopmentCard(colors[2], levels[2], 36, 9,0,0,0,6, 2, 0,0, 0,0, 3,0,0,2);
                cards[cardsInDeck++] = new DevelopmentCard(colors[2], levels[2], 40, 10,0,2,0,5, 0, 1,0, 1,2, 0,2,0,1);
                cards[cardsInDeck++] = new DevelopmentCard(colors[2], levels[2], 44, 11,0,0,0,7, 1, 0,0, 0,0, 1,0,0,3);
                cards[cardsInDeck++] = new DevelopmentCard(colors[2], levels[2], 48, 12,0,4,0,4, 1, 0,0, 0,0, 3,0,1,0);

            }
        }

        //GREEN CARDS
        else if(this.color == colors[3]) {
            if (this.level == levels[0]) {

                cards[cardsInDeck++] = new DevelopmentCard(colors[3], levels[0], 1, 1,2,0,0,0, 0, 0,1, 0,0, 0,0,0,1);
                cards[cardsInDeck++] = new DevelopmentCard(colors[3], levels[0], 5, 2,1,1,0,1, 0, 0,0, 1,0, 1,0,0,0);
                cards[cardsInDeck++] = new DevelopmentCard(colors[3], levels[0], 9, 3,3,0,0,0, 0, 2,0, 0,1, 0,1,1,0);
                cards[cardsInDeck++] = new DevelopmentCard(colors[3], levels[0], 13, 4,2,0,2,0, 0, 1,0, 1,0, 0,2,0,1);

            } else if (this.level == levels[1]) {

                cards[cardsInDeck++] = new DevelopmentCard(colors[3], levels[1], 17, 5,4,0,0,0, 0, 0,0, 1,0, 0,0,0,2);
                cards[cardsInDeck++] = new DevelopmentCard(colors[3], levels[1], 21, 6,3,2,0,0, 1, 1,0, 0,0, 0,0,3,0);
                cards[cardsInDeck++] = new DevelopmentCard(colors[3], levels[1], 25, 7,5,0,0,0, 0, 0,2, 0,0, 0,0,2,2);
                cards[cardsInDeck++] = new DevelopmentCard(colors[3], levels[1], 29, 8,3,0,3,0, 0, 0,1, 0,2, 0,0,0,1);

            } else if (this.level == levels[2]) {

                cards[cardsInDeck++] = new DevelopmentCard(colors[3], levels[2], 33, 9,6,0,0,0, 0, 0,2, 0,0, 0,0,3,2);
                cards[cardsInDeck++] = new DevelopmentCard(colors[3], levels[2], 37, 10,5,2,0,0, 0, 1,1, 0,2, 0,0,2,1);
                cards[cardsInDeck++] = new DevelopmentCard(colors[3], levels[2], 41, 11,7,0,0,0, 0, 1,0, 0,0, 0,1,0,3);
                cards[cardsInDeck++] = new DevelopmentCard(colors[3], levels[2], 45, 12,4,0,4,0, 0, 0,0, 1,1, 0,3,0,0);

            }
        }
    }
}
