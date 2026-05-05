package Design_Patterns.Behavioral.Command_Pattern;

import java.util.*;

// Command Interface
interface OrderCommand {
    void execute();
}

// Receiver class that performs the actual operations
class OrderService {

    public void placeOrder(String item) {
        System.out.println("Order placed for: " + item);
    }

    public void cancelOrder(String item) {
        System.out.println("Order cancelled for: " + item);
    }
}

// Concrete Command for placing an order
class PlaceOrderCommand implements OrderCommand {
    private OrderService orderService;
    private String item;

    public PlaceOrderCommand(OrderService orderService, String item) {
        this.orderService = orderService;
        this.item = item;
    }

    public void execute() {
        orderService.placeOrder(item);
    }
}

// Concrete Command for cancelling an order
class CancelOrderCommand implements OrderCommand {
    private OrderService orderService;
    private String item;

    public CancelOrderCommand(OrderService orderService, String item) {
        this.orderService = orderService;
        this.item = item;
    }

    public void execute() {
        orderService.cancelOrder(item);
    }
}

// Invoker class that holds and executes commands

class OrderProcessor {
    private Queue<OrderCommand> queue = new LinkedList<>();

    public void takeOrder(OrderCommand command) {
        queue.add(command);
    }

    public void processOrders() {
        while (!queue.isEmpty()) {
            OrderCommand command = queue.poll();
            command.execute();
        }
    }
}

public class Main {
    public static void main(String[] args) {

        OrderService service = new OrderService();

        OrderCommand order1 = new PlaceOrderCommand(service, "Burger");
        OrderCommand order2 = new PlaceOrderCommand(service, "Pizza");
        OrderCommand cancel = new CancelOrderCommand(service, "Burger");

        OrderProcessor processor = new OrderProcessor();

        processor.takeOrder(order1);
        processor.takeOrder(order2);
        processor.takeOrder(cancel);

        processor.processOrders();
    }
}
