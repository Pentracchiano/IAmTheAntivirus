/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.game;

import models.Keyboard.Key;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import javax.imageio.ImageIO;
import models.Base;
import models.Keyboard;
import models.Virus;


public class GameView extends View{
 
    private Keyboard keyboard;
    private Base base;
    private ArrayList<Virus> viruses;
    private final int B_WIDTH = 1300;
    private final int B_HEIGHT = 747;
    private BufferedImage backgroundImage;

    public GameView() throws IOException {

        initView();
    }

    private void initView() throws IOException{

        viruses = new ArrayList<>();
        base = new Base(100, 100, 10);
        setBackground(Color.GRAY);        
        backgroundImage = ImageIO.read(new File("src/resources/background/background.png"));               
	setFocusable(true);
        setPreferredSize(new Dimension(B_WIDTH,B_HEIGHT));
        keyboard = new Keyboard(160,300);
    }
    
    @Override
    public void update(){
       this.repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage,0,0,this);
        
        drawKeyboard(g);
        drawViruses(g);
        drawBase(g);
        
        Toolkit.getDefaultToolkit().sync();
    }
    
    private void drawViruses(Graphics g){
        for(Virus vir : viruses){
            g.drawImage(vir.getImage(), vir.getX(), vir.getY(), this);
        }
    }
    
    private void drawKeyboard(Graphics g) {
        
        
        g.drawImage(keyboard.getImage(), keyboard.getX(),keyboard.getY(), this);
        
        Collection<Key> coll = keyboard.getKeys();
        
        for(Key key:coll){
            g.drawImage(key.getImage(),key.getX(),key.getY(),this);
        };
        
    }
    
    private void drawBase(Graphics g){
        g.drawImage(base.getImage(), base.getX(),base.getY(), this);
    }

    public Keyboard getKeyboard() {
        return keyboard;
    }

    public Base getBase() {
        return base;
    }

    public ArrayList<Virus> getViruses() {
        return viruses;
    }
    
    
    
    
    

   


}