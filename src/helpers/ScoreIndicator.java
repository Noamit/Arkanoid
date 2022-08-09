package helpers;
import biuoop.DrawSurface;
import interfaces.Sprite;
import java.awt.Color;
/**
 * @author Noa Amit <noa1amit2@gmail.com>.
 * @version 1.0
 * @since 7.6.2021
 */
public class ScoreIndicator implements Sprite {
    //CONSTANT PARAMETER
    static final int TEXT_START = 350;
    static final int TEXT_END = 25;
    static final int TEXT_SIZE = 20;
    static final int RECT_Y_START = 5;
    static final int WIDTH = 800;
    static final int HIGHT = 30;
    //Fields
    private final Counter currentScore;

    /**
     * Contractor.
     * @param c - the counter that count the score.
     */
    public ScoreIndicator(Counter c) {
        this.currentScore = c;
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.white);
        d.fillRectangle(0, RECT_Y_START, WIDTH, HIGHT);
        d.setColor(Color.black);
        d.drawText(TEXT_START, TEXT_END, "Score:" + " " + currentScore.getValue(), TEXT_SIZE);
    }

    @Override
    public void timePassed() {
        return;
    }
}
