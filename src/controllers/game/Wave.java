/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import models.sprites.Virus;

/**
 * Wave represents a wave of viruses that the player has to defeat.
 * 
 * @author LordCatello
 */
public class Wave {
        // It represents the number of viruses that have to spawn in the current wave
        private final int waveSize;
        private final List<VirusToSpawn> virusesToSpawn;
        // It's final because we only add or remove viruses, but we don't change the referenced object
        // It containts the spawned and alive viruses
        private final Collection<Virus> aliveSpawnedViruses;
        
        /**
         * Creates a wave with the specified collection of VirusToSpaw passed by parameters.
         * 
         * @param virusesToSpawn The collection of VirusToSpawn that have to spawn in this wave.
         */
        public Wave(List<VirusToSpawn> virusesToSpawn) {
            this.virusesToSpawn = virusesToSpawn;
            this.waveSize = virusesToSpawn.size();
            this.aliveSpawnedViruses = new ArrayList<>();
        }
        
        public int getWaveSize() {
            return waveSize;
        }
        
        /**
         * 
         * @return Return true If there is at least one virus that is not spawned yet.
         *         Return false otherwise.
         */
        public boolean hasVirusToSpawn() {
            return !virusesToSpawn.isEmpty();
        }
        
        /**
         * 
         * @return Return true if there is at least one spawned alive virus in the wave. 
         *         Return false otherwise.
         */     
        public boolean hasAliveViruses() {
            return !aliveSpawnedViruses.isEmpty();
        }
        
        /**
         * Spawns the next virus if it has to spawn.
         * 
         * @param timeCount The number of iterations that are elapsed in this wave.
         */
        public void spawnVirus(int timeCount) {
            if (hasVirusToSpawn()) {
                VirusToSpawn virusToSpawn = virusesToSpawn.get(0);
                if(virusToSpawn.getTimeToSpawn() <= timeCount) {
                    virusesToSpawn.remove(0);
                    aliveSpawnedViruses.add(virusToSpawn.getVirus());
                }
            }
        }
        
        /**
         * 
         * @return Return the collection of alive spawned viruses. 
         */
        public Collection<Virus> getAliveSpawnedViruses() {
            return aliveSpawnedViruses;
        }
        
        public int getVirusesToSpawnSize() {
            return virusesToSpawn.size();
        }
        
}
