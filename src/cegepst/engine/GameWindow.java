package cegepst.engine;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GameWindow {

    private static final int SLEEP = 25;
    private long before;
    private JFrame frame;
    private JPanel panel;
    private Ball ball;
    private BufferedImage bufferedImage;
    private Graphics2D buffer;
    private boolean playing = true;
    private int score = 0;

    public GameWindow() {
        initialiseFrame();
        initialisePanel();
        frame.add(panel);
        ball = new Ball(25);
    }

    public void start() {
        // Affichage de l'écran
        frame.setVisible(true);
        updateSyncTime();

        while (playing) {
            bufferedImage = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
            buffer = bufferedImage.createGraphics();
            buffer.setRenderingHints(getOptimalRenderingHints());

            update();
            drawOnBuffer();
            drawOnScreen();
            sleep();
        }
    }

    private void drawOnBuffer() {
        ball.draw(buffer);
        buffer.setPaint(Color.white);
        buffer.drawString("Score : " + score, 10, 20);
    }

    private void drawOnScreen() {
        Graphics2D graphics2D = (Graphics2D) panel.getGraphics();
        graphics2D.drawImage(bufferedImage, 0, 0, panel);
        Toolkit.getDefaultToolkit().sync();
        graphics2D.dispose();
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

    private RenderingHints getOptimalRenderingHints() {
        RenderingHints  rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        return rh;
    }

    private void initialisePanel() {
        panel = new JPanel();
        panel.setBackground(Color.blue);
        panel.setFocusable(true);
        panel.setDoubleBuffered(true);
    }

    private void initialiseFrame() {
        frame = new JFrame();
        frame.setSize(800, 600);
        // Pour centrer
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setTitle("Bouncing Ball Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
