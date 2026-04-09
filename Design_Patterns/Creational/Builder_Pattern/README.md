# Builder Pattern

## Overview

The **Builder Pattern** is a creational design pattern that separates the construction of a complex object from its representation, allowing the same construction process to create different representations. Instead of creating an object all at once with a large constructor, it builds the object step-by-step through method chaining.

## Key Characteristics

- **Purpose**: Construct complex objects step-by-step
- **Type**: Creational Pattern
- **Problem**: When an object has many optional and required parameters, constructors become messy
- **Solution**: Use a builder to set properties one at a time and finally build the object

## When to Use

✅ Objects have many optional parameters  
✅ Creating multiple representations of an object  
✅ Constructors need too many parameters (avoid constructor telescoping)  
✅ Object creation requires multiple steps  
✅ Object is immutable after creation

## How It Actually Works - Step by Step

### Step 1: Private Constructor

The main class (`User`) has a **private constructor** that only accepts a `Builder` object:

```java
class User {
    private String name;
    private int age;
    private String email;
    private String phone;

    // Private constructor - prevents direct instantiation
    private User(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.email = builder.email;
        this.phone = builder.phone;
    }
}
```

**Why private?** This forces users to go through the Builder class. Direct instantiation is impossible.

---

### Step 2: Static Nested Builder Class

A static nested class handles object construction:

```java
public static class Builder {
    // Required fields
    private String name;
    private int age;

    // Optional fields
    private String email;
    private String phone;

    // Constructor for required fields
    public Builder(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
```

**Key points:**

- **Required fields** (`name`, `age`) in builder constructor
- **Optional fields** (`email`, `phone`) initialized to null
- **Static nested** means you access it as `User.Builder`

---

### Step 3: Method Chaining (Setter Methods)

Each builder method returns `this` to enable chaining:

```java
public Builder setEmail(String email) {
    this.email = email;
    return this;  // Return this for chaining
}

public Builder setPhone(String phone) {
    this.phone = phone;
    return this;  // Return this for chaining
}
```

**Flow:**

```
new Builder("John", 25)     // Returns Builder object
    .setEmail("john@...")   // Returns same Builder
    .setPhone("123...")     // Returns same Builder
    .build()                // Builds the final User object
```

---

### Step 4: Build Method

The `build()` method creates the final object:

```java
public User build() {
    return new User(this);  // Pass the builder to User constructor
}
```

This method:

- Takes all accumulated state from the builder
- Passes itself to User's private constructor
- Returns the fully constructed `User` object

---

## Complete Workflow Example

```java
// Step 1: Create builder with required fields
User user = new User.Builder("Aritra", 22)

            // Step 2: Optional fields (method chaining)
            .setEmail("aritra@gmail.com")
            .setPhone("1234567890")

            // Step 3: Build the object
            .build();

System.out.println(user);
```

### What happens internally:

```
1. new User.Builder("Aritra", 22)
   └─ Creates Builder object with name="Aritra", age=22

2. .setEmail("aritra@gmail.com")
   └─ Sets email in builder, returns this (same Builder)

3. .setPhone("1234567890")
   └─ Sets phone in builder, returns this (same Builder)

4. .build()
   └─ Calls new User(this)
   └─ User private constructor copies all fields from builder
   └─ Returns fully constructed User object
```

---

## Architectural Components

### 1. **Director** (The Client)

```java
public class Main {
    public static void main(String[] args) {
        User user = new User.Builder("Aritra", 22)
                .setEmail("aritra@gmail.com")
                .setPhone("1234567890")
                .build();
    }
}
```

Client code that uses the builder.

### 2. **Builder** (Construction Pattern)

```java
public static class Builder {
    private String name;
    private int age;
    private String email;
    private String phone;

    // Required parameters in constructor
    public Builder(String name, int age) { ... }

    // Optional parameters via setters
    public Builder setEmail(String email) { ... }
    public Builder setPhone(String phone) { ... }

    // Final construction
    public User build() { ... }
}
```

### 3. **Product** (The Complex Object)

```java
class User {
    private String name;
    private int age;
    private String email;
    private String phone;

    private User(Builder builder) { ... }  // Only built by Builder
}
```

---

## Comparison: With vs Without Builder

### Without Builder (Constructor Telescoping)

