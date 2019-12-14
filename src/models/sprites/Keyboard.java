package models.sprites;

import models.sprites.exceptions.KeyNotFoundException;
import java.awt.Image;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import utilities.ImageUtilities;

public class Keyboard extends Sprite {

    public enum State {
        PRESSED, RELEASED
    }

    private static final int DEFAULT_KEY_ATTACK = 10;
    private static final char CHARACTERS[] = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0', 'Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P', 'A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'Z', 'X', 'C', 'V', 'B', 'N', 'M'};
    private static final String KEYBOARD_IMAGE_PATH = "src/resources/keyboard/keyboard.png";

    private Map<Character, Key> keyboard;

    public Keyboard(int x, int y) {
        super(x, y, KEYBOARD_IMAGE_PATH);
        initKeyboard();
    }

    private void initKeyboard() {
        keyboard = new HashMap<>();
        initKeys();
    }

    private void initKeys() {

        int spaceAmongKeys = 5;
        int border = 15;
        int xKeyboard = getX();
        int yKeyboard = getY();
        int xKey = xKeyboard + border;
        int yKey = yKeyboard + border;
        char idKey;
        int widthKey;
        int heightKey;
        Key key;

        for (int i = 0; i < CHARACTERS.length; i++) {

            idKey = CHARACTERS[i];
            key = new Key(xKey, yKey, idKey); //creo sprite tasto
            keyboard.put(idKey, key); //inserisco nella tabella

            widthKey = key.getImage().getWidth(null);
            heightKey = key.getImage().getHeight(null);

            if (idKey == '0' || idKey == 'P' || idKey == 'L') {
                //vado a capo
                xKey = xKeyboard + border;
                yKey = yKey + border + heightKey;
            } else {
                xKey = xKey + widthKey + spaceAmongKeys;
            }
        }
    }

    //attenzione dopo si deve rivedere
    public Set<Character> getSetKeyKeys() {

        return keyboard.keySet();
    }

    public Collection<Key> getKeys() {
        return keyboard.values();
    }
    //fine attenzione

    public void press(char id) throws KeyNotFoundException{      
        if(!keyboard.containsKey(id))
            throw new KeyNotFoundException();
        
        Key key = keyboard.get(id);
        key.press();
        
    }

    public Key getKey(char keyCode) throws KeyNotFoundException{
        if(!keyboard.containsKey(keyCode))
            throw new KeyNotFoundException();
        return keyboard.get(keyCode);
    }

    public void release(char id) throws KeyNotFoundException{
        
        if(!keyboard.containsKey(id))
            throw new KeyNotFoundException();
            
        Key key = keyboard.get(id);
        key.release();
        
    }

    public class Key extends Sprite {

        private char id;
        private State state;
        private int attack;
        private final static String PARENT_IMAGE_PATH = "src/resources/key/";
        private Map<State, Image> keyImages;

        private Key(int x, int y, char id) {
            super(x, y, PARENT_IMAGE_PATH + "released/" + id + ".png");
            this.initKey(id);
        }

        private void initKey(char id) {
            this.id = id;
            this.attack = Keyboard.DEFAULT_KEY_ATTACK;
            this.state = State.RELEASED;

            Image imageKeyPressed = ImageUtilities.loadImageFromPath(PARENT_IMAGE_PATH + "pressed/" + id + ".png");
            Image imageKeyReleased = ImageUtilities.loadImageFromPath(PARENT_IMAGE_PATH + "released/" + id + ".png");

            keyImages = new HashMap<>();
            keyImages.put(State.PRESSED, imageKeyPressed);
            keyImages.put(State.RELEASED, imageKeyReleased);

        }

        public char getId() {
            return id;
        }

        public State getState() {
            return state;
        }

        public int getAttack() {
            return attack;
        }

        public void setAttack(int attack) {
            this.attack = attack;
        }

        private void press() {
            this.state = State.PRESSED;
            setImage(keyImages.get(state));
        }

        private void release() {
            this.state = State.RELEASED;
            setImage(keyImages.get(state));
        }
    }
}
