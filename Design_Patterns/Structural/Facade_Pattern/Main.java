package Design_Patterns.Structural.Facade_Pattern;

// Subsystem classes

class InventoryService {
    public void checkStock(String item) {
        System.out.println("Checking stock for " + item);
    }
}

class PaymentService {
    public void processPayment(String method) {
        System.out.println("Processing payment via " + method);
    }
}

class OrderService {
    public void createOrder(String item) {
        System.out.println("Order created for " + item);
    }
}

class NotificationService {
    public void sendNotification() {
        System.out.println("Notification sent to user");
    }
}

// Facade class

class ShoppingService {
    private InventoryService inventory;
    private PaymentService payment;
    private OrderService order; // Dependancy Injection
    private NotificationService notification;

    public void placeOrder(String item, String paymentMethod) {
        inventory.checkStock(item);
        payment.processPayment(paymentMethod);
        order.createOrder(item);
        notification.sendNotification();
    }
}

public class Main {
    public static void main(String[] args) {
        ShoppingService shoppingService = new ShoppingService();
        shoppingService.placeOrder("Laptop", "Credit Card");
    }

}
