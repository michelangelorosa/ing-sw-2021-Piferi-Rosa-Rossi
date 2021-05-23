package it.polimi.ingsw.View.ReducedModel;

import it.polimi.ingsw.Model.Enums.Color;
import it.polimi.ingsw.Model.Enums.Level;
import it.polimi.ingsw.Model.Enums.ResourceType;
import it.polimi.ingsw.View.ANSIColors;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * DevelopmentCard Class describes the different attributes and methods
 * needed for the creation of a Development Card.
 */
public class RedDevelopmentCard implements Serializable {
    private static final long serialVersionUID = 0x1;

    /**
     * Color and Level are enum type classes containing all possible colors and levels
     * a Development Card could have.
     * Each instantiated Development Card has two attributes (color and level) indicating the card's
     * color and level, which are final, has they shall not be changed.
     */
    protected final Color color;
    protected final Level level;

    /**
     * Each Development Card has its own unique cardId (hence the "final" declaration), used for
     * recognition, and a finite number of victory points.
     */
    protected final int cardId;
    protected int victoryPoints;

    /**
     * A Development Card has three ResourceStack Class - type attributes containing namely the cost,
     * production input and production output of the card. As some cards can have faith points as a
     * production outcome, an integer attribute called outputFaith is introduced.
     */
    protected RedResourceStack cost;
    protected RedResourceStack input;
    protected RedResourceStack output;
    protected int outputFaith;

    protected RedDevelopmentCard(int cardId, Color color, Level level) {
        this.cardId = cardId;
        this.color = color;
        this.level = level;
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
    public RedResourceStack getCost() {
        return cost;
    }

    /**
     * Getter for "input" attribute in DevelopmentCard Class.
     */
    public RedResourceStack getInput() {
        return input;
    }

    /**
     * Getter for "output" attribute in DevelopmentCard Class.
     */
    public RedResourceStack getOutput() {
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
        String c = this.color.backColorToString();
        String fc = this.color.frontColorToString();
        String color = this.color.colorToChar();
        String r = ANSIColors.RESET;

        devCard.add(c+"╔═══╦═╦════════════════╦═╦═══╗"+r);
        devCard.add(c+"║ "+level.get(0)+" ║"+r+" ║  "+this.cost.toCliSymbol(ResourceType.SHIELDS)+"    "+this.cost.toCliSymbol(ResourceType.SERVANTS)+"  ║ "+c+"║ "+level.get(0)+" ║"+r);
        devCard.add(c+"║ "+level.get(1)+" ║"+r+" ║  "+this.cost.toCliSymbol(ResourceType.COINS)+"    "+this.cost.toCliSymbol(ResourceType.STONES)+"  ║ "+c+"║ "+level.get(1)+" ║"+r);
        devCard.add(c+"║ "+level.get(2)+" ║"+r+" ╚════════════════╝ "+c+"║ "+level.get(2)+" ║"+r);
        devCard.add(c+"╠═══╝"+r+"                    "+c+"╚═══╣"+r);
        devCard.add("║   ╔═════════╦══════════╗   ║");
        devCard.add("║   ║  " +this.input.toCliSymbol(ResourceType.SHIELDS)+"   ║  "+this.output.toCliSymbol(ResourceType.SHIELDS)+"    ║   ║");
        devCard.add("║   ║  " +this.input.toCliSymbol(ResourceType.SERVANTS)+"   ║  "+this.output.toCliSymbol(ResourceType.SERVANTS)+"    ║   ║");
        devCard.add("║   ║  " +this.input.toCliSymbol(ResourceType.COINS)+"   ║  "+this.output.toCliSymbol(ResourceType.COINS)+"    ║   ║");
        devCard.add("║   ║  " +this.input.toCliSymbol(ResourceType.STONES)+"   ║  "+this.output.toCliSymbol(ResourceType.STONES)+"    ║   ║");
        devCard.add("║   ║         ║  "+ResourceType.faithPointsToCli(this.outputFaith)+"    ║   ║");
        devCard.add("║   ╚═════════╩══════════╝   ║");
        devCard.add("║           ╔════╗           ║");
        devCard.add("║ "+fc+color+r+"         ║ "+this.victoryPointsToCli()+" ║     "+fc+level.get(0)+" "+level.get(1)+" "+level.get(2)+r+" ║");
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
