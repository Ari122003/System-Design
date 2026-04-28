# Flyweight Pattern

## Overview

The **Flyweight Pattern** is a structural design pattern that uses sharing to support large numbers of fine-grained objects efficiently. It reduces memory consumption by sharing common state (intrinsic state) among multiple objects rather than storing it separately in each object. The pattern is ideal when dealing with a large number of similar objects that consume significant memory.

## Key Characteristics

- **Memory Optimization**: Reduces memory usage by sharing common state
- **Shared State**: Intrinsic state is shared among multiple objects
- **Unique State**: Extrinsic state is unique to each object instance
- **Factory Pattern**: Uses factory to manage and reuse shared objects
- **Performance**: Reduces garbage collection pressure and memory footprint

## Problem it Solves

Creating 1000 tree objects with duplicate properties wastes memory:

- **Without Pattern**: Each tree object stores name, color, and texture (1000 duplicates)
- **With Pattern**: Only unique combinations are stored once, shared across all instances

## Pattern Components

### 1. **Flyweight Interface**

Defines the interface for shared objects:

```java
interface Tree {
    void display(int x, int y); // extrinsic state
}
```

### 2. **Concrete Flyweight**

Implements the shared (intrinsic) state:

```java
class TreeType implements Tree {
    private String name;
    private String color;
    private String texture;

    public TreeType(String name, String color, String texture) {
        this.name = name;
        this.color = color;
        this.texture = texture;
    }

    @Override
    public void display(int x, int y) {
        System.out.println("Drawing " + name + " tree at (" + x + "," + y + ") with color " + color);
    }
}
```

### 3. **Flyweight Factory**

Creates and manages shared objects, ensuring reuse:

```java
class TreeFactory {
    private static Map<String, TreeType> treeMap = new HashMap<>();

    public static TreeType getTreeType(String name, String color, String texture) {
        String key = name + color + texture;

        if (!treeMap.containsKey(key)) {
            treeMap.put(key, new TreeType(name, color, texture));
        }

        return treeMap.get(key);
    }
}
```

### 4. **Context (Client)**

Stores extrinsic state and uses the flyweight:

```java
class TreeContext {
    private int x, y; // extrinsic
    private TreeType type; // shared

    public TreeContext(int x, int y, TreeType type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public void display() {
        type.display(x, y);
    }
}
```

## How It Works

The Flyweight Pattern separates state into two types:

1. **Intrinsic State** (Shared):
   - Properties that don't change: `name`, `color`, `texture`
   - Stored once in the flyweight
   - Shared across multiple objects

2. **Extrinsic State** (Unique):
   - Properties that change: `x`, `y` coordinates
   - Stored in the context
   - Passed when needed

## Usage Example

```java
List<TreeContext> trees = new ArrayList<>();

for (int i = 0; i < 1000; i++) {
    TreeType type = TreeFactory.getTreeType("Oak", "Green", "Rough");
    trees.add(new TreeContext(i, i + 1, type));
}

for (TreeContext tree : trees) {
    tree.display();
}
```

### Output (First 5 lines):

```
Drawing Oak tree at (0,1) with color Green
Drawing Oak tree at (1,2) with color Green
Drawing Oak tree at (2,3) with color Green
Drawing Oak tree at (3,4) with color Green
Drawing Oak tree at (4,5) with color Green
...
```

Despite creating 1000 TreeContext objects, only ONE TreeType object is created and reused.

## Memory Optimization Comparison

### Without Flyweight Pattern:

```
1000 Trees
├── Tree 1: Oak, Green, Rough, (0,1)   [100 bytes]
├── Tree 2: Oak, Green, Rough, (1,2)   [100 bytes]
├── Tree 3: Oak, Green, Rough, (2,3)   [100 bytes]
└── ...
Total Memory: 1000 × 100 = 100,000 bytes
```

### With Flyweight Pattern:

```
Shared TreeType: Oak, Green, Rough        [70 bytes]
1000 TreeContexts
├── Context 1: reference, (0,1)          [30 bytes]
├── Context 2: reference, (1,2)          [30 bytes]
├── Context 3: reference, (2,3)          [30 bytes]
└── ...
Total Memory: 70 + (1000 × 30) = 30,070 bytes
Memory Saved: ~70% reduction
```

## Flyweight Architecture

```
Client Code
    ↓
TreeFactory (manages flyweights)
    ↓
    ├─→ TreeType Cache
    │    ├─→ Oak-Green-Rough
    │    ├─→ Pine-Brown-Smooth
    │    └─→ Birch-White-Smooth
    ↓
TreeContext (uses flyweight)
    ├─→ x, y, reference to Oak-Green-Rough
    ├─→ x, y, reference to Oak-Green-Rough
    └─→ x, y, reference to Pine-Brown-Smooth
```

## Advantages

✅ **Memory Efficiency**: Significantly reduces memory consumption  
✅ **Performance**: Fewer objects means less garbage collection  
✅ **Scalability**: Can handle large numbers of similar objects  
✅ **Sharing**: Common state is shared efficiently  
✅ **Reusability**: Flyweights are automatically reused

