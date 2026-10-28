@FunctionalInterface
interface Notifier{
    void send(String message);
}

interface Urgent {
}

public class NotificationSenders{
    public static void main(String[] args){

        Notifier email = (message) -> System.out.println("Email: " + message);
        Notifier sms = (message) -> System.out.println("SMS: " + message);

        Notifier[] senders = {email,sms};
        String message = "Exam starts at 10 AM";
        System.out.println("---- Broadcasting Message ----");

        for (Notifier sender : senders) {
            sender.send(message);

            if (sender instanceof Urgent) {
                sender.send(message);
            }
        }
    }
}