/**
 * GameFX
 * <p>
 * The MIT License (MIT)
 * <p>
 * Copyright (c) 2016 Tarun Boddupalli
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package me.tarunb.gamefx.entity;

import me.tarunb.gamefx.graphics.Sprite;
import me.tarunb.gamefx.graphics.Updatable;
import me.tarunb.gamefx.input.KeyHandler;
import me.tarunb.gamefx.input.MouseHandler;
import me.tarunb.gamefx.physics.Physics;
import me.tarunb.gamefx.physics.Position;
import me.tarunb.gamefx.physics.Vector;
import me.tarunb.gamefx.view.View;
import javafx.scene.Group;

/**
 * An entity is an object in a view that can have motion,
 * can handle input events, and is {@link Updatable}
 */
public class Entity extends Group implements Updatable {

    private Position position;
    private Vector velocity;
    private Sprite sprite;

    private View view;

    private MouseHandler mouseHandler;
    private KeyHandler keyHandler;

    /**
     * Constructs an entity with the specified position, velocity,
     * and sprite.
     * @param position the initial position for this entity.
     * @param velocity the initial velocity for this entity.
     * @param sprite the sprite for this entity.
     */
    public Entity(Position position, Vector velocity, Sprite sprite) {
        super(sprite.getImageView());

        this.position = position;
        setTranslateX(position.getX() * Physics.PPM);
        setTranslateY(-position.getY() * Physics.PPM);

        this.velocity = velocity;
        this.sprite = sprite;

        position.xProperty().addListener((observable, oldValue, newValue) -> {
            translateXProperty().set(newValue.doubleValue() * Physics.PPM);
        });

        position.yProperty().addListener((observable, oldValue, newValue) -> {
            translateYProperty().set(newValue.doubleValue() * Physics.PPM);
        });

    }

    /**
     * Constructs and entity with a position of <code>(0, 0</code>, and the
     * specified velocity and sprite.
     * @param velocity The initial velocity for this entity.
     * @param sprite The sprite for this entity.
     */
    public Entity(Vector velocity, Sprite sprite) {
        this(new Position(0, 0), velocity, sprite);
    }

    public Entity(Position pos, Sprite sprite) {
        this(pos, new Vector(0, 0), sprite);
    }

    /**
     * Constructs an Entity with a position of <code>(0, 0)</code> and
     * a velocity of <code>(0, 0)</code>, and the specified Sprite.
     * @param sprite The sprite for this entity
     */
    public Entity(Sprite sprite) {
        this(new Position(0, 0), new Vector(0, 0), sprite);
    }

    /**
     * This method is called every frame by the entity's containing view.
     * Should you override this method in a subclass of {@link Entity},
     * you must always called <code>super.update(dt)</code>, else the physics
     * will fail to update.
     * @param dt The amount of elapsed time, in seconds, since the last frame.
     */
    @Override
    public void update(double dt) {

        // v      =     v_0           +        a        * t
        velocity.setX(velocity.getX() + Physics.GRAVITY.getX() * dt);
        velocity.setY(velocity.getY() + Physics.GRAVITY.getY() * dt);

        double xPos = position.getX();
        xPos += velocity.getX() * dt;
        if (xPos < 0) {
            xPos = 0;
            velocity.setX(0);
        }

        double yPos = position.getY();
        yPos += velocity.getY() * dt;
        if (yPos < 0) {
            yPos = 0;
            velocity.setY(0);
        }

        position.setX(xPos);
        position.setY(yPos);

    }

    /**
     * Adds this entity to the specified view.
     *
     * @param view The view to which this entity should be added
     */
    public void addToView(View view) {
        view.addEntity(this);
        this.view = view;
    }

    /**
     * Removes this entity from its containing view, if it exists.
     * Otherwise fails silently.
     */
    public void removeFromParentView() {
        if (view != null) {
            view.removeEntity(this);
            this.view = null;
        }
    }

    /**
     * Returns the view that contains this entity.
     * If this entity has no containing view, this method returns null.
     *
     * @return the view that contains this entity.
     */
    public View getView() {
        return view;
    }

    /**
     * Returns the position of this entity in its containing view.
     *
     * @return the position of this entity in its containing view.
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Sets the position of this entity in its containing view.
     * @param position the new position of the entity
     */
    public void setPosition(Position position) {
        this.position.setX(position.getX());
        this.position.setY(position.getY());
    }

    /**
     * Returns the velocity of this entity.
     * @return the velocity of this entity.
     */
    public Vector getVelocity() {
        return velocity;
    }

    /**
     * Sets the velocity of this entity.
     * @param velocity the new velocity of the entity
     */
    public void setVelocity(Vector velocity) {
        this.velocity = velocity;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    /**
     * Returns the event handler for mouse events on this
     * entity, or null if it doesn't exist.
     * @return the {@link MouseHandler} for this entity
     */
    public MouseHandler getMouseHandler() {
        return mouseHandler;
    }

    /**
     * Sets the new event handler for mouse events on this
     * entity.
     * @param mouseHandler the new {@link MouseHandler} for this entity.
     */
    public void setMouseHandler(MouseHandler mouseHandler) {
        this.mouseHandler = mouseHandler;
    }

    /**
     * Returns the event handler for keyboard events on this
     * entity, or null if it doesn't exist.
     * @return the {@link KeyHandler} for this entity
     */
    public KeyHandler getKeyHandler() {
        return keyHandler;
    }

    /**
     * Sets the new event handler for keyboard events on this
     * entity.
     * @param keyHandler the new {@link KeyHandler} for this entity.
     */
    public void setKeyHandler(KeyHandler keyHandler) {
        this.keyHandler = keyHandler;
    }
}