```java
// Too many overloaded constructors!
User user1 = new User("John", 25);
User user2 = new User("John", 25, "john@email.com");
User user3 = new User("John", 25, "john@email.com", "123456");
User user4 = new User("John", 25, null, "123456"); // What's null? Confusing!
```

### With Builder (Clean & Readable)

```java
User user = new User.Builder("John", 25)
        .setEmail("john@email.com")
        .setPhone("123456")
        .build();
```

---

## Advantages

| Advantage                 | Explanation                                     |
| ------------------------- | ----------------------------------------------- |
| **Readability**           | Clear which field is being set via method names |
| **Flexibility**           | Easy to add/remove optional fields              |
| **Immutability**          | Can create immutable objects after building     |
| **No Null Checks**        | Only required fields are mandatory              |
| **Method Chaining**       | Fluent, elegant API                             |
| **Single Responsibility** | Separation of construction from representation  |

---

## Disadvantages

| Disadvantage             | Explanation                                          |
| ------------------------ | ---------------------------------------------------- |
| **More Code**            | Requires a nested Builder class and extra methods    |
| **Performance Overhead** | Creates intermediate Builder object                  |
| **Complexity**           | Overkill for simple objects with few parameters      |
| **Memory Usage**         | Builder object remains in memory during construction |

---

## Real-World Applications

- **StringBuilder**: Java's `StringBuilder` uses this pattern to build strings efficiently
- **Thread Pools**: Creating thread pool configurations with many optional parameters
- **HTTP Requests**: Libraries like OkHttp or Retrofit use builders for configuring requests
- **Configuration Objects**: Complex application configurations with many optional settings
- **UI Components**: Building UI elements with various optional properties
- **Database Queries**: Query builders that allow progressive query customization
- **JSON Objects**: Creating JSON payloads with optional fields

---

## Step-by-Step Execution Trace

For the code:

```java
User user = new User.Builder("Aritra", 22)
        .setEmail("aritra@gmail.com")
        .setPhone("1234567890")
        .build();
```

**Execution trace:**

| Step | Operation                        | Builder State                                                         |
| ---- | -------------------------------- | --------------------------------------------------------------------- |
| 1    | `new User.Builder("Aritra", 22)` | `name="Aritra", age=22, email=null, phone=null`                       |
| 2    | `.setEmail("aritra@gmail.com")`  | `name="Aritra", age=22, email="aritra@gmail.com", phone=null`         |
| 3    | `.setPhone("1234567890")`        | `name="Aritra", age=22, email="aritra@gmail.com", phone="1234567890"` |
| 4    | `.build()`                       | Creates User with all values from builder                             |

---

## Variations

### 1. **With Validation**

```java
public User build() {
    if (name == null || name.isEmpty()) {
        throw new IllegalArgumentException("Name is required");
    }
    if (age < 0 || age > 150) {
        throw new IllegalArgumentException("Age must be between 0 and 150");
    }
    return new User(this);
}
```

### 2. **With Defaults**

```java
public Builder(String name, int age) {
    this.name = name;
    this.age = age;
    this.email = "";      // Default value
    this.phone = "";      // Default value
}
```

### 3. **Copy Constructor (Clone via Builder)**

```java
public User(User other) {
    this(new Builder(other.name, other.age)
        .setEmail(other.email)
        .setPhone(other.phone));
}
```

---

## When NOT to Use

❌ For simple objects with few parameters  
❌ When you don't have optional parameters  
❌ In performance-critical code creating millions of objects  
❌ When the object structure is very simple

---

## Summary

The **Builder Pattern** provides:

1. **Clarity**: Clear construction steps with method names
2. **Flexibility**: Easy to handle optional parameters
3. **Immutability**: Objects are immutable after creation
4. **Maintainability**: Adding new fields doesn't break existing code
5. **Readability**: Fluent API eliminates the "which parameter is null?" confusion

**Key Takeaway**: Use Builder when constructing objects with many optional parameters or multiple steps. It trades a bit of code for significant clarity and flexibility.

---

## Related Patterns

- **Factory Pattern**: Both are creational, but Factory creates objects, Builder constructs them step-by-step
- **Strategy Pattern**: Can use builders to configure different strategies
- **Composite Pattern**: Builders often used with composite objects

---

## Reference

For more design patterns, visit the main Design Patterns documentation.
