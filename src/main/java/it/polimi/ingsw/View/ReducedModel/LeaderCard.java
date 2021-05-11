package it.polimi.ingsw.View.ReducedModel;

import it.polimi.ingsw.Model.Enums.LeaderCardAction;
import it.polimi.ingsw.Model.Enums.ResourceType;
import it.polimi.ingsw.View.ANSIColors;
import it.polimi.ingsw.View.ReducedModel.Enums.Marble;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * There are 4 types of Leader Card, depending on the type of special effect they apply on the Game.
 * All of them have the following attributes: a CardId for identification, victoryPoints for the score.
 * Type is a byte identifying the type
 *  */
public class LeaderCard implements Serializable {
    private static final long serialVersionUID = 0x1;

    private final int cardId;
    private final int victoryPoints;
    private final LeaderCardAction action;
    private boolean active;
    private final ResourceStack resourcesRequired;
    private final LeaderRequirements cardsRequired;
    //ABILITY ONE: Discount on Development Card
    private ResourceStack discount;
    //ABILITY TWO: White Marble to Resource
    private Marble marble;
    //ABILITY THREE: New Dev w/ faith
    private ResourceStack input;
    private int faith;
    private int jollyOut;
    //ABILITY FOUR: Xtra Depot
    private ResourceType resource;

