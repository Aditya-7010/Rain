package com.Scandel.rain.level.tile;

import com.Scandel.rain.graphics.Screen;
import com.Scandel.rain.graphics.Sprite;

public class Fence extends Tile {

    public Fence(Sprite sprite) {
        super(sprite);
    }

    public void render (int x, int y, Screen screen) {
        screen.renderTile(x << 4, y << 4, this);
    }

    
    public boolean solid() {
        return true;
    }
    
}
