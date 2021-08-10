package range.application;

import oop.range.Range;

public class Main {
    public static void main(String[] args) {
        Range range = new Range(-4, 2);

        System.out.println("Range created: from " + range.getFrom() + " to " + range.getTo());
        System.out.println("Range has size: " + range.size());

        double insideNumber = -2;

        System.out.println("Number " + insideNumber + " inside in range?");

        boolean isInside = range.isInside(insideNumber);

        if (isInside) {
            System.out.println(insideNumber + " include in range");
        } else {
            System.out.println(insideNumber + " exclude from range");
        }

        System.out.println("-----Base task - complete-----");
        System.out.println("Intersection");

        Range secondRange = new Range(-8.0, -2.0);
        Range newRange = range.intersection(secondRange);

        if (newRange != null) {
            System.out.println(newRange.getFrom() + "|" + newRange.getTo());
        } else {
            System.out.println("Intersection not exist");
        }

        System.out.println("Merge");

        Range[] rangeArray = range.merge(secondRange);

        if (rangeArray[1] == null) {
            System.out.println("Merged range: [" + rangeArray[0].getFrom() + ";" + rangeArray[0].getTo() + "]");
        } else {
            System.out.println("Merge not exist. Result: first range ["
                    + rangeArray[0].getFrom() + ";" + rangeArray[0].getTo()
                    + "] second range: [" + rangeArray[1].getFrom() + ";" + rangeArray[1].getTo() + "]");
        }

        System.out.println("Difference:");

        rangeArray = range.difference(secondRange);

        if (rangeArray == null) {
            System.out.println("Interval don't overlap. Difference not exist.");
        } else {
            if (rangeArray[1] != null) {
                System.out.println("First range: " + rangeArray[0].getFrom() + ";" + rangeArray[0].getTo()
                        + " second range: " + rangeArray[1].getFrom() + ";" + rangeArray[1].getTo());
            } else {
                System.out.println("Result: " + rangeArray[0].getFrom() + ";" + rangeArray[0].getTo());
            }
        }
    }
}
