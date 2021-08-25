package kcp.oop.application;

import kcp.oop.range.Range;

public class Main {
    public static void main(String[] args) {
        Range range = new Range(-4, 2);

        System.out.println("Range created: from " + range.getFrom() + " to " + range.getTo());
        System.out.println("Range has size: " + range.getLength());

        double insideNumber = -2;

        System.out.println("Number " + insideNumber + " inside in range?");

        boolean isInside = range.isInside(insideNumber);

        if (isInside) {
            System.out.println(insideNumber + " include in range");
        } else {
            System.out.println(insideNumber + " exclude from range");
        }

        System.out.println("-----Base task - complete-----");
        System.out.println("Intersection:");

        Range intersectionRange1 = new Range(-7.0, 15.0);
        Range intersectionRange2 = new Range(15.0, 35.0);

        Range intersectionRange = intersectionRange1.getIntersection(intersectionRange2);

        System.out.println("Range 1 = [" + intersectionRange1.getFrom() + ";" + intersectionRange1.getTo()
                + "] Range 2 = [" + intersectionRange2.getFrom() + ";" + intersectionRange2.getTo() + "]");

        if (intersectionRange != null) {
            System.out.println("[" + intersectionRange.getFrom() + ";" + intersectionRange.getTo() + "]");
        } else {
            System.out.println("Intersection not exist");
        }

        System.out.println("Union:");

        Range unionRange1 = new Range(2.0, 5.0);
        Range unionRange2 = new Range(-6.0, 9.0);

        Range[] unionRangeArray = unionRange1.getUnion(unionRange2);

        System.out.println("Range 1 = [" + unionRange1.getFrom() + ";" + unionRange1.getTo()
                + "] Range 2 = [" + unionRange2.getFrom() + ";" + unionRange2.getTo() + "]");

        if (unionRangeArray.length == 1) {
            System.out.println("Union range: [" + unionRangeArray[0].getFrom() + ";" + unionRangeArray[0].getTo() + "]");
        } else {
            System.out.println("Union not exist. Result: first range ["
                    + unionRangeArray[0].getFrom() + ";" + unionRangeArray[0].getTo()
                    + "] second range: [" + unionRangeArray[1].getFrom() + ";" + unionRangeArray[1].getTo() + "]");
        }

        System.out.println("Difference:");

        Range differenceRange1 = new Range(1.0, 10.0);
        Range differenceRange2 = new Range(5.0, 9.0);

        Range[] differenceRangeArray = differenceRange1.getDifference(differenceRange2);

        System.out.println("Range 1 = [" + differenceRange1.getFrom() + ";" + differenceRange1.getTo()
                + "] Range 2 = [" + differenceRange2.getFrom() + ";" + differenceRange2.getTo() + "]");

        if (differenceRangeArray.length == 0) {
            System.out.println("Interval don't overlap. Difference not exist");
        }

        if (differenceRangeArray.length == 1) {
            System.out.println("Result: " + differenceRangeArray[0].getFrom() + ";" + differenceRangeArray[0].getTo());
        }

        if (differenceRangeArray.length == 2) {
            System.out.println("Result. Range 1 = [" + differenceRangeArray[0].getFrom() + ";" + differenceRangeArray[0].getTo() + "]"
                    + " Range 2 = [" + differenceRangeArray[1].getFrom() + ";" + differenceRangeArray[1].getTo() + "]");
        }
    }
}
