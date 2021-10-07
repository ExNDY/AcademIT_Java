package kks.oop.array_list_home;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TaskUtils {
    public static ArrayList<String> getStringsFromFile(String path) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            ArrayList<String> list = new ArrayList<>();
            String string;

            while ((string = reader.readLine()) != null) {
                list.add(string);
            }

            return list;
        }
    }

    public static void deleteEvenNumbers(ArrayList<Integer> list) {
        if (list == null) {
            throw new NullPointerException("List shouldn't be NULL");
        }

        for (int i = list.size() - 1; i >= 0; i--) {
            if (list.get(i) % 2 == 0) {
                list.remove(i);
            }
        }
    }

    public static ArrayList<Integer> getUniqueItems(ArrayList<Integer> list) {
        if (list == null) {
            throw new NullPointerException("List shouldn't be NULL");
        }

        ArrayList<Integer> uniqueItemsList = new ArrayList<>(list.size());

        for (Integer item : list) {
            if (!uniqueItemsList.contains(item)) {
                uniqueItemsList.add(item);
            }
        }

        return uniqueItemsList;
    }
}
