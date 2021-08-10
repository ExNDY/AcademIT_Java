package range.application;

import oop.range.Range;

public class Main {
    public static void main(String[] args) {
        System.out.println("Create range");

        Range range = new Range(-4.2, 2.5);

        System.out.println("Range created: from " + range.getFrom() + " to " + range.getTo());
        System.out.println("Range has size: " + range.size());

        double insideNumber =-4.25;

        System.out.println("Number " + insideNumber + " inside in range?");

        boolean isInside = range.isInside(insideNumber);

        if (isInside) {
            System.out.println(insideNumber + " include in range");
        } else {
            System.out.println(insideNumber + " exclude from range");
        }

        System.out.println("-----Base task was tested-----");
        System.out.println("Intersection");

        Range secondRange = new Range (2.4, 9);
        Range newRange = range.intersection(secondRange);

        if (newRange != null){
            System.out.println(newRange.getFrom() + "|" + newRange.getTo());
        } else {
            System.out.println("Intersection not exist");
        }

        System.out.println("Merge of range");

        Range[] rangeArray = range.merge(secondRange);

        if (rangeArray[1] == null){
            System.out.println("Merged range: [" + rangeArray[0].getFrom() + ";" + rangeArray[0].getTo() + "]");
        } else {
            System.out.println("Merge not exist. Result: " + rangeArray[0] + ";" + rangeArray[1]);
        }

    }
}
