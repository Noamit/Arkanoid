package animations;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import interfaces.Animation;
/**
 * @author Noa Amit <noa1amit2@gmail.com>.
 * @version 1.0
 * @since 7.6.2021
 */
public class AnimationRunner {
    //Fields
    private final GUI gui;
    private final int framesPerSecond;
    private final Sleeper sleeper;
    /** Contractor.
     * @param gui - the game screen
     */
    public AnimationRunner(GUI gui) {
        this.gui = gui;
        this.framesPerSecond = 60;
        this.sleeper = new Sleeper();
    }
    /** run - Runs the game continuously.
     * @param animation - the current animation that is running.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (!animation.shouldStop()) {
            DrawSurface d = gui.getDrawSurface();
            long startTime = System.currentTimeMillis(); // timing
            animation.doOneFrame(d);
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
