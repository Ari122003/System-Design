# Factory Pattern

The Factory Pattern is a creational design pattern that provides an interface for creating objects, but lets subclasses decide which class to instantiate. Instead of directly instantiating objects using the `new` keyword, the factory pattern encapsulates object creation logic in a factory class.

## What is the Factory Pattern?

The Factory Pattern is used to create objects without specifying the exact classes of objects that will be created. This promotes loose coupling and makes the code more maintainable and flexible.

## Key Components

1. **Product Interface**: An interface that defines the common behavior for all products (e.g., `Car`)
2. **Concrete Products**: Classes that implement the product interface (e.g., `BMW`, `Audi`)
3. **Factory**: A class that creates objects based on input parameters (e.g., `CarFactory`)

## Bad Design (Without Factory Pattern)

In the bad design, the client directly instantiates objects, creating tight coupling:

```java
// Tightly coupled - client knows about concrete classes
Car car = new BMW();
car.drive();
```

**Problems:**

- Client is tightly coupled to concrete classes (`BMW`, `Audi`)
- Difficult to add new car types without modifying client code
- Testing becomes harder due to direct dependencies on concrete implementations
- Violates the Open/Closed Principle

## Good Design (With Factory Pattern)

The good design uses a factory class to handle object creation:

```java
// Step 1: Define the Product Interface
interface Car {
    public void drive();
}

// Step 2: Create Concrete Products
class BMW implements Car {
    @Override
    public void drive() {
        System.out.println("Driving a BMW");
    }
}

class Audi implements Car {
    @Override
    public void drive() {
        System.out.println("Driving Audi");
    }
}

// Step 3: Create the Factory
class CarFactory {
    public static Car getCar(String type) {
        if (type.equalsIgnoreCase("BMW")) {
            return new BMW();
        } else if (type.equalsIgnoreCase("Audi")) {
            return new Audi();
        }
        throw new IllegalArgumentException("Unknown car type");
    }
}

// Step 4: Use the Factory (Loosely coupled)
class Main {
    public static void main(String[] args) {
        Car car = CarFactory.getCar("BmW"); // Factory creates the object
        car.drive();
    }
}
```

**Output:**

```
Driving a BMW
```

**Benefits:**

- Client is **loosely coupled** to concrete classes; it only knows about the `Car` interface and `CarFactory`
- Easy to add new car types by creating a new class and updating the factory
- Centralized object creation logic makes it easier to maintain
- Follows the Open/Closed Principle (open for extension, closed for modification)

## How the Factory Pattern Works

1. **Client requests an object** by calling `CarFactory.getCar("BmW")`
2. **Factory determines the type** based on the input parameter
3. **Factory instantiates the correct object** (`new BMW()`)
4. **Factory returns the object** as the `Car` interface type
5. **Client uses the object** without knowing its concrete type

## Advantages

✅ Loose coupling between client and concrete classes
✅ Easier to add new types without modifying existing code
✅ Centralized object creation logic
✅ Improves code maintainability and testability
✅ Follows SOLID principles

## Disadvantages

❌ May introduce additional classes and complexity
❌ If only a few objects are created, it might be overkill

## When to Use

- When you have multiple classes that share a common interface
- When you need to create objects without coupling to specific classes
- When the object creation logic is complex or should be centralized
- When you anticipate adding new types of objects in the future
