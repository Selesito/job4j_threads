package ru.job4j.cache;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CacheTest {

    @Test
    public void whenUpdateThenException() throws InterruptedException {
        AtomicReference<Exception> exception = new AtomicReference<>();
        Cache cache = new Cache();
        Base base = new Base(1, 1);
        cache.add(base);
        base.setName("base");
        Thread thread = new Thread(
                () -> {
                    base.setName("base1");
                    try {
                        cache.update(base);
                    } catch (Exception e) {
                        exception.set(e);
                    }
                }
        );
        Thread thread1 = new Thread(
                () -> {
                    base.setName("base2");
                    try {
                        cache.update(base);
                    } catch (Exception e) {
                        exception.set(e);
                    }
                }
        );
        thread.start();
        thread1.start();
        thread.join();
        thread1.join();
        assertThat(exception.get().getMessage(), is("Versions are not equal"));
    }
}