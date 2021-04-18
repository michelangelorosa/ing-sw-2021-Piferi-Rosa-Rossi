package it.polimi.ingsw.Controller.Actions;

import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.ResourceType;

public class EndMarket extends Action implements ActionInterface {

    public EndMarket() {
        this.actionType = ActionType.END_MARKET;
    }

    @Override
    public boolean isCorrect() {
        return true;
    }

    @Override
    public boolean canBeApplied(Game game) {
        return true;
    }

    @Override
    public String doAction(Game game, ChooseProductionOutput chooseProductionOutput, ChooseCardSlot chooseCardSlot, ResetWarehouse resetWarehouse) {
        ResourceType[] resourceTypes = ResourceType.values();
        game.getCurrentPlayer().getBoard().getResourceManager().remainingResourcesToFaith(game.getCurrentPlayer(), game.getPlayers(), game.getFaithTrack());
        resetWarehouse.emptyBackupResource();

        return "SUCCESS";
    }
}
