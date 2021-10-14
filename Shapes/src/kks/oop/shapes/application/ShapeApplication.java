package kks.oop.shapes.application;

import kks.oop.shapes.*;
import kks.oop.shapes.comparators.AreaComparator;
import kks.oop.shapes.comparators.PerimeterComparator;

import java.util.Arrays;

public class ShapeApplication {
    public static void main(String[] args) {
        System.out.println("Part 1");

        Circle circle = new Circle(4);
        Rectangle rectangle = new Rectangle(6, 2);
        Square square = new Square(4);
        Triangle triangle = new Triangle(-2, -3, 0, 5, 6, 1);

        System.out.println(circle);
        System.out.println(rectangle);
        System.out.println(square);
        System.out.println(triangle);

        System.out.println("Part 2");

        Shape[] shapes = getShapesArray();

        System.out.println("Shapes array created.");

        for (Shape s : shapes) {
            System.out.println(s);
        }

        Shape maxAreaShape = getShapeWithMaxArea(shapes);
        System.out.println("The maximum area is a " + maxAreaShape);

        Shape secondPerimeterShape = getShapeWithSecondLargestPerimeter(shapes);
        System.out.println("The second value by Perimeter is a " + secondPerimeterShape);
    }

    private static Shape[] getShapesArray() {
        return new Shape[]{
                new Circle(4),
                new Rectangle(6, 2),
                new Square(4),
                new Triangle(-2, -3, 0, 5, 6, 1),
                new Circle(7),
                new Rectangle(2, 5),
                new Square(2),
                new Triangle(-4, 2, 0, 9, 2, -3)
        };
    }

    private static Shape getShapeWithMaxArea(Shape[] shapes) {
        if (shapes == null) {
            throw new NullPointerException("Array of shapes shouldn't be NULL");
        }

        if (shapes.length == 0){
            throw new ArrayIndexOutOfBoundsException("Wrong array size! Array of shapes shouldn't be EMPTY");
        }

        if (shapes.length == 1) {
            return shapes[0];
        }

        Arrays.sort(shapes, new AreaComparator());

        return shapes[shapes.length - 1];
    }

    private static Shape getShapeWithSecondLargestPerimeter(Shape[] shapes) {
        if (shapes == null) {
            throw new NullPointerException("Array of shapes shouldn't be NULL");
        }

        if (shapes.length < 2) {
            throw new ArrayIndexOutOfBoundsException("Wrong array size! The array is less than 2 in length. Current length: " + shapes.length);
        }

        Arrays.sort(shapes, new PerimeterComparator());

        return shapes[shapes.length - 2];
    }
}
