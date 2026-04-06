package Design_Patterns.Behavioral.Observer_Pattern;

public interface Channel {
    void subscribe(Subscriber sub);

    void unsubscribe(Subscriber sub);

    void notifySubscribers();
}
