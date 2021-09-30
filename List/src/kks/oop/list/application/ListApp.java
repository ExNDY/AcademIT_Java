package kks.oop.list.application;

import kks.oop.list.ListItem;
import kks.oop.list.MyLinkedList;

public class ListApp {
    public static void main(String[] args) {
        MyLinkedList<Integer> list = new MyLinkedList<>(new ListItem<>(5));

        System.out.println("List created: " + list);

        list.addAsFirstItem(new ListItem<>(39));

        System.out.println("Add as first item 39: " + list);

        list.addByIndex(1, new ListItem<>(2));

        System.out.println("Add by index 2: " + list);
        System.out.println("Add new item (add to the end of list) value = 65. Status:" + list.add(new ListItem<>(65)));
        System.out.println("get index = 2: " + list.get(2));
        System.out.println("set 12 index = 1: " + list.set(1, 12));
        System.out.println(list + " has size " + list.size());
        System.out.println("getFirstItem: " + list.getFirstItem());
        System.out.println("remove item by index 1. status: " + list.removeItemByIndex(1) + " was delete");
        System.out.println(list);
        System.out.println("remove first item: " + list.removeFirstItem());
        System.out.println(list);
        System.out.println("remove item with value = 13. status: " + list.removeItemByValue(13));
        System.out.println("Reflect list after add items");
        System.out.println(list);

        list.add(new ListItem<>(5));
        list.add(new ListItem<>(15));
        list.add(new ListItem<>(21));
        list.add(new ListItem<>(7));

        System.out.println(list);

        list.reflectList();

        System.out.println(list);

        MyLinkedList<Integer> secondList = list.copy();

        System.out.println("Second List after copy(): " + secondList);
    }
}
