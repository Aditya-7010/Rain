package com.Scandel.rain;
import javax.swing.JFrame;

import com.Scandel.rain.entity.mob.Player;
import com.Scandel.rain.graphics.Screen;
import com.Scandel.rain.input.Keyboard;
import com.Scandel.rain.level.Level;
import com.Scandel.rain.level.SpawnLevel;
import com.Scandel.rain.graphics.Ui;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;


public class Game extends Canvas implements Runnable {
    
    private static final long serialVersionUID = 1L;
    // Window gets bigger in size but actual height and width of frame remains the same
    // So game will appear bigger but less data will have to be processed
    public static int width = 500;
    public static int height = width / 16 * 9;
    public static int scale = 3;

    
    private Thread thread; // thread for the game 
    private boolean running = false;
    private JFrame frame;
    private Keyboard key;
    private Level level;
    private Player player;
    private Ui ui;

    private Screen screen; // object to process graphics
    
    private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);  //store frame in buffer
    private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData(); // get writable array of image data and store it as pixels

    public Game() {
        Dimension size = new Dimension(width*scale, height*scale); // scale up the window by 3 times
        setPreferredSize(size);
        screen = new Screen(width, height);  // create instance of screen size 300 * 168
        frame = new JFrame();
        key = new Keyboard();
        level = new SpawnLevel();
        player = new Player((level.getSpawnPoint()[0] << 4),(level.getSpawnPoint()[1] << 4),key);
        player.init(level);
        ui = new Ui(player);

        addKeyListener(key);
    }

    public synchronized void start() {
        running = true;
        thread = new Thread(this, "Display");  // start the game process by name display
        thread.start();
    }

    public synchronized void stop() {
        running = false;
        try{
            thread.join();
        } catch(InterruptedException a) {
            a.printStackTrace();
        }
    }


    public void run() {
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double ns = 1000000000.0 / 60.0;  // time taken to update once in nanoseconds
        double delta = 0;
        int frames = 0;
        int updates = 0;
        requestFocus();
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;  // calc if it is time to run update function 
            lastTime = now;
            while (delta >= 1) {
                update();
                updates++;
                delta--;
                render();
                frames++;
            }
            
            if (System.currentTimeMillis() - timer > 1000) { // do it once per second
                frame.setTitle("Rain" + "  " + "FPS: " + frames + "\tUpdates: " + updates);
                updates = 0;
                frames = 0;
                timer += 1000;
            }
        }
        stop();
    }

    public void update() {
        key.update();
        player.update();
        level.update(screen, player);
        ui.update();
    }


    public void render() {
        BufferStrategy bs = getBufferStrategy(); // to manipulate bs of canvas class which game is subclass of
        if (bs == null) {
            createBufferStrategy(3); // 1 image on screen 1 on buffer and 1 will be worked on in background
            return;
        }
        int xScroll = (int)player.x - screen.width/2;
        int yScroll = (int)player.y - screen.height/2;
        screen.clear();
        level.render(xScroll, yScroll, screen);
        player.render(xScroll, yScroll, screen);
        ui.render(screen);

        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = screen.pixels[i]; // retrieve pixels calculated by screen class
        }
        Graphics g = bs.getDrawGraphics(); // creates link between graphics and bs
        // everything to be displayed between upper line and g.dispose
        // g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight()); // getwidth return size of window and 0,0 is top left
        // System.out.println(getWidth() + " " + getHeight()); getwidth is 900aaaaaaaaa
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        g.dispose(); // release the system resources
        bs.show();  // display buffer to the screensssss
    }


    public static void main(String[] args) {
        Game game = new Game();
        game.frame.setResizable(false); 
        game.frame.setTitle("RAIN");
        game.frame.add(game);
        game.frame.pack();
        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.frame.setLocationRelativeTo(null); // postition the window to center
        game.frame.setVisible(true); 

        game.start();
    }
}