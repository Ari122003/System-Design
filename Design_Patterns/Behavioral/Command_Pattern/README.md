# Command Pattern

## Overview

The **Command Pattern** is a behavioral design pattern that encapsulates a request as an object, thereby letting you parameterize clients with different requests, queue requests, and support undoable operations. It decouples the objects that issue requests from the objects that perform the requests. The pattern turns a request into a stand-alone object that contains all information about the request, enabling parameterization, queuing, logging, and undo functionality.

## Key Characteristics

- **Encapsulation**: Request is encapsulated as an object
- **Parameterization**: Requests can be parameterized and queued
- **Decoupling**: Invoker and receiver are decoupled
- **Flexibility**: Commands can be executed, queued, logged, or undone
- **First-Class Objects**: Commands are treated as objects (passed, stored, composed)

## Problem it Solves

Without the Command Pattern, there's tight coupling between invoker and receiver:

```java
// Without Command Pattern - Tight coupling
public void processOrder(String operation, String item) {
    if (operation.equals("place")) {
        orderService.placeOrder(item);
    } else if (operation.equals("cancel")) {
        orderService.cancelOrder(item);
    }
}
```

With the Command Pattern, requests are encapsulated as objects, enabling queuing, logging, and undo.

## Pattern Components

### 1. **Command Interface**

Defines the contract for all commands:

```java
interface OrderCommand {
    void execute();
}
```

### 2. **Concrete Commands**

Implements specific operations:

```java
class PlaceOrderCommand implements OrderCommand {
    private OrderService orderService;
    private String item;

    public PlaceOrderCommand(OrderService orderService, String item) {
        this.orderService = orderService;
        this.item = item;
    }

    public void execute() {
        orderService.placeOrder(item);
    }
}

class CancelOrderCommand implements OrderCommand {
    private OrderService orderService;
    private String item;

    public CancelOrderCommand(OrderService orderService, String item) {
        this.orderService = orderService;
        this.item = item;
    }

    public void execute() {
        orderService.cancelOrder(item);
    }
}
```

### 3. **Receiver**

Performs the actual operations:

```java
class OrderService {
    public void placeOrder(String item) {
        System.out.println("Order placed for: " + item);
    }

    public void cancelOrder(String item) {
        System.out.println("Order cancelled for: " + item);
    }
}
```

### 4. **Invoker**

Executes commands, possibly after queuing:

```java
class OrderProcessor {
    private Queue<OrderCommand> queue = new LinkedList<>();

    public void takeOrder(OrderCommand command) {
        queue.add(command);
    }

    public void processOrders() {
        while (!queue.isEmpty()) {
            OrderCommand command = queue.poll();
            command.execute();
        }
    }
}
```

## How It Works

The Command Pattern encapsulates requests as objects:

1. **Command Creation**: Create command objects with receiver and parameters
2. **Queuing**: Store commands in a queue or collection
3. **Delayed Execution**: Execute commands later when ready
4. **Batch Processing**: Process multiple commands together
5. **Flexibility**: Commands can be logged, undone, or rescheduled

## Usage Example

```java
OrderService service = new OrderService();

OrderCommand order1 = new PlaceOrderCommand(service, "Burger");
OrderCommand order2 = new PlaceOrderCommand(service, "Pizza");
OrderCommand cancel = new CancelOrderCommand(service, "Burger");

OrderProcessor processor = new OrderProcessor();

processor.takeOrder(order1);
processor.takeOrder(order2);
processor.takeOrder(cancel);

processor.processOrders();
```

### Output:

```
Order placed for: Burger
Order placed for: Pizza
Order cancelled for: Burger
```

Commands are queued and executed in order.

## Command Pattern Workflow

```
Main
    ↓
Create OrderService (Receiver)
    ↓
Create Commands with service & parameters
    ├─→ PlaceOrderCommand("Burger")
    ├─→ PlaceOrderCommand("Pizza")
    └─→ CancelOrderCommand("Burger")
    ↓
Create OrderProcessor (Invoker)
    ↓
Queue Commands
    ├─→ takeOrder(order1)
    ├─→ takeOrder(order2)
    └─→ takeOrder(cancel)
    ↓
Process (Execute) Commands
    ├─→ command.execute()
    ├─→ command.execute()
    └─→ command.execute()
    ↓
Commands execute on Receiver (OrderService)
```

## Command Encapsulation

```
Without Command Pattern:
┌──────────────┐      Direct Call      ┌─────────────────┐
│   Client     │─────────────────────→ │ OrderService    │
└──────────────┘                       └─────────────────┘

With Command Pattern:
┌──────────────┐     Encapsulated      ┌─────────────────┐     Executes on
│   Client     │─→ OrderCommand ────→  │ OrderProcessor  │─→  OrderService
└──────────────┘    (Request Object)   └─────────────────┘     (Receiver)
```

## Queue Processing Pipeline

