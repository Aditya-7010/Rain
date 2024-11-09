package com.Scandel.rain.level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpawnLevel extends Level {

    public SpawnLevel(String path) {
        super(path);
    }

    protected void loadLevel(String path) {
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

      
}