    /**
     * Constructor for LeaderCard Class. The override of ability is implicit in the parameters
     */
    public LeaderCard(int cardId, int victoryPoints, ResourceStack resourcesRequired, LeaderRequirements cards, ResourceStack discount) {
        this.cardId = cardId;
        this.victoryPoints = victoryPoints;
        this.resourcesRequired = resourcesRequired;
        this.cardsRequired = cards;
        this.action = LeaderCardAction.DISCOUNT;
        this.active = false;
        this.discount=discount;
    }
    public LeaderCard(int cardId, int victoryPoints, ResourceStack resourcesRequired, LeaderRequirements cards, Marble marble) {
        this.cardId = cardId;
        this.victoryPoints = victoryPoints;
        this.resourcesRequired = resourcesRequired;
        this.cardsRequired = cards;
        this.action = LeaderCardAction.WHITEMARBLE;
        this.active = false;
        this.marble = marble;
    }
    public LeaderCard(int cardId, int victoryPoints, ResourceStack resourcesRequired, LeaderRequirements cards, ResourceStack input, int jollyOut, int faith) {
        this.cardId = cardId;
        this.victoryPoints = victoryPoints;
        this.resourcesRequired = resourcesRequired;
        this.cardsRequired = cards;
        this.action = LeaderCardAction.PRODUCTIONPOWER;
        this.active = false;
        this.input = input;
        this.faith = faith;
        this.jollyOut = jollyOut;
    }
    public LeaderCard(int cardId, int victoryPoints, ResourceStack resourcesRequired, LeaderRequirements cards, ResourceType resource) {
        this.cardId = cardId;
        this.victoryPoints = victoryPoints;
        this.resourcesRequired = resourcesRequired;
        this.cardsRequired = cards;
        this.action = LeaderCardAction.EXTRADEPOT;
        this.active = false;
        this.resource = resource;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getCardId() {
        return cardId;
    }

    public int getVictoryPoints() {
        return victoryPoints;
    }

    public LeaderCardAction getAction() {
        return action;
    }

    public boolean isActive() {
        return active;
    }

    public ResourceStack getResourcesRequired() {
        return resourcesRequired;
    }

    public LeaderRequirements getCardsRequired() {
        return cardsRequired;
    }

    public ResourceStack getDiscount() {
        return discount;
    }

    public Marble getMarble() {
        return marble;
    }

    public ResourceStack getInput() {
        return input;
    }

    public int getFaith() {
        return faith;
    }

    public int getJollyOut() {
        return jollyOut;
    }

    public ResourceType getResource() {
        return resource;
    }


   /* public ArrayList<String> toCli(Game game, Player player) {
        ArrayList<String> leadCard = new ArrayList<>();
        //ArrayList<String> cards = this.cardLevelToCli();
        //String c = this.color.colorToString();
        //String color = this.color.colorToChar();
        String r = ANSIColors.RESET;
        String name = " ";
            name = " ";

            if (game.getMyNickname() == player.getNickname() || this.active) {
                leadCard.add("╔══════════════════════════╗" + r);
                leadCard.add("║ " + ANSIColors.FRONT_BLUE + "B " + ANSIColors.FRONT_PURPLE + "   P " + ANSIColors.FRONT_YELLOW + "   Y  " + ANSIColors.FRONT_BRIGHT_GREEN + "  G    " + this.getResourcesRequired().toCliSymbol(ResourceType.SHIELDS) + " ║" + r);
                leadCard.add("║ " + ANSIColors.FRONT_BLUE + this.cardsRequired.getBlueCardLv1() + "    " + ANSIColors.FRONT_PURPLE + this.cardsRequired.getPurpleCardLv1() + "    " + ANSIColors.FRONT_YELLOW + this.cardsRequired.getYellowCardLv1() + "    " + ANSIColors.FRONT_BRIGHT_GREEN + this.cardsRequired.getGreenCardLv1() + "    " + this.getResourcesRequired().toCliSymbol(ResourceType.SERVANTS) + " ║" + r);
                leadCard.add("║ " + ANSIColors.FRONT_BLUE + this.cardsRequired.getBlueCardLv2() + "    " + ANSIColors.FRONT_PURPLE + this.cardsRequired.getPurpleCardLv2() + "    " + ANSIColors.FRONT_YELLOW + this.cardsRequired.getYellowCardLv2() + "    " + ANSIColors.FRONT_BRIGHT_GREEN + this.cardsRequired.getGreenCardLv2() + "    " + this.getResourcesRequired().toCliSymbol(ResourceType.COINS) + " ║" + r);
                leadCard.add("║ " + ANSIColors.FRONT_BLUE + this.cardsRequired.getBlueCardLv3() + "    " + ANSIColors.FRONT_PURPLE + this.cardsRequired.getPurpleCardLv3() + "    " + ANSIColors.FRONT_YELLOW + this.cardsRequired.getYellowCardLv3() + "    " + ANSIColors.FRONT_BRIGHT_GREEN + this.cardsRequired.getGreenCardLv3() + "    " + this.getResourcesRequired().toCliSymbol(ResourceType.STONES) + " ║" + r);
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
            } else {
                leadCard.add("╔══════════════════════════╗" + r);
                leadCard.add("║          __      _       ║");
                leadCard.add("║         /__\\__  //       ║");
                leadCard.add("║        //_____\\///       ║");
                leadCard.add("║       _| /-_-\\)|/_       ║");
                leadCard.add("║      (___\\ _ //___\\      ║");
                leadCard.add("║      (  |\\\\_/// * \\\\     ║");
                leadCard.add("║       \\_| \\_((*   *))    ║");
                leadCard.add("║       ( |__|_\\\\  *////     ║");
      /*        leadCard.add("║       (o/  _  \\_*_/      ║");
                leadCard.add("║       //\\__|__/\\         ║");
                leadCard.add("║      // |  | |  |        ║");
                leadCard.add("║     //  _\\ | |___)       ║");
                leadCard.add("║    //  (___|             ║");
                leadCard.add("╚══════════════════════════╝");
            }
            return leadCard;
    }
    */


    public String victoryPointsToCli() {
        Integer points = this.victoryPoints;
        if(this.victoryPoints > 9) return points.toString(); else return "0" + points.toString();
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
        if(this.marble == Marble.BLUE)return "║     ║  ⚪ → "+ ANSIColors.FRONT_BLUE+this.marble + ANSIColors.RESET+"   ║     ║";
        if(this.marble == Marble.PURPLE)return "║     ║ ⚪ → "+ ANSIColors.FRONT_PURPLE+this.marble + ANSIColors.RESET+"  ║     ║";
        if(this.marble == Marble.YELLOW)return "║     ║ ⚪ → "+ ANSIColors.FRONT_YELLOW+this.marble + ANSIColors.RESET+"  ║     ║";
        if(this.marble == Marble.GREY)return "║     ║  ⚪ → "+ ANSIColors.FRONT_GREY+this.marble + ANSIColors.RESET+"   ║     ║";
        if(this.marble == Marble.RED)return "║     ║  ⚪ → "+ ANSIColors.FRONT_RED+this.marble + ANSIColors.RESET+"    ║     ║";
        else return "║     ║              ║     ║";
    }

    public String jollyToString(int jolly) {
        if(jolly > 9) return "?:"+jolly; else return "?: "+jolly;
    }

    public String state(){
        if(this.active == true) return ANSIColors.FRONT_GREEN + "  ACTIVE  " + ANSIColors.RESET;
        else return ANSIColors.FRONT_BRIGHT_RED + "NOT ACTIVE" + ANSIColors.RESET;
    }

    public ArrayList<String> toCliUp(){
        ArrayList<String> leadCard = new ArrayList<>();
        String r = ANSIColors.RESET;

        leadCard.add("╔══════════════════════════╗" + r);
        leadCard.add("║ " + ANSIColors.FRONT_BLUE + "B " + ANSIColors.FRONT_PURPLE + "   P " + ANSIColors.FRONT_YELLOW + "   Y  " + ANSIColors.FRONT_BRIGHT_GREEN + "  G    " + this.getResourcesRequired().toCliSymbol(ResourceType.SHIELDS) + " ║" + r);
        leadCard.add("║ " + ANSIColors.FRONT_BLUE + this.cardsRequired.getBlueCardLv1() + "    " + ANSIColors.FRONT_PURPLE + this.cardsRequired.getPurpleCardLv1() + "    " + ANSIColors.FRONT_YELLOW + this.cardsRequired.getYellowCardLv1() + "    " + ANSIColors.FRONT_BRIGHT_GREEN + this.cardsRequired.getGreenCardLv1() + "    " + this.getResourcesRequired().toCliSymbol(ResourceType.SERVANTS) + " ║" + r);
        leadCard.add("║ " + ANSIColors.FRONT_BLUE + this.cardsRequired.getBlueCardLv2() + "    " + ANSIColors.FRONT_PURPLE + this.cardsRequired.getPurpleCardLv2() + "    " + ANSIColors.FRONT_YELLOW + this.cardsRequired.getYellowCardLv2() + "    " + ANSIColors.FRONT_BRIGHT_GREEN + this.cardsRequired.getGreenCardLv2() + "    " + this.getResourcesRequired().toCliSymbol(ResourceType.COINS) + " ║" + r);
        leadCard.add("║ " + ANSIColors.FRONT_BLUE + this.cardsRequired.getBlueCardLv3() + "    " + ANSIColors.FRONT_PURPLE + this.cardsRequired.getPurpleCardLv3() + "    " + ANSIColors.FRONT_YELLOW + this.cardsRequired.getYellowCardLv3() + "    " + ANSIColors.FRONT_BRIGHT_GREEN + this.cardsRequired.getGreenCardLv3() + "    " + this.getResourcesRequired().toCliSymbol(ResourceType.STONES) + " ║" + r);
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
/*
    public void printD(LeaderCard card){
        for(String string :card.toCliDown())
            System.out.println(string);
    }
    public void printU(LeaderCard card){
        for(String string :card.toCliUp())
            System.out.println(string);
    }

    public void printCards(Game game, Player player){
        String myName = game.getMyNickname();
        String hisName = player.getNickname();

        if(myName == hisName){
            player.getLeaderCards()[0].printU(player.getLeaderCards()[0]);
            player.getLeaderCards()[1].printU(player.getLeaderCards()[1]);
        }
        else {
            if (player.getLeaderCards()[0].active) {
                player.getLeaderCards()[0].printU(player.getLeaderCards()[0]);
            } else player.getLeaderCards()[0].printD(player.getLeaderCards()[0]);
            if (player.getLeaderCards()[1].active) {
                player.getLeaderCards()[1].printU(player.getLeaderCards()[1]);
            } else player.getLeaderCards()[1].printD(player.getLeaderCards()[1]);
        }
    }
 */
}
