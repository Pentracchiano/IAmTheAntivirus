/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package behaviors;

/**
 *
 * @author Francesco
 */
public class MovableGreaterThanFieldException extends RuntimeException{
    
    public MovableGreaterThanFieldException(){
        super("A movable object can't be greater than the field it moves over");
    }
}
