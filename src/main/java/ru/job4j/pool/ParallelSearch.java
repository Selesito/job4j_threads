package ru.job4j.pool;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelSearch<T> extends RecursiveTask<Integer> {
    private final T[] array;
    private final T element;
    private final int from;
    private final int to;

    public ParallelSearch(T[] array, T element, int from, int to) {
        this.array = array;
        this.element = element;
        this.from = from;
        this.to = to;
    }

    @Override
    protected Integer compute() {
        if (array.length < 10) {
            return Arrays.asList(array).indexOf(element);
        }
        int mid = (from + (to - from)) / 2;
        ParallelSearch<T> leftSort = new ParallelSearch(array, element, from, mid);
        ParallelSearch<T> rightSort = new ParallelSearch(array, element, mid + 1, to);
        leftSort.fork();
        rightSort.fork();
        int left = leftSort.join();
        int right = rightSort.join();
        return left != -1 ? left : right;
    }

    public static <T> int findIndex(T[] array, T element) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new ParallelSearch<>(array, element, 0, array.length - 1));
    }
}
