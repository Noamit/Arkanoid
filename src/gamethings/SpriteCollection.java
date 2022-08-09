package gamethings;
import java.util.ArrayList;
import java.util.List;
import biuoop.DrawSurface;
import interfaces.Sprite;
/**
 * @author Noa Amit <noa1amit2@gmail.com>.
 * @version 1.0
 * @since 7.6.2021
 */
public class SpriteCollection {
    /**
     * class that holds an array with all the objects in the game.
     * the class has a method which draw the object on the screen,
     * and methods that notify the sprite that time has passed.
     */
    //Fields
    private final List<Sprite> sprites;

    /**
     * Contractor.
     * @param sprites - a list of sprites objects.
     */
    public SpriteCollection(List<Sprite> sprites) {
        this.sprites = new ArrayList<>();
    }

    /**
     * addSprite - the method adds a sprite object to the list.
     * @param s - a sprite object.
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }
    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> spriteCope = new ArrayList<Sprite>(this.sprites);
        for (Sprite s : spriteCope) {
            s.timePassed();
        }
    }
    /**
     * call drawOn(d) on all sprites.
     * @param d - The object through which to draw on the board.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : sprites) {
            s.drawOn(d);
        }
    }

    /**
     * getter.
     * @return a list of the sprites in the game.
     */
    public List<Sprite> getSprites() {
        return this.sprites;
    }
}
