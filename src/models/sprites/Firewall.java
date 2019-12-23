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
import models.sprites.behaviors.Command;

/**
 *
 * @author mario
 */
public class Firewall extends Sprite implements ActionListener, Command{
    
    private List<Image> animationFirewall;
    private final static String PARENT_IMAGE_PATH = "src/resources/command/firewall/";
    private static final String FIREWALL_IMAGE_PATH = PARENT_IMAGE_PATH+"0.png";
    private Timer timer;
    private Iterator animation;
    private final String name = "FIREWALL";
    private boolean active;
    
    public Firewall(int x, int y){
        super(x,y,FIREWALL_IMAGE_PATH);
        timer = new Timer(200,this);
        
        initFirewall();
        
    }
    
    private void initFirewall(){
        active = false;
        animationFirewall = new LinkedList<>();
        Image image0 = ImageUtilities.loadImageFromPath(PARENT_IMAGE_PATH + "0.png");
        Image image1 = ImageUtilities.loadImageFromPath(PARENT_IMAGE_PATH + "1.png");
        Image image2 = ImageUtilities.loadImageFromPath(PARENT_IMAGE_PATH+"2.png");
        Image image3 = ImageUtilities.loadImageFromPath(PARENT_IMAGE_PATH+"3.png");
        
        //animationFirewall.add(image0);
        animationFirewall.add(image1);
        animationFirewall.add(image2);
        animationFirewall.add(image3);
        animationFirewall.add(image2);
        animationFirewall.add(image3);
        animationFirewall.add(image1);
        animationFirewall.add(image0);

    }
    
    @Override
    public String getName(){
        return this.name;
    }
    
    @Override
    public void launch(){
        animation = animationFirewall.iterator();
        active = true;
        timer.start();
     
    }
    
    public boolean isActive(){
        return active;
    }
   
    private void startFirewall(){
        
        
        if(animation.hasNext()){
            Image i = (Image)animation.next();
            setImage(i);
            //System.out.println(i);
        }else{
            active = false;
            timer.stop();
            
           
        }
        
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        startFirewall();
        
        
    }
        
        
        
    }
    
    
    
    
    
    
    

