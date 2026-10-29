public class DeadlockDemo {

    static Object accountA = new Object();
    static Object accountB = new Object();

    static void transferAtoB() {

        synchronized (accountA) {
            System.out.println("Thread 1 locked Account A");

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            synchronized (accountB) {
                System.out.println("Thread 1 locked Account B");
            }
        }
    }
    
    static void transferBtoA() {

        synchronized (accountB) {
            System.out.println("Thread 2 locked Account B");

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            synchronized (accountA) {
                System.out.println("Thread 2 locked Account A");
            }
        }
    }

    public static void main(String[] args) {

        Thread thread1 = new Thread(DeadlockDemo::transferAtoB);
        Thread thread2 = new Thread(DeadlockDemo::transferBtoA);

        thread1.start();
        thread2.start();
    }
}
