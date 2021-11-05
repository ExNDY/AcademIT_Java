package kks.oop.array_list;

import java.util.*;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;

    private T[] items;
    private int size;
    private int modCount;

    public ArrayList() {
        //noinspection unchecked
        items = (T[]) new Object[DEFAULT_CAPACITY];
    }

    /***
     * Constructor for empty ArrayList with current capacity
     * @param capacity sets size of empty ArrayList
     */
    public ArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity = " + capacity + ", must be >= 0");
        }

        //noinspection unchecked
        items = (T[]) new Object[capacity];
    }

    /***
     * Constructor with list initialization
     * @param c has data for new ArrayList
     */
    public ArrayList(Collection<? extends T> c) {
        if (c == null) {
            throw new IllegalArgumentException("Collection shouldn't be NULL");
        }

        //noinspection unchecked
        items = (T[]) new Object[c.size()];
        size = c.size();

        int i = 0;

        for (T item : c) {
            items[i] = item;
            i++;
        }
    }

    /***
     * Constructor with array initialization
     * @param array has data for new ArrayList
     */
    public ArrayList(T[] array) {
        if (array == null) {
            throw new IllegalArgumentException("Array shouldn't be NULL");
        }

        items = Arrays.copyOf(array, array.length);
        size = array.length;
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
        return indexOf(o) != -1;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayListIterator();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(items, size);
    }

    @Override
    public <E> E[] toArray(E[] a) {
        if (a.length < size) {
            //noinspection unchecked
            return (E[]) Arrays.copyOf(items, size, a.getClass());
        }

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(items, 0, a, 0, size);

        if (a.length > size) {
            a[size] = null;
        }

        return a;
    }

    @Override
    public boolean add(T item) {
        add(size, item);

        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (size == 0) {
            return false;
        }

        int index = indexOf(o);

        if (index != -1) {
            remove(index);

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
        return addAll(size, c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        checkIndex(index, true);

        if (c.isEmpty()) {
            return false;
        }

        ensureCapacity(size + c.size());

        System.arraycopy(items, index, items, index + c.size(), size - index);

        int i = index;

        for (T item : c) {
            items[i] = item;
            i++;
        }

        size += c.size();
        modCount++;

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c.isEmpty()) {
            return false;
        }

        int initialSize = size;

        for (int i = initialSize - 1; i >= 0; i--) {
            if (c.contains(items[i])) {
                remove(i);
            }
        }

        return size != initialSize;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int initialSize = size;

        for (int i = initialSize - 1; i >= 0; i--) {
            if (!c.contains(items[i])) {
                remove(i);
            }
        }

        return size != initialSize;
    }

    @Override
    public void clear() {
        if (size == 0) {
            return;
        }

        for (int i = 0; i < size; i++) {
            items[i] = null;
        }

        size = 0;
        modCount++;
    }

    @Override
    public T get(int index) {
        checkIndex(index, false);

        return items[index];
    }

    @Override
    public T set(int index, T item) {
        checkIndex(index, false);

        T oldItem = items[index];
        items[index] = item;

        return oldItem;
    }

    @Override
    public void add(int index, T item) {
        checkIndex(index, true);

        if (size >= items.length) {
            increaseCapacity();
        }

        if (index < size) {
            System.arraycopy(items, index, items, index + 1, size - index);
        }

        items[index] = item;

        size++;
        modCount++;
    }

    @Override
    public T remove(int index) {
        checkIndex(index, false);

        T removedItem = items[index];

        System.arraycopy(items, index + 1, items, index, size - (index + 1));

        items[size - 1] = null;
        size--;
        modCount++;

        return removedItem;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(items[i], o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(items[i], o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder("[");

        for (int i = 0; i < size; i++) {
            stringBuilder.append(items[i]).append(", ");
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        stringBuilder.append("]");

        return stringBuilder.toString();
    }

    /***
     * Check out-of-bounds index
     * @param index value
     * @param isUpperBoundsIncluded include in check upper bounds of array (include array.length() or not)
     */
    private void checkIndex(int index, boolean isUpperBoundsIncluded) {
        int maxIndex = isUpperBoundsIncluded ? size : size - 1;

        if (index < 0 || index > maxIndex) {
            throw new IndexOutOfBoundsException("Index \"" + index + "\" out of bounds 0.." + maxIndex + ".");
        }
    }

    private void ensureCapacity(int minCapacity) {
        if (items.length < minCapacity) {
            items = Arrays.copyOf(items, minCapacity);
        }
    }

    public void trimToSize() {
        if (items.length > size) {
            items = Arrays.copyOf(items, size);
        }
    }

    private void increaseCapacity() {
        int newCapacity = items.length != 0 ? items.length : DEFAULT_CAPACITY;

        items = Arrays.copyOf(items, newCapacity);
    }

    @Override
    public ListIterator<T> listIterator() {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Method not implemented");
    }

    /***
     * Iterator for ArrayList
     */
    private class ArrayListIterator implements Iterator<T> {
        private int currentIndex = -1;
        private final int initialModCount = modCount;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Next element doesn't exist.");
            }

            if (initialModCount != modCount) {
                throw new ConcurrentModificationException("The list has been changed.");
            }

            currentIndex++;

            return items[currentIndex];
        }
    }
}
