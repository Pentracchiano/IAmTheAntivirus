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
    private int value = 0;
    private String name;
    private String id;
    private int cost;
    private String description;
    private static final int MAX_CAP = 999999;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
    public Stat(String id, String name, int cost, int value, String description) {
        this.value = value;
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.description = description;
    }
    
    public void increase(int value){
        int next = this.check_max_cap(this.value += value);
        this.value = next;
    }

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
        return this.check_max_cap(value*2);
        
    }
    
    public int getNextCost(){
        return this.check_max_cap(cost*2);
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