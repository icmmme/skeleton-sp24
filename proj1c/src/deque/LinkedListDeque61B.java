package deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class LinkedListDeque61B<T> implements Deque61B<T> {

    private class Node {
        T item;
        Node prev;
        Node next;

        Node(T item, Node prev, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        private Node p = sentinel.next;

        @Override
        public boolean hasNext() {
            return p != sentinel;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T result = p.item;
            p = p.next;
            return result;
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
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
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
        while (p != sentinel) {
            list.add(p.item);
            p = p.next;
        }
        return list; // 修复了之前的 List.of()
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) return null;
        Node first = sentinel.next;
        T val = first.item;
        sentinel.next = first.next;
        first.next.prev = sentinel;

        first.next = null;
        first.prev = null;
        first.item = null;
        size--;
        return val;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) return null;
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
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        return p.item;
    }

    @Override
    public T getRecursive(int index) {
        if (index < 0 || index >= size) return null;
        return getRecursiveHelper(sentinel.next, index);
    }

    private T getRecursiveHelper(Node p, int index) {
        if (index == 0) return p.item;
        return getRecursiveHelper(p.next, index - 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Deque61B<?> other)) return false;
        if (this.size() != other.size()) return false;

        for (int i = 0; i < size; i++) {
            T a = this.get(i);
            Object b = other.get(i);
            if (a == null) {
                if (b != null) return false;
            } else if (!a.equals(b)) {
                return false;
            }
        }
        return true;
    }
}