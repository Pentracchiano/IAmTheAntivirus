/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.game;

import java.awt.Component;
import models.GameStatus;
import models.sprites.behaviors.Command;

/**
 *
 * @author Gerardo
 */
public class Shell extends Component {

    private String digitedCommand = "";
    private final String DEFAULT_STRING = "c:\\";
    private GameStatus gamestatus;
    private final int BACKSPACE_KEYCODE = 8;
    private final int DELETE_KEYCODE = 127;

    public Shell() {
        this.setFocusable(false);
        this.setDefault();
        this.gamestatus = GameStatus.getInstance();
    }

    /**
     * This method looks if the string digited in the shell is a command, if yes
     * it launch it. Then set the default string
     */
    public synchronized void launchCommand() {
        /*
        if digitedCommand
         */
        for (Command c : gamestatus.getCommands()) {

            if (this.getDigitedCommand().equals(c.getName())) {
                c.launch();
                setDefault();
                return;
            }
        }
        setDefault();
    }

    private synchronized void setDefault() {
        this.digitedCommand = DEFAULT_STRING;

    }

    public synchronized String getDigitedCommand() {
        return this.digitedCommand.substring(this.DEFAULT_STRING.length());
    }

    public synchronized void digitcommands(char c) {
        if ((int) c == BACKSPACE_KEYCODE || (int) c == DELETE_KEYCODE) {
            if (this.digitedCommand.length() > this.DEFAULT_STRING.length()) {
                this.digitedCommand = this.digitedCommand.substring(0, this.digitedCommand.length() - 1);
            } else {
                return;
            }
        } else {
            this.digitedCommand += c;
        }
    }

    public synchronized String getText() {
        return this.digitedCommand;
    }

}
