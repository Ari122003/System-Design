# Abstract Factory Pattern

## Overview

The **Abstract Factory Pattern** is a creational design pattern that provides an interface for creating families of related or dependent objects without specifying their concrete classes. It's used to ensure that a group of related objects are created in a consistent manner.

## Key Characteristics

- **Purpose**: Creates families of related objects
- **Type**: Creational Pattern
- **Problem**: When you need to create objects that belong to a family and must be used together consistently
- **Solution**: Define an abstract factory interface that declares methods for creating each type of object in the family

## When to Use

✅ When a system needs to work with multiple families of related products  
✅ When you want to provide a library of products revealing only interfaces, not implementations  
✅ When you need to ensure consistency among a family of related products  
✅ When creating highly configurable systems with different product variants

## Structure

### 1. **Abstract/Base Interfaces** (Product Families)

These define the contract for all objects in the family:

```java
interface Car {
    void drive();
}

interface Engine {
    void start();
}

interface Interior {
    void design();
}
```

### 2. **Concrete Product Implementations**

Each brand implements the product families:

```java
// BMW Family
class BMW implements Car {
    public void drive() {
        System.out.println("Driving BMW");
    }
}

class BMWEngine implements Engine {
    public void start() {
        System.out.println("BMW Engine started");
    }
}

class BMWInterior implements Interior {
    public void design() {
        System.out.println("BMW Interior design");
    }
}

// Audi Family
class Audi implements Car {
    public void drive() {
        System.out.println("Driving Audi");
    }
}

class AudiEngine implements Engine {
    public void start() {
        System.out.println("Audi Engine started");
    }
}

class AudiInterior implements Interior {
    public void design() {
        System.out.println("Audi Interior design");
    }
}
```

### 3. **Abstract Factory Interface**

Defines methods to create each product type:

```java
interface CarFactory {
    Car createCar();
    Engine createEngine();
    Interior createInterior();
}
```

### 4. **Concrete Factories**

Each factory creates a complete family of products:

```java
// BMW Factory
class BMWFactory implements CarFactory {
    public Car createCar() {
        return new BMW();
    }

    public Engine createEngine() {
        return new BMWEngine();
    }

    public Interior createInterior() {
        return new BMWInterior();
    }
}

// Audi Factory
class AudiFactory implements CarFactory {
    public Car createCar() {
        return new Audi();
    }

    public Engine createEngine() {
        return new AudiEngine();
    }

    public Interior createInterior() {
        return new AudiInterior();
    }
}
```

## Usage Example

```java
public class Main {
    public static void main(String[] args) {
        // Create BMW family products
        CarFactory bmwFactory = new BMWFactory();

        Car bmwCar = bmwFactory.createCar();
        Engine bmwEngine = bmwFactory.createEngine();
        Interior bmwInterior = bmwFactory.createInterior();

        bmwCar.drive();           // Output: Driving BMW
        bmwEngine.start();        // Output: BMW Engine started
        bmwInterior.design();     // Output: BMW Interior design
    }
}
```

## Advantages

| Advantage                 | Description                                                       |
| ------------------------- | ----------------------------------------------------------------- |
| **Encapsulation**         | Client code doesn't need to know concrete classes                 |
| **Consistency**           | Ensures that related products are used together                   |
| **Flexibility**           | Easy to add new product families by creating new factories        |
| **Decoupling**            | Reduces coupling between client code and concrete product classes |
| **Single Responsibility** | Each factory is responsible for creating one family of products   |

## Disadvantages

| Disadvantage       | Description                                                                                        |
| ------------------ | -------------------------------------------------------------------------------------------------- |
| **Complexity**     | Requires many interfaces and classes, increasing code complexity                                   |
| **Overkill**       | May be unnecessary for simple applications with few product families                               |
| **Hard to extend** | Adding new product types requires modifying the abstract factory interface and all implementations |

## Real-World Applications

- **UI Toolkits**: Creating platform-specific UI components (Windows, Mac, Linux)
- **Database Drivers**: Creating database-specific connection pools, queries, and result sets
- **Document Creation**: Creating different document formats (PDF, Word, HTML) with consistent structure
- **Game Development**: Creating platform-specific graphics, audio, and input systems
- **Log Frameworks**: Creating different logging implementations across various platforms

## Difference from Factory Pattern

| Feature        | Factory Pattern        | Abstract Factory Pattern                     |
| -------------- | ---------------------- | -------------------------------------------- |
| **Purpose**    | Creates single objects | Creates families of related objects          |
| **Scope**      | One product type       | Multiple related product types               |
| **Interface**  | One creation method    | Multiple creation methods                    |
| **Complexity** | Simpler                | More complex                                 |
| **Use Case**   | Simple object creation | Ensuring consistency across product families |

## How to Extend

To add a new car brand (e.g., Mercedes):

1. Create concrete product classes:

   ```java
   class Mercedes implements Car { /* ... */ }
   class MercedesEngine implements Engine { /* ... */ }
   class MercedesInterior implements Interior { /* ... */ }
   ```

2. Create a concrete factory:

   ```java
   class MercedesFactory implements CarFactory {
       public Car createCar() { return new Mercedes(); }
       public Engine createEngine() { return new MercedesEngine(); }
       public Interior createInterior() { return new MercedesInterior(); }
   }
   ```

3. Use it in client code:
   ```java
   CarFactory mercedesFactory = new MercedesFactory();
   // Create Mercedes family products...
   ```

## Contact & Questions

For more information about design patterns, refer to the main Design Patterns documentation.
