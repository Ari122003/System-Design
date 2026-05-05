package Design_Patterns.Behavioral.Observer_Pattern;

import java.util.*;

// Observer interface
interface Observer {
    void update(String message);
}

// Subject interface
interface Subject {
    void addObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObservers();
}

// Concrete Subject

class YouTubeChannel implements Subject {
    private List<Observer> subscribers = new ArrayList<>();
    private String videoTitle;

    public void uploadVideo(String title) {
        this.videoTitle = title;
        notifyObservers();
    }

    public void addObserver(Observer observer) {
        subscribers.add(observer);
    }

    public void removeObserver(Observer observer) {
        subscribers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer sub : subscribers) {
            sub.update(videoTitle);
        }
    }
}

// Concrete Observer
class Subscriber implements Observer {
    private String name;

    public Subscriber(String name) {
        this.name = name;
    }

    public void update(String videoTitle) {
        System.out.println(name + " received notification: New video - " + videoTitle);
    }
}

public class Main {
    public static void main(String[] args) {
        YouTubeChannel channel = new YouTubeChannel();

        Observer user1 = new Subscriber("Aritra");
        Observer user2 = new Subscriber("Rahul");

        channel.addObserver(user1);
        channel.addObserver(user2);

        channel.uploadVideo("Observer Pattern Explained");

        channel.removeObserver(user1);

        channel.uploadVideo("Java Design Patterns");

    }
}