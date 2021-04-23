package ru.job4j.synch;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SingleList<E> implements Iterable<E> {
    private Object[] container = new Object[10];
    private int size = 0;
    private int modCount = 0;

    public void add(E model) {
        if (size == container.length - 1) {
            upgrade(container.length * 2);
        }
        container[size++] = model;
        modCount++;
    }

    public Object get(int index) {
        checkIndex(index);
        return container[index];
    }

    public void checkIndex(int index) {
        if (index < 0 || index >= this.size) {
            throw new  IndexOutOfBoundsException("Такого индекса нет!");
        }
    }

    private void upgrade(int length) {
        Object[] rsl = new Object[length];
        System.arraycopy(container, 0, rsl, 0, size);
        container = rsl;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int positionIterator = 0;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return this.positionIterator < size;
            }

            @Override
            public E next() {
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                return (E) container[positionIterator++];
            }
        };
    }
}