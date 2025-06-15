package com.Scandel.rain.entity.mob;
import com.Scandel.rain.graphics.Screen;
import com.Scandel.rain.graphics.Sprite;
import com.Scandel.rain.input.Keyboard;
import com.Scandel.rain.utilities.DamageHandler;


public class Player extends Mob {
    
    public Keyboard input;
    private Sprite sprite;
    private int anim;
    private boolean walking;
    private float speed = 2f;
    private int hitCooldown = 25;

    public Player(Keyboard input) {
        this.input = input;
    }

    public Player(int x, int y, Keyboard input) {
        this.x = x;
        this.y = y;
        this.input = input;
        health = 10;
    }

    public void update() {
        float xa = 0, ya = 0;
        if (hitCooldown > 0) hitCooldown--;
        if (anim < 7500) anim++;
        else anim = 0;

        // if (input.left && input.up) {
        //     xa -= Math.cos(45) * speed;
        //     ya -= Math.cos(45) * speed;
        // }
        // else if (input.right && input.up) {
        //     xa += Math.cos(45) * speed;
        //     ya -= Math.cos(45) * speed;
        // }
        // else if (input.left && input.down) {
        //     xa -= Math.cos(45) * speed;
        //     ya += Math.cos(45) * speed;
        // }
        // else if (input.right && input.down) {
        //     xa += Math.cos(45) * speed;
        //     ya += Math.cos(45) * speed;
        // }
        if (input.up) ya -= 1 * speed;
        if (input.down) ya += 1 * speed;
        if (input.left) xa -= 1 * speed;
        if (input.right) xa += 1 * speed;
        
        if (input.hit && hitCooldown <= 0) {
            DamageHandler.regularDamage(this, level);
            hitCooldown = 25;  
        } 

        if (xa != 0 || ya != 0 ) {
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

    public void reduceHealth(float damage) {
        health -= damage;
    }
}
