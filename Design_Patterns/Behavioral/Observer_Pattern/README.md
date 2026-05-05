# Observer Pattern

## Overview

The **Observer Pattern** is a behavioral design pattern that defines a one-to-many dependency between objects so that when one object changes state, all its dependents are notified automatically. It promotes loose coupling by establishing a publish-subscribe mechanism where subjects notify observers about state changes without tight coupling. The pattern is fundamental to event-driven programming.

## Key Characteristics

- **Loose Coupling**: Subject and observers are loosely coupled
- **Dynamic Subscription**: Observers can subscribe/unsubscribe at runtime
- **Automatic Notification**: Observers are automatically notified of changes
- **One-to-Many**: One subject can have many observers
- **Event-Driven**: Enables reactive and event-driven architectures

## Problem it Solves

Without the Observer Pattern, direct coupling would be required:

- **Tight Coupling**: Subject needs to know all observers and call their methods
- **Hard to Maintain**: Adding new observers requires modifying subject code
- **Scalability Issues**: Subject becomes dependent on observer implementations

With the Observer Pattern, the subject only knows the observer interface, enabling loose coupling.

## Pattern Components

### 1. **Observer Interface**

Defines the contract for observers:

```java
interface Observer {
    void update(String message);
}
```

### 2. **Subject Interface**

Defines operations for managing observers:

```java
interface Subject {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}
```

### 3. **Concrete Subject**

Maintains observer list and notifies them of state changes:

```java
class YouTubeChannel implements Subject {
    private List<Observer> subscribers = new ArrayList<>();
    private String videoTitle;

    public void uploadVideo(String title) {
        this.videoTitle = title;
        notifyObservers();
    }

    public void addObserver(Observer observer) {
        subscribers.add(observer);
    }

    public void removeObserver(Observer observer) {
        subscribers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer sub : subscribers) {
            sub.update(videoTitle);
        }
    }
}
```

### 4. **Concrete Observer**

Implements the observer interface to receive updates:

```java
class Subscriber implements Observer {
    private String name;

    public Subscriber(String name) {
        this.name = name;
    }

    public void update(String videoTitle) {
        System.out.println(name + " received notification: New video - " + videoTitle);
    }
}
```

## How It Works

The Observer Pattern establishes a publish-subscribe mechanism:

1. **Subscription**: Observers register themselves with the subject
2. **State Change**: Subject's state changes
3. **Notification**: Subject notifies all registered observers
4. **Update**: Each observer receives the update and reacts

## Usage Example

```java
YouTubeChannel channel = new YouTubeChannel();

Observer user1 = new Subscriber("Aritra");
Observer user2 = new Subscriber("Rahul");

channel.addObserver(user1);
channel.addObserver(user2);

channel.uploadVideo("Observer Pattern Explained");

channel.removeObserver(user1);

channel.uploadVideo("Java Design Patterns");
```

### Output:

```
Aritra received notification: New video - Observer Pattern Explained
Rahul received notification: New video - Observer Pattern Explained
Rahul received notification: New video - Java Design Patterns
```

Notice how Aritra is no longer notified after being removed.

## Observer Pattern Workflow

```
YouTubeChannel (Subject)
    ↓
    ├─→ addObserver(Aritra)
    ├─→ addObserver(Rahul)
    │
    ├─→ uploadVideo("Observer Pattern")
    │   └─→ notifyObservers()
    │       ├─→ Aritra.update()
    │       └─→ Rahul.update()
    │
    ├─→ removeObserver(Aritra)
    │
    └─→ uploadVideo("Java Patterns")
        └─→ notifyObservers()
            └─→ Rahul.update()
```

## Observer Subscription Lifecycle

```
Time 1: Subscribe
├── Observers: [Aritra, Rahul]
│   └─→ Both receive notifications

Time 2: Unsubscribe Aritra
├── Observers: [Rahul]
│   └─→ Only Rahul receives notifications

Time 3: Subscribe new Observer
├── Observers: [Rahul, Priya]
│   └─→ Both receive notifications
```

## Advantages

✅ **Loose Coupling**: Subject doesn't depend on concrete observer classes  
✅ **Dynamic Relationships**: Observers can be added/removed at runtime  
✅ **Automatic Updates**: All observers are notified automatically  
✅ **Separation of Concerns**: Subject and observers have different responsibilities  
✅ **Reusability**: Observers can be reused with different subjects  
✅ **Event-Driven**: Natural fit for event-driven systems

## Disadvantages

❌ **Unpredictable Order**: Observers notified in unpredictable order  
❌ **Memory Leaks**: Forgotten observer registrations cause memory leaks  
❌ **Debugging Difficulty**: Complex chains of notifications hard to trace  
❌ **Performance**: Many observers can impact performance  
❌ **Hidden Dependencies**: Observer dependencies are implicit

## Real-World Examples

