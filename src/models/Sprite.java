/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author ccarratu
 */
import java.awt.Image;
import java.awt.Rectangle;
import utilities.ImageUtilities;

public abstract class Sprite {
    private int x;
    private int y;
    // maybe is useful to provide a default image
    private Image image;
    
    private Sprite(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Sprite(int x, int y, Image image) {
        this(x, y);
        
        this.image = image;
    }
    
    // this constructor accepts the path of the Image instead of the Image
    public Sprite(int x, int y, String imagePath) {
        this(x, y);
        
        this.image = ImageUtilities.loadImageFromPath(imagePath);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public void setX(int x) {
        this.x = x;
    }
    
    public void setY(int y) {
        this.y = y;
    }
    
    public Image getImage() {
        return image;
    }
    
    public void setImage(Image image) {
        this.image = image;
    }
    
    public void setImage(String imagePath) {
        this.image = ImageUtilities.loadImageFromPath(imagePath);
    }
    
    @Override
    public String toString() {
        return "x=" + x + ", y=" + y;
    }

    public Rectangle getBounds() throws NullBoundsException {
        if(this.image == null) {
            throw new NullBoundsException();
        }
        
        return new Rectangle(x, y, image.getWidth(null), image.getHeight(null));
    }
}