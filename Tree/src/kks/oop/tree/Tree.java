package kks.oop.tree;

import java.util.*;
import java.util.function.Consumer;

public class Tree<T> {
    private Node<T> root;
    private int size = 0;
    private Comparator<? super T> comparator;

    public Tree() {

    }

    public Tree(T rootValue) {
        root = new Node<>(rootValue);
        size++;
    }

    public Tree(Comparator<? super T> comparator) {
        if (comparator == null){
            throw new NullPointerException("Comparator shouldn't be NULL");
        }

        this.comparator = comparator;
    }

    public Tree(Comparator<? super T> comparator, T rootValue) {
        if (comparator == null){
            throw new NullPointerException("Comparator shouldn't be NULL");
        }

        if (rootValue == null){
            throw new NullPointerException("Root value shouldn't be NULL");
        }

        this.comparator = comparator;
        this.root = new Node<>(rootValue);
        size++;
    }

    public int size() {
        return size;
    }

    /***
     * Input new node in root
     * @param newNode value of new Node
     */
    public boolean add(T newNode) {
        if (newNode == null) {
            throw new NullPointerException("Node shouldn't be NULL");
        }

        if (root == null) {
            root = new Node<>(newNode);
            size++;

            return true;
        }

        Node<T> node = root;

        while (true) {
            int result = compare(newNode, node.getValue());

            if (result < 0) {
                if (node.getLeftChild() != null) {
                    node = node.getLeftChild();

                    continue;
                }

                node.setLeftChild(new Node<>(newNode));
            } else {
                if (node.getRightChild() != null) {
                    node = node.getRightChild();

                    continue;
                }

                node.setRightChild(new Node<>(newNode));
            }

            break;
        }

        size++;

        return true;
    }

    public boolean contains(T value) {
        if (root == null) {
            return false;
        }

        if (value == null) {
            throw new NullPointerException("Node shouldn't be NULL");
        }

        Node<T> node = root;

        while (true) {
            int result = compare(value, node.getValue());

            if (result == 0) {
                return true;
            }

            if (result < 0) {
                if (node.getLeftChild() != null) {
                    node = node.getLeftChild();

                    continue;
                }

                return false;
            }

            if (node.getRightChild() != null) {
                node = node.getRightChild();

                continue;
            }

            return false;
        }
    }

    public boolean remove(T value) {
        if (value == null) {
            throw new NullPointerException("Node shouldn't be NULL");
        }

        if (root == null) {
            return false;
        }

        Node<T> node = root;
        Node<T> removedNode;
        Node<T> removedNodeParent = null;

        // Find Node
        while (true) {
            int result = compare(value, node.getValue());

            if (result == 0) {
                removedNode = node;
                break;
            }

            if (result < 0) {
                if (node.getLeftChild() != null) {
                    removedNodeParent = node;
                    node = node.getLeftChild();

                    continue;
                }

                return false;
            }

            if (node.getRightChild() != null) {
                removedNodeParent = node;
                node = node.getRightChild();

                continue;
            }

            return false;
        }

        // Remove leaf node (Node without children)
        if (removedNode.getLeftChild() == null && removedNode.getRightChild() == null) {
            if (removedNodeParent == null){
                root = null;
            } else {
                if (removedNodeParent.getLeftChild() == removedNode) {
                    removedNodeParent.setLeftChild(null);
                } else {
                    removedNodeParent.setRightChild(null);
                }
            }

            size--;

            return true;
        }

        // Moved to a separate line only for readability
        boolean hasOnlyLeftChild = removedNode.getLeftChild() != null && removedNode.getRightChild() == null;
        boolean hasOnlyRightChild = removedNode.getLeftChild() == null && removedNode.getRightChild() != null;

        if (hasOnlyLeftChild || hasOnlyRightChild) {
            Node<T> deleteNodeChild = removedNode.getLeftChild() != null ? removedNode.getLeftChild() : removedNode.getRightChild();

            if (removedNodeParent == null) {
                root = deleteNodeChild;
            } else if (removedNodeParent.getLeftChild() == removedNode) {
                removedNodeParent.setLeftChild(deleteNodeChild);
            } else {
                removedNodeParent.setRightChild(deleteNodeChild);
            }

            size--;

            return true;
        }

        // Remove node with two children
        Node<T> minNode = removedNode.getRightChild();
        Node<T> minNodeParent = removedNode;

        assert minNode != null;
        if (minNode.getLeftChild() != null) {
            while (minNode.getLeftChild() != null) {
                minNodeParent = minNode;
                minNode = minNode.getLeftChild();
            }

            minNodeParent.setLeftChild(minNode.getRightChild());
            minNode.setRightChild(removedNode.getRightChild());
        }

        minNode.setLeftChild(removedNode.getLeftChild());

        // Remove Root
        if (removedNodeParent == null) {
            root = minNode;
        } else {
            if (removedNodeParent.getLeftChild() == removedNode) {
                removedNodeParent.setLeftChild(minNode);
            } else {
                removedNodeParent.setRightChild(minNode);
            }
        }

        size--;

        return true;
    }

    public void breadthFirstSearch(Consumer<T> consumer){
        if (consumer == null) {
            throw new NullPointerException("Consumer shouldn't be NULL");
        }

        Queue<Node<T>> queue = new LinkedList<>();

        queue.add(this.root);

        while (!queue.isEmpty()){
            Node<T> node = queue.poll();
            consumer.accept(node.getValue());

            if (node.getLeftChild() != null) {
                queue.add(node.getLeftChild());
            }

            if (node.getRightChild() != null) {
                queue.add(node.getRightChild());
            }
        }
    }

    public void depthFirstSearch(Consumer<T> consumer) {
        if (consumer == null) {
            throw new NullPointerException("Consumer shouldn't be NULL");
        }

        Deque<Node<T>> deque = new ArrayDeque<>(size);

        deque.addLast(root);

        while (!deque.isEmpty()) {
            Node<T> node = deque.removeLast();

            consumer.accept(node.getValue());

            if (node.getLeftChild() != null) {
                deque.addLast(node.getLeftChild());
            }

            if (node.getRightChild() != null) {
                deque.addLast(node.getRightChild());
            }
        }
    }

    public void depthFirstSearchRecursive(Consumer<T> consumer){
        depthFirstSearchRecursive(root, consumer);
    }

    private void depthFirstSearchRecursive(Node<T> node, Consumer<T> consumer){
        if (node == null) return;

        consumer.accept(node.getValue());

        depthFirstSearchRecursive(node.getLeftChild(), consumer);
        depthFirstSearchRecursive(node.getRightChild(), consumer);
    }

    private int compare(T value1, T value2) {
        if (comparator == null) {
            // warning: Unchecked cast
            Comparable<? super T> comparableValue = (Comparable<? super T>) value1;

            return Integer.compare(comparableValue.compareTo(value2), 0);
        }

        return Integer.compare(comparator.compare(value1, value2), 0);
    }

    @Override
    public String toString(){
        if (root == null) return "Tree is empty";

        Deque<Node<T>> deque = new LinkedList<>();

        deque.addLast(root);

        StringBuilder sb = new StringBuilder();

        int appendedElementsCount = 0;

        while (!deque.isEmpty()) {
            Node<T> element = deque.removeLast();

            sb.append(element.getValue());

            appendedElementsCount++;

            if (appendedElementsCount != size) {
                sb.append(", ");
            }

            if (element.getRightChild() != null) {
                deque.addLast(element.getRightChild());
            }

            if (element.getLeftChild() != null) {
                deque.addLast(element.getLeftChild());
            }
        }

        return sb.toString();
    }
}
