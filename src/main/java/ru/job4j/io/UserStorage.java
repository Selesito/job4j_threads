package ru.job4j.io;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.ConcurrentHashMap;

@ThreadSafe
public class UserStorage {

    @GuardedBy("this")
    private final ConcurrentHashMap<Integer, User> storage = new ConcurrentHashMap<>();

    public synchronized boolean add(User user) {
        if (!storage.containsKey(user.getId())) {
            storage.put(user.getId(), User.of(user.getId(), user.getAmount()));
            return true;
        }
        return false;
    }

    public synchronized boolean update(User user) {
        if (storage.containsKey(user.getId())) {
            storage.replace(user.getId(), User.of(user.getId(), user.getAmount()));
            return true;
        }
        return false;
    }

    public synchronized boolean delete(User user) {
        storage.remove(user.getId());
        if (!storage.containsValue(user)) {
            return true;
        }
        return false;
    }

    public synchronized boolean transfer(int fromid, int toId, int amount) {
        boolean result = false;
        User from = storage.get(fromid);
        User to = storage.get(toId);
        if (from != null && to != null) {
            if (from.getAmount() >= amount) {
                update(User.of(from.getId(), from.getAmount() - amount));
                update(User.of(to.getId(), to.getAmount() + amount));
            }
        }
        return result;
    }
}
