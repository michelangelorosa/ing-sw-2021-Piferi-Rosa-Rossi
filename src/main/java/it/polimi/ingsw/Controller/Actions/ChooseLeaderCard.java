package it.polimi.ingsw.Controller.Actions;

import it.polimi.ingsw.Model.*;

public class ChooseLeaderCard {
    private final ActionType actionType;
    private final int leaderCard;

    public ChooseLeaderCard(int leaderCard) {
        this.actionType = ActionType.CHOOSE_LEADER_CARD;
        this.leaderCard = leaderCard;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public int getLeaderCard() {
        return leaderCard;
    }

    public boolean isCorrect() throws IllegalArgumentException {
        if(leaderCard != 0 && leaderCard != 1)
            throw new IllegalArgumentException("Leader Card index out of bounds");
        return true;
    }

    public boolean canBeApplied(Player player) {
        return player.getBoard().getLeaderCards()[leaderCard].isActive() && player.getBoard().getLeaderCards()[leaderCard].getAction() == LeaderCardAction.WHITEMARBLE;
    }

    public LeaderCard chooseLeaderCard(Game game) {
        this.isCorrect();
        if(!this.canBeApplied(game.getCurrentPlayer()))
            return null;

        return game.getCurrentPlayer().getBoard().getLeaderCards()[this.getLeaderCard()];
    }
}
