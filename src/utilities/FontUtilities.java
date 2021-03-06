/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author gabri
 */
public class FontUtilities {
    
    public static Font registerFont(String fontPath){
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            Font minecraft = Font.createFont(Font.TRUETYPE_FONT, new File(fontPath));
            ge.registerFont(minecraft);
            return minecraft;
        } catch (IOException|FontFormatException e) {
            System.out.println("Failed to register font.");
        }
        return null;
    }
    
}
