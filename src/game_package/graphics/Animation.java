package game_package.graphics;

import java.awt.image.BufferedImage;

public class Animation {
    private BufferedImage[] images_animation;
    private int currentFrame;
    private int numFrames;

    private int count;
    private int delay;

    private int timesPlayed;

    public Animation(BufferedImage[] frames){
        setAnimation(frames);
    }

    public Animation(){
        timesPlayed = 0;
    }

    public void setAnimation(BufferedImage[] frames){
        images_animation = frames;
        currentFrame = 0;
        timesPlayed = 0;
        delay = 2;
        numFrames = frames.length;
    }

    public void setDelay(int delay) { this.delay = delay; }
    public void setFrame(int frame_index) { this.currentFrame = frame_index; }
    public void setNumFrames(int num_frame) { this.numFrames = num_frame; }

    public void update(){
        if (delay == -1) return;
        count++;

        if (count == delay) {
            currentFrame++;
            count = 0;
        }
        if (currentFrame == numFrames){
            currentFrame = 0;
            timesPlayed++;
        }
    }

    public int getDelay() { return  this.delay; }
    public int getFrame() { return  this.currentFrame; }
    public int getCount() { return  this.count; }
    public BufferedImage getImage() { return this.images_animation[this.currentFrame]; }
    public boolean hasPlayedOnce() { return timesPlayed > 0; }
    public boolean hasPlayed(int cur_time) { return cur_time == timesPlayed; }
}
