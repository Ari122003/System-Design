# Bridge Pattern

## Overview

The **Bridge Pattern** is a structural design pattern that decouples an abstraction from its implementation so that the two can vary independently. It uses composition to decouple the abstraction hierarchy from the implementation hierarchy, allowing them to change independently without affecting each other. The pattern bridges the gap between abstraction and implementation.

## Key Characteristics

- **Decoupling**: Separates abstraction from implementation
- **Independent Variation**: Both can be extended independently
- **Composition**: Uses composition instead of inheritance
- **Flexibility**: Allows runtime switching between implementations
- **Scalability**: Avoids the combinatorial explosion of subclasses

## Problem it Solves

Without the Bridge Pattern, if you have multiple types of notifications (Order, OTP) and multiple channels (Email, SMS, Push), you'd need 2×3=6 classes:

- OrderNotificationEmail, OrderNotificationSMS, OrderNotificationPush
- OTPNotificationEmail, OTPNotificationSMS, OTPNotificationPush

With the Bridge Pattern, you need only 2+3=5 classes.

## Pattern Components

### 1. **Implementation Interface (Bridge)**

Defines the contract for different implementations:

```java
interface NotificationChannel {
    void send(String message);
}
```

### 2. **Concrete Implementations**

Different ways to send notifications:

```java
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
```

### 3. **Abstraction**

The high-level abstraction that uses the implementation:

```java
abstract class Notification {
    protected NotificationChannel channel;

    public Notification(NotificationChannel channel) {
        this.channel = channel;
    }

    abstract void notifyUser(String user, String message);
}
```

### 4. **Refined Abstractions**

Extended abstractions that define specific behaviors:

```java
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
```

## How It Works

The Bridge Pattern establishes a bridge between abstraction (notification types) and implementation (delivery channels):

1. **Abstraction Hierarchy**: Defines different notification types (OrderNotification, OTPNotification)
2. **Implementation Hierarchy**: Defines different channels (EmailChannel, SMSChannel, PushChannel)
3. **Bridge**: The `NotificationChannel` interface acts as the bridge
4. **Independence**: Each hierarchy can be extended independently

## Usage Example

```java
NotificationChannel email = new EmailChannel();
NotificationChannel sms = new SMSChannel();

Notification orderEmail = new OrderNotification(email);
orderEmail.notifyUser("Aritra", "Your order is shipped!");

Notification otpSMS = new OTPNotification(sms);
otpSMS.notifyUser("Aritra", "123456");
```

### Output:

```
Sending EMAIL: Order update for Aritra: Your order is shipped!
Sending SMS: OTP for Aritra: 123456
```

## Architecture Diagram

```
Abstraction Hierarchy          Implementation Hierarchy
       ┌─────────────┐                ┌──────────────────┐
       │Notification │                │NotificationChannel│
       └──────┬──────┘                └────────┬─────────┘
              │                                │
        ┌─────┴─────┐                  ┌──────┴──────┬────────┐
        │            │                  │             │        │
 ┌──────────────┐ ┌──────────────┐  ┌────────┐ ┌────────┐ ┌────────┐
 │OrderNotif.   │ │OTPNotif.     │  │Email   │ │SMS     │ │Push    │
 └──────────────┘ └──────────────┘  └────────┘ └────────┘ └────────┘
        │              │
        └──────────────┘
             │
    (Both use the bridge)
```

## Combination Matrix

The bridge allows independent combinations:

| Abstraction           | EmailChannel | SMSChannel | PushChannel |
| --------------------- | ------------ | ---------- | ----------- |
| **OrderNotification** | ✅           | ✅         | ✅          |
| **OTPNotification**   | ✅           | ✅         | ✅          |

Each abstraction can use any implementation without creating separate classes.

## Advantages

✅ **Decoupling**: Abstraction and implementation are independent  
✅ **Flexibility**: Can mix and match abstractions with implementations  
✅ **Extensibility**: Add new abstractions and implementations without modifying existing code  
✅ **Avoids Explosion**: Prevents combinatorial explosion of subclasses  
✅ **Runtime Switching**: Change implementation at runtime

## Disadvantages

❌ **Complexity**: Adds extra abstraction layers  
❌ **Overhead**: More objects and indirection  
❌ **Learning Curve**: More difficult to understand initially  
❌ **Overkill**: May be unnecessary for simple systems

## Real-World Examples

- **Database Drivers**: Different database implementations (MySQL, PostgreSQL) with different query executors
- **Rendering Engines**: Different shape rendering for different graphics libraries
- **Payment Gateways**: Different payment methods (Credit Card, PayPal, UPI) with different processors
- **Logging Frameworks**: Different log levels (INFO, DEBUG, ERROR) with different output handlers
- **UI Frameworks**: Different UI components on different platforms (Windows, macOS, Linux)
- **Notification Systems**: Different notification types through different channels
- **Vehicle Transmission**: Different vehicle types with different transmission types

## Use Cases

- When you want to avoid permanent binding between abstraction and implementation
- When changes in implementation shouldn't affect clients
- When you need to share implementation among multiple objects
- When you want to reduce the number of subclasses in a hierarchy
- When combining multiple independent dimensions of variation

## Bridge vs Other Patterns

| Pattern              | Purpose                                  | Key Difference             |
| -------------------- | ---------------------------------------- | -------------------------- |
| **Bridge**           | Separate abstraction from implementation | Decouples hierarchies      |
| **Adapter**          | Make incompatible interfaces compatible  | Adapts existing interface  |
| **Decorator**        | Add behavior to objects                  | Wraps objects dynamically  |
| **Facade**           | Simplify complex subsystems              | Provides simplified access |
| **Abstract Factory** | Create families of objects               | Creates object families    |

## Class Explosion Problem (Without Bridge)

```
Classes Needed = Abstractions × Implementations

Without Bridge:
- 2 Notification Types (Order, OTP)
- 3 Channels (Email, SMS, Push)
- Classes Needed: 2 × 3 = 6 classes

With Bridge:
- 2 Notification Types + 3 Channels = 5 classes
```

## Best Practices

✅ **Use Composition**: Prefer composition over inheritance  
✅ **Define Clear Boundaries**: Separate abstraction from implementation clearly  
✅ **Keep Interfaces Simple**: Don't overload the bridge interface  
✅ **Document Relationships**: Explain how abstractions and implementations relate  
✅ **Plan for Extensions**: Design with future variations in mind

## Related Patterns

- **Adapter Pattern**: Similar but focuses on making incompatible interfaces work
- **Decorator Pattern**: Similar structure but focuses on adding behavior
- **Abstract Factory Pattern**: Creates families of related objects
- **Strategy Pattern**: Can be used to select implementations dynamically
- **Template Method Pattern**: Defines skeleton of algorithm in base class
