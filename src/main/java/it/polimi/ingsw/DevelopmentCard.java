package it.polimi.ingsw;

public class DevelopmentCard {
    enum Color {
        BLUE, PURPLE, YELLOW, GREEN;

        private static final Color[] colors = Color.values();
        public static Color getColor(int i) { return Color.colors[i]; }
    }

    enum Level {
        ONE, TWO, THREE;

        private static final Level[] levels = Level.values();
        public static Level getLevel(int i) { return Level.levels[i]; }
    }

    private final Color color;
    private final Level level;

    private final int cardId;
    private int victoryPoints;

    private int costShields;
    private int costServants;
    private int costCoins;
    private int costStones;

    private int inputShields;
    private int inputServants;
    private int inputCoins;
    private int inputStones;

    private int outputShields;
    private int outputServants;
    private int outputCoins;
    private int outputStones;

    private int outputFaith;


    public DevelopmentCard(final Color color, final Level level, final int cardId, int victoryPoints, int costShields, int costServants, int costCoins, int costStones, int inputShields, int inputServants, int inputCoins, int inputStones, int outputShields, int outputServants, int outputCoins, int outputStones, int outputFaith) {
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

    public int getCardId() {
        return cardId;
    }

    public int getVictoryPoints() {
        return victoryPoints;
    }

    public void setVictoryPoints(int victoryPoints) {
        this.victoryPoints = victoryPoints;
    }

    public int getCostShields() {
        return costShields;
    }

    public void setCostShields(int costShields) {
        this.costShields = costShields;
    }

    public int getCostServants() {
        return costServants;
    }

    public void setCostServants(int costServants) {
        this.costServants = costServants;
    }

    public int getCostCoins() {
        return costCoins;
    }

    public void setCostCoins(int costCoins) {
        this.costCoins = costCoins;
    }

    public int getCostStones() {
        return costStones;
    }

    public void setCostStones(int costStones) {
        this.costStones = costStones;
    }

    public int getInputShields() {
        return inputShields;
    }

    public void setInputShields(int inputShields) {
        this.inputShields = inputShields;
    }

    public int getInputServants() {
        return inputServants;
    }

    public void setInputServants(int inputServants) {
        this.inputServants = inputServants;
    }

    public int getInputCoins() {
        return inputCoins;
    }

    public void setInputCoins(int inputCoins) {
        this.inputCoins = inputCoins;
    }

    public int getInputStones() {
        return inputStones;
    }

    public void setInputStones(int inputStones) {
        this.inputStones = inputStones;
    }

    public int getOutputShields() {
        return outputShields;
    }

    public void setOutputShields(int outputShields) {
        this.outputShields = outputShields;
    }

    public int getOutputServants() {
        return outputServants;
    }

    public void setOutputServants(int outputServants) {
        this.outputServants = outputServants;
    }

    public int getOutputCoins() {
        return outputCoins;
    }

    public void setOutputCoins(int outputCoins) {
        this.outputCoins = outputCoins;
    }

    public int getOutputStones() {
        return outputStones;
    }

    public void setOutputStones(int outputStones) {
        this.outputStones = outputStones;
    }

    public int getOutputFaith() {
        return outputFaith;
    }

    public void setOutputFaith(int outputFaith) {
        this.outputFaith = outputFaith;
    }
}
