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

package me.tarunb.gamefx.physics;

public class Vector {

    public static final Vector ZERO = new Vector(0, 0);
    public static final Vector IDENTITY = new Vector(1, 1);

    private double x = 0, y = 0;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Vector setX(double x) {
        this.x = x;
        return this;
    }

    public Vector setY(double y) {
        this.y = y;
        return this;
    }

    public Vector multiply(Vector other) {
        return multiply(other.x, other.y);
    }

    public Vector multiply(double factor) {
        return multiply(factor, factor);
    }

    public Vector multiply(double xFactor, double yFactor) {
        x *= xFactor;
        y *= yFactor;
        return this;
    }

    public Vector add(Vector other) {
        return add(other.x, other.y);
    }

    public Vector add(double x, double y) {
        this.x += x;
        this.y += y;
        return this;
    }

    public double magnitude() {
        return Math.sqrt(x*x + y*y);
    }

    public Vector normalize() {
        return new Vector(x / magnitude(), y / magnitude());
    }

    public PolarVector toPolarVector() {
        return new PolarVector(magnitude(), Math.toDegrees(Math.atan2(y, x)));
    }

    public Position toPosition() {
        return new Position(x, y);
    }

    @Override
    public String toString() {
        return "Vector{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
