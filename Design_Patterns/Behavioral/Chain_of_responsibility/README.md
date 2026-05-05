# Chain of Responsibility Pattern

## Overview

The **Chain of Responsibility Pattern** is a behavioral design pattern that lets you pass requests along a chain of handlers. Each handler decides either to process the request or to pass it along the chain to the next handler. It establishes a chain of handlers where each handler can decide to process a request or pass it to the next handler in the chain. This pattern is useful for implementing request processing pipelines, logging frameworks, and middleware systems.

## Key Characteristics

- **Chain of Handlers**: Creates a linked chain of handlers
- **Decoupling**: Sender and receiver are decoupled
- **Dynamic Chain**: Chain can be built dynamically at runtime
- **Request Propagation**: Request flows through the chain
- **Flexible Processing**: Each handler can process or delegate the request

## Problem it Solves

Without the Chain of Responsibility Pattern, you'd need complex conditional logic:

- **Tight Coupling**: Sender must know all handlers
- **Hard to Maintain**: Adding new handlers requires modifying existing code
- **Inflexible**: Order of processing is hardcoded

With the Chain of Responsibility Pattern, handlers are loosely coupled and can be arranged in any order.

## Pattern Components

### 1. **Handler Interface**

Defines the contract for handlers in the chain:

```java
abstract class Handler {
    protected Handler next;

    public void setNext(Handler next) {
        this.next = next;
    }

    public abstract void handle(String username, String password);
}
```

### 2. **Concrete Handlers**

Implementations that process or pass requests:

```java
class UsernameCheck extends Handler {
    @Override
    public void handle(String username, String password) {
        if (username == null || username.isEmpty()) {
            System.out.println("Username is missing ❌");
            return;
        }
        System.out.println("Username OK ✅");

        if (next != null) next.handle(username, password);
    }
}

class PasswordCheck extends Handler {
    @Override
    public void handle(String username, String password) {
        if (password == null || password.isEmpty()) {
            System.out.println("Password is missing ❌");
            return;
        }
        System.out.println("Password OK ✅");

        if (next != null) next.handle(username, password);
    }
}

class UserValidation extends Handler {
    public void handle(String username, String password) {
        if (username.equals("admin") && password.equals("1234")) {
            System.out.println("Login Successful 🎉");
        } else {
            System.out.println("Invalid Credentials ❌");
        }
    }
}
```

## How It Works

The Chain of Responsibility processes requests through a series of handlers:

1. **Build Chain**: Link handlers using `setNext()`
2. **Start Request**: Send request to the first handler
3. **Process**: First handler checks the request
4. **Propagate**: If successful, passes to next handler
5. **Terminate**: Chain ends when a handler stops propagation

## Usage Example

```java
Handler h1 = new UsernameCheck();
Handler h2 = new PasswordCheck();
Handler h3 = new UserValidation();

h1.setNext(h2);
h2.setNext(h3);

// Test
h1.handle("admin", "1234");
```

### Output:

```
Username OK ✅
Password OK ✅
Login Successful 🎉
```

### Failed Case:

```java
h1.handle("", "1234");
```

### Output:

```
Username is missing ❌
```

Notice how the chain stops when a handler fails.

## Chain of Responsibility Workflow

```
Request: ("admin", "1234")
    ↓
UsernameCheck
    ├─→ Check: username == null or empty?
    ├─→ Result: NO ✅
    └─→ Pass to next handler
    ↓
PasswordCheck
    ├─→ Check: password == null or empty?
    ├─→ Result: NO ✅
    └─→ Pass to next handler
    ↓
UserValidation
    ├─→ Check: username == "admin" && password == "1234"?
    ├─→ Result: YES ✅
    └─→ "Login Successful 🎉"
```

## Request Processing Pipeline

```
                 ┌──────────────────┐
                 │ Initial Request  │
                 └────────┬─────────┘
                          ↓
            ┌─────────────────────────────┐
            │   Handler 1: UsernameCheck  │
            │  ✓ Username exists?         │
            │  └─→ Pass to next           │
            └────────┬────────────────────┘
                     ↓
            ┌─────────────────────────────┐
            │   Handler 2: PasswordCheck  │
            │  ✓ Password exists?         │
            │  └─→ Pass to next           │
            └────────┬────────────────────┘
                     ↓
            ┌─────────────────────────────┐
            │  Handler 3: UserValidation  │
            │  ✓ Credentials match?       │
            │  └─→ Process & Terminate    │
            └────────┬────────────────────┘
                     ↓
                 ┌──────────────────┐
                 │ Final Response   │
                 └──────────────────┘
```

## Chain Termination Points

```
Successful Path:
UsernameCheck → PasswordCheck → UserValidation → Complete

Failed Path (Missing Username):
UsernameCheck ❌ STOPS
└─→ Error: "Username is missing ❌"

Failed Path (Missing Password):
UsernameCheck ✅ → PasswordCheck ❌ STOPS
└─→ Error: "Password is missing ❌"

Failed Path (Invalid Credentials):
UsernameCheck ✅ → PasswordCheck ✅ → UserValidation ❌ STOPS
└─→ Error: "Invalid Credentials ❌"
```

## Advantages

✅ **Loose Coupling**: Senders and receivers are decoupled  
✅ **Single Responsibility**: Each handler has one reason to change  
✅ **Dynamic Chain**: Chain can be built/modified at runtime  
✅ **Flexible Order**: Change handler order without modifying code  
✅ **Extensibility**: Add new handlers easily  
✅ **Reusability**: Handlers can be reused in different chains

