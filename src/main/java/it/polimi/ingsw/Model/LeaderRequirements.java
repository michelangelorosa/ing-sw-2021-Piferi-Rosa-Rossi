package it.polimi.ingsw.Model;
/**
 * Defines a Card with the characteristics required from Leader Card; a Level and a Colour
 */
public class LeaderRequirements {
  private final int blueCardLv1;
  private final int purpleCardLv1;
  private final int yellowCardLv1;
  private final int greenCardLv1;
  private final int blueCardLv2;
  private final int purpleCardLv2;
  private final int yellowCardLv2;
  private final int greenCardLv2;
  private final int blueCardLv3;
  private final int purpleCardLv3;
  private final int yellowCardLv3;
  private final int greenCardLv3;

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
    }

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

    public boolean hasRequirements(LeaderRequirements cardsHad) {
        return  cardsHad.getBlueCardLv1() >= this.blueCardLv1 && cardsHad.getBlueCardLv2() >= this.blueCardLv2 && cardsHad.getBlueCardLv3() >= this.blueCardLv3 &&
                cardsHad.getPurpleCardLv1() >= this.purpleCardLv1 && cardsHad.getPurpleCardLv2() >= this.purpleCardLv2 && cardsHad.getPurpleCardLv3() >= this.purpleCardLv3 &&
                cardsHad.getYellowCardLv1() >= this.yellowCardLv1 && cardsHad.getYellowCardLv2() >= this.yellowCardLv2 && cardsHad.getYellowCardLv3() >= this.yellowCardLv3 &&
                cardsHad.getGreenCardLv1() >= this.greenCardLv1 && cardsHad.getGreenCardLv2() >= this.greenCardLv2 && cardsHad.getGreenCardLv3() >= this.greenCardLv3;
    }

}