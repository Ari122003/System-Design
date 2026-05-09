# Iterator Pattern

## Overview

The **Iterator Pattern** is a behavioral design pattern that provides a way to access elements of a collection sequentially without exposing its underlying representation. It lets you traverse different collection types in a uniform way. The pattern defines an interface for accessing collection elements one by one while keeping the collection's internal structure hidden from the client.

## Key Characteristics

- **Sequential Access**: Access elements one at a time in sequence
- **Abstraction**: Hide internal collection structure from clients
- **Uniform Interface**: Access different collections uniformly
- **Stateful Iteration**: Iterator maintains position in collection
- **Decoupling**: Client code is decoupled from collection implementation

## Problem it Solves

Without the Iterator Pattern, clients must know collection internals:

```java
// Without Iterator - Tight coupling to collection structure
Book[] books = library.getBooks();
for (int i = 0; i < books.length; i++) {
    if (books[i] != null) {
        System.out.println(books[i].getName());
    }
}

// Different collection types require different iteration code
List<Book> bookList = library.getBookList();
for (Book book : bookList) {
    System.out.println(book.getName());
}
```

With the Iterator Pattern, clients use a uniform interface regardless of collection type.

## Pattern Components

### 1. **Element Class**

The objects stored in the collection:

```java
class Book {
    private String name;

    public Book(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
```

### 2. **Collection Interface**

Defines contract for creating iterators:

```java
interface Library {
    Iterator createIterator();
}
```

### 3. **Concrete Collection**

Implements the collection and provides iterator:

```java
class BookCollection implements Library {
    private Book[] books;
    private int index = 0;

    public BookCollection(int size) {
        books = new Book[size];
    }

    public void addBook(Book book) {
        if (index < books.length) {
            books[index++] = book;
        }
    }

    public Iterator createIterator() {
        return new BookIterator(books);
    }
}
```

### 4. **Iterator Interface**

Defines contract for iteration operations:

```java
interface Iterator {
    boolean hasNext();
    Book next();
}
```

### 5. **Concrete Iterator**

Implements iteration logic for the collection:

```java
class BookIterator implements Iterator {
    private Book[] books;
    private int position = 0;

    public BookIterator(Book[] books) {
        this.books = books;
    }

    public boolean hasNext() {
        return position < books.length && books[position] != null;
    }

    public Book next() {
        return books[position++];
    }
}
```

## How It Works

The Iterator Pattern separates traversal from collection structure:

1. **Create Collection**: Create a BookCollection and add books
2. **Get Iterator**: Request iterator from collection
3. **Check Availability**: Use `hasNext()` to check if more elements exist
4. **Access Element**: Use `next()` to get current element and advance position
5. **Repeat**: Continue until all elements are traversed

## Usage Example

```java
BookCollection library = new BookCollection(5);

library.addBook(new Book("Java"));
library.addBook(new Book("Spring Boot"));
library.addBook(new Book("Microservices"));

Iterator iterator = library.createIterator();

while (iterator.hasNext()) {
    Book book = iterator.next();
    System.out.println(book.getName());
}
```

### Output:

```
Java
Spring Boot
Microservices
```

The client code doesn't know the collection uses an array internally.

## Iterator Pattern Workflow

```
BookCollection library
    ↓
library.addBook(Book)
library.addBook(Book)
library.addBook(Book)
    ↓
library.createIterator()
    ↓
Iterator iterator
    ├─→ hasNext()? Yes
    ├─→ next() → Book ("Java")
    ├─→ hasNext()? Yes
    ├─→ next() → Book ("Spring Boot")
    ├─→ hasNext()? Yes
    ├─→ next() → Book ("Microservices")
    ├─→ hasNext()? No
    └─→ Exit loop
```

## Iterator State Machine

```
┌───────────┐
│  Initial  │ position = 0
└─────┬─────┘
      │ hasNext()? (true if books[0] != null)
      ↓
┌─────────────────┐
│ At Position 0   │ next() → return books[0], position++
└────────┬────────┘
         │ hasNext()? (true if books[1] != null)
         ↓
┌─────────────────┐
│ At Position 1   │ next() → return books[1], position++
└────────┬────────┘
         │ hasNext()? (true if books[2] != null)
         ↓
┌─────────────────┐
│ At Position 2   │ next() → return books[2], position++
└────────┬────────┘
         │ hasNext()? false (position >= length)
         ↓
┌───────────┐
│ Finished  │
└───────────┘
```

## Collection Abstraction Comparison

### Without Iterator (Direct Access):

```
Client Code                Collection
    ↓
    ├─→ getBooks()         Array [Book, Book, Book]
    ├─→ getSize()          List<Book>
    ├─→ getElement(i)      Tree<Book>
    └─→ Custom logic for each type
```

### With Iterator (Uniform Access):

