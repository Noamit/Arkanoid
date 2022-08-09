package gameobjects;
import biuoop.DrawSurface;
import gamethings.GameLevel;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import helpers.Velocity;
import interfaces.Collidable;
import interfaces.HitListener;
import interfaces.HitNotifier;
import interfaces.Sprite;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Noa Amit <noa1amit2@gmail.com>.
 * @version 1.0
 * @since 7.6.2021
 */
public class Block implements Collidable, Sprite, HitNotifier {
    /**
     * the class creates a block.
     * the ball collides with the blocks and need to change his direction accordingly.
     */
    //Fields
    private final Rectangle block;
    private final java.awt.Color color;
    private List<HitListener> hitListeners;
    private GameLevel game;
    /** Contractor.
     * @param block - the rectangle shape of the block.
     * @param color - the color of the block.
     */
    public Block(Rectangle block, Color color) {
        this.block = block;
        this.color = color;
        this.hitListeners = new ArrayList<>();
    }
    @Override
    public Rectangle getCollisionRectangle() {
        return this.block;
    }
    @Override
    public java.util.List<Point> intersectionPointOfCollide(Line line) {
        List<Point> intersectionPoints = new ArrayList<>();
        if (line.intersectionWith(this.block.getLeftSide()) != null) {
            Point inter = line.intersectionWith(this.block.getLeftSide());
            inter.setBlock(this);
            intersectionPoints.add(inter);
        }
        if (line.intersectionWith(this.block.getRightSide()) != null) {
            Point inter = line.intersectionWith(this.block.getRightSide());
            inter.setBlock(this);
            intersectionPoints.add(inter);
        }
        if (line.intersectionWith(this.block.getDownLine()) != null) {
            Point inter = line.intersectionWith(this.block.getDownLine());
            inter.setBlock(this);
            intersectionPoints.add(inter);
        }
        if (line.intersectionWith(this.block.getUpperLine()) != null) {
            Point inter = line.intersectionWith(this.block.getUpperLine());
            inter.setBlock(this);
            intersectionPoints.add(inter);
        }
        return intersectionPoints;
    }
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        notifyHit(hitter);
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        double epsilon = Math.pow(10, -2);

        double x1 = collisionPoint.getX() - epsilon;
        double x2 = collisionPoint.getX() + epsilon;

        double y1 = collisionPoint.getY() - epsilon;
        double y2 = collisionPoint.getY() + epsilon;

        Line interPoint1 = new Line(x1, collisionPoint.getY(), x2, collisionPoint.getY());
        Line interPoint2 = new Line(collisionPoint.getX(), y1, collisionPoint.getX(), y2);

        if (interPoint1.isIntersecting(collisionPoint.getRectangle().getRightSide())
                || interPoint1.isIntersecting(collisionPoint.getRectangle().getLeftSide())) {
            dx = -dx;
        }
        if (interPoint2.isIntersecting(collisionPoint.getRectangle().getUpperLine())
                || interPoint2.isIntersecting(collisionPoint.getRectangle().getDownLine())) {
            dy = -dy;
        }
        return new Velocity(dx, dy);
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(color);
        d.fillRectangle((int) this.block.getUpperLeft().getX(), (int) this.block.getUpperLeft().getY(),
                (int) this.block.getWidth(), (int) this.block.getHeight());
        d.setColor(Color.black);
        d.drawRectangle((int) this.block.getUpperLeft().getX(), (int) this.block.getUpperLeft().getY(),
                (int) this.block.getWidth(), (int) this.block.getHeight());
    }
    @Override
    public void timePassed() {
    }
    /**
     * Add this paddle to the game.
     * @param g the game that the block is add to.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable((Collidable) this);
    }
    /**
     * setter.
     * @param game1 - the game that the block is in it.
     */
    public void setG(GameLevel game1) {
        this.game = game1;
    }
    /**
     * removeFromGame - remove a block from he game.
     * @param game1 -the game that the block is removed from it.
     */
    public void removeFromGame(GameLevel game1) {
        game1.removeCollidable(this);
        game1.removeSprite(this);
    }
    /**
     * notifyHit - notify to all the lisiners that there was a hit.
     * @param hitter - the ball that makes the hit.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
}
