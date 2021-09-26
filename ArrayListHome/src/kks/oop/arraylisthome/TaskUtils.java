package kks.oop.arraylisthome;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskUtils {
    public static ArrayList<String> getStringsFromFile(String path) {
        try (Scanner s = new Scanner(new FileInputStream(path))) {
            ArrayList<String> list = new ArrayList<>();

            while (s.hasNextLine()) {
                list.add(s.nextLine());
            }

            return list;
        } catch (FileNotFoundException ex) {
            System.out.println("FileNotFoundException: " + ex);

            return null;
        }
    }

    public static void deleteEvenNumbers(ArrayList<Integer> list) {
        if (list == null) {
            throw new NullPointerException("List shouldn't be NULL");
        }

        for (int i = list.size() - 1; i != 0; i--) {
            if (list.get(i) % 2 == 0) {
                list.remove(i);
            }
        }
    }

    public static ArrayList<Integer> getUniqueItems(ArrayList<Integer> list) {
        if (list == null) {
            throw new NullPointerException("List shouldn't be NULL");
        }

        ArrayList<Integer> uniqueItemsList = new ArrayList<>();

        for (Integer item : list) {
            if (!uniqueItemsList.contains(item)) {
                uniqueItemsList.add(item);
            }
        }

        return uniqueItemsList;
    }
}
