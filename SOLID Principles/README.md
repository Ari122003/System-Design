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

```java
class UserService {
    void saveUser() {
    }
}

class EmailService {
    void sendEmail() {
    }
}

public class Single_Responsibility {
    public static void main(String[] args) {
        System.out.println("Single Responsibility Principle");
    }
}
```

### Problem in Bad Design

In the bad design, a single `UserService` class is responsible for both saving users and sending emails. This violates the principle because if the email-sending logic changes, the `UserService` class also needs to be modified.

### Solution in Good Design

The good design separates these responsibilities into two classes: `UserService` for saving users and `EmailService` for sending emails. This ensures that changes to email logic do not affect user-related functionality.

## 2. Open/Closed Principle

Software entities should be open for extension, but closed for modification.

Example: [Open_Close_Principle.java](Open_Close_Principle.java)

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

public class Open_Close_Principle {
    public static void main(String[] a) {

    }
}
```

### Problem in Bad Design

The bad design uses `if` checks inside a single `Discount` class to handle different discount types. Adding a new discount type requires modifying the existing class, which can introduce bugs and violates the principle.

### Solution in Good Design

The good design introduces an interface `Discount` and separate classes like `DiwaliDiscount` and `NewUserDiscount` that implement this interface. This allows new discount types to be added without modifying existing code.

## 3. Liskov Substitution Principle

Subtypes should be replaceable by their base types without breaking the program.

Example: [Liskov_Substitution_Principle.java](Liskov_Substitution_Principle.java)

```java
class Bird {
    void fly() {
    }
}

class FlyingBird extends Bird {
    void fly() {
    }
}

class Ostrich extends Bird {
}

class Sparrow extends FlyingBird {
}

public class Liskov_Substitution_Principle {
    public static void main(String[] a) {

    }
}
```

### Problem in Bad Design

In the bad design, `Ostrich` extends `Bird` and overrides the `fly()` method to throw an exception. This breaks the program if `Ostrich` is used where a `Bird` is expected.

### Solution in Good Design

The good design introduces a `FlyingBird` class for birds that can fly, and `Ostrich` does not override `fly()`. This ensures that `Ostrich` can safely replace `Bird` without breaking the program.

## 4. Interface Segregation Principle

A class should not be forced to implement interfaces it does not use.

Example: [Interface_Segregation_Principle.java](Interface_Segregation_Principle.java)

```java
interface Workable {
    void work();
}

interface Eatable {
    void eat();
}

class Robot implements Workable {
    public void work() {
    }
}

class Human implements Workable, Eatable {
    public void work() {
    }

    public void eat() {
    }
}

public class Interface_Segregation_Principle {

}
```

### Problem in Bad Design

The bad design combines `work()` and `eat()` methods in a single `Worker` interface. This forces `Robot` to implement an `eat()` method it does not use.

### Solution in Good Design

The good design splits the `Worker` interface into `Workable` and `Eatable` interfaces. `Robot` implements only `Workable`, while `Human` implements both. This avoids forcing classes to implement unused methods.

## 5. Dependency Inversion Principle

High-level modules should not depend on low-level modules. Both should depend on abstractions.

Example: [Dependacy_Inversion_Principle.java](Dependacy_Inversion_Principle.java)

```java
interface Database {
    void connect();
}

class MySQLDatabase implements Database {
    public void connect() {
    }
}

class UserService {
    Database db;

    UserService(Database db) {
        this.db = db;
    }
}

public class Dependacy_Inversion_Principle {

}
```

### Problem in Bad Design

In the bad design, `UserService` is directly coupled to `MySQLDatabase`. If the database implementation changes, the `UserService` class must also change.

### Solution in Good Design

The good design introduces a `Database` interface, and `UserService` depends on this abstraction. This allows the database implementation to change without affecting `UserService`.

## Summary

The five SOLID principles help make code easier to maintain, extend, and test:

- Single Responsibility: one class, one job
- Open/Closed: extend behavior without changing existing code
- Liskov Substitution: subclasses must work anywhere their parent type is expected
- Interface Segregation: keep interfaces small and focused
- Dependency Inversion: depend on abstractions, not concrete implementations

You can use the Java files in this folder as quick reference examples for each principle.
