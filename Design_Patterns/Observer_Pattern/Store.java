package Design_Patterns.Observer_Pattern;

import Design_Patterns.Observer_Pattern.Observables.DispatchedObersvable;
import Design_Patterns.Observer_Pattern.Observables.StockOversable;
import Design_Patterns.Observer_Pattern.Observers.AppNotificationAlert;
import Design_Patterns.Observer_Pattern.Observers.EmailNotificationAlert;

public class Store {
    public static void main(String[] args) {
        StockOversable stockOb = new StockOversable();
        DispatchedObersvable disOb = new DispatchedObersvable();

        AppNotificationAlert observer1 = new AppNotificationAlert("Aritra", stockOb);
        AppNotificationAlert observer2 = new AppNotificationAlert("Niladri", stockOb);
        AppNotificationAlert observer3 = new AppNotificationAlert("Shoal", stockOb);

        AppNotificationAlert observer4 = new AppNotificationAlert("Shoal", disOb);
        EmailNotificationAlert observer5 = new EmailNotificationAlert("ss@gmail.com", disOb);

        stockOb.add(observer1);
        stockOb.add(observer2);
        stockOb.add(observer3);

        disOb.add(observer4);
        disOb.add(observer5);

        stockOb.setStockCount(10);
        disOb.dispatch();
    }

}
