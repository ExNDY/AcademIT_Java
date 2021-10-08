package kks.oop.tree;

import java.util.*;
import java.util.function.Consumer;

public class Tree<T> {
    private Node<T> root;
    private int size;
    private Comparator<? super T> comparator;

    public Tree() {

    }

    public Tree(T rootValue) {
        root = new Node<>(rootValue);
        size++;
    }

    public Tree(Comparator<? super T> comparator) {
        this.comparator = comparator;
    }

    public Tree(Comparator<? super T> comparator, T rootValue) {
        this.comparator = comparator;
        this.root = new Node<>(rootValue);
        size++;
    }

    public int size() {
        return size;
    }

    /***
     * Input new node in root
     * @param value value of new Node
     */
    public boolean add(T value) {
        if (root == null) {
            root = new Node<>(value);
            size++;

            return true;
        }

        Node<T> node = root;

        while (true) {
            int result = compare(value, node.getValue());

            if (result < 0) {
                if (node.getLeftChild() != null) {
                    node = node.getLeftChild();

                    continue;
                }

                node.setLeftChild(new Node<>(value));
            } else {
                if (node.getRightChild() != null) {
                    node = node.getRightChild();

                    continue;
                }

                node.setRightChild(new Node<>(value));
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
            if (removedNodeParent == null) {
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

        // Remove node with one child
        if (removedNode.getLeftChild() == null || removedNode.getRightChild() == null) {
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

    public void breadthFirstSearch(Consumer<T> consumer) {
        if (root == null) {
            return;
        }

        Queue<Node<T>> queue = new LinkedList<>();

        queue.add(root);

        while (!queue.isEmpty()) {
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
        if (root == null) {
            return;
        }

        Deque<Node<T>> deque = new ArrayDeque<>(size);

        deque.addFirst(root);

        while (!deque.isEmpty()) {
            Node<T> node = deque.removeFirst();

            consumer.accept(node.getValue());

            if (node.getLeftChild() != null) {
                deque.addFirst(node.getLeftChild());
            }

            if (node.getRightChild() != null) {
                deque.addFirst(node.getRightChild());
            }
        }
    }

    public void depthFirstSearchRecursive(Consumer<T> consumer) {
        depthFirstSearchRecursive(root, consumer);
    }

    private void depthFirstSearchRecursive(Node<T> node, Consumer<T> consumer) {
        if (node == null) {
            return;
        }

        consumer.accept(node.getValue());

        depthFirstSearchRecursive(node.getLeftChild(), consumer);
        depthFirstSearchRecursive(node.getRightChild(), consumer);
    }

    private int compare(T value1, T value2) {
        if (comparator == null) {
            if (value1 != null && value2 != null) {
                //noinspection unchecked
                return ((Comparable<T>) value1).compareTo(value2);
            }

            if (value1 == null && value2 == null) {
                return 0;
            }

            if (value1 == null) {
                return -1;
            }

            return 1;
        }

        return comparator.compare(value1, value2);
    }

    @Override
    public String toString() {
        if (root == null) {
            return "Tree is empty";
        }

        Deque<Node<T>> deque = new LinkedList<>();

        deque.addLast(root);

        StringBuilder stringBuilder = new StringBuilder("[");

        int appendedElementsCount = 0;

        while (!deque.isEmpty()) {
            Node<T> element = deque.removeLast();

            stringBuilder.append(element.getValue());

            appendedElementsCount++;

            if (appendedElementsCount != size) {
                stringBuilder.append(", ");
            }

            if (element.getRightChild() != null) {
                deque.addLast(element.getRightChild());
            }

            if (element.getLeftChild() != null) {
                deque.addLast(element.getLeftChild());
            }
        }

        return stringBuilder.append("]").toString();
    }
}
