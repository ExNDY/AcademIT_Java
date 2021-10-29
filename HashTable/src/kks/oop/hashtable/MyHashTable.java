package kks.oop.hashtable;

import java.util.*;

public class MyHashTable<T> implements Collection<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int MINIMUM_CAPACITY = 1;
    private static final double MAXIMUM_LOAD_FACTOR = 0.65;

    private LinkedList<T>[] lists;
    private int size;
    private int modCount;

    public MyHashTable() {
        //noinspection unchecked
        lists = (LinkedList<T>[]) new LinkedList[DEFAULT_CAPACITY];
    }

    public MyHashTable(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Wrong capacity size: " + capacity + ", shouldn't be less " + MINIMUM_CAPACITY);
        }

        //noinspection unchecked
        lists = (LinkedList<T>[]) new LinkedList[capacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        int index = getIndex(o);

        return lists[index] != null && lists[index].contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return new MyHashTableIterator();
    }

    @Override
    public Object[] toArray() {
        Object[] resultArray = new Object[size];

        int index = 0;

        for (T item : this) {
            resultArray[index] = item;
            index++;
        }

        return resultArray;
    }

    @Override
    public <E> E[] toArray(E[] array) {
        if (array == null) {
            throw new NullPointerException("Array shouldn't be NULL");
        }

        Object[] items = toArray();

        if (array.length < size) {
            //noinspection unchecked
            return (E[]) Arrays.copyOf(items, size, array.getClass());
        }

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(items, 0, array, 0, size);

        if (array.length > size) {
            array[size] = null;
        }

        return array;
    }

    @Override
    public boolean add(T item) {
        int index = getIndex(item);

        if (lists[index] == null) {
            lists[index] = new LinkedList<>();
        }

        lists[index].add(item);
        size++;

        checkAndIncreaseCapacity();

        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (size == 0) {
            return false;
        }

        int index = getIndex(o);

        if (lists[index] == null) {
            return false;
        }

        if (lists[index].remove(o)) {
            size--;
            modCount++;

            return true;
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        if (c.size() == 0) {
            return false;
        }

        for (T item : c) {
            add(item);
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c.size() == 0) {
            return false;
        }

        boolean wasChanged = false;

        for (Object o : c) {
            while (remove(o)) {
                wasChanged = true;
            }
        }

        return wasChanged;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean wasChanged = false;

        for (LinkedList<T> list : lists) {
            if (list == null) {
                continue;
            }

            int oldListSize = lists.length;

            if (list.retainAll(c) && oldListSize > 0) {
                wasChanged = true;

                size += list.size() - oldListSize;
            }
        }

        if (wasChanged) {
            modCount++;
        }

        return wasChanged;
    }

    @Override
    public void clear() {
        if (size != 0) {
            Arrays.fill(lists, null);
            modCount++;
            size = 0;
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[HASH | [ITEMS]]: ");

        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null && lists[i].size() > 0) {
                stringBuilder.append("[ ")
                        .append(String.format("%4d | ", i))
                        .append(lists[i])
                        .append("], ");
            }
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());

        return stringBuilder.toString();
    }

    private int getIndex(Object o) {
        return getIndex(o, lists.length);
    }

    private static int getIndex(Object o, int listsLength) {
        return (o == null) ? 0 : Math.abs(o.hashCode() % listsLength);
    }

    private void checkAndIncreaseCapacity() {
        modCount++;

        if ((double) size / lists.length < MAXIMUM_LOAD_FACTOR) {
            return;
        }

        int newCapacity = lists.length * 2;

        //noinspection unchecked
        LinkedList<T>[] newLists = (LinkedList<T>[]) new LinkedList[newCapacity];

        for (T item : this) {
            int index = getIndex(item, newCapacity);

            if (newLists[index] == null) {
                newLists[index] = new LinkedList<>();
            }

            newLists[index].add(item);
        }

        lists = newLists;
    }

    private class MyHashTableIterator implements Iterator<T> {
        private final int initialModCount = modCount;
        private int arrayIndex = -1;
        private int viewedItemsCount;
        private Iterator<T> listIterator;

        @Override
        public boolean hasNext() {
            return viewedItemsCount < size;
        }

        @Override
        public T next() {
            if (initialModCount != modCount) {
                throw new ConcurrentModificationException("Table was changed");
            }

            if (!hasNext()) {
                throw new NoSuchElementException("Table contains no more elements");
            }

            if (arrayIndex == -1 || !listIterator.hasNext()) {
                while (arrayIndex < lists.length) {
                    arrayIndex++;

                    if (lists[arrayIndex] != null && lists[arrayIndex].size() != 0) {
                        listIterator = lists[arrayIndex].iterator();
                        break;
                    }
                }
            }

            viewedItemsCount++;

            return listIterator.next();
        }
    }
}
