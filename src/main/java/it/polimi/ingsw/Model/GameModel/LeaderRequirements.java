package it.polimi.ingsw.Model.GameModel;

import it.polimi.ingsw.View.ReducedModel.RedLeaderRequirements;

/**
 * Defines a Card with the characteristics required from Leader Card; a Level and a Colour.
 * If The cards doesn't require a minimum level it is set to 1
 * @author michelangelorosa, francescopiferi
 */
public class LeaderRequirements extends RedLeaderRequirements {

    /**Constructor for the requirements that need a specific number of card of a specific level*/
    public LeaderRequirements(int blueCardLv1, int purpleCardLv1, int yellowCardLv1, int greenCardLv1, int blueCardLv2, int purpleCardLv2, int yellowCardLv2, int greenCardLv2, int blueCardLv3, int purpleCardLv3, int yellowCardLv3, int greenCardLv3) {
        this.blueCardLv1 = blueCardLv1;
        this.purpleCardLv1 = purpleCardLv1;
        this.yellowCardLv1 = yellowCardLv1;
        this.greenCardLv1 = greenCardLv1;
        this.blueCardLv2 = blueCardLv2;
        this.purpleCardLv2 = purpleCardLv2;
        this.yellowCardLv2 = yellowCardLv2;
        this.greenCardLv2 = greenCardLv2;
        this.blueCardLv3 = blueCardLv3;
        this.purpleCardLv3 = purpleCardLv3;
        this.yellowCardLv3 = yellowCardLv3;
        this.greenCardLv3 = greenCardLv3;
        this.generic = false;
    }

    /**Constructor for the requirements that need a number of cards of a specific color ONLY*/
    public LeaderRequirements(int needBlueCard, int needPurpleCard, int needYellowCard, int needGreenCard){
        this.needBlueCard = needBlueCard;
        this.needPurpleCard = needPurpleCard;
        this.needYellowCard = needYellowCard;
        this.needGreenCard = needGreenCard;
        this.generic = true;
    }

    /**Method to check if the cards resources are enough*/
    public boolean hasRequirements(LeaderRequirements cardsHad) {
        if(!cardsHad.generic){
            return  cardsHad.getBlueCardLv1() >= this.blueCardLv1 && cardsHad.getBlueCardLv2() >= this.blueCardLv2 && cardsHad.getBlueCardLv3() >= this.blueCardLv3 &&
                    cardsHad.getPurpleCardLv1() >= this.purpleCardLv1 && cardsHad.getPurpleCardLv2() >= this.purpleCardLv2 && cardsHad.getPurpleCardLv3() >= this.purpleCardLv3 &&
                    cardsHad.getYellowCardLv1() >= this.yellowCardLv1 && cardsHad.getYellowCardLv2() >= this.yellowCardLv2 && cardsHad.getYellowCardLv3() >= this.yellowCardLv3 &&
                    cardsHad.getGreenCardLv1() >= this.greenCardLv1 && cardsHad.getGreenCardLv2() >= this.greenCardLv2 && cardsHad.getGreenCardLv3() >= this.greenCardLv3;
        }
        else return cardsHad.getNeedBlueCard() >= this.getNeedBlueCard() && cardsHad.getNeedPurpleCard() >= this.needPurpleCard && cardsHad.getNeedYellowCard() >= this.getNeedYellowCard() && cardsHad.getNeedGreenCard() >= this.getNeedGreenCard();
    }
}
