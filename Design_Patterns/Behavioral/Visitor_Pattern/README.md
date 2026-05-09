# Visitor Pattern

## Overview

The **Visitor Pattern** is a behavioral design pattern that represents an operation to be performed on the elements of an object structure. It allows you to define a new operation without changing the classes of the elements on which it operates. The visitor pattern lets you add new functionality to existing code without modifying the structure of the classes, following the Open/Closed Principle.

## Key Characteristics

- **Double Dispatch**: Uses method overloading based on runtime types
- **Separation of Concerns**: Operations are separated from object structure
- **Easy to Add Operations**: New operations are added via new visitor classes
- **No Modification to Elements**: Element classes don't need to change
- **Complex Algorithms**: Operations can be complex and stateful
- **Single Responsibility**: Each visitor handles one specific operation

## Problem it Solves

Without the Visitor Pattern, adding new operations requires modifying existing classes:

```java
// Without Visitor - Adding operation requires modifying classes
class Book {
    public void calculatePrice() { }
    public void applyDiscount() { }
    public void generateReport() { }
    public void calculateTax() { }
    // Adding more operations = modifying Book
}

class Laptop {
    public void calculatePrice() { }
    public void applyDiscount() { }
    public void generateReport() { }
    public void calculateTax() { }
    // Same operations needed for each element type
}
```

With the Visitor Pattern, operations are external and can be added without modifying elements.

## Pattern Components

### 1. **Element Interface**

Defines contract for elements that can be visited:

```java
interface Product {
    void accept(Visitor visitor);
}
```

### 2. **Concrete Elements**

Implement the element interface:

```java
class Book implements Product {
    private String name;
    private int price;

    public Book(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

class Laptop implements Product {
    private String brand;
    private int price;

    public Laptop(String brand, int price) {
        this.brand = brand;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public String getBrand() {
        return brand;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
```

### 3. **Visitor Interface**

Declares visit methods for each element type:

```java
interface Visitor {
    void visit(Book book);
    void visit(Laptop laptop);
}
```

### 4. **Concrete Visitors**

Implement specific operations:

```java
class PriceVisitor implements Visitor {
    @Override
    public void visit(Book book) {
        System.out.println("Book: " + book.getName() + " Price: " + book.getPrice());
    }

    @Override
    public void visit(Laptop laptop) {
        System.out.println("Laptop: " + laptop.getBrand() + " Price: " + laptop.getPrice());
    }
}

class DiscountVisitor implements Visitor {
    @Override
    public void visit(Book book) {
        int discountedPrice = (int) (book.getPrice() * 0.9); // 10% discount
        System.out.println("Book: " + book.getName() + " Discounted Price: " + discountedPrice);
    }

    @Override
    public void visit(Laptop laptop) {
        int discountedPrice = (int) (laptop.getPrice() * 0.85); // 15% discount
        System.out.println("Laptop: " + laptop.getBrand() + " Discounted Price: " + discountedPrice);
    }
}
```

## How It Works

The Visitor Pattern uses double dispatch:

1. **Accept Operation**: Element's `accept()` method is called with a visitor
2. **Forward to Visitor**: Element passes itself to the visitor's `visit()` method
3. **Process Element**: Visitor's overloaded `visit()` method processes the specific element
4. **Add Operations**: New visitors can be added without modifying element classes

## Usage Example

```java
Product book = new Book("Java Programming", 50);
Product laptop = new Laptop("Dell XPS", 1000);

Visitor priceVisitor = new PriceVisitor();
Visitor discountVisitor = new DiscountVisitor();

System.out.println("Price Information:");
book.accept(priceVisitor);
laptop.accept(priceVisitor);

System.out.println("\nDiscount Information:");
book.accept(discountVisitor);
laptop.accept(discountVisitor);
```

### Output:

```
Price Information:
Book: Java Programming Price: 50
Laptop: Dell XPS Price: 1000

Discount Information:
Book: Java Programming Discounted Price: 45
Laptop: Dell XPS Discounted Price: 850
```

Notice how different operations can be applied to the same objects without modifying their classes.

## Visitor Pattern Workflow

```
Object Structure:
├─ Book
├─ Laptop
└─ Monitor

Operation 1 (PriceVisitor):
├─ Visit Book → Calculate price
├─ Visit Laptop → Calculate price
└─ Visit Monitor → Calculate price

Operation 2 (DiscountVisitor):
├─ Visit Book → Apply 10% discount
├─ Visit Laptop → Apply 15% discount
└─ Visit Monitor → Apply 20% discount

Operation 3 (ReportVisitor):
├─ Visit Book → Generate book report
├─ Visit Laptop → Generate laptop report
└─ Visit Monitor → Generate monitor report
```

## Double Dispatch Mechanism

