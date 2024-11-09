package com.Scandel.rain.graphics;

public class Sprite {

    public final int SIZE; // 16 pixels as for now
    private int x, y; // where the sprite is in sheet
    public int[] pixels; // SAME AS AREA OF OUR SPRITE
    private SpriteSheet sheet;

    public static Sprite grass = new Sprite(16,0,0, SpriteSheet.spriteSheet);
    public static Sprite voidSprite = new Sprite(16, 0x000000);
    public static Sprite cobbleStone = new Sprite(16, 1,0, SpriteSheet.spriteSheet);
    public static Sprite flower = new Sprite(16, 3,0, SpriteSheet.spriteSheet);
    public static Sprite fence = new Sprite(16, 2,0, SpriteSheet.spriteSheet);
    public static Sprite woodenFloor = new Sprite(16, 4, 0, SpriteSheet.spriteSheet);
    public static Sprite stoneBrick = new Sprite(16, 5, 0, SpriteSheet.spriteSheet);


    // hearts need to be updated
    public static Sprite heart = new Sprite(16, 6, 0, SpriteSheet.spriteSheet);
    public static Sprite emptyHeart = new Sprite(16, 7, 0, SpriteSheet.spriteSheet);
    public static Sprite npcHealth = new Sprite(32, 6, 1, SpriteSheet.spriteSheet);



    public static Sprite player_forward = new Sprite(32, 6,3,SpriteSheet.spriteSheet);
    public static Sprite player_forward_1 = new Sprite(32, 5,3,SpriteSheet.spriteSheet);
    public static Sprite player_forward_2 = new Sprite(32, 7,3,SpriteSheet.spriteSheet);
    public static Sprite player_backward = new Sprite(32, 6,0,SpriteSheet.spriteSheet);
    public static Sprite player_backward_1 = new Sprite(32, 5,0,SpriteSheet.spriteSheet);
    public static Sprite player_backward_2 = new Sprite(32, 7,0,SpriteSheet.spriteSheet);
    public static Sprite player_left = new Sprite(32, 6,1,SpriteSheet.spriteSheet);
    public static Sprite player_left_1 = new Sprite(32, 5,1,SpriteSheet.spriteSheet);
    public static Sprite player_left_2 = new Sprite(32, 7,1,SpriteSheet.spriteSheet);
    public static Sprite player_right = new Sprite(32, 6,2,SpriteSheet.spriteSheet);
    public static Sprite player_right_1 = new Sprite(32, 5,2,SpriteSheet.spriteSheet);
    public static Sprite player_right_2 = new Sprite(32, 7,2,SpriteSheet.spriteSheet);

    public static Sprite npc_forward = new Sprite(32, 6,7,SpriteSheet.spriteSheet);
    public static Sprite npc_forward_1 = new Sprite(32, 5,7,SpriteSheet.spriteSheet);
    public static Sprite npc_forward_2 = new Sprite(32, 7,7,SpriteSheet.spriteSheet);
    public static Sprite npc_backward = new Sprite(32, 6,4,SpriteSheet.spriteSheet);
    public static Sprite npc_backward_1 = new Sprite(32, 5,4,SpriteSheet.spriteSheet);
    public static Sprite npc_backward_2 = new Sprite(32, 7,4,SpriteSheet.spriteSheet);
    public static Sprite npc_left = new Sprite(32, 6,5,SpriteSheet.spriteSheet);
    public static Sprite npc_left_1 = new Sprite(32, 5,5,SpriteSheet.spriteSheet);
    public static Sprite npc_left_2 = new Sprite(32, 7,5,SpriteSheet.spriteSheet);
    public static Sprite npc_right = new Sprite(32, 6,6,SpriteSheet.spriteSheet);
    public static Sprite npc_right_1 = new Sprite(32, 5,6,SpriteSheet.spriteSheet);
    public static Sprite npc_right_2 = new Sprite(32, 7,6,SpriteSheet.spriteSheet);


    public Sprite(int size, int color) {
        SIZE = size;
        pixels = new int[SIZE*SIZE];
        setColor(color);
    }

    public Sprite(int size, int x, int y, SpriteSheet sheet) {
        SIZE = size; // size of individual sprite
        this.x = x * size; 
        this.y = y * size;
        this.sheet = sheet;
        pixels = new int[SIZE * SIZE];
        load();
    }

    private void load() {
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                // x and y tells which pixel of sprite is needed and this.x and this.y are added to tell their location int the sheet
                pixels[x+y*SIZE] = sheet.pixels[(x+this.x) + (y+this.y) * sheet.SIZE];
            }
        }
    }

    private void setColor(int color) {
        for (int i = 0; i < SIZE * SIZE; i++) {
            pixels[i] = color;
        }
    }
    
}
