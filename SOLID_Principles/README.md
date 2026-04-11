# SOLID Principles

SOLID Principles are 5 important design principles in Object-Oriented Programming (OOP) that help you write clean, maintainable, scalable, and flexible code.

Think of SOLID like rules for writing good code architecture, especially useful in Java (which you’re focusing on).

# What is SOLID?

SOLID =

S → Single Responsibility Principle
O → Open/Closed Principle
L → Liskov Substitution Principle
I → Interface Segregation Principle
D → Dependency Inversion Principle

## 1. Single Responsibility Principle

A class should have only one reason to change.

Example: [Single_Responsibility.java](Single_Responsibility.java)

### Bad Design

This design violates the principle because it combines multiple responsibilities into a single class. For example, `UserService` is responsible for both saving users and sending emails. This makes the class harder to maintain and test, as changes to one responsibility might affect the other.

```java
class UserService {
    void saveUser() {
        // Save user logic
    }

    void sendEmail() {
        // Send email logic
    }
}
```

### Good Design

This design adheres to the principle by separating responsibilities into distinct classes. `UserService` handles user-related logic, while `EmailService` is solely responsible for email-related logic. This separation makes the code easier to maintain, test, and extend.

```java
class UserService {
    void saveUser() {
        // Save user logic
    }
}

class EmailService {
    void sendEmail() {
        // Send email logic
    }
}
```

## 2. Open/Closed Principle

Software entities should be open for extension, but closed for modification.

Example: [Open_Close_Principle.java](Open_Close_Principle.java)

### Bad Design

This design violates the principle because it mixes the logic for different discount types within a single class. Adding a new discount type requires modifying the existing `Discount` class, which can introduce bugs and violates the Open/Closed Principle.

```java
class Discount {
    double calculate(String type) {
        if (type.equals("DIWALI")) {
            return 10;
        }
        if (type.equals("NEWUSER")) {
            return 20;
        }
        return 0;
    }
}
```

### Good Design

This design adheres to the principle by using an interface and separate classes for each discount type. Adding a new discount type only requires creating a new class that implements the `Discount` interface, without modifying existing code.

```java
interface Discount {
    double calculate();
}

class DiwaliDiscount implements Discount {
    public double calculate() {
        return 10;
    }
}

class NewUserDiscount implements Discount {
    public double calculate() {
        return 20;
    }
}
```

## 3. Liskov Substitution Principle

Subtypes should be replaceable by their base types without breaking the program.

Example: [Liskov_Substitution_Principle.java](Liskov_Substitution_Principle.java)

### Bad Design

This design violates the principle because the `Ostrich` class cannot fulfill the contract of the `Bird` class. The `fly` method in `Ostrich` throws an exception, which breaks the expectation that all `Bird` subclasses can fly.

```java
class Bird {
    void fly() {
        // Fly logic
    }
}

class Ostrich extends Bird {
    void fly() {
        throw new UnsupportedOperationException();
    }
}
```

### Good Design

This design adheres to the principle by separating the behavior into appropriate classes. The `Bird` class represents general bird behavior, while specific behaviors like flying are handled by subclasses that can actually perform those actions.

```java
class Bird {
    // Common bird properties
}

class FlyingBird extends Bird {
    void fly() {
        // Fly logic
    }
}

class Ostrich extends Bird {
    // Ostrich-specific properties
}

class Sparrow extends FlyingBird {
    // Sparrow-specific properties
}
```

## 4. Interface Segregation Principle

A class should not be forced to implement interfaces it does not use.

Example: [Interface_Segregation_Principle.java](Interface_Segregation_Principle.java)

### Bad Design

This design violates the principle because the `Worker` interface forces all implementing classes to define methods they might not need. For example, `Robot` has to implement the `eat` method, which is irrelevant to its functionality.

```java
interface Worker {
    void work();
    void eat();
}

class Robot implements Worker {
    public void work() {
        // Work logic
    }

    public void eat() {
        // Useless for Robot
    }
}
```

### Good Design

This design adheres to the principle by splitting the `Worker` interface into smaller, more specific interfaces. Classes like `Robot` only implement the methods they need, avoiding unnecessary code.

```java
interface Workable {
    void work();
}

interface Eatable {
    void eat();
}

class Robot implements Workable {
    public void work() {
        // Work logic
    }
}

class Human implements Workable, Eatable {
    public void work() {
        // Work logic
    }

    public void eat() {
        // Eat logic
    }
}
```

## 5. Dependency Inversion Principle

High-level modules should not depend on low-level modules. Both should depend on abstractions.

Example: [Dependacy_Inversion_Principle.java](Dependacy_Inversion_Principle.java)

### Bad Design

This design violates the principle because the `UserService` class directly depends on the `MySQLDatabase` class. This tight coupling makes it difficult to switch to a different database implementation without modifying the `UserService` class.

```java
class MySQLDatabase {
    void connect() {
        // Connection logic
    }
}

class UserService {
    MySQLDatabase db = new MySQLDatabase();

    void performOperation() {
        db.connect();
    }
}
```

### Good Design

This design adheres to the principle by introducing an abstraction (`Database` interface). The `UserService` class depends on the `Database` abstraction, allowing it to work with any database implementation without requiring changes.

```java
interface Database {
    void connect();
}

class MySQLDatabase implements Database {
    public void connect() {
        // Connection logic
    }
}

class UserService {
    Database db;

    UserService(Database db) {
        this.db = db;
    }
}
```
