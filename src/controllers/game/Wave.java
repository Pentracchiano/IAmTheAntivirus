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
 *
 * @author LordCatello
 */
public class Wave {
        // It represents the number of viruses that have to spawn in the current wave
        private final int waveSize;
        private final List<VirusToSpawn> virusesToSpawn;
        // it's final because we only add or remove viruses, but we don't change the referenced object
        // it containts the spawned and alive viruses
        private final Collection<Virus> aliveSpawnedViruses;
        
        public Wave(List<VirusToSpawn> virusesToSpawn) {
            this.virusesToSpawn = virusesToSpawn;
            this.waveSize = virusesToSpawn.size();
            this.aliveSpawnedViruses = new ArrayList<>();
        }
        
        public int getWaveSize() {
            return waveSize;
        }
        
        public boolean hasVirusToSpawn() {
            return !virusesToSpawn.isEmpty();
        }
        
        public boolean hasAliveViruses() {
            return !aliveSpawnedViruses.isEmpty();
        }
        
        public void spawnVirus(int timeCount) {
            if (hasVirusToSpawn()) {
                VirusToSpawn virusToSpawn = virusesToSpawn.get(0);
                if(virusToSpawn.getTimeToSpawn() <= timeCount) {
                    virusesToSpawn.remove(0);
                    aliveSpawnedViruses.add(virusToSpawn.getVirus());
                }
            }
        }
        
        public Collection<Virus> getAliveSpawnedViruses() {
            return aliveSpawnedViruses;
        }
        
        public int getVirusesToSpawnSize() {
            return virusesToSpawn.size();
        }
        
}
