package com.Scandel.rain.utilities;

import java.util.Arrays;

import com.Scandel.rain.entity.Entity;
import com.Scandel.rain.level.Level;

public class DamageHandler {
    public static void regularDamage(Entity player, Level level) {
        for (int i = 0; i < level.npcs.length; i++) {
            if (level.npcs[i] == null) continue;
            double distance = Math.sqrt(Math.pow((player.x - level.npcs[i].posX),2) + Math.pow((player.y - level.npcs[i].posY),2));
            if (distance < 64) {
                level.npcs[i].reduceHealth(5);
                if (level.npcs[i].getHealth() <= 0) level.npcs[i] = null;
                System.out.println(Arrays.toString(level.npcs));
            }
        }
    }
}
