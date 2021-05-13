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

    /**Method for converting model classes to view classes*/
    public it.polimi.ingsw.View.ReducedModel.PopeTileClass toView() {
        it.polimi.ingsw.View.ReducedModel.PopeTileClass popeTileClass = new it.polimi.ingsw.View.ReducedModel.PopeTileClass(this.victoryPoints);
        popeTileClass.setPopeTile(this.popeTile);

        return popeTileClass;
    }
}
