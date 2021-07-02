package it.polimi.ingsw.View.ReducedModel;


import it.polimi.ingsw.Model.Enums.PopeTile;
import it.polimi.ingsw.View.Utility.ANSIColors;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Contains all the information regarding a PopeTile
 */
public class RedPopeTileClass implements Serializable {
    protected PopeTile popeTile;
    protected int victoryPoints;

    /**
     * Getter for a RedPopeTile
     * @return  PopeTile
     */
    public PopeTile getPopeTile() {
        return popeTile;
    }

    /**
     * Gets the VictoryPoints of the PopeTile
     * @return  number of victory points given by the tile
     */
    public int getVictoryPoints() {
        return victoryPoints;
    }

    /**
     * Setter for the PopeTile
     * @param popeTile
     */
    public void setPopeTile(PopeTile popeTile) {
        this.popeTile = popeTile;
    }

    /**
     * Sets the victory points of the popetile
     * @param victoryPoints
     */
    public void setVictoryPoints(int victoryPoints) {
        this.victoryPoints = victoryPoints;
    }

    /**
     * Method used to convert a popetile into an ArrayList of strings for the cli to be shown
     * @return  ArrayList of the given pope tile information
     */
    public ArrayList<String> toCli(){
        ArrayList<String> favorTile = new ArrayList<>();
        String r = ANSIColors.RESET;
        String c = ANSIColors.FRONT_RED;

        switch (this.popeTile) {
            case No:
                favorTile.add("╔═  ══  ══  ══  ═╗");
                favorTile.add("                  ");
                favorTile.add("║                ║");
                favorTile.add("                  ");
                favorTile.add("║                ║");
                favorTile.add("                  ");
                favorTile.add("╚═  ══  ══  ══  ═╝");
                break;
            case UP:
                favorTile.add("╔════════════════╗");
                favorTile.add("║  POPE SECTION  ║");
                favorTile.add("╠════════════════╣");
                favorTile.add("║      +" + pointsToCli() + "       ║");
                favorTile.add("╠════════════════╣");
                favorTile.add("║  POPE SECTION  ║");
                favorTile.add("╚════════════════╝");
                break;
            case DOWN:
                favorTile.add(c + "╔════════════════╗" + r);
                favorTile.add(c + "║  POPE SECTION  ║" + r);
                favorTile.add(c + "╠════════════════╣" + r);
                favorTile.add(c + "║ X    +" + pointsToCli() + "     X ║" + r);
                favorTile.add(c + "╠════════════════╣" + r);
                favorTile.add(c + "║  POPE SECTION  ║" + r);
                favorTile.add(c + "╚════════════════╝" + r);
                break;
        }
        return favorTile;
    }

    /**Method to print the number of victory points as a String*/
    public String pointsToCli(){
        String points;
        if(this.victoryPoints < 9) points = " " + this.victoryPoints;
        else points = ""+this.victoryPoints;
        return points;
    }
}
