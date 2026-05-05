# State Pattern

## Overview

The **State Pattern** is a behavioral design pattern that allows an object to alter its behavior when its internal state changes. The pattern appears to change its class when the internal state changes. It lets you define state-specific behavior and encapsulate each state in a separate class. The context object delegates state-specific behavior to its current state object, which can transition to different states.

## Key Characteristics

- **State-Specific Behavior**: Each state encapsulates specific behavior
- **Dynamic Behavior Change**: Behavior changes based on state
- **State Transition**: States can transition to other states
- **Eliminates Conditionals**: Replaces if-else chains for state checks
- **Single Responsibility**: Each state class has one responsibility

## Problem it Solves

Without the State Pattern, you'd need complex conditional logic:

```java
// Without State Pattern - Hard to maintain
public void pressPlay() {
    if (state == PLAYING) {
        System.out.println("Pausing the music...");
        state = PAUSED;
    } else if (state == PAUSED) {
        System.out.println("Resuming the music...");
        state = PLAYING;
    } else if (state == STOPPED) {
        System.out.println("Starting the music...");
        state = PLAYING;
    }
}
```

With the State Pattern, each state handles its own behavior using polymorphism.

## Pattern Components

### 1. **State Interface**

Defines the contract for all states:

```java
interface PlayerState {
    void pressPlay(MediaPlayer player);
}
```

### 2. **Concrete States**

Implements state-specific behavior:

```java
class PlayingState implements PlayerState {
    public void pressPlay(MediaPlayer player) {
        System.out.println("Pausing the music...");
        player.setState(new PausedState());
    }
}

class PausedState implements PlayerState {
    public void pressPlay(MediaPlayer player) {
        System.out.println("Resuming the music...");
        player.setState(new PlayingState());
    }
}

class StoppedState implements PlayerState {
    public void pressPlay(MediaPlayer player) {
        System.out.println("Starting the music...");
        player.setState(new PlayingState());
    }
}
```

### 3. **Context**

Maintains state and delegates to current state:

```java
class MediaPlayer {
    private PlayerState state;

    public MediaPlayer() {
        state = new StoppedState(); // initial state
    }

    public void setState(PlayerState state) {
        this.state = state;
    }

    public void pressPlay() {
        state.pressPlay(this);
    }
}
```

## How It Works

The State Pattern encapsulates state-specific behavior:

1. **Context Initialization**: MediaPlayer starts in StoppedState
2. **Action Triggered**: `pressPlay()` is called on the player
3. **Delegation**: Player delegates to current state's `pressPlay()` method
4. **State Transition**: State changes by calling `setState()` on context
5. **Behavior Change**: Next action uses the new state's behavior

## Usage Example

```java
MediaPlayer player = new MediaPlayer();

player.pressPlay(); // Starting the music...
player.pressPlay(); // Pausing the music...
player.pressPlay(); // Resuming the music...
```

### Output:

```
Starting the music...
Pausing the music...
Resuming the music...
```

Each call results in different behavior based on the current state.

## State Pattern Workflow

```
MediaPlayer Creation
    ↓
state = StoppedState
    ↓
player.pressPlay()
    ↓
StoppedState.pressPlay(player)
    ├─→ Prints: "Starting the music..."
    └─→ player.setState(PlayingState)
    ↓
state = PlayingState
    ↓
player.pressPlay()
    ↓
PlayingState.pressPlay(player)
    ├─→ Prints: "Pausing the music..."
    └─→ player.setState(PausedState)
    ↓
state = PausedState
```

## State Transition Diagram

```
          ┌──────────────┐
          │ StoppedState │
          └──────┬───────┘
                 │
         pressPlay() │
                 ↓
          ┌──────────────┐
    ┌────→│ PlayingState │◄────┐
    │     └──────┬───────┘     │
    │            │              │
    │      pressPlay()    pressPlay()
    │            ↓              │
    │     ┌──────────────┐      │
    └─────│ PausedState  │──────┘
          └──────────────┘
```

## State Transition Table

| Current State | Action      | Next State   | Action Output           |
| ------------- | ----------- | ------------ | ----------------------- |
| StoppedState  | pressPlay() | PlayingState | "Starting the music..." |
| PlayingState  | pressPlay() | PausedState  | "Pausing the music..."  |
| PausedState   | pressPlay() | PlayingState | "Resuming the music..." |
| PlayingState  | pressPlay() | PausedState  | "Pausing the music..."  |

