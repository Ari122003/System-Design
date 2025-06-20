package Design_Patterns.Observer_Pattern.Observers;

import Design_Patterns.Observer_Pattern.Observables.DispatchedObersvable;
import Design_Patterns.Observer_Pattern.Observables.StockOversable;

public class AppNotificationAlert implements Observer {
    String userName;
    StockOversable stockOb;
    DispatchedObersvable disOb;

    public AppNotificationAlert(String userName, StockOversable stockOb) {
        this.userName = userName;
        this.stockOb = stockOb;
    }

    public AppNotificationAlert(String userName, DispatchedObersvable disOb) {
        this.userName = userName;
        this.disOb = disOb;
    }

    public void update() {
        if (stockOb != null && stockOb.getStock() > 0) {
            sendNotification("Hi " + userName + ", Stock has been updated! New stock: " + stockOb.getStock());
        }

        if (disOb != null && disOb.isDispatched()) {
            sendNotification("Hi " + userName + ", Your order has been dispatched!");
        }
    }

    private void sendNotification(String msg) {
        System.out.println(msg);
    }
}
