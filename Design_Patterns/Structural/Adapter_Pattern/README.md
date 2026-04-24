# Adapter Pattern

## Overview

The **Adapter Pattern** is a structural design pattern that converts the interface of a class into another interface that clients expect. It lets classes work together that couldn't otherwise because of incompatible interfaces. The adapter acts as a bridge between two incompatible interfaces, allowing them to communicate and work together seamlessly.

## Key Characteristics

- **Interface Conversion**: Converts one interface to another that clients expect
- **Compatibility**: Enables incompatible classes to work together
- **Wrapper**: Wraps an existing class with an incompatible interface
- **Reusability**: Allows reuse of existing classes without modification
- **Integration**: Solves integration problems between different systems

## Pattern Components

### 1. **Target Interface**

The interface that the client expects:

```java
interface XboxController {
    void pressA();
    void pressB();
}
```

### 2. **Adaptee**

The existing class with incompatible interface:

```java
class PlayStationController {
    public void pressX() {
        System.out.println("Pressed X (PlayStation)");
    }

    public void pressCircle() {
        System.out.println("Pressed Circle (PlayStation)");
    }
}
```

### 3. **Adapter**

Converts the Adaptee interface to the Target interface:

```java
class PlayStationToXboxAdapter implements XboxController {

    private PlayStationController psController;

    public PlayStationToXboxAdapter(PlayStationController psController) {
        this.psController = psController;
    }

    @Override
    public void pressA() {
        // Mapping A → X
        psController.pressX();
    }

    @Override
    public void pressB() {
        // Mapping B → Circle
        psController.pressCircle();
    }
}
```

## How It Works

The Adapter Pattern bridges the gap between incompatible interfaces through mapping:

1. **Client Expectation**: The game expects an `XboxController` interface with `pressA()` and `pressB()` methods
2. **Incompatible Class**: The `PlayStationController` has different method names: `pressX()` and `pressCircle()`
3. **Adapter Solution**: The `PlayStationToXboxAdapter` wraps the PlayStation controller and maps Xbox buttons to PlayStation buttons
4. **Transparent Usage**: The client code uses the adapter as if it were the target interface

## Usage Example

```java
// Player has PlayStation controller
PlayStationController ps = new PlayStationController();

// Convert it into Xbox-compatible controller
XboxController controller = new PlayStationToXboxAdapter(ps);

// Game only understands Xbox inputs
controller.pressA();
controller.pressB();
```

### Output:

```
Pressed X (PlayStation)
Pressed Circle (PlayStation)
```

The adapter translates Xbox button calls into PlayStation button calls transparently.

## Interface Mapping

| Xbox Interface | PlayStation Interface | Adapter Mapping |
| -------------- | --------------------- | --------------- |
| `pressA()`     | `pressX()`            | A → X           |
| `pressB()`     | `pressCircle()`       | B → Circle      |

## Advantages

✅ **Reusability**: Use existing classes without modification  
✅ **Flexibility**: Support multiple incompatible interfaces  
✅ **Single Responsibility**: Separates interface conversion logic  
✅ **Open/Closed Principle**: Open for extension without modifying existing code  
✅ **Integration**: Seamlessly integrate incompatible systems

## Disadvantages

❌ **Complexity**: Adds an extra layer of indirection  
❌ **Overhead**: Additional object creation and method calls  
❌ **Maintenance**: Multiple adapters can become difficult to manage  
❌ **Code Duplication**: If many adapters are needed, can lead to similar code

## Types of Adapters

### 1. **Class Adapter** (Inheritance)

Uses inheritance to adapt the interface:

```java
class ClassAdapter extends PlayStationController implements XboxController {
    @Override
    public void pressA() {
        pressX();
    }
}
```

### 2. **Object Adapter** (Composition)

Uses composition to adapt the interface (as shown in our example):

```java
class ObjectAdapter implements XboxController {
    private PlayStationController ps;

    public ObjectAdapter(PlayStationController ps) {
        this.ps = ps;
    }

    @Override
    public void pressA() {
        ps.pressX();
    }
}
```

## Real-World Examples

- **USB Adapters**: Converting USB-C to USB-A
- **Travel Adapters**: Electrical outlet adapters for different countries
- **Media Players**: Playing different media formats through a common interface
- **Database Drivers**: JDBC adapters for different database systems
- **API Wrappers**: Adapting third-party APIs to your application's interface
- **Legacy System Integration**: Adapting old systems to new architectures
- **Payment Gateways**: Adapting different payment providers to a common interface

## Use Cases

- When you want to use a class with an incompatible interface
- When integrating third-party libraries into your codebase
- When working with legacy systems that can't be modified
- When you need multiple implementations with similar but different interfaces
- When you want to decouple client code from specific implementations

## Adapter vs Other Patterns

| Pattern       | Purpose                                  | Key Difference                |
| ------------- | ---------------------------------------- | ----------------------------- |
| **Adapter**   | Make incompatible interfaces compatible  | Adapts existing interface     |
| **Decorator** | Add responsibilities to objects          | Adds behavior to objects      |
| **Facade**    | Simplify complex subsystems              | Provides simplified access    |
| **Bridge**    | Separate abstraction from implementation | Decouples at design time      |
| **Proxy**     | Control access to another object         | Provides surrogate for object |

## Related Patterns

- **Decorator Pattern**: Similar structure but focuses on adding behavior
- **Facade Pattern**: Provides simplified access, but doesn't adapt interfaces
- **Bridge Pattern**: Separates abstraction from implementation
- **Proxy Pattern**: Controls access but maintains same interface
