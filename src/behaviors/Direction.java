/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package behaviors;

/**
 *
 * @author ccarratu
 */
public class Direction {
    int x; // 0, 1, -1
    int y; // 0, 1, -1
    // 9 combinations

    public Direction(int x, int y) {
        // we should make some checks, because x and y can be only 0, 1, -1
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Direction{" + "x=" + x + ", y=" + y + '}';
    }
    
}
