package kks.oop.shapes.application;

import kks.oop.Range;

public class Main {
    public static void main(String[] args) {
        Range range = new Range(-4, 2);

        System.out.println("Range created:");
        System.out.println(range);
        System.out.println("Range has size: " + range.getLength());

        double insideNumber = -2;

        System.out.println("Number " + insideNumber + " inside in range?");

        boolean isInside = range.isInside(insideNumber);

        System.out.println(insideNumber + " " + resultIsInsideToString(isInside));

        System.out.println("-----Base task - complete-----");
        System.out.println("Intersection:");

        Range intersectionRange1 = new Range(-7.0, 15.0);
        Range intersectionRange2 = new Range(15.0, 35.0);

        System.out.println(intersectionRange1);
        System.out.println(intersectionRange2);

        Range intersectionRange = intersectionRange1.getIntersection(intersectionRange2);

        printResultIntersection(intersectionRange);

        System.out.println("Union:");

        Range unionRange1 = new Range(2.0, 5.0);
        Range unionRange2 = new Range(-6.0, 9.0);

        System.out.println(unionRange1);
        System.out.println(unionRange2);

        Range[] unionRanges = unionRange1.getUnion(unionRange2);

        printResultUnionRanges(unionRanges);

        System.out.println("Difference:");

        Range differenceRange1 = new Range(1.0, 10.0);
        Range differenceRange2 = new Range(5.0, 9.0);

        System.out.println(differenceRange1);
        System.out.println(differenceRange2);

        Range[] differenceRanges = differenceRange1.getDifference(differenceRange2);

        printResultDifferenceRanges(differenceRanges);
    }

    private static void printRanges(Range[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println("Range " + (i + 1) + ": " + array[i]);
        }
    }

    private static String resultIsInsideToString(boolean isInside) {
        if (isInside) {
            return "include in range";
        }

        return "exclude from range";
    }

    private static void printResultIntersection(Range range) {
        if (range != null) {
            System.out.println("Intersection: " + range);
        } else {
            System.out.println("Intersection not exist");
        }
    }

    private static void printResultUnionRanges(Range[] array) {
        if (array.length == 1) {
            System.out.println("Union: " + array[0]);
        } else {
            System.out.println("Union not exist. Result:");
            System.out.println(array[0] + ", " + array[1]);
        }
    }

    private static void printResultDifferenceRanges(Range[] array) {
        switch (array.length) {
            case 0 -> System.out.println("Interval don't overlap. Difference not exist");
            case 1 -> System.out.println("Result: " + array[0]);
            case 2 -> System.out.println("Result:" + array[0]
                    + ", " + array[1]);
            default -> System.out.println("Error.");
        }
    }
}
