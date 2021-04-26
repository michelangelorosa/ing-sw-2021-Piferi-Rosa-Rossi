package it.polimi.ingsw.View.ReducedModel;

import it.polimi.ingsw.View.ANSIColors;
import it.polimi.ingsw.View.ReducedModel.Enums.*;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * DevelopmentCard Class describes the different attributes and methods
 * needed for the creation of a Development Card.
 */
public class DevelopmentCard implements Serializable {
    private static final long serialVersionUID = 0x1;

    /**
     * Color and Level are enum type classes containing all possible colors and levels
     * a Development Card could have.
     * Each instantiated Development Card has two attributes (color and level) indicating the card's
     * color and level, which are final, has they shall not be changed.
     */
    private final Color color;
    private final Level level;

    /**
     * Each Development Card has its own unique cardId (hence the "final" declaration), used for
     * recognition, and a finite number of victory points.
     */
    private final int cardId;
    private int victoryPoints;

    /**
     * A Development Card has three ResourceStack Class - type attributes containing namely the cost,
     * production input and production output of the card. As some cards can have faith points as a
     * production outcome, an integer attribute called outputFaith is introduced.
     */
    private ResourceStack cost;
    private ResourceStack input;
    private ResourceStack output;
    private int outputFaith;

    /**
     * Constructor for DevelopmentCard Class.
     */
    public DevelopmentCard(Color color, Level level, int cardId, int victoryPoints, ResourceStack cost, ResourceStack input, ResourceStack output, int outputFaith) {
        this.color = color;
        this.level = level;
        this.cardId = cardId;
        this.victoryPoints = victoryPoints;
        this.cost = cost;
        this.input = input;
        this.output = output;
        this.outputFaith = outputFaith;
    }

    /**
     * Getter for "color" attribute in DevelopmentCard Class.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Getter for "level" attribute in DevelopmentCard Class.
     */
    public Level getLevel() {
        return this.level;
    }

    /**
     * Getter for "cardId" attribute in DevelopmentCard Class.
     */

    public int getCardId() {
        return cardId;
    }

    /**
     * Getter for "victoryPoints" attribute in DevelopmentCard Class.
     */
    public int getVictoryPoints() {
        return victoryPoints;
    }

    /**
     * Getter for "outputFaith" attribute in DevelopmentCard Class.
     */
    public int getOutputFaith() {
        return outputFaith;
    }

    /**
     * Getter for "cost" attribute in DevelopmentCard Class.
     */
    public ResourceStack getCost() {
        return cost;
    }

    /**
     * Getter for "input" attribute in DevelopmentCard Class.
     */
    public ResourceStack getInput() {
        return input;
    }

    /**
     * Getter for "output" attribute in DevelopmentCard Class.
     */
    public ResourceStack getOutput() {
        return output;
    }

    /**
     * toString override method for DevelopmentCard Class.
     */
    @Override
    public String toString() {
        return cardId+" "+victoryPoints+" "+color+" "+level+" "+cost+" "+input+" "+output+" "+outputFaith;
    }

    public ArrayList<String> toCli() {
        ArrayList<String> devCard = new ArrayList<>();
        ArrayList<String> level = this.cardLevelToCli();
        String c = this.color.colorToString();
        String color = this.color.colorToChar();
        String r = ANSIColors.RESET;

        devCard.add(c+"╔═══╦"+r+"═╦════════════════╦═"+c+"╦═══╗"+r);
        devCard.add(c+"║ "+level.get(0)+" ║"+r+" ║ "+ ANSIColors.THREE_PER_EM +ANSIColors.SIX_PER_EM+this.cost.toCliSymbol(ResourceType.SHIELDS)+"   "+this.cost.toCliSymbol(ResourceType.SERVANTS)+"  ║ "+c+"║ "+level.get(0)+" ║"+r);
        devCard.add(c+"║ "+level.get(1)+" ║"+r+" ║ "+ ANSIColors.THREE_PER_EM +ANSIColors.SIX_PER_EM+this.cost.toCliSymbol(ResourceType.COINS)+"   "+this.cost.toCliSymbol(ResourceType.STONES)+"  ║ "+c+"║ "+level.get(1)+" ║"+r);
        devCard.add(c+"║ "+level.get(2)+" ║"+r+" ╚════════════════╝ "+c+"║ "+level.get(2)+" ║"+r);
        devCard.add(c+"╠═══╝"+r+"                    "+c+"╚═══╣"+r);
        devCard.add("║   ╔═════════╦══════════╗   ║");
        devCard.add("║   ║  " +ANSIColors.FOUR_PER_EM+this.input.toCliSymbol(ResourceType.SHIELDS)+"  ║ "+ANSIColors.FOUR_PER_EM+this.output.toCliSymbol(ResourceType.SHIELDS)+"    ║   ║");
        devCard.add("║   ║  " +ANSIColors.FOUR_PER_EM+this.input.toCliSymbol(ResourceType.SERVANTS)+"  ║ "+ANSIColors.FOUR_PER_EM+this.output.toCliSymbol(ResourceType.SERVANTS)+"    ║   ║");
        devCard.add("║   ║  " +ANSIColors.FOUR_PER_EM+this.input.toCliSymbol(ResourceType.COINS)+"  ║ "+ANSIColors.FOUR_PER_EM+this.output.toCliSymbol(ResourceType.COINS)+"    ║   ║");
        devCard.add("║   ║  " +ANSIColors.FOUR_PER_EM+this.input.toCliSymbol(ResourceType.STONES)+"  ║ "+ANSIColors.FOUR_PER_EM+this.output.toCliSymbol(ResourceType.STONES)+"    ║   ║");
        devCard.add("║   ║         ║ "+ANSIColors.FOUR_PER_EM+ANSIColors.SIX_PER_EM+ANSIColors.FOUR_PER_EM+ResourceType.faithPointsToCli(this.outputFaith)+"    ║   ║");
        devCard.add("║   ╚═════════╩══════════╝   ║");
        devCard.add("║           ╔════╗           ║");
        devCard.add("║ "+color+"         ║ "+this.victoryPointsToCli()+" ║     "+level.get(0)+" "+level.get(1)+" "+level.get(2)+" ║");
        devCard.add("╚═══════════╩════╩═══════════╝");


        return devCard;
    }

    public ArrayList<String> cardLevelToCli() {
        ArrayList<String> level = new ArrayList<>();
        if(this.level == Level.ONE) {
            level.add(" ");
            level.add(" ");
            level.add("*");
        }
        else if(this.level == Level.TWO) {
            level.add(" ");
            level.add("*");
            level.add("*");
        }
        else if(this.level == Level.THREE) {
            level.add("*");
            level.add("*");
            level.add("*");
        }
        return level;
    }

    public String victoryPointsToCli() {
        Integer points = this.victoryPoints;
        if(this.victoryPoints > 9) return points.toString(); else return "0" + points.toString();
    }

}
