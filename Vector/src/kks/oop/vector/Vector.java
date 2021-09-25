package kks.oop.vector;

import java.util.Arrays;

public class Vector {
    private double[] elements;

    public Vector(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Size should be more then 0");
        }

        elements = new double[size];
    }

    public Vector(Vector v) {
        this(v.elements);
    }

    public Vector(double[] array) {
        this(array.length, array);
    }

    public Vector(int size, double[] array) {
        if (size <= 0) {
            throw new IllegalArgumentException("Size should be more then 0");
        }

        if (array == null) {
            throw new IllegalArgumentException("Array shouldn't be NULL");
        }

        elements = new double[size];

        System.arraycopy(array, 0, elements, 0, Math.min(array.length, size));
    }

    public int getSize() {
        return elements.length;
    }

    public double getLength() {
        double powSum = 0;

        for (double e : elements) {
            powSum += e * e;
        }

        return Math.sqrt(powSum);
    }

    public double get(int index) {
        if (index < 0 || index > elements.length) {
            throw new IllegalArgumentException("Index out of bounds array");
        }

        return elements[index];
    }

    public double[] getAll() {
        return elements;
    }


    public void set(int index, double value) {
        if (index < 0 || index > elements.length) {
            throw new IllegalArgumentException("Index out of bounds array");
        }

        elements[index] = value;
    }

    public void add(Vector v) {
        if (v == null) {
            throw new IllegalArgumentException("Vector shouldn't be NULL");
        }

        if (elements.length < v.elements.length) {
            elements = Arrays.copyOf(elements, v.elements.length);
        }

        for (int i = 0; i < v.elements.length; i++) {
            elements[i] += v.elements[i];
        }
    }

    public void subtract(Vector v) {
        if (v == null) {
            throw new IllegalArgumentException("Vector shouldn't be NULL");
        }

        if (elements.length < v.elements.length) {
            elements = Arrays.copyOf(elements, v.elements.length);
        }

        for (int i = 0; i < v.elements.length; i++) {
            elements[i] -= v.elements[i];
        }
    }

    public void multiplyByScalar(double value) {
        for (int i = 0; i < elements.length; i++) {
            elements[i] *= value;
        }
    }

    public void reflect() {
        multiplyByScalar(-1);
    }

    public static Vector getSum(Vector v1, Vector v2) {
        if (v1 == null) {
            throw new IllegalArgumentException("First argument shouldn't be NULL");
        }

        if (v2 == null) {
            throw new IllegalArgumentException("Second argument shouldn't be NULL");
        }

        Vector resultVector = new Vector(v1);

        resultVector.add(v2);

        return resultVector;
    }

    public static Vector getSubtract(Vector v1, Vector v2) {
        if (v1 == null) {
            throw new IllegalArgumentException("First argument shouldn't be NULL");
        }

        if (v2 == null) {
            throw new IllegalArgumentException("Second argument shouldn't be NULL");
        }

        Vector resultVector = new Vector(v1);

        resultVector.subtract(v2);

        return resultVector;
    }

    public static double getScalarProduct(Vector v1, Vector v2) {
        if (v1 == null) {
            throw new IllegalArgumentException("First argument shouldn't be NULL");
        }

        if (v2 == null) {
            throw new IllegalArgumentException("Second argument shouldn't be NULL");
        }

        double result = 0.0;
        int minimumLength = Math.min(v1.elements.length, v2.elements.length);

        for (int i = 0; i < minimumLength; i++) {
            result += (v1.elements[i] * v2.elements[i]);
        }

        return result;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("{");

        for (double e : elements) {
            stringBuilder.append(e).append(", ");
        }

        stringBuilder.replace(stringBuilder.length() - 2, stringBuilder.length(), "}");

        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Vector v = (Vector) o;

        return Arrays.equals(elements, v.elements);
    }

    @Override
    public int hashCode() {
        final int prime = 39;
        int hash = 1;

        hash = prime * hash + Arrays.hashCode(elements);

        return hash;
    }
}

