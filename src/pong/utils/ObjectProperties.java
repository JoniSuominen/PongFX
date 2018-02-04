/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong.utils;

/**
 *
 * @author Joni
 */
public enum ObjectProperties {

    PADDLE_HEIGHT(200),
    PADDLE_WIDTH(20),
    BALL_WIDTH(20),
    BALL_RAY(10),
    GAME_WIDTH(600),
    GAME_HEIGHT(600);

    private double value;

    private ObjectProperties(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

}
