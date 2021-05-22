package it.polimi.ingsw.View.ReducedModel;

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
}