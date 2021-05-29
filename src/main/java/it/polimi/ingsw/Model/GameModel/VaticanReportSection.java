package it.polimi.ingsw.Model.GameModel;

import it.polimi.ingsw.View.ReducedModel.RedVaticanReportSection;

public class VaticanReportSection extends RedVaticanReportSection {
    private static final long serialVersionUID = 0x1;

    public VaticanReportSection(int begin, int end, int points) {
        super(begin, end, points);
    }

    /**Method to check if the the vatican section is correct*/
    public void isCorrect() throws  IllegalArgumentException{
        if(begin < end || begin < 0 || end < 0 || begin > 25 || end > 25) throw new IllegalArgumentException("Vatican Report Section index out of bound");
    }

    /**Method for converting model classes to view classes*/
    public RedVaticanReportSection toView() {
        return (RedVaticanReportSection)this;
    }
}
