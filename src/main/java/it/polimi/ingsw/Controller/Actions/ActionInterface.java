package it.polimi.ingsw.Controller.Actions;

import it.polimi.ingsw.Model.Game;

public interface ActionInterface {
    public boolean isCorrect();
    public boolean canBeApplied(Game game);
    public String doAction(Game game, ChooseProductionOutput chooseProductionOutput, ChooseCardSlot chooseCardSlot, ResetWarehouse resetWarehouse);
}
