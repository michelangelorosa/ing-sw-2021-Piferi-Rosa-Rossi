package it.polimi.ingsw.Controller.Actions;

import it.polimi.ingsw.Controller.ActionController;
import it.polimi.ingsw.View.ReducedModel.LeaderCard;

public class InitChooseLeaderCards extends Action{
    private final LeaderCard leaderCard1;
    private final LeaderCard leaderCard2;

    public InitChooseLeaderCards(LeaderCard leaderCard1, LeaderCard leaderCard2) {
        this.leaderCard1 = leaderCard1;
        this.leaderCard2 = leaderCard2;
    }

    @Override
    public String doAction(ActionController actionController) {
        actionController.getGame().getCurrentPlayer().getBoard().getLeaderCards()[0] = it.polimi.ingsw.Model.LeaderCard.toModel(leaderCard1);
        actionController.getGame().getCurrentPlayer().getBoard().getLeaderCards()[1] = it.polimi.ingsw.Model.LeaderCard.toModel(leaderCard2);

        return "SUCCESS";
    }

    public LeaderCard getLeaderCard1() {
        return leaderCard1;
    }

    public LeaderCard getLeaderCard2() {
        return leaderCard2;
    }
}
