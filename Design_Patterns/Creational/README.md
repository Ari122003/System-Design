In Low-Level Design (LLD), Creational Design Patterns are a category of design patterns that deal with object creation mechanisms—how objects are created in a flexible, reusable, and controlled way.

Instead of creating objects directly using new, these patterns abstract the instantiation process, making your code more scalable, maintainable, and loosely coupled.

🔹 What are Creational Design Patterns?

Creational patterns focus on:

- How objects are created
- Hiding creation logic
- Improving flexibility in object creation

👉 Without these patterns:

```java
Car car = new Car();
```

👉 With creational patterns:

- Object creation is centralized
- You can change implementation without touching client code

🔹 Why do we need them?

- Avoid tight coupling between classes
- Improve code reusability
- Control object lifecycle
- Make code easier to extend and maintain

🔹 Types of Creational Design Patterns

There are 5 main creational patterns:

1. Singleton
2. Factory Method
3. Abstract Factory
4. Builder
5. Prototype