```
Queue State:
Initial:      []
After order1: [PlaceOrderCommand(Burger)]
After order2: [PlaceOrderCommand(Burger), PlaceOrderCommand(Pizza)]
After cancel: [PlaceOrderCommand(Burger), PlaceOrderCommand(Pizza), CancelOrderCommand(Burger)]

Processing:
Step 1: poll() → PlaceOrderCommand(Burger) → execute()
Step 2: poll() → PlaceOrderCommand(Pizza) → execute()
Step 3: poll() → CancelOrderCommand(Burger) → execute()

Queue becomes empty []
```

## Advantages

✅ **Decoupling**: Invoker and receiver are decoupled  
✅ **Queuing**: Commands can be queued and executed later  
✅ **Composition**: Commands can be composed into macros  
✅ **Undo/Redo**: Commands can support undo and redo operations  
✅ **Logging**: Commands can be logged for audit trails  
✅ **Transactional**: Multiple commands can be executed atomically  
✅ **Flexibility**: New commands can be added without modifying invoker

## Disadvantages

❌ **Complexity**: Adds more classes and complexity  
❌ **Memory Overhead**: Each command object consumes memory  
❌ **Performance**: Command objects may have performance overhead  
❌ **Overkill for Simple Cases**: May be unnecessary for simple operations  
❌ **Learning Curve**: Developers need to understand the pattern

## Real-World Examples

- **GUI Actions**: Button clicks, menu items as command objects
- **Transaction Processing**: Database operations as commands
- **Undo/Redo Systems**: Text editors, drawing applications
- **Task Scheduling**: Job queues, scheduled tasks
- **Macro Recording**: Sequence of actions as single command
- **Remote Execution**: Sending commands over network
- **Request Queuing**: HTTP request handling, message queues
- **Batch Processing**: Executing multiple operations
- **Event Systems**: Event handling as commands
- **API Endpoints**: HTTP requests mapped to commands

## Use Cases

- When you want to parameterize objects with requests
- When you need to queue, log, or delay request execution
- When you want to support undo/redo operations
- When you need to structure requests as objects
- When multiple commands need to be executed together

## Command vs Other Patterns

| Pattern                     | Purpose                         | Key Difference           |
| --------------------------- | ------------------------------- | ------------------------ |
| **Command**                 | Encapsulate requests as objects | Request becomes object   |
| **Strategy**                | Encapsulate algorithms          | Algorithm selection      |
| **State**                   | Object behavior based on state  | State changes behavior   |
| **Observer**                | Notify multiple objects         | One-to-many notification |
| **Chain of Responsibility** | Pass request along chain        | Chain of handlers        |

## Command Pattern Variants

### 1. **Basic Command** (What we implemented)

Simple request encapsulation:

```java
interface Command {
    void execute();
}
```

### 2. **Command with Undo**

Supports undoable operations:

```java
interface Command {
    void execute();
    void undo();
}

class PlaceOrderCommand implements Command {
    public void execute() {
        orderService.placeOrder(item);
    }

    public void undo() {
        orderService.cancelOrder(item);
    }
}
```

### 3. **Command with Parameters**

Pass additional context:

```java
interface Command {
    void execute(Context context);
}
```

### 4. **Macro Command**

Composite of multiple commands:

```java
class MacroCommand implements Command {
    private List<Command> commands = new ArrayList<>();

    public void addCommand(Command command) {
        commands.add(command);
    }

    public void execute() {
        for (Command command : commands) {
            command.execute();
        }
    }
}
```

## Advanced Example: Undo/Redo System

```java
interface Command {
    void execute();
    void undo();
}

class UndoableOrderCommand implements Command {
    private OrderService service;
    private String item;

    public void execute() {
        service.placeOrder(item);
    }

    public void undo() {
        service.cancelOrder(item);
    }
}

class CommandHistory {
    private Stack<Command> undoStack = new Stack<>();
    private Stack<Command> redoStack = new Stack<>();

    public void execute(Command command) {
        command.execute();
        undoStack.push(command);
        redoStack.clear();  // Clear redo on new action
    }

    public void undo() {
        if (!undoStack.isEmpty()) {
            Command command = undoStack.pop();
            command.undo();
            redoStack.push(command);
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            Command command = redoStack.pop();
            command.execute();
            undoStack.push(command);
        }
    }
}

// Usage
CommandHistory history = new CommandHistory();
history.execute(new UndoableOrderCommand(service, "Burger"));
history.execute(new UndoableOrderCommand(service, "Pizza"));

history.undo();  // Undo Pizza
history.redo();  // Redo Pizza
```

## Advanced Example: Macro Commands

