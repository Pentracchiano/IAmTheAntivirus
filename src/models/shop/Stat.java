/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.shop;

/**
 *
 * @author gabri
 */
public class Stat {
    private final String name;
    private final String id;
    
    private int value = 0;
    private int cost;
    private String description;
    private static final int MAX_CAP = 999999;
    
    private final int BASE_VALUE;
    private final int BASE_COST;
    
    private final double VALUE_MULTIPLIER;
    private final double COST_MULTIPLIER; 

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
    public Stat(String id, String name, String description, int BASE_VALUE, int BASE_COST, double VALUE_MULTIPLIER, double COST_MULTIPLIER) {
        this.id = id;
        this.name = name;
        this.description = description;
        
        this.value = BASE_VALUE;
        this.cost = BASE_COST;
        
        this.BASE_VALUE = BASE_VALUE;
        this.BASE_COST = BASE_COST;
        this.VALUE_MULTIPLIER = VALUE_MULTIPLIER;
        this.COST_MULTIPLIER = COST_MULTIPLIER;
    }
    
    /*
    public void increase(int value){
        int next = this.check_max_cap(this.value += value);
        this.value = next;
    }
    */

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getId() {
        return id;
    }
    
    public int getNextValue(){
        int newValue = this.value + (int) (BASE_VALUE * VALUE_MULTIPLIER);
        return this.check_max_cap(newValue);
        
    }
    
    public int getNextCost(){
        // the cost is increased in exponential way
        // this way the player should lose after some time
        int newCost = (int) ( this.cost * COST_MULTIPLIER );
        return this.check_max_cap(newCost);
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
    
    public void buy(){
        this.cost = this.getNextCost();
        this.value = this.getNextValue();
    }
    
    private int check_max_cap(int value){
        if( value > MAX_CAP )
            return MAX_CAP;
        else
            return value;
    }
    
}