## Disadvantages

❌ **Request May Not Be Handled**: No guarantee request reaches a handler that handles it  
❌ **Debugging Difficulty**: Complex chains hard to trace  
❌ **Performance**: Passing through many handlers adds overhead  
❌ **Complexity**: More complex than direct handling  
❌ **Order Dependency**: Results may depend on chain order

## Real-World Examples

- **Logging Frameworks**: Log4j, SLF4J with different log levels
- **HTTP Request Processing**: Servlet filters, middleware chains
- **Approval Workflows**: Manager → Director → VP → CEO approvals
- **Event Handling**: GUI event handling in DOM trees
- **Exception Handling**: Try-catch blocks catching different exception types
- **Browser Request Processing**: Caching → Authentication → Processing
- **Validation Chains**: Form validation with multiple validators
- **Payment Processing**: Fraud check → Balance check → Process payment
- **Spring Framework**: Filter chains for request handling

## Use Cases

- When multiple objects may handle a request
- When you want to issue requests without knowing the receiver
- When you want to specify handlers dynamically at runtime
- When you need a processing pipeline with multiple stages
- When you want to decouple requestors from handlers

## Chain of Responsibility vs Other Patterns

| Pattern                     | Purpose                         | Key Difference              |
| --------------------------- | ------------------------------- | --------------------------- |
| **Chain of Responsibility** | Pass requests along chain       | Handlers don't know senders |
| **Command**                 | Encapsulate requests as objects | Objects store request info  |
| **Strategy**                | Encapsulate algorithms          | One algorithm selected      |
| **Decorator**               | Add behavior dynamically        | Wraps single object         |
| **Middleware**              | Process requests in pipeline    | Specific to web frameworks  |

## Implementation Variations

### 1. **Array-Based Chain**

```java
class ChainManager {
    private Handler[] handlers;

    public ChainManager(Handler... handlers) {
        this.handlers = handlers;
    }

    public void process(String username, String password) {
        for (Handler handler : handlers) {
            if (!handler.handle(username, password)) {
                break;  // Stop if handler returns false
            }
        }
    }
}
```

### 2. **List-Based Chain**

```java
class ChainManager {
    private List<Handler> handlers = new ArrayList<>();

    public void addHandler(Handler handler) {
        handlers.add(handler);
    }

    public void process(String username, String password) {
        for (Handler handler : handlers) {
            handler.handle(username, password);
        }
    }
}
```

### 3. **Conditional Chain Building**

```java
public static Handler buildAuthChain(boolean checkUsername,
                                     boolean checkPassword) {
    Handler chain = null;

    if (checkPassword) {
        chain = new UserValidation();
    }
    if (checkUsername) {
        Handler check = new PasswordCheck();
        check.setNext(chain);
        chain = check;
    }
    return chain;
}
```

## Best Practices

✅ **Always Check Next**: Verify `next != null` before calling  
✅ **Stop Condition**: Define clear stop conditions for the chain  
✅ **Default Handler**: Provide a default handler at the end  
✅ **Error Handling**: Handle cases where no handler processes request  
✅ **Return Value**: Consider return values to indicate success/failure  
✅ **Immutable Requests**: Pass immutable request objects  
✅ **Document Chain Order**: Clearly document the expected chain order

## Advanced Example: Logging with Return Values

```java
abstract class Handler {
    protected Handler next;

    public void setNext(Handler next) {
        this.next = next;
    }

    public abstract boolean handle(String username, String password);
}

class UsernameCheck extends Handler {
    public boolean handle(String username, String password) {
        if (username == null || username.isEmpty()) {
            System.out.println("❌ Username is missing");
            return false;
        }
        System.out.println("✅ Username OK");

        if (next != null) {
            return next.handle(username, password);
        }
        return true;
    }
}

// Usage
Handler chain = new UsernameCheck();
chain.setNext(new PasswordCheck());
chain.setNext(new UserValidation());

boolean success = chain.handle("admin", "1234");
if (success) {
    System.out.println("All checks passed!");
} else {
    System.out.println("Request failed validation");
}
```

## Common Mistakes

❌ **Not Checking Next**: Forgetting to check if `next != null`

```java
// WRONG - NullPointerException if next is null
next.handle(username, password);

// RIGHT
if (next != null) next.handle(username, password);
```

❌ **No Termination**: Creating infinite loops

```java
// WRONG - Creates infinite loop
this.setNext(this);

// RIGHT - Set proper chain termination
h1.setNext(h2);
h2.setNext(h3);
h3.setNext(null);  // Explicit termination
```

❌ **Modifying Request**: Changing request in the chain

```java
// WRONG - Don't modify original request
public void handle(String username, String password) {
    username = username.toLowerCase();  // Modifies original
}

// RIGHT - Create new request or pass as-is
public void handle(String username, String password) {
    String normalizedUsername = username.toLowerCase();
}
```

## When NOT to Use Chain of Responsibility

- When only one object needs to handle the request
- When handler order is fixed and simple
- When all handlers must process the request
- When performance is critical and chain depth is large
- When you need guaranteed handling of all requests

## Related Patterns

- **Command Pattern**: Encapsulates requests similarly
- **Observer Pattern**: Notifies multiple handlers differently
- **Decorator Pattern**: Similar wrapping but with different purpose
- **Strategy Pattern**: Encapsulates algorithms similarly
- **Interpreter Pattern**: Parses language similarly
- **Composite Pattern**: Often used with Chain of Responsibility for tree traversal
