/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sounds;

import java.io.File;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
/**
 *
 * @author marta
 */
public class BackgroundMusic implements Runnable  {
    
    private String fileName;
    private Clip clip;
    private boolean running = true;

    public BackgroundMusic(String fileName) {
        this.fileName = fileName;
        initSound(fileName);
    }
    
    private void initSound (String fileName){
        try {
            File soundFile = new File(fileName);           
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            AudioFormat format = audioInputStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            clip = (Clip)AudioSystem.getLine(info);
            clip.open(audioInputStream);  
            FloatControl gainControl = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-10); //volume            
        } catch( Exception e){
        }
    }
    
    public void setRunning (Boolean running){
        this.running = running;
    }
    
    @Override 
    public void run (){
        while(true){    
            if(running){
                clip.loop(Clip.LOOP_CONTINUOUSLY);
                //clip.start();
            } else {
                clip.stop();
            }
        }
    }
    
}
