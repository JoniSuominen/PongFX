/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong.GameObjects;

import pong.utils.Location;
import pong.utils.ObjectProperties;

/**
 *
 * @author Joni
 */
public class Ball {

    private Location location;
    private double movX;
    private double movY;

    public Ball(int x, int y) {
        this.location = new Location(x, y);
        movX = 4;
        movY = 0;
    }

    public Location getLocation() {
        return location;
    }

    public void setLoc(Location loc) {
        this.location = loc;
    }

    public double getMovX() {
        return movX;
    }

    public void setMovX(double movX) {
        if (movX < 0.6) {
            this.movX = movX;
        }
    }

    public double getMovY() {
        return movY;
    }

    public void setMovY(double movY) {
        this.movY = movY;
    }

    public void move(Paddle p1, Paddle p2) {
        if (this.getLocation().getX() + movX - ObjectProperties.BALL_WIDTH.getValue() < p1.getLocation().getX() && this.location.getY() >= p1.getLocation().getY()
                && this.location.getY() <= p1.getLocation().getY() + ObjectProperties.PADDLE_HEIGHT.getValue()) {
            this.getLocation().setX(p1.getLocation().getX() + ObjectProperties.BALL_WIDTH.getValue());

        } else if (this.getLocation().getX() + movX + ObjectProperties.BALL_WIDTH.getValue() > p2.getLocation().getX()
                && this.location.getY() >= p2.getLocation().getY() && this.location.getY() <= p2.getLocation().getY() + ObjectProperties.PADDLE_HEIGHT.getValue()) {
            this.getLocation().setX(p2.getLocation().getX() - ObjectProperties.BALL_WIDTH.getValue());
        } else {
            this.location.setX(location.getX() + movX);
        }
        this.location.setY(location.getY() + movY);
    }

    public void bounce() {
        this.movY = -movY;
    }

    public void paddleBounce(Paddle p) {
        double paddleX = p.getLocation().getX();
        double paddleY = p.getLocation().getY();

        double collisionLocation = this.getLocation().getY() - paddleY;

        if (collisionLocation <= 40) {
            this.setMovX(this.getMovX() * 1.1);
            this.setMovY(-5);
        } else if (collisionLocation > 40 && collisionLocation <= 80) {
            this.setMovY(-2.5);

        } else if (collisionLocation > 80 && collisionLocation <= 120) {
            this.setMovY(1);
        } else if (collisionLocation > 120 && collisionLocation <= 160) {
            this.setMovY(2.5);
        } else if (collisionLocation > 160 && collisionLocation <= 200) {
            this.setMovY(5);
        } else {
            this.setMovX(this.getMovX() * 1.1);
            this.setMovY(+4);
        }
        this.movX = -movX;

    }

}
