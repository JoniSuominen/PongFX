/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong.GameObjects;

import java.util.List;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import pong.utils.Location;
import pong.utils.Type;

/**
 *
 * @author Joni
 */
public abstract class Paddle {

    private Type type;
    private Location location;

    public Paddle(int x, int y, Type type) {
        this.location = new Location(x, y);
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public abstract void move();

}
