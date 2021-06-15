package it.polimi.ingsw.Model.MessagesToClient;

import it.polimi.ingsw.Controller.Actions.ActionType;
import it.polimi.ingsw.Model.Enums.SoloActionToken;
import it.polimi.ingsw.View.User.UIActions;
import it.polimi.ingsw.View.User.UserInteraction;

public class EndTurnSingleplayerMessage extends MessageToClient{
    private SoloActionToken token;
    private int victoryPoints;
    private int lorenzoFaith;

    public EndTurnSingleplayerMessage(String playerNickname) {
        super(playerNickname);
        this.actionDone = ActionType.END_TURN_SINGLEPLAYER;
    }

    public SoloActionToken getToken() {
        return token;
    }

    public void setToken(SoloActionToken token) {
        this.token = token;
    }

    public int getVictoryPoints() {
        return victoryPoints;
    }

    public void setVictoryPoints(int victoryPoints) {
        this.victoryPoints = victoryPoints;
    }

    public int getLorenzoFaith() {
        return lorenzoFaith;
    }

    public void setLorenzoFaith(int lorenzoFaith) {
        this.lorenzoFaith = lorenzoFaith;
    }

    @Override
    public void updateView(UserInteraction userInteraction) {
        super.updateView(userInteraction);

        switch (this.error) {
            case "SINGLEPLAYER":
                userInteraction.setLorenzoToken(this.token);
                userInteraction.getGame().getPlayers().get(1).setFaithTrackPosition(this.lorenzoFaith);
                userInteraction.nextAction(UIActions.SINGLEPLAYER_TURN);
                break;
            case "SINGLEPLAYER LOOSE":
                userInteraction.nextAction(UIActions.SINGLEPLAYER_END_LOST);
                break;
            case "SINGLEPLAYER WIN":
                userInteraction.nextAction(UIActions.SINGLEPLAYER_END_WON);
                break;
        }
    }
}
