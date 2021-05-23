package it.polimi.ingsw.Model;

import it.polimi.ingsw.View.ReducedModel.RedBasicProduction;

/**
 * BasicProduction Class defines inputs and outputs of a basic production.
 */
public class BasicProduction extends RedBasicProduction {

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
        return (ResourceStack) fixedInputs;
    }

    /**
     * Getter for "fixedInputs" attribute in BasicProduction Class.
     */
    public ResourceStack getFixedOutputs() {
        return (ResourceStack) fixedOutputs;
    }

    /**Method for converting model classes to view classes*/
    public RedBasicProduction toView() {
        return (RedBasicProduction) this;
    }
}