```java
interface Command {
    void execute();
}

class MacroCommand implements Command {
    private List<Command> commands = new ArrayList<>();

    public void add(Command command) {
        commands.add(command);
    }

    public void remove(Command command) {
        commands.remove(command);
    }

    public void execute() {
        for (Command command : commands) {
            command.execute();
        }
    }
}

// Usage
MacroCommand lunchOrders = new MacroCommand();
lunchOrders.add(new PlaceOrderCommand(service, "Burger"));
lunchOrders.add(new PlaceOrderCommand(service, "Fries"));
lunchOrders.add(new PlaceOrderCommand(service, "Drink"));

lunchOrders.execute();  // Execute all commands
```

## Advanced Example: Logging Commands

```java
class LoggingOrderProcessor {
    private Queue<OrderCommand> queue = new LinkedList<>();
    private List<String> log = new ArrayList<>();

    public void takeOrder(OrderCommand command, String description) {
        queue.add(command);
        log.add("Queued: " + description);
    }

    public void processOrders() {
        while (!queue.isEmpty()) {
            OrderCommand command = queue.poll();
            try {
                command.execute();
                log.add("Executed: " + command.getClass().getSimpleName());
            } catch (Exception e) {
                log.add("Failed: " + e.getMessage());
            }
        }
    }

    public void printLog() {
        for (String entry : log) {
            System.out.println(entry);
        }
    }
}
```

## Best Practices

✅ **Encapsulate Fully**: Store all request information in command  
✅ **Immutable Commands**: Make commands immutable when possible  
✅ **Clear Interface**: Define clear execute methods  
✅ **Document Behavior**: Document what each command does  
✅ **Error Handling**: Handle command execution failures  
✅ **Logging**: Log command execution for audit trails  
✅ **Parameterization**: Support command parameterization

## Common Mistakes

❌ **Storing Mutable State**: Don't store mutable state in commands

```java
// WRONG - Command state changes
class PlaceOrderCommand implements Command {
    private String item;  // Can be changed

    public void setItem(String item) {
        this.item = item;  // Dangerous!
    }
}

// RIGHT - Command is immutable
class PlaceOrderCommand implements Command {
    private final String item;  // Immutable

    public PlaceOrderCommand(String item) {
        this.item = item;
    }
}
```

❌ **Direct Receiver Coupling**: Don't tightly couple to receiver

```java
// WRONG - Direct receiver access
class OrderCommand implements Command {
    private OrderService service = new OrderService();  // Tight coupling
}

// RIGHT - Inject receiver
class OrderCommand implements Command {
    private OrderService service;  // Injected

    public OrderCommand(OrderService service) {
        this.service = service;
    }
}
```

❌ **No Error Handling**: Always handle execution errors

```java
// WRONG - No error handling
public void processOrders() {
    while (!queue.isEmpty()) {
        queue.poll().execute();  // Can fail silently
    }
}

// RIGHT - Handle errors gracefully
public void processOrders() {
    while (!queue.isEmpty()) {
        try {
            queue.poll().execute();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
```

## When NOT to Use Command Pattern

- When operations are simple and don't need queuing
- When there's no need for undo/redo or logging
- When performance is critical and command overhead matters
- When requests are always executed immediately
- When there's only one receiver and one command

## Pattern Comparison: Command vs Strategy

| Aspect               | Command          | Strategy         |
| -------------------- | ---------------- | ---------------- |
| **Encapsulates**     | Request/Action   | Algorithm        |
| **Parameterization** | Request details  | Algorithm choice |
| **Queuing**          | Yes, can queue   | No               |
| **Undo/Redo**        | Yes, can support | No               |
| **Execution Time**   | Can be deferred  | Selected upfront |
| **Focus**            | When to execute  | How to execute   |

## Related Patterns

- **Strategy Pattern**: Similar encapsulation but different purpose
- **Observer Pattern**: Can notify about command execution
- **Composite Pattern**: MacroCommand uses composition
- **Memento Pattern**: Often combined for undo/redo
- **State Pattern**: Similar polymorphic dispatch
- **Factory Pattern**: Often used to create commands
- **Chain of Responsibility Pattern**: Commands can form a chain
- **Template Method Pattern**: Can define command templates

## Complete Example: Task Queue System

```java
interface Task extends Command {
    void execute();
}

class PrintTask implements Task {
    private String message;

    public PrintTask(String message) {
        this.message = message;
    }

    public void execute() {
        System.out.println(message);
    }
}

class TaskQueue {
    private Queue<Task> tasks = new LinkedList<>();

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void executeTasks() {
        while (!tasks.isEmpty()) {
            tasks.poll().execute();
        }
    }

    public int pendingTasks() {
        return tasks.size();
    }
}

// Usage
TaskQueue queue = new TaskQueue();
queue.addTask(new PrintTask("Task 1"));
queue.addTask(new PrintTask("Task 2"));
queue.addTask(new PrintTask("Task 3"));

System.out.println("Pending: " + queue.pendingTasks());  // 3
queue.executeTasks();
System.out.println("Pending: " + queue.pendingTasks());  // 0
```
