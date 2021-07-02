package it.polimi.ingsw.View.ReducedModel;

import it.polimi.ingsw.Model.Enums.ResourceType;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * BasicProduction Class defines inputs and outputs of a basic production.
 * <p><b>Attributes:</b></p>
 * <ul>
 *     <li>RedResourceStack "fixedInputs": contains fixed resource inputs of the basic production</li>
 *     <li>RedResourceStack "fixedOutputs": contains fixed resource outputs of the basic production</li>
 *     <li>int "jollyIn": number of non specified input resources of the basic production</li>
 *     <li>int "jollyOut": number of non specified input resources of the basic production</li>
 *     <li>int "outputFaith": number of output faith points of the basic production</li>
 * </ul>
 */
public class RedBasicProduction implements Serializable {
    protected RedResourceStack fixedInputs;
    protected RedResourceStack fixedOutputs;

    protected int jollyIn;
    protected int jollyOut;

    protected int outputFaith;

    /**
     * Getter for "fixedInputs" attribute.
     */
    public RedResourceStack getFixedInputs() {
        return fixedInputs;
    }

    /**
     * Getter for "fixedInputs" attribute.
     */
    public RedResourceStack getFixedOutputs() {
        return fixedOutputs;
    }

    /**
     * Getter for "jollyIn" attribute.
     */
    public int getJollyIn() {
        return jollyIn;
    }

    /**
     * Getter for "jollyOut" attribute.
     */
    public int getJollyOut() {
        return jollyOut;
    }

    /**
     * Getter for "outputFaith" attribute.
     */
    public int getOutputFaith() {
        return outputFaith;
    }

    /**
     * Builds a visual representation of the basic production.
     * @return An ArrayList of string containing the visual representation.
     */
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

    /**
     * Builds a visual representation of jolly resources.
     * @param jolly number of jolly resources.
     * @return A String containing the visual representation.
     */
    public String jollyToString(int jolly) {
        if(jolly > 9) return "?:"+jolly; else return "?: "+jolly;
    }
}
