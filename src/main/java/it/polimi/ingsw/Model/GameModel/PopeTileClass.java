package it.polimi.ingsw.Model.GameModel;

import it.polimi.ingsw.Model.Enums.PopeTile;
import it.polimi.ingsw.View.ReducedModel.RedPopeTileClass;

/**
 * PopeTileClass Class adds methods to Pope Tiles to be used on the Model.
 * @author francescopiferi99
 */
public class PopeTileClass extends RedPopeTileClass {

    /**
     * Constructor for PopeTileClass.
     */
    public PopeTileClass(int victoryPoints) {
        this.popeTile = PopeTile.DOWN;
        this.victoryPoints = victoryPoints;
    }

    /**
     * Setter for "popeTile" attribute.
     */
    public void setPopeTile(PopeTile popeTile) {
        this.popeTile = popeTile;
    }
}
