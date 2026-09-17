package io.tunctan.threadtutorials.concurency.reentrant;

public class ProducerConsumerClient {

    public static void main(String[] args)
            throws InterruptedException {

        MyBlockingQueue<Integer> queue =
                new MyBlockingQueue<>(3);

        Thread producer1 =
                new Thread(
                        new Producer(queue, 1),
                        "producer-1"
                );

        Thread producer2 =
                new Thread(
                        new Producer(queue, 2),
                        "producer-2"
                );

        Thread consumer1 =
                new Thread(
                        new Consumer(queue, 1),
                        "consumer-1"
                );

        Thread consumer2 =
                new Thread(
                        new Consumer(queue, 2),
                        "consumer-2"
                );

        Thread consumer3 =
                new Thread(
                        new Consumer(queue, 3),
                        "consumer-3"
                );

        producer1.start();
        producer2.start();
//        producer3.start();

        consumer1.start();
        consumer2.start();
        consumer3.start();

//        producer1.join();
//        producer2.join();
//        producer2.join();
//
//        consumer1.join();
//        consumer2.join();
//        consumer2.join();

        System.out.println("All threads finished.");
    }
}