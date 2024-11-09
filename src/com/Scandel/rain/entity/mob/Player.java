package com.Scandel.rain.entity.mob;

import com.Scandel.rain.graphics.Screen;
import com.Scandel.rain.graphics.Sprite;
import com.Scandel.rain.input.Keyboard;


public class Player extends Mob {
    
    public Keyboard input;
    private Sprite sprite;
    private int anim;
    private boolean walking;

    public Player(Keyboard input) {
        this.input = input;
    }

    public Player(int x, int y, Keyboard input) {
        this.x = x;
        this.y = y;
        this.input = input;
    }

    public void update() {
        int xa = 0, ya = 0;
        if (anim < 7500) anim++;
        else anim = 0;
        if (input.left && input.up) {
            
        }
        if (input.right && input.up) xa++;
        if (input.left && input.down) xa--;
        if (input.right && input.down) xa++;
        if (input.up) ya--;
        if (input.down) ya++;
        if (input.left) xa--;
        if (input.right) xa++;

        if (xa != 0 || ya != 0 && (!cutScene)) {
            move(xa, ya);   
            walking = true;
        }
        else {
            walking = false;
        }
    }

    public void render(int xScroll, int yScroll, Screen screen) {
        if (dir == 0) {
            sprite = Sprite.player_forward;
            if (walking) {
                if (anim % 20 > 10) sprite = Sprite.player_forward_1;
                else sprite = Sprite.player_forward_2;
            }
        }  
        if (dir == 1) {
            sprite = Sprite.player_right;
            if (walking) {
                if (anim % 20 > 10) sprite = Sprite.player_right_1;
                else sprite = Sprite.player_right_2;
            }
        } 
        if (dir == 2) {
            sprite = Sprite.player_backward;
            if (walking) {
                if (anim % 20 > 10) sprite = Sprite.player_backward_1;
                else sprite = Sprite.player_backward_2;
            }
        } 
        if (dir == 3) {
            sprite = Sprite.player_left;
            if (walking) {
                if (anim % 20 > 10) sprite = Sprite.player_left_1;
                else sprite = Sprite.player_left_2;
            }
        }  
        screen.renderPlayer(x-16,y-16, sprite);
    }

    
}
