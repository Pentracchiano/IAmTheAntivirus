/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.sprites;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import utilities.ImageUtilities;
import javax.swing.Timer;
import models.GameStatus;
import models.sprites.behaviors.Command;

/**
 *
 * @author mario
 */
public class Firewall extends Sprite implements ActionListener, Command{
    
    private List<Image> animationFirewall;
    private final static String PARENT_IMAGE_PATH = "src/resources/command/firewall/";
    private static final String FIREWALL_IMAGE_PATH = PARENT_IMAGE_PATH+"0.png";
    private final int COOL_DOWN = 10*1000;
    private final int DELAY = 200;
    private final int DURATION = 2*1000;
    private final Timer timer;
    private Iterator animation;
    private final String name = "FIREWALL";
    private boolean active;
    private int coolDown;
    private int time;
    
    public Firewall(int x, int y){
        super(x,y,FIREWALL_IMAGE_PATH);
        timer = new Timer(DELAY,this);
        initFirewall();
        
    }
    
    private void initFirewall(){
        active = false;
        coolDown = 0;
        time = 0;
        animationFirewall = new LinkedList<>();
        
        Image image2 = ImageUtilities.loadImageFromPath(PARENT_IMAGE_PATH+"2.png");
        Image image3 = ImageUtilities.loadImageFromPath(PARENT_IMAGE_PATH+"3.png");

        animationFirewall.add(image2);
        animationFirewall.add(image3);
        

    }
    
    @Override
    public String getName(){
        return this.name;
    }
    
    @Override
    public void launch(){
        if(isInCoolDown() || isActive())
            return;
        coolDown = COOL_DOWN;
        time = 0;
        start();
        
    }
    
    private void start(){
        animation = animationFirewall.iterator(); 
        timer.start();
    }
    
    private void stop(){
        if(!timer.isRunning())
            return;
        timer.stop();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(!GameStatus.getInstance().isInWave()){            
            endAnimate();
            stop();
            time = 0;
            coolDown = 0;
            return;
        }
        coolDown-=DELAY;        
        time+=DELAY;
        active = true;
        
        if(time==DELAY){
            startAnimate();
        }
        else if(time<DURATION)
            keepAnimate();
        else {
            endAnimate();
        }
        
        if(!isInCoolDown() && !isActive())
            stop();           
    }
    
    private void print(){
        System.out.println("Cool down: "+coolDown);   
        System.out.println("Active: "+isActive());
        System.out.println("Time: "+time);
        System.out.println("In wave: "+GameStatus.getInstance().isInWave());
        System.out.println("Timer: "+timer.isRunning());
    }
    private void startAnimate(){
        Image image1 = ImageUtilities.loadImageFromPath(PARENT_IMAGE_PATH + "1.png");
        setImage(image1);
    }
    private void keepAnimate(){   
        
        if(animation.hasNext()){
            Image i = (Image)animation.next();
            setImage(i);
        }
        else{
            animation = animationFirewall.iterator();
        }
                 
    }
    
    private void endAnimate(){
        Image image0 = ImageUtilities.loadImageFromPath(PARENT_IMAGE_PATH + "0.png");
        setImage(image0);
        active = false; 
        
    }
    
    @Override
    public boolean isActive(){
        return active;
    }
    
    @Override
    public boolean isInCoolDown(){
      return coolDown>0;  
    }

   
        
        
        
    }
    
    
    
    
    
    
    