```
Client calls: element.accept(visitor)
    ↓
Element's accept() method (1st dispatch)
    └─→ visitor.visit(this)
        ↓
Visitor's visit() method (2nd dispatch)
    └─→ Knows the actual element type at runtime
        └─→ Performs type-specific operation

Example:
book.accept(priceVisitor)
    ↓
Book.accept(PriceVisitor) - Element type known
    └─→ priceVisitor.visit(this)
        ↓
PriceVisitor.visit(Book) - Visitor type known
    └─→ Executes Book-specific price logic
```

## Design Pattern Comparison

### Without Visitor (Tight Coupling):

```
Book
├─ calculatePrice()
├─ applyDiscount()
├─ generateReport()
└─ calculateTax()

Laptop
├─ calculatePrice()
├─ applyDiscount()
├─ generateReport()
└─ calculateTax()

Problem: Operations scattered across multiple classes
```

### With Visitor (Separation of Concerns):

```
Elements:
├─ Book { accept() }
├─ Laptop { accept() }

Visitors:
├─ PriceVisitor { visit(Book), visit(Laptop) }
├─ DiscountVisitor { visit(Book), visit(Laptop) }
├─ ReportVisitor { visit(Book), visit(Laptop) }

Benefit: All operations for one type are in one visitor
```

## Advantages

✅ **Easy to Add Operations**: New visitors add new functionality without modifying elements  
✅ **Separation of Concerns**: Operations are separated from data structures  
✅ **Single Responsibility**: Each visitor handles one specific operation  
✅ **Related Operations**: Related operations are grouped in one visitor class  
✅ **Flexible**: Can apply multiple operations to same object structure  
✅ **Cleaner Code**: Elements remain focused on their core responsibility  
✅ **Stateful Visitors**: Visitors can maintain state during traversal

## Disadvantages

❌ **Difficult to Add Elements**: Adding new element types requires updating all visitors  
❌ **Visitor Complexity**: Visitor interface can become complex with many element types  
❌ **Element Encapsulation**: Elements may need to expose data to visitors  
❌ **Double Dispatch Overhead**: Slightly more complex than direct methods  
❌ **Coupling to Visitor**: Elements are tightly coupled to visitor interface

## Real-World Examples

- **Compiler Design**: Different operations on Abstract Syntax Tree (AST) nodes
- **File System Operations**: Calculate size, search, or move operations on files/folders
- **E-commerce Systems**: Different calculations for products (price, tax, discount, shipping)
- **Report Generation**: Generate different report formats from data structures
- **Game Development**: Apply different actions to game objects
- **XML/JSON Processing**: Apply transformations to document nodes
- **Tax Calculation**: Different tax rules for different product types
- **UI Rendering**: Rendering elements in different formats (HTML, PDF, etc.)
- **Data Validation**: Validate different data types with specific rules
- **Document Processing**: Different operations on document elements

## Use Cases

- When many distinct operations are needed on object structure
- When operations change frequently
- When adding new operations is more common than adding new elements
- When object structure is relatively stable
- When elements are too tightly coupled currently
- When you need to avoid polluting element classes with operations

## Visitor vs Other Patterns

| Pattern             | Purpose                                   | Key Difference                  |
| ------------------- | ----------------------------------------- | ------------------------------- |
| **Visitor**         | Add operations without modifying elements | Operations external to elements |
| **Strategy**        | Change algorithm at runtime               | Algorithm within one class      |
| **Command**         | Encapsulate requests as objects           | Request encapsulation           |
| **Decorator**       | Add behavior dynamically                  | Wraps object and adds behavior  |
| **Observer**        | Notify multiple objects of changes        | Push-based notifications        |
| **Template Method** | Define algorithm skeleton                 | Algorithm in base class         |

## Visitor Variations

### 1. **Generic Visitor**

```java
interface Visitor<T> {
    T visit(Book book);
    T visit(Laptop laptop);
}

class PriceCalculatorVisitor implements Visitor<Integer> {
    @Override
    public Integer visit(Book book) {
        return book.getPrice();
    }

    @Override
    public Integer visit(Laptop laptop) {
        return laptop.getPrice();
    }
}
```

### 2. **Stateful Visitor**

```java
class TotalPriceVisitor implements Visitor {
    private int totalPrice = 0;

    public void visit(Book book) {
        totalPrice += book.getPrice();
    }

    public void visit(Laptop laptop) {
        totalPrice += laptop.getPrice();
    }

    public int getTotalPrice() {
        return totalPrice;
    }
}
```

### 3. **Composite Visitor**

```java
class CompositeVisitor implements Visitor {
    private List<Visitor> visitors;

    public void visit(Book book) {
        for (Visitor visitor : visitors) {
            visitor.visit(book);
        }
    }

    public void visit(Laptop laptop) {
        for (Visitor visitor : visitors) {
            visitor.visit(laptop);
        }
    }
}
```

