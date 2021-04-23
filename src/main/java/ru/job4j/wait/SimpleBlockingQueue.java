package ru.job4j.wait;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {
    private int maxSize = 10;

    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();

    public synchronized void offer(T value) throws InterruptedException {
            while (queue.size() == maxSize) {
                queue.wait();
            }
            queue.offer(value);
            notifyAll();
    }

    public synchronized T poll() throws InterruptedException {
        T rsl = null;
        while (queue.size() == 0) {
            queue.wait();
        }
        rsl = queue.poll();
        notifyAll();
        return rsl;
    }

    public synchronized boolean isEmpty() {
        return queue.size() == 0;
    }
}
