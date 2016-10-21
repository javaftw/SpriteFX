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

package me.tarunb.examplegame;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import me.tarunb.gamefx.input.KeyHandler;
import me.tarunb.gamefx.physics.PolarVector;
import me.tarunb.gamefx.view.View;

public class View1 extends View {

    public View1() {
        super("view1", Color.LIGHTBLUE);
    }

    @Override
    protected void onBeforeLoad() {
        super.onBeforeLoad(); // must call super.onBeforeLoad(), otherwise game loop won't be started
        SquareEntity square = new SquareEntity();
        square.setKeyHandler(new KeyHandler() {
            @Override
            public void pressed(KeyCode button, KeyCode modifier) {
                if (button == KeyCode.SPACE) {
                    square.setVelocity(new PolarVector(10, 45).toVector());
                }
            }
        });
        square.addToView(this);
    }

}
