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

        Range intersectionRange1 = new Range(-7.0, 19.0);
        Range intersectionRange2 = new Range(15.0, 35.0);

        calculateIntersection(intersectionRange1, intersectionRange2);

        printDivider();

        Range unionRange1 = new Range(1.0, 3.0);
        Range unionRange2 = new Range(5.0, 9.0);

        calculateUnion(unionRange1, unionRange2);

        printDivider();

        Range differenceRange1 = new Range(5.0, 7.0);
        Range differenceRange2 = new Range(3.0, 7.0);

        calculateDifference(differenceRange1, differenceRange2);

        printDivider();

        Range urange1 = new Range(1.0, 3.0);
        Range urange2 = new Range(5.0, 9.0);

        test(urange1, urange2);
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

        if (unionRanges.length == 1) {
            System.out.println("Result: " + unionRanges[0]);
        } else {
            System.out.println("Result: ");

            printRanges(unionRanges);
        }
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

    private static void test(Range range1, Range range2){
        System.out.println("TEST");
        System.out.println(range1);
        System.out.println(range2);


        double x = Math.min(range1.getFrom(), range2.getFrom());
        double y = Math.max(range1.getTo(), range2.getTo());

        if (x > y){
            System.out.println("WOW");
        } else {
            System.out.println("MEW");
        }
    }
}
