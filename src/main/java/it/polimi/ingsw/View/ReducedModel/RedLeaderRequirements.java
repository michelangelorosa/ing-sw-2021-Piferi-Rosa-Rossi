package it.polimi.ingsw.View.ReducedModel;

import it.polimi.ingsw.Model.GameModel.LeaderRequirements;

import java.io.Serializable;

/**
 * Defines a Card with the characteristics required from Leader Card; a Level and a Colour.
 * If The cards doesn't require a minimum level it is set to 1
 */
public class RedLeaderRequirements implements Serializable {
    private static final long serialVersionUID = 0x1;

    protected int blueCardLv1;
    protected int purpleCardLv1;
    protected int yellowCardLv1;
    protected int greenCardLv1;
    protected int blueCardLv2;
    protected int purpleCardLv2;
    protected int yellowCardLv2;
    protected int greenCardLv2;
    protected int blueCardLv3;
    protected int purpleCardLv3;
    protected int yellowCardLv3;
    protected int greenCardLv3;
    protected int needBlueCard;
    protected int needPurpleCard;
    protected int needYellowCard;
    protected int needGreenCard;
    protected boolean generic;

    public RedLeaderRequirements() {

    }

    /**Getters*/
    public int getBlueCardLv1() {
        return blueCardLv1;
    }

    public int getPurpleCardLv1() {
        return purpleCardLv1;
    }

    public int getYellowCardLv1() {
        return yellowCardLv1;
    }

    public int getGreenCardLv1() {
        return greenCardLv1;
    }

    public int getBlueCardLv2() {
        return blueCardLv2;
    }

    public int getPurpleCardLv2() {
        return purpleCardLv2;
    }

    public int getYellowCardLv2() {
        return yellowCardLv2;
    }

    public int getGreenCardLv2() {
        return greenCardLv2;
    }

    public int getBlueCardLv3() {
        return blueCardLv3;
    }

    public int getPurpleCardLv3() {
        return purpleCardLv3;
    }

    public int getYellowCardLv3() {
        return yellowCardLv3;
    }

    public int getGreenCardLv3() {
        return greenCardLv3;
    }

    public int getNeedBlueCard() {
        return needBlueCard;
    }

    public int getNeedPurpleCard() {
        return needPurpleCard;
    }

    public int getNeedYellowCard() {
        return needYellowCard;
    }

    public int getNeedGreenCard() {
        return needGreenCard;
    }

    public boolean getGeneric(){
        return generic;
    }

    /**
     * Checks if the requirements are valid
     * @param leaderRequirements        the requirements to check
     * @return                          true if the params are valid, false otherwise
     */
    public boolean isValid(LeaderRequirements leaderRequirements){
        if(     leaderRequirements.blueCardLv1<0||
                leaderRequirements.purpleCardLv1<0||
                leaderRequirements.yellowCardLv1<0||
                leaderRequirements.greenCardLv1<0||
                leaderRequirements.blueCardLv2<0||
                leaderRequirements.purpleCardLv2<0||
                leaderRequirements.yellowCardLv2<0||
                leaderRequirements.greenCardLv2<0||
                leaderRequirements.blueCardLv3<0||
                leaderRequirements.purpleCardLv3<0||
                leaderRequirements.yellowCardLv3<0||
                leaderRequirements.greenCardLv3<0||
                leaderRequirements.needBlueCard<0||
                leaderRequirements.needPurpleCard<0||
                leaderRequirements.needYellowCard<0||
                leaderRequirements.needGreenCard<0)
            if(     leaderRequirements.blueCardLv1>4||
                    leaderRequirements.purpleCardLv1>4||
                    leaderRequirements.yellowCardLv1>4||
                    leaderRequirements.greenCardLv1>4||
                    leaderRequirements.blueCardLv2>4||
                    leaderRequirements.purpleCardLv2>4||
                    leaderRequirements.yellowCardLv2>4||
                    leaderRequirements.greenCardLv2>4||
                    leaderRequirements.blueCardLv3>4||
                    leaderRequirements.purpleCardLv3>4||
                    leaderRequirements.yellowCardLv3>4||
                    leaderRequirements.greenCardLv3>4||
                    leaderRequirements.needBlueCard>4||
                    leaderRequirements.needPurpleCard>4||
                    leaderRequirements.needYellowCard>4||
                    leaderRequirements.needGreenCard>4)
                return false;
            return true;
    }
}