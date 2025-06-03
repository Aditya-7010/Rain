package com.Scandel.rain.level;

public class SpawnLevel extends Level {

    private int[] spawnPoint = {2, 2};

    public SpawnLevel() {
        super("/textures/map.png");
    }

    public int[] getSpawnPoint() {
        return spawnPoint;
    }

}
