package animations;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import interfaces.Animation;
/**
 * @author Noa Amit <noa1amit2@gmail.com>.
 * @version 1.0
 * @since 7.6.2021
 */
public class KeyPressStoppableAnimation implements Animation {
    //Fields
    private final KeyboardSensor keyboard;
    private final String key;
    private final Animation animation;
    private boolean stop, isAlreadyPressed;
    /** Contractor.
     * @param sensor - the KeyboardSensor.
     * @param key - the key to stop the animation.
     * @param animation - the current animation that is running.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.keyboard = sensor;
        this.key = key;
        this.animation = animation;
        this.stop = false;
        this.isAlreadyPressed = true;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        animation.doOneFrame(d);
        if (this.keyboard.isPressed(this.key)) {
            //checks if the key is already pressed before the needed time.
            if (!isAlreadyPressed) {
                this.stop = true;
            }
        } else {
            this.isAlreadyPressed = false;
        }
    }
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
