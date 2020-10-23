package cegepst.engine;

import cegepst.engine.entity.StaticEntity;

import java.awt.*;
import java.util.Random;

public class Footprint extends StaticEntity {

    public Footprint(int x, int y) {
        setDimensions(5, 5);
        teleport(x, y);
    }

    public void draw(Buffer buffer) {
        Color color = new Color(getRandomValue(),
                getRandomValue(), getRandomValue());
        buffer.drawRectangle(x, y, width, height, color);
    }

    private int getRandomValue() {
        return (new Random()).nextInt(256);
    }
}
