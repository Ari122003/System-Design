# Composite Pattern

## Overview

The **Composite Pattern** is a structural design pattern that lets you compose objects into tree structures to represent part-whole hierarchies. It allows clients to treat individual objects and compositions of objects uniformly. The pattern is useful when you need to work with hierarchical structures where individual elements and groups should be treated in the same way.

## Key Characteristics

- **Tree Structure**: Creates a hierarchy of objects that can be represented as a tree
- **Uniform Treatment**: Both individual objects and containers are treated uniformly
- **Recursive Composition**: Containers can contain other containers
- **Flexibility**: Easy to add new component types
- **Simplicity**: Client code doesn't need to distinguish between leaf and composite objects

## Pattern Components

### 1. **Component Interface**
Defines the common interface for both leaves and composites:

```java
interface FileSystemComponent {
    void showDetails();
}
```

### 2. **Leaf**
Represents end objects that have no children:

```java
class File implements FileSystemComponent {
    private String name;

    public File(String name) {
        this.name = name;
    }

    @Override
    public void showDetails() {
        System.out.println("File: " + name);
    }
}
```

### 3. **Composite**
Contains child components and implements the same interface:

```java
class Folder implements FileSystemComponent {
    private String name;
    private List<FileSystemComponent> components = new ArrayList<>();

    public Folder(String name) {
        this.name = name;
    }

    public void add(FileSystemComponent component) {
        components.add(component);
    }

    public void remove(FileSystemComponent component) {
        components.remove(component);
    }

    @Override
    public void showDetails() {
        System.out.println("Folder: " + name);

        for (FileSystemComponent component : components) {
            component.showDetails();
        }
    }
}
```

## How It Works

The Composite Pattern allows you to build a hierarchical structure where both individual items and containers are treated uniformly:

1. **Leaf Objects**: `File` objects represent individual files with no children
2. **Composite Objects**: `Folder` objects contain other components (files or folders)
3. **Recursive Structure**: Folders can contain other folders, creating a tree structure
4. **Uniform Interface**: Both `File` and `Folder` implement `FileSystemComponent`

## Usage Example

```java
// Leaf objects
File resume = new File("resume.pdf");
File degree = new File("degree.pdf");

File photo = new File("photo.jpg");
File pan = new File("pan.pdf");

// Composite objects
Folder education = new Folder("Education");
education.add(resume);
education.add(degree);

Folder images = new Folder("Images");
images.add(photo);
images.add(pan);

Folder mainFolder = new Folder("Main Folder");
mainFolder.add(education);
mainFolder.add(images);

// Treating both same - recursive traversal
mainFolder.showDetails();
```

### Output:
```
Folder: Main Folder
Folder: Education
File: resume.pdf
File: degree.pdf
Folder: Images
File: photo.jpg
File: pan.pdf
```

The `showDetails()` method recursively traverses the entire tree structure, handling both files and folders uniformly.

## Tree Structure Visualization

```
Main Folder
├── Education
│   ├── resume.pdf
│   └── degree.pdf
└── Images
    ├── photo.jpg
    └── pan.pdf
```

## Advantages

✅ **Uniformity**: Client treats individual and composite objects the same way  
✅ **Flexibility**: Easy to add new component types  
✅ **Tree Structures**: Perfect for representing hierarchical data  
✅ **Recursive Composition**: Containers can contain other containers  
✅ **Simplified Client Code**: No need for type checking or special handling  

## Disadvantages

❌ **Design Complexity**: Can make the design more complex than simple approaches  
❌ **Type Safety**: Harder to restrict what components can be added  
❌ **Performance**: Recursive traversal can be slower for large trees  
❌ **Memory Usage**: Can consume more memory with deep hierarchies  

## Real-World Examples

- **File Systems**: Directories containing files and subdirectories
- **GUI Components**: Panels containing buttons, text fields, and other panels
- **Organization Charts**: Departments containing teams and employees
- **DOM Trees**: HTML elements containing other elements
- **Graphics Libraries**: Shapes containing other shapes and groups
- **Menu Systems**: Menus containing submenus and menu items

## Use Cases

- When you need to represent hierarchical structures
- When clients should ignore the difference between object compositions and individual objects
- When you want to add new types of objects without changing existing code
- When building tree-like data structures (file systems, org charts, etc.)

## Related Patterns

- **Decorator Pattern**: Similar structure but focuses on adding functionality to individual objects
- **Iterator Pattern**: Often used to traverse composite structures
- **Visitor Pattern**: Can be used to perform operations on composite structures
- **Strategy Pattern**: Can be combined to change behavior of composites
