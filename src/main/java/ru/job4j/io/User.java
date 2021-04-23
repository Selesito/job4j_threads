package ru.job4j.io;

import java.util.Objects;

public class User {
    private int id;
    private int amount;

    public static User of(int id, int amount) {
        User user = new User();
        user.id = id;
        user.amount = amount;
        return user;
    }

    public synchronized int getId() {
        return id;
    }

    public synchronized int getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id == user.id
                && amount == user.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount);
    }
}
