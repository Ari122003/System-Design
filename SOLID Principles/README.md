# SOLID Principles

This folder contains small Java examples for all five SOLID principles. Each file shows the idea in a compact form, with a commented bad design and a better design.

## 1. Single Responsibility Principle

A class should have only one reason to change.

Example: [Single_Responsibility.java](Single_Responsibility.java)

In this file, `UserService` only saves users, while `EmailService` only sends emails. This separates responsibilities instead of putting both actions into one class.

## 2. Open/Closed Principle

Software entities should be open for extension, but closed for modification.

Example: [Open_Close_Principle.java](Open_Close_Principle.java)

The bad design uses `if` checks inside a single `Discount` class. The better design introduces `DiwaliDiscount` and `NewUserDiscount` classes that implement the `Discount` interface, so new discount types can be added without changing the existing logic.

## 3. Liskov Substitution Principle

Subtypes should be replaceable by their base types without breaking the program.

Example: [Liskov_Substitution_Principle.java](Liskov_Substitution_Principle.java)

The file shows why forcing every bird to fly is a bad design. `Ostrich` does not need to override `fly()` with unsupported behavior. Instead, `FlyingBird` is used for birds that can fly, and `Sparrow` extends it safely.

## 4. Interface Segregation Principle

A class should not be forced to implement methods it does not use.

Example: [Interface_Segregation_Principle.java](Interface_Segregation_Principle.java)

The bad design combines `work()` and `eat()` in one `Worker` interface. The better design splits them into `Workable` and `Eatable`, so `Robot` only implements `Workable` and `Human` implements both.

## 5. Dependency Inversion Principle

High-level modules should depend on abstractions, not on concrete classes.

Example: [Dependacy_Inversion_Principle.java](Dependacy_Inversion_Principle.java)

The bad design directly couples `UserService` to `MySQLDatabase`. The better design introduces a `Database` interface, and `UserService` depends on that abstraction instead of a specific database class.

## Summary

The five SOLID principles help make code easier to maintain, extend, and test:

- Single Responsibility: one class, one job
- Open/Closed: extend behavior without changing existing code
- Liskov Substitution: subclasses must work anywhere their parent type is expected
- Interface Segregation: keep interfaces small and focused
- Dependency Inversion: depend on abstractions, not concrete implementations

You can use the Java files in this folder as quick reference examples for each principle.
