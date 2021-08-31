package kks.oop.shapes_application;

import kks.oop.shapes.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        System.out.println("Part 1");

        Circle circle = new Circle(4);
        Rectangle rectangle = new Rectangle(6, 2);
        Square square = new Square(4);
        Triangle triangle = new Triangle(-2, -3, 0, 5, 6, 1);

        System.out.println("Circle"
                + " width: " + circle.getWidth()
                + " height: " + circle.getHeight()
                + " area: " + Math.round(circle.getArea())
                + " perimeter: " + Math.round(circle.getPerimeter()));
        System.out.println("Rectangle"
                + " width: " + rectangle.getWidth()
                + " height: " + rectangle.getHeight()
                + " area: " + Math.round(rectangle.getArea())
                + " perimeter: " + Math.round(rectangle.getPerimeter()));
        System.out.println("Square"
                + " width: " + square.getWidth()
                + " height: " + square.getHeight()
                + " area: " + Math.round(square.getArea())
                + " perimeter: " + Math.round(square.getPerimeter()));
        System.out.println("Triangle"
                + " width: " + triangle.getWidth()
                + " height: " + triangle.getHeight()
                + " area: " + Math.round(triangle.getArea())
                + " perimeter: " + Math.round(triangle.getPerimeter()));

        System.out.println("Part 2");

        ArrayList<Shape> figureList = getFiguresList();

        if (figureList.size() != 0){

        }
    }

    private static ArrayList<Shape> getFiguresList(){
        ArrayList<Shape> list = new ArrayList<>();

        Circle circle1 = new Circle(4);
        Rectangle rectangle1 = new Rectangle(6, 2);
        Square square1 = new Square(4);
        Triangle triangle1 = new Triangle(-2, -3, 0, 5, 6, 1);
        Circle circle2 = new Circle(7);
        Rectangle rectangle2 = new Rectangle(2, 5);
        Square square2 = new Square(2);
        Triangle triangle2 = new Triangle(-4, 2, 0, 9, 2, -3);

        list.add(circle1);
        list.add(circle2);
        list.add(rectangle1);
        list.add(rectangle2);
        list.add(square1);
        list.add(square2);
        list.add(triangle1);
        list.add(triangle2);

        return list;
    }

    private static Shape getMaxFigure(ArrayList<Shape> list){
        /*Arrays.sort(list, new Comparator<Shape>() {
            @Override
            public int compare(Shape s1, Shape s2) {
                //if (s1.getArea())
                //return s1.getArea() > s2.getArea();
            }
        })*/
        //list.sort();
        return null;
    }
}
