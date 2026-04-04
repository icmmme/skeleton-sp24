package deque;

import java.util.*;

public class ArrayDeque61B<T> implements Deque61B<T> {

    private T[] items;
    private int size;
    private int First;
    private int Last;

    public ArrayDeque61B(){
        items = (T[]) new Object[8];
        size = 0;
        First =0;
        Last=0;
    }


    private void resize(int capacity){
        T[] a = (T[]) new Object[capacity];
        System.arraycopy(items,0,a,0,size);
        items=a;
        First = capacity-1;
        Last = size;
    }

    @Override
    public void addFirst(T x) {
        if(size == items.length){
            resize(size*2);
        }
        items[size]=x;
        First = (First-1+items.length)%items.length;
        size+=1;
    }


    @Override
    public void addLast(T x) {
        if(size == items.length){
            resize(size*2);
        }
        items[size]=x;
        Last = (Last+1)%items.length;
        size+=1;

    }

    @Override
    public List<T> toList() {
        List<T> list = new ArrayList<>();
        for(int i=0; i<size; i++){
            list.add(items[(First+1+i)%items.length]);
        }
        return list;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if(size==0){
            return null;
        }
        int first = (First+1)%items.length;
        T item = items[first];
        items[first] = null;
        First = first;
        size-=1;
        if(items.length>16 && size< items.length/4){
            resize(items.length/2);
        }

        return item;
    }

    @Override
    public T removeLast() {
        if(size==0){
            return null;
        }
        int last = (Last-1+items.length)%items.length;
        T item = items[last];
        items[last]=null;
        Last = last;
        size-=1;
        if(items.length>16 && size< items.length/4){
            resize(items.length/2);
        }
        return item;
    }

    @Override
    public T get(int index) {
        if(index<0 ||index>=size){
            return null;
        }
        int first=(First+1)%items.length;
        return items[(first+index)%items.length];
    }

    @Override
    public T getRecursive(int index) {
        if(index<0 ||index>=size){
            return null;
        }
        return getRecursiveHelper((First+1)%items.length,index);
    }
    private T getRecursiveHelper(int pos, int index){
        if(index==0){
            return  items[pos];
        }
        return getRecursiveHelper((pos+1)%items.length, index-1);
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int pos = 0;

        @Override
        public boolean hasNext() {
            return pos < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T result = get(pos);
            pos += 1;
            return result;
        }
    }
}