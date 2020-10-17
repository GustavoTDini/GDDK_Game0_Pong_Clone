package com.company.Pong;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game extends Canvas implements Runnable {

    private static final long serialVersionUID = 1L;
    public static JFrame frame;
    private Thread thread;

    public boolean isRunning = true;
    public static final int WIDTH = 160;
    public static final int HEIGHT = 120;
    private final int SCALE = 8;

    private final BufferedImage layer = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);

    public static Player player;
    public static Opponent opponent;
    public static Ball ball;

    public static int myPoints, opponentPoints;

    public Game(){
        setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
        player = new Player(60, HEIGHT - 10);
        addKeyListener(player);
        opponent = new Opponent(60, 2);
        ball = new Ball(WIDTH/2 - 5, HEIGHT/2 - 5);
        myPoints = 0;
        opponentPoints = 0;
    }

    public void initFrame(){
        frame = new JFrame("Game #1 Pong");
        frame.add(this);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setFocusable(true);
        frame.setVisible(true);
        frame.toFront();
        frame.setAlwaysOnTop(true);
        frame.setAlwaysOnTop(false);
        frame.requestFocus();
    }

    public synchronized  void start(){
        thread = new Thread(this);
        initFrame();
        isRunning = true;
        thread.start();
    }

    public synchronized void stop(){
        isRunning=false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void tick(){
        player.tick();
        opponent.tick();
        ball.tick();
    }

    public void render(){
        BufferStrategy bs = getBufferStrategy();
        if(bs == null){
            createBufferStrategy(3);
            return;
        }

        Graphics g = layer.getGraphics();
        g.setColor(Color.black);
        g.fillRect(0,0,WIDTH, HEIGHT);
        g.setColor(Color.white);
        g.drawLine(0,HEIGHT/2, WIDTH, HEIGHT/2);
        g.setFont(new Font("Arial", Font.PLAIN, 16));
        g.drawString(String.valueOf(myPoints), 4, HEIGHT/2 + 17);
        g.drawString(String.valueOf(opponentPoints), 4 , HEIGHT/2 - 4);
        player.render(g);
        opponent.render(g);
        ball.render(g);

        g = bs.getDrawGraphics();
        g.drawImage(layer,0,0,WIDTH*SCALE,HEIGHT*SCALE,null);

        bs.show();

    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        int frames = 0;
        double timer = System.currentTimeMillis();
        requestFocus();
        while (isRunning){
            long now = System.nanoTime();
            delta+= (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1){
                tick();
                render();
                frames++;
                delta--;
            }

            if (System.currentTimeMillis() - timer >= 1000){
                System.out.println(("FPS: " + frames));
                frames = 0;
                timer += 1000;
            }

        }
        stop();
    }
}