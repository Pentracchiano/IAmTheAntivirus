/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.awt.Image;

/**
 *
 * @author ccarratu
 */
public abstract class Virus extends Enemy implements Movable, Damageable {

    private int hp;
    private int speed;

    public Virus(int x, int y, Image image, int attack, int hp, int speed) {
        super(x, y, image, attack);

        initVirus(hp, speed);
    }

    public Virus(int x, int y, String imagePath, int attack, int hp, int speed) {
        super(x, y, imagePath, attack);

        initVirus(hp, speed);
    }

    private void initVirus(int hp, int speed) {
        this.hp = hp;
        this.speed = speed;
    }

    public int getHp() {
        return hp;
    }

    public int getSpeed() {
        return speed;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isAlive() {
        return hp > 0;
    }

    @Override
    public void move() {
        // at the moment, any virus simply advance towards the base following a line
        setY(getY() - speed);

        if (getY() < 0) {
            setY(0);
        }
    }

    @Override
    public void damage(int damage) {
        // maybe it's better to set hp = 0 if hp < 0
        this.hp -= damage;
    }

    @Override
    public String toString() {
        return super.toString() + ", hp=" + hp + ", speed=" + speed;
    }

}