## Disadvantages

❌ **Complexity**: Requires separation of intrinsic and extrinsic state  
❌ **Thread Safety**: Shared objects may require synchronization  
❌ **Hidden Costs**: Memory savings may be offset by factory overhead  
❌ **Development Complexity**: More difficult to design and maintain  
❌ **Not Always Applicable**: Benefits depend on number of objects and state duplication

## State Classification Guide

### Intrinsic State (Shared):

- Doesn't change: immutable
- Expensive to store multiple copies
- Can be shared safely
- Examples: `name`, `color`, `texture`

### Extrinsic State (Unique):

- Changes frequently: mutable
- Cheap to store individually
- Cannot be shared
- Examples: `x`, `y`, position, rendering context

## Real-World Examples

- **Java String Pool**: Identical strings are shared in memory
- **Text Editors**: Character objects sharing font, style, color
- **Game Development**: Particle systems sharing texture and shader data
- **Web Browsers**: DOM nodes sharing CSS styles
- **Graphics Libraries**: Cached shapes, gradients, patterns
- **Connection Pools**: Sharing database connections
- **Thread Pools**: Sharing thread objects
- **Font Systems**: Glyph caching across documents

## Use Cases

- When you have many similar objects consuming significant memory
- When object creation is expensive and objects can be shared
- When object state is mostly intrinsic
- When number of objects is very large
- When you need to optimize memory usage

## Flyweight vs Other Patterns

| Pattern         | Purpose                           | Key Difference                     |
| --------------- | --------------------------------- | ---------------------------------- |
| **Flyweight**   | Share common state to save memory | Focuses on memory optimization     |
| **Object Pool** | Reuse expensive objects           | Focuses on object reuse/recreation |
| **Singleton**   | Single instance                   | Creates only one instance globally |
| **Prototype**   | Clone objects                     | Creates copies of objects          |
| **Factory**     | Create objects                    | Creates new instances              |

## Implementation Considerations

### 1. **Thread Safety**

Ensure factory is thread-safe when accessed from multiple threads:

```java
class TreeFactory {
    private static synchronized TreeType getTreeType(String key) {
        if (!treeMap.containsKey(key)) {
            treeMap.put(key, new TreeType(...));
        }
        return treeMap.get(key);
    }
}
```

### 2. **Immutability**

Keep flyweight objects immutable to ensure safe sharing:

```java
class TreeType implements Tree {
    private final String name;
    private final String color;
    private final String texture;

    // No setters!
}
```

### 3. **Factory Key Design**

Design meaningful keys for caching:

```java
String key = name + color + texture; // Simple combination
// Or use hashCode(), UUID, or custom comparison
```

## Memory Calculation Example

```
Scenario: 10,000 trees with 5 unique types

Without Flyweight:
- Each TreeType object: ~50 bytes (name, color, texture)
- Each TreeContext object: ~16 bytes (x, y, reference)
- Total: 10,000 × (50 + 16) = 660,000 bytes

With Flyweight:
- 5 TreeType objects: 5 × 50 = 250 bytes
- 10,000 TreeContext objects: 10,000 × 16 = 160,000 bytes
- Total: 160,250 bytes

Memory Saved: (660,000 - 160,250) / 660,000 = 75.7% reduction
```

## When NOT to Use Flyweight Pattern

- When most objects have unique state
- When memory isn't a concern
- When creating objects is cheap
- When number of objects is small
- When sharing state requires complex synchronization

## Advanced Example: Custom Equals and HashCode

```java
class TreeType implements Tree {
    private String name;
    private String color;
    private String texture;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TreeType treeType = (TreeType) o;
        return Objects.equals(name, treeType.name) &&
               Objects.equals(color, treeType.color) &&
               Objects.equals(texture, treeType.texture);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, color, texture);
    }
}

// Better Factory using Map with custom key
class TreeFactory {
    private static Map<TreeType, TreeType> treeMap = new HashMap<>();

    public static TreeType getTreeType(String name, String color, String texture) {
        TreeType key = new TreeType(name, color, texture);
        return treeMap.computeIfAbsent(key, k -> key);
    }
}
```

## Best Practices

✅ **Separate State Clearly**: Define intrinsic vs extrinsic state upfront  
✅ **Immutable Flyweights**: Make flyweight objects immutable  
✅ **Thread-Safe Factory**: Ensure factory is thread-safe  
✅ **Good Cache Keys**: Design meaningful keys for caching  
✅ **Monitor Cache**: Track cache hit rates and performance  
✅ **Document Sharing**: Clearly document what's being shared

## Related Patterns

- **Factory Pattern**: Used to create and manage flyweights
- **Object Pool Pattern**: Similar concept but focuses on object reuse
- **Composite Pattern**: Often combined with Flyweight for tree structures
- **Singleton Pattern**: Factory may use singleton pattern
- **Strategy Pattern**: Can be combined for different sharing strategies
