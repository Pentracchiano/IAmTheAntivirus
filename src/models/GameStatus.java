/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author ccarratu
 */
// this is a singleton
public class GameStatus {

    private static GameStatus instance = null;

    private boolean inGame;
    private boolean inWave;
    private boolean inWaveTransition;
    // the number of the current wave
    private int currentWave;

    // number of enemies assigned to the currentWave
    private int totalWaveEnemies;
    // totalWaveEnemies - totalDeadEnemies
    private int remainingWaveEnemies;

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
    private final int MAX_MULTIPLIER = 8;

    /**
     * represents how many times the player hit the viruses consecutively and is
     * used to update the multiplier, every HITS_PER_MULTIPLIER the multiplier
     * is incremented.
     */
    private int consecutiveHits = 0;
    private final int HITS_PER_MULTIPLIER = 3;

    private GameStatus(boolean inGame, boolean inWave, int currentWave, int totalWaveEnemies, int remainingWaveEnemies, boolean waveTransition) {
        this.inGame = inGame;
        this.inWave = inWave;

        this.currentWave = currentWave;

        this.totalWaveEnemies = totalWaveEnemies;
        this.remainingWaveEnemies = remainingWaveEnemies;
    }

    public synchronized static GameStatus getInstance() {
        if (GameStatus.instance == null) {
            instance = new GameStatus(false, false, 0, 0, 0, false);
        }
        return instance;
    }

    public synchronized static void resetInstance(){
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

    public synchronized int getCurrentWave() {
        return currentWave;
    }

    public synchronized void setCurrentWave(int currentWave) {
        this.currentWave = currentWave;
    }

    public synchronized int getTotalWaveEnemies() {
        return totalWaveEnemies;
    }

    public synchronized void setTotalWaveEnemies(int totalWaveEnemies) {
        this.totalWaveEnemies = totalWaveEnemies;
    }

    public synchronized int getRemainingWaveEnemies() {
        return remainingWaveEnemies;
    }

    public synchronized void setRemainingWaveEnemies(int remainingWaveEnemies) {
        this.remainingWaveEnemies = remainingWaveEnemies;
    }

    public synchronized boolean isInWaveTransition() {
        return inWaveTransition;
    }

    public synchronized void setInWaveTransition(boolean waveTransition) {
        this.inWaveTransition = waveTransition;
    }

}
