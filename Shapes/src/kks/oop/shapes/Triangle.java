package kks.oop.shapes;

public class Triangle implements Shape {
    private final double x1;
    private final double y1;
    private final double x2;
    private final double y2;
    private final double x3;
    private final double y3;

    public double getX1() {
        return x1;
    }

    public double getY1() {
        return y1;
    }

    public double getX2() {
        return x2;
    }

    public double getY2() {
        return y2;
    }

    public double getX3() {
        return x3;
    }

    public double getY3() {
        return y3;
    }

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

    public double getSideABLength(){
        return Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
    }

    public double getSideBCLength(){
        return Math.sqrt(Math.pow((x3 - x2), 2) + Math.pow((y3 - y2), 2));
    }

    public double getSideCALength(){
        return Math.sqrt(Math.pow((x1 - x3), 2) + Math.pow((y1 - y3), 2));
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
        double semiPerimeter = (getSideABLength() + getSideBCLength() + getSideCALength()) / 2;

        return Math.sqrt(semiPerimeter * (semiPerimeter - getSideABLength()) * (semiPerimeter - getSideBCLength()) * (semiPerimeter - getSideCALength()));
    }

    @Override
    public double getPerimeter() {
        return getSideABLength() + getSideBCLength() + getSideCALength();
    }

    @Override
    public String toString() {
        return "Triangle: A(" + x1 + ";" + y1 +"), B(" + x2 + ";" + y2 + "), C(" + x3 + ";" + y3 + "). Area = " + getArea() + ", perimeter = " + getPerimeter() + ".";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }

        Triangle t = (Triangle) o;

        return this.x1 == t.x1 && this.y1 == t.y1
                && this.x2 == t.x2 && this.y2 == t.y2
                && this.x3 == t.x3 && this.y3 == t.y3;
    }

    @Override
    public int hashCode() {
        final int prime = 13;
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
