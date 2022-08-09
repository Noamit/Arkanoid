package animations;
import biuoop.DrawSurface;
import biuoop.Sleeper;
import gamethings.SpriteCollection;
import interfaces.Animation;
import interfaces.Sprite;
import java.awt.Color;
/**
 * @author Noa Amit <noa1amit2@gmail.com>.
 * @version 1.0
 * @since 7.6.2021
 */
public class CountdownAnimation implements Animation {
    //CONSTANT PARAMETER
    static final int REC_X_START = 390;
    static final int REC_Y_START = 270;
    static final int TEXT_SIZE = 32;
    static final int START_COUNTDOWN = 3;
    static final int WIDTH_AND_HIGHT = 40;
    static final int MILLISECOND = 1000;
    //Fields
    private final double numOfSeconds;
    private int countFrom;
    private boolean stop;
    private final SpriteCollection gameScreen;
    private final Sprite background;
    /** Contractor.
     * @param numOfSeconds - the seconds it takes to count down between each number.
     * @param countFrom - the first number of the count down.
     * @param gameScreen - the sprites in level during the count down.
     * @param background - the background of level during the count down.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen, Sprite background) {
        this.numOfSeconds = numOfSeconds * MILLISECOND;
        this.countFrom = countFrom;
        this.stop = false;
        this.gameScreen = gameScreen;
        this.background = background;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        this.background.drawOn(d);
        d.setColor(Color.white);
        d.fillRectangle(REC_X_START, REC_Y_START, WIDTH_AND_HIGHT, WIDTH_AND_HIGHT);
        d.setColor(Color.black);
        this.gameScreen.drawAllOn(d);
        //count down
        d.drawText(d.getWidth() / 2, d.getHeight() / 2, "" + this.countFrom + "", TEXT_SIZE);
        if (countFrom != START_COUNTDOWN) {
            Sleeper sleeper = new Sleeper();
            long startTime = System.currentTimeMillis();
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = ((long) numOfSeconds - usedTime);
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
        if (this.countFrom == 0) {
            this.stop = true;
        }
        //for the next count
        this.countFrom = countFrom - 1;
    }
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}