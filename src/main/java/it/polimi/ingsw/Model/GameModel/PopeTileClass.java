package it.polimi.ingsw.Model.GameModel;

import it.polimi.ingsw.Model.Enums.PopeTile;
import it.polimi.ingsw.View.ReducedModel.RedPopeTileClass;

public class PopeTileClass extends RedPopeTileClass {

    public PopeTileClass(int victoryPoints) {
        this.popeTile = PopeTile.DOWN;
        this.victoryPoints = victoryPoints;
    }

    public void setPopeTile(PopeTile popeTile) {
        this.popeTile = popeTile;
    }

    /**Method for converting model classes to view classes*/
    public RedPopeTileClass toView() {
        return (RedPopeTileClass) this;
    }
}
