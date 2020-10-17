package com.company.Pong;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player implements KeyListener {

    public boolean right, left;

    public int PLAYER_HEIGHT, PLAYER_WIDTH, x, y;
    public Rectangle boundsPlayer;


    public Player(int initialX, int initialY) {
        this.x = initialX;
        this.y = initialY;
        this.PLAYER_WIDTH = 40;
        this.PLAYER_HEIGHT = 8;
    }

    public void render(Graphics g){
        g.setColor(Color.magenta);
        g.fillRect(x, y, PLAYER_WIDTH, PLAYER_HEIGHT);
    }

    public void tick(){
        boundsPlayer = new Rectangle(x, y, PLAYER_WIDTH, PLAYER_HEIGHT);
        if (right){
            x++;
        }
        if (left){
            x--;
        }
        if (x+PLAYER_WIDTH + 2 > Game.WIDTH){
            x = Game.WIDTH - PLAYER_WIDTH - 2;
        }
        if (x <2){
            x = 2;
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println(e.getKeyCode());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT ){
            Game.player.right = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT ){
            Game.player.left = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT ){
            Game.player.right = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT ){
            Game.player.left = false;
        }
    }
}