## Advantages

✅ **Eliminates Conditionals**: Replaces if-else chains with polymorphism  
✅ **Single Responsibility**: Each state has one reason to change  
✅ **Open/Closed Principle**: Open for extension, closed for modification  
✅ **Easy to Add States**: New states can be added without modifying context  
✅ **Clear State Logic**: Each state's behavior is clearly defined  
✅ **Testable**: Each state can be tested independently

## Disadvantages

❌ **Complexity**: Adds more classes and complexity  
❌ **Memory Overhead**: Each state is a separate object  
❌ **Overkill for Simple Cases**: May be unnecessary for few states  
❌ **Indirection**: Behavior is less obvious than direct conditionals  
❌ **Context Exposure**: States need access to context for transitions

## Real-World Examples

- **Media Players**: Play, Pause, Stop states
- **Traffic Lights**: Red, Yellow, Green states
- **Document Editing**: Draft, Review, Published states
- **Order Processing**: New, Paid, Shipped, Delivered states
- **TCP Connections**: Established, Listen, Closed states
- **Game Character States**: Walking, Running, Jumping, Falling states
- **Workflow Systems**: Pending, Approved, Rejected, Completed states
- **Authentication**: Logged Out, Logged In, Session Expired states
- **File Upload**: Uploading, Paused, Completed, Failed states
- **State Machines**: Any system with distinct states and transitions

## Use Cases

- When object behavior depends on internal state
- When object has many state-dependent behaviors
- When operations have different implementations based on state
- When you want to eliminate large conditional statements
- When you need to make state transitions explicit

## State vs Strategy Pattern

Both patterns have similar structure but different purposes:

| Aspect             | State                                 | Strategy                 |
| ------------------ | ------------------------------------- | ------------------------ |
| **Purpose**        | Alter behavior when state changes     | Select algorithm         |
| **Responsibility** | State changes behavior                | Client selects algorithm |
| **Intent**         | Behavior varies with object state     | Behavior is selectable   |
| **Usage**          | Object lifetime spans multiple states | Algorithm selected once  |
| **Interaction**    | State changes context state           | Strategy stays same      |

## State Pattern Variants

### 1. **Self-Transitioning States** (What we implemented)

States are responsible for transitions:

```java
public void pressPlay(MediaPlayer player) {
    player.setState(new PausedState());
}
```

### 2. **Context-Based Transitions**

Context controls state transitions:

```java
public void pressPlay() {
    state.pressPlay();
    if (state instanceof PlayingState) {
        state = new PausedState();
    }
}
```

### 3. **Enum-Based States**

```java
enum PlayerStateEnum {
    STOPPED, PLAYING, PAUSED;

    public PlayerStateEnum transition() {
        switch(this) {
            case STOPPED: return PLAYING;
            case PLAYING: return PAUSED;
            case PAUSED: return PLAYING;
        }
        return this;
    }
}
```

## Comparison: Without and With State Pattern

### Without State Pattern:

```java
class MediaPlayer {
    private String state = "STOPPED";

    public void pressPlay() {
        if (state.equals("STOPPED")) {
            System.out.println("Starting the music...");
            state = "PLAYING";
        } else if (state.equals("PLAYING")) {
            System.out.println("Pausing the music...");
            state = "PAUSED";
        } else if (state.equals("PAUSED")) {
            System.out.println("Resuming the music...");
            state = "PLAYING";
        }
    }
}
```

### With State Pattern:

```java
class MediaPlayer {
    private PlayerState state;

    public void pressPlay() {
        state.pressPlay(this);  // Delegation
    }
}
```

## Best Practices

✅ **State Immutability**: Make states immutable and stateless when possible  
✅ **Single Responsibility**: Each state handles one behavior  
✅ **Clear Transitions**: Make state transitions explicit  
✅ **Singleton States**: Reuse state instances if stateless  
✅ **Documentation**: Clearly document state transitions  
✅ **Error Handling**: Handle invalid state transitions gracefully  
✅ **Testing**: Test each state independently

## Advanced Example: Singleton States

