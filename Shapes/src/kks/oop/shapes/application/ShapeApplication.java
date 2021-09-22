package kks.oop.shapes.application;

import kks.oop.shapes.*;

import java.util.Arrays;
import java.util.Comparator;

public class ShapeApplication {
    public static void main(String[] args) {
        System.out.println("Part 1");

        Circle circle = new Circle(4);
        Rectangle rectangle = new Rectangle(6, 2);
        Square square = new Square(4);
        Triangle triangle = new Triangle(-2, -3, 0, 5, 6, 1);

        printShapeInfo(circle);

        printShapeInfo(rectangle);

        printShapeInfo(square);

        printShapeInfo(triangle);

        System.out.println("Part 2");

        Shape[] shapes = getShapeList();

        System.out.println("Shapes array created.");

        Shape maxAreaShape = getShapeWithMaxArea(shapes);

        System.out.println("The maximum area is a " + defineShape(maxAreaShape));
        printShapeInfo(maxAreaShape);

        Shape secondByPerimeterShape = getShapeSecondLargestOfPerimeter(shapes);
        System.out.println("The second value by Perimeter is a " + defineShape(secondByPerimeterShape));
        printShapeInfo(secondByPerimeterShape);
    }

    private static void printShapeInfo(Shape shape) {
        System.out.println("Shape: " + defineShape(shape) + " info: " + shape.toString());
    }

    private static String defineShape(Shape shape) {
        if (shape instanceof Circle) {
            return "Circle";
        }
        if (shape instanceof Rectangle) {
            return "Rectangle";
        }
        if (shape instanceof Square) {
            return "Square";
        }
        if (shape instanceof Triangle) {
            return "Triangle";
        }

        return "Shape not identify";
    }

    private static Shape[] getShapeList() {
        Shape[] shapes = new Shape[8];

        shapes[0] = new Circle(4);
        shapes[1] = new Rectangle(6, 2);
        shapes[2] = new Square(4);
        shapes[3] = new Triangle(-2, -3, 0, 5, 6, 1);
        shapes[4] = new Circle(7);
        shapes[5] = new Rectangle(2, 5);
        shapes[6] = new Square(2);
        shapes[7] = new Triangle(-4, 2, 0, 9, 2, -3);

        return shapes;
    }

    private static Shape getShapeWithMaxArea(Shape[] shapes) {
        Arrays.sort(shapes,
                Comparator.comparingDouble(Shape::getArea));

        System.out.println("Sorted array of shapes by Area field:");

        for (Shape shape : shapes) {
            printShapeInfo(shape);
        }

        return shapes[shapes.length - 1];
    }

    private static Shape getShapeSecondLargestOfPerimeter(Shape[] shapes) {
        Arrays.sort(shapes,
                Comparator.comparingDouble(Shape::getPerimeter));

        System.out.println("Sorted array of shapes by Perimeter field:");

        for (Shape shape : shapes) {
            printShapeInfo(shape);
        }

        return shapes[shapes.length - 2];
    }
}
