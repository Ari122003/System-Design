# Facade Pattern

## Overview

The **Facade Pattern** is a structural design pattern that provides a unified, simplified interface to a set of interfaces in a subsystem. It hides the complexity of multiple interconnected components and provides a single entry point for client code. The facade makes a complex subsystem easier to use by providing a convenient wrapper.

## Key Characteristics

- **Simplification**: Reduces complexity by providing a simple interface
- **Decoupling**: Decouples client code from complex subsystems
- **Unified Access**: Single point of access to multiple services
- **Dependency Management**: Manages dependencies between multiple components
- **Ease of Use**: Clients don't need to know about internal implementation details

## Pattern Components

### 1. **Subsystem Classes**

Complex classes that perform specific tasks:

```java
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
```

### 2. **Facade Class**

Provides a simplified interface by orchestrating the subsystem:

```java
class ShoppingService {
    private InventoryService inventory;
    private PaymentService payment;
    private OrderService order;
    private NotificationService notification;

    public void placeOrder(String item, String paymentMethod) {
        inventory.checkStock(item);
        payment.processPayment(paymentMethod);
        order.createOrder(item);
        notification.sendNotification();
    }
}
```

## How It Works

The Facade Pattern simplifies complex workflows by hiding multiple steps behind a single method:

1. **Without Facade**: Client code must orchestrate multiple services manually
2. **With Facade**: Client calls one simple method on the facade
3. **Internal Coordination**: The facade internally coordinates all required services
4. **Result**: Clean, simple, and maintainable client code

## Usage Example

```java
ShoppingService shoppingService = new ShoppingService();
shoppingService.placeOrder("Laptop", "Credit Card");
```

### Output:

```
Checking stock for Laptop
Processing payment via Credit Card
Order created for Laptop
Notification sent to user
```

The client code calls a single method, while the facade handles the entire complex process behind the scenes.

## Subsystem Workflow

```
Client Code
    ↓
    ├─→ ShoppingService (Facade)
           ↓
           ├─→ InventoryService.checkStock()
           ├─→ PaymentService.processPayment()
           ├─→ OrderService.createOrder()
           └─→ NotificationService.sendNotification()
```

## Comparison: With and Without Facade

### Without Facade (Complex):

```java
InventoryService inventory = new InventoryService();
PaymentService payment = new PaymentService();
OrderService order = new OrderService();
NotificationService notification = new NotificationService();

inventory.checkStock("Laptop");
payment.processPayment("Credit Card");
order.createOrder("Laptop");
notification.sendNotification();
```

### With Facade (Simple):

```java
ShoppingService shoppingService = new ShoppingService();
shoppingService.placeOrder("Laptop", "Credit Card");
```

## Advantages

✅ **Simplicity**: Clients see a simple, unified interface  
✅ **Reduced Coupling**: Clients are decoupled from complex subsystems  
✅ **Maintainability**: Changes to subsystems don't affect client code  
✅ **Reusability**: Multiple clients can share the same facade  
✅ **Flexibility**: Subsystems can be modified without affecting clients

## Disadvantages

❌ **Hidden Complexity**: Hides details that some clients might need  
❌ **Limited Control**: Clients have less control over individual components  
❌ **Inflexibility**: Facade might not support all subsystem combinations  
❌ **God Object**: Facade can become large and handle too many responsibilities

## Real-World Examples

- **E-commerce Platforms**: Order placement orchestrating inventory, payment, and shipping
- **Database Drivers**: JDBC providing simplified interface to database connections
- **File I/O Libraries**: High-level file operations hiding OS-level complexity
- **Web Frameworks**: Simplified APIs for complex HTTP and routing logic
- **Graphics Libraries**: Simple shape drawing API hiding complex rendering
- **Hotel Booking Systems**: Single booking process coordinating rooms, payments, confirmations
- **Home Automation**: Single command controlling multiple devices (lights, AC, security)

## Use Cases

- When working with complex subsystems with many interdependent classes
- When you want to provide a simple interface to a complex library
- When you need to decouple client code from subsystem components
- When you want to layer your subsystems with clear dependencies
- When integrating legacy systems with modern code

## Facade vs Other Patterns

| Pattern       | Purpose                                  | Key Difference                 |
| ------------- | ---------------------------------------- | ------------------------------ |
| **Facade**    | Simplify complex subsystems              | Provides single entry point    |
| **Adapter**   | Make incompatible interfaces compatible  | Converts existing interface    |
| **Decorator** | Add behavior to objects                  | Adds functionality dynamically |
| **Proxy**     | Control access to another object         | Acts as surrogate for object   |
| **Bridge**    | Separate abstraction from implementation | Decouples at design time       |

## Best Practices

✅ **Keep it Simple**: Facade should provide a simple, intuitive interface  
✅ **Single Responsibility**: Each facade method should represent one logical operation  
✅ **Don't Hide Everything**: Allow direct access to subsystems when needed  
✅ **Dependency Injection**: Inject dependencies into the facade (as shown in the example)  
✅ **Document Complex Operations**: Explain what the facade method does internally

## Related Patterns

- **Adapter Pattern**: Similar intent but adapts incompatible interfaces
- **Decorator Pattern**: Similar structure but focuses on adding behavior
- **Bridge Pattern**: Separates abstraction from implementation
- **Composite Pattern**: Creates tree structures while facade creates simplified access
- **Strategy Pattern**: Can work together for different implementation strategies
