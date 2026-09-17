package io.tunctan.threadtutorials.concurency.reentrant;

public class Consumer implements Runnable {

    private final MyBlockingQueue<Integer> queue;
    private final int consumerId;

    public Consumer(MyBlockingQueue<Integer> queue, int consumerId) {
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