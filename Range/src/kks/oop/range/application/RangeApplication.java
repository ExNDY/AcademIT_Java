package kks.oop.range.application;

import kks.oop.range.Range;

public class RangeApplication {
    public static void main(String[] args) {
        Range range = new Range(-4, 2);

        System.out.println("Range created: " + range);
        System.out.println("Range has size: " + range.getLength());

        int insideNumber = -2;

        calculateIsInside(range, insideNumber);

        printDivider();

        Range range1 = new Range(15.0, 32.0);
        Range range2 = new Range(7.0, 15.0);

        calculateIntersection(range1, range2);

        printDivider();

        calculateUnion(range1, range2);

        printDivider();

        calculateDifference(range1, range2);

        printDivider();
    }

    private static void calculateIsInside(Range range, int insideNumber) {
        System.out.println("Number " + insideNumber + " inside in range?");

        boolean isInside = range.isInside(insideNumber);

        System.out.println(insideNumber + " " + resultIsInsideToString(isInside));
    }

    private static void calculateIntersection(Range range1, Range range2) {
        System.out.println("Intersection:");
        System.out.println(range1);
        System.out.println(range2);

        Range intersectionRange = range1.getIntersection(range2);

        if (intersectionRange != null) {
            System.out.println("Intersection: " + intersectionRange);
        } else {
            System.out.println("Intersection not exist");
        }
    }

    private static void calculateUnion(Range range1, Range range2) {
        System.out.println("Union:");
        System.out.println(range1);
        System.out.println(range2);

        Range[] unionRanges = range1.getUnion(range2);

        printRanges(unionRanges);
    }

    private static void calculateDifference(Range range1, Range range2) {
        System.out.println("Difference:");
        System.out.println(range1);
        System.out.println(range2);

        Range[] differenceRanges = range1.getDifference(range2);

        System.out.println("Result:");

        printRanges(differenceRanges);
    }

    private static String resultIsInsideToString(boolean isInside) {
        if (isInside) {
            return "include in range";
        }

        return "exclude from range";
    }

    private static void printRanges(Range[] array) {
        System.out.print("Ranges [");

        for (int i = 0; i < array.length; i++) {
            if (i == array.length - 1) {
                System.out.print(array[i]);
            } else {
                System.out.print(array[i] + ",");
            }
        }

        System.out.println("].");
    }

    private static void printDivider() {
        System.out.println("###################");
    }
}
