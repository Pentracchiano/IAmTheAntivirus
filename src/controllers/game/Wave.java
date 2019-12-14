/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import models.Virus;

/**
 *
 * @author LordCatello
 */
public class Wave {
        // I shall create a class that represent a virus with a delay
        private final List<VirusToSpawn> list;
        
        // it's final because we only add or remove viruses, but we don't change the referenced object
        // it containts the spawned and alive viruses
        private final Collection<Virus> aliveSpawnedViruses;
        
        public Wave() {
            list = new ArrayList<>();
        }
        
        public int getSize() {
            return list.size();
        }
        
        public VirusToSpawn getCurrentElement() {
            if(list.isEmpty()) {
                return null;
            }
            
            return list.get(0);
        }
        
        public VirusToSpawn removeCurrentElement() {
            return list.remove(0);
        }
        
        public void addElement(VirusToSpawn element) {
            list.add(element);
        }
}
