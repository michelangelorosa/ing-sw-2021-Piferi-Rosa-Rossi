package it.polimi.ingsw.View.ReducedModel;

import it.polimi.ingsw.View.ReducedModel.Enums.*;

import java.io.Serializable;
import java.util.ArrayList;

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

    public ArrayList<String> toCli() {
        ArrayList<String> basic = new ArrayList<>();
        String jolly1 = this.jollyToString(this.jollyIn);
        String jolly2 = this.jollyToString(this.jollyOut);

        if(this.fixedInputs.isEmpty() && this.fixedOutputs.isEmpty()) {

            basic.add("╔══════╦══════╦════════════════╗");
            basic.add("║      ║      ║ ░ BASIC      ░ ║");
            basic.add("║      ║      ║ ░ PRODUCTION ░ ║");
            basic.add("║ "+jolly1+" → "+jolly2+" ╠════════════════╝");
            basic.add("║      ║      ║");
            basic.add("║      ║      ║");
            basic.add("║      ║      ║");
            basic.add("╚══════╩══════╝");
        }
        else {
            basic.add("╔══════╦══════╦════════════════╗");
            basic.add("║ "+jolly1+" ║ "+jolly2+" ║ ░ BASIC      ░ ║");
            basic.add("║ "+this.fixedInputs.toCliSymbol(ResourceType.SHIELDS)+ " ║ "+this.fixedOutputs.toCliSymbol(ResourceType.SHIELDS)+ " ║ ░ PRODUCTION ░ ║");
            basic.add("║ "+this.fixedInputs.toCliSymbol(ResourceType.SERVANTS)+" → "+this.fixedOutputs.toCliSymbol(ResourceType.SERVANTS)+" ╠════════════════╝");
            basic.add("║ "+this.fixedInputs.toCliSymbol(ResourceType.COINS)+   " → "+this.fixedOutputs.toCliSymbol(ResourceType.COINS)+   " ║");
            basic.add("║ "+this.fixedInputs.toCliSymbol(ResourceType.STONES)+  " ║ "+this.fixedOutputs.toCliSymbol(ResourceType.STONES)+  " ║");
            basic.add("║      ║ "+ResourceType.faithPointsToCli(this.outputFaith)+" ║");
            basic.add("╚══════╩══════╝");
        }
        return basic;
    }

    public String jollyToString(int jolly) {
        if(jolly > 9) return "?:"+jolly; else return "?: "+jolly;
    }
}
