# Prototype Pattern

## Overview

The **Prototype Pattern** is a creational design pattern that lets you copy existing objects without making your code dependent on their classes. It's used to create new objects by cloning an existing object (the prototype) rather than creating from scratch.

## Key Concepts

### What Problem Does It Solve?

- Creating objects that are expensive to construct can be costly in terms of memory and computation
- The Prototype Pattern allows you to avoid the overhead by cloning an existing object instead of creating a new one from scratch
- Useful when you need multiple similar objects with slight variations

### When to Use It

1. When object creation is expensive or complex
2. When you need to create many similar objects
3. When you want to avoid subclassing for creating variations of objects
4. When you need to work with a copy of an object independently

## Implementation Types

### 1. Shallow Copy

A shallow copy creates a new object but does not recursively copy nested objects. The cloned object shares references to the same nested objects as the original.

**Example from Main.java:**

```java
class User implements Cloneable {
    String name;
    int age;

    User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone(); // shallow copy
    }
}
```

**Usage:**

```java
User original = new User("John", 25);
User cloned = (User) original.clone();
```

⚠️ **Issue with Shallow Copy:** If User had a mutable reference type (like an Address object), modifications to that object would affect both the original and cloned instances.

### 2. Deep Copy

A deep copy creates a new object and recursively copies all nested objects. This ensures complete independence between the original and cloned objects.

**Example from Main.java:**

```java
class Address {
    String city;

    Address(String city) {
        this.city = city;
    }
}

class Customer implements Cloneable {
    String name;
    Address address;

    Customer(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Customer cloned = (Customer) super.clone();
        cloned.address = new Address(this.address.city); // deep copy
        return cloned;
    }
}
```

**Usage:**

```java
Address addr = new Address("New York");
Customer original = new Customer("Alice", addr);
Customer cloned = (Customer) original.clone();

// Modifying cloned.address will NOT affect original.address
cloned.address.city = "Boston";
```

✅ **Benefit of Deep Copy:** The cloned object is completely independent from the original, ensuring no unintended side effects.

## Key Differences

| Feature        | Shallow Copy             | Deep Copy              |
| -------------- | ------------------------ | ---------------------- |
| Nested Objects | Shared references        | New copies             |
| Independence   | Partial                  | Complete               |
| Performance    | Faster                   | Slower                 |
| Memory Usage   | Less                     | More                   |
| When to Use    | Immutable nested objects | Mutable nested objects |

## Advantages

- ✅ Avoids expensive object creation
- ✅ Better performance when cloning complex objects
- ✅ Reduces coupling between classes
- ✅ Simplifies object creation for variations

## Disadvantages

- ❌ May be overkill for simple objects
- ❌ Requires implementing Cloneable interface
- ❌ Can be tricky with circular references
- ❌ Requires careful consideration of shallow vs deep copy

## Real-World Analogy

Think of a prototype as a template. Instead of designing a new product from scratch every time, you take an existing product (prototype) as a template and make a copy with minor modifications. This is much faster than designing everything from the beginning.

## Java Implementation Notes

In Java, the Prototype Pattern is typically implemented through:

1. **Cloneable Interface**: Must be implemented by classes that can be cloned
2. **clone() Method**: Should be overridden from `Object` class
3. **CloneNotSupportedException**: Thrown if cloning is not supported

```java
User user = new User("John", 25);
try {
    User clonedUser = (User) user.clone();
} catch (CloneNotSupportedException e) {
    e.printStackTrace();
}
```

## Conclusion

The Prototype Pattern is most useful when you need to create copies of complex objects efficiently. Always remember to implement **deep copy** when your objects contain mutable reference types to ensure complete independence between the original and cloned instances.
