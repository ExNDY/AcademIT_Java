package kcp.oop.range;

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
            intersectionFrom = Double.max(from, range.from);

            intersectionTo = Double.min(to, range.to);
        }

        if (intersectionFrom == intersectionTo) {
            return null;
        } else {
            return new Range(intersectionFrom, intersectionTo);
        }
    }

    public Range[] getUnion(Range range) {
        if ((from <= range.from && range.from <= to) || (range.from <= from && from <= range.to)) {
            Range[] array = new Range[1];

            array[0] = new Range(Double.min(from, range.from), Double.max(to, range.to));

            return array;
        }

        if (range.from < to || range.to < from) {
            Range[] array = new Range[2];

            array[0] = new Range(from, to);
            array[1] = new Range(range.from, range.to);

            return array;
        }

        return null;
    }

    public Range[] getDifference(Range range) {
        if (from <= range.from && range.to <= to) {
            Range[] differenceRangesArray = new Range[2];

            differenceRangesArray[0] = new Range(from, range.from);
            differenceRangesArray[1] = new Range(range.to, to);

            return differenceRangesArray;
        }

        if (from <= range.from && range.from < to) {
            Range[] differenceRangesArray = new Range[1];

            differenceRangesArray[0] = new Range(from, range.from);
            return differenceRangesArray;
        }

        return new Range[0];
    }
}
