/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong.GameObjects;

import java.util.Arrays;
import java.util.stream.Stream;
import pong.utils.Direction;
import pong.utils.ObjectProperties;
import pong.utils.Type;

/**
 *
 * @author Joni
 */
public class HumanPaddle extends Paddle {

    private boolean upMov, downMov;
    private double velocity;

    public HumanPaddle(int x, int y, Type type) {
        super(x, y, type);
        this.upMov = false;
        this.downMov = false;
    }

    @Override
    public void move() {
        if (upMov) {
            velocity -= 2;
        } else if (downMov) {
            velocity += 2;
        } else if (!upMov && !downMov) {
            velocity = 0;
        }

        if (velocity > 10) {
            velocity = 10;
        } else if (velocity < -10) {
            velocity = -10;
        }
        this.getLocation().setY(this.getLocation().getY() + velocity);

        if (this.getLocation().getY() + ObjectProperties.PADDLE_HEIGHT.getValue() > ObjectProperties.GAME_HEIGHT.getValue()) {
            this.getLocation().setY(ObjectProperties.GAME_HEIGHT.getValue() - ObjectProperties.PADDLE_HEIGHT.getValue());
        }
        if (this.getLocation().getY() < 0) {
            this.getLocation().setY(0);
        }

    }

    public void setUpMov(boolean upMov) {
        this.upMov = upMov;
    }

    public void setDownMov(boolean downMov) {
        this.downMov = downMov;
    }

    public double getVelocity() {
        return velocity;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

}
