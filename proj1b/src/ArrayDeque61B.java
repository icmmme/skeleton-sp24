import java.util.Arrays;
import java.util.List;

public class ArrayDeque61B<T> implements Deque61B<T> {

    private T[] items;
    private int size;
    private int First;
    private int Last;

    private void resive(int capacity){
        int[] a = new int[capacity];
        System.arraycopy(items,0,a,0,size);
        items=a;
    }
    public ArrayDeque61B(){
        items = (T[]) new Object[8];
        size = 0;
        First =0;
        Last=0;

    }
    @Override
    public void addFirst(T x) {
        if(size == items.length){
            resive(size+1);
        }
        items[size]=x;
        size+=1;
    }


    @Override
    public void addLast(T x) {

    }

    @Override
    public List<T> toList() {
        return List.of();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public T removeFirst() {
        return null;
    }

    @Override
    public T removeLast() {
        return null;
    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public T getRecursive(int index) {
        return null;
    }
}
