package kks.oop.shapes;

public class Square extends Shape{
    private double height;

    public Square() {
    }

    public Square(double value) {
        this.height = value;
    }

    @Override
    public double getWidth() {
        return height;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public double getArea() {
        return height * height;
    }

    @Override
    public double getPerimeter() {
        return height * 4;
    }
}
