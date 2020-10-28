package cegepst;

import cegepst.engine.Buffer;
import cegepst.engine.Footprint;
import cegepst.engine.Game;

import java.util.ArrayList;

public class MovingRectangleGame extends Game {

    private Player player;
    private GameController controller;
    private ArrayList<Footprint> footprints;

    public MovingRectangleGame() {
        controller = new GameController();
        player = new Player(controller);
        footprints = new ArrayList<>();
        super.addKeyListener(controller); // Viens de game
    }

    @Override
    public void initialise() {

    }

    @Override
    public void conclude() {

    }

    @Override
    public void update() {
        if (controller.isQuitPressed()) {
            super.stop();
        }
        player.update();
        if (controller.isMoving()) {
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
