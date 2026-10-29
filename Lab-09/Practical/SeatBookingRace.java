public class SeatBookingRace {

    static class BookingSystem {

        int seatsLeft = 5;
        boolean bookWithoutSync() {

            if (seatsLeft > 0) {

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                seatsLeft--;
                return true;
            }

            return false;
        }

        synchronized boolean book() {

            if (seatsLeft > 0) {

                seatsLeft--;

                return true;
            }

            return false;
        }
    }

    public static void main(String[] args) throws InterruptedException {

        int numberOfThreads = 10;

        BookingSystem system1 = new BookingSystem();

        Thread[] threads = new Thread[numberOfThreads];

        int[] successfulBookings = {0};

        for (int i = 0; i < numberOfThreads; i++) {

            threads[i] = new Thread(() -> {

                if (system1.bookWithoutSync()) {

                    synchronized (successfulBookings) {
                        successfulBookings[0]++;
                    }

                    System.out.println(
                        Thread.currentThread().getName()
                        + " booked a seat"
                    );
                }
            });

            threads[i].start();
        }

        for (Thread t : threads) {
            t.join();
        }

        System.out.println("\nWithout synchronization:");
        System.out.println(
            "Successful bookings: " + successfulBookings[0]
        );
        System.out.println(
            "Seats left: " + system1.seatsLeft
        );

        BookingSystem system2 = new BookingSystem();

        Thread[] threads2 = new Thread[numberOfThreads];

        int[] successfulBookings2 = {0};

        for (int i = 0; i < numberOfThreads; i++) {

            threads2[i] = new Thread(() -> {

                if (system2.book()) {

                    synchronized (successfulBookings2) {
                        successfulBookings2[0]++;
                    }

                    System.out.println(
                        Thread.currentThread().getName()
                        + " booked a seat"
                    );
                }
            });

            threads2[i].start();
        }

        for (Thread t : threads2) {
            t.join();
        }

        System.out.println("\nWith synchronization:");
        System.out.println(
            "Successful bookings: " + successfulBookings2[0]
        );
        System.out.println(
            "Seats left: " + system2.seatsLeft
        );
    }
}