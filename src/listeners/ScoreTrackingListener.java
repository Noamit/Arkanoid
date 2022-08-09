package listeners;
import gameobjects.Ball;
import gameobjects.Block;
import helpers.Counter;
import interfaces.HitListener;
/**
 * @author Noa Amit <noa1amit2@gmail.com>.
 * @version 1.0
 * @since 7.6.2021
 */
public class ScoreTrackingListener implements HitListener {
    //Fields
    private final Counter currentScore;

    /**
     * Contractor.
     * @param scoreCounter - the counter that count the score.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
    }
    /**
     * getter.
     * @return the corrent score of the game.
     */
    public Counter getCurrentScore() {
        return currentScore;
    }
}