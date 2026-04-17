# Decorator Pattern

## Overview

The **Decorator Pattern** is a structural design pattern that allows you to dynamically add new functionality or responsibilities to an object without altering its structure. It provides a flexible alternative to subclassing for extending functionality.

## Purpose

- **Add responsibilities to objects dynamically** without modifying their original structure
- **Avoid combinatorial explosion** of subclasses (e.g., instead of creating MilkSugarCoffee, MilkChocolateCoffee, etc.)
- **Follow the Open/Closed Principle** - open for extension, closed for modification
- **Compose behaviors** by wrapping objects with one or more decorators

## Key Components

1. **Component Interface** - Defines the common interface for both concrete components and decorators
2. **Concrete Component** - The original object to which we add functionality
3. **Decorator (Abstract)** - Abstract class that implements the component interface and maintains a reference to a component object
4. **Concrete Decorators** - Extend the decorator and add specific responsibilities

## Example Implementation: Coffee Ordering System

### 1. Component Interface

```java
interface Coffee {
    String getDescription();
    int cost();
}
```

This interface defines the contract that both the concrete coffee and decorators must implement.

### 2. Concrete Component

```java
class BasicCoffee implements Coffee {
    public String getDescription() {
        return "Basic Coffee";
    }

    public int cost() {
        return 50;
    }
}
```

`BasicCoffee` is the core object that can be decorated with additional features like milk or sugar.

### 3. Abstract Decorator

```java
abstract class CoffeeDecorator implements Coffee {
    protected Coffee coffee;

    public CoffeeDecorator(Coffee coffee) {
        this.coffee = coffee;
    }
}
```

The abstract decorator:

- Implements the same `Coffee` interface
- Holds a reference to a `Coffee` object (which could be another decorator or the base component)
- Serves as the base class for all concrete decorators

### 4. Concrete Decorators

```java
class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    public String getDescription() {
        return coffee.getDescription() + ", Milk";
    }

    public int cost() {
        return coffee.cost() + 20;
    }
}
```

```java
class SugarDecorator extends CoffeeDecorator {
    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }

    public String getDescription() {
        return coffee.getDescription() + ", Sugar";
    }

    public int cost() {
        return coffee.cost() + 10;
    }
}
```

Each concrete decorator:

- Wraps the original coffee object
- Enhances the description and cost
- Delegates to the wrapped coffee object

### 5. Usage

```java
public static void main(String[] args) {
    Coffee coffee = new BasicCoffee();

    coffee = new MilkDecorator(coffee);
    coffee = new SugarDecorator(coffee);

    System.out.println(coffee.getDescription()); // Output: Basic Coffee, Milk, Sugar
    System.out.println(coffee.cost());           // Output: 80
}
```

**Composition in Action:**

- Start with `BasicCoffee` (cost: 50)
- Wrap it with `MilkDecorator` (adds 20) → cost: 70
- Wrap that with `SugarDecorator` (adds 10) → cost: 80
- The final description: "Basic Coffee, Milk, Sugar"

## Advantages

✅ **Flexibility** - Add behaviors dynamically at runtime  
✅ **Single Responsibility** - Each decorator handles one concern  
✅ **Avoid Subclass Explosion** - No need for MilkSugarCoffee, MilkChocolateCoffeeSugar, etc.  
✅ **Combine Multiple Decorators** - Stack decorators in any order  
✅ **Open/Closed Principle** - Add new decorators without modifying existing code

## Disadvantages

❌ **Order Matters** - Different stacking orders might produce different results  
❌ **Complexity** - Can be harder to understand with many nested decorators  
❌ **Performance** - Each decorator adds a layer of indirection

## Real-World Use Cases

- **I/O Streams** - Java's BufferedInputStream, DataInputStream wrap InputStream
- **UI Components** - Adding borders, scrollbars, or shadows to windows
- **HTTP Requests** - Adding compression, authentication headers, logging
- **Cake Decorators** - Adding toppings, frosting, sprinkles to a base cake
- **File Compression** - Wrapping files with encryption, compression layers

## When to Use

Use the Decorator Pattern when:

- You need to add responsibilities to individual objects, not entire classes
- You want to avoid creating many subclasses for various combinations
- Responsibilities might be added or removed at runtime
- You need to compose behaviors in flexible ways
