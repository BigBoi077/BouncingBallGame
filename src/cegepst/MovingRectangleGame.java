package cegepst;

import cegepst.engine.Buffer;
import cegepst.engine.Footprint;
import cegepst.engine.Game;
import cegepst.engine.InputHandler;

import java.awt.*;
import java.util.ArrayList;

public class MovingRectangleGame extends Game {

    private Player player;
    private InputHandler inputHandler;
    private ArrayList<Footprint> footprints;

    public MovingRectangleGame() {
        player = new Player(100, 100);
        inputHandler = new InputHandler();
        footprints = new ArrayList<>();
        super.addKeyListener(inputHandler); // vien de game
    }

    @Override
    public void initialise() {

    }

    @Override
    public void conclude() {

    }

    @Override
    public void update() {
        if (inputHandler.isQuitPressed()) {
            super.stop();
        }
        player.update(inputHandler);
        if (inputHandler.isMoving()) {
            footprints.add(player.layFootPrint());
        }
    }

    @Override
    public void draw(Buffer buffer) {
        for (Footprint footprint : footprints) {
            footprint.draw(buffer);
        }
        player.draw(buffer);
    }
}
