package com.Scandel.rain.graphics;

import com.Scandel.rain.entity.mob.Player;

public class Ui {
    
    private int playerHealth;
    Player player;

    public Ui(Player player) {
        this.player = player;
        playerHealth = player.getHealth(); 
        
    }

    public void update() {
        playerHealth = player.getHealth();
    }

    public void render(Screen screen) {
        screen.drawHealth(0, 0, playerHealth);
    }

}
