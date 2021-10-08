package kks.oop.list.application;

import kks.oop.list.MyLinkedList;

public class ListApp {
    public static void main(String[] args) {
        MyLinkedList<Integer> list = new MyLinkedList<>(5);

        System.out.println("List created: " + list);

        list.addFirst(39);

        System.out.println("Add as first item 39: " + list);

        list.addByIndex(1, 2);

        System.out.println("Add by index = 1, value = 2: " + list);
        System.out.println("Add new item (add to the end of list) value = 65.");

        list.add(65);

        System.out.println("get index = 2: " + list.get(2));
        System.out.println("set 12 index = 1: " + list.set(1, 12));
        System.out.println(list + " has size " + list.size());
        System.out.println("getFirstItem: " + list.getFirst());
        System.out.println("remove item by index 1. status: " + list.removeByIndex(1) + " was delete");
        System.out.println(list);
        System.out.println("remove first item: " + list.removeFirst());

        list.addByIndex(0, 13);

        System.out.println(list);
        System.out.println("remove item with value = 13. status: " + list.removeByData(13));
        System.out.println("Reflect list after add items");
        System.out.println(list);

        list.add(5);
        list.add(15);
        list.add(21);
        list.add(7);

        System.out.println(list + " before");

        list.reverse();

        System.out.println(list + " after");

        MyLinkedList<Integer> secondList = list.copy();

        System.out.println("Second List after copy(): " + secondList);
    }
}
