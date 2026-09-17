package io.tunctan.threadtutorials.concurency.reentrant;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyBlockingQueue<T> {

    private final Object[] items;

    private int head;
    private int tail;
    private int count;

    private final ReentrantLock lock = new ReentrantLock(true);

    private final Condition notEmpty = lock.newCondition();
    private final Condition notFull = lock.newCondition();

    public MyBlockingQueue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than 0");
        }

        this.items = new Object[capacity];
    }

    public void put(T item) throws InterruptedException {

        lock.lock();

        try {

            // Queue doluysa producer beklesin
            while (count == items.length) {
                notFull.await();
            }

            items[tail] = item;

            tail = (tail + 1) % items.length;

            count++;

            // Artık queue boş değil. consume edilebilir. herhangi bekleyen bir threadi uyandır
            notEmpty.signal();

        } finally {
            lock.unlock();
        }
    }

    @SuppressWarnings("unchecked")
    public T take() throws InterruptedException {

        lock.lock();

        try {

            // Queue boşsa consumer beklesin
            while (count == 0) {
                notEmpty.await();
            }

            T item = (T) items[head];

            items[head] = null;

            head = (head + 1) % items.length;

            count--;

            // Artık queue dolu değil.
            notFull.signal();

            return item;

        } finally {
            lock.unlock();
        }
    }

    public int size() {

        lock.lock();

        try {
            return count;
        } finally {
            lock.unlock();
        }
    }
}