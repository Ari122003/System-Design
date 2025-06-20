package Design_Patterns.Observer_Pattern.Observables;

import Design_Patterns.Observer_Pattern.Observers.Observer;

public interface Observable {
    public void add(Observer observer);

    public void remove(Observer observer);

    public void notifyUsers();
}
