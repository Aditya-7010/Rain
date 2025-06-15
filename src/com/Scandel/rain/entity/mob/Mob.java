package com.Scandel.rain.entity.mob;

import com.Scandel.rain.entity.Entity;
import com.Scandel.rain.graphics.Sprite;
import com.Scandel.rain.level.Level;


public abstract class Mob extends Entity{
    
    protected Sprite sprite;
    protected int health;
    protected int dir = 0; //direction 0 -n, 1 -e, 2 -s, 3 -w
    

    public void move(float xa, float ya) {
        float xnp = xa + x, ynp = ya + y;
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
        
        if (!collision((int)xnp,(int) ynp)) {
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
    
    public abstract void reduceHealth(float damage);

    public int getHealth() {return health;}

    public void strike() {
        
    }

}
