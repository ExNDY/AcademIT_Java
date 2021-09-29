package kks.oop.shapes;

public class Rectangle implements Shape {
    private final double width;
    private final double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public double getArea() {
        return width * height;
    }

    @Override
    public double getPerimeter() {
        return 2 * (width + height);
    }

    @Override
    public String toString() {
        return "Rectangle: width = " + width + ", height = " + height + ", area: " + getArea() + ", perimeter: " + getPerimeter() + ".";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }

        Rectangle r = (Rectangle) o;

        return this.width == r.width && this.height == r.height;
    }

    @Override
    public int hashCode() {
        final int prime = 13;
        int hashValue = 1;

        hashValue = prime * hashValue + Double.hashCode(width);
        hashValue = prime * hashValue + Double.hashCode(height);

        return hashValue;
    }
}
