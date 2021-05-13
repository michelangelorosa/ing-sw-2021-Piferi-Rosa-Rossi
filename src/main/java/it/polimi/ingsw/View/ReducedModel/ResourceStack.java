package it.polimi.ingsw.View.ReducedModel;

import it.polimi.ingsw.Model.Enums.ResourceType;
import it.polimi.ingsw.View.ANSIColors;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * ResourceStack Class contains four integer private attributes indicating the amount of each
 * different type of resource: shields, servants, coins, stones.
 */
public class ResourceStack implements Serializable{
    private static final long serialVersionUID = 0x1;

    private int shields;
    private int servants;
    private int coins;
    private int stones;

    /**
     * Constructor for ResourceStack Class.
     */
    public ResourceStack(int shields, int servants, int coins, int stones) {
        this.shields = shields;
        this.servants = servants;
        this.coins = coins;
        this.stones = stones;
    }

    /**
     * Getter for a specified type of resource inside the stack.
     * @param resourceType is the specified resource type.
     * @return the number of resources of the specified type.
     */
    public int getResource(ResourceType resourceType) {
        if(resourceType == ResourceType.SHIELDS)
            return shields;
        else if (resourceType == ResourceType.SERVANTS)
            return servants;
        else if (resourceType == ResourceType.COINS)
            return coins;
        else if (resourceType == ResourceType.STONES)
            return stones;
        else
            return -1;
    }

    /**
     * Setter for a specified type of resource inside the stack.
     * @param resourceType is the specified resource type.
     */
    public void setResource(int resources, ResourceType resourceType) {
        if(resourceType == ResourceType.SHIELDS)
            this.shields = resources;
        else if (resourceType == ResourceType.SERVANTS)
            this.servants = resources;
        else if (resourceType == ResourceType.COINS)
            this.coins = resources;
        else if (resourceType == ResourceType.STONES)
            this.stones = resources;
    }

    /**
     * This method is used to check if a Resource Stack is empty,
     * @return true if the Resource Stack is empty.
     */
    public boolean isEmpty() {
        ResourceType[] types = ResourceType.values();
        for(int i = 1; i < 5; i++)
            if(this.getResource(types[i]) != 0)
                return false;
        return true;
    }

    public boolean hasResource(ResourceType type) {
        return this.getResource(type) > 0;
    }

    /**
     * toString override method for ResourceStack Class.
     */
    public String toString() {
        return shields+" "+servants+" "+coins+" "+stones;
    }

    public String toCliWord(ResourceType type) {
        switch(type) {
            case SHIELDS: if(this.shields > 9) return ANSIColors.FRONT_BLUE +"SHIELDS:"+this.shields; else return ANSIColors.FRONT_BLUE +"SHIELDS: "+this.shields;

            case SERVANTS: if(this.servants > 9) return ANSIColors.FRONT_PURPLE +"SERVANTS:"+this.servants; else return ANSIColors.FRONT_PURPLE +"SERVANTS: "+this.servants;

            case COINS: if(this.coins > 9) return ANSIColors.FRONT_YELLOW +"COINS:"+this.coins; else return ANSIColors.FRONT_YELLOW +"COINS: "+this.coins;

            case STONES: if(this.stones > 9) return ANSIColors.FRONT_GREY +"STONES:"+this.stones; else return ANSIColors.FRONT_GREY +"STONES: "+this.stones;

            default: return null;
        }
    }

    /**Method to print the symbols of the cli*/
    public String toCliSymbol(ResourceType type) {
        switch(type) {
            case SHIELDS: if(this.shields > 9) return ANSIColors.FRONT_BLUE +ANSIColors.SHIELD+":"+this.shields+ANSIColors.RESET; else return ANSIColors.FRONT_BLUE +ANSIColors.SHIELD+": "+this.shields+ANSIColors.RESET;

            case SERVANTS: if(this.servants > 9) return ANSIColors.FRONT_PURPLE +ANSIColors.SERVANT+":"+this.servants+ANSIColors.RESET; else return ANSIColors.FRONT_PURPLE +ANSIColors.SERVANT+": "+this.servants+ANSIColors.RESET;

            case COINS: if(this.coins > 9) return ANSIColors.FRONT_YELLOW +ANSIColors.COIN+":"+this.coins+ANSIColors.RESET; else return ANSIColors.FRONT_YELLOW +ANSIColors.COIN+": "+this.coins+ANSIColors.RESET;

            case STONES: if(this.stones > 9) return ANSIColors.FRONT_GREY +ANSIColors.STONE+":"+this.stones+ANSIColors.RESET; else return ANSIColors.FRONT_GREY +ANSIColors.STONE+": "+this.stones+ANSIColors.RESET;

            default: return null;
        }
    }

    /**Method to save in an ArrayList the resourceStack*/
    public ArrayList<String> toCli() {
        ArrayList<String> stack = new ArrayList<>();
        stack.add("╔══════╦══════╦══════╦══════╗");
        stack.add("║      ║      ║      ║      ║");
        stack.add("║ "+this.toCliSymbol(ResourceType.SHIELDS)+" ║ "+this.toCliSymbol(ResourceType.SERVANTS)+" ║ "+this.toCliSymbol(ResourceType.COINS)+" ║ "+this.toCliSymbol(ResourceType.STONES)+" ║");
        stack.add("║      ║      ║      ║      ║");
        stack.add("╚══════╩══════╩══════╩══════╝");

        return stack;
    }

    /**Method to save in an ArrayList */
    public ArrayList<String> toCliAdd() {
        ArrayList<String> stack = this.toCli();
        ArrayList<String> message = new ArrayList<>();

        message.add( "╔═══════════════════════════╗");
        message.add( "║ "+ANSIColors.FRONT_GREEN+"░░░ RESOURCES TO ADD  ░░░"+ANSIColors.RESET+" ║");
        stack.set(0, "╠══════╦══════╦══════╦══════╣");

        stack.addAll(0, message);

        return stack;
    }

    public ArrayList<String> toCliPay() {
        ArrayList<String> stack = this.toCli();
        ArrayList<String> message = new ArrayList<>();

        message.add( "╔═══════════════════════════╗");
        message.add( "║ "+ANSIColors.FRONT_RED+"░░░ RESOURCES TO PAY  ░░░"+ANSIColors.RESET+" ║");
        stack.set(0, "╠══════╦══════╦══════╦══════╣");

        stack.addAll(0, message);

        return stack;
    }


}
