# Mediator Pattern

## Overview

The **Mediator Pattern** is a behavioral design pattern that defines an object that encapsulates how a set of objects interact. It promotes loose coupling by keeping objects from referring to each other explicitly and letting you vary their interaction independently. Instead of components communicating directly with each other, they communicate through a central mediator object, which reduces dependencies and simplifies maintenance.

## Key Characteristics

- **Centralized Communication**: All communication goes through the mediator
- **Loose Coupling**: Objects don't directly reference each other
- **Simplified Interactions**: Complex communication logic is centralized
- **Easy to Maintain**: Changes to interaction logic are localized
- **Reusable Components**: Objects can be reused in different mediators

## Problem it Solves

Without the Mediator Pattern, objects communicate directly, creating tight coupling:

```java
// Without Mediator - Tight coupling
class ChatUser {
    public void send(String message, ChatUser receiver) {
        receiver.receive(message);  // Direct reference
    }
}

// Every user must know about every other user
user1.send(message, user2);
user1.send(message, user3);
user1.send(message, user4);
```

With the Mediator Pattern, a central mediator manages all communication.

## Pattern Components

### 1. **Mediator Interface**

Defines contract for the mediator:

```java
interface ChatMediator {
    void sendMessage(String message, User sender);
    void addUser(User user);
}
```

### 2. **Concrete Mediator**

Implements the actual communication logic:

```java
class ChatRoom implements ChatMediator {
    private List<User> users = new ArrayList<>();

    @Override
    public void addUser(User user) {
        users.add(user);
    }

    @Override
    public void sendMessage(String message, User sender) {
        for (User user : users) {
            // sender should not receive own message
            if (user != sender) {
                user.receive(message);
            }
        }
    }
}
```

### 3. **Abstract Colleague**

Defines the interface for objects that communicate through the mediator:

```java
abstract class User {
    protected ChatMediator mediator;
    protected String name;

    public User(ChatMediator mediator, String name) {
        this.mediator = mediator;
        this.name = name;
    }

    abstract void send(String message);
    abstract void receive(String message);
}
```

### 4. **Concrete Colleague**

Implements colleague-specific behavior:

```java
class ChatUser extends User {
    public ChatUser(ChatMediator mediator, String name) {
        super(mediator, name);
    }

    @Override
    void send(String message) {
        System.out.println(name + " sends: " + message);
        mediator.sendMessage(message, this);
    }

    @Override
    void receive(String message) {
        System.out.println(name + " received: " + message);
    }
}
```

## How It Works

The Mediator Pattern centralizes object interaction:

1. **Create Mediator**: Create a ChatRoom (mediator) instance
2. **Register Objects**: Add users to the chat room
3. **Send Message**: User calls send(), which delegates to mediator
4. **Distribute Message**: Mediator forwards message to all other users
5. **Receive Message**: Other users receive the message

## Usage Example

```java
ChatMediator chatRoom = new ChatRoom();

User user1 = new ChatUser(chatRoom, "Alice");
User user2 = new ChatUser(chatRoom, "Bob");
User user3 = new ChatUser(chatRoom, "Charlie");

chatRoom.addUser(user1);
chatRoom.addUser(user2);
chatRoom.addUser(user3);

user1.send("Hello everyone!");
user2.send("Hi Alice!");
```

### Output:

```
Alice sends: Hello everyone!
Bob received: Hello everyone!
Charlie received: Hello everyone!
Bob sends: Hi Alice!
Alice received: Hi Alice!
Charlie received: Hi Alice!
```

Notice that users don't communicate directly; the ChatRoom mediates all communication.

## Mediator Pattern Workflow

```
User1.send("Message")
    ↓
ChatRoom.sendMessage("Message", User1)
    ↓
For each user in users:
    ├─→ if user != sender
    │   └─→ user.receive("Message")
    │
    ├─→ User2.receive("Message")
    └─→ User3.receive("Message")
```

## Communication Architecture Comparison

### Without Mediator (Direct Communication):

```
        ┌────────┐
        │ User 1 │
        └────┬───┘
             │ ↓ (Direct references)
        ┌────────┐
        │ User 2 │─ ── ─ ─ ┐
        └───┬────┘         │
            │ ↓            ↓
        ┌────────┐    ┌────────┐
        │ User 3 │────│ User 4 │
        └────────┘    └────────┘

Complexity: O(n²) - Each user knows all others
```

### With Mediator (Centralized Communication):

```
        ┌────────┐  ┌────────┐
        │ User 1 │  │ User 2 │
        └────┬───┘  └───┬────┘
             │          │
             └──────┬───┘
                    │
             ┌──────────────┐
             │  ChatRoom    │
             │  (Mediator)  │
             └──────┬───────┘
                    │
        ┌───────────┴──────────┐
        │                      │
    ┌───────┐            ┌────────┐
    │User 3 │            │ User 4 │
    └───────┘            └────────┘

Complexity: O(n) - Only connected to mediator
```

## Message Flow Diagram

