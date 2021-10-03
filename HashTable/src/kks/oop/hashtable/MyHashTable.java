package kks.oop.hashtable;

import java.util.*;

public class MyHashTable<T> implements Collection<T> {
    private final LinkedList<T>[] table;
    private int size;
    private int modCount;

    public MyHashTable(LinkedList<T>[] table) {
        this.table = table;
    }

    public MyHashTable(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Size of table shouldn't be less 0");
        }

        // Unchecked assignment: 'java.util.LinkedList[]' to 'java.util.LinkedList<T>[]'
        table = new LinkedList[size];
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
        Object[] result = new Object[size];

        int index = 0;

        for (T item : this) {
            result[index] = item;
            index++;
        }

        return result;
    }

    @Override
    public <E> E[] toArray(E[] a) {
        if (a.length < size) {
            // Unchecked cast: 'java.lang.Object[]' to 'T1[]'
            a = (E[]) Arrays.copyOf(a, size, a.getClass());
        }

        int index = 0;

        for (T item : this){
            // Unchecked cast: 'T' to 'T1'
            a[index] = (E) item;
            index++;
        }

        if (a.length > size){
            a[size] = null;
        }

        return a;
    }

    @Override
    public boolean add(T t) {
        int index = getIndex(t);

        if (table[index] == null){
            table[index] = new LinkedList<>();
        }

        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    private int getIndex(Object o) {
        return (o == null) ? 0 : Math.abs(o.hashCode() % table.length);
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
