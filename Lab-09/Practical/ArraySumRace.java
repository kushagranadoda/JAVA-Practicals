import java.util.concurrent.atomic.AtomicLong;

public class ArraySumRace {

    static long sharedTotal = 0;
    static void addWithoutSync(long value) {
        sharedTotal += value;
    }

    static synchronized void addWithSync(long value) {
        sharedTotal += value;
    }

    public static void main(String[] args) throws InterruptedException {

        int arraySize = 1_000_000;
        int numberOfThreads = 8;

        long[] numbers = new long[arraySize];

        for (int i = 0; i < arraySize; i++) {
            numbers[i] = 1;
        }

        long expectedTotal = 0;

        for (long n : numbers) {
            expectedTotal += n;
        }

        System.out.println("Expected total: " + expectedTotal);
        System.out.println();

        sharedTotal = 0;

        Thread[] threads = new Thread[numberOfThreads];

        int chunk = arraySize / numberOfThreads;

        long start = System.nanoTime();

        for (int i = 0; i < numberOfThreads; i++) {

            int startIndex = i * chunk;
            int endIndex = (i == numberOfThreads - 1)
                    ? arraySize
                    : startIndex + chunk;

            threads[i] = new Thread(() -> {

                for (int j = startIndex; j < endIndex; j++) {
                    addWithoutSync(numbers[j]);
                }

            });

            threads[i].start();
        }

        for (Thread t : threads) {
            t.join();
        }

        long end = System.nanoTime();

        System.out.println("WITHOUT SYNCHRONIZATION");
        System.out.println("Actual total:   " + sharedTotal);
        System.out.println("Expected total: " + expectedTotal);
        System.out.println("Time: " + (end - start) + " ns");
        System.out.println();

        sharedTotal = 0;

        threads = new Thread[numberOfThreads];

        start = System.nanoTime();

        for (int i = 0; i < numberOfThreads; i++) {

            int startIndex = i * chunk;
            int endIndex = (i == numberOfThreads - 1)
                    ? arraySize
                    : startIndex + chunk;

            threads[i] = new Thread(() -> {

                for (int j = startIndex; j < endIndex; j++) {
                    addWithSync(numbers[j]);
                }

            });

            threads[i].start();
        }

        for (Thread t : threads) {
            t.join();
        }

        end = System.nanoTime();

        System.out.println("WITH SYNCHRONIZATION");
        System.out.println("Actual total:   " + sharedTotal);
        System.out.println("Expected total: " + expectedTotal);
        System.out.println("Time: " + (end - start) + " ns");
        System.out.println();

        AtomicLong atomicTotal = new AtomicLong(0);

        threads = new Thread[numberOfThreads];

        start = System.nanoTime();

        for (int i = 0; i < numberOfThreads; i++) {

            int startIndex = i * chunk;
            int endIndex = (i == numberOfThreads - 1)
                    ? arraySize
                    : startIndex + chunk;

            threads[i] = new Thread(() -> {

                for (int j = startIndex; j < endIndex; j++) {
                    atomicTotal.addAndGet(numbers[j]);
                }

            });

            threads[i].start();
        }

        for (Thread t : threads) {
            t.join();
        }

        end = System.nanoTime();

        System.out.println("WITH ATOMICLONG");
        System.out.println("Actual total:   " + atomicTotal.get());
        System.out.println("Expected total: " + expectedTotal);
        System.out.println("Time: " + (end - start) + " ns");
    }
}