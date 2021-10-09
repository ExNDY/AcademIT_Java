package kks.oop.hashtable;

import java.util.*;

public class MyHashTable<T> implements Collection<T> {
    private final int DEFAULT_CAPACITY = 10;
    private final double DEFAULT_LOAD_FACTOR = 0.65;

    private LinkedList<T>[] table;
    private int size;
    private int modCount;

    public MyHashTable() {
        //noinspection unchecked
        table = (LinkedList<T>[]) new LinkedList[DEFAULT_CAPACITY];
    }

    public MyHashTable(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Size of table should be more 0");
        }

        //noinspection unchecked
        table = (LinkedList<T>[]) new LinkedList[capacity];
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
        int index = getHash(o);

        if (table[index] == null) {
            return false;
        }

        return table[index].contains(o);
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
        int hash = getHash(item);

        if (table[hash] == null) {
            table[hash] = new LinkedList<>();
        }

        table[hash].add(item);
        size++;
        modCount++;

        checkAndIncreaseCapacity();

        return false;
    }

    @Override
    public boolean remove(Object o) {
        if (size == 0) {
            return false;
        }

        int hash = getHash(o);

        if (table[hash] == null) {
            return false;
        }

        if (table[hash].remove(o)) {
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

        int initialSize = size;

        for (Object o : c) {
            remove(o);
        }

        return initialSize != size;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public boolean retainAll(Collection<?> c) {
        int initialSize = size;

        for (LinkedList<T> item : table) {
            if (item == null || item.size() == 0) {
                continue;
            }

            int itemInitialSize = item.size();

            if (item.retainAll(c)) {
                size += item.size() - itemInitialSize;
            }

        }

        if (size == initialSize) {
            return false;
        }

        modCount++;

        return true;
    }

    @Override
    public void clear() {
        if (size != 0) {
            Arrays.fill(table, null);
            modCount++;
            size = 0;
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("HASH | ITEMS (" + size + ")");

        stringBuilder.append(System.lineSeparator());

        for (int i = 0; i < table.length; i++) {
            if (table[i] != null && table[i].size() > 0) {
                stringBuilder.append(String.format("%4d | ", i))
                        .append(table[i])
                        .append(System.lineSeparator());
            }
        }

        stringBuilder.append(String.format("Capacity: %d%n", table.length))
                .append(String.format("Size: %d", size));

        return stringBuilder.toString();
    }

    private int getHash(Object o) {
        return getHash(o, table.length);
    }

    private int getHash(Object o, int tableLength) {
        return (o == null) ? 0 : Math.abs(o.hashCode() % tableLength);
    }

    private void checkAndIncreaseCapacity() {
        if ((double) size / table.length > DEFAULT_LOAD_FACTOR) {
            int newCapacity = table.length * 2;

            //noinspection unchecked
            LinkedList<T>[] newTable = (LinkedList<T>[]) new LinkedList[newCapacity];

            for (T item : this) {
                int hash = getHash(item, newCapacity);

                if (newTable[hash] == null) {
                    newTable[hash] = new LinkedList<>();
                }

                newTable[hash].add(item);
            }

            table = newTable;
        }
    }

    private class MyHashTableIterator implements Iterator<T> {
        private final int oldModCount = modCount;
        private int index = -1;
        private int viewedItemsCount = 0;
        private Iterator<T> iterator;

        @Override
        public boolean hasNext() {
            return viewedItemsCount < size;
        }

        @Override
        public T next() {
            if (oldModCount != modCount) {
                throw new ConcurrentModificationException("Table was changed");
            }

            if (!hasNext()) {
                throw new NoSuchElementException("Table contains no more elements");
            }

            if (index == -1 || !iterator.hasNext()) {
                while (index < table.length) {
                    index++;

                    if (table[index] != null && table[index].size() != 0) {
                        iterator = table[index].iterator();
                        break;
                    }
                }
            }

            viewedItemsCount++;

            return iterator.next();
        }
    }
}
