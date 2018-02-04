/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong.game;

import pong.game.Game;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import pong.GameObjects.HumanPaddle;
import pong.utils.Direction;
import pong.utils.ObjectProperties;

/**
 *
 * @author Joni
 */
public class Pong extends Application {

    @Override
    public void start(Stage primaryStage) {
        int squares = 600;
        int squareSize = 20;

        BorderPane layout = new BorderPane();
        Scene scene = new Scene(layout);

        Canvas canvas = new Canvas(squares, squares);

        GraphicsContext gc = canvas.getGraphicsContext2D();
        Game g = new Game(squares, squares);

        scene.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.DOWN)) {
                HumanPaddle paddle = (HumanPaddle) g.getRightPaddle();
                paddle.setDownMov(true);
            } else if (event.getCode().equals(KeyCode.UP)) {
                HumanPaddle paddle = (HumanPaddle) g.getRightPaddle();
                paddle.setUpMov(true);
            }

            if (event.getCode().equals(KeyCode.SPACE)) {
                if (g.isStarted()) {
                    g.setStarted(false);
                } else {
                    g.setStarted(true);
                }
            }
        });

        scene.setOnKeyReleased((event) -> {
            HumanPaddle paddle = (HumanPaddle) g.getRightPaddle();
            if (event.getCode().equals(KeyCode.DOWN)) {

                paddle.setDownMov(false);
            } else if (event.getCode().equals(KeyCode.UP)) {
                paddle.setUpMov(false);
            }
        });

        /**
         * draw the objects
         */
        new AnimationTimer() {
            long before = 0;

            @Override
            public void handle(long now) {
                if (now - before < 100000000 / 60) {
                    return;
                }

                g.update();
            }

        }.start();

        new AnimationTimer() {
            long before = 0;

            @Override
            public void handle(long now) {
                if (now - before < 100000000 / 60) {
                    return;
                }

                gc.setFill(Color.BLACK);
                gc.fillRect(0, 0, 600, 600);

                gc.setFill(Color.LIGHTGREY);
                gc.fillRect(g.getLeftPaddle().getLocation().getX(), g.getLeftPaddle().getLocation().getY(), ObjectProperties.PADDLE_WIDTH.getValue(), ObjectProperties.PADDLE_HEIGHT.getValue());
                gc.fillRect(g.getRightPaddle().getLocation().getX(), g.getRightPaddle().getLocation().getY(), ObjectProperties.PADDLE_WIDTH.getValue(), ObjectProperties.PADDLE_HEIGHT.getValue());

                gc.setFill(Color.LIGHTBLUE);
                gc.fillOval(g.getBall().getLocation().getX(), g.getBall().getLocation().getY(), ObjectProperties.BALL_WIDTH.getValue(), ObjectProperties.BALL_WIDTH.getValue());

                gc.setFill(Color.LIGHTGREY);
                gc.setFont(new Font("Arial", 60));
                gc.fillText(g.getScoreP1() + " ", 100, 100);
                gc.fillText(g.getScoreP2() + " ", 440, 100);
            }

        }.start();

        layout.setCenter(canvas);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
