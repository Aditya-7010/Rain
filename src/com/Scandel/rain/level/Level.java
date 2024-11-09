package com.Scandel.rain.level;

import com.Scandel.rain.graphics.Screen;
import com.Scandel.rain.level.tile.Tile;

public class Level {
    protected int width, height;
    protected int[] tiles;

    public Level(int width, int height) {
        this.width = width;
        this.height = height;
        tiles = new int[width*height];
        generateLevel();
    }

    public Level(String path) {
        loadLevel(path);
    }

    // random generation of level
    protected void generateLevel() {

    }

    protected void loadLevel(String path) {

    }

    public void update() { // 60 ups

    }

    // private void time() { // daylight cycles
    // }

    public void render(int xScroll, int yScroll, Screen screen) { // tells which tiles are needed
        screen.setOffset(xScroll, yScroll);  // xSroll and yScroll are top left corner coords
        int x0 = xScroll >> 4; // converting from pixel to tile coords
        int x1 = (xScroll + screen.width + 16) >> 4;
        int y0 = yScroll >> 4;
        int y1 = (yScroll + screen.height + 16) >> 4;

        for (int y = y0; y < y1; y++) {
            for (int x = x0; x < x1; x++) {
                getTile(x, y).render(x, y, screen); // render tile on x,y porsition on screen in tile coords
            }
        }
    }

    public Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height ) {
            return Tile.voidTile;
        } 
        if (tiles[x + y * width] == 0xff22B14C) return Tile.grass;
        if (tiles[x + y * width] == 0xff7F7F7F) return Tile.cobbleTile;
        if (tiles[x + y * width] == 0xffFF7F27) return Tile.fence;
        if (tiles[x + y * width] == 0xffFFF200) return Tile.flower;
        if (tiles[x + y * width] == 0xff880015) return Tile.woodenFloor;
        if (tiles[x + y * width] == 0xff7092BE) return Tile.stoneBrick;
        
        return Tile.fence;
    } 
}
