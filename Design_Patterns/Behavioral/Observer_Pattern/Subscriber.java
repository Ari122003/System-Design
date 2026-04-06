package Design_Patterns.Behavioral.Observer_Pattern;

// Observer

public class Subscriber implements User {

    private String name;

    Subscriber(String name) {
        this.name = name;
    }

    @Override
    public void update(String msg) {
        System.out.println(name + ":" + msg);
    }

}
