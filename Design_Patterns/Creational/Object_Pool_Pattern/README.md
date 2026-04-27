# Object Pool Pattern

## Overview

The **Object Pool Pattern** is a creational design pattern that reuses objects that are expensive to create by maintaining a pool of initialized objects. Instead of creating and destroying objects repeatedly, the pattern keeps a pool of ready-to-use objects and hands them out when needed. When an object is no longer needed, it's returned to the pool for reuse rather than being destroyed. This improves performance and reduces resource consumption.

## Key Characteristics

- **Reusability**: Objects are reused instead of being created and destroyed repeatedly
- **Performance**: Reduces the overhead of expensive object creation
- **Resource Management**: Efficiently manages limited resources like database connections
- **Thread Safety**: Often implemented with synchronization for thread safety
- **Object State**: Objects maintain state and are reset before reuse

## Problem it Solves

Creating expensive objects (like database connections, thread pools, or socket connections) repeatedly is inefficient:

- **Without Pattern**: Create → Use → Destroy → Create → Use → Destroy (repetitive and slow)
- **With Pattern**: Create (once) → Acquire → Use → Release → Acquire → Use (efficient reuse)

## Pattern Components

### 1. **Reusable Object**

The expensive object that needs to be pooled:

```java
class DBConnection {
    private final int id;

    public DBConnection(int id) {
        this.id = id;
        System.out.println("Creating DB Connection: " + id);
    }

    public void executeQuery(String query) {
        System.out.println("Executing on connection " + id + ": " + query);
    }

    public void reset() {
        System.out.println("Resetting connection " + id);
    }
}
```

### 2. **Object Pool**

Manages the pool of reusable objects:

```java
class ConnectionPool {
    private Queue<DBConnection> available;
    private Set<DBConnection> inUse;
    private int maxSize;
    private int currentSize = 0;

    public ConnectionPool(int maxSize) {
        this.maxSize = maxSize;
        available = new LinkedList<>();
        inUse = new HashSet<>();
    }

    public synchronized DBConnection acquire() {
        if (!available.isEmpty()) {
            DBConnection conn = available.poll();
            inUse.add(conn);
            return conn;
        }

        if (currentSize < maxSize) {
            DBConnection conn = new DBConnection(++currentSize);
            inUse.add(conn);
            return conn;
        }

        throw new RuntimeException("No available connections!");
    }

    public synchronized void release(DBConnection conn) {
        if (conn == null)
            return;

        conn.reset();
        inUse.remove(conn);
        available.offer(conn);
    }
}
```

## How It Works

The Object Pool manages two collections:

1. **Available Pool**: Ready-to-use objects waiting for acquisition
2. **In Use Set**: Objects currently being used

### Acquire Process:

1. Check if available pool has objects
2. If yes, return one from the pool
3. If no, create a new object (if limit not reached)
4. Mark object as in-use
5. Return to client

### Release Process:

1. Receive object from client
2. Reset object to initial state
3. Move from in-use to available pool
4. Ready for next acquisition

## Usage Example

```java
ConnectionPool pool = new ConnectionPool(2);

DBConnection c1 = pool.acquire();
c1.executeQuery("SELECT * FROM users");

DBConnection c2 = pool.acquire();
c2.executeQuery("SELECT * FROM orders");

pool.release(c1);

DBConnection c3 = pool.acquire(); // reuses c1
c3.executeQuery("SELECT * FROM products");

pool.release(c2);
pool.release(c3);
```

### Output:

```
Creating DB Connection: 1
Executing on connection 1: SELECT * FROM users
Creating DB Connection: 2
Executing on connection 2: SELECT * FROM orders
Resetting connection 1
Executing on connection 1: SELECT * FROM products
Resetting connection 2
```

Notice that only 2 connections are created (the max size), and c1 is reused for c3 after being released.

## Pool Lifecycle Diagram

```
Initial State (Pool Size: 2)
├── Available: []
└── In Use: []

After acquire() 3 times
├── Available: []
└── In Use: [c1, c2]

After release(c1)
├── Available: [c1]
└── In Use: [c2]

After acquire() again
├── Available: []
└── In Use: [c2, c3=c1]
```

## Object Lifecycle Comparison

### Without Object Pool:

```
Create → Use → Destroy → Create → Use → Destroy
(repeated for each request)
```

### With Object Pool:

```
Create (once) → [Acquire → Use → Release] × N
(creation happens only once, reuse many times)
```

## Advantages

✅ **Performance**: Reduces object creation overhead  
✅ **Resource Efficiency**: Limits resource consumption by capping pool size  
✅ **Predictability**: Known maximum resource usage  
✅ **Reusability**: Objects are recycled, reducing garbage collection pressure  
✅ **Scalability**: Better performance under load

## Disadvantages

❌ **Complexity**: More complex than simple object creation  
❌ **Thread Safety**: Requires synchronization for thread safety  
❌ **Resource Holding**: Objects hold resources even when unused  
❌ **State Management**: Objects must be properly reset before reuse  
❌ **Memory Overhead**: Maintains unused objects in memory

## Key Implementation Considerations

### 1. **Thread Safety**

Use `synchronized` keyword for acquire/release methods:

```java
public synchronized DBConnection acquire() {
    // thread-safe implementation
}

public synchronized void release(DBConnection conn) {
    // thread-safe implementation
}
```

### 2. **Object Reset**

Always reset object state before returning to pool:

```java
public void reset() {
    System.out.println("Resetting connection " + id);
    // Clear any client-specific data
}
```

### 3. **Pool Size Management**

Define appropriate max size:

- Too small: Clients wait for available objects
- Too large: Wastes memory and resources

### 4. **Exception Handling**

Handle cases when no objects available:

```java
if (currentSize < maxSize) {
    // create new object
} else {
    throw new RuntimeException("No available connections!");
}
```

## Real-World Examples

- **Database Connection Pools**: JDBC connection pooling (HikariCP, Apache Commons DBCP)
- **Thread Pools**: ExecutorService in Java for managing thread reuse
- **Socket Pools**: Managing reusable network connections
- **Memory Pools**: Pre-allocated memory for real-time systems
- **String Pool**: Java's internal string interning
- **Object Pools**: Graphics buffers, file handles, HTTP connections
- **Cache Pools**: Pre-allocated cache objects in distributed systems

## Use Cases

- When object creation is expensive (database connections, threads)
- When you need to limit resource consumption
- When you have high frequency of object creation and destruction
- When you need predictable performance
- When managing limited system resources (connections, sockets, file handles)

## Pool Size Calculation

```
Optimal Pool Size = (Core Connections) + (Additional for Peak Load)

Example for Database:
- Baseline: 5 connections
- Peak Load Multiplier: 2x
- Max Pool Size: 5 + (5 × 2) = 15 connections
```

## Object Pool vs Other Patterns

| Pattern         | Purpose                 | Key Difference                        |
| --------------- | ----------------------- | ------------------------------------- |
| **Object Pool** | Reuse expensive objects | Maintains pool of initialized objects |
| **Singleton**   | Single instance         | Creates only one instance             |
| **Factory**     | Create objects          | Doesn't manage reuse                  |
| **Flyweight**   | Share common objects    | Shares intrinsic state                |
| **Prototype**   | Clone objects           | Creates new instances via cloning     |

## Best Practices

✅ **Synchronize Access**: Ensure thread-safe acquire/release  
✅ **Reset Objects**: Always reset state before reuse  
✅ **Monitor Pool**: Track available/in-use counts  
✅ **Set Limits**: Define appropriate max pool size  
✅ **Handle Exceptions**: Gracefully handle pool exhaustion  
✅ **Timeout Mechanism**: Add timeout for acquire operations  
✅ **Validation**: Validate objects before returning to pool

## Example: Advanced Pool with Timeout

```java
public synchronized DBConnection acquire(long timeoutMs) throws TimeoutException {
    long startTime = System.currentTimeMillis();

    while (available.isEmpty() && currentSize >= maxSize) {
        long elapsed = System.currentTimeMillis() - startTime;
        if (elapsed > timeoutMs) {
            throw new TimeoutException("No connections available within timeout");
        }
        try {
            wait(Math.min(timeoutMs - elapsed, 100));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Interrupted while waiting for connection", e);
        }
    }

    return acquireInternal();
}
```

## Related Patterns

- **Singleton Pattern**: Creates single instance, while pool creates multiple
- **Factory Pattern**: Creates objects, while pool reuses them
- **Flyweight Pattern**: Shares common state, pool manages object lifecycle
- **Prototype Pattern**: Clones objects, while pool reuses originals
- **Proxy Pattern**: Controls access, similar to how pool controls object access
