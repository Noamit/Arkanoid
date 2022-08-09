
package interfaces;
/**
 * @author Noa Amit <noa1amit2@gmail.com>.
 * @version 1.0
 * @since 7.6.2021
 */
public interface HitNotifier {
    /**
     * Add hl as a listener to hit events.
     * @param hl - a listener.
     */
    void addHitListener(HitListener hl);
    /**
     * Remove hl from the list of listeners to hit events.
     * @param hl - a listener.
     */
    void removeHitListener(HitListener hl);
}
