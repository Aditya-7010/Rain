package com.Scandel.rain.level.tile;

import com.Scandel.rain.graphics.Screen;
import com.Scandel.rain.graphics.Sprite;

// gonna make it abstract in future

public class Tile {
    public int x, y;
    public Sprite sprite;    

    public static Tile grass = new GrassTile(Sprite.grass);
    public static Tile cobbleTile = new CobbleStone(Sprite.cobbleStone);
    public static Tile voidTile = new VoidTile(Sprite.voidSprite);
    public static Tile fence = new Fence(Sprite.fence);
    public static Tile flower = new Flower(Sprite.flower);
    public static Tile woodenFloor = new WoodenFloor(Sprite.woodenFloor);
    public static Tile stoneBrick = new StoneBrick(Sprite.stoneBrick);


    public Tile(Sprite sprite) {
        this.sprite = sprite;
    }

    public void render(int x, int y, Screen screen) {
        
    }

    public boolean solid() {
        return false;
    }
}
