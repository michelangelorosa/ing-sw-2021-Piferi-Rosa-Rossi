package it.polimi.ingsw.Model;

public class DevelopmentCardJSON {

    /**This class is use to create the file "DevelopmentCards.json"*/
    int color, level, cardId, victoryPoints, costShields, costServants, costCoins, costStones, inputShields, inputServants, inputCoins, inputStones, outputShields, outputServants, outputCoins, outputStones, outputFaith;

    public DevelopmentCardJSON(int color, int level, int cardId, int victoryPoints, int costShields, int costServants, int costCoins, int costStones, int inputShields, int inputServants, int inputCoins, int inputStones, int outputShields, int outputServants, int outputCoins, int outputStones, int outputFaith) {
        this.color = color;
        this.level = level;
        this.cardId = cardId;
        this.victoryPoints = victoryPoints;
        this.costShields = costShields;
        this.costServants = costServants;
        this.costCoins = costCoins;
        this.costStones = costStones;
        this.inputShields = inputShields;
        this.inputServants = inputServants;
        this.inputCoins = inputCoins;
        this.inputStones = inputStones;
        this.outputShields = outputShields;
        this.outputServants = outputServants;
        this.outputCoins = outputCoins;
        this.outputStones = outputStones;
        this.outputFaith = outputFaith;
    }
}