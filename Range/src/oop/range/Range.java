package oop.range;

public class Range {
    private double from;
    private double to;

    public Range() {
    }

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

    public double size() {
        return to - from;
    }

    public boolean isInside(double value) {
        return (from <= value && value <= to);
    }

    public Range intersection(Range range) {
        if (range.getFrom() == to || range.getTo() == from) {
            return null;
        }

        Range newRange = new Range();

        //range inside range from start
        if (from < range.getFrom() && range.getFrom() < to) {
            newRange.setFrom(range.getFrom());

            if (from < range.getTo() && range.getTo() < to) {
                newRange.setTo(range.getTo());
            } else {
                newRange.setTo(to);
            }

            return newRange;
        }

        //range inside range only for end
        if (from < range.getTo() && range.getTo() < to) {
            newRange.setFrom(from);
            newRange.setTo(range.getTo());

            return newRange;
        }

        if (range.getFrom() < from && from < range.getTo()) {
            newRange.setFrom(from);

            if (range.getFrom() < to && to < range.getTo()) {
                newRange.setTo(to);
            } else {
                newRange.setTo(range.getTo());
            }

            return newRange;
        }

        if (range.getFrom() < to && to < range.getTo()) {
            newRange.setFrom(range.from);
            newRange.setTo(to);

            return newRange;
        }

        return null;
    }

    public Range[] merge(Range range) {
        Range[] newRange = new Range[2];

        if (isInside(range.getFrom()) && isInside(range.getTo())) {
            newRange[0] = new Range(from, to);
        } else if (isInside(range.getTo()) && !isInside(range.getFrom())) {
            newRange[0] = new Range(range.getFrom(), to);
        } else if (isInside(range.getFrom()) && !isInside(range.getTo())) {
            newRange[0] = new Range(from, range.getTo());
        } else {
            newRange[0] = new Range(from, to);
            newRange[1] = range;
        }

        return newRange;
    }
}