```
Alice sends "Hello"
    ↓
┌─────────────────────────────────────┐
│  ChatRoom.sendMessage()             │
│  ├─→ Iterate users                  │
│  ├─→ Check if user != sender        │
│  └─→ Call user.receive()            │
└─────────────────────────────────────┘
    ├─→ Bob.receive("Hello")
    └─→ Charlie.receive("Hello")
```

## Advantages

✅ **Loose Coupling**: Objects don't directly reference each other  
✅ **Centralized Logic**: Communication logic is in one place  
✅ **Easy to Maintain**: Changes to interaction don't affect objects  
✅ **Reusable Components**: Colleagues can be reused with different mediators  
✅ **Simplified Objects**: Objects focus on their own behavior  
✅ **Flexible Interaction**: Interaction rules can be changed in mediator

## Disadvantages

❌ **God Object**: Mediator can become complex with many colleagues  
❌ **Central Bottleneck**: Mediator can become performance bottleneck  
❌ **Single Point of Failure**: If mediator fails, all communication fails  
❌ **Testability**: Mediator can be difficult to test with many colleagues  
❌ **Debugging**: Complex interaction flows are hard to trace

## Real-World Examples

- **Chat Applications**: Chat rooms coordinating user messages
- **GUI Frameworks**: Dialog boxes coordinating widget interactions
- **Air Traffic Control**: Control tower coordinating aircraft
- **Auction Systems**: Auctioneer coordinating bidders
- **Organization Structure**: HR department coordinating employees
- **Network Routers**: Router managing packet routing
- **Middleware**: Web server coordinating client-server requests
- **Workflow Systems**: Process engine coordinating task execution
- **Game Lobbies**: Lobby server coordinating players
- **Publish-Subscribe Systems**: Message broker mediating publishers/subscribers

## Use Cases

- When objects communicate in complex ways creating tangled dependencies
- When reusing objects becomes difficult due to communication patterns
- When communication patterns need to be varied or changed
- When you want to centralize control of object interactions
- When communication logic needs to be tested independently

## Mediator vs Other Patterns

| Pattern                     | Purpose                                | Key Difference             |
| --------------------------- | -------------------------------------- | -------------------------- |
| **Mediator**                | Centralize complex object interactions | Central coordinator        |
| **Observer**                | One-to-many notifications              | Decentralized observers    |
| **Facade**                  | Simplify subsystem interface           | Provides simplified access |
| **Pub-Sub**                 | Decouple publishers/subscribers        | Topic-based messaging      |
| **Chain of Responsibility** | Pass requests along chain              | Sequential processing      |

## Mediator Variations

### 1. **Callback-Based Mediator**

Uses callbacks for communication:

```java
interface ChatMediator {
    void broadcast(String message, ChatUser sender);
    void whisper(String message, ChatUser sender, ChatUser recipient);
}
```

### 2. **Event-Based Mediator**

Uses events for communication:

```java
class EventChatRoom implements ChatMediator {
    private EventBus eventBus;

    public void sendMessage(String message, User sender) {
        MessageEvent event = new MessageEvent(message, sender);
        eventBus.publish(event);
    }
}
```

### 3. **Hierarchy-Based Mediator**

Multiple mediators in hierarchy:

```java
class ChatRoom implements ChatMediator {
    private ChatMediator parentMediator;
    private List<User> localUsers;

    public void sendMessage(String message, User sender) {
        // Send to local users
        // Then send to parent mediator
    }
}
```

## Advanced Example: Dialogue Box Mediator

```java
interface DialogMediator {
    void registerComponent(DialogComponent component);
    void handleEvent(DialogComponent component, String event);
}

class DialogBox implements DialogMediator {
    private List<DialogComponent> components = new ArrayList<>();

    public void registerComponent(DialogComponent component) {
        components.add(component);
    }

    public void handleEvent(DialogComponent component, String event) {
        if (event.equals("submit")) {
            validateAll();
            submitForm();
        } else if (event.equals("clear")) {
            clearAll();
        }
    }

    private void validateAll() {
        for (DialogComponent comp : components) {
            if (!comp.validate()) {
                System.out.println("Validation failed");
                return;
            }
        }
    }
}

abstract class DialogComponent {
    protected DialogMediator mediator;

    public DialogComponent(DialogMediator mediator) {
        this.mediator = mediator;
        mediator.registerComponent(this);
    }

    abstract void update();
    abstract boolean validate();
}

class TextField extends DialogComponent {
    private String value;

    public TextField(DialogMediator mediator) {
        super(mediator);
    }

    public void setValue(String value) {
        this.value = value;
        mediator.handleEvent(this, "valueChanged");
    }

    @Override
    void update() {
        System.out.println("TextFieldUpdated: " + value);
    }

    @Override
    boolean validate() {
        return value != null && !value.isEmpty();
    }
}
```

## Advanced Example: Air Traffic Control

