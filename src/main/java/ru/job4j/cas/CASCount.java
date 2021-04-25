package ru.job4j.cas;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {
    private final AtomicReference<Integer> count = new AtomicReference<>();

    public void increment() {
        int curCount;
        int newCount;
        do {
            curCount = count.get();
            newCount = curCount + 1;
        } while (!count.compareAndSet(curCount, newCount));
    }

    public int get() {
        return count.get();
    }
}
