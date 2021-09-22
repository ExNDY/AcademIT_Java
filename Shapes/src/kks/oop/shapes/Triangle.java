package kks.oop.shapes;

public class Triangle extends Shape {
    private final double x1;
    private final double y1;
    private final double x2;
    private final double y2;
    private final double x3;
    private final double y3;

    /***
     * Constructor with coordinate three point on XY axis
     * @param x1 coordinate x of A point
     * @param y1 coordinate y of A point
     * @param x2 coordinate x of B point
     * @param y2 coordinate y of B point
     * @param x3 coordinate x of C point
     * @param y3 coordinate y of C point
     */
    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;
        this.y1 = y1;
        this.y2 = y2;
        this.y3 = y3;
    }

    @Override
    public double getWidth() {
        return Math.max(Math.max(x1, x2), x3) - Math.min(Math.min(x1, x2), x3);
    }

    @Override
    public double getHeight() {
        return Math.max(Math.max(y1, y2), y3) - Math.min(Math.min(y1, y2), y3);
    }

    @Override
    public double getArea() {
        double a = Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
        double b = Math.sqrt(Math.pow((x3 - x2), 2) + Math.pow((y3 - y2), 2));
        double c = Math.sqrt(Math.pow((x1 - x3), 2) + Math.pow((y1 - y3), 2));
        double p = (a + b + c) / 2;

        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }

    @Override
    public double getPerimeter() {
        double a = Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
        double b = Math.sqrt(Math.pow((x3 - x2), 2) + Math.pow((y3 - y2), 2));
        double c = Math.sqrt(Math.pow((x1 - x3), 2) + Math.pow((y1 - y3), 2));

        return a + b + c;
    }

    @Override
    public String toString() {
        return "Triangle: width = " + getWidth() + ", height = " + getHeight() + ", area: " + Math.round(getArea()) + ", perimeter: " + Math.round(getPerimeter()) + ".";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;

        if (o == null || o.getClass() != this.getClass()) return false;

        Triangle t = (Triangle) o;

        return x1 == t.x1 && y1 == t.y1
                && x2 == t.x2 && y2 == t.y2
                && x3 == t.x3 && y3 == t.y3;
    }

    @Override
    public int hashCode() {
        final int prime = 39;
        int hashValue = 1;

        hashValue = prime * hashValue + Double.hashCode(x1);
        hashValue = prime * hashValue + Double.hashCode(x2);
        hashValue = prime * hashValue + Double.hashCode(x3);
        hashValue = prime * hashValue + Double.hashCode(y1);
        hashValue = prime * hashValue + Double.hashCode(y2);
        hashValue = prime * hashValue + Double.hashCode(y3);

        return hashValue;
    }
}
