/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.sprites.behaviors;

/**
 *
 * @author mario
 */
public interface Command {
    
    public String getName();
    public void launch();
    
}
