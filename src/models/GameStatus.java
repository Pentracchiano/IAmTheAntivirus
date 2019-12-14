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
    private int currentWaveNumber;
    
    private GameStatus() {
        this.inGame = false;
        this.inWave = false;
        this.inWaveTransition = false;
        
        this.currentWaveNumber = 0;
    }
    
    public synchronized static void resetInstance() {
        GameStatus.instance = null;
    }
    
    public synchronized static GameStatus getInstance() {
        if(GameStatus.instance == null) {
            instance = new GameStatus();        
        }
        return instance;
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

    @Override
    public synchronized String toString() {
        return "GameStatus{" + "inGame=" + inGame + ", inWave=" + inWave + ", inWaveTransition=" + inWaveTransition + ", currentWaveNumber=" + currentWaveNumber + '}';
    }
    
}
