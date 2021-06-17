package it.polimi.ingsw.ModelTest.MessageToClientTest;

import it.polimi.ingsw.CommonTestMethods;
import it.polimi.ingsw.Controller.ControllerClasses.ActionController;
import it.polimi.ingsw.Model.MessagesToClient.OtherMessages.DisconnectedMessage;
import it.polimi.ingsw.View.ReducedModel.Game;
import it.polimi.ingsw.View.ReducedModel.Player;
import it.polimi.ingsw.View.User.CliController;
import it.polimi.ingsw.View.User.UserInteraction;
import org.junit.Test;

public class DisconnectedMessageTest {

    @Test
    public void test() {
        ActionController actionController = new ActionController();
        CommonTestMethods.gameInitOne(actionController.getGame());
        DisconnectedMessage message = new DisconnectedMessage(actionController, "antonino");
        UserInteraction userInteraction = new UserInteraction() {
        };
        userInteraction.setGame(new Game());
        userInteraction.setUi(new CliController());
        userInteraction.getGame().getPlayers().add(new Player("Zero", 0, true));

        message.updateView(userInteraction);
    }
}
