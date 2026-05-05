# Null Object Pattern

## Overview

The **Null Object Pattern** is a behavioral design pattern that provides an object as a surrogate for the lack of an object of a given type. Instead of using null values, the pattern provides a null object with default behavior. This eliminates the need for null checks throughout the code and reduces the likelihood of NullPointerExceptions. The pattern is a way to avoid explicit null handling by using polymorphism.

## Key Characteristics

- **No Null Checks**: Eliminates the need for null checks
- **Safe Substitution**: NullObject behaves like a real object
- **Default Behavior**: Provides default, harmless behavior
- **Polymorphism**: Uses inheritance/interfaces for safe substitution
- **Simplifies Client Code**: Clients don't need to check for null

## Problem it Solves

Without the Null Object Pattern, code is filled with null checks:

```java
Customer customer = getCustomer("Unknown");
if (customer != null) {
    System.out.println(customer.getName());  // Multiple null checks
} else {
    System.out.println("Not Available");
}
```

With the Null Object Pattern, no null checks are needed:

```java
Customer customer = getCustomer("Unknown");
System.out.println(customer.getName());  // Always returns a value
```

## Pattern Components

### 1. **Abstract Class/Interface**

Defines the contract for both real and null objects:

```java
abstract class Customer {
    protected String name;

    public abstract String getName();

    public abstract boolean isNull();
}
```

### 2. **Real Object**

The actual object implementation:

```java
class RealCustomer extends Customer {

    public RealCustomer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isNull() {
        return false;
    }
}
```

### 3. **Null Object**

Provides default behavior instead of null:

```java
class NullCustomer extends Customer {

    public String getName() {
        return "Not Available";
    }

    public boolean isNull() {
        return true;
    }
}
```

### 4. **Factory**

Returns either real or null objects:

```java
class CustomerFactory {

    private static final String[] names = { "Aritra", "Rahul", "Soham" };

    public static Customer getCustomer(String name) {
        for (String n : names) {
            if (n.equalsIgnoreCase(name)) {
                return new RealCustomer(name);
            }
        }
        return new NullCustomer(); // No null return!
    }
}
```

## How It Works

The Null Object Pattern provides a safe default behavior:

1. **Factory Method**: Called to get a customer
2. **Object Search**: Looks for matching customer in the list
3. **Return Decision**: Returns RealCustomer if found, NullCustomer otherwise
4. **Client Usage**: Client can use either without null checks
5. **Default Behavior**: NullCustomer provides harmless defaults

## Usage Example

```java
Customer c1 = CustomerFactory.getCustomer("Aritra");
Customer c2 = CustomerFactory.getCustomer("Unknown");

System.out.println(c1.getName()); // Aritra
System.out.println(c2.getName()); // Not Available

// No null check needed!
```

### Output:

```
Aritra
Not Available
```

Both calls work without null checking logic.

## Null Object Pattern Workflow

```
CustomerFactory.getCustomer("Unknown")
    ↓
Search in names array
    ├─→ Found? → return new RealCustomer(name)
    └─→ Not Found? → return new NullCustomer()
    ↓
Client receives either RealCustomer or NullCustomer
    ↓
Call getName() on either object
    ├─→ RealCustomer.getName() → returns actual name
    └─→ NullCustomer.getName() → returns "Not Available"
```

## Null Checking Comparison

### Without Null Object Pattern:

```java
Customer customer = getCustomer(name);
if (customer != null) {
    String customerName = customer.getName();
} else {
    String customerName = "Not Available";
}
// Complex logic with repeated null checks
```

### With Null Object Pattern:

```java
Customer customer = getCustomer(name);
String customerName = customer.getName();  // Always works!
// Simple, clean code
```

## Type Checking vs Null Checking

```
Without Pattern:
┌────────────────────┐
│ Get Customer       │
├────────────────────┤
│ if (customer !=    │ ← Null check
│     null) {        │
│   ...              │
│ }                  │
└────────────────────┘

With Pattern:
┌────────────────────┐
│ Get Customer       │
├────────────────────┤
│ if (customer       │ ← Type check (optional)
│   .isNull()) {     │
│   ...              │
│ }                  │
└────────────────────┘
```

## Advantages

✅ **No Null Checks**: Eliminates defensive null checking  
✅ **Cleaner Code**: Simpler, more readable code  
✅ **Safe Defaults**: Provides safe, predictable behavior  
✅ **Reduced Bugs**: Fewer NullPointerExceptions  
✅ **Polymorphism**: Uses object-oriented principles  
✅ **Easy to Extend**: Adding new behaviors is straightforward

## Disadvantages

❌ **Extra Objects**: Creates additional null objects in memory  
❌ **Potential Confusion**: May hide actual null problems  
❌ **Type Checking**: Still need `isNull()` checks sometimes  
❌ **Overhead**: Small performance overhead from polymorphism  
❌ **Not Always Appropriate**: Not suitable for all situations

## Real-World Examples

- **User Authentication**: Return NullUser instead of null if not authenticated
- **Database Queries**: Return NullRecord instead of null for missing records
- **Collection Operations**: Return empty collections instead of null
- **Logging Systems**: NullLogger for disabled logging
- **Configuration Objects**: NullConfig with default values
- **Service Implementations**: NullService for missing services
- **Iterator Pattern**: Empty iterator instead of null
- **Optional Values**: Null object pattern predecessor to Java Optional

## Use Cases