## Advanced Example: Expression Evaluator

```java
interface Expression {
    void accept(ExpressionVisitor visitor);
}

class NumberExpression implements Expression {
    private int value;

    public NumberExpression(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.visit(this);
    }
}

class BinaryExpression implements Expression {
    private Expression left;
    private String operator;
    private Expression right;

    public BinaryExpression(Expression left, String operator, Expression right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    public Expression getLeft() { return left; }
    public String getOperator() { return operator; }
    public Expression getRight() { return right; }

    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.visit(this);
    }
}

interface ExpressionVisitor {
    void visit(NumberExpression expr);
    void visit(BinaryExpression expr);
}

class EvaluatorVisitor implements ExpressionVisitor {
    private int result = 0;

    public void visit(NumberExpression expr) {
        result = expr.getValue();
    }

    public void visit(BinaryExpression expr) {
        int leftVal = evaluate(expr.getLeft());
        int rightVal = evaluate(expr.getRight());

        switch (expr.getOperator()) {
            case "+":
                result = leftVal + rightVal;
                break;
            case "-":
                result = leftVal - rightVal;
                break;
            case "*":
                result = leftVal * rightVal;
                break;
        }
    }

    private int evaluate(Expression expr) {
        expr.accept(this);
        return result;
    }

    public int getResult() {
        return result;
    }
}

class PrinterVisitor implements ExpressionVisitor {
    public void visit(NumberExpression expr) {
        System.out.print(expr.getValue());
    }

    public void visit(BinaryExpression expr) {
        System.out.print("(");
        expr.getLeft().accept(this);
        System.out.print(" " + expr.getOperator() + " ");
        expr.getRight().accept(this);
        System.out.print(")");
    }
}

// Usage
Expression expr = new BinaryExpression(
    new BinaryExpression(new NumberExpression(5), "+", new NumberExpression(3)),
    "*",
    new NumberExpression(2)
);

EvaluatorVisitor evaluator = new EvaluatorVisitor();
expr.accept(evaluator);
System.out.println("Result: " + evaluator.getResult()); // 16

PrinterVisitor printer = new PrinterVisitor();
expr.accept(printer); // ((5 + 3) * 2)
```

## Advanced Example: File System Operations

```java
interface FileSystemElement {
    void accept(FileSystemVisitor visitor);
}

class File implements FileSystemElement {
    private String name;
    private int size;

    public File(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public String getName() { return name; }
    public int getSize() { return size; }

    @Override
    public void accept(FileSystemVisitor visitor) {
        visitor.visit(this);
    }
}

class Directory implements FileSystemElement {
    private String name;
    private List<FileSystemElement> elements = new ArrayList<>();

    public Directory(String name) {
        this.name = name;
    }

    public void add(FileSystemElement element) {
        elements.add(element);
    }

    public String getName() { return name; }
    public List<FileSystemElement> getElements() { return elements; }

    @Override
    public void accept(FileSystemVisitor visitor) {
        visitor.visit(this);
    }
}

interface FileSystemVisitor {
    void visit(File file);
    void visit(Directory directory);
}

class SizeCalculatorVisitor implements FileSystemVisitor {
    private int totalSize = 0;

    public void visit(File file) {
        totalSize += file.getSize();
    }

    public void visit(Directory directory) {
        for (FileSystemElement element : directory.getElements()) {
            element.accept(this);
        }
    }

    public int getTotalSize() {
        return totalSize;
    }
}

class PrinterVisitor implements FileSystemVisitor {
    private int indent = 0;

    public void visit(File file) {
        System.out.println("  ".repeat(indent) + "- " + file.getName() + " (" + file.getSize() + " bytes)");
    }

    public void visit(Directory directory) {
        System.out.println("  ".repeat(indent) + "+ " + directory.getName());
        indent++;
        for (FileSystemElement element : directory.getElements()) {
            element.accept(this);
        }
        indent--;
    }
}
```

## Best Practices

✅ **Stable Element Hierarchy**: Use Visitor when element types are stable  
✅ **Clear Visitor Interface**: Define visitor methods for all element types  
✅ **Single Responsibility**: Each visitor should handle one operation  
✅ **Stateless Visitors**: Keep visitors stateless when possible  
✅ **Documentation**: Document what each visitor does  
✅ **Avoid Tight Coupling**: Don't expose unnecessary element internals  
✅ **Type Safety**: Use generics for type-safe visitors

## Common Mistakes

❌ **Exposing Encapsulation**: Breaking element encapsulation for visitors

