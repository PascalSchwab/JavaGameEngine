package de.pascalschwab.standard.math;

public final class Vector2 extends Vector<Vector2> {
    public final double x;
    public final double y;

    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public Vector2 add(Vector2 v) {
        return new Vector2(this.x + v.x, this.y + v.y);
    }

    @Override
    public Vector2 subtract(Vector2 v) {
        return new Vector2(this.x - v.x, this.y - v.y);
    }

    @Override
    public Vector2 multiScalar(double scalar) {
        return new Vector2(this.x * scalar, this.y * scalar);
    }

    @Override
    public double getLength() {
        return Math.sqrt(this.x * this.x + this.y * this.y);
    }

    @Override
    public Vector2 standardize() {
        double standardizedX = (x != 0) ? x / Math.abs(x) : x;
        double standardizedY = (y != 0) ? y / Math.abs(y) : y;
        return new Vector2(standardizedX, standardizedY);
    }

    @Override
    public boolean isEqual(Vector2 v) {
        return this.x == v.x && this.y == v.y;
    }
}
