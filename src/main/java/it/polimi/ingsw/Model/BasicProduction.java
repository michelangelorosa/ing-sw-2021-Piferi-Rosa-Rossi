package it.polimi.ingsw.Model;

import java.io.Serializable;

/**
 * BasicProduction Class defines inputs and outputs of a basic production.
 */
public class BasicProduction implements Serializable {
    private static final long serialVersionUID = 0x1;

    /** Fixed inputs and outputs can be added before playing the game */
    private final ResourceStack fixedInputs;
    private final ResourceStack fixedOutputs;

    /** Jollies represent inputs and outputs the players can choose when starting the Production. */
    private final int jollyIn;
    private final int jollyOut;

    private final int outputFaith;

    /**
     * Constructor for Standard BasicProduction Class.
     * @param jollyIn Number of input resources to be chosen.
     * @param jollyOut Number of output resources to be chosen.
     */
    public BasicProduction(int jollyIn, int jollyOut) {
        fixedInputs = new ResourceStack(0,0,0,0);
        fixedOutputs = new ResourceStack(0,0,0,0);
        this.jollyIn = jollyIn;
        this.jollyOut = jollyOut;
        this.outputFaith = 0;
    }

    /**
     * Constructor for Parametric BasicProduction Class.
     * @param jollyIn Number of input resources to be chosen.
     * @param jollyOut Number of output resources to be chosen.
     * @param fixedInputs ResourceStack containing fixed input resources.
     * @param fixedOutputs ResourceStack containing fixed output resources.
     * @param outputFaith Number of faith points given when completing the basic production.
     */
    public BasicProduction(ResourceStack fixedInputs, ResourceStack fixedOutputs, int jollyIn, int jollyOut, int outputFaith) {
        this.fixedInputs = fixedInputs;
        this.fixedOutputs = fixedOutputs;
        this.jollyIn = jollyIn;
        this.jollyOut = jollyOut;
        this.outputFaith = outputFaith;
    }

    /**
     * Getter for "fixedInputs" attribute in BasicProduction Class.
     */
    public ResourceStack getFixedInputs() {
        return fixedInputs;
    }

    /**
     * Getter for "fixedInputs" attribute in BasicProduction Class.
     */
    public ResourceStack getFixedOutputs() {
        return fixedOutputs;
    }

    /**
     * Getter for "jollyIn" attribute in BasicProduction Class.
     */
    public int getJollyIn() {
        return jollyIn;
    }

    /**
     * Getter for "jollyOut" attribute in BasicProduction Class.
     */
    public int getJollyOut() {
        return jollyOut;
    }

    /**
     * Getter for "outputFaith" attribute in BasicProduction Class.
     */
    public int getOutputFaith() {
        return outputFaith;
    }
}
