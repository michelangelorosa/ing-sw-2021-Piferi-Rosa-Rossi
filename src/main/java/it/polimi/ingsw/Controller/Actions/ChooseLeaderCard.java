package it.polimi.ingsw.Controller.Actions;

import it.polimi.ingsw.Model.*;

public class ChooseLeaderCard extends Action implements ActionInterface {
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

    @Override
    public boolean isCorrect() throws IllegalArgumentException {
        if(leaderCard != 0 && leaderCard != 1)
            throw new IllegalArgumentException("Leader Card index out of bounds");
        return true;
    }

    @Override
    public boolean canBeApplied(Game game) {
        Player player = game.getCurrentPlayer();
        return player.getBoard().getLeaderCards()[leaderCard].isActive() && player.getBoard().getLeaderCards()[leaderCard].getAction() == LeaderCardAction.WHITEMARBLE;
    }

    @Override
    public String doAction(Game game, ChooseProductionOutput chooseProductionOutput, ChooseCardSlot chooseCardSlot, ResetWarehouse resetWarehouse) {
        this.isCorrect();
        if(!this.canBeApplied(game))
            return "Leader Card not active or not of type WHITE MARBLE";

        game.getMarket().whiteMarbleToResource(game.getCurrentPlayer(), game.getCurrentPlayer().getBoard().getLeaderCards()[leaderCard]);

        if(game.getCurrentPlayer().getBoard().getResourceManager().getTemporaryWhiteMarbles() > 0)
            return "Another White Marble";
        else
            return "SUCCESS";
    }
}
