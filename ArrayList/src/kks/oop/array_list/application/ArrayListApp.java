package kks.oop.array_list.application;

import kks.oop.array_list.ArrayList;

import java.util.Arrays;
import java.util.LinkedList;

public class ArrayListApp {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>(6);

        list.add(404);
        list.add(303);
        list.add(101);
        list.add(505);
        list.add(1);

        System.out.println("List of ArrayList: " + list);
        System.out.println("ArrayList has size: " + list.size());
        System.out.println("List is empty? Answer: " + list.isEmpty());

        int index = 2;
        System.out.println("Element by index = " + index + ": " + list.get(index));
        System.out.println("Replace by index = " + index + " on: \"7\" " + list.set(index, 7) + " was replaced");
        System.out.println("List of ArrayList: " + list);

        System.out.println("List contains: \"101\": " + list.contains(101));
        System.out.println("Remove 505: " + list.remove(Integer.valueOf(505)));
        System.out.println("List of ArrayList: " + list);

        Integer[] array = {11, 25, 36, 45, 99};
        LinkedList<Integer> linkedList = new LinkedList<>(Arrays.asList(array));

        System.out.println("AddAll array in list. Result: " + list.addAll(linkedList));
        System.out.println("List of ArrayList: " + list);
        System.out.println("Delete all array elements from list. Result: " + list.removeAll(linkedList));
        System.out.println("List of ArrayList: " + list);
        System.out.println("Get index of 101 (indexOf): " + list.indexOf(101));
        System.out.println("Get index of 303 (lastIndexOf): " + list.lastIndexOf(303));

        Integer[] a = list.toArray(new Integer[]{5, 25, 39});

        System.out.println("List to Array: Array = " + Arrays.toString(a));
        System.out.println("Iterator use: ");

        for (Integer integer : list) {
            System.out.print(integer + " | ");
        }
    }
}
