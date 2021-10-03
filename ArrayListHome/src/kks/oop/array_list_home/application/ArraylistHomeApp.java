package kks.oop.array_list_home.application;

import kks.oop.array_list_home.TaskUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class ArraylistHomeApp {
    public static void main(String[] args) {
        System.out.println("Read file ArrayListHome/input.txt to ArrayList: ");

        readAndPrintFile("ArrayListHome/input.txt");

        ArrayList<Integer> numbersList = new ArrayList<>(Arrays.asList(39, 45, 38, 94, 12, 155, 6485485, 65, 124, 4848, 65, 1, 12, 69, 45, 38, 94, 1555, 155, 17));

        System.out.println("Numbers list: " + numbersList);
        System.out.println("Unique items of list: " + TaskUtils.getUniqueItems(numbersList));
        System.out.println("Delete even numbers from list: ");

        TaskUtils.deleteEvenNumbers(numbersList);

        System.out.println("Numbers list without even numbers: " + numbersList);
    }

    private static void readAndPrintFile(String path) {
        ArrayList<String> stringsList;

        try {
            stringsList = TaskUtils.getStringsFromFile(path);

            printArrayList(stringsList);
        } catch (IOException ex) {
            System.out.println("The file " + path + " cannot be read: " + ex);
        }
    }

    private static void printArrayList(ArrayList<String> list) {
        if (list == null) {
            System.out.println("List is EMPTY.");
        } else {
            for (String line : list) {
                System.out.println(line);
            }
        }
    }
}
