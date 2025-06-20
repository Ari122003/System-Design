package Design_Patterns.Observer_Pattern.Observables;

import java.util.ArrayList;

import Design_Patterns.Observer_Pattern.Observers.Observer;

public class StockOversable implements Observable {

    private ArrayList<Observer> observerList = new ArrayList<>();

    private int stock = 0;

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

    public void setStockCount(int newStock) {
        if (stock == 0) {
            stock += newStock;
            notifyUsers();
        } else {
            stock += newStock;
        }
    }

    public int getStock() {
        return stock;
    }

}
