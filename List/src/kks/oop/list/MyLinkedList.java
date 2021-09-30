package kks.oop.list;

public class MyLinkedList<T> {
    private ListItem<T> head;
    private int size = 0;

    public MyLinkedList(ListItem<T> head) {
        this.head = head;
        size++;
    }

    public MyLinkedList() {

    }

    public int size() {
        return size;
    }

    public T get(int index) {
        rangeIndex(index);

        return getItemByIndex(index).getData();
    }

    public T set(int index, T data) {
        rangeIndex(index);

        if (data == null) {
            throw new NullPointerException("Value shouldn't be NULL");
        }

        ListItem<T> item = getItemByIndex(index);

        T oldData = item.getData();
        item.setData(data);

        return oldData;
    }

    public T getFirstItem() {
        if (head == null) {
            return null;
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

    public void addAsFirstItem(ListItem<T> item) {
        if (item == null) {
            throw new NullPointerException("Argument shouldn't be NULL");
        }

        item.setNext(head);
        head = item;

        size++;
    }

    public void addByIndex(int index, ListItem<T> item) {
        rangeIndex(index);

        if (head == null) {
            throw new IndexOutOfBoundsException("List is EMPTY");
        }

        if (item == null) {
            throw new IllegalArgumentException("Item shouldn't be NULL");
        }

        int i = 0;

        for (ListItem<T> t = head, previous = null; t != null; previous = t, t = t.getNext()) {
            if (i == index) {
                item.setNext(t);

                if (previous != null) {
                    previous.setNext(item);
                } else {
                    head = item;
                }

                size++;
            }

            i++;
        }
    }

    public boolean add(ListItem<T> item) {
        if (item == null) {
            throw new IllegalArgumentException("Argument shouldn't be NULL");
        }

        if (head == null) {
            head = item;

            return true;
        }

        for (ListItem<T> t = head; t != null; t = t.getNext()) {
            if (t.getNext() == null) {
                t.setNext(item);

                size++;

                return true;
            }
        }

        return false;
    }

    public T removeItemByIndex(int index) {
        rangeIndex(index);

        if (head == null) {
            return null;
        }

        int i = 0;

        for (ListItem<T> item = head, previous = null; item != null; previous = item, item = item.getNext()) {
            if (i == index) {
                T data = item.getData();

                if (previous != null) {
                    previous.setNext(item.getNext());
                } else {
                    head = item.getNext();
                }

                size--;

                return data;
            }

            i++;
        }

        return null;
    }

    public boolean removeItemByValue(T data) {
        if (head == null) {
            return false;
        }

        if (data == null) {
            throw new IllegalArgumentException("Data shouldn't be NULL");
        }

        for (ListItem<T> item = head, previous = null; item != null; previous = item, item = item.getNext()) {
            if (item.getData() == data) {
                if (previous != null) {
                    previous.setNext(item.getNext());
                } else {
                    head = item.getNext();
                }

                size--;

                return true;
            }
        }

        return false;
    }

    public T removeFirstItem() {
        T data = head.getData();
        head = head.getNext();

        size--;

        return data;
    }

    public void reflectList() {
        int i = 0;
        int j = size - 1;

        while (j > i) {
            T temp = getItemByIndex(i).getData();

            set(i, getItemByIndex(j).getData());
            set(j, temp);

            i++;
            j--;
        }
    }

    public MyLinkedList<T> copy() {
        if (head == null) {
            return null;
        }

        MyLinkedList<T> copiedList = new MyLinkedList<>();

        for (ListItem<T> item = head; item != null; item = item.getNext()) {
            copiedList.add(new ListItem<>(item.getData()));
        }

        return copiedList;
    }

    private void rangeIndex(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Index shouldn't be >= 0");
        }

        if (index >= size) {
            throw new IndexOutOfBoundsException("Index value out of bounds");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");

        for (ListItem<T> item = head; item != null; item = item.getNext()) {
            sb.append(item.getData());

            if (item.getNext() != null) {
                sb.append(", ");
            }
        }

        return sb.append("]").toString();
    }
}