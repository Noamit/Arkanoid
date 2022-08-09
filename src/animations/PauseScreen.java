package animations;
import biuoop.DrawSurface;
import interfaces.Animation;
/**
 * @author Noa Amit <noa1amit2@gmail.com>.
 * @version 1.0
 * @since 7.6.2021
 */
public class PauseScreen implements Animation {
    //CONSTANT PARAMETER
    static final int TEXT_SIZE = 32;
    /** Contractor.
     */
    public PauseScreen() {
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", TEXT_SIZE);
    }
    @Override
    public boolean shouldStop() {
        return false;
    }
}
