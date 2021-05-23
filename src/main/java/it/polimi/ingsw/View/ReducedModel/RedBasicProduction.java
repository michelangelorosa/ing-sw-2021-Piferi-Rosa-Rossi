package it.polimi.ingsw.View.ReducedModel;

import it.polimi.ingsw.Model.Enums.ResourceType;
import it.polimi.ingsw.Model.ResourceStack;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * BasicProduction Class defines inputs and outputs of a basic production.
 */
public class RedBasicProduction implements Serializable {
    private static final long serialVersionUID = 0x1;

    /** Fixed inputs and outputs can be added before playing the game */
    protected RedResourceStack fixedInputs;
    protected RedResourceStack fixedOutputs;

    /** Jollies represent inputs and outputs the players can choose when starting the Production. */
    protected int jollyIn;
    protected int jollyOut;

    protected int outputFaith;

    /**
     * Getter for "fixedInputs" attribute in BasicProduction Class.
     */
    public RedResourceStack getFixedInputs() {
        return fixedInputs;
    }

    /**
     * Getter for "fixedInputs" attribute in BasicProduction Class.
     */
    public RedResourceStack getFixedOutputs() {
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
