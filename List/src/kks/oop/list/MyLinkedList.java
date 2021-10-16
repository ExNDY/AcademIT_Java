package kks.oop.list;

import java.util.NoSuchElementException;
import java.util.Objects;

public class MyLinkedList<T> {
    private ListItem<T> head;
    private int size;

    public MyLinkedList(T data) {
        head = new ListItem<>(data);
        size++;
    }

    public MyLinkedList() {

    }

    public int size() {
        return size;
    }

    public T get(int index) {
        checkIndex(index, false);

        return getItemByIndex(index).getData();
    }

    public T set(int index, T data) {
        checkIndex(index, false);

        ListItem<T> item = getItemByIndex(index);

        T oldData = item.getData();
        item.setData(data);

        return oldData;
    }

    public T getFirst() {
        if (head == null) {
            throw new NoSuchElementException("List is empty");
        }

        return head.getData();
    }

    private ListItem<T> getItemByIndex(int index) {
        ListItem<T> item = head;

        int i = 0;

        while (i < index) {
            item = item.getNext();
            i++;
        }

        return item;
    }

    public void addFirst(T data) {
        head = new ListItem<>(data, head);

        size++;
    }

    public void addByIndex(int index, T data) {
        checkIndex(index, true);

        if (index == 0) {
            addFirst(data);

            return;
        }

        ListItem<T> previousItem = getItemByIndex(index - 1);
        ListItem<T> newItem = new ListItem<>(data, previousItem.getNext());
        previousItem.setNext(newItem);

        size++;
    }

    public void add(T data) {
        addByIndex(size, data);
    }

    public T removeByIndex(int index) {
        checkIndex(index, false);

        if (index == 0) {
            return removeFirst();
        }

        ListItem<T> previousItem = getItemByIndex(index - 1);
        T removedData = previousItem.getNext().getData();
        previousItem.setNext(previousItem.getNext().getNext());

        size--;

        return removedData;
    }

    public boolean removeByData(T data) {
        ListItem<T> currentItem = head;
        ListItem<T> previousItem = null;

        while (currentItem != null) {
            if (Objects.equals(data, currentItem.getData())) {
                if (previousItem != null) {
                    previousItem.setNext(currentItem.getNext());
                    size--;

                    return true;
                }

                removeFirst();

                return true;
            }

            previousItem = currentItem;
            currentItem = currentItem.getNext();
        }

        return false;
    }

    public T removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException("List is empty");
        }

        T removedData = head.getData();
        head = head.getNext();

        size--;

        return removedData;
    }

    public void reverse() {
        if (size < 2) {
            return;
        }

        ListItem<T> previousItem = head;
        ListItem<T> nextItem = head.getNext();

        head = nextItem;
        nextItem = nextItem.getNext();
        previousItem.setNext(null);

        while (nextItem != null) {
            head.setNext(previousItem);
            previousItem = head;
            head = nextItem;
            nextItem = nextItem.getNext();
        }

        head.setNext(previousItem);
    }

    public MyLinkedList<T> copy() {
        MyLinkedList<T> copiedList = new MyLinkedList<>();

        if (head == null) {
            return copiedList;
        }

        copiedList.head = new ListItem<>(head.getData());
        ListItem<T> previousItem = copiedList.head;

        for (ListItem<T> item = head.getNext(); item != null; item = item.getNext()) {
            ListItem<T> nextItem = new ListItem<>(item.getData());

            previousItem.setNext(nextItem);
            previousItem = nextItem;
        }

        copiedList.size = size;

        return copiedList;
    }

    private void checkIndex(int index, boolean isUpperBoundsIncluded) {
        int maxIndex = isUpperBoundsIncluded ? size : size - 1;

        if (index < 0 || index > maxIndex) {
            throw new IndexOutOfBoundsException("Index \"" + index + "\" out of bounds 0.." + maxIndex + ".");
        }
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder("[");

        for (ListItem<T> item = head; item != null; item = item.getNext()) {
            sb.append(item.getData()).append(", ");
        }

        sb.delete(sb.length() - 2, sb.length());

        return sb.append("]").toString();
    }
}