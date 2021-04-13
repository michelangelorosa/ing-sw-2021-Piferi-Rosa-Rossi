package it.polimi.ingsw.Controller.Actions;

import it.polimi.ingsw.Model.*;

public class MarketChooseRow {
    private final ActionType action;
    private final boolean row;
    private final int rowOrColumnNumber;
    private final boolean leaderCard;
    private final int leaderCardPosition;

    public MarketChooseRow(boolean row, int rowOrColumnNumber, boolean leaderCard, int leaderCardPosition) {
        this.action = ActionType.MARKET_CHOOSE_ROW;
        this.row = row;
        this.rowOrColumnNumber = rowOrColumnNumber;
        this.leaderCard = leaderCard;
        this.leaderCardPosition = leaderCardPosition;
    }

    public ActionType getAction() {
        return action;
    }

    public boolean isRow() {
        return row;
    }

    public int getRowOrColumnNumber() {
        return rowOrColumnNumber;
    }

    public boolean isLeaderCard() {
        return leaderCard;
    }

    public int getLeaderCardPosition() {
        return leaderCardPosition;
    }

    public boolean isCorrect() throws IllegalArgumentException {
        if(rowOrColumnNumber < 0 || (row && rowOrColumnNumber > 2) || (!row && rowOrColumnNumber > 3))
            throw new IllegalArgumentException("Row or Column index out of bounds.");

        return true;
    }

    public boolean canBeApplied(Player player) {
        return !leaderCard || (player.getBoard().getLeaderCards()[leaderCardPosition].isActive() && player.getBoard().getLeaderCards()[leaderCardPosition].getAction() == LeaderCardAction.WHITEMARBLE);
    }

    public String marketChooseRow(ChooseLeaderCard choice, ResetWarehouse resetWarehouse, Game game) {
        this.isCorrect();

        if(!this.canBeApplied(game.getCurrentPlayer()))
            return "Leader Card is not active or not of right type";

        LeaderCard leaderCard;
        LeaderCard card1 = game.getCurrentPlayer().getBoard().getLeaderCards()[0];
        LeaderCard card2 = game.getCurrentPlayer().getBoard().getLeaderCards()[1];

        if(this.leaderCard)
            leaderCard = game.getCurrentPlayer().getBoard().getLeaderCards()[this.leaderCardPosition];
        else if(card1.isActive() && card1.getAction() == LeaderCardAction.WHITEMARBLE && card2.isActive() && card2.getAction() == LeaderCardAction.WHITEMARBLE)
            leaderCard = choice.chooseLeaderCard(game);
        else
            leaderCard = null;

        if(this.row)
            game.getMarket().rowToResources(this.getRowOrColumnNumber(), game.getCurrentPlayer(), leaderCard);
        else
            game.getMarket().columnToResources(this.getRowOrColumnNumber(), game.getCurrentPlayer(), leaderCard);

        resetWarehouse.setBackupWarehouse(game.getCurrentPlayer().getBoard().getResourceManager().getWarehouse().copyWarehouse());
        resetWarehouse.setBackupResources(game.getCurrentPlayer().getBoard().getResourceManager().getTemporaryResourcesToPay().copyStack());

        return "SUCCESS";
    }
}
