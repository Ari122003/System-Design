package Design_Patterns.Structural.Bridge_Pattern;

// Abstraction

interface NotificationChannel {
    void send(String message);
}

// Implementations
class EmailChannel implements NotificationChannel {
    public void send(String message) {
        System.out.println("Sending EMAIL: " + message);
    }
}

class SMSChannel implements NotificationChannel {
    public void send(String message) {
        System.out.println("Sending SMS: " + message);
    }
}

class PushChannel implements NotificationChannel {
    public void send(String message) {
        System.out.println("Sending PUSH: " + message);
    }
}

// Refined Abstraction
abstract class Notification {
    protected NotificationChannel channel;

    public Notification(NotificationChannel channel) {
        this.channel = channel;
    }

    abstract void notifyUser(String user, String message);
}

// Concrete Implementations
class OrderNotification extends Notification {

    public OrderNotification(NotificationChannel channel) {
        super(channel);
    }

    public void notifyUser(String user, String message) {
        channel.send("Order update for " + user + ": " + message);
    }
}

class OTPNotification extends Notification {

    public OTPNotification(NotificationChannel channel) {
        super(channel);
    }

    public void notifyUser(String user, String message) {
        channel.send("OTP for " + user + ": " + message);
    }
}

public class Main {

    public static void main(String[] args) {
        NotificationChannel email = new EmailChannel();
        NotificationChannel sms = new SMSChannel();

        Notification orderEmail = new OrderNotification(email);
        orderEmail.notifyUser("Aritra", "Your order is shipped!");

        Notification otpSMS = new OTPNotification(sms);
        otpSMS.notifyUser("Aritra", "123456");
    }

}