- **Event Listeners**: GUI button clicks triggering multiple handlers
- **MVC Architecture**: Model notifying views of data changes
- **Pub-Sub Systems**: Message brokers, Kafka, RabbitMQ
- **Stock Market**: Stock price changes notifying multiple investors
- **Social Media**: User actions notifying followers
- **Notification Systems**: Email, SMS, push notifications
- **Reactive Programming**: RxJava, Project Reactor
- **Weather Systems**: Weather station notifying displays
- **File Systems**: File watcher monitoring directory changes
- **Java Event Model**: Swing/AWT event listeners

## Use Cases

- When multiple objects need to be notified of state changes
- When an object doesn't know how many objects need to be notified
- When you need loose coupling between components
- When you want to implement event-driven systems
- When you need publish-subscribe functionality

## Observer vs Other Patterns

| Pattern       | Purpose                                  | Key Difference                |
| ------------- | ---------------------------------------- | ----------------------------- |
| **Observer**  | Notify multiple objects of state changes | One-to-many publish-subscribe |
| **Mediator**  | Centralize communication                 | Central mediator object       |
| **Pub-Sub**   | Decouple publishers from subscribers     | Topic-based messaging         |
| **Command**   | Encapsulate requests as objects          | Requests as objects           |
| **Event Bus** | Decoupled event communication            | Global event distribution     |

## Observer Pattern Variants

### 1. **Push Model** (What we implemented)

Subject sends data to observers:

```java
public void notifyObservers() {
    for (Observer sub : subscribers) {
        sub.update(videoTitle);  // Pass data
    }
}
```

### 2. **Pull Model**

Subject notifies, observers fetch data:

```java
public void notifyObservers() {
    for (Observer sub : subscribers) {
        sub.update();  // No parameters
    }
}

public void update() {
    String title = channel.getVideoTitle();  // Fetch data
}
```

## Best Practices

✅ **Use Interface**: Define clear observer interface  
✅ **Thread Safety**: Use synchronization for thread-safe notifications  
✅ **Cleanup**: Ensure observers unsubscribe when done  
✅ **Weak References**: Consider weak references to prevent memory leaks  
✅ **Error Handling**: Handle observer exceptions gracefully  
✅ **Documentation**: Clearly document subscription requirements  
✅ **Immutable Events**: Pass immutable data structures to observers

## Advanced Example: Thread-Safe Observer

```java
class ThreadSafeYouTubeChannel implements Subject {
    private List<Observer> subscribers = new CopyOnWriteArrayList<>();
    private String videoTitle;

    public synchronized void uploadVideo(String title) {
        this.videoTitle = title;
        notifyObservers();
    }

    public synchronized void addObserver(Observer observer) {
        subscribers.add(observer);
    }

    public synchronized void removeObserver(Observer observer) {
        subscribers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer sub : subscribers) {
            try {
                sub.update(videoTitle);
            } catch (Exception e) {
                System.err.println("Error notifying observer: " + e.getMessage());
            }
        }
    }
}
```

## Memory Leak Prevention

```java
// Problem: Observer not unsubscribed
Observer obs = new Subscriber("User");
channel.addObserver(obs);
// ... later, obs goes out of scope
// But channel still holds reference!

// Solution: Always unsubscribe
channel.removeObserver(obs);  // Prevents memory leak
```

## Weak References Approach

```java
class WeakSubject {
    private List<WeakReference<Observer>> subscribers = new ArrayList<>();

    public void addObserver(Observer observer) {
        subscribers.add(new WeakReference<>(observer));
    }

    public void notifyObservers() {
        subscribers.removeIf(ref -> ref.get() == null);  // Clean up GC'd objects
        for (WeakReference<Observer> ref : subscribers) {
            Observer obs = ref.get();
            if (obs != null) {
                obs.update("message");
            }
        }
    }
}
```

## Java Built-in Observer

Java provides `java.util.Observable` and `java.util.Observer`, though they're deprecated:

```java
class YouTubeChannel extends Observable {
    public void uploadVideo(String title) {
        setChanged();
        notifyObservers(title);
    }
}

class Subscriber implements Observer {
    public void update(Observable o, Object arg) {
        System.out.println("New video: " + arg);
    }
}
```

## When NOT to Use Observer Pattern

- When only one object needs to be notified
- When the number of observers is dynamic and very large
- When the frequency of notifications is very high
- When observer order is critical and must be predictable
- When tight coupling is acceptable and simpler

## Comparison: Observer vs Event Bus

| Aspect          | Observer          | Event Bus           |
| --------------- | ----------------- | ------------------- |
| **Coupling**    | Direct to subject | Indirect via bus    |
| **Complexity**  | Simple            | More complex        |
| **Scalability** | Limited           | Highly scalable     |
| **Debugging**   | Easier            | Harder              |
| **Decoupling**  | Subject-Observer  | Complete decoupling |

## Related Patterns

- **Mediator Pattern**: Centralizes communication differently
- **Pub-Sub Pattern**: Topic-based variant of Observer
- **Event Bus Pattern**: Decoupled variant using central bus
- **Command Pattern**: Encapsulates requests similarly
- **State Pattern**: Often used with Observer for state changes
- **MVC Pattern**: Uses Observer for model-view updates
