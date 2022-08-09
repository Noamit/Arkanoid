package listeners;
import gameobjects.Ball;
import gameobjects.Block;
import gamethings.GameLevel;
import helpers.Counter;
import interfaces.HitListener;
/**
 * @author Noa Amit <noa1amit2@gmail.com>.
 * @version 1.0
 * @since 7.6.2021
 */
public class BallRemover implements HitListener {
    /**
     * a BallRemover is in charge of removing balls from the game, as well as keeping count
     * of the number of balls that remain.
     */
    //Fields
    private final GameLevel game;
    private final Counter remainingBalls;
    /** Contractor.
     * @param game - the current game.
     * @param remainingBalls - the number of balls that are in the game.
     */
    public BallRemover(GameLevel game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.remainingBalls.decrease(1);
        game.removeSprite(hitter);
    }
}
