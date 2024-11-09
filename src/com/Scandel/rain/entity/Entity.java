package com.Scandel.rain.entity;

import com.Scandel.rain.graphics.Screen;
import com.Scandel.rain.level.Level;
import java.util.Random;

public abstract class Entity {

    public int x , y;
    private boolean removed = false; // removed from the level
    protected Level level;
    protected final Random random = new Random(); 

    public void update() { // 60 ups

    }

    public void render (Screen screen) {

    }

    public void remove() {
        removed = true; // to be updated in the future
    }

    public boolean isRemoved() {
        return removed;
    }
    
}
