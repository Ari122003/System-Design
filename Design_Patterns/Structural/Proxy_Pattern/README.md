# Proxy Pattern

## Overview

The **Proxy Pattern** is a structural design pattern that provides a surrogate or placeholder for another object to control access to it. It acts as an intermediary between a client and the real object, allowing you to perform additional operations before or after delegating to the real object.

## Key Characteristics

- **Lazy Loading**: Defer expensive operations until they're actually needed
- **Access Control**: Control when and how the real object is accessed
- **Logging & Monitoring**: Track access to the real object
- **Caching**: Store results to avoid repeated expensive operations
- **Security**: Restrict access based on permissions

## Pattern Components

### 1. **Subject Interface**

Defines the common interface for both the Proxy and Real Subject:

```java
interface Image {
    void display();
}
```

### 2. **Real Subject**

The actual object that performs the real work:

```java
class RealImage implements Image {
    private String fileName;

    public RealImage(String fileName) {
        this.fileName = fileName;
        loadFromDisk(); // expensive operation
    }

    private void loadFromDisk() {
        System.out.println("Loading image from disk: " + fileName);
    }

    public void display() {
        System.out.println("Displaying image: " + fileName);
    }
}
```

### 3. **Proxy**

Controls access to the Real Subject and implements lazy initialization:

```java
class ProxyImage implements Image {
    private RealImage realImage;
    private String fileName;

    public ProxyImage(String fileName) {
        this.fileName = fileName;
    }

    public void display() {
        if (realImage == null) {
            realImage = new RealImage(fileName); // lazy initialization
        }
        realImage.display();
    }
}
```

## How It Works

The proxy intercepts the method call and decides whether to create the real object:

1. **First Call**: When `display()` is called for the first time:
   - The proxy checks if `realImage` is null
   - Since it is, it creates a new `RealImage` object (triggers expensive disk loading)
   - The real object's `display()` method is called

2. **Subsequent Calls**: When `display()` is called again:
   - The proxy checks if `realImage` is null
   - Since it's already loaded, it directly calls the cached object's `display()` method
   - No additional disk loading occurs

## Usage Example

```java
public static void main(String[] args) {
    Image image = new ProxyImage("photo.png");

    // First time → loads from disk
    image.display();

    System.out.println();

    // Second time → no loading, uses cached object
    image.display();
}
```

### Output:

```
Loading image from disk: photo.png
Displaying image: photo.png

Displaying image: photo.png
```

Notice how the second call doesn't trigger the "Loading image from disk" message because the proxy uses the cached `RealImage` object.

## Advantages

✅ **Lazy Initialization**: Expensive objects are created only when needed  
✅ **Performance**: Reduces unnecessary object creation and resource usage  
✅ **Control**: Fine-grained control over when the real object is accessed  
✅ **Logging**: Easy to add logging, monitoring, or caching  
✅ **Security**: Can enforce access control before accessing the real object

## Disadvantages

❌ **Complexity**: Adds an extra layer of indirection  
❌ **Performance Overhead**: Proxy checking adds a small performance cost  
❌ **Code Duplication**: The proxy must implement the same interface as the real object

## Real-World Examples

- **Database Proxies**: Lazy-load database connections
- **Remote Proxies**: Control access to remote objects over network
- **Protection Proxies**: Restrict access based on user permissions
- **Image Loading**: Defer loading images until they're actually displayed
- **Virtual Proxies**: Delay creation of expensive objects until needed

## Related Patterns

- **Decorator Pattern**: Similar structure but focuses on adding behavior
- **Adapter Pattern**: Both use indirection but with different purposes
- **Facade Pattern**: Provides simplified access to complex subsystems
