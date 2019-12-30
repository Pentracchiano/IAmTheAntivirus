/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import models.shop.Stat;
import models.sprites.behaviors.Command;

/**
 *
 * @author ccarratu
 */
// this is a singleton
public class GameStatus {

    private static GameStatus instance = null;

    private boolean inGame;
    private boolean inWave;
    private boolean inShop;
    private boolean inWaveTransition;
    private final List<Stat> stats = new ArrayList<>();
    private final List<String> highscores = new ArrayList<>();
    
    // the number of the current wave
    private int currentWaveNumber;
    
    /**
     * the next two variables represent the score of the player, incrementing
     * during the game. The "score" never decreases; the bitcoins are always
     * equal to the score, until the player decides to spend them in the shop to
     * get upgrades. This doesn't affect the total overall score.
     */
    private int score = 0;
    private int bitcoins = 0;

    /**
     * The multiplier increses when the player consecutively hits enemies, the
     * maximum value reachable is MAX_MULTIPLIER. When the player misses a virus
     * the multiplier is reset to 1.
     */
    private int multiplier = 1;
    public final int MAX_MULTIPLIER = 8;

    /**
     * represents how many times the player hit the viruses consecutively and is
     * used to update the multiplier, every HITS_PER_MULTIPLIER the multiplier
     * is incremented. The consecutiveHits is reset when the player misses a virus
     * or when a virus hit the base
     */
    private int consecutiveHits = 0;
    public final int HITS_PER_MULTIPLIER = 3;
    
    private final static int BASE_MAX_HEALTH = 40;
    private final static int BASE_ATTACK = 15;
    
    private final static int BASE_COST = 150;
    private final static double COST_MULTIPLIER = 1.8;
    
    // The multipliers are used to balance the differences between the attributes.
    // For example, a set fo standard value for the attributes is:
    // health = 25, attack = 5, speed = 1
    // all the attributes have the same importance, so, when the difficulty, the value and the growth of the attributes are evaluated, the values have to be balanced.
    // So for the difficulty, each attribute si multiplied for the inverse of the value of the corresponding multiplier.
    // For example the speed is multiplied by 5 and the health by 2
    // In this way the attributes are more balanced.
    
    // thiese multipliers are also used for deciding the price of an item in the shop
    public final static double VIRUS_HEALTH_MULTIPLIER = 0.5;
    public final static double ENEMY_ATTACK_MULTIPLIER = 0.3;
    public final static double VIRUS_SPEED_MULTIPLIER = 0.1;
    
    
    private final Set<Command> commands;

    private GameStatus() {
        this.inGame = false;
        this.inWave = false;
        this.inShop = false;
        this.inWaveTransition = false;
        this.currentWaveNumber = 0;

        commands = new HashSet<>();
   
        this.stats.add(new Stat("health", "Health", "Next max health value: ", BASE_MAX_HEALTH, BASE_COST, ENEMY_ATTACK_MULTIPLIER, COST_MULTIPLIER));

        this.stats.add(new Stat("attack", "Attack", "Next attack value: ", BASE_ATTACK, BASE_COST, VIRUS_HEALTH_MULTIPLIER, COST_MULTIPLIER));
        
        for(int i = 0; i < 5; i++){
            this.highscores.add("-----;000");
        }
    }

    public synchronized List<Stat> getStats() {
        return stats;
    }

    public synchronized Set<Command> getCommands() {
        return commands;
    }
    
    public synchronized void addCommand(Command command){
        this.commands.add(command);
    }


    public synchronized static GameStatus getInstance() {
        if(GameStatus.instance == null) {
            instance = new GameStatus();       
        }
        return instance;
    }

    /**
     * Marks the current GameStatus instance for deletion: the next instance will be a fresh one
     * with default parameters.
     */
    public synchronized static void disposeInstance(){
        GameStatus.instance = null;
    }
    
    public synchronized int getMultiplier() {
        return multiplier;
    }

    public synchronized void resetConsecutiveHits() {
        this.multiplier = 1;
        this.consecutiveHits = 0;
    }

    public synchronized void incrementConsecutiveHits() {
        this.consecutiveHits += 1;

        this.multiplier = consecutiveHits / HITS_PER_MULTIPLIER + 1;

        if (this.multiplier > MAX_MULTIPLIER) {
            this.multiplier = MAX_MULTIPLIER;
        }
    }

    public synchronized void addBitcoinsAndScore(int bitcoins) {
        this.bitcoins += bitcoins;
        this.score += bitcoins;
    }

    public synchronized void withdrawBitcoins(int bitcoins) {
        if (this.bitcoins - bitcoins < 0) {
            throw new IllegalArgumentException("Bitcoins can't become negative!");
        }

        this.bitcoins -= bitcoins;
    }

    public synchronized int getScore() {
        return score;
    }

    public synchronized int getBitcoins() {
        return bitcoins;
    }

    public synchronized boolean isInGame() {
        return inGame;
    }

    public synchronized void setInGame(boolean inGame) {
        this.inGame = inGame;
    }

    public synchronized boolean isInWave() {
        return inWave;
    }

    public synchronized void setInWave(boolean inWave) {
        this.inWave = inWave;
    }

    public synchronized int getCurrentWaveNumber() {
        return currentWaveNumber;
    }

    public synchronized void setCurrentWaveNumber(int currentWaveNumber) {
        this.currentWaveNumber = currentWaveNumber;
    }

    public synchronized boolean isInWaveTransition() {
        return inWaveTransition;
    }

    public synchronized void setInWaveTransition(boolean waveTransition) {
        this.inWaveTransition = waveTransition;
    }

    public synchronized boolean isInShop() {
        return inShop;
    }

    public synchronized void setInShop(boolean inShop) {
        this.inShop = inShop;
    }

    public synchronized int getDefaultMaxHealth() {
        return BASE_MAX_HEALTH;
    }

    public synchronized List<String> getHighscores() {
        return highscores;
    }

    public synchronized void setHighscores(int i, String name, String score) {
        this.highscores.set(i, name + ";" + score);
    }


    
    
    @Override
    public String toString() {
        return "GameStatus{" + "inGame=" + inGame + ", inWave=" + inWave + ", inShop=" + inShop + ", inWaveTransition=" + inWaveTransition + ", stats=" + stats + ", currentWaveNumber=" + currentWaveNumber + ", score=" + score + ", bitcoins=" + bitcoins + ", multiplier=" + multiplier + ", MAX_MULTIPLIER=" + MAX_MULTIPLIER + ", consecutiveHits=" + consecutiveHits + ", HITS_PER_MULTIPLIER=" + HITS_PER_MULTIPLIER + '}';
    }
}
