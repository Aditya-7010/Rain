package com.Scandel.rain.graphics;

import java.util.Random;

import com.Scandel.rain.entity.mob.Npc;
import com.Scandel.rain.level.tile.Tile;

public class Screen {

    public static int width; // same as height and width of a frame or screen
    public int height;
    public final int MAP_SIZE = 64;
    public final int MAP_SIZE_MASK = MAP_SIZE - 1;

    public static int xOffset;
    public static int yOffset;

    public static int[] pixels;  // stores final data to be displayed on the screen
    public int[] tiles = new int[MAP_SIZE * MAP_SIZE];  // stores texture of each tile 

    private Random random = new Random();

    public Screen(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new int[width * height];

        for (int i = 0; i < MAP_SIZE * MAP_SIZE; i++) {
            tiles[i] = random.nextInt(0xffffff);  // set each tile color as random
        }
    }

    public void clear() {
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = 0; //sets all pixels to black
        }
    }

    public void renderTile(int xp, int yp, Tile tile) {
        yp -= yOffset; // offset due to player movement (top left corner probably)
        xp -= xOffset; // in pixels coords
        for (int y = 0; y < tile.sprite.SIZE; y++) {
            int ya = yp + y;
            for (int x = 0; x < tile.sprite.SIZE; x++) {
                int xa = xp + x;
                if (xa < -tile.sprite.SIZE || xa >= width || ya < 0 || ya >= height) break;
                if (xa < 0) xa = 0;
                int col = tile.sprite.pixels[x + y * tile.sprite.SIZE];
                if (col != 0xffed1c24) {
                    pixels[xa + ya * width] = col;  // xa + ya * width is where tile is printed
                }
            }
        }
    }

    public void renderPlayer(float xp, float yp, Sprite sprite) {
        yp -= yOffset; // offset due to player movement
        xp -= xOffset; 
        for (int y = 0; y < sprite.SIZE; y++) {
            float ya = yp + y;
            for (int x = 0; x < sprite.SIZE; x++) {
                float xa = xp + x;
                if (xa <  -sprite.SIZE || xa >= width || ya < 0 || ya >= height) break;
                if (xa < 0) xa = 0;
                int col = sprite.pixels[x+y*sprite.SIZE];
                if (col != 0xffed1c24)
                    pixels[(int)xa + (int)ya * width] = col;
            }
        }
    }

    public void setOffset(int xOffset, int yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public void renderNpc(int sx, int sy, Npc npc) {
        sy -= yOffset; // offset due to player movement (top left corner probably)
        sx -= xOffset; // in pixels coords
        for (int y = 0; y < npc.sprite.SIZE; y++) {
            int ya = sy + y;
            for (int x = 0; x < npc.sprite.SIZE; x++) {
                int xa = sx + x;
                if (xa < -npc.sprite.SIZE || xa >= width || ya < 0 || ya >= height) break;
                if (xa < 0) xa = 0;
                int col = npc.sprite.pixels[x + y * npc.sprite.SIZE];
                if (col != 0xffed1c24) {
                    pixels[xa + ya * width] = col;  // xa + ya * width is where tile is printed
                }
            }
        }
    }

    public void drawHealth(int yp, int xp, int health) { // in pixels coords
        int i = 0;
        for (; i < health; i++) {
            for (int y = 0; y < Sprite.heart.SIZE; y++) {
                int ya = yp + y;
                for (int x = 0; x < Sprite.heart.SIZE; x++) {
                    int xa = xp + x + (i * 9);
                    int col = Sprite.heart.pixels[x + y * Sprite.heart.SIZE];
                    if (col != 0xffed1c24) pixels[xa + ya * width] = col;
                }
            }
        }
        for (; i < 10; i++) {
            for (int y = 0; y < Sprite.emptyHeart.SIZE; y++) {
                int ya = yp + y;
                for (int x = 0; x < Sprite.emptyHeart.SIZE; x++) {
                    int xa = xp + x + (i * 9);
                    int col =  Sprite.emptyHeart.pixels[x + y * Sprite.emptyHeart.SIZE];
                    if (col != 0xffed1c24) pixels[xa + ya * width] = col;
                }
            }
        }
    }

    public static void renderNpcHealth(int sx, int sy, float health) {
    
    }

}