```java
abstract class PlayerState {
    protected static PlayingState playing = new PlayingState();
    protected static PausedState paused = new PausedState();
    protected static StoppedState stopped = new StoppedState();

    public abstract void pressPlay(MediaPlayer player);
}

class PlayingState extends PlayerState {
    public void pressPlay(MediaPlayer player) {
        System.out.println("Pausing the music...");
        player.setState(paused);  // Reuse singleton
    }
}

// Usage - same state instance reused
MediaPlayer player = new MediaPlayer();
player.pressPlay();  // Uses singleton instance
```

## Advanced Example: State History

```java
class MediaPlayerWithHistory {
    private PlayerState state;
    private Stack<PlayerState> history = new Stack<>();

    public void setState(PlayerState newState) {
        history.push(state);
        state = newState;
    }

    public void previousState() {
        if (!history.isEmpty()) {
            state = history.pop();
        }
    }
}
```

## Advanced Example: Transition Validation

```java
interface PlayerState {
    void pressPlay(MediaPlayer player);
    boolean canTransitionTo(PlayerState newState);
}

class PlayingState implements PlayerState {
    public void pressPlay(MediaPlayer player) {
        PausedState newState = new PausedState();
        if (canTransitionTo(newState)) {
            System.out.println("Pausing the music...");
            player.setState(newState);
        } else {
            System.out.println("Cannot transition to PausedState");
        }
    }

    public boolean canTransitionTo(PlayerState newState) {
        return newState instanceof PausedState;
    }
}
```

## Common Mistakes

❌ **Context Logic in States**: Don't put context logic in states

```java
// WRONG - State shouldn't know context details
public void pressPlay(MediaPlayer player) {
    if (player.isNetworkAvailable()) {  // Wrong!
        player.setState(new PlayingState());
    }
}

// RIGHT - Keep state logic simple
public void pressPlay(MediaPlayer player) {
    player.setState(new PlayingState());  // Simple transition
}
```

❌ **Circular Transitions**: Creating infinite loops

```java
// WRONG - Creates infinite loop
class PlayingState implements PlayerState {
    public void pressPlay(MediaPlayer player) {
        player.setState(new PlayingState());  // Same state!
    }
}
```

❌ **State Duplication**: Creating identical state instances

```java
// WRONG - Creates new instance each time
public void setState(PlayerState state) {
    this.state = new PlayingState();  // Wasteful
}

// RIGHT - Reuse state instances
public void setState(PlayerState state) {
    this.state = state;  // Reuse
}
```

## When NOT to Use State Pattern

- When object has only one or two states
- When state logic is simple and unlikely to change
- When performance is critical and state instantiation is expensive
- When states rarely change after initialization
- When conditional logic is already clear and simple

## Related Patterns

- **Strategy Pattern**: Similar structure but different purpose
- **Command Pattern**: Can be combined with State for actions
- **Observer Pattern**: Can notify when state changes
- **Singleton Pattern**: States often implemented as singletons
- **Factory Pattern**: Used to create state instances
- **Template Method Pattern**: Can define common state behavior
- **Decorator Pattern**: Can wrap state with additional behavior

## State Machine Example: Complete Implementation

```java
interface TrafficLightState {
    void next(TrafficLight light);
    void previous(TrafficLight light);
    void show();
}

class RedLight implements TrafficLightState {
    public void next(TrafficLight light) {
        light.setState(new GreenLight());
    }

    public void previous(TrafficLight light) {
        light.setState(new YellowLight());
    }

    public void show() {
        System.out.println("🔴 Red Light - Stop!");
    }
}

class YellowLight implements TrafficLightState {
    public void next(TrafficLight light) {
        light.setState(new RedLight());
    }

    public void previous(TrafficLight light) {
        light.setState(new GreenLight());
    }

    public void show() {
        System.out.println("🟡 Yellow Light - Prepare!");
    }
}

class GreenLight implements TrafficLightState {
    public void next(TrafficLight light) {
        light.setState(new YellowLight());
    }

    public void previous(TrafficLight light) {
        light.setState(new RedLight());
    }

    public void show() {
        System.out.println("🟢 Green Light - Go!");
    }
}

class TrafficLight {
    private TrafficLightState state;

    public TrafficLight() {
        state = new RedLight();
    }

    public void setState(TrafficLightState state) {
        this.state = state;
    }

    public void next() {
        state.next(this);
    }

    public void show() {
        state.show();
    }
}

// Usage
TrafficLight light = new TrafficLight();
light.show();   // 🔴 Red Light - Stop!
light.next();
light.show();   // 🟢 Green Light - Go!
```
