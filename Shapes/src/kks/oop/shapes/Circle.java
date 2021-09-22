package kks.oop.shapes;

public class Circle extends Shape {
    private final double radius;

    public Circle(double value) {
        this.radius = value;
    }

    @Override
    public double getWidth() {
        return radius * 2;
    }

    @Override
    public double getHeight() {
        return radius * 2;
    }

    @Override
    public double getArea() {
        return 2 * Math.PI * Math.pow(radius, 2);
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public String toString() {
        return "Circle: radius = " + radius + ", diameter = " + radius * 2 + ", area: " + Math.round(getArea()) + ", perimeter: " + Math.round(getPerimeter()) + ".";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;

        if (o == null || o.getClass() != this.getClass()) return false;

        return radius == ((Circle) o).radius;
    }

    @Override
    public int hashCode() {
        final int prime = 39;
        int hashValue = 1;
        return prime * hashValue + Double.hashCode(radius);
    }
}
