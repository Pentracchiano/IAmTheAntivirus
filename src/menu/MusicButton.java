/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;

/**
 *
 * @author marta
 */
public class MusicButton extends JToggleButton {
    
    private static Icon musicOn = new ImageIcon("src/resources/on.png");
    private static Icon musicOff = new ImageIcon("src/resources/off.png");
    
    public MusicButton (){
        super(musicOn);
        super.setSelectedIcon(musicOff);
        this.initLookAndFeel(); 
    }
    
    public void initLookAndFeel(){
        this.setMargin(new Insets(0,0,0,0));
        this.setFocusPainted(false);
        this.setBorderPainted(false);  
        this.setFocusPainted(false);
        this.setContentAreaFilled(false);  
        this.setPreferredSize(new Dimension(70,70));
    }
    
}
