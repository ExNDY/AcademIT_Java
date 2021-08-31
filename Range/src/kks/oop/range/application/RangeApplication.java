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

        calculateIntersection();

        printDivider();

        calculateUnion();

        printDivider();

        calculateDifference();

        printDivider();
    }

    private static void calculateIsInside(Range range, int insideNumber) {
        System.out.println("Number " + insideNumber + " inside in range?");

        boolean isInside = range.isInside(insideNumber);

        System.out.println(insideNumber + " " + resultIsInsideToString(isInside));
    }

    private static void calculateIntersection() {
        System.out.println("Intersection:");

        Range intersectionRange1 = new Range(-7.0, 19.0);
        Range intersectionRange2 = new Range(15.0, 35.0);

        System.out.println(intersectionRange1);
        System.out.println(intersectionRange2);

        Range intersectionRange = intersectionRange1.getIntersection(intersectionRange2);

        printResultIntersection(intersectionRange);
    }

    private static void calculateUnion() {
        System.out.println("Union:");

        Range unionRange1 = new Range(1.0, 3.0);
        Range unionRange2 = new Range(5.0, 9.0);

        System.out.println(unionRange1);
        System.out.println(unionRange2);

        Range[] unionRanges = unionRange1.getUnion(unionRange2);

        printResultUnionRanges(unionRanges);
    }

    private static void calculateDifference() {
        System.out.println("Difference:");

        Range differenceRange1 = new Range(5.0, 7.0);
        Range differenceRange2 = new Range(1.0, 3.0);

        System.out.println(differenceRange1);
        System.out.println(differenceRange2);

        Range[] differenceRanges = differenceRange1.getDifference(differenceRange2);

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

            printRanges(array);
        }
    }

    private static void printDivider() {
        System.out.println("###################");
    }
}