```
Client Code                Collection         Iterator
    ↓                           ↓                 ↓
    ├─→ createIterator()───→ BookCollection ──→ BookIterator
    ├─→ hasNext()           ┌──────────────────┐
    ├─→ next()              │ position = 0     │
    └─→ Same code           │ position = 1     │
       for all types        │ position = 2     │
                            └──────────────────┘
```

## Advantages

✅ **Uniform Interface**: Access any collection uniformly  
✅ **Abstraction**: Hide collection internals from clients  
✅ **Single Responsibility**: Collection stores data, iterator accesses it  
✅ **Multiple Iterators**: Can have multiple iterators on same collection  
✅ **Decoupling**: Client doesn't depend on collection implementation  
✅ **Extensibility**: Add new collection types without changing client code

## Disadvantages

❌ **Complexity**: Adds more classes and interfaces  
❌ **Performance**: Iterator access may be slower than direct access  
❌ **Overkill for Simple Collections**: May be unnecessary for small collections  
❌ **Concurrent Modification**: Modifying collection during iteration can cause issues  
❌ **Additional Memory**: Iterator objects consume additional memory

## Real-World Examples

- **Java Collections**: List, Set, Map provide Iterator interface
- **Database Cursors**: Cursor objects iterate over query results
- **File System Traversal**: Traversing directory trees
- **DOM Trees**: Traversing HTML/XML elements
- **Graph Traversal**: DFS/BFS algorithms on graphs
- **Streaming APIs**: Sequential data stream processing
- **Directory Listing**: Iterating over files and folders
- **Database Rows**: ResultSet iteration in JDBC
- **Event Streams**: Processing event sequences
- **Range Objects**: Iterating over numeric ranges

## Use Cases

- When you need to access elements sequentially without exposing structure
- When you have different collection types that need uniform access
- When you want to hide collection internals from clients
- When you need multiple traversals of the same collection
- When you want to decouple collection from client code

## Iterator vs Other Patterns

| Pattern           | Purpose                                  | Key Difference              |
| ----------------- | ---------------------------------------- | --------------------------- |
| **Iterator**      | Sequential access to collection elements | Uniform traversal interface |
| **Composite**     | Create tree structures                   | Hierarchical composition    |
| **Strategy**      | Encapsulate algorithms                   | Algorithm selection         |
| **Visitor**       | Perform operations on collection         | Operations on elements      |
| **For-Each Loop** | Built-in iteration                       | Language-level construct    |

## Iterator Variants

### 1. **Forward Iterator** (What we implemented)

Iterates from first to last element:

```java
interface Iterator {
    boolean hasNext();
    Book next();
}
```

### 2. **Bidirectional Iterator**

Iterates in both directions:

```java
interface BidirectionalIterator {
    boolean hasNext();
    boolean hasPrevious();
    Book next();
    Book previous();
}
```

### 3. **Random Access Iterator**

Access elements by index:

```java
interface RandomAccessIterator {
    Book get(int index);
    void set(int index, Book book);
}
```

### 4. **Filtered Iterator**

Iterates only matching elements:

```java
class FilteredIterator implements Iterator {
    private Iterator iterator;
    private Predicate<Book> filter;

    public boolean hasNext() {
        while (iterator.hasNext()) {
            if (filter.test(iterator.next())) {
                return true;
            }
        }
        return false;
    }
}
```

## Advanced Example: Bidirectional Iterator

```java
interface BidirectionalIterator {
    boolean hasNext();
    boolean hasPrevious();
    Book next();
    Book previous();
}

class BiDirectionalBookIterator implements BidirectionalIterator {
    private Book[] books;
    private int position = -1;

    public BiDirectionalBookIterator(Book[] books) {
        this.books = books;
    }

    public boolean hasNext() {
        return position + 1 < books.length && books[position + 1] != null;
    }

    public boolean hasPrevious() {
        return position > 0;
    }

    public Book next() {
        return books[++position];
    }

    public Book previous() {
        return books[--position];
    }
}
```

## Advanced Example: Filtered Iterator

```java
class FilteredBookIterator implements Iterator {
    private Iterator iterator;
    private Predicate<Book> filter;
    private Book nextBook;
    private boolean hasNextBook;

    public FilteredBookIterator(Iterator iterator, Predicate<Book> filter) {
        this.iterator = iterator;
        this.filter = filter;
        advance();
    }

    private void advance() {
        hasNextBook = false;
        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (filter.test(book)) {
                nextBook = book;
                hasNextBook = true;
                break;
            }
        }
    }

    public boolean hasNext() {
        return hasNextBook;
    }

    public Book next() {
        Book book = nextBook;
        advance();
        return book;
    }
}

// Usage
Iterator baseIterator = library.createIterator();
Iterator filtered = new FilteredBookIterator(baseIterator,
    book -> book.getName().contains("Java"));

while (filtered.hasNext()) {
    System.out.println(filtered.next().getName());
}
```

