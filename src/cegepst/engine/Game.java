package cegepst.engine;

import java.awt.*;

public class Game {

    private static final int SLEEP = 25;
    private long before;
    private boolean playing = true;
    private int score = 0;
    private RenderingEngine renderingEngine;
    private Ball ball;

    private Graphics2D buffer;

    public Game() {
        renderingEngine = new RenderingEngine();
        ball = new Ball(25);
    }

    public void start() {
        // Affichage de l'écran
        renderingEngine.start();
        updateSyncTime();

        while (playing) {
            update();
            drawOnBuffer(renderingEngine.getRenderingBuffer());
            renderingEngine.renderBufferOnScreen();
            sleep();
        }
        renderingEngine.stop();
    }

    private void drawOnBuffer(Graphics2D buffer) {
        ball.draw(buffer);
        buffer.setPaint(Color.white);
        buffer.drawString("Score : " + score, 10, 20);
    }

    private void updateSyncTime() {
        before = System.currentTimeMillis();
    }

    private void sleep() {
        try {
            Thread.sleep(getSleepTime());
        } catch ( InterruptedException e) {
            e.printStackTrace();
        }
        updateSyncTime();
    }

    private long getSleepTime() {
        long sleep = SLEEP - (System.currentTimeMillis() - before);
        if (sleep < 0) {
            sleep = 4;
        }
        return sleep;
    }

    private void update() {
        ball.update();
        if (ball.hasTouchedBound()) {
            score += 10;
        }
    }
}