package kks.oop.hashtable.application;

import kks.oop.hashtable.MyHashTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HashTableApp {
    public static void main(String[] args) {
        MyHashTable<String> hashTable = new MyHashTable<>(5);

        hashTable.add("First item");
        hashTable.add("Second Item");
        hashTable.add("Third item");
        hashTable.add("Item for increase capacity");

        System.out.println("Create HashTable, add item. Result: ");

        printHashTable(hashTable);

        System.out.println("//////////////////////////");
        System.out.println("HashTable size = " + hashTable.size());
        System.out.println("HashTable is empty = " + hashTable.isEmpty());
        System.out.println("HashTable contains \"First item\" = " + hashTable.contains("First item"));
        System.out.println("HashTable remove \"Third item\" = " + hashTable.remove("Third item"));

        printHashTable(hashTable);

        String[] array = new String[1];

        array = hashTable.toArray(array);

        System.out.println("HashTable toArray(E[] array)");

        for (String string: array){
            System.out.println(string);
        }

        System.out.println("Retain array : " + hashTable.retainAll(Arrays.asList(array)));

        List<String> list = new ArrayList<>();

        System.out.println("Retain empty list: " + hashTable.retainAll(list));
        System.out.println("//////////////////////////");
        System.out.println("Clear HashTable:");

        hashTable.clear();

        printHashTable(hashTable);

        System.out.println("Add items");

        hashTable.add("Fourth item");
        hashTable.add("Fifth item");

        printHashTable(hashTable);

        System.out.println("Delete from HashTable: \"Fourth item\", \"List item\"");

        hashTable.removeAll(Arrays.asList("Fourth item", "List item"));

        printHashTable(hashTable);

        System.out.println("Add array to HashTable: " + hashTable.addAll(Arrays.asList("Fourth item", "List item")));
        System.out.println("ContainsAll: " + hashTable.containsAll(Arrays.asList("Fourth item", "List item")));

        printHashTable(hashTable);

        System.out.println("Iterator:");

        for (String item: hashTable){
            System.out.println(item);
        }
    }

    private static void printHashTable(MyHashTable<String> table){
        System.out.println("___________________");
        System.out.println(table);
        System.out.println("-------------------");
    }
}
