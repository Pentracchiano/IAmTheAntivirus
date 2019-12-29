/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.game;


import java.awt.Component;
import java.util.Set;
import models.GameStatus;
import models.sprites.behaviors.Command;

/**
 *
 * @author Gerardo
 */
public class Shell extends Component{
    private String digitedCommand="";
    private final String DEFAULT_STRING= ":/" ;
    private GameStatus gamestatus;

    public Shell(Set<Command> commands) {
        this.setFocusable(false);
        this.setText(DEFAULT_STRING);
        this.gamestatus = GameStatus.getInstance() ;    
    }
    
   
    
    public synchronized void launchCommand(){
        /*
        if digitedCommand
        */
        for(Command c : gamestatus.getCommands()){
            System.out.println(c.getName());
            if(this.getDigitedCommand().equals(c.getName())){
                c.launch();
                setDefault();
                return;
            }
        }
        setDefault();   
    }
    
    private synchronized void setDefault(){
        this.digitedCommand = "";
        this.setText(DEFAULT_STRING);
        
    }
    
    
    public synchronized String getDigitedCommand() {
        System.out.println(this.digitedCommand.substring(2));
        return this.digitedCommand.substring(2);
    }

    public synchronized void setDigitedCommand(String digitedCommand) {
        this.digitedCommand = digitedCommand;
    }

    public synchronized void digitcommands(char c){
        if((int)c == 8 || (int)c==127){
            this.digitedCommand=this.digitedCommand.substring(0, this.digitedCommand.length()-1);  
        }
        else
            this.digitedCommand+=c;
        //System.out.println((int)c+" "+c);
        
        //this.setText(DEFAULT_STRING + digitedCommand);
    }
    
    public synchronized void setText(String command){
        this.digitedCommand += command;
    } 
    
    public synchronized String getText(){
        return this.digitedCommand;
    }
   
}
