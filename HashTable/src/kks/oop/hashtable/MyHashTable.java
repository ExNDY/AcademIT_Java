package kks.oop.hashtable;

import java.util.*;

public class MyHashTable<T> implements Collection<T> {
    private static final int DEFAULT_CAPACITY_SIZE = 10;
    private static final int MINIMUM_CAPACITY_SIZE = 1;
    private static final double MAXIMUM_LOAD_FACTOR = 0.65;

    private LinkedList<T>[] lists;
    private int size;
    private int modCount;

    public MyHashTable() {
        //noinspection unchecked
        lists = (LinkedList<T>[]) new LinkedList[DEFAULT_CAPACITY_SIZE];
    }

    public MyHashTable(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Wrong capacity size: " + capacity + ", shouldn't be less " + MINIMUM_CAPACITY_SIZE);
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
        if (array.length < size) {
            //noinspection unchecked
            array = (E[]) Arrays.copyOf(array, size, array.getClass());
        }

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(toArray(), 0, array, 0, size);

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
        modCount++;

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

        size--;
        modCount++;

        return lists[index].remove(o);
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
        if (c.isEmpty()) {
            return false;
        }

        boolean wasChanged = false;

        for (LinkedList<T> list : lists) {
            if (list != null && list.retainAll(c)) {
                wasChanged = true;
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

    private static int getIndex(Object o, int tableLength) {
        return (o == null) ? 0 : Math.abs(o.hashCode() % tableLength);
    }

    private void checkAndIncreaseCapacity() {
        if ((double) size / lists.length > MAXIMUM_LOAD_FACTOR) {
            int newCapacity = lists.length * 2;

            //noinspection unchecked
            LinkedList<T>[] newTable = (LinkedList<T>[]) new LinkedList[newCapacity];

            for (T item : this) {
                int index = getIndex(item, newCapacity);

                if (newTable[index] == null) {
                    newTable[index] = new LinkedList<>();
                }

                newTable[index].add(item);
            }

            lists = newTable;
        }
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
