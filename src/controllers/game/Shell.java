/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.game;


import java.awt.event.KeyEvent;
import java.util.Set;
import javax.swing.JLabel;
import models.sprites.behaviors.Command;

/**
 *
 * @author Gerardo
 */
public class Shell extends JLabel{
    private String digitedCommand="";
    private final String DEFAULT_STRING= ":/" ;
    private final Set<Command> commands;

    public Shell(Set<Command> commands) {
        this.setFocusable(false);
        this.setText(DEFAULT_STRING);
        this.commands = commands;
        
    }
    
   
    
    public void launchCommand(){
        /*
        if digitedCommand
        */
        for(Command c : commands){
            if(digitedCommand.equals(c.getName())){
                c.launch();
                setDefault();
                return;
            }
        }
        setDefault();   
    }
    
    private void setDefault(){
        this.setText(DEFAULT_STRING);
        this.digitedCommand = "";
    }
    
    
    public String getDigitedCommand() {
        return digitedCommand;
    }

    public void setDigitedCommand(String digitedCommand) {
        this.digitedCommand = digitedCommand;
    }

    public void digitcommands(char c){
        if((int)c == 8 || (int)c==127){
            this.digitedCommand=this.digitedCommand.substring(0, this.digitedCommand.length()-1);  
        }
        else
            this.digitedCommand+=c;
        //System.out.println((int)c+" "+c);
        this.setText(DEFAULT_STRING + digitedCommand);
    }
    
    
}
