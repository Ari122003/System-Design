# Singleton Pattern

## What is the Singleton Pattern?

The **Singleton Pattern** is a creational design pattern that ensures a class has **only one instance** and provides a **global point of access** to that instance. It is used to control object creation, limit the number of objects created from a particular class, and ensure coordinated access to unique resources.

### Key Characteristics:

- **Single Instance**: Only one object of the class can be created
- **Global Access**: The instance can be accessed globally through a static method
- **Private Constructor**: Prevents instantiation from outside the class
- **Lazy or Eager Initialization**: Can create the instance on first use or at startup

---

## Implementations & Approaches

### 1. **Basic Singleton (Eager/Lazy - Not Thread-Safe)**

#### Implementation:

```java
class BasicSingleton {

    // Step 1: Create a static instance
    private static BasicSingleton instance;

    // Step 2: Make constructor private
    private BasicSingleton() {
    }

    // Step 3: Provide global access method
    public static BasicSingleton getInstance() {
        if (instance == null) {
            instance = new BasicSingleton();
        }
        return instance;
    }
}
```

#### How It Works:

- **Step 1**: A static variable holds the unique instance
- **Step 2**: Constructor is private, preventing direct instantiation
- **Step 3**: `getInstance()` method returns the single instance, creating it on first call (lazy initialization)

#### Pros:

✅ Simple and easy to understand  
✅ Lazy initialization - instance created only when needed  
✅ Minimal memory overhead initially

#### Cons:

❌ **Not thread-safe** - Multiple threads can create multiple instances simultaneously  
❌ Race condition in multi-threaded environments  
❌ Cannot be used in concurrent applications

#### When to Use:

- Single-threaded applications only
- Demo or prototype code
- Applications where thread safety is not a concern

---

### 2. **Synchronized Singleton (Thread-Safe)**

#### Implementation:

```java
class SynchronizedSingleton {

    private static SynchronizedSingleton instance;

    private SynchronizedSingleton() {
    }

    public static synchronized SynchronizedSingleton getInstance() {
        if (instance == null) {
            instance = new SynchronizedSingleton();
        }
        return instance;
    }
}
```

#### How It Works:

- Uses the `synchronized` keyword on the `getInstance()` method
- Only one thread can execute the method at a time
- Ensures thread-safe initialization of the singleton instance

#### Pros:

✅ Thread-safe - Only one thread can access at a time  
✅ Ensures only one instance is created  
✅ Simple implementation

#### Cons:

❌ **Performance overhead** - Every call to `getInstance()` is synchronized  
❌ Even after initialization, every access is locked  
❌ Unnecessary synchronization after the instance is created  
❌ Can create bottlenecks in high-concurrency scenarios

#### When to Use:

- Thread-safe applications with LOW concurrency
- When simplicity is more important than performance
- Applications where method calls are infrequent

---

### 3. **Double-Checked Locking Singleton (Optimized Thread-Safe)**

#### Implementation:

```java
class DoubleCheckedLockingSingleton {

    private static volatile DoubleCheckedLockingSingleton instance;

    private DoubleCheckedLockingSingleton() {
    }

    public static DoubleCheckedLockingSingleton getInstance() {
        if (instance == null) {
            synchronized (DoubleCheckedLockingSingleton.class) {
                if (instance == null) {
                    instance = new DoubleCheckedLockingSingleton();
                }
            }
        }
        return instance;
    }
}
```

#### How It Works:

- **First check** (without lock): Returns instance if already created (fast path)
- **Synchronized block**: Locks only when instance is null
- **Second check** (with lock): Verifies instance was not created by another thread while waiting for lock
- **volatile keyword**: Ensures visibility of changes across threads

#### Pros:

✅ Thread-safe  
✅ High performance - synchronization only during initialization  
✅ After first initialization, subsequent calls are very fast (no lock)  
✅ Suitable for high-concurrency environments

#### Cons:

❌ Complex and harder to understand  
❌ Requires understanding of volatile and synchronization  
❌ Potential issues with Java memory model in older versions (pre-Java 5)  
❌ Overkill for simple applications

#### When to Use:

- Thread-safe applications with HIGH concurrency
- Performance-critical systems
- Scenarios where the singleton is accessed frequently
- When you need lazy initialization with thread safety

---

### 4. **Bill Pugh Singleton (Best Practice - Thread-Safe & Efficient)**

#### Implementation:

```java
class Singleton {

    private Singleton() {
    }

    private static class Helper {
        private static final Singleton INSTANCE = new Singleton();
    }

    public static Singleton getInstance() {
        return Helper.INSTANCE;
    }
}
```

