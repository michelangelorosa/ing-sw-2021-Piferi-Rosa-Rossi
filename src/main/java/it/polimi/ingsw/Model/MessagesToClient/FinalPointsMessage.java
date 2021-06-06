package it.polimi.ingsw.Model.MessagesToClient;

import it.polimi.ingsw.Model.Enums.PlayerStatus;
import it.polimi.ingsw.View.ReducedModel.Player;
import it.polimi.ingsw.View.User.UIActions;
import it.polimi.ingsw.View.User.UserInteraction;

import java.util.ArrayList;
import java.util.HashMap;

public class FinalPointsMessage extends MessageToClient {
    private HashMap<String, Integer> nicknamePoints;
    private final ArrayList<String> winningPlayers = new ArrayList<>();

    public FinalPointsMessage(String playerNickname) {
        super(playerNickname);
        this.error = "SUCCESS";
    }

    public HashMap<String, Integer> getNicknamePoints() {
        return nicknamePoints;
    }

    public void setNicknamePoints(HashMap<String, Integer> nicknamePoints) {
        this.nicknamePoints = nicknamePoints;
    }

    public ArrayList<String> getWinningPlayers() {
        return winningPlayers;
    }

    public void addWinningPlayers(String winningPlayer) {
        this.winningPlayers.add(winningPlayer);
    }

    @Override
    public void updateView(UserInteraction userInteraction) {
        super.updateView(userInteraction);

        for(Player player : userInteraction.getGame().getPlayers()) {
            player.setVictoryPoints(this.nicknamePoints.get(player.getNickname()));
            if(this.winningPlayers.contains(player.getNickname()))
                player.setStatus(PlayerStatus.WON);
            else
                player.setStatus(PlayerStatus.LOST);
        }

        userInteraction.nextAction(UIActions.FINAL_POINTS);
    }
}
