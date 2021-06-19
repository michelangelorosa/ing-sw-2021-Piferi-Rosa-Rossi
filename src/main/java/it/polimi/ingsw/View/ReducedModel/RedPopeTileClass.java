package it.polimi.ingsw.View.ReducedModel;


import it.polimi.ingsw.Model.Enums.PopeTile;
import it.polimi.ingsw.View.Utility.ANSIColors;

import java.io.Serializable;
import java.util.ArrayList;

public class RedPopeTileClass implements Serializable {
    private static final long serialVersionUID = 0x1;

    protected PopeTile popeTile;
    protected int victoryPoints;

    /**Getter and setter*/
    public PopeTile getPopeTile() {
        return popeTile;
    }

    public int getVictoryPoints() {
        return victoryPoints;
    }

    public void setPopeTile(PopeTile popeTile) {
        this.popeTile = popeTile;
    }

    public void setVictoryPoints(int victoryPoints) {
        this.victoryPoints = victoryPoints;
    }


    /**Method to save in anArrayList the pope tile*/
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
