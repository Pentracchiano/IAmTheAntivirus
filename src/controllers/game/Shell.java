/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.game;

import javax.swing.JLabel;

/**
 *
 * @author Gerardo
 */
public class Shell extends JLabel{
    private String digitedCommand="";
    private final String DEFAULT_STRING= ":/" ;
    //private Map<String,Command> commands;

    public Shell() {
        this.setFocusable(false);
        this.setText(DEFAULT_STRING);
    }
    
    public void launchCommand(){
        /*
        if digitedCommand
        */
        if(digitedCommand.equals("FIREWALL")){
            System.out.println("Firewall");
        }
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
        this.digitedCommand+=c;
        this.setText(DEFAULT_STRING + digitedCommand);
    }
    
    
}
