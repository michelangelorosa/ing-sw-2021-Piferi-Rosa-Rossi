package it.polimi.ingsw.View.ReducedModel;


import it.polimi.ingsw.View.ANSIColors;
import it.polimi.ingsw.View.ReducedModel.Enums.PopeTile;
import it.polimi.ingsw.View.ReducedModel.Game;
import it.polimi.ingsw.View.ReducedModel.VaticanReportSection;

import java.util.ArrayList;

public class PopeTileClass {
    private PopeTile popeTile;
    private final int victoryPoints;

    public PopeTileClass(int victoryPoints) {
        this.popeTile = PopeTile.DOWN;
        this.victoryPoints = victoryPoints;
    }

    public PopeTile getPopeTile() {
        return popeTile;
    }

    public void setPopeTile(PopeTile popeTile) {
        this.popeTile = popeTile;
    }

    public int getVictoryPoints() {
        return victoryPoints;
    }

    public ArrayList<String> toCli(int position){
        ArrayList<String> favorTile = new ArrayList<>();
        String r = ANSIColors.RESET;
        String c = ANSIColors.FRONT_RED;

        switch (this.popeTile) {
            case No:
                favorTile.add(c+"╔════════════════╗"+r);
                favorTile.add(c+"║                ║"+r);
                favorTile.add(c+"║                ║"+r);
                favorTile.add(c+"║                ║"+r);
                favorTile.add(c+"║                ║"+r);
                favorTile.add(c+"║                ║"+r);
                favorTile.add(c+"╚════════════════╝"+r);
                break;
            case UP:
                favorTile.add(c+"╔════════════════╗"+r);
                favorTile.add(c+"║  POPE SECTION  ║"+r);
                favorTile.add(c+"╠════════════════╣"+r);
                favorTile.add(c+"║      +"+pointsToCli()+"       ║"+r);
                favorTile.add(c+"╠════════════════╣"+r);
                favorTile.add(c+"║  POPE SECTION  ║"+r);
                favorTile.add(c+"╚════════════════╝"+r);
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

    public String pointsToCli(){
        String points = " ";
        if(this.victoryPoints < 9) points = " " + this.victoryPoints;
        else points = ""+this.victoryPoints;
        return points;
    }
}
