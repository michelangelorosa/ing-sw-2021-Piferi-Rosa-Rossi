package it.polimi.ingsw.Model.JSON;

/**
 * DevelopmentCardDeckJSON is a class used to write on JSON files
 * @author francescopiferi99
 */
public class DevelopmentCardDeckJSON {
    int greenThree, blueThree, yellowThree, purpleThree, greenTwo, blueTwo, yellowTwo, purpleTwo, greenOne, blueOne, yellowOne, purpleOne;

    /**
     * Method used to convert CardsInDeck in to a JSON file
     * @param greenThree cardsInDeck in the green level 3 deck
     * @param blueThree cardsInDeck in the blue level 3 deck
     * @param yellowThree cardsInDeck in the yellow level 3 deck
     * @param purpleThree cardsInDeck in the purple level 3 deck
     * @param greenTwo cardsInDeck in the green level 2 deck
     * @param blueTwo cardsInDeck in the blue level 2 deck
     * @param yellowTwo cardsInDeck in the yellow level 2 deck
     * @param purpleTwo cardsInDeck in the purple level 2 deck
     * @param greenOne cardsInDeck in the green level 1 deck
     * @param blueOne cardsInDeck in the blue level 1 deck
     * @param yellowOne cardsInDeck in the yellow level 1 deck
     * @param purpleOne cardsInDeck in the purple level 1 deck
     */
    public DevelopmentCardDeckJSON(int greenThree, int blueThree, int yellowThree, int purpleThree, int greenTwo, int blueTwo, int yellowTwo, int purpleTwo, int greenOne, int blueOne, int yellowOne, int purpleOne) {
        this.greenThree = greenThree;
        this.blueThree = blueThree;
        this.yellowThree = yellowThree;
        this.purpleThree = purpleThree;
        this.greenTwo = greenTwo;
        this.blueTwo = blueTwo;
        this.yellowTwo = yellowTwo;
        this.purpleTwo = purpleTwo;
        this.greenOne = greenOne;
        this.blueOne = blueOne;
        this.yellowOne = yellowOne;
        this.purpleOne = purpleOne;
    }
}