#### How It Works:

- Uses a **static inner class** (Helper) to hold the singleton instance
- **Class loading guarantee**: Java ensures that the static field is initialized only once when the class is loaded
- **Lazy initialization**: Helper class is loaded only when `getInstance()` is first called
- No explicit synchronization needed; JVM handles thread safety

#### Pros:

✅ **Thread-safe** - JVM handles synchronization during class loading  
✅ **Lazy initialization** - Instance created only when first accessed  
✅ High performance - No synchronization calls after initialization  
✅ **Elegant and clean** - Easy to read and understand  
✅ Works perfectly with Java's class loading mechanism  
✅ **Most recommended approach** in modern Java

#### Cons:

❌ Slightly more code than basic singleton
❌ Requires understanding class loading and inner classes
❌ Less intuitive for beginners

#### When to Use:

- **Recommended for almost all production code**
- Thread-safe applications
- Performance-sensitive applications
- Modern Java projects
- When both thread safety and lazy initialization are needed

---

## Comparison Table

| Feature             | Basic           | Synchronized    | Double-Checked Locking | Bill Pugh      |
| ------------------- | --------------- | --------------- | ---------------------- | -------------- |
| Thread-Safe         | ❌ No           | ✅ Yes          | ✅ Yes                 | ✅ Yes         |
| Lazy Initialization | ✅ Yes          | ✅ Yes          | ✅ Yes                 | ✅ Yes         |
| Performance         | ⭐⭐⭐⭐⭐      | ⭐⭐            | ⭐⭐⭐⭐               | ⭐⭐⭐⭐⭐     |
| Complexity          | ⭐⭐            | ⭐⭐⭐          | ⭐⭐⭐⭐⭐             | ⭐⭐⭐⭐       |
| Use Case            | Single-threaded | Low concurrency | High concurrency       | **Production** |

---

## Advantages of Singleton Pattern

1. **Controlled Object Creation**: Ensures exactly one instance of a class
2. **Global Access Point**: Easy access to the singleton from anywhere in application
3. **Resource Optimization**: Prevents unnecessary duplication of expensive resources (DB connections, thread pools)
4. **Lazy Initialization**: Instance created only when needed
5. **Consistency**: Single source of truth for shared state

---

## Disadvantages of Singleton Pattern

1. **Hides Dependencies**: Class dependencies are not explicit; harder to identify what a class needs
2. **Testing Difficulty**: Hard to mock in unit tests; tight coupling
3. **Multithreading Complexity**: Requires careful handling in concurrent environments
4. **Global State**: Can lead to unexpected behavior if not managed carefully
5. **Over-usage**: Often used when simpler solutions would suffice
6. **Inheritance Issues**: Cannot subclass a singleton easily
7. **Serialization Issues**: Deserialization can create multiple instances

---

## Real-World Use Cases

- **Database Connection Pool**: Single shared connection pool across the application
- **Logger**: Single logger instance used throughout the system
- **Configuration Manager**: Single instance holding application configuration
- **Thread Pools**: Single thread pool executor for the application
- **Caching Systems**: Single cache instance shared by multiple components
- **Service Locator**: Single registry for service dependencies

---

## Best Practices

1. **Use Bill Pugh Pattern** for production code - it's the most reliable and performant
2. **Make the instance immutable** if possible to avoid state-related issues
3. **Use dependency injection** instead of singleton when possible
4. **Document clearly** that the class is a singleton
5. **Avoid singletons in testing** - use mocks or optional dependencies instead
6. **Be cautious with singletons holding mutable state** - thread safety becomes critical
7. **Consider using enums** for singletons in some cases (Java best practice)

---

## Alternative: Enum Singleton (Java Best Practice)

```java
public enum SingletonEnum {
    INSTANCE;

    public void doSomething() {
        // implementation
    }
}

// Usage
SingletonEnum.INSTANCE.doSomething();
```

This is considered the **best practice in modern Java** because:

- Thread-safe by guarantee
- Serialization-safe
- Reflection-proof
- Clean and simple

---

## Summary

| Implementation             | Best For                   | Recommendation       |
| -------------------------- | -------------------------- | -------------------- |
| **Basic**                  | Learning only              | ❌ Avoid             |
| **Synchronized**           | Simple single-threaded use | ⚠️ Limited use       |
| **Double-Checked Locking** | High-concurrency scenarios | ✅ Use if needed     |
| **Bill Pugh**              | Production code            | ✅✅ **RECOMMENDED** |
| **Enum**                   | Modern Java                | ✅✅✅ **BEST**      |
