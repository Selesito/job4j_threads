package ru.job4j.synch;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Iterator;

@ThreadSafe
public class SingleLockList<T> implements Iterable<T> {

    @GuardedBy("this")
    private final SingleList<T> list = new SingleList<>();

    public synchronized void add(T value) {
        list.add(value);
    }

    public synchronized T get(int index) {
        return (T) list.get(index);
    }

    @Override
    public synchronized Iterator<T> iterator() {
        return copy(this.list).iterator();
    }

    private synchronized SingleList<T> copy(SingleList<T> list) {
        SingleList<T> result = new SingleList<>();
        for (T rsl : list) {
            result.add(rsl);
        }
        return result;
    }
}
