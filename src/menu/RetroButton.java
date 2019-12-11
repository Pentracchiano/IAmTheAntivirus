/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import javax.swing.JButton;

/**
 *
 * @author gabri
 */
public class RetroButton extends JButton {
    
    private static final Color DEFAULT_BG_COLOR = Color.black;
    private static final Color DEFAULT_FG_COLOR = Color.green;
    
    public RetroButton(){
        super();
        this.initLookAndFeel();    
    }
    
    private void initLookAndFeel(){
        this.setMargin(new Insets(0,0,0,0));
        this.setFocusPainted(false);
        this.setBorderPainted(false);  
        this.setFocusPainted(false);
        this.setContentAreaFilled(false);  
        this.setBackground(DEFAULT_BG_COLOR);
        this.setForeground(DEFAULT_FG_COLOR);
        this.setFont(new Font("Minecraft", Font.BOLD, 40));
        
    }
    
    @Override
    public void paintComponent( Graphics g ){
        
        g.setColor(this.getBackground());
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        super.paintComponent(g);
        
    }
    
    public void toggleColors(){
        Color bgcolor = this.getForeground();
        Color fgcolor = this.getBackground();
        
        this.setBackground(bgcolor);
        this.setForeground(fgcolor);

        this.repaint();
    }
    
    
    
    
}