## Java Collections Iterator

Java provides built-in Iterator interface:

```java
// Collection provides Iterator
List<Book> books = new ArrayList<>();
books.add(new Book("Java"));
books.add(new Book("Spring"));

// Get iterator
Iterator<Book> iterator = books.iterator();

// Use iterator
while (iterator.hasNext()) {
    Book book = iterator.next();
    System.out.println(book.getName());
}

// Or use enhanced for loop (uses Iterator internally)
for (Book book : books) {
    System.out.println(book.getName());
}
```

## Best Practices

✅ **Immutable Iterators**: Make iterators immutable when possible  
✅ **Clear Interface**: Define clear hasNext() and next() methods  
✅ **Handle Edge Cases**: Properly handle null elements and empty collections  
✅ **Throw Exceptions**: Throw NoSuchElementException when appropriate  
✅ **Document Behavior**: Clearly document iteration order  
✅ **Fail-Fast**: Detect concurrent modifications when possible  
✅ **Efficiency**: Design iterators for efficient traversal

## Common Mistakes

❌ **Not Checking hasNext()**: Can cause NoSuchElementException

```java
// WRONG
while (true) {
    Book book = iterator.next();  // Can throw exception
}

// RIGHT
while (iterator.hasNext()) {
    Book book = iterator.next();  // Safe
}
```

❌ **Modifying Collection During Iteration**: Causes undefined behavior

```java
// WRONG - Can skip elements or crash
while (iterator.hasNext()) {
    Book book = iterator.next();
    library.removeBook(book);  // Don't modify during iteration
}

// RIGHT - Remove through iterator
while (iterator.hasNext()) {
    Book book = iterator.next();
    iterator.remove();  // If supported
}
```

❌ **Exposing Internal Structure**: Defeats the purpose of Iterator

```java
// WRONG - Exposes array
public Book[] getBooks() {
    return books;  // External code can access directly
}

// RIGHT - Force use of iterator
public Iterator createIterator() {
    return new BookIterator(books);  // Only way to access
}
```

## Concurrent Modification Handling

```java
class SafeBookIterator implements Iterator {
    private Book[] books;
    private int position = 0;
    private int expectedModCount;
    private int modCount;  // Shared with collection

    public SafeBookIterator(Book[] books, int modCount) {
        this.books = books;
        this.modCount = modCount;
        this.expectedModCount = modCount;
    }

    public boolean hasNext() {
        checkForModification();
        return position < books.length && books[position] != null;
    }

    public Book next() {
        checkForModification();
        return books[position++];
    }

    private void checkForModification() {
        if (expectedModCount != modCount) {
            throw new ConcurrentModificationException();
        }
    }
}
```

## When NOT to Use Iterator Pattern

- When direct array/collection access is sufficient
- When performance is critical and iterator overhead matters
- When you have very simple, stable collections
- When concurrent access patterns are expected
- When you don't need to support multiple collection types

## Iterable vs Iterator

```java
// Iterable - Can be iterated
interface Iterable<T> {
    Iterator<T> iterator();
}

// Iterator - Does the iteration
interface Iterator<T> {
    boolean hasNext();
    T next();
    void remove();
}

// Iterable allows for-each loop
for (Book book : library) {  // Uses Iterable.iterator()
    System.out.println(book.getName());
}
```

## Related Patterns

- **Composite Pattern**: Often used with Iterator for tree traversal
- **Visitor Pattern**: Can be combined with Iterator for operations
- **Strategy Pattern**: Different iteration strategies can be strategies
- **Factory Pattern**: Often creates iterators
- **Template Method Pattern**: Iterator defines template for traversal
- **Memento Pattern**: Can store iterator state for resuming

## Complete Example: Multiple Collection Types

```java
interface BookCollection {
    Iterator createIterator();
}

class ArrayBookCollection implements BookCollection {
    private Book[] books;

    public ArrayBookCollection(Book[] books) {
        this.books = books;
    }

    public Iterator createIterator() {
        return new ArrayIterator(books);
    }
}

class ListBookCollection implements BookCollection {
    private List<Book> books;

    public ListBookCollection(List<Book> books) {
        this.books = books;
    }

    public Iterator createIterator() {
        return new ListIterator(books);
    }
}

// Client code works with both uniformly
BookCollection collection1 = new ArrayBookCollection(...);
BookCollection collection2 = new ListBookCollection(...);

Iterator iter1 = collection1.createIterator();
Iterator iter2 = collection2.createIterator();

// Same iteration code for both!
while (iter1.hasNext()) {
    System.out.println(iter1.next().getName());
}

while (iter2.hasNext()) {
    System.out.println(iter2.next().getName());
}
```
