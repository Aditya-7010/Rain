package com.Scandel.rain.entity.mob;

import java.util.Random;

import com.Scandel.rain.graphics.Screen;
import com.Scandel.rain.graphics.Sprite;

public class Npc extends Mob{

    public int yc = 0, xc = 0, sx, sy; // pixels
    public Sprite sprite = Sprite.player_forward;
    private boolean walking = true;
    private Random random = new Random();
    private int counter = 120, anim = 0;

    public Npc(int sx, int sy) {
        this.sx = sx; 
        this.sy = sy;
    }

    public String getDialougue(String[] dialogues) {
        int index = random.nextInt(4);
        return dialogues[index];
    }

    public void render(Screen screen) {
        if (dir == 0) {
            sprite = Sprite.npc_forward;
            if (walking) {
                if (anim % 20 > 10) sprite = Sprite.npc_forward_1;
                else sprite = Sprite.npc_forward_2;
            }
        }  
        if (dir == 1) {
            sprite = Sprite.npc_right;
            if (walking) {
                if (anim % 20 > 10) sprite = Sprite.npc_right_1;
                else sprite = Sprite.npc_right_2;
            }
        } 
        if (dir == 2) {
            sprite = Sprite.npc_backward;
            if (walking) {
                if (anim % 20 > 10) sprite = Sprite.npc_backward_1;
                else sprite = Sprite.npc_backward_2;
            }
        } 
        if (dir == 3) {
            sprite = Sprite.npc_left;
            if (walking) {
                if (anim % 20 > 10) sprite = Sprite.npc_left_1;
                else sprite = Sprite.npc_left_2;
            }
        }  
        screen.renderNpc(sx , sy , this);
    }

    public void update(Screen screen, Player player) {  // check if npc appears on screen and only then update it
        if (anim < 7500) anim++;
        else anim = 0;
        int xa = sx - screen.xOffset;
        int ya = sy - screen.yOffset;
        if (xa < -this.sprite.SIZE - 64 || xa >= screen.width + 64|| ya < -64 || ya >= screen.height + 64) return;

        if (yc == 0 && xc == 0) {
            xc = random.nextInt(-64,64);
            yc = random.nextInt(-64,64);
        }
        
        // if (checkForPlayer(player)) {
        //     playerInteraction = true;
        // }
        checkforPlayer(player, screen);
        if (player.cutScene && player.input.skip) {
            walking = true;
            player.cutScene = false;
        } 

        if (counter % 2 == 0)
            move();
        
        counter--;
    } 

    public void move() {      
        if (!walking) return;
        if (yc != 0) {
            int ym = (Math.abs(yc)/yc);
            boolean a = collision(sx, sy + (ym << 4));
            if (a) {
                yc -= ym * 2; 
            }
            if (!a && yc != 0) {
                sy += ym;
                yc -= ym;    
            }
            if (ym < 0) dir = 0;
            else dir = 2;
            changeSprite();
            return; 
        }
    
        if (xc != 0) {
            int xm = (Math.abs(xc)/xc);
            boolean b = collision(sx + (xm << 4), sy);
            if (b) {
                xc -= xm * 2;
            }
            if (!b && xc != 0) {
                sx += xm;
                xc -= xm;
            }
            if (xm > 0) dir = 1;
            else dir = 3;
            changeSprite();
        }

    }
   

    private void interactWithPlayer(Player player) {
        player.cutScene = true;
        int dx = sx - player.x;
        int dy = sy - player.y;

        if (Math.abs(dx) > Math.abs(dy)) {  
            if (dx > 0) dir = 3;        
            else dir = 1;              
        }
         else {                           
            if (dy > 0) dir = 0;         
            else dir = 2;                
        }
        walking = false;
        changeSprite();
        
    }

    private void changeSprite() {
        if (dir == 0) sprite = Sprite.npc_forward;
        if (dir == 1) sprite = Sprite.npc_right;
        if (dir == 2) sprite = Sprite.npc_backward;
        if (dir == 3) sprite = Sprite.npc_left;
    }

    private void checkforPlayer(Player player, Screen screen) {
        if ((player.x > (sx - 64)) && (player.x < (sx + 64)) && ((player.y < sy + 64) && (player.y > sy - 64))) {
            
            if (player.input.interact)
                interactWithPlayer(player);
        }
    }



}
