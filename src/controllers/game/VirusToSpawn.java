/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.game;

import models.sprites.Virus;

/**
 * 
 * 
 * @author LordCatello
 */
public class VirusToSpawn {
    private final Virus virus;
    private final int timeToSpawn;

    public VirusToSpawn(Virus virus, int delay) {
        this.virus = virus;
        this.timeToSpawn = delay;
    }

    public Virus getVirus() {
        return virus;
    }

    public int getTimeToSpawn() {
        return timeToSpawn;
    }

    @Override
    public String toString() {
        return "VirusToSpawn{" + "virus=" + virus + ", delay=" + timeToSpawn + '}';
    }
    
}
