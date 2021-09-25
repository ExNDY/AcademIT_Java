package kks.oop.arraylist;

import java.util.*;

public class ArrayList<T> implements List<T> {
    private T[] items;
    private int size;
    private int modCount = 0;

    /***
     * Constructor for empty ArrayList with current capacity
     * @param capacity sets size of empty ArrayList
     */
    public ArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity = " + capacity + " must be >=0");
        }

        // warning
        items = (T[]) new Object[capacity];
    }

    /***
     * Constructor with list initialization
     * @param list has data for new ArrayList
     */
    public ArrayList(List<T> list) {
        if (list == null) {
            throw new IllegalArgumentException("List shouldn't be null");
        }

        if (list.size() == 0) {
            // warning
            items = (T[]) new Object[]{};
        }
        // warning
        items = (T[]) new Object[list.size()];
        size = list.size();

        for (int i = 0; i < list.size(); i++) {
            items[i] = list.get(i);
        }
    }

    /***
     * Constructor with array initialization
     * @param array has data for new ArrayList
     */
    public ArrayList(T[] array) {
        if (array == null) {
            throw new IllegalArgumentException("Array shouldn't be null");
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
        if (a == null) {
            throw new NullPointerException("Empty array");
        }

        if ((a.length < size)) {
            // warning: Unchecked cast: 'java.lang.Object[]' to 'T[]'
            return (E[]) Arrays.copyOf(items, size, a.getClass());
        }

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
        if (indexOf(0) != -1) {
            remove(indexOf(o));

            return true;
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (c == null) {
            throw new IllegalArgumentException("Collection shouldn't be null");
        }

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
        if (c == null) {
            throw new IllegalArgumentException("Collection shouldn't be null");
        }

        rangeIndex(index);

        if (c.isEmpty()) {
            return false;
        }

        ensureCapacity(size + c.size());

        System.arraycopy(items, index, items, index + c.size(), size - index);

        int oldSize = size;

        int i = index;

        for (T t : c) {
            items[i] = t;
            i++;
        }

        if (oldSize != items.length) {
            this.modCount++;

            this.size = items.length;
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c == null){
            throw new NullPointerException("Collection shouldn't be NULL");
        }

        if (c.isEmpty()){
            return false;
        }

        int oldModCount = modCount;

        for (int i = size-1; i >= 0; i--){
            if (c.contains(items[i])){
                remove(i);
            }
        }

        return modCount != oldModCount;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c == null){
            throw new NullPointerException("Collection shouldn't be NULL");
        }

        int oldModCount = modCount;

        for (int i = size-1; i >= 0; i--){
            if (!c.contains(items[i])){
                remove(i);
            }
        }

        return modCount != oldModCount;
    }

    @Override
    public void clear() {
        Arrays.fill(items, null);

        size = 0;

        modCount++;
    }

    @Override
    public T get(int index) {
        rangeIndex(index);

        return items[index];
    }

    @Override
    public T set(int index, T item) {
        rangeIndex(index);

        T oldValue = items[index];
        items[index] = item;

        return oldValue;
    }

    @Override
    public void add(int index, T item) {
        if (size + 1 > items.length) {
            increaseCapacity();
        }

        rangeIndex(index);

        if (index < size) {
            System.arraycopy(items, index, items, index + 1, size - index);
        }

        items[index] = item;

        size++;
        modCount++;
    }

    @Override
    public T remove(int index) {
        rangeIndex(index);

        T oldValue = items[index];
        System.arraycopy(items, index + 1, items, index, size - (index + 1));

        size--;
        modCount++;

        return oldValue;
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

    /***
     * Check out-of-bounds index
     * @param index value
     */
    private void rangeIndex(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Index \"" + index + "\" should be >0");
        }

        if (index > size) {
            throw new IndexOutOfBoundsException("Index \"" + index + "\" should be less then array list size");
        }
    }

    public void ensureCapacity(int minCapacity) {
        if (minCapacity < 0) {
            throw new IllegalArgumentException("Capacity = " + minCapacity + " must be >=0");
        }

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
        items = Arrays.copyOf(items, items.length * 2);
    }

    // Without realisation
    @Override
    public ListIterator listIterator() {
        return null;
    }

    @Override
    public ListIterator listIterator(int index) {
        return null;
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        return null;
    }

    /***
     * Iterator for ArrayList
     */
    private class ArrayListIterator implements Iterator<T> {
        private int currentIndex = -1;
        private final int currentModCount = modCount;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Next element doesn't exist.");
            }

            if (currentModCount != modCount) {
                throw new ConcurrentModificationException("The list has been changed.");
            }

            currentIndex++;

            return items[currentIndex];
        }
    }
}
