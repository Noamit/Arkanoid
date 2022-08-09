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
public class BlockRemover implements HitListener {
    /**
     * a BlockRemover is in charge of removing blocks from the game, as well as keeping count
     * of the number of blocks that remain.
     */
    //Fields
    private final GameLevel game;
    private final Counter remainingBlocks;
    /**
     * Contractor.
     * @param game - the current game.
     * @param remainingBlocks - the number of block that are in the game.
     */
    public BlockRemover(GameLevel game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(game);
        this.remainingBlocks.decrease(1);
    }
}