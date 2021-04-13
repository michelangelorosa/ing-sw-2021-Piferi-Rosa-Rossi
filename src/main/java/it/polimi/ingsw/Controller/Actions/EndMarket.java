package it.polimi.ingsw.Controller.Actions;

import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.ResourceType;

public class EndMarket {

    public EndMarket() {

    }

    public String remainingResourcesToFaith(Game game, ResetWarehouse resetWarehouse) {
        ResourceType[] resourceTypes = ResourceType.values();
        game.getCurrentPlayer().getBoard().getResourceManager().remainingResourcesToFaith(game.getCurrentPlayer(), game.getPlayers(), game.getFaithTrack());

        resetWarehouse.getBackupWarehouse().reset();
        for(int i = 1; i <= 4; i++)
            resetWarehouse.getBackupResources().setResource(0, resourceTypes[i]);

        return "SUCCESS";
    }
}
