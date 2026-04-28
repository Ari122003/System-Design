# Strategy Pattern

## Overview

The **Strategy Pattern** is a behavioral design pattern that defines a family of algorithms, encapsulates each one, and makes them interchangeable. The Strategy pattern lets the algorithm vary independently from clients that use it. It enables selecting an algorithm's behavior at runtime and switching between different implementations seamlessly.

## Key Characteristics

- **Encapsulation**: Each algorithm is encapsulated in its own class
- **Interchangeability**: Strategies are interchangeable at runtime
- **Flexibility**: Easy to add new strategies without modifying existing code
- **Runtime Selection**: Choose strategy dynamically based on runtime conditions
- **Elimination of Conditionals**: Replaces if-else or switch-case chains with polymorphism

## Problem it Solves

Without the Strategy Pattern, you'd need multiple conditional statements:

```java
// Without Strategy - Hard to maintain and extend
if (paymentMethod == "CREDIT_CARD") {
    // credit card logic
} else if (paymentMethod == "UPI") {
    // UPI logic
} else if (paymentMethod == "PAYPAL") {
    // PayPal logic
}
```

With the Strategy Pattern, each algorithm is a separate, swappable class.

## Pattern Components

### 1. **Strategy Interface**

Defines the contract for all supported strategies:

```java
interface PaymentStrategy {
    void pay(int amount);
}
```

### 2. **Concrete Strategies**

Implements different variations of the algorithm:

```java
class CreditCardPayment implements PaymentStrategy {
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using Credit Card");
    }
}

class UpiPayment implements PaymentStrategy {
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using UPI");
    }
}

class PaypalPayment implements PaymentStrategy {
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using PayPal");
    }
}
```

### 3. **Context**

Uses a strategy interface to execute the strategy:

```java
class PaymentContext {
    private PaymentStrategy strategy;

    public void setStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public void makePayment(int amount) {
        strategy.pay(amount);
    }
}
```

## How It Works

The Strategy Pattern allows selecting different algorithms at runtime:

1. **Define Strategy Interface**: Common interface for all payment methods
2. **Implement Strategies**: Each payment method is a concrete implementation
3. **Context Uses Strategy**: PaymentContext delegates to the selected strategy
4. **Runtime Selection**: Call `setStrategy()` to choose different payment method
5. **Execute Strategy**: Call `makePayment()` which uses the selected strategy

## Usage Example

```java
PaymentContext context = new PaymentContext();

context.setStrategy(new CreditCardPayment());
context.makePayment(1000);

context.setStrategy(new UpiPayment());
context.makePayment(500);

context.setStrategy(new PaypalPayment());
context.makePayment(2000);
```

### Output:

```
Paid 1000 using Credit Card
Paid 500 using UPI
Paid 2000 using PayPal
```

Each payment method is executed based on the selected strategy at runtime.

## Strategy Selection Workflow

```
PaymentContext
    ↓
    setStrategy(CreditCardPayment)
    ↓
    makePayment(1000)
    ↓
    CreditCardPayment.pay(1000)
    ↓
    "Paid 1000 using Credit Card"
```

## Benefits of Strategy Pattern

### 1. **Open/Closed Principle**