- When you want to avoid null checks throughout the code
- When missing objects should have default/safe behavior
- When you want to use polymorphism instead of conditionals
- When creating user-friendly error handling
- When building robust systems that handle missing data gracefully

## Null Object vs Other Patterns

| Pattern           | Purpose                                      | Key Difference                     |
| ----------------- | -------------------------------------------- | ---------------------------------- |
| **Null Object**   | Provide default behavior for missing objects | Polymorphic null replacement       |
| **Optional**      | Encapsulate optional values                  | Explicit presence/absence handling |
| **Strategy**      | Encapsulate algorithms                       | Different algorithm selection      |
| **Decorator**     | Add behavior to objects                      | Wraps single object                |
| **Default Value** | Provide fallback values                      | Direct value assignment            |

## Implementation Approaches

### 1. **Boolean Indicator** (What we implemented)

```java
abstract class Customer {
    public abstract boolean isNull();
}

class NullCustomer extends Customer {
    public boolean isNull() {
        return true;
    }
}
```

### 2. **Class Type Checking**

```java
if (customer instanceof NullCustomer) {
    // Handle null case
}
```

### 3. **Optional Pattern** (Java 8+)

```java
Optional<Customer> customer = Optional.ofNullable(getCustomer(name));
customer.ifPresent(c -> System.out.println(c.getName()));
```

### 4. **Supplier Pattern**

```java
Supplier<Customer> getCustomer = () ->
    names.contains(name) ? new RealCustomer(name) : new NullCustomer();
```

## Null Object Variations

### 1. **Simple Null Object**

Returns default values:

```java
class NullCustomer extends Customer {
    public String getName() {
        return "Unknown";
    }
}
```

### 2. **Logging Null Object**

Logs actions that would be taken:

```java
class LoggingNullCustomer extends Customer {
    public String getName() {
        System.out.println("WARNING: Attempting to access null customer");
        return "Unknown";
    }
}
```

### 3. **Throwing Null Object**

Throws exceptions for certain operations:

```java
class StrictNullCustomer extends Customer {
    public String getName() {
        throw new IllegalStateException("Customer does not exist");
    }
}
```

## Common Null Object Implementations

### Collection Example:

```java
List<String> items = getItems();
// Without Null Object - returns null
if (items != null) {
    for (String item : items) {
        // process
    }
}

// With Null Object - returns empty list
List<String> items = getItems();  // Returns empty list if null
for (String item : items) {
    // process - works with empty list
}
```

### Database Query Example:

```java
// Without Null Object
User user = database.find(id);
if (user != null) {
    String email = user.getEmail();
}

// With Null Object
User user = database.find(id);  // Returns NullUser if not found
String email = user.getEmail();  // Returns default email
```

## Best Practices

✅ **Implement Interface Completely**: Implement all abstract methods  
✅ **Default Behavior**: Choose sensible defaults for null object  
✅ **Document Behavior**: Clearly document null object behavior  
✅ **Use Factory**: Factory pattern manages null object creation  
✅ **Consider Logging**: Log when null objects are used  
✅ **Type Safe**: Use `isNull()` method instead of type checking  
✅ **Immutable**: Make null objects immutable when possible

## When NOT to Use Null Object Pattern

- When null has semantic meaning (like representing "unknown" intentionally)
- When you need to distinguish between missing and empty
- When null represents a specific state you need to track
- When performance is critical and null objects add overhead
- When the absence of an object needs special handling

## Java Optional vs Null Object Pattern

| Aspect          | Null Object              | Optional                      |
| --------------- | ------------------------ | ----------------------------- |
| **Approach**    | Polymorphic substitution | Wrapper/container             |
| **Null Checks** | None required            | Use `ifPresent()`, `orElse()` |
| **Type System** | Return same type         | Return Optional wrapper       |
| **Clarity**     | Implicit null handling   | Explicit presence checking    |
| **Use Case**    | Polymorphic behavior     | Optional value representation |

## Advanced Example: Null Logger

```java
interface Logger {
    void log(String message);
}

class ConsoleLogger implements Logger {
    public void log(String message) {
        System.out.println("[LOG] " + message);
    }
}

class NullLogger implements Logger {
    public void log(String message) {
        // Do nothing - silent logging
    }
}

class Application {
    private Logger logger;

    public Application(Logger logger) {
        this.logger = logger != null ? logger : new NullLogger();
    }

    public void run() {
        logger.log("Starting application");  // Works with or without logger
        // ... more code
    }
}

// Usage
Application app = new Application(null);  // Uses NullLogger silently
app.run();
```

## Advanced Example: Null Service

```java
interface DataService {
    Data fetchData(String id);
    void saveData(Data data);
}

class RealDataService implements DataService {
    public Data fetchData(String id) {
        // Actual database call
        return database.query(id);
    }

    public void saveData(Data data) {
        database.save(data);
    }
}

class NullDataService implements DataService {
    public Data fetchData(String id) {
        return new Data("No data available");
    }

    public void saveData(Data data) {
        // Do nothing - silent failure
    }
}

// Usage in tests or offline mode
DataService service = new NullDataService();
Data data = service.fetchData("123");  // Returns default data
```

## Related Patterns

- **Factory Pattern**: Often used to create null objects
- **Strategy Pattern**: Null object can implement strategy pattern
- **Decorator Pattern**: Can be combined with null object
- **Optional Pattern**: Modern Java approach to handling nulls
- **Command Pattern**: Null commands perform no action
- **Singleton Pattern**: Null objects often implemented as singletons
