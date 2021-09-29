package kks.oop.shapes;

public class Square implements Shape {
    private final double sideLength;

    public Square(double sideLength) {
        this.sideLength = sideLength;
    }

    public double getSideLength() {
        return sideLength;
    }

    @Override
    public double getWidth() {
        return sideLength;
    }

    @Override
    public double getHeight() {
        return sideLength;
    }

    @Override
    public double getArea() {
        return sideLength * sideLength;
    }

    @Override
    public double getPerimeter() {
        return sideLength * 4;
    }

    @Override
    public String toString() {
        return "Square: Side length = " + sideLength + ", area: " + getArea() + ", perimeter: " + getPerimeter() + ".";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }

        Square s = (Square) o;

        return this.sideLength == s.sideLength;
    }

    @Override
    public int hashCode() {
        final int prime = 13;
        int hashValue = 1;

        hashValue = prime * hashValue + Double.hashCode(sideLength);

        return hashValue;
    }
}
