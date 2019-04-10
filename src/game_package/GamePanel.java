package game_package;

import game_package.states.GameStateManager;
import game_package.util.KeyHandler;
import game_package.util.MouseHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable{

    public static int width;
    public static int height;

    private Thread thread;
    private boolean running = false;

    private BufferedImage image;
    private Graphics2D grs;

    private MouseHandler mouse;
    private KeyHandler key;

    private GameStateManager gsm;

    public GamePanel(int width, int height){
        this.width = width;
        this.height = height;
        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        requestFocus();
    }

    public void addNotify(){
        super.addNotify();

        if (thread == null){
            thread = new Thread(this, "GameThread");
            thread.start();
        }
    }

    public void init(){
        running = true;

        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        grs = (Graphics2D) image.getGraphics();

        mouse = new MouseHandler(this);
        key = new KeyHandler(this);

        gsm = new GameStateManager();
    }

    public void run(){
        init();

        final double GAME_HERTZ = 60.0;
        final double timeBeforeUpdate = 1000000000 / GAME_HERTZ;

        final int mostUpdateBeforeRender = 5;

        double lastUpdateTime = System.nanoTime();
        double lastRenderTime;

        final double TARGET_FPS = 60;
        final double TTBR = 1000000000 / TARGET_FPS;  // TOTAL TIME BEFORE RENDER

        int frameCount = 0;
        int lastSecondTime = (int) lastUpdateTime / 1000000000;
        int oldFrameCount = 0;

        while(running){
            double now = System.nanoTime();
            int updateCount = 0;
            while ((now - lastUpdateTime) > timeBeforeUpdate && updateCount < mostUpdateBeforeRender){
                update();
                input(mouse, key);
                lastUpdateTime += timeBeforeUpdate;
                updateCount += 1;
            }

            if(now - lastUpdateTime > timeBeforeUpdate){
                lastUpdateTime = now - timeBeforeUpdate;
            }

            input(mouse, key);
            render();
            draw();
            lastRenderTime = now;
            frameCount++;

            int thisSecond = (int) (lastUpdateTime / 1000000000);
            if (thisSecond > lastSecondTime){
                if (frameCount != oldFrameCount){
                    System.out.println("New seconds " + thisSecond + " " + frameCount);
                    oldFrameCount = frameCount;
                }
                frameCount = 0;
                lastSecondTime = thisSecond;
            }

            while (now - lastRenderTime < TTBR && now - lastUpdateTime < timeBeforeUpdate){
                Thread.yield();

                try {
                    Thread.sleep(1);
                } catch (Exception e){
                    System.out.println("Error: yielding thread.");
                }
                now = System.nanoTime();
            }
        }
    }

    public void update() {
        gsm.update();
    }

    public void input(MouseHandler mouse, KeyHandler key) {
        gsm.input(mouse, key);
    }

    public void render(){
        if (grs != null){
            grs.setColor(new Color(66, 134, 244));
            grs.fillRect(0, 0, width, height);
            gsm.render(grs);
        }
    }

    public void draw(){
        Graphics graphics2 = (Graphics) this.getGraphics();
        graphics2.drawImage(image, 0, 0, width, height, null);
        graphics2.dispose();
    }
}

