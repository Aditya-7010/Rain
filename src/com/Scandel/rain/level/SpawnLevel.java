package com.Scandel.rain.level;

import com.Scandel.rain.entity.mob.Npc;
import com.Scandel.rain.entity.mob.Villager;

public class SpawnLevel extends Level {

    protected int[] spawnPoint = {2, 2};
    protected Npc[] npcs;
    protected Npc[] visibleNpcs;

    public SpawnLevel() {
        super("/textures/map.png", 3);
    }

    public int[] getSpawnPoint() {
        return spawnPoint;
    }

    public boolean spawnNpcs() {
        super.npcs[0] = new Villager((2 << 4), (2 << 4));
        super.npcs[1] = new Villager((25 << 4), (19 << 4));
        super.npcs[2] = new Villager((24 << 4), (51 << 4));
        for (Npc npc : super.npcs) {
            npc.init(this);
        }
        return true;
    }

}
