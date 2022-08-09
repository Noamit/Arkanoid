
package interfaces;
import biuoop.DrawSurface;
/**
 * @author Noa Amit <noa1amit2@gmail.com>.
 * @version 1.0
 * @since 7.6.2021
 */
public interface Sprite {
    /**
     * the interface of all the objects in the game.
     * each object is drawn on the screen and moves according to its properties.
     */
    /**
     * draw the sprite to the screen.
     * @param d The object through which to draw on the board.
     */
    void drawOn(DrawSurface d);
    /**
     * notify the sprite that time has passed.
     */
    void timePassed();
}