```java
// WRONG - Exposes private data
class Visitor {
    public void visit(Book book) {
        System.out.println(book.internalPrice);  // Private data exposed
    }
}

// RIGHT - Use public accessors
class Visitor {
    public void visit(Book book) {
        System.out.println(book.getPrice());  // Use public method
    }
}
```

❌ **Heavy Visitors**: Making visitors do too much

```java
// WRONG - Visitor handles multiple concerns
class Visitor {
    public void visit(Book book) {
        calculatePrice();
        applyDiscount();
        generateReport();
        sendEmail();
    }
}

// RIGHT - Single responsibility
class PriceVisitor {
    public void visit(Book book) {
        calculatePrice();
    }
}
```

❌ **Forgetting to Accept**: Not implementing accept() properly

```java
// WRONG - Accept doesn't call visitor
class Book implements Product {
    public void accept(Visitor visitor) {
        // Missing: visitor.visit(this);
    }
}

// RIGHT - Accept calls visitor with self
class Book implements Product {
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
```

## When NOT to Use Visitor Pattern

- When adding new element types frequently
- When element hierarchy is unstable
- When operations are simple and infrequent
- When code clarity is reduced by the pattern
- When performance is critical and overhead matters
- When you only have one or two operations

## Visitor vs Strategy Pattern

| Aspect        | Visitor                                 | Strategy                    |
| ------------- | --------------------------------------- | --------------------------- |
| **Focus**     | Multiple operations on object structure | Single algorithm variant    |
| **Scope**     | Operates on multiple element types      | Operates on one context     |
| **Addition**  | New operations easy, new elements hard  | Strategy change at runtime  |
| **Structure** | Complex object hierarchy                | Simple algorithm selection  |
| **Coupling**  | Couples elements to visitor interface   | Couples context to strategy |

## Related Patterns

- **Composite Pattern**: Often used together for tree structures
- **Iterator Pattern**: Can be used with Visitor to traverse structures
- **Strategy Pattern**: Similar but focuses on algorithm selection
- **Observer Pattern**: Notifies multiple observers of events
- **Template Method Pattern**: Defines algorithm skeleton
- **Chain of Responsibility Pattern**: Alternative for handling operations

## Complete E-Commerce Example

```java
interface Product {
    void accept(ProductVisitor visitor);
}

class Book implements Product {
    private String title;
    private double price;

    public Book(String title, double price) {
        this.title = title;
        this.price = price;
    }

    public String getTitle() { return title; }
    public double getPrice() { return price; }

    @Override
    public void accept(ProductVisitor visitor) {
        visitor.visit(this);
    }
}

class Laptop implements Product {
    private String model;
    private double price;

    public Laptop(String model, double price) {
        this.model = model;
        this.price = price;
    }

    public String getModel() { return model; }
    public double getPrice() { return price; }

    @Override
    public void accept(ProductVisitor visitor) {
        visitor.visit(this);
    }
}

interface ProductVisitor {
    void visit(Book book);
    void visit(Laptop laptop);
}

class ShoppingCartVisitor implements ProductVisitor {
    private double total = 0;
    private int itemCount = 0;

    @Override
    public void visit(Book book) {
        total += book.getPrice();
        itemCount++;
    }

    @Override
    public void visit(Laptop laptop) {
        total += laptop.getPrice();
        itemCount++;
    }

    public void printReceipt() {
        System.out.println("Items: " + itemCount);
        System.out.println("Total: $" + total);
    }
}

class TaxCalculatorVisitor implements ProductVisitor {
    private double totalTax = 0;

    @Override
    public void visit(Book book) {
        totalTax += book.getPrice() * 0.02; // 2% tax on books
    }

    @Override
    public void visit(Laptop laptop) {
        totalTax += laptop.getPrice() * 0.08; // 8% tax on electronics
    }

    public double getTotalTax() {
        return totalTax;
    }
}

// Usage
List<Product> cart = new ArrayList<>();
cart.add(new Book("Java Programming", 50));
cart.add(new Laptop("Dell XPS", 1000));
cart.add(new Book("Design Patterns", 45));

ShoppingCartVisitor cartVisitor = new ShoppingCartVisitor();
for (Product product : cart) {
    product.accept(cartVisitor);
}
cartVisitor.printReceipt();

TaxCalculatorVisitor taxVisitor = new TaxCalculatorVisitor();
for (Product product : cart) {
    product.accept(taxVisitor);
}
System.out.println("Total Tax: $" + taxVisitor.getTotalTax());
```

## Conclusion

The Visitor Pattern is a powerful tool for managing operations on complex object structures. It excels when you need to add many operations to a stable hierarchy of objects without modifying those objects. By separating operations from the data structures, it keeps code clean, maintainable, and follows the Open/Closed Principle. However, it's not suitable for scenarios where the element hierarchy changes frequently or when simplicity is paramount.
