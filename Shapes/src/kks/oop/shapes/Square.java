package kks.oop.shapes;

public class Square extends Shape {
    private final double side;

    public Square(double value) {
        this.side = value;
    }

    @Override
    public double getWidth() {
        return side;
    }

    @Override
    public double getHeight() {
        return side;
    }

    @Override
    public double getArea() {
        return side * side;
    }

    @Override
    public double getPerimeter() {
        return side * 4;
    }

    @Override
    public String toString() {
        return "Square: width = " + getWidth() + ", height = " + getHeight() + ", area: " + Math.round(getArea()) + ", perimeter: " + Math.round(getPerimeter()) + ".";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;

        if (o == null || o.getClass() != this.getClass()) return false;

        Square s = (Square) o;

        return side == s.side;
    }

    @Override
    public int hashCode() {
        final int prime = 39;
        int hashValue = 1;

        hashValue = prime * hashValue + Double.hashCode(side);

        return hashValue;
    }
}
