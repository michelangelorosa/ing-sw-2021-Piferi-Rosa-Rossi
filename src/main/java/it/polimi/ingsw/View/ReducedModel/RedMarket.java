package it.polimi.ingsw.View.ReducedModel;

import it.polimi.ingsw.Model.Enums.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * Market Class defines the contents and behaviour of the game's market. A Market Class-type
 * object contains a 2D-array final attribute containing marbles and an extra marble to
 * push inside the market whenever a player buys resources.
 */
public class RedMarket implements Serializable {
    private static final long serialVersionUID = 0x1;

    protected final Marble[][] marbles;
    protected Marble extraMarble;

    protected RedMarket() {
        this.marbles = new Marble[3][4];
    }

    protected RedMarket(Marble[][] marbles) {
        this.marbles = marbles;
    }
    /**
     * Getter for "marbles" attribute.
     */
    public Marble[][] getMarbles() {
        return marbles;
    }

    /**
     * Gets a single marble from the market, given the parameters
     * @param marketColumn  Column of the market [0-3]
     * @param marketRow     Row of the market    [0-2]
     * @return  Marble corresponding to the coordinates
     */
    public Marble getMarble(int marketColumn,int marketRow){
        if(marketColumn>=0&&marketColumn<=3&&marketRow>=0&&marketRow<=2)
        return getMarbles()[marketRow][marketColumn];
        else
            return Marble.RED;
    }

    /**
     * Getter for "extraMarble" attribute.
     */
    public Marble getExtraMarble() {
        return extraMarble;
    }

    /**
     * toString override method for Market Class.
     */
    @Override
    public String toString() {
        return  marbles[0][0]+" "+marbles[0][1]+" "+marbles[0][2]+" "+marbles[0][3]+System.lineSeparator()+
                marbles[1][0]+" "+marbles[1][1]+" "+marbles[1][2]+" "+marbles[1][3]+System.lineSeparator()+
                marbles[2][0]+" "+marbles[2][1]+" "+marbles[2][2]+" "+marbles[2][3];
    }

    public ArrayList<String> toCli() {
        ArrayList<String> market = new ArrayList<>();

        for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 8; j++) {
                market.add("║ " + marbles[i][0].toCli().get(j) + " " + marbles[i][1].toCli().get(j) + " " + marbles[i][2].toCli().get(j) + " " + marbles[i][3].toCli().get(j) + "                 ║");
            }
            market.add("║                                                                                     ║");
        }

        for (int j = 7; j >= 0; j--) {
            market.add(0, "║                                                                    " + this.extraMarble.toCli().get(j) + " ║");
        }
        market.add(0, "╔═════════════════════════════════════════════════════════════════════════════════════╗");
        market.add( "╚═════════════════════════════════════════════════════════════════════════════════════╝");
        market.addAll(upArrowToCli());

        ArrayList<String> leftArrow = leftArrowToCli();
        for(int i = 8; i < leftArrow.size() + 8; i++)
            market.set(i, market.get(i) + leftArrow.get(i - 8));

        return market;
    }

    private static ArrayList<String> upArrowToCli() {
        ArrayList<String> arrow = new ArrayList<>();
        arrow.add("                ");
        arrow.add("      ████      ");
        arrow.add("    ██░░░░██    ");
        arrow.add("  ██▒▒▒▒▒▒░░██  ");
        arrow.add(" █▒▒▒▒▒▒▒▒▒▒░░█ ");
        arrow.add("      ████      ");
        arrow.add("      ▓▓▒▒      ");
        arrow.add("      ▓▓▒▒      ");
        arrow.add("      ▓▓▒▒      ");


        for(int j = 0; j < 9; j++)
            arrow.set(j, " "+arrow.get(j)+" "+arrow.get(j)+" "+arrow.get(j)+" "+arrow.get(j)+" ");

        return arrow;
    }

    private static ArrayList<String> leftArrowToCli() {
        ArrayList<String> arrow = new ArrayList<>();
        arrow.add("                    ");
        arrow.add("          ████      ");
        arrow.add("        ██▒▒██      ");
        arrow.add("      ██▒▒▒▒██      ");
        arrow.add("    ██░░▒▒▒▒██▒▒▒▒▒▒");
        arrow.add("    ██░░▒▒▒▒██▓▓▓▓▓▓");
        arrow.add("      ██░░▒▒██      ");
        arrow.add("        ██░░██      ");
        arrow.add("          ████      ");
        arrow.add("                    ");

        ArrayList<String> arrows = new ArrayList<>();
        for(int i = 0; i < 3; i++)
        arrows.addAll(arrow);

        return arrows;
    }
}
