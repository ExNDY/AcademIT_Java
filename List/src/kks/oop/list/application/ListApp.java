package kks.oop.list.application;

import kks.oop.list.MyLinkedList;

public class ListApp {
    public static void main(String[] args) {
        MyLinkedList<Integer> list1 = new MyLinkedList<>(5);

        System.out.println("List created: " + list1);

        list1.addFirst(39);

        System.out.println("Add as first item 39: " + list1);

        list1.addByIndex(1, 2);

        System.out.println("Add by index = 1, value = 2: " + list1);
        System.out.println("Add new item (add to the end of list) value = 65.");

        list1.add(65);

        System.out.println("get index = 2: " + list1.get(2));
        System.out.println("set 12 index = 1: " + list1.set(1, 12));
        System.out.println(list1 + " has size " + list1.size());
        System.out.println("getFirstItem: " + list1.getFirst());
        System.out.println("remove item by index 1. status: " + list1.removeByIndex(1) + " was delete");
        System.out.println(list1);
        System.out.println("remove first item: " + list1.removeFirst());

        list1.addByIndex(0, 13);

        System.out.println(list1);
        System.out.println("remove item with value = 13. status: " + list1.removeByData(13));
        System.out.println("Reflect list after add items");
        System.out.println(list1);

        list1.add(5);
        list1.add(15);
        list1.add(21);
        list1.add(7);

        System.out.println(list1 + " before");

        list1.reverse();

        System.out.println(list1 + " after");

        MyLinkedList<Integer> list2 = list1.copy();

        System.out.println("Second List after copy(): " + list2);
    }
}
