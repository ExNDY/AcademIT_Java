package kks.oop.arraylisthome.application;

import kks.oop.arraylisthome.TaskUtils;

import java.util.ArrayList;
import java.util.Arrays;

public class ArraylistHomeApp {
    public static void main(String[] args) {
        System.out.println("Read file to ArrayList: ");

        ArrayList<String> stringsList = TaskUtils.getStringsFromFile("ArrayListHome/input.txt");

        printArrayList(stringsList);

        System.out.println("Delete even numbers from list: ");

        ArrayList<Integer> numbersList = new ArrayList<>(Arrays.asList(39, 45, 38, 94, 12, 155, 6485485, 65, 124, 4848, 65, 1, 12, 69, 45, 38, 94, 1555, 155, 17));

        System.out.println("Numbers list: " + numbersList);

        TaskUtils.deleteEvenNumbers(numbersList);

        System.out.println("Numbers list without even numbers: " + numbersList);
        System.out.println("Unique items of list: " + TaskUtils.getUniqueItems(numbersList));
    }

    private static void printArrayList(ArrayList<String> list) {
        if (list == null) {
            System.out.println("List is NULL");
        } else {
            for (String line : list) {
                System.out.println(line);
            }
        }
    }
}
