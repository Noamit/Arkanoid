
package interfaces;
import gameobjects.Ball;
import gameobjects.Block;
/**
 * @author Noa Amit <noa1amit2@gmail.com>.
 * @version 1.0
 * @since 7.6.2021
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     * @param beingHit - the beingHit object is hit.
     * @param hitter - the Ball that's doing the hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
