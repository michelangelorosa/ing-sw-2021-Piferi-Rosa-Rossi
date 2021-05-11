package it.polimi.ingsw.Model;

import it.polimi.ingsw.Model.Enums.PopeTile;

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



}
