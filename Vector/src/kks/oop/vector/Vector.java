package kks.oop.vector;

import java.util.Arrays;

public class Vector {
    private double[] elements;

    public Vector(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Size of vector should be more then 0. Current size = " + size);
        }

        elements = new double[size];
    }

    public Vector(Vector vector) {
        this(vector.elements);
    }

    public Vector(double[] array) {
        this(array.length, array);
    }

    public Vector(int size, double[] array) {
        if (size <= 0) {
            throw new IllegalArgumentException("Size of vector should be more then 0. Current size = " + size);
        }

        if (array == null) {
            throw new IllegalArgumentException("Array shouldn't be NULL");
        }

        elements = Arrays.copyOf(array, size);
    }

    public int getSize() {
        return elements.length;
    }

    public double getLength() {
        double sum = 0;

        for (double e : elements) {
            sum += e * e;
        }

        return Math.sqrt(sum);
    }

    public double get(int index) {
        checkIndex(index);

        return elements[index];
    }

    public void set(int index, double value) {
        checkIndex(index);

        elements[index] = value;
    }

    public void add(Vector vector) {
        if (vector == null) {
            throw new IllegalArgumentException("Vector shouldn't be NULL");
        }

        if (elements.length < vector.elements.length) {
            elements = Arrays.copyOf(elements, vector.elements.length);
        }

        for (int i = 0; i < vector.elements.length; i++) {
            elements[i] += vector.elements[i];
        }
    }

    public void subtract(Vector vector) {
        if (vector == null) {
            throw new IllegalArgumentException("Vector shouldn't be NULL");
        }

        if (elements.length < vector.elements.length) {
            elements = Arrays.copyOf(elements, vector.elements.length);
        }

        for (int i = 0; i < vector.elements.length; i++) {
            elements[i] -= vector.elements[i];
        }
    }

    public void multiplyByScalar(double value) {
        for (int i = 0; i < elements.length; i++) {
            elements[i] *= value;
        }
    }

    public void reverse() {
        multiplyByScalar(-1);
    }

    public static Vector getSum(Vector vector1, Vector vector2) {
        checkVectors(vector1, vector2);

        Vector resultVector = new Vector(vector1);

        resultVector.add(vector2);

        return resultVector;
    }

    public static Vector getDifference(Vector vector1, Vector vector2) {
        checkVectors(vector1, vector2);

        Vector resultVector = new Vector(vector1);

        resultVector.subtract(vector2);

        return resultVector;
    }

    public static double getScalarProduct(Vector vector1, Vector vector2) {
        checkVectors(vector1, vector2);

        double result = 0.0;
        int minSize = Math.min(vector1.elements.length, vector2.elements.length);

        for (int i = 0; i < minSize; i++) {
            result += vector1.elements[i] * vector2.elements[i];
        }

        return result;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= elements.length) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds array. Valid index values: 0.." + (elements.length - 1));
        }
    }

    private static void checkVectors(Vector vector1, Vector vector2) {
        if (vector1 == null) {
            throw new IllegalArgumentException("First argument shouldn't be NULL");
        }

        if (vector2 == null) {
            throw new IllegalArgumentException("Second argument shouldn't be NULL");
        }
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
        final int prime = 13;
        int hash = 1;

        hash = prime * hash + Arrays.hashCode(elements);

        return hash;
    }
}