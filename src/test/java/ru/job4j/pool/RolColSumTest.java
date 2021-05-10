package ru.job4j.pool;

import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;

public class RolColSumTest {

    @Test
    public void whenSumThenTrue() {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        RolColSum.Sums[] exp = RolColSum.sum(matrix);
        assertEquals(15, exp[1].getColSum());
    }

    @Test
    public void whenAsyncSumThenTrue() throws ExecutionException, InterruptedException {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        RolColSum.Sums[] exp = RolColSum.sum(matrix);
        assertEquals(36, exp[2].getColSum());
        assertEquals(58, exp[3].getRowSum());
    }
}