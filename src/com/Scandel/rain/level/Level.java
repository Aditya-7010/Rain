package com.Scandel.rain.level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.Scandel.rain.entity.mob.Npc;
import com.Scandel.rain.entity.mob.Player;
import com.Scandel.rain.entity.mob.Villager;
import com.Scandel.rain.graphics.Screen;
import com.Scandel.rain.level.tile.Tile;

public abstract class Level {
    protected int width, height;
    protected int[] tiles;
    protected int[] spawnPoint;
    public Npc[] npcs;

    public Level(String path, int npcCount) {
        loadLevel(path);
        npcs = new Villager[npcCount];
        spawnNpcs();
    }

    public abstract int[] getSpawnPoint();

    public abstract boolean spawnNpcs();

    private void loadLevel(String path) {
        try {
            BufferedImage image = ImageIO.read(SpawnLevel.class.getResource(path));
            width = image.getWidth(); // tile coords
            height = image.getHeight();
            tiles = new int[width * height];
            image.getRGB(0,0,width,height,tiles,0,width);
        } catch (IOException e) {
            System.out.println("File was not loaded");
        }
    }

    public void update(Screen screen, Player player) { // 60 ups
        for (Npc npc : npcs) {
            if (npc == null) continue;
            if (npc.isOnScreen(screen))
                npc.update(screen, player);
        }
        
    }


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
        for (Npc npc : npcs) {
            if (npc == null) continue;
            if (npc.isOnScreen(screen))
                npc.render(screen);
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
