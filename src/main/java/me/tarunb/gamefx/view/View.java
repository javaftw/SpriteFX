package me.tarunb.gamefx.view;

import me.tarunb.gamefx.Utils;
import me.tarunb.gamefx.entity.Entity;
import me.tarunb.gamefx.input.KeyHandler;
import me.tarunb.gamefx.input.MouseHandler;
import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.util.concurrent.ConcurrentLinkedQueue;

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

public class View extends Scene {

    private final String id;
    private StackPane pane = new StackPane();

    // Concurrent so you can add entities from input handlers
    private ConcurrentLinkedQueue<Entity> entities = new ConcurrentLinkedQueue<>();

    private KeyHandler keyHandler;
    private MouseHandler mouseHandler;

    public MouseHandler getMouseHandler() {
        return mouseHandler;
    }

    public void setMouseHandler(MouseHandler mouseHandler) {
        this.mouseHandler = mouseHandler;
    }

    public KeyHandler getKeyHandler() {
        return keyHandler;
    }

    public void setKeyHandler(KeyHandler keyHandler) {
        this.keyHandler = keyHandler;
    }

    private AnimationTimer animationTimer = new AnimationTimer() {
        private long last = System.nanoTime();

        @Override
        public void handle(long now) {
            entities.forEach(e -> e.update((now - last) / 1e9));
            last = now;
        }

    };

    private Runnable beforeLoad, afterLoad, beforeUnload, afterUnload;

    View() {
        super(new StackPane());
        this.id = "default";
        setRoot(pane);
    }

    public View(String id) {
        super(new StackPane());
        if (id.equals("default")) {
            throw new IllegalArgumentException("View id cannot be \"default\", this is reserved.");
        }
        this.id = id;
        setRoot(pane);
        pane.setAlignment(Pos.BOTTOM_LEFT);
        registerInputHandlers();

    }

    public View(String id, Color backgroundColor) {
        this(id);
        setFill(backgroundColor);
    }

    public Pane getPane() {
        return pane;
    }

    public String getId() {
        return id;
    }

    public void setBeforeLoad(Runnable beforeLoad) {
        this.beforeLoad = beforeLoad;
    }

    public void setAfterLoad(Runnable afterLoad) {
        this.afterLoad = afterLoad;
    }

    public void setBeforeUnload(Runnable beforeUnload) {
        this.beforeUnload = beforeUnload;
    }

    public void setAfterUnload(Runnable afterUnload) {
        this.afterUnload = afterUnload;
    }

    /**
     * This method is called by {@link Entity}, and should not be used anywhere else.
     * Use the {@link Entity#addToView} method instead.
     * @param newEntity The entity to add
     */
    public void addEntity(Entity newEntity) {
        entities.add(newEntity);
        pane.getChildren().add(newEntity);
    }

    /**
     * This method is called by {@link Entity}, and should not be used anywhere else.
     * Use the {@link Entity#removeFromParentView()} method instead.
     * @param entity The entity to remove
     */
    public void removeEntity(Entity entity) {
        entities.remove(entity);
    }

    private void registerInputHandlers() {
        setOnMousePressed(e -> {
            if (mouseHandler != null) {
                mouseHandler.pressed(e.getButton());
            }
            entities.forEach(entity -> {
                if (entity.getMouseHandler() != null) {
                    entity.getMouseHandler().pressed(e.getButton());
                }
            });
        });

        setOnMouseReleased(e -> {
            if (mouseHandler != null) {
                mouseHandler.released(e.getButton());
            }
            entities.forEach(entity -> {
                if (entity.getMouseHandler() != null) {
                    entity.getMouseHandler().released(e.getButton());
                }
            });
        });

        setOnMouseClicked(e -> {
            if (mouseHandler != null) {
                mouseHandler.clicked(e);
            }
            entities.forEach(entity -> {
                if (entity.getMouseHandler() != null) {
                    entity.getMouseHandler().clicked(e);
                }
            });
        });

        setOnKeyPressed(e -> {
            if (keyHandler != null) {
                keyHandler.pressed(e.getCode(), Utils.getKeyModifier(e));
            }
            entities.forEach(entity -> {
                if (entity.getKeyHandler() != null) {
                    entity.getKeyHandler().pressed(e.getCode(), Utils.getKeyModifier(e));
                }
            });
        });

        setOnKeyReleased(e -> {
            if (keyHandler != null) {
                keyHandler.released(e.getCode());
            }
            entities.forEach(entity -> {
                if (entity.getKeyHandler() != null) {
                    entity.getKeyHandler().released(e.getCode());
                }
            });
        });

        setOnKeyTyped(e -> {
            if (keyHandler != null) {
                keyHandler.typed(e.getCode(), Utils.getKeyModifier(e));
            }
            entities.forEach(entity -> {
                if (entity.getKeyHandler() != null) {
                    entity.getKeyHandler().typed(e.getCode(), Utils.getKeyModifier(e));
                }
            });
        });
    }

    void beforeLoad() {
        if (beforeLoad != null) {
            beforeLoad.run();
        }
    }

    void beforeUnload() {
        if (beforeUnload != null) {
            beforeUnload.run();
        }
        animationTimer.stop();
    }

    void afterLoad() {
        if (afterLoad != null) {
            afterLoad.run();
        }
        animationTimer.start();
    }

    void afterUnload() {
        if (afterUnload != null) {
            afterUnload.run();
        }
    }

    public Runnable getBeforeLoad() {
        return beforeLoad;
    }

    public Runnable getAfterLoad() {
        return afterLoad;
    }

    public Runnable getBeforeUnload() {
        return beforeUnload;
    }

    public Runnable getAfterUnload() {
        return afterUnload;
    }


}
