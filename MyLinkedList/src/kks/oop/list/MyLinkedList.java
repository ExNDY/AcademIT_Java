package kks.oop.list;

import java.util.StringJoiner;

public class MyLinkedList<T> {
    private ListItem<T> head;
    private int size;

    public MyLinkedList(ListItem<T> head){
        this.head = head;
        size++;
    }

    public int size(){
        return size;
    }

    public T getHeadData(){
        if (size == 0){
            throw new IllegalArgumentException("List is empty.");
        }

        return head.getData();
    }

    public T get(int index){
        isNullCheck(index);

        return getItemByIndex(index).getData();
    }

    public T set(int index, T data){
        isNullCheck(index);

        if (data == null){
            throw new NullPointerException("Value shouldn't be NULL");
        }

        ListItem<T> p = getItemByIndex(index);

        T oldData = p.getData();
        p.setData(data);

        return oldData;
    }


    private ListItem<T> getItemByIndex(int index){
        ListItem<T> p = head;

        int i = 0;

        while (i < index){
            p = p.getNext();
            i++;
        }

        return p;
    }

    private void isNullCheck(int index){
        if (index < 0){
            throw new IndexOutOfBoundsException("Index shouldn't be >= 0");
        }

        if (index >= size){
            throw  new IndexOutOfBoundsException("Index value out of bounds");
        }
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("[");

        for (ListItem<T> p = head; p != null; p = p.getNext()){
            sb.append(p.getData());

            if (p.getNext() != null){
                sb.append(", ");
            }
        }

        return sb.append("]").toString();
    }
}
