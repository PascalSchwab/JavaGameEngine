package de.pascalschwab.standard.math;

public final class Vector4 extends Vector<Vector4> {
    public final double x;
    public final double y;
    public final double z;
    public final double a;

    public Vector4(double x, double y, double z, double a) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.a = a;
    }

    @Override
    public Vector4 add(Vector4 v) {
        return new Vector4(this.x + v.x, this.y + v.y, this.z + v.z, this.a + v.a);
    }

    @Override
    public Vector4 subtract(Vector4 v) {
        return new Vector4(this.x - v.x, this.y - v.y, this.z - v.z, this.a - v.a);
    }

    @Override
    public Vector4 multiScalar(double scalar) {
        return new Vector4(this.x * scalar, this.y * scalar, this.z * scalar, this.a * scalar);
    }

    @Override
    public double getLength() {
        return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z + this.a * this.a);
    }

    @Override
    public Vector4 standardize() {
        double standardizedX = (x != 0) ? x / Math.abs(x) : x;
        double standardizedY = (y != 0) ? y / Math.abs(y) : y;
        double standardizedZ = (z != 0) ? z / Math.abs(z) : z;
        double standardizedA = (a != 0) ? a / Math.abs(a) : a;
        return new Vector4(standardizedX, standardizedY, standardizedZ, standardizedA);
    }

    @Override
    public boolean isEqual(Vector4 v) {
        return this.x == v.x && this.y == v.y && this.z == v.z && this.a == v.a;
    }
}
