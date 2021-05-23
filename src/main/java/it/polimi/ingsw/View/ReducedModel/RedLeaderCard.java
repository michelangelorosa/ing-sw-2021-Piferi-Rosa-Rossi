package it.polimi.ingsw.View.ReducedModel;

import it.polimi.ingsw.Model.Enums.*;
import it.polimi.ingsw.View.ANSIColors;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * There are 4 types of Leader Card, depending on the type of special effect they apply on the Game.
 * All of them have the following attributes: a CardId for identification, victoryPoints for the score.
 * Type is a byte identifying the type
 *  */
public class RedLeaderCard implements Serializable {
    private static final long serialVersionUID = 0x1;

    protected int cardId;
    protected int victoryPoints;
    protected LeaderCardAction action;
    protected boolean active;
    protected RedResourceStack resourcesRequired;
    protected RedLeaderRequirements cardsRequired;
    //ABILITY ONE: Discount on Development Card
    protected RedResourceStack discount;
    //ABILITY TWO: White Marble to Resource
    protected Marble marble;
    //ABILITY THREE: New Dev w/ faith
    protected RedResourceStack input;
    protected int faith;
    protected int jollyOut;
    //ABILITY FOUR: Xtra Depot
    protected ResourceType resource;

    /**Getter for cardId*/
    public int getCardId() {
        return cardId;
    }

    /**Getter for victoryPoints*/
    public int getVictoryPoints() {
        return victoryPoints;
    }

    /**Getter for Action*/
    public LeaderCardAction getAction() {
        return action;
    }

    /**Getter for Active*/
    public boolean isActive() {
        return active;
    }

    /**Getter for ResourceRequired*/
    public RedResourceStack getResourcesRequired() {
        return resourcesRequired;
    }

    /**Getter for cardsRequired*/
    public RedLeaderRequirements getCardsRequired() {
        return cardsRequired;
    }

    /**Getter for discount*/
    public RedResourceStack getDiscount() {
        return discount;
    }

    /**Getter for marble*/
    public Marble getMarble() {
        return marble;
    }

    /**Getter for input*/
    public RedResourceStack getInput() {
        return input;
    }

    /**Getter for faith*/
    public int getFaith() {
        return faith;
    }

    /**Getter for jollyOut*/
    public int getJollyOut() {
        return jollyOut;
    }

    /**Getter for Resource*/
    public ResourceType getResource() {
        return resource;
    }

    public String victoryPointsToCli() {
        int points = this.victoryPoints;
        if(this.victoryPoints > 9) return Integer.toString(points); else return "0" + Integer.toString(points);
    }



    public String printAbility(){
        if(this.action == LeaderCardAction.DISCOUNT) return "║         "+ this.action +"         ║";
        else if(this.action == LeaderCardAction.WHITEMARBLE) return "║        "+ this.action +"       ║";
        else if(this.action == LeaderCardAction.EXTRADEPOT) return "║        "+ this.action +"        ║";
        else if(this.action == LeaderCardAction.PRODUCTIONPOWER) return "║      "+ this.action +"     ║";
        else return " ";
    }

