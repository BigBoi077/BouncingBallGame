package cegepst;

import cegepst.engine.Buffer;
import cegepst.engine.InputHandler;

import java.awt.*;

public class Player {

    private final int SPEED = 4;
    private int x;
    private int y;
    private int width = 30;
    private int height= 60;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void update(InputHandler inputHandler) {
        if (inputHandler.isDownPressed()) {
            y += SPEED;
        } else if (inputHandler.isUpPressed()) {
            y -= SPEED;
        } else if (inputHandler.isLeftPressed()) {
            x -= SPEED;
        } else if (inputHandler.isRightPressed()) {
            x += SPEED;
        }
    }

    public void draw(Buffer buffer) {
        buffer.drawRectangle(x, y, width, height, Color.green);
    }
}
