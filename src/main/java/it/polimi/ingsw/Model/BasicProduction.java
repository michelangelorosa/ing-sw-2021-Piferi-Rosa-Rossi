package it.polimi.ingsw.Model;

public class BasicProduction {
    private final ResourceStack fixedInputs;
    private final ResourceStack fixedOutputs;

    private final int jollyIn;
    private final int jollyOut;

    private final int outputFaith;

    public BasicProduction(ResourceStack fixedInputs, ResourceStack fixedOutputs, int jollyIn, int jollyOut, int outputFaith) {
        this.fixedInputs = fixedInputs;
        this.fixedOutputs = fixedOutputs;
        this.jollyIn = jollyIn;
        this.jollyOut = jollyOut;
        this.outputFaith = outputFaith;
    }

    public ResourceStack getFixedInputs() {
        return fixedInputs;
    }

    public ResourceStack getFixedOutputs() {
        return fixedOutputs;
    }

    public int getJollyIn() {
        return jollyIn;
    }

    public int getJollyOut() {
        return jollyOut;
    }

    public int getOutputFaith() {
        return outputFaith;
    }
}