    public ArrayList<String> printEffect() {
        ArrayList<String> effect = new ArrayList<>();

        switch (this.action) {
            case DISCOUNT:
                effect.add("║           " + this.discount.toCliSymbol(ResourceType.SHIELDS) + "           ║");
                effect.add("║           " + this.discount.toCliSymbol(ResourceType.SERVANTS) + "           ║");
                effect.add("║           " + this.discount.toCliSymbol(ResourceType.COINS) + "           ║");
                effect.add("║           " + this.discount.toCliSymbol(ResourceType.STONES) + "           ║");
                break;
            case WHITEMARBLE:
                effect.add("║                          ║");
                effect.add("║     ╔══════════════╗     ║");
                effect.add(printMarble());
                effect.add("║     ╚══════════════╝     ║");
                break;
            case EXTRADEPOT:
                switch (this.resource) {
                    case SHIELDS:
                        effect.add("║ ╔═════════╗  ╔═════════╗ ║");
                        effect.add("║ ║ "+ANSIColors.FRONT_BLUE+"SHIELDS"+ANSIColors.RESET+" ║  ║ "+ANSIColors.FRONT_BLUE+"SHIELDS "+ANSIColors.RESET+"║ ║");
                        effect.add("║ ╚═════════╝  ╚═════════╝ ║");
                        effect.add("║                          ║");
                        break;
                    case SERVANTS:
                        effect.add("║ ╔══════════╗╔══════════╗ ║");
                        effect.add("║ ║"+ANSIColors.FRONT_PURPLE+" SERVANTS "+ANSIColors.RESET+"║║ "+ANSIColors.FRONT_PURPLE+"SERVANTS "+ANSIColors.RESET+"║ ║");
                        effect.add("║ ╚══════════╝╚══════════╝ ║");
                        effect.add("║                          ║");
                        break;
                    case COINS:
                        effect.add("║ ╔═════════╗  ╔═════════╗ ║");
                        effect.add("║ ║ "+ANSIColors.FRONT_YELLOW+" COINS "+ANSIColors.RESET+" ║  ║ "+ANSIColors.FRONT_YELLOW+" COINS "+ANSIColors.RESET+" ║ ║");
                        effect.add("║ ╚═════════╝  ╚═════════╝ ║");
                        effect.add("║                          ║");
                        break;
                    case STONES:
                        effect.add("║ ╔══════════╗╔══════════╗ ║");
                        effect.add("║ ║ "+ANSIColors.FRONT_GREY+" STONES "+ANSIColors.RESET+" ║║ "+ANSIColors.FRONT_GREY+" STONES "+ANSIColors.RESET+" ║ ║");
                        effect.add("║ ╚══════════╝╚══════════╝ ║");
                        effect.add("║                          ║");
                        break;
                }
                break;
            case PRODUCTIONPOWER:
                effect.add("║  ╔════════════╦═══════╗  ║");
                effect.add("║  ║ "+ this.input.toCliSymbol(ResourceType.SHIELDS)+" "+this.input.toCliSymbol(ResourceType.SERVANTS)+"  ║ "+jollyToString(this.jollyOut)+"  ║  ║");
                effect.add("║  ║ "+ this.input.toCliSymbol(ResourceType.COINS)+" "+this.input.toCliSymbol(ResourceType.STONES)+"  ║ "+ResourceType.faithPointsToCli(this.faith)+"  ║  ║");
                effect.add("║  ╚════════════╩═══════╝  ║");
                break;
            }
        return effect;
    }

    public String printMarble(){
        if(this.marble == Marble.BLUE)return "║     ║   o → "+ ANSIColors.FRONT_BLUE+this.marble + ANSIColors.RESET+"   ║     ║";
        if(this.marble == Marble.PURPLE)return "║     ║  o → "+ ANSIColors.FRONT_PURPLE+this.marble + ANSIColors.RESET+"  ║     ║";
        if(this.marble == Marble.YELLOW)return "║     ║  o → "+ ANSIColors.FRONT_YELLOW+this.marble + ANSIColors.RESET+"  ║     ║";
        if(this.marble == Marble.GREY)return "║     ║   o → "+ ANSIColors.FRONT_GREY+this.marble + ANSIColors.RESET+"   ║     ║";
        if(this.marble == Marble.RED)return "║     ║   o → "+ ANSIColors.FRONT_RED+this.marble + ANSIColors.RESET+"    ║     ║";
        else return "║     ║              ║     ║";
    }

    public String jollyToString(int jolly) {
        if(jolly > 9) return "?:"+jolly; else return "?: "+jolly;
    }

    public String state(){
        if(this.active) return ANSIColors.FRONT_GREEN + "  ACTIVE  " + ANSIColors.RESET;
        else return ANSIColors.FRONT_BRIGHT_RED + "NOT ACTIVE" + ANSIColors.RESET;
    }

