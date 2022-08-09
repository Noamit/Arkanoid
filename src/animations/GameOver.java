package animations;
import biuoop.DrawSurface;
import interfaces.Animation;
import java.awt.Color;
/**
 * @author Noa Amit <noa1amit2@gmail.com>.
 * @version 1.0
 * @since 7.6.2021
 */
public class GameOver implements Animation {
    //CONSTANT PARAMETER
    static final int TEXT_X_START = 200;
    static final int REC_TEXT_WIDTH = 260;
    static final int REC_TEXT_HIGHT = 50;
    static final int TEXT_SIZE = 32;
    static final int START_BOARD = 0;
    static final int WIDTH = 800;
    static final int HIGHT = 600;
    //Fields
    private final int finalScore;
    /** Contractor.
     * @param finalScore - the score in the end of the game.
     */
    public GameOver(int finalScore) {
        this.finalScore = finalScore;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(START_BOARD, START_BOARD, WIDTH, HIGHT);
        d.setColor(Color.WHITE);
        d.fillRectangle(TEXT_X_START, (d.getHeight() / 2) + 70, REC_TEXT_WIDTH, REC_TEXT_HIGHT);
        d.setColor(Color.white);
        d.drawText(TEXT_X_START, d.getHeight() / 2, "Game Over. Your score is : " + this.finalScore, TEXT_SIZE);
        d.drawText(TEXT_X_START, (d.getHeight() / 2) - 100, "maybe next time...", TEXT_SIZE);
        d.setColor(Color.BLACK);
        d.drawText(TEXT_X_START, (d.getHeight() / 2) + 100, "press to continue:)", TEXT_SIZE);
    }
    @Override
    public boolean shouldStop() {
        return false;
    }
}
