package it.polimi.ingsw.Controller.Actions;

import it.polimi.ingsw.Controller.ActionController;
import it.polimi.ingsw.Model.LeaderCard;
import it.polimi.ingsw.View.ReducedModel.RedLeaderCard;

public class InitChooseLeaderCards extends Action{
    private final RedLeaderCard leaderCard1;
    private final RedLeaderCard leaderCard2;

    public InitChooseLeaderCards(RedLeaderCard leaderCard1, RedLeaderCard leaderCard2) {
        this.leaderCard1 = leaderCard1;
        this.leaderCard2 = leaderCard2;
    }

    @Override
    public boolean isCorrect() throws IllegalArgumentException {
        if(this.leaderCard1 == null)
            throw new IllegalArgumentException("Error: Leader Card 1 is null");

        if(this.leaderCard2 == null)
            throw new IllegalArgumentException("Error: Leader Card 2 is null");

        return true;
    }

    @Override
    public String doAction(ActionController actionController) {
        if(!this.isCorrect())
            return null;

        actionController.getGame().getCurrentPlayer().getBoard().getLeaderCards()[0] = (LeaderCard)leaderCard1;
        actionController.getGame().getCurrentPlayer().getBoard().getLeaderCards()[1] = (LeaderCard)leaderCard2;

        return "SUCCESS";
    }

    public RedLeaderCard getLeaderCard1() {
        return leaderCard1;
    }

    public RedLeaderCard getLeaderCard2() {
        return leaderCard2;
    }
}