    public ArrayList<String> toCliUp(){
        ArrayList<String> leadCard = new ArrayList<>();
        String r = ANSIColors.RESET;

        leadCard.add("╔══════════════════════════╗" + r);
        leadCard.add(printResources().get(0));
        leadCard.add(printResources().get(1));
        leadCard.add(printResources().get(2));
        leadCard.add(printResources().get(3));
        leadCard.add("╠══════════════════════════╣" + r);
        leadCard.add(printAbility() + r);
        leadCard.add("╠══════════════════════════╣" + r);
        leadCard.add(printEffect().get(0) + r);
        leadCard.add(printEffect().get(1) + r);
        leadCard.add(printEffect().get(2) + r);
        leadCard.add(printEffect().get(3) + r);
        leadCard.add("╠════╗        ╔════════════╣" + r);
        leadCard.add("║ " + victoryPointsToCli() + " ║        ║ " + state() + " ║" + r);
        leadCard.add("╚════╩════════╩════════════╝");
        return leadCard;
    }

    public  ArrayList<String> toCliDown(){
        ArrayList<String> leadCard = new ArrayList<>();
        String r = ANSIColors.RESET;
        String c = ANSIColors.FRONT_BRIGHT_CYAN;

        leadCard.add("╔══════════════════════════╗" + r);
        leadCard.add("║"+c+" L "+r+"       __      _       ║");
        leadCard.add("║"+c+" E "+r+"      /__\\__  //       ║");
        leadCard.add("║"+c+" A "+r+"     //_____\\///     "+c+"L "+r+"║");
        leadCard.add("║"+c+" D "+r+"    _| /-_-\\)|/_     "+c+"E "+r+"║");
        leadCard.add("║"+c+" E "+r+"   (___\\ _ //___\\    "+c+"A "+r+"║");
        leadCard.add("║"+c+" R "+r+"   (  |\\\\_/// * \\\\   "+c+"D "+r+"║");
        leadCard.add("║"+c+"   "+r+"    \\_| \\_((*   *))  "+c+"E "+r+"║");
        leadCard.add("║"+c+" C "+r+"    ( |__|_\\\\  *//   "+c+"R "+r+"║");
        leadCard.add("║"+c+" A "+r+"    (o/  _  \\_*_/    "+c+"  "+r+"║");
        leadCard.add("║"+c+" R "+r+"    //\\__|__/\\       "+c+"C "+r+"║");
        leadCard.add("║"+c+" D "+r+"   // |  | |  |      "+c+"A "+r+"║");
        leadCard.add("║     //  _\\ | |___)     "+c+"R "+r+"║");
        leadCard.add("║    //  (___|           "+c+"D "+r+"║");
        leadCard.add("╚══════════════════════════╝");
        return leadCard;
    }

