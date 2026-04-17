# Structural Design Patterns

## Overview

Structural design patterns deal with object composition and relationships between entities. They help form larger structures by combining classes and objects while keeping these structures flexible and efficient. These patterns focus on how classes and objects are combined to form larger structures and how they interact with each other.

## Key Aspects

### 1. **Object Composition**

- Structural patterns use composition to create new functionality by combining existing objects and classes
- They organize relationships between entities to achieve new capabilities without modifying their individual structures
- Emphasize creating complex structures from simpler building blocks

### 2. **Decoupling & Flexibility**

- Separate the abstraction from the implementation details
- Allow structures to be composed dynamically at runtime
- Reduce dependencies between components for better flexibility and maintainability

### 3. **Reusability**

- Existing classes and objects can be reused in new contexts
- Avoid code duplication by wrapping and extending existing functionality
- Promote DRY (Don't Repeat Yourself) principle

### 4. **Inheritance vs Composition Trade-offs**

- Prefer composition over inheritance for better flexibility
- Reduce class hierarchies and tight coupling
- Allow runtime changes without modifying class definitions

## Common Structural Design Patterns

### **1. Adapter Pattern**

- **Purpose**: Convert the interface of a class into another interface expected by clients
- **Use Case**: Making incompatible interfaces work together
- **Key Benefit**: Enables integration of classes that wouldn't otherwise work together

### **2. Bridge Pattern**

- **Purpose**: Decouple an abstraction from its implementation so they can vary independently
- **Use Case**: When you want to extend both abstract and concrete classes
- **Key Benefit**: Reduces the class explosion problem caused by multiple hierarchies

### **3. Composite Pattern**

- **Purpose**: Compose objects into tree structures to represent part-whole hierarchies
- **Use Case**: Building tree-like structures (directories, UI components, organizational hierarchies)
- **Key Benefit**: Allows clients to treat individual objects and compositions uniformly

### **4. Decorator Pattern**

- **Purpose**: Attach additional responsibilities to an object dynamically
- **Use Case**: Adding features to objects without modifying their structure
- **Key Benefit**: Provides a flexible alternative to subclassing for extending functionality

### **5. Facade Pattern**

- **Purpose**: Provide a unified, simplified interface to a set of interfaces in a subsystem
- **Use Case**: Simplifying complex systems with many interconnected components
- **Key Benefit**: Reduces complexity and improves code readability by hiding internal details

### **6. Flyweight Pattern**

- **Purpose**: Use sharing to support large numbers of fine-grained objects efficiently
- **Use Case**: Reducing memory consumption for objects that are frequently created
- **Key Benefit**: Optimizes memory usage by sharing common data between objects

### **7. Proxy Pattern**

- **Purpose**: Provide a surrogate or placeholder for another object to control access to it
- **Use Case**: Lazy initialization, access control, logging, caching
- **Key Benefit**: Allows control over object access without changing the object itself

## Design Principles

### **Single Responsibility Principle (SRP)**

- Each pattern component has a single, well-defined responsibility
- Makes the code easier to understand and maintain

### **Open/Closed Principle (OCP)**

- Structures are open for extension through composition
- Closed for modification of existing code

### **Dependency Inversion Principle (DIP)**

- Depend on abstractions rather than concrete implementations
- Allows flexible composition and substitution

## When to Use Structural Patterns

✅ **Use when you need to:**

- Combine multiple objects or classes into larger structures
- Add new functionality to objects without changing their structure
- Simplify complex class hierarchies
- Improve code reusability and flexibility
- Control how objects interact with each other
- Reduce memory consumption or improve performance

❌ **Avoid when:**

- The added complexity doesn't provide clear benefits
- The structure is simple and unlikely to change
- Performance overhead isn't justified by the flexibility gained

## Implementation Considerations

1. **Composition Strategy**: Decide how to combine objects effectively
2. **Interface Design**: Create clear and consistent interfaces between components
3. **Runtime Flexibility**: Ensure patterns allow runtime configuration when needed
4. **Performance**: Consider the overhead of additional layers of abstraction
5. **Maintainability**: Balance flexibility with code clarity and simplicity

## Comparison with Other Pattern Types

| Pattern Type   | Focus              | Complexity  | Flexibility       |
| -------------- | ------------------ | ----------- | ----------------- |
| **Creational** | Object creation    | Low-Medium  | Creation time     |
| **Structural** | Object composition | Medium-High | Runtime structure |
| **Behavioral** | Object interaction | Medium-High | Communication     |

## Key Takeaways

- Structural patterns provide elegant solutions for organizing complex systems
- They promote code reusability and flexibility through composition
- Understanding these patterns helps in designing scalable and maintainable code
- Choose the right pattern based on your specific problem and requirements
- Often used in real-world frameworks (collections, streams, UI components)

## References

For detailed implementations of each pattern, explore the individual pattern folders in this directory. Each contains complete code examples and detailed explanations.
