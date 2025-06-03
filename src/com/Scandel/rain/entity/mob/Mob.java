package com.Scandel.rain.entity.mob;

import com.Scandel.rain.entity.Entity;
import com.Scandel.rain.graphics.Sprite;
import com.Scandel.rain.level.Level;


public abstract class Mob extends Entity{
    
    protected Sprite sprite;
    protected int dir = 0; //direction 0 -n, 1 -e, 2 -s, 3 -w
    protected boolean moving = false;
    protected boolean cutScene = false;
    protected Level level;
    protected int health = 10;

    public void move(int xa, int ya) {
        if (cutScene) return;
        int xnp = xa + x, ynp = ya + y;
        if (xa > 0) {
            dir = 1;
            xnp += 9;
        }    
        if (xa < 0) {
           dir = 3; 
           xnp -= 9;
        } 
        if (ya > 0) {
           dir = 2;  
           ynp += 14;
        } 
        if (ya < 0) {
            dir = 0;
        } 
        
        if (!collision(xnp, ynp)) {
            x += xa;  // in pixels
            y += ya;
        }
    }

    public void update() {
    }

    protected boolean collision(int x, int y) {
        return level.getTile(x >> 4, y >> 4).solid();
    }

    public void render() {
        
    }

    public void init(Level level) {
        this.level = level;
    }
    
    public void reduceHealth(int damage) {
        health -= damage;
    }

    public int getHealth() {return health;}

    public void strike() {
        
    }

}