    public ArrayList<String> printResources(){
        ArrayList<String> leadCard = new ArrayList<>();
        String r = ANSIColors.RESET;

        if(!cardsRequired.getGeneric()){
            leadCard.add("║ " + ANSIColors.FRONT_BLUE + " B " + ANSIColors.FRONT_PURPLE + "  P " + ANSIColors.FRONT_YELLOW + "   Y  " + ANSIColors.FRONT_BRIGHT_GREEN + "  G    " + this.getResourcesRequired().toCliSymbol(ResourceType.SHIELDS) + " ║" + r);
            leadCard.add("║ " + ANSIColors.FRONT_BLUE + resourceToCli("BlueLv1") + "  " + ANSIColors.FRONT_PURPLE + resourceToCli("PurpleLv1") + "   " + ANSIColors.FRONT_YELLOW + resourceToCli("YellowLv1") + "   " + ANSIColors.FRONT_BRIGHT_GREEN + resourceToCli("GreenLv1") + "    " + this.getResourcesRequired().toCliSymbol(ResourceType.SERVANTS) + " ║" + r);
            leadCard.add("║ " + ANSIColors.FRONT_BLUE + resourceToCli("BlueLv2") + "  " + ANSIColors.FRONT_PURPLE + resourceToCli("PurpleLv2") + "   " + ANSIColors.FRONT_YELLOW + resourceToCli("YellowLv2") + "   " + ANSIColors.FRONT_BRIGHT_GREEN + resourceToCli("GreenLv2") + "    " + this.getResourcesRequired().toCliSymbol(ResourceType.COINS) + " ║" + r);
            leadCard.add("║ " + ANSIColors.FRONT_BLUE + resourceToCli("BlueLv3") + "  " + ANSIColors.FRONT_PURPLE + resourceToCli("PurpleLv3") + "   " + ANSIColors.FRONT_YELLOW + resourceToCli("YellowLv3") + "   " + ANSIColors.FRONT_BRIGHT_GREEN + resourceToCli("GreenLv3") + "    " + this.getResourcesRequired().toCliSymbol(ResourceType.STONES) + " ║" + r);
        }
        else{
            leadCard.add("║ " + ANSIColors.FRONT_BLUE + "  BLUE     " + ANSIColors.FRONT_BLUE + resourceToCli("Blue") + "     " + this.getResourcesRequired().toCliSymbol(ResourceType.SHIELDS) + "   ║" + r);
            leadCard.add("║ " + ANSIColors.FRONT_PURPLE + "  PURPLE   " + ANSIColors.FRONT_PURPLE  + resourceToCli("Purple") + "     "+ this.getResourcesRequired().toCliSymbol(ResourceType.SERVANTS) + "   ║" + r);
            leadCard.add("║ " + ANSIColors.FRONT_YELLOW + "  YELLOW   " + ANSIColors.FRONT_YELLOW  + resourceToCli("Yellow") + "     "+ this.getResourcesRequired().toCliSymbol(ResourceType.COINS) + "   ║" + r);
            leadCard.add("║ " + ANSIColors.FRONT_BRIGHT_GREEN + "  GREEN    " + ANSIColors.FRONT_BRIGHT_GREEN  + resourceToCli("Green") + "     "+ this.getResourcesRequired().toCliSymbol(ResourceType.STONES) + "   ║" + r);}
        return leadCard;
    }

    public String resourceToCli(String input){
        int resource = 0;
        switch (input) {
            case "BlueLv1":
                resource = this.cardsRequired.getBlueCardLv1();
                break;
            case "BlueLv2":
                resource = this.cardsRequired.getBlueCardLv2();
                break;
            case "BlueLv3":
                resource = this.cardsRequired.getBlueCardLv3();
                break;
            case "PurpleLv1":
                resource = this.cardsRequired.getPurpleCardLv1();
                break;
            case "PurpleLv2":
                resource = this.cardsRequired.getPurpleCardLv2();
                break;
            case "PurpleLv3":
                resource = this.cardsRequired.getPurpleCardLv3();
                break;
            case "YellowLv1":
                resource = this.cardsRequired.getYellowCardLv1();
                break;
            case "YellowLv2":
                resource = this.cardsRequired.getYellowCardLv2();
                break;
            case "YellowLv3":
                resource = this.cardsRequired.getYellowCardLv3();
                break;
            case "GreenLv1":
                resource = this.cardsRequired.getGreenCardLv1();
                break;
            case "GreenLv2":
                resource = this.cardsRequired.getGreenCardLv2();
                break;
            case "GreenLv3":
                resource = this.cardsRequired.getGreenCardLv3();
                break;
            case "Blue":
                resource = this.cardsRequired.getNeedBlueCard();
                break;
            case "Purple":
                resource = this.cardsRequired.getNeedPurpleCard();
                break;
            case "Yellow":
                resource = this.cardsRequired.getNeedYellowCard();
                break;
            case "Green":
                resource = this.cardsRequired.getNeedGreenCard();
                break;
        }

        if(resource < 9) return " " + resource;
        else return ""+resource;

    }

}
