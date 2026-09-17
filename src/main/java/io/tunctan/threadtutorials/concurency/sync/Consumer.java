package io.tunctan.threadtutorials.concurency.sync;

public class Consumer implements Runnable {

    private final SynchronizedBlockingQueue<Integer> queue;
    private final int consumerId;

    public Consumer(SynchronizedBlockingQueue<Integer> queue, int consumerId) {
        this.queue = queue;
        this.consumerId = consumerId;
    }

    @Override
    public void run() {

        try {

            for (int i = 1; i <= 10; i++) {

                Integer value = queue.take();

                System.out.println(
                        "Consumer-" + consumerId +
                        " TAKE <- " + value
                );


                Thread.sleep(1000);
            }

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();

            System.out.println(
                    "Consumer-" + consumerId + " interrupted"
            );
        }
    }
}