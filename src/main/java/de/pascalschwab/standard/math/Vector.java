package de.pascalschwab.standard.math;

public abstract class Vector<T> {
    public abstract T add(T v);

    public abstract T subtract(T v);

    public abstract T multiScalar(double scalar);

    public abstract double getLength();

    public T normalize() {
        return this.multiScalar(1 / this.getLength());
    }

    public abstract T standardize();

    public abstract boolean isEqual(T v);
}
