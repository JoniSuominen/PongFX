/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong.GameObjects;

import java.util.Random;
import pong.utils.ObjectProperties;
import pong.utils.Type;

/**
 *
 * @author Joni
 */
public class AiPaddle extends Paddle {

    private Ball ball;
    private Random r = new Random();

    public AiPaddle(int x, int y, Type type, Ball ball) {
        super(x, y, type);
        this.ball = ball;
    }

    @Override
    public void move() {

        double y = 0;
        double i = r.nextInt(10) + 5;
        if (super.getLocation().getY() > ball.getLocation().getY()) {
            i *= -1;
            super.getLocation().setY(super.getLocation().getY() + i);

        } else if (super.getLocation().getY() + ObjectProperties.PADDLE_HEIGHT.getValue() < ball.getLocation().getY()) {
            super.getLocation().setY(super.getLocation().getY() + i);
        }

    }

    public int posPredict() {

        return 0;
    }

}
