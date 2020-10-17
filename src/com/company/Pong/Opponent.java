package com.company.Pong;

import java.awt.*;

public class Opponent {

    public double x,y;
    public int OPPONENT_HEIGHT, OPPONENT_WIDTH, ERROR;
    private final double speed;
    public Rectangle boundsOpponent;

    public Opponent(int initialX, int initialY) {
        this.x = initialX;
        this.y = initialY;
        this.OPPONENT_WIDTH = 40;
        this.OPPONENT_HEIGHT = 8;
        this.ERROR = 8;
        this.speed = 0.03;
    }

    public void render(Graphics g){
        g.setColor(new Color(71, 15, 124));
        g.fillRect((int)x, (int)y, OPPONENT_WIDTH, OPPONENT_HEIGHT);
    }

    public void tick(){
        boundsOpponent = new Rectangle((int)x, (int)y, OPPONENT_WIDTH,OPPONENT_HEIGHT);

        x+= (Game.ball.x - x - ERROR)*speed;

        if (x+ OPPONENT_WIDTH + 2 > Game.WIDTH){
            x = Game.WIDTH - OPPONENT_WIDTH - 2;
        }
        if (x <2){
            x = 2;
        }
    }


}
