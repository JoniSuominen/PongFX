/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong.game;

import java.util.ArrayList;
import java.util.List;
import pong.GameObjects.AiPaddle;
import pong.GameObjects.Ball;
import pong.utils.Type;
import pong.GameObjects.Paddle;
import pong.GameObjects.HumanPaddle;
import pong.utils.ObjectProperties;

/**
 *
 * @author Joni
 */
public class Game {

    private int areaHeight;
    private int areaWidth;
    private AiPaddle leftPaddle;
    private HumanPaddle rightPaddle;
    private List<Paddle> paddles;
    private int scoreP1;
    private int scoreP2;
    private Ball ball;
    private boolean started;

    public Game(int areaHeight, int areaWidth) {
        this.areaHeight = areaHeight;
        this.areaWidth = areaWidth;
        this.paddles = new ArrayList<>();
        initializeObjects();
        this.started = false;

    }

    public void update() {
        if (!started) {
            return;
        }

        if (checkGameWon()) {
            initializeObjects();
            this.setStarted(false);
        }
        this.rightPaddle.move();
        leftPaddle.move();

        checkRoundWin();

        if (ballIntersectsWall()) {
            ball.bounce();
        }
        

        for (Paddle p : paddles) {
            if (ballTouchesPaddle(p)) {
                ball.paddleBounce(p);
            }
        }
        
        ball.move(leftPaddle, rightPaddle);

    }

    public boolean ballTouchesPaddle(Paddle paddle) {
        double ballY = this.ball.getLocation().getY();
        double ballX = this.ball.getLocation().getX();
        double paddleY = paddle.getLocation().getY();
        double paddleX = paddle.getLocation().getX();
        
        
        if (ballY + ObjectProperties.BALL_WIDTH.getValue() >= paddleY
                && ballY - ObjectProperties.BALL_WIDTH.getValue() <= paddleY + ObjectProperties.PADDLE_HEIGHT.getValue()) {
            if (Math.abs(paddleX - (ballX + ObjectProperties.BALL_WIDTH.getValue())) == 0
                    || Math.abs(paddleX - (ballX - ObjectProperties.BALL_WIDTH.getValue())) == 0) {
                return true;
            }

        }

        return false;
    }

    public boolean ballIntersectsWall() {

        double ballY = ball.getLocation().getY();

        if ((ballY + ObjectProperties.BALL_WIDTH.getValue()) >= areaHeight || (ballY) <= 0) {
            System.out.println(ballY);
            return true;
        }
        return false;
    }

    public void checkRoundWin() {
        if (ball.getLocation().getX() - ObjectProperties.BALL_WIDTH.getValue() <= 0) {
            this.scoreP2++;
            initializeObjects();
        } else if (ball.getLocation().getX() + ObjectProperties.BALL_WIDTH.getValue() >= this.areaWidth) {
            this.scoreP1++;
            initializeObjects();
        }
    }

    public void initializeObjects() {
        this.paddles.clear();
        this.ball = new Ball(areaWidth / 2, areaHeight / 2);
        this.leftPaddle = new AiPaddle(20, areaHeight / 3, Type.AI, ball);
        this.rightPaddle = new HumanPaddle(areaWidth - 40, areaHeight / 3, Type.HUMAN);
        this.paddles.add(leftPaddle);
        this.paddles.add(rightPaddle);
    }

    public boolean checkGameWon() {
        if (this.scoreP1 == 10 || this.scoreP2 == 10) {
            return true;
        }
        return false;
    }

    public int getScoreP1() {
        return scoreP1;
    }

    public void setScoreP1(int scoreP1) {
        this.scoreP1 = scoreP1;
    }

    public int getScoreP2() {
        return scoreP2;
    }

    public void setScoreP2(int scoreP2) {
        this.scoreP2 = scoreP2;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public boolean isStarted() {
        return this.started;
    }

    public Paddle getLeftPaddle() {
        return leftPaddle;
    }

    public Paddle getRightPaddle() {
        return rightPaddle;
    }

    public Ball getBall() {
        return ball;
    }

    public boolean checkBounds() {

        return true;
    }

}