Open for extension (add new strategies), closed for modification (don't change existing code).

### 2. **Eliminates Conditionals**

Replace if-else chains with polymorphism:

```java
// Before Strategy Pattern
if (type.equals("CREDIT_CARD")) {
    // credit card logic
} else if (type.equals("UPI")) {
    // UPI logic
}

// After Strategy Pattern
strategy.pay(amount); // polymorphic call
```

### 3. **Runtime Flexibility**

```java
// Select strategy at runtime based on user preference
String userChoice = getUserInput();
PaymentStrategy strategy = PaymentStrategyFactory.create(userChoice);
context.setStrategy(strategy);
```

## Advantages

✅ **Flexibility**: Switch algorithms at runtime  
✅ **Extensibility**: Add new strategies without modifying existing code  
✅ **Eliminates Conditionals**: Replaces complex if-else chains  
✅ **Testability**: Each strategy can be tested independently  
✅ **Separation of Concerns**: Algorithm logic is separated from business logic  
✅ **Reusability**: Strategies can be reused across different contexts

## Disadvantages

❌ **Complexity**: Introduces more classes and interfaces  
❌ **Memory Overhead**: More objects in memory  
❌ **Overkill for Few Strategies**: May be unnecessary for simple cases  
❌ **Learning Curve**: Developers need to understand the pattern

## Real-World Examples

- **Payment Methods**: Credit Card, UPI, PayPal, Apple Pay, Google Pay
- **Sorting Algorithms**: QuickSort, MergeSort, BubbleSort implementations
- **Compression Algorithms**: GZIP, ZIP, RAR compression strategies
- **Authentication**: OAuth, JWT, Basic Auth, LDAP
- **Route Finding**: Dijkstra, A\*, Bellman-Ford algorithms
- **Image Processing**: Different filters (blur, sharpen, grayscale)
- **Discounting**: Fixed discount, percentage discount, BOGO strategies
- **Caching**: LRU, LFU, FIFO cache eviction strategies
- **Deployment**: Docker, Kubernetes, VMs deployment strategies

## Use Cases

- When multiple algorithms solve the same problem
- When algorithm needs to be selected at runtime
- When you want to avoid conditional statements
- When you need to test different algorithms independently
- When algorithm implementation may change during development
- When you want to provide different implementations to users

## Strategy vs Other Patterns

| Pattern             | Purpose                                | Key Difference                           |
| ------------------- | -------------------------------------- | ---------------------------------------- |
| **Strategy**        | Encapsulate interchangeable algorithms | Runtime selection of algorithm           |
| **State**           | Allow object to change behavior        | Object state changes behavior            |
| **Decorator**       | Add behavior dynamically               | Adds functionality to objects            |
| **Command**         | Encapsulate requests as objects        | Encapsulates request as action           |
| **Factory**         | Create objects                         | Creates objects, doesn't select behavior |
| **Template Method** | Define algorithm skeleton              | Base class defines skeleton              |

## Strategy vs Template Method

Both define algorithm variations, but differ in approach:

| Aspect             | Strategy      | Template Method |
| ------------------ | ------------- | --------------- |
| **Implementation** | Composition   | Inheritance     |
| **Selection**      | Runtime       | Compile time    |
| **Flexibility**    | More flexible | Less flexible   |
| **Code Reuse**     | Limited       | More code reuse |

## Best Practices

✅ **Use Composition**: Prefer composition over inheritance  
✅ **Single Responsibility**: Each strategy should have one reason to change  
✅ **Immutable Strategies**: Make strategies stateless when possible  
✅ **Factory Pattern**: Use factory to create strategies  
✅ **Default Strategy**: Provide default strategy if none specified  
✅ **Document Algorithms**: Clearly document what each strategy does  
✅ **Consistent Interface**: All strategies implement the same interface

## Advanced Example: Strategy Factory

```java
class PaymentStrategyFactory {
    public static PaymentStrategy createStrategy(String type) {
        switch(type.toUpperCase()) {
            case "CREDIT_CARD":
                return new CreditCardPayment();
            case "UPI":
                return new UpiPayment();
            case "PAYPAL":
                return new PaypalPayment();
            default:
                throw new IllegalArgumentException("Unknown strategy: " + type);
        }
    }
}

// Usage
PaymentContext context = new PaymentContext();
context.setStrategy(PaymentStrategyFactory.createStrategy("UPI"));
context.makePayment(500);
```

## Lambda Expression Approach (Java 8+)

```java
@FunctionalInterface
interface PaymentStrategy {
    void pay(int amount);
}

// Using lambda expressions
PaymentContext context = new PaymentContext();

context.setStrategy(amount ->
    System.out.println("Paid " + amount + " using Credit Card"));
context.makePayment(1000);

context.setStrategy(amount ->
    System.out.println("Paid " + amount + " using UPI"));
context.makePayment(500);
```

## When NOT to Use Strategy Pattern

- When you have only one algorithm
- When algorithms rarely change
- When algorithm differences are minimal
- When performance overhead is critical
- When conditional logic is trivial and clear

## Related Patterns

- **State Pattern**: Similar structure but object state changes behavior
- **Factory Pattern**: Often used with Strategy to create strategies
- **Decorator Pattern**: Can be combined with Strategy for behavior composition
- **Template Method Pattern**: Similar concept but uses inheritance
- **Command Pattern**: Encapsulates requests similar to how Strategy encapsulates algorithms
