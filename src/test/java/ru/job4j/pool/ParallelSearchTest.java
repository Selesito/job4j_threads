package ru.job4j.pool;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ParallelSearchTest {

    @Test
    public void whenFindIndexTrue() {
        Integer[] array1 = {5, 1, 3, 6, 7, 9, 4};
        int index1 = ParallelSearch.findIndex(array1, 6);
        assertThat(index1, is(3));
    }

    @Test
    public void whenFindIndexFalse() {
        Integer[] array1 = {5, 1, 3, 6, 7, 9, 4};
        int index1 = ParallelSearch.findIndex(array1, 56);
        assertThat(index1, is(-1));
    }

    @Test
    public void whenFindIndexSize30ThenTrue() {
        Integer[] array1 = new Integer[31];
        for (int i = 0; i < array1.length; i++) {
            array1[i] = i;
        }
        int index1 = ParallelSearch.findIndex(array1, 14);
        assertThat(index1, is(14));
    }
}