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

public abstract class Sprite {
    protected int x;
    protected int y;
    protected Image image;

    public Sprite(int x, int y, Image image) {
        this.x = x;
        this.y = y;
        
        // maybe is usefult to provide a default image if "image" is null
        this.image = image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public Image getImage() {
        return image;
    }

    @Override
    public String toString() {
        return "Sprite{" + "x=" + x + ", y=" + y + ", image=" + image + '}';
    }

    public Rectangle getBounds() throws NullBoundsException{
        if(image == null) {
            throw new NullBoundsException();
        }
        
        return new Rectangle(x, y, image.getWidth(null), image.getHeight(null));
    }
}