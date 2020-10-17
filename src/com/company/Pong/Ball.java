package com.company.Pong;

import java.awt.*;
import java.util.Random;

public class Ball {

    public double x,y;
    private final int BALL_SIZE;
    private final double SPEED;
    public Rectangle boundsBall;

    public double dx, dy;


    public Ball(int initialX, int initialY) {
        this.x = initialX;
        this.y = initialY;
        this.BALL_SIZE = 10;
        this.SPEED = 1.8;
        setAngle(true);

    }

    private void setAngle(boolean myDirection){
        int angle = new Random().nextInt(120-45) + 45 +1;
        dx = Math.cos(Math.toRadians(angle));
        dy = Math.sin(Math.toRadians(angle));
        if (myDirection){
            dy *=-1;
        }
    }

    public void render(Graphics g){
        g.setColor(Color.yellow);
        g.fillOval((int)x, (int)y, BALL_SIZE, BALL_SIZE);
    }

    public void tick(){
        boundsBall = new Rectangle((int)(x+(dx*SPEED)), (int)(y+(dy*SPEED)), BALL_SIZE,BALL_SIZE);
        if (x+(dx*SPEED) + BALL_SIZE >= Game.WIDTH){
            dx*=-1;
        } else if (x+(dx*SPEED) < 0 ){
            dx*=-1;
        }

        if (y> Game.HEIGHT){
            System.out.println("Ponto do inimigo");
            x = Game.WIDTH/2 - 5;
            y = Game.HEIGHT/2 - 5;
            Game.opponentPoints = Game.opponentPoints + 1;
            setAngle(true);
        } else if(y<0){
            System.out.println("Nosso Ponto!!");
            x = Game.WIDTH/2 - 5;
            y = Game.HEIGHT/2 - 5;
            Game.myPoints = Game.myPoints + 1;
            setAngle(false);
        }

        if (boundsBall.intersects(Game.player.boundsPlayer)){
            setAngle(false);
            if (dy> 0) dy*=-1;
        } else if (boundsBall.intersects(Game.opponent.boundsOpponent)){
            setAngle(true);
            if (dy< 0) dy*=-1;
        }

        x+=dx*SPEED;
        y+=dy*SPEED;
    }

}
