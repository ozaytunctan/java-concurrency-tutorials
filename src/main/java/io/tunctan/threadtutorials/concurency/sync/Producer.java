package io.tunctan.threadtutorials.concurency.sync;

public class Producer implements Runnable {

    private final SynchronizedBlockingQueue<Integer> queue;
    private final int producerId;

    public Producer(SynchronizedBlockingQueue<Integer> queue, int producerId) {
        this.queue = queue;
        this.producerId = producerId;
    }

    @Override
    public void run() {

        try {

            for (int i = 1; i <= 10; i++) {

                int value = producerId * 100 + i;

                System.out.println(
                        "Producer-" + producerId +
                        " PUT -> " + value
                );

                queue.put(value);

                Thread.sleep(200);
            }

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();

            System.out.println(
                    "Producer-" + producerId + " interrupted"
            );
        }
    }
}