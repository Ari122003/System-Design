package Design_Patterns.Observer_Pattern.Observables;

import java.util.ArrayList;

import Design_Patterns.Observer_Pattern.Observers.Observer;

public class DispatchedObersvable implements Observable {
    private ArrayList<Observer> observerList = new ArrayList<>();

    private boolean hasDispatched = false;

    @Override
    public void add(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void remove(Observer observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifyUsers() {
        for (Observer obs : observerList) {
            obs.update();
        }
    }

    public void dispatch() {
        if (!hasDispatched) {
            hasDispatched = true;
            notifyUsers();
        }
    }

    public boolean isDispatched() {
        return hasDispatched;
    }

}
