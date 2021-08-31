package kks.oop.range;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public double getLength() {
        return to - from;
    }

    public boolean isInside(double value) {
        return from <= value && value <= to;
    }

    public Range getIntersection(Range range) {
        double intersectionFrom = 0.0;
        double intersectionTo = 0.0;

        if ((from <= range.from && range.from <= to) || (range.from <= from && from <= range.to)) {
            intersectionFrom = Math.max(from, range.from);

            intersectionTo = Math.min(to, range.to);
        }

        if (intersectionFrom != intersectionTo) {
            return new Range(intersectionFrom, intersectionTo);
        }

        return null;
    }

    public Range[] getUnion(Range range) {
        if ((from <= range.from && range.from <= to) || (range.from <= from && from <= range.to)) {
            return new Range[]{new Range(Math.min(from, range.from), Math.max(to, range.to))};
        }

        return new Range[]{new Range(from, to), new Range(range.from, range.to)};
    }

    public Range[] getDifference(Range range) {
        if (from >= range.to || to <= range.from) {
            return new Range[]{new Range(from, to)};
        }

        if (from < range.from && range.to < to) {
            return new Range[]{new Range(from, range.from), new Range(range.to, to)};
        }

        if (from < range.to && range.to < to) {
            return new Range[]{new Range(range.to, to)};
        }

        if (to > range.from && range.to >= to && from != range.from) {
            return new Range[]{new Range(from, range.from)};
        }

        return new Range[0];
    }

    @Override
    public String toString() {
        return "(" + from +
                "; " + to +
                ')';
    }
}