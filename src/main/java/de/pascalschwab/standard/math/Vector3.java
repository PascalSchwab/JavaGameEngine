package de.pascalschwab.standard.math;

public final class Vector3 extends Vector<Vector3> {
    public final double x;
    public final double y;
    public final double z;

    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public Vector3 add(Vector3 v) {
        return new Vector3(this.x + v.x, this.y + v.y, this.z + v.z);
    }

    @Override
    public Vector3 subtract(Vector3 v) {
        return new Vector3(this.x - v.x, this.y - v.y, this.z - v.z);
    }

    @Override
    public Vector3 multiScalar(double scalar) {
        return new Vector3(this.x * scalar, this.y * scalar, this.z * scalar);
    }

    @Override
    public double getLength() {
        return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
    }

    @Override
    public Vector3 standardize() {
        double standardizedX = (x != 0) ? x / Math.abs(x) : x;
        double standardizedY = (y != 0) ? y / Math.abs(y) : y;
        double standardizedZ = (z != 0) ? z / Math.abs(z) : z;
        return new Vector3(standardizedX, standardizedY, standardizedZ);
    }

    @Override
    public boolean isEqual(Vector3 v) {
        return this.x == v.x && this.y == v.y && this.z == v.z;
    }
}
