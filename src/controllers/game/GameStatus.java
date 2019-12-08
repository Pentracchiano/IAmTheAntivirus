/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.game;

/**
 *
 * @author ccarratu
 */
// this is a singleton
public class GameStatus {
    private static GameStatus istance = null;
    
    private boolean inGame;
    private boolean inWave;
    
    // the number of the current wave
    private int currentWave;
    
    // number of enemies assigned to the currentWave
    private int totalWaveEnemies;
    // totalWaveEnemies - totalDeadEnemies
    private int remainingWaveEnemies;
    
    private GameStatus(boolean inGame, boolean inWave, int currentWave, int totalWaveEnemies, int remainingWaveEnemies) {
        this.inGame = inGame;
        this.inWave = inWave;
        
        this.currentWave = currentWave;
        
        this.totalWaveEnemies = totalWaveEnemies;
        this.remainingWaveEnemies = remainingWaveEnemies;
    }
    
    public synchronized static GameStatus getIstance() {
        if(GameStatus.istance == null) {
            istance = new GameStatus(false, false, 0, 0, 0);        
        }
        return istance;
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
}
