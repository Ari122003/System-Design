package Design_Patterns.Behavioral.Mediator_Pattern;

import java.util.*;

// Mediator Interface
interface ChatMediator {
    void sendMessage(String message, User sender);

    void addUser(User user);
}

// Concrete Mediator
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

// Abstract Colleague

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

// Concrete Colleague
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

public class Main {
    public static void main(String[] args) {

        ChatMediator chatRoom = new ChatRoom();

        User user1 = new ChatUser(chatRoom, "Alice");
        User user2 = new ChatUser(chatRoom, "Bob");
        User user3 = new ChatUser(chatRoom, "Charlie");

        chatRoom.addUser(user1);
        chatRoom.addUser(user2);
        chatRoom.addUser(user3);

        user1.send("Hello everyone!");
        user2.send("Hi Alice!");
    }

}
