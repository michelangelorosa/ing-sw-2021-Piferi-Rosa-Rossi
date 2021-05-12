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
public class Market implements Serializable {
    private static final long serialVersionUID = 0x1;

    private final Marble[][] marbles;
    private Marble extraMarble;

    /**
     * Constructor for Market Class.
     */
    public Market() {
        marbles = new Marble[3][4];
    }

    public Market(Marble[][] marbles) {
        this.marbles = marbles;
    }

    /**
     * Getter for "marbles" attribute.
     */
    public Marble[][] getMarbles() {
        return marbles;
    }

    /**
     * Getter for "extraMarble" attribute.
     */
    public Marble getExtraMarble() {
        return extraMarble;
    }

    public void setExtraMarble(Marble extraMarble) {
        this.extraMarble = extraMarble;
    }

    public void rowChange(int row) {
        Marble[] newMarbles = new Marble[4];
        newMarbles[0] = this.marbles[row][1];
        newMarbles[1] = this.marbles[row][2];
        newMarbles[2] = this.marbles[row][3];
        newMarbles[3] = extraMarble;

        extraMarble = this.marbles[row][0];
        this.marbles[row][0] = newMarbles[0];
        this.marbles[row][1] = newMarbles[1];
        this.marbles[row][2] = newMarbles[2];
        this.marbles[row][3] = newMarbles[3];
    }

    public void columnChange(int column) {
        Marble[] newMarbles = new Marble[3];
        newMarbles[0] = this.marbles[1][column];
        newMarbles[1] = this.marbles[2][column];
        newMarbles[2] = extraMarble;

        extraMarble = this.marbles[0][column];
        this.marbles[0][column] = newMarbles[0];
        this.marbles[1][column] = newMarbles[1];
        this.marbles[2][column] = newMarbles[2];
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