```java
interface AirTrafficControlMediator {
    void registerAircraft(Aircraft aircraft);
    void requestLanding(Aircraft aircraft);
    void grantLanding(Aircraft aircraft);
    void requestTakeoff(Aircraft aircraft);
    void grantTakeoff(Aircraft aircraft);
}

class AirportControlTower implements AirTrafficControlMediator {
    private List<Aircraft> aircraft = new ArrayList<>();
    private boolean runwayFree = true;

    public void registerAircraft(Aircraft aircraft) {
        aircraft.add(aircraft);
    }

    public void requestLanding(Aircraft plane) {
        if (runwayFree) {
            grantLanding(plane);
        } else {
            plane.hold();  // Circle until runway is free
        }
    }

    public void grantLanding(Aircraft plane) {
        runwayFree = false;
        plane.land();
        runwayFree = true;
    }
}

class Aircraft {
    private String name;
    private AirTrafficControlMediator mediator;

    public Aircraft(String name, AirTrafficControlMediator mediator) {
        this.name = name;
        this.mediator = mediator;
        mediator.registerAircraft(this);
    }

    public void requestLanding() {
        mediator.requestLanding(this);
    }

    public void land() {
        System.out.println(name + " landing...");
    }

    public void hold() {
        System.out.println(name + " holding pattern...");
    }
}
```

## Best Practices

✅ **Keep Mediator Focused**: Don't let mediator become too complex  
✅ **Define Clear Interface**: Make mediator interface clear and minimal  
✅ **Document Communication**: Document how colleagues interact  
✅ **Single Responsibility**: Mediator should have one reason to change  
✅ **Testability**: Test mediator independently from colleagues  
✅ **Error Handling**: Handle communication errors gracefully  
✅ **Performance**: Consider performance with many colleagues

## Common Mistakes

❌ **God Object**: Mediator doing too much

```java
// WRONG - Mediator handles everything
class BadMediator {
    public void everythingElse() {
        // Database operations
        // File I/O
        // Calculations
        // etc.
    }
}

// RIGHT - Mediator only coordinates
class GoodMediator {
    public void coordinate(ColleagueA a, ColleagueB b) {
        a.action1();
        b.action2();
    }
}
```

❌ **Circular Dependencies**: Creating mediator cycles

```java
// WRONG - Circular dependency
class UserMediator {
    public void send(User sender) {
        mediator.send(sender);  // Calling itself
    }
}

// RIGHT - Clear flow
class ChatRoom {
    public void sendMessage(User sender) {
        // Distribute to others
    }
}
```

❌ **Direct Communication**: Bypassing the mediator

```java
// WRONG - Defeats purpose of pattern
user1.receive(message);  // Direct call, not through mediator

// RIGHT - Always through mediator
mediator.sendMessage(message, user1);
```

## When NOT to Use Mediator Pattern

- When objects rarely interact with each other
- When interactions are simple and direct is clearer
- When performance is critical and mediator adds overhead
- When mediator would become overly complex
- When system only has two objects communicating

## Mediator vs Observer Pattern

| Aspect           | Mediator                       | Observer                              |
| ---------------- | ------------------------------ | ------------------------------------- |
| **Relationship** | Many-to-many with coordinator  | One-to-many with subject              |
| **Flow**         | Centralized through mediator   | Direct from subject to observers      |
| **Coupling**     | Colleagues coupled to mediator | Observers coupled to subject          |
| **Scalability**  | Mediator can become bottleneck | Scales better with many observers     |
| **Control**      | Mediator controls interaction  | Subject has no control over observers |
| **Use Case**     | Complex interactions           | Simple notifications                  |

## Related Patterns

- **Observer Pattern**: Similar decoupling but different structure
- **Facade Pattern**: Simplifies subsystem but doesn't coordinate interaction
- **Command Pattern**: Can be used within mediator for queuing
- **Strategy Pattern**: Can be used for different interaction strategies
- **Composite Pattern**: Can mediate composite object interactions
- **State Pattern**: Mediator can manage colleague states

## Complete Example: Document Collaboration

```java
interface DocumentMediator {
    void registerEditor(Editor editor);
    void updateContent(String content, Editor source);
    void showStatus(String status);
}

class SharedDocument implements DocumentMediator {
    private List<Editor> editors = new ArrayList<>();
    private String content = "";

    public void registerEditor(Editor editor) {
        editors.add(editor);
    }

    public void updateContent(String content, Editor source) {
        this.content = content;
        showStatus("Document updated");

        // Notify all other editors
        for (Editor editor : editors) {
            if (editor != source) {
                editor.displayContent(content);
            }
        }
    }

    public void showStatus(String status) {
        System.out.println("[Status] " + status);
    }
}

abstract class Editor {
    protected DocumentMediator mediator;
    protected String name;

    public Editor(DocumentMediator mediator, String name) {
        this.mediator = mediator;
        this.name = name;
        mediator.registerEditor(this);
    }

    public void editContent(String newContent) {
        System.out.println(name + " editing: " + newContent);
        mediator.updateContent(newContent, this);
    }

    public void displayContent(String content) {
        System.out.println(name + " displaying: " + content);
    }
}

// Usage
DocumentMediator doc = new SharedDocument();
Editor editor1 = new ConcreteEditor(doc, "Editor1");
Editor editor2 = new ConcreteEditor(doc, "Editor2");

editor1.editContent("Hello World");
// Both editors see the update
```
