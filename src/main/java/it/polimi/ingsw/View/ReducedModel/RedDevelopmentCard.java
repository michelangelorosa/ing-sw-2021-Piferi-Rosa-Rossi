package it.polimi.ingsw.View.ReducedModel;

import it.polimi.ingsw.Model.Enums.Color;
import it.polimi.ingsw.Model.Enums.Level;
import it.polimi.ingsw.Model.Enums.ResourceType;
import it.polimi.ingsw.View.Utility.ANSIColors;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * RedDevelopmentCard Class describes the different attributes and methods
 * needed for the creation of a Development Card.
 * <p><b>Attributes:</b></p>
 * <ul>
 *     <li>Color "color": color of the development card</li>
 *     <li>Level "level": level of the development card</li>
 *     <li>int "cardId": id of the development card</li>
 *     <li>int "victoryPoints": victory points of the development cards</li>
 *     <li>RedResourceStack "cost": cost of the development card</li>
 *     <li>RedResourceStack "input": input resources of the development card</li>
 *     <li>RedResourceStack "output": output resources of the development card</li>
 *     <li>int "outputFaith": output faith of the development card</li>
 * </ul>
 */
public class RedDevelopmentCard implements Serializable {
    protected final Color color;
    protected final Level level;

    protected final int cardId;
    protected int victoryPoints;

    protected RedResourceStack cost;
    protected RedResourceStack input;
    protected RedResourceStack output;
    protected int outputFaith;

    /**
     * Constructor for RedDevelopmentCard Class.
     */
    protected RedDevelopmentCard(int cardId, Color color, Level level) {
        this.cardId = cardId;
        this.color = color;
        this.level = level;
    }

    /**
     * Getter for "color" attribute.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Getter for "level" attribute.
     */
    public Level getLevel() {
        return this.level;
    }

    /**
     * Getter for "cardId" attribute.
     */

    public int getCardId() {
        return cardId;
    }

    /**
     * Getter for "victoryPoints" attribute.
     */
    public int getVictoryPoints() {
        return victoryPoints;
    }

    /**
     * Getter for "outputFaith" attribute.
     */
    public int getOutputFaith() {
        return outputFaith;
    }

    /**
     * Getter for "cost" attribute.
     */
    public RedResourceStack getCost() {
        return cost;
    }

    /**
     * Getter for "input" attribute.
     */
    public RedResourceStack getInput() {
        return input;
    }

    /**
     * Getter for "output" attribute.
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

    /**
     * Builds a visual representation of the Development Card
     * @return An ArrayList of Strings containing visual representation of the Development Card
     */
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

    /**
     * Builds a visual representation of the Development Card's level
     * @return An ArrayList of Strings containing visual representation of the Development Card's level
     */
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

    /**
     * Builds a visual representation of the Development Card's victory points
     * @return An ArrayList of Strings containing visual representation of the Development Card's victory points
     */
    public String victoryPointsToCli() {
        Integer points = this.victoryPoints;
        if(this.victoryPoints > 9) return points.toString(); else return "0" + points.toString();
    }

}
