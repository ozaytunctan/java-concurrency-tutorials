package io.tunctan.threadtutorials.concurency.sync;

public class SynchronizedBlockingQueue<T> {

    private final Object[] items;

    private int head;
    private int tail;
    private int count;

    private final Object lock = new Object();

    public SynchronizedBlockingQueue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than 0");
        }

        this.items = new Object[capacity];
    }

    public void put(T item) throws InterruptedException {
        synchronized (lock) {
            // Queue doluysa producer beklesin
            while (count == items.length) {
                lock.wait();
            }

            items[tail] = item;

            tail = (tail + 1) % items.length;

            count++;

            // Artık queue boş değil. consume edilebilir. herhangi bekleyen bir threadi uyandır
            lock.notify();

        }
    }

    @SuppressWarnings("unchecked")
    public T take() throws InterruptedException {
        synchronized (lock) {

            // Queue boşsa consumer beklesin
            while (count == 0) {
                lock.wait();
            }

            T item = (T) items[head];

            items[head] = null;

            head = (head + 1) % items.length;

            count--;

            // Artık queue dolu değil.
            lock.notify();

            return item;

        }
    }

    public int size() {
        synchronized (lock) {
            return count;
        }
    }
}