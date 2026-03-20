import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.PrimitiveIterator;

public class LinkedListDeque61B<T> implements Deque61B<T> {
    private class Node{
        T item;
        Node prev;
        Node next;

        Node(T item, Node prev, Node next){
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }
    private Node sentinel;
    private int size;

    public LinkedListDeque61B() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }
    @Override
    public void addFirst(T x) {
        Node first = new Node(x, sentinel, sentinel.next);
        sentinel.next.prev = first;
        sentinel.next = first;
        size++;
    }

    @Override
    public void addLast(T x) {
        Node last = new Node(x, sentinel.prev, sentinel);
        sentinel.prev.next = last;
        sentinel.prev = last;
        size++;
    }

    @Override
    public List<T> toList() {
        List<T> list = new ArrayList<>(size);
        Node p = sentinel.next;
        while (p != sentinel){
            list.add(p.item);
            p = p.next;
        }
        return List.of();
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
        if(isEmpty()) return null;
        Node first = sentinel.next;
        T val = first.item;
        sentinel.next = first.next;
        first.next.prev = sentinel;
        // help gc
        first.next = null;
        first.prev = null;
        first.item = null;
        size--;
        return val;
    }

    @Override
    public T removeLast() {
        if(isEmpty()) return null;
        Node last = sentinel.prev;
        T val = last.item;
        sentinel.prev = last.prev;
        last.prev.next = sentinel;
        last.next = null;
        last.prev = null;
        last.item = null;
        size--;
        return val;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) return null;
        Node p = sentinel.next;
        for (int i=0; i<index; i++){
            p = p.next;
        }
        return p.item;
    }

    @Override
    public T getRecursive(int index) {
        if (index < 0 || index >= size) return null;
        return getRecursiveHelper(sentinel.next, index);
    }
    private T getRecursiveHelper(Node p, int index){
        if(p == sentinel) return null;
        if(index == 0) return p.item;
        return getRecursiveHelper(p.next, index-1);
    }
}
