package deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class ArrayDeque61B<T> implements Deque61B<T> {

    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    @SuppressWarnings("unchecked")
    public ArrayDeque61B() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 7;
        nextLast = 0;
    }

    @SuppressWarnings("unchecked")
    private void resize(int capacity) {
        T[] newArray = (T[]) new Object[capacity];
        // 必须按逻辑顺序，依次把元素搬到新数组的 0, 1, 2... 位置
        for (int i = 0; i < size; i++) {
            newArray[i] = get(i);
        }
        items = newArray;
        nextFirst = capacity - 1;
        nextLast = size;
    }

    @Override
    public void addFirst(T x) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        items[nextFirst] = x;
        nextFirst = (nextFirst - 1 + items.length) % items.length;
        size += 1;
    }

    @Override
    public void addLast(T x) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        items[nextLast] = x;
        nextLast = (nextLast + 1) % items.length;
        size += 1;
    }

    @Override
    public List<T> toList() {
        List<T> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(get(i));
        }
        return list;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    private void checkShrink() {
        if (items.length >= 16 && size < items.length / 4) {
            resize(items.length / 2);
        }
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        int firstIndex = (nextFirst + 1) % items.length;
        T item = items[firstIndex];
        items[firstIndex] = null; // 帮助垃圾回收
        nextFirst = firstIndex;
        size -= 1;
        checkShrink();
        return item;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        int lastIndex = (nextLast - 1 + items.length) % items.length;
        T item = items[lastIndex];
        items[lastIndex] = null; // 帮助垃圾回收
        nextLast = lastIndex;
        size -= 1;
        checkShrink();
        return item;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return items[(nextFirst + 1 + index) % items.length];
    }

    @Override
    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return getRecursiveHelper((nextFirst + 1) % items.length, index);
    }

    private T getRecursiveHelper(int pos, int index) {
        if (index == 0) {
            return items[pos];
        }
        return getRecursiveHelper((pos + 1) % items.length, index - 1);
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