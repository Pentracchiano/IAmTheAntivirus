/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;
import java.util.List;
import models.shop.Stat;

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
    
    private final int DEFAULT_MAX_HEALTH = 20;
    private final int DEFAULT_ATTACK = 10;
    private final int DEFAULT_COST = 50;

    private GameStatus() {
        this.inGame = false;
        this.inWave = false;
        this.inShop = false;
        this.inWaveTransition = false;
        this.currentWaveNumber = 0;
        
        
        this.stats.add(new Stat("health","Max Health",DEFAULT_COST,DEFAULT_MAX_HEALTH,"Next max health value: "));
        this.stats.add(new Stat("attack","Attack",DEFAULT_COST,DEFAULT_ATTACK,"Next attack value: "));
    }

    public synchronized List<Stat> getStats() {
        return stats;
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
        return DEFAULT_MAX_HEALTH;
    }

    @Override
    public String toString() {
        return "GameStatus{" + "inGame=" + inGame + ", inWave=" + inWave + ", inShop=" + inShop + ", inWaveTransition=" + inWaveTransition + ", stats=" + stats + ", currentWaveNumber=" + currentWaveNumber + ", score=" + score + ", bitcoins=" + bitcoins + ", multiplier=" + multiplier + ", MAX_MULTIPLIER=" + MAX_MULTIPLIER + ", consecutiveHits=" + consecutiveHits + ", HITS_PER_MULTIPLIER=" + HITS_PER_MULTIPLIER + '}';
    }
}
