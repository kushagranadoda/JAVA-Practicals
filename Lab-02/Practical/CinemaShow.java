public class CinemaShow {
    private String title;
    private int seatsAvailable;
    private final int capacity;
    private static int totalBooked = 0;

    public CinemaShow(String title, int capacity) {
        this.title = title;
        this.capacity = capacity;
        this.seatsAvailable = capacity;
    }

    
    public CinemaShow(String title) {
        this(title, 100);
    }

    public boolean book(int n) {
        if (n <= seatsAvailable) {
            seatsAvailable -= n;
            totalBooked += n;
            return true;
        } else {
            return false;
        }
    }

    public void cancel(int n) {
        seatsAvailable += n;
        if (seatsAvailable > capacity) {
            seatsAvailable = capacity;
        }
    }

    public int getSeatsAvailable() {
        return seatsAvailable;
    }

    public static int getTotalBooked() {
        return totalBooked;
    }

    public static void main(String[] args) {
        CinemaShow show1 = new CinemaShow("Interstellar", 50);
        CinemaShow show2 = new CinemaShow("Oppenheimer");

        System.out.println("Booking 20 seats for " + show1.title + ": " + show1.book(20));
        System.out.println("Seats available: " + show1.getSeatsAvailable());

        System.out.println("Booking 40 seats for " + show1.title + ": " + show1.book(40));
        System.out.println("Seats available: " + show1.getSeatsAvailable());

        System.out.println("Cancelling 10 seats for " + show1.title);
        show1.cancel(10);
        System.out.println("Seats available: " + show1.getSeatsAvailable());

        System.out.println("Cancelling 100 seats for " + show1.title + " (should cap at capacity)");
        show1.cancel(100);
        System.out.println("Seats available: " + show1.getSeatsAvailable());

        System.out.println("Booking 60 seats for " + show2.title + ": " + show2.book(60));
        System.out.println("Seats available: " + show2.getSeatsAvailable());

        System.out.println("Booking 50 seats for " + show2.title + ": " + show2.book(50));
        System.out.println("Seats available: " + show2.getSeatsAvailable());

        System.out.println("Total seats booked across all shows: " + CinemaShow.getTotalBooked());
    }
}