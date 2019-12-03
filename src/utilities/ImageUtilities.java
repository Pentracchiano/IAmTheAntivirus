/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author ccarratu
 */
public class ImageUtilities {
    
    public static Image loadImageFromPath(String path) {     
        ImageIcon imageIcon = new ImageIcon(path);
        return imageIcon.getImage();
    }
    
}
