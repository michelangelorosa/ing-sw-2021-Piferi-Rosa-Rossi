package it.polimi.ingsw.Model.GameModel;

import it.polimi.ingsw.View.ReducedModel.RedVaticanReportSection;

/**
 * VaticanReportSection Class adds methods to be used on Vatican Report Sections on the Model.
 * @author francescopiferi99
 */
public class VaticanReportSection extends RedVaticanReportSection {
    public VaticanReportSection(int begin, int end, int points) {
        super(begin, end, points);
    }

    /**Checks if the the vatican section is correct*/
    public void isCorrect() throws  IllegalArgumentException {
        if(begin > end || begin < 0 || begin > 25 || end > 25) throw new IllegalArgumentException("Vatican Report Section index out of bound");
    }
}
