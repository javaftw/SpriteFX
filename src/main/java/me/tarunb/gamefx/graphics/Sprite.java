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

package me.tarunb.gamefx.graphics;

import me.tarunb.gamefx.physics.Vector;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Sprite {

    public static final Sprite BLANK_IMAGE = new Sprite("#/black.jpg");

    private final ImageView imageView;
    private Vector scale;

    public Sprite(Image image, Vector scale) {
        this.imageView = new ImageView(image);
        this.scale = scale;
        imageView.setScaleX(scale.getX());
        imageView.setScaleY(scale.getY());
    }

    public Sprite(Image image) {
        this(image, Vector.IDENTITY);
    }

    public Sprite(String fileName) {
        if (fileName.startsWith("#/")) {
            this.imageView = new ImageView(new Image(getClass().getResourceAsStream("/res/img/" + fileName.substring(2))));
        } else {
            this.imageView = new ImageView(fileName);
        }
    }

    public Sprite(String fileName, Vector scale) {
        if (fileName.startsWith("#/")) {
            this.imageView = new ImageView(new Image(getClass().getResourceAsStream("/res/img/" + fileName.substring(2))));
        } else {
            this.imageView = new ImageView(fileName);
        }
        imageView.setScaleX(scale.getX());
        imageView.setScaleY(scale.getY());
    }

    public Image getImage() {
        return imageView.getImage();
    }

    public void setImage(Image image) {
        imageView.setImage(image);
    }

    public void setImage(String fileName) {
        if (fileName.startsWith("#/")) {
            setImage(new Image(getClass().getResourceAsStream("/res/img/" + fileName.substring(2))));
        } else {
            setImage(new Image(fileName));
        }
    }

    public ImageView getImageView() {
        return imageView;
    }


}
