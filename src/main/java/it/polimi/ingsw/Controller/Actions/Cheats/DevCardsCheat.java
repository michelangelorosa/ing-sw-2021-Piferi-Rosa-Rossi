package it.polimi.ingsw.Controller.Actions.Cheats;

import it.polimi.ingsw.Controller.Actions.Action;
import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.Controller.ControllerClasses.ActionController;
import it.polimi.ingsw.Model.Enums.Color;
import it.polimi.ingsw.Model.Enums.Level;
import it.polimi.ingsw.Model.GameModel.DevelopmentCard;
import it.polimi.ingsw.Model.GameModel.DevelopmentCardSlots;
import it.polimi.ingsw.Model.GameModel.ResourceStack;
import it.polimi.ingsw.Model.MessagesToClient.MessageToClient;
import it.polimi.ingsw.Model.Server.Server;


public class DevCardsCheat extends Action {

    public DevCardsCheat() {
        this.actionType = ActionType.DEVCARDS_CHEAT;
    }

    private void setCards(DevelopmentCardSlots slots) {
        ResourceStack stack = new ResourceStack(1, 0, 0, 0);
        slots.getSlots()[0].getCards()[0] = new DevelopmentCard(Color.BLUE, Level.ONE, 1000, 5, stack, stack, stack, 1);
        slots.getSlots()[0].getCards()[1] = new DevelopmentCard(Color.BLUE, Level.TWO, 1001, 5, stack, stack, stack, 1);
        slots.getSlots()[0].getCards()[2] = new DevelopmentCard(Color.BLUE, Level.THREE, 1002, 5, stack, stack, stack, 1);
        slots.getSlots()[0].setLevelOccupied(3);

        slots.getSlots()[1].getCards()[0] = new DevelopmentCard(Color.YELLOW, Level.ONE, 1003, 5, stack, stack, stack, 1);
        slots.getSlots()[1].getCards()[1] = new DevelopmentCard(Color.YELLOW, Level.TWO, 1004, 5, stack, stack, stack, 1);
        slots.getSlots()[1].getCards()[2] = new DevelopmentCard(Color.YELLOW, Level.THREE, 1005, 5, stack, stack, stack, 1);
        slots.getSlots()[1].setLevelOccupied(3);
    }

    @Override
    public String doAction(ActionController actionController) {
        if(Server.isCheatMode()) {
            setCards(actionController.getGame().getCurrentPlayer().getBoard().getDevelopmentCardSlots());
            this.response = "SUCCESS";
            return "SUCCESS";
        }
        else
            this.response = "{ ! CHEAT MODE NOT ACTIVATED ! }";
        return "{ ! CHEAT MODE NOT ACTIVATED ! }";
    }

    @Override
    public MessageToClient messagePrepare(ActionController actionController) {
        return this.cheatMessage(actionController);
    }
}
