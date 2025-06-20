package Design_Patterns.Observer_Pattern.Observers;

import Design_Patterns.Observer_Pattern.Observables.DispatchedObersvable;

public class EmailNotificationAlert implements Observer {
    String emailId;
    DispatchedObersvable observable;

    public EmailNotificationAlert(String emailId, DispatchedObersvable obersvable) {
        this.emailId = emailId;
        this.observable = obersvable;
    }

    public void update() {
        sendMail(emailId, "Your order has be dispatched!!!!!!");
    }

    private void sendMail(String emailId, String msg) {
        System.out.println(msg);
    }
}
