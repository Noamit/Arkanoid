
package interfaces;
import biuoop.DrawSurface;
/**
 * @author Noa Amit <noa1amit2@gmail.com>.
 * @version 1.0
 * @since 7.6.2021
 */
public interface Animation {
    /**
     * doOneFrame - What is being done in its current animation in one frame.
     * @param d - The object through which to draw on the board.
     */
    void doOneFrame(DrawSurface d);

    /**
     * shouldStop - By this function the program knows whether to continue running the animation to one more frame.
     * @return if the current animation need to be stopped.
     */
    boolean shouldStop();
}
