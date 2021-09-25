package kks.oop.vector.application;


import kks.oop.vector.Vector;

import java.util.Arrays;

public class VectorApp {
    public static void main(String[] args) {
        Vector v1 = new Vector(new double[]{85.0, 115.0, 156.0});

        System.out.println(v1);
        System.out.println("V1 size: " + v1.getSize());
        System.out.println("V1 length: " + v1.getLength());
        System.out.println("V1 get by index 2: " + v1.get(2));
        System.out.println("V1 set 110 on element with index 1: ");

        try {
            Vector emptyVector = new Vector(new double[]{});

            System.out.println(emptyVector);
        } catch (IllegalArgumentException ex) {
            System.out.println("Create of empty Vector. Status: " + ex);
        }

        Vector copiedVector = new Vector(v1);

        System.out.println("Constructor Vector: " + copiedVector);

        v1.set(1, 110.0);

        System.out.println(v1);

        double[] array = v1.getAll();

        System.out.println("Test for getAll command. Result Array: " + Arrays.toString(array));
        System.out.println("Create Vector v2:");

        Vector v2 = new Vector(new double[]{5.0, 71.0, 81.0});

        System.out.println(v2);
        System.out.println("Test add command for v1: ");

        v1.add(v2);

        System.out.println(v1);
        System.out.println("Test subtract command for v1: ");

        v1.subtract(v2);

        System.out.println(v1);
        System.out.println("Test multiplyByScalar(2.0) command for v1: ");

        v1.multiplyByScalar(2.0);

        System.out.println(v1);
        System.out.println("Test reflect command for v1: ");

        v1.reflect();

        System.out.println(v1);
        System.out.println("Test Static method getSum(v1, v2):");
        System.out.println(v1);
        System.out.println(v2);


        Vector resultVector = Vector.getSum(v1, v2);

        System.out.println(resultVector);
        System.out.println("Test Static method getSubtract(v1, v2):");
        System.out.println(v1);
        System.out.println(v2);

        resultVector = Vector.getSubtract(v1, v2);

        System.out.println(resultVector);
        System.out.println("Test Static method getScalarProduct(v1, v2):");
        System.out.println(v1);
        System.out.println(v2);

        double result = Vector.getScalarProduct(v1, v2);

        System.out.println("Scalar product: " + result);
    }
}
