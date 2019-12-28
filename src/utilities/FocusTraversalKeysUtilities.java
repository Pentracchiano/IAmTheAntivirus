/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.awt.Component;
import java.awt.KeyboardFocusManager;
import java.util.HashSet;
import java.util.Set;
import javax.swing.KeyStroke;

/**
 *
 * @author gabri
 */
public class FocusTraversalKeysUtilities {
    
    public static void changeFocusTraversalKeys(Component c) {
        Set<KeyStroke> forwardKeys = new HashSet<>();
        forwardKeys.add(KeyStroke.getKeyStroke("DOWN"));
        forwardKeys.add(KeyStroke.getKeyStroke("TAB"));
        
        Set<KeyStroke> backwardKeys = new HashSet<>();
        backwardKeys.add(KeyStroke.getKeyStroke("UP"));
        
       
        c.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, forwardKeys);
        c.setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, backwardKeys);
    }
}
