package ru.job4j.wait;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleBlockingQueueTest {

    @Test
    public void whenOfferAndPoll() throws InterruptedException {
        final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();
        final List<Integer> rsl = new ArrayList<>();
        Thread offer = new Thread(
                () -> {
                    for (int i = 0; i < 8; i++) {
                        try {
                            queue.offer(i);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        Thread poll = new Thread(
                () -> {
                    while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
                        try {
                            rsl.add(queue.poll());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        offer.start();
        poll.start();
        offer.join();
        poll.interrupt();
        poll.join();
        assertThat(rsl, is(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7)));
    }
}