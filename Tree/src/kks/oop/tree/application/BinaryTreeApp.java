package kks.oop.tree.application;

import kks.oop.tree.Tree;

import java.util.Arrays;
import java.util.function.Consumer;

public class BinaryTreeApp {
    public static void main(String[] args) {
        Tree<Integer> binaryTree = new Tree<>(39);

        System.out.println("Binary tree with root = 39 created: " + binaryTree);
        System.out.println("Add array (data of node) in tree: ");

        int[] array = {7, 12, 45, 17, 135, 84, 3, 65, 48};

        System.out.println("Array: " + Arrays.toString(Arrays.stream(array).toArray()));

        for (int element : array) {
            binaryTree.add(element);
        }

        System.out.println("Binary tree: " + binaryTree);
        System.out.println("Add single node (value = 13) status: " + binaryTree.add(13));
        System.out.println("Binary tree: " + binaryTree + ", has size: " + binaryTree.size());
        System.out.println("Tree has node with value = 39? Status: " + binaryTree.contains(13));
        System.out.println("Remove node with value = 75: " + binaryTree.remove(17));
        System.out.println("Binary tree: " + binaryTree + ". Tree has size: " + binaryTree.size());

        printDivider();

        Consumer<Integer> printer = System.out::println;

        System.out.println("Breadth-First Search");

        binaryTree.breadthFirstSearch(printer);

        printDivider();

        System.out.println("Depth-First search");

        binaryTree.depthFirstSearch(printer);

        printDivider();

        System.out.println("Depth-First search (recursive)");

        binaryTree.depthFirstSearchRecursive(printer);
    }

    private static void printDivider() {
        System.out.println("---------------------");
    }
}